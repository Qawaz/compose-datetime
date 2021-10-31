plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
}

android {
    compileSdk = 31
    buildToolsVersion = "30.0.3"

    defaultConfig {

        applicationId = "com.vanpra.composematerialdialogs"
        minSdk = 23
        targetSdk = 30

        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    packagingOptions.excludes.addAll(
        listOf(
            "META-INF/LICENSE",
            "META-INF/AL2.0",
            "META-INF/**",
        )
    )

    buildFeatures {
        viewBinding = true
        compose = true
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.AndroidX.Compose.version
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":datetime"))
    implementation(project(":color"))

//    implementation(Dependencies.ComposeMaterialDialogs.core)
//    implementation(Dependencies.ComposeMaterialDialogs.datetime)
//    implementation(Dependencies.ComposeMaterialDialogs.color)

    implementation(Dependencies.Kotlin.stdlib)
    implementation(Dependencies.Google.material)
    implementation(Dependencies.AndroidX.coreKtx)

    implementation(Dependencies.AndroidX.Compose.ui)
    implementation(Dependencies.AndroidX.Compose.material3)
    implementation(Dependencies.AndroidX.Compose.materialIconsExtended)
    implementation(Dependencies.AndroidX.Compose.animation)
    implementation(Dependencies.AndroidX.Compose.foundationLayout)

    implementation(Dependencies.AndroidX.Compose.activity)
    implementation(Dependencies.AndroidX.Compose.navigation)

    implementation(kotlin("stdlib-jdk8"))
    coreLibraryDesugaring(Dependencies.desugar)
}
