plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytic)
    alias(libs.plugins.firebase.performance.monitoring)
}

android {
    namespace = "com.lelestacia.lelenime"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.lelestacia.lelenime"
        minSdk = 26
        targetSdk = 33
        versionCode = 2
        versionName = "2.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        create("benchmark") {
            isMinifyEnabled = true
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
            excludes += "/META-INF/LICENSE"
            excludes += "/META-INF/LICENSE.txt"
            excludes += "/META-INF/NOTICE"
            excludes += "/META-INF/NOTICE.txt"
            excludes += "/META-INF/DEPENDENCIES"
            excludes += "/META-INF/*.kotlin_module"
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
    val composeBOM = platform(libs.compose.bom)
    implementation(composeBOM)
    implementation(libs.compose.activity)
    implementation(libs.bundles.compose.toolkit)
    implementation(libs.compose.navigation)
    androidTestImplementation(composeBOM)
    androidTestImplementation(libs.compose.junit)
    debugImplementation(libs.bundles.compose.tooling.and.manifest)

    // KTX
    implementation(libs.kotlin.ktx)

    // Lifecycle
    implementation(libs.lifecycle.runtime)

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
    implementation(libs.accompanist.navigation.animation)
    implementation(libs.accompanist.navigation.bottomsheet)

    //  Profiler
    implementation(libs.baseline.profiler)

    //  Firebase
    implementation(libs.firebase.crashlytics.ktx)
    implementation(libs.firebase.analytics.ktx)
    implementation(libs.firebase.perf.ktx)
}

kapt {
    correctErrorTypes = true
    showProcessorStats = true
    useBuildCache = true
}
