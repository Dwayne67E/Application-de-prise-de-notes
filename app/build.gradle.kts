plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
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
    implementation(libs.material.v1110beta01)
    implementation(libs.kotlin.stdlib.v1922)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat.v131)
    implementation(libs.material.v140)
    implementation(libs.androidx.constraintlayout)

    // Navigation components
    implementation(libs.androidx.navigation.fragment.ktx.v273)
    implementation(libs.androidx.navigation.ui.ktx.v273)

    // Hilt for dependency injection
    implementation(libs.hilt.android.v2381)
    kapt(libs.hilt.android.compiler)

    // Test dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Commented out the xmlpull library to avoid duplicates.
    // If you need it for some specific reason, uncomment the below line and ensure you are excluding any duplicates
    // implementation("xmlpull:xmlpull:1.1.3.1")
}

// Remove the implementation line for navigation.safe.args if it's causing issues.
