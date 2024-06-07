plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id ("kotlin-kapt")


}

android {
    namespace = "com.example.passwordmanager"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.passwordmanager"
        minSdk = 24
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.4.32")
    implementation ("androidx.room:room-runtime:2.6.1")


    implementation ("androidx.compose.ui:ui:1.6.7")
    implementation ("androidx.navigation:navigation-compose:2.4.0")

    implementation ("androidx.navigation:navigation-compose:2.7.7-alpha05")

    val roomversion = "2.4.2"

    implementation ("androidx.room:room-runtime:$roomversion")
    implementation ("androidx.room:room-ktx:$roomversion")

    kapt ("androidx.room:room-compiler:$roomversion")
    testImplementation ("androidx.room:room-testing:$roomversion")
    androidTestImplementation("android.room:room-testing:$roomversion")
    implementation ("androidx.room:room-runtime:2.4.3")
    kapt ("androidx.room:room-compiler:2.4.3")
    implementation( "androidx.room:room-ktx:2.4.3")
    implementation( "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    var room_version = "2.3.0"
    implementation("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")

// Coroutines
    var coroutines_version = "1.3.9"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")



}