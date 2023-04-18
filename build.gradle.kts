// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(libs.ktlint.gradle.linter)
        classpath(libs.gradle)
        classpath(libs.google.services)
        classpath(libs.firebase.crashlytics.gradle)
        classpath(libs.perf.plugin)
    }
}

plugins {
    alias(libs.plugins.android.application.plugin) apply false
    alias(libs.plugins.android.library.plugin) apply false
    alias(libs.plugins.kotlin.android.plugin) apply false
    alias(libs.plugins.dagger.hilt.module.plugin) apply false
    alias(libs.plugins.com.android.test) apply false
}

allprojects {
    apply(plugin = ("org.jlleitschuh.gradle.ktlint"))
}
