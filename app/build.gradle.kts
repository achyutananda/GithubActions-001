import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.slab.githubactions_001"
    compileSdk {
        version = release(36)
    }
    val localProperties = Properties().apply {
        val file = rootProject.file("local.properties")
        if (file.exists())
            load(file.inputStream())
    }

    defaultConfig {
        applicationId = "com.slab.githubactions_001"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        val apiKey = System.getenv("API_KEY")?: localProperties["API_KEY"]?:"API Key Not Set"
        val baseUrl = System.getenv("BASE_URL")?: localProperties["BASE_URL"]?:"Base URL Not Set"
        buildConfigField(
            type = "String",
            name = "API_KEY",
            value = "\"${apiKey}\""
        )
        buildConfigField("String","BASE_URL","\"${baseUrl}\"")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
//    flavorDimensions += listOf("env", "tier")
//    productFlavors {
//        create("uat") {
//            dimension = "env"
//            applicationIdSuffix = ".uat"
//            versionNameSuffix = "-uat"
//        }
//        create("prod") {
//            dimension = "env"
//            applicationIdSuffix = ".prod"
//            versionNameSuffix = "-prod"
//        }
//        create("free") {
//            dimension = "tier"
//            applicationIdSuffix = ".free"
//            versionNameSuffix = "-free"
//        }
//        create("premium") {
//            dimension = "tier"
//            applicationIdSuffix = ".premium"
//            versionNameSuffix = "-premium"
//        }
//    }
//    androidComponents {
//        beforeVariants { variantBuilder ->
//            if (variantBuilder.productFlavors.any {
//                    it.second == "uat" && variantBuilder.buildType == "release"
//                }) {
//                variantBuilder.enable = false
//            }
//        }
//    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}