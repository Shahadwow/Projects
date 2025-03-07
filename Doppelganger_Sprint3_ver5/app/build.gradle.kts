plugins {
    id("com.android.application") // Android plugin
    id("org.jetbrains.kotlin.android") version "1.9.0" // Updated to a compatible Kotlin version
    id("com.google.gms.google-services") version "4.3.15" // Updated Firebase Google Services plugin
}

android {
    namespace = "com.doppelganger"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.doppelganger"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Firebase dependencies
    implementation ("com.google.firebase:firebase-firestore:24.5.0")
    implementation(platform("com.google.firebase:firebase-bom:32.2.0"))
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-database")
    implementation("com.google.firebase:firebase-storage")

    // Core AndroidX dependencies
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Material Design components
    implementation("com.google.android.material:material:1.9.0")

    // Glide for image handling
    implementation("com.github.bumptech.glide:glide:4.12.0")
    implementation(libs.activity)
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")

    // Unit and UI Testing dependencies
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

apply(plugin = "com.google.gms.google-services")
