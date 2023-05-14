plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.dagger.hilt.android)
}

android {
    namespace = "com.lelestacia.lelenime.feature.more"
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    // Module Implementation
    implementation(project(":core:common"))
    implementation(project(":core:domain"))

    //  Compose Toolkit
    val composeBOM = platform(libs.compose.bom)
    implementation(composeBOM)
    implementation(libs.bundles.compose.toolkit)
    implementation(libs.compose.navigation)
    androidTestImplementation(composeBOM)
    androidTestImplementation(libs.compose.junit)
    debugImplementation(libs.bundles.compose.tooling.and.manifest)

    //  Coil
    implementation(libs.coil.compose)

    // Android JUnit
    androidTestImplementation(libs.android.junit)

    // Junit
    testImplementation(libs.junit)

    // Dagger-Hilt
    implementation(libs.dagger.hilt.module)
    kapt(libs.dagger.hilt.compiler)
}
