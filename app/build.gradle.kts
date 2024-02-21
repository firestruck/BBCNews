import java.util.Properties

plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

val localProperties = Properties()
val localPropertiesFile: File = rootProject.file("local.properties")

if (localPropertiesFile.exists()) {
    localPropertiesFile.inputStream().use {
        localProperties.load(it)
    }
}
val apiKey = localProperties.getProperty("api.key") ?: "No API Key Found"

android {
    flavorDimensions += "newsSource"

    buildFeatures {
        buildConfig = true
    }
    defaultConfig {
        buildConfigField("String", "API_KEY", "\"$apiKey\"")
        buildConfigField("String", "BASE_URL", "\"https://newsapi.org\"")
    }

    productFlavors {
        create("bbcNews") {
            dimension = "newsSource"
            applicationIdSuffix = ".bbcnews"
            versionNameSuffix = "-bbcNews"

            buildConfigField("String", "SOURCE_NAME", "\"bbc-news\"")
        }
        create("cnnNews") {
            dimension = "newsSource"
            applicationIdSuffix = ".cnnnews"
            versionNameSuffix = "-cnnNews"
            buildConfigField("String", "SOURCE_NAME", "\"cnn\"")
        }
    }

    namespace = "com.dmanlancers.breakingnews"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.dmanlancers.breakingnews"
        minSdk = 24
        targetSdk = 34
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
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}
// Library versions declaration
val activityComposeVersion = rootProject.extra["activity-compose"]
val coreKtxVersion = rootProject.extra["core-ktx"]
val lifecycleRuntimeVersion = rootProject.extra["lifecycle-runtime"]
val composeBomVersion = rootProject.extra["compose-bom"]
val espressoCoreVersion = rootProject.extra["espresso-core"]
val daggerHiltVersion = rootProject.extra["dagger_hilt"]
val hiltNavigationComposeVersion = rootProject.extra["hilt-navigation-compose"]
val retrofitVersion = rootProject.extra["retrofit_version"]



dependencies {

    implementation("androidx.core:core-ktx:$coreKtxVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleRuntimeVersion")
    implementation("androidx.activity:activity-compose:$activityComposeVersion")
    implementation(platform("androidx.compose:compose-bom:$composeBomVersion"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.runtime:runtime-livedata")
    implementation("androidx.navigation:navigation-compose")
    api("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleRuntimeVersion")

    //DI
    implementation("androidx.hilt:hilt-navigation-compose:$hiltNavigationComposeVersion")
    implementation("com.google.dagger:hilt-android:2.48")
    implementation("androidx.appcompat:appcompat:1.6.1")
    ksp("com.google.dagger:hilt-android-compiler:2.48")

    // coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Coil
    implementation("io.coil-kt:coil-compose:2.4.0")

    //Network
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    //Debug
    implementation("com.jakewharton.timber:timber:5.0.1")

    //Auth
    implementation("androidx.biometric:biometric:1.2.0-alpha05")
    implementation ("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")

    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:4.0.0")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.9.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:$espressoCoreVersion")
    androidTestImplementation(platform("androidx.compose:compose-bom:$composeBomVersion"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}
