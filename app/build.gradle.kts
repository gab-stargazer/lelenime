plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.lelestacia.lelenime"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.lelestacia.lelenime"
        minSdk = 26
        targetSdk = 33
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
        create("benchmark") {
            initWith(buildTypes.getByName("release"))
            signingConfig = signingConfigs.getByName("debug")
            matchingFallbacks += listOf("release")
            isDebuggable = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    //  Module Implementation
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":core:domain"))
    implementation(project(":feature:explore"))
    implementation(project(":feature:collection"))
    implementation(project(":feature:detail"))
    implementation(project(":feature:more"))

    //  Compose Toolkit
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose.toolkit)
    implementation(libs.compose.navigation)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.compose.junit)
    debugImplementation(libs.bundles.compose.tooling.and.manifest)

    // KTX
    implementation(libs.kotlin.ktx)

    // Lifecycle
    implementation(libs.lifecycle.runtime)

    //  Activity Compose
    implementation(libs.activity.compose)

    // Android JUnit
    androidTestImplementation(libs.android.junit)

    // Junit
    testImplementation(libs.junit)

    // Espresso UI Test
    androidTestImplementation(libs.espresso)

    //  Dagger Hilt
    implementation(libs.dagger.hilt.module)
    kapt(libs.dagger.hilt.compiler)
    implementation(libs.dagger.hilt.compose)

    //  Timber
    implementation(libs.timber)

    //  Accompanist
    implementation(libs.accompanist.system.ui.controller)
    implementation(libs.accompanist.animation.navigation)

    //  Profiler
    implementation(libs.baseline.profiler)
}

kapt {
    correctErrorTypes = true
    showProcessorStats = true
    useBuildCache = true
}
