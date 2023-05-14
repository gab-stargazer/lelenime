plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ktlint)
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
    //  Module Implementation
    implementation(project(":core:model"))

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

    //  Paging
    implementation(libs.paging.compose)

    // Android JUnit
    androidTestImplementation(libs.android.junit)

    // Junit
    testImplementation(libs.junit)

    //  Desugaring
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.3")
}
