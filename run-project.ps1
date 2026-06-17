param(
  [switch]$SkipInstall,
  [switch]$SkipAndroid
)

$ErrorActionPreference = "Stop"

$RootDir = Split-Path -Parent $MyInvocation.MyCommand.Path
$AppDir = Join-Path $RootDir "SeniorProject-main"
$BackendDir = Join-Path $RootDir "sanad"
$AiBackendDir = Join-Path $BackendDir "backendAI"
$BackendPort = 3000
$AiBackendPort = 5000
$PreferredNodeVersion = "22.14.0"

function Write-Step {
  param([string]$Message)
  Write-Host ""
  Write-Host "==> $Message" -ForegroundColor Cyan
}

function Test-CommandExists {
  param([string]$Name)
  return $null -ne (Get-Command $Name -ErrorAction SilentlyContinue)
}

function Install-WingetPackage {
  param(
    [string]$CommandName,
    [string]$PackageId,
    [string]$DisplayName
  )

  if (Test-CommandExists $CommandName) {
    Write-Host "$DisplayName is already installed."
    return
  }

  if ($SkipInstall) {
    Write-Warning "$DisplayName is missing. Skipping install because -SkipInstall was used."
    return
  }

  if (-not (Test-CommandExists "winget")) {
    throw "winget is not installed. Install App Installer from Microsoft Store, then run this script again."
  }

  Write-Host "Installing $DisplayName with winget..."
  winget install --id $PackageId -e --accept-package-agreements --accept-source-agreements
  Update-SessionPath
}

function Update-SessionPath {
  $machinePath = [Environment]::GetEnvironmentVariable("Path", "Machine")
  $userPath = [Environment]::GetEnvironmentVariable("Path", "User")
  $env:Path = "$machinePath;$userPath"
}

function Add-DirectoryToSessionPath {
  param([string]$Directory)

  if (-not $Directory -or -not (Test-Path $Directory)) {
    return $false
  }

  $resolved = (Resolve-Path $Directory).Path
  $pathEntries = $env:Path -split ';' | Where-Object { $_ }

  if ($pathEntries -notcontains $resolved) {
    $env:Path = "$resolved;$env:Path"
  }

  return $true
}

function Add-AndroidPlatformToolsToPath {
  $candidateRoots = @(
    $env:ANDROID_HOME,
    $env:ANDROID_SDK_ROOT,
    (Join-Path $env:LOCALAPPDATA "Android\Sdk")
  ) | Where-Object { $_ } | Select-Object -Unique

  foreach ($root in $candidateRoots) {
    $platformTools = Join-Path $root "platform-tools"
    $adbPath = Join-Path $platformTools "adb.exe"

    if (Test-Path $adbPath) {
      Add-DirectoryToSessionPath -Directory $platformTools | Out-Null

      if (-not $env:ANDROID_HOME) {
        $env:ANDROID_HOME = $root
      }

      if (-not $env:ANDROID_SDK_ROOT) {
        $env:ANDROID_SDK_ROOT = $root
      }

      Write-Host "Android platform tools found at $platformTools"
      return $true
    }
  }

  return $false
}

function Configure-AndroidBuildEnvironment {
  $javaCandidates = @(
    $env:JAVA_HOME,
    "C:\Program Files\Java\jdk-17",
    "C:\Program Files\Microsoft\jdk-17"
  ) | Where-Object { $_ } | Select-Object -Unique

  foreach ($javaHome in $javaCandidates) {
    $javaExe = Join-Path $javaHome "bin\java.exe"

    if (Test-Path $javaExe) {
      $env:JAVA_HOME = $javaHome
      Add-DirectoryToSessionPath -Directory (Join-Path $javaHome "bin") | Out-Null
      Write-Host "JAVA_HOME set to $javaHome"
      break
    }
  }

  $androidHome = $env:ANDROID_HOME

  if (-not $androidHome) {
    $androidHome = $env:ANDROID_SDK_ROOT
  }

  if (-not $androidHome) {
    $androidHome = Join-Path $env:LOCALAPPDATA "Android\Sdk"
  }

  if (Test-Path $androidHome) {
    $env:ANDROID_HOME = $androidHome
    $env:ANDROID_SDK_ROOT = $androidHome

    Add-DirectoryToSessionPath -Directory (Join-Path $androidHome "platform-tools") | Out-Null
    Add-DirectoryToSessionPath -Directory (Join-Path $androidHome "emulator") | Out-Null

    Write-Host "ANDROID_HOME set to $androidHome"
  }
}

