// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false
    // Ensure Safe Args is included correctly
    id("androidx.navigation.safeargs") version "2.8.1" apply false
}
