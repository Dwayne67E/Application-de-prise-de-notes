plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id ("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.devinci.notes_v1"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.devinci.notes_v1"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}
dependencies {
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    // Composant de base de données Room
    val room_version = "2.6.1"
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.room.compiler)
    implementation(libs.androidx.core.ktx)
// To use Kotlin annotation processing tool (kapt)
    kapt(libs.androidx.room.room.compiler)
    implementation(libs.androidx.room.ktx)
// Composants de cycle de vie
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
// LiveData
    implementation(libs.androidx.lifecycle.livedata.ktx)
    kapt(libs.androidx.lifecycle.compiler)
    implementation (libs.androidx.fragment.ktx)

    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation (libs.kotlin.stdlib)
    implementation(libs.hilt.android)
}