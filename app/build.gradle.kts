plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    //id("com.google.relay") version "0.3.11"

//    id ("com.google.dagger.hilt.android")
//    id ("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")

    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id ("dagger.hilt.android.plugin")
    id ("kotlin-kapt")



}

android {
    namespace = "com.example.uberapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.uberapp"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")




    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")

            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    implementation("com.google.code.gson:gson:2.9.0")






    //Lifecycle




    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")
    //Google Servic("s & Maps

    implementation ("com.google.accompanist:accompanist-permissions:0.31.3-beta")

    //Hilt
//    implementation ("com.google.dagger:hilt-android:2.51")
//    kapt ("com.google.dagger:hilt-compiler:2.44")






    // maps compose

    // google map sevisec


    // google maps (
    implementation ("com.google.maps.android:android-maps-utils:3.4.0")






    // Google maps
    implementation ("com.google.android.gms:play-services-maps:18.2.0")
    implementation ("com.google.android.gms:play-services-location:21.2.0")
    // Google maps ("or compose
    implementation ("com.google.maps.android:maps-compose:2.11.5")

    // KTX for the ("aps SDK for Android
    implementation("com.google.maps.android:maps-ktx:3.3.0")
    // KTX for the ("aps SDK for Android Utility Library
    implementation("com.google.maps.android:maps-utils-ktx:3.2.1")
    // Hilt
    implementation ("com.google.dagger:hilt-android:2.51")
    kapt ("com.google.dagger:hilt-compiler:2.44")


//    implementation("com.google.android.gms:play-services-location:21.2.0")
//    implementation("com.google.accompanist:accompanist-permissions:0.35.0-alpha")
//    implementation("com.google.accompanist:accompanist-permissions:0.24.13-rc")

}