function Assert-NodeVersion {
  $rawVersion = (node --version).Trim().TrimStart("v")
  $currentVersion = [version]$rawVersion
  $requiredVersion = [version]"22.11.0"

  if ($currentVersion -lt $requiredVersion) {
    if (Test-CommandExists "nvm") {
      throw "Node.js $requiredVersion or newer is required. Current version is $currentVersion. Run: nvm use $PreferredNodeVersion"
    }

    throw "Node.js $requiredVersion or newer is required. Current version is $currentVersion. Run: winget upgrade OpenJS.NodeJS"
  }
}

function Use-PreferredNodeVersion {
  if (-not (Test-CommandExists "nvm")) {
    return
  }

  $activeVersion = (node --version).Trim().TrimStart("v")

  if ([version]$activeVersion -ge [version]"22.11.0") {
    return
  }

  $installedVersions = (nvm list) -join "`n"
  $versionPattern = [regex]::Escape($PreferredNodeVersion)

  if ($installedVersions -notmatch "(^|\s)$versionPattern(\s|$)") {
    Write-Warning "Node.js $PreferredNodeVersion is not installed in nvm. Run: nvm install $PreferredNodeVersion"
    return
  }

  Write-Host "Switching Node.js to $PreferredNodeVersion with nvm..."
  nvm use $PreferredNodeVersion
  Update-SessionPath

  $activeVersion = (node --version).Trim().TrimStart("v")

  if ([version]$activeVersion -lt [version]"22.11.0") {
    Write-Warning "nvm use did not update the active Node.js version in this terminal. Current version is $activeVersion."
  }
}

function Assert-ProjectFolders {
  if (-not (Test-Path $AppDir)) {
    throw "App folder not found: $AppDir"
  }

  if (-not (Test-Path $BackendDir)) {
    throw "Backend folder not found: $BackendDir"
  }

  if (-not (Test-Path $AiBackendDir)) {
    throw "AI backend folder not found: $AiBackendDir"
  }
}

function Install-NpmDependencies {
  param([string]$Directory)

  $nodeModules = Join-Path $Directory "node_modules"
  $packageLock = Join-Path $Directory "package-lock.json"

  if (Test-Path $nodeModules) {
    Write-Host "node_modules already exists in $Directory"
    return
  }

  Write-Host "Installing npm dependencies in $Directory"
  Push-Location $Directory
  try {
    if (Test-Path $packageLock) {
      npm ci
    } else {
      npm install
    }
  } finally {
    Pop-Location
  }
}

function Install-PythonDependencies {
  param([string]$Directory)

  $requirements = Join-Path $Directory "requirements.txt"

  if (-not (Test-Path $requirements)) {
    return
  }

  if ($SkipInstall) {
    Write-Host "Skipping Python dependency install because -SkipInstall was used."
    return
  }

  Write-Host "Installing Python dependencies in $Directory"
  Push-Location $Directory
  try {
    python -m pip install -r requirements.txt
  } finally {
    Pop-Location
  }
}

function Stop-OldProcessOnPortIfNeeded {
  param(
    [int]$Port,
    [string]$CommandLinePattern,
    [string]$DisplayName
  )

  $listeners = Get-NetTCPConnection -LocalPort $Port -State Listen -ErrorAction SilentlyContinue

  foreach ($listener in $listeners) {
    $process = Get-CimInstance Win32_Process -Filter "ProcessId=$($listener.OwningProcess)" -ErrorAction SilentlyContinue

    if ($process -and $process.CommandLine -like $CommandLinePattern) {
      Write-Host "Stopping existing $DisplayName process on port $Port (PID $($listener.OwningProcess))."
      Stop-Process -Id $listener.OwningProcess -Force
    } else {
      throw "Port $Port is already in use by PID $($listener.OwningProcess). Stop it manually or change the $DisplayName port."
    }
  }
}

