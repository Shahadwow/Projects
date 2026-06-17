// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0" apply false // Ensure compatibility with your Gradle version
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false // Ensure consistency across the project
    id("com.google.gms.google-services") version "4.3.15" apply false // Firebase Google Services Plugin
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir) // Clean build directory
}
