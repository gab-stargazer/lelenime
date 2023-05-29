plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.dokka)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.dagger.hilt.android)
}

android {
    namespace = "com.lelestacia.lelenime.core.network"
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
    buildFeatures {
        buildConfig = true
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    // Common Module
    implementation(project(":core:common"))

    // Retrofit + GSON
    implementation(libs.bundles.retrofit.gson)

    // Paging
    implementation(libs.paging.runtime)

    // Logging Interceptor
    implementation(libs.logging.interceptor)

    // Timber
    implementation(libs.timber)

    // Dagger-Hilt
    implementation(libs.dagger.hilt.module)
    kapt(libs.dagger.hilt.compiler)

    // Android JUnit
    androidTestImplementation(libs.android.junit)

    // Junit
    testImplementation(libs.junit)

    //  Mockk
    testImplementation(libs.mockk)

    //  Coroutine
    testImplementation(libs.coroutine.test)
}

kapt {
    correctErrorTypes = true
    showProcessorStats = true
    useBuildCache = true
}
