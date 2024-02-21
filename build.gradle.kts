// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.devtools.ksp") version "1.9.20-1.0.14" apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false
}

buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48")
    }
    extra.apply {
        set("activity-compose", "1.8.2")
        set("core-ktx", "1.12.0")
        set("lifecycle-runtime", "2.7.0")
        set("compose-bom", "2023.08.00")
        set("espresso-core", "3.5.1")
        set("dagger_hilt", "2.46.1")
        set("hilt-navigation-compose", "1.1.0")
        set("retrofit_version", "2.9.0")
    }
}