function Start-ProjectProcess {
  param(
    [string]$Title,
    [string]$Directory,
    [string]$Command
  )

  $escapedTitle = $Title.Replace("'", "''")
  $escapedDir = $Directory.Replace("'", "''")
  $escapedCommand = $Command.Replace("'", "''")
  $arguments = "-NoExit -ExecutionPolicy Bypass -Command `"Set-Location '$escapedDir'; `$Host.UI.RawUI.WindowTitle = '$escapedTitle'; $escapedCommand`""

  Start-Process powershell.exe -ArgumentList $arguments
}

function Test-AndroidDevice {
  if (-not (Test-CommandExists "adb")) {
    return $false
  }

  $devices = adb devices | Select-String -Pattern "`tdevice$"
  return $null -ne $devices
}

function Configure-MetroForAndroid {
  if (-not (Test-CommandExists "adb")) {
    return
  }

  $devices = adb devices | Select-String -Pattern "`tdevice$"

  if ($null -eq $devices) {
    return
  }

  Write-Host "Configuring Android device to reach Metro on port 8081..."
  adb reverse tcp:8081 tcp:8081 | Out-Null
}

Assert-ProjectFolders

Write-Step "Checking required tools"
Install-WingetPackage -CommandName "git" -PackageId "Git.Git" -DisplayName "Git"
Install-WingetPackage -CommandName "node" -PackageId "OpenJS.NodeJS" -DisplayName "Node.js"
Install-WingetPackage -CommandName "python" -PackageId "Python.Python.3.11" -DisplayName "Python"
Install-WingetPackage -CommandName "ffmpeg" -PackageId "Gyan.FFmpeg" -DisplayName "FFmpeg"
Install-WingetPackage -CommandName "java" -PackageId "Microsoft.OpenJDK.17" -DisplayName "Java JDK 17"
Configure-AndroidBuildEnvironment
Add-AndroidPlatformToolsToPath | Out-Null
Install-WingetPackage -CommandName "adb" -PackageId "Google.PlatformTools" -DisplayName "Android platform tools"

if (-not (Test-CommandExists "npm")) {
  throw "npm was not found. Install Node.js, close this terminal, then run the script again."
}

if (-not (Test-CommandExists "python")) {
  throw "python was not found. Install Python, close this terminal, then run the script again."
}

Use-PreferredNodeVersion
Assert-NodeVersion

Write-Step "Installing project dependencies if needed"
Install-NpmDependencies -Directory $AppDir
Install-NpmDependencies -Directory $BackendDir
Install-PythonDependencies -Directory $AiBackendDir

Write-Step "Starting backend"
Stop-OldProcessOnPortIfNeeded -Port $BackendPort -CommandLinePattern "*server.js*" -DisplayName "backend"
Start-ProjectProcess -Title "SeniorProject Backend" -Directory $BackendDir -Command "npm start"

Write-Step "Starting AI backend"
Stop-OldProcessOnPortIfNeeded -Port $AiBackendPort -CommandLinePattern "*app.py*" -DisplayName "AI backend"
Start-ProjectProcess -Title "SeniorProject AI Backend" -Directory $AiBackendDir -Command "python app.py"

Write-Step "Starting Metro"
Start-ProjectProcess -Title "SeniorProject Metro" -Directory $AppDir -Command "npm start -- --host 0.0.0.0"

if ($SkipAndroid) {
  Write-Host "Skipping Android run because -SkipAndroid was used."
} elseif (Test-AndroidDevice) {
  Configure-MetroForAndroid
  Write-Step "Starting Android app"
  Start-ProjectProcess -Title "SeniorProject Android" -Directory $AppDir -Command "npm run android"
} else {
  Write-Warning "No Android device or emulator is connected. Backend and Metro were started. Open an emulator/device, then run: npm run android"
}

Write-Host ""
Write-Host "Done. Unified backend and website: http://192.168.0.117:$BackendPort" -ForegroundColor Green
Write-Host "AI backend: http://127.0.0.1:$AiBackendPort" -ForegroundColor Green
