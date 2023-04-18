plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.lelestacia.lelenime.core.common"
    compileSdk = 33

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    //  Module Implementation
    implementation(project(":core:model"))

    //  Compose Toolkit
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose.toolkit)
    implementation(libs.compose.navigation)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.compose.junit)
    debugImplementation(libs.bundles.compose.tooling.and.manifest)

    //  Coil
    implementation(libs.coil.compose)

    //  Paging
    implementation(libs.paging.compose)

    // Android JUnit
    androidTestImplementation(libs.android.junit)

    // Junit
    testImplementation(libs.junit)

    //  Desugaring
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.3")
}
