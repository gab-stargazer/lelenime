plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.lelestacia.lelenime.core.domain"
    compileSdk = 33

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
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
}

dependencies {
    // Module Implementation
    implementation(project(":core:common"))
    implementation(project(":core:data"))
    implementation(project(":core:model"))

    // Coroutine
    implementation(libs.bundles.coroutine)

    // Paging
    implementation(libs.paging.runtime)

    // Dagger Hilt
    implementation(libs.dagger.hilt.module)
    kapt(libs.dagger.hilt.compiler)

    // Android JUnit
    androidTestImplementation(libs.android.junit)

    // Junit
    testImplementation(libs.junit)
}

kapt {
    correctErrorTypes = true
    showProcessorStats = true
    useBuildCache = true
}
