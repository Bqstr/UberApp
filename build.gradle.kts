// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:2.0.1")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.51")
    }
}
plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
//    id ("com.google.dagger.hilt.android") version "2.44" apply false
//    id ("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1" apply false

}

