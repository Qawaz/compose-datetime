import java.util.Properties
import java.io.FileInputStream

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version ProjectConfig.ComposeVersion
    id("com.android.library")
    id("maven-publish")
    id("shot")
    id("org.jetbrains.dokka")
}

group = ProjectConfig.Info.group
version = ProjectConfig.Info.version

kotlin {
    android {
        publishLibraryVariants("release")
    }
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                implementation(Dependencies.Accompanist.Pager)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting
        val androidTest by getting
        val desktopMain by getting {
            dependencies {
                api(compose.preview)
            }
        }
        val desktopTest by getting
    }
}

android {
    compileSdk = ProjectConfig.compileSdk
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk

        testInstrumentationRunner = "com.karumi.shot.ShotTestRunner"
        testApplicationId = "com.wakaztahir.composematerialdialogs.test"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
        isCoreLibraryDesugaringEnabled = true
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro"
            )
        }
    }
    packagingOptions.excludes.addAll(
        listOf(
            "META-INF/DEPENDENCIES.txt",
            "META-INF/LICENSE",
            "META-INF/LICENSE.txt",
            "META-INF/NOTICE",
            "META-INF/NOTICE.txt",
            "META-INF/AL2.0",
            "META-INF/LGPL2.1"
        )
    )
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.AndroidX.Compose.version
    }
    dependencies {
        coreLibraryDesugaring(Dependencies.desugar)
    }
}

shot {
    tolerance = 1.0 // Tolerance needed for CI
}

val githubProperties = Properties()
kotlin.runCatching { githubProperties.load(FileInputStream(rootProject.file("github.properties"))) }

afterEvaluate {
    publishing {
        repositories {
            maven {
                name = "GithubPackages"
                url = uri("https://maven.pkg.github.com/codeckle/compose-datetime")

                runCatching {
                    credentials {
                        /**Create github.properties in root project folder file with gpr.usr=GITHUB_USER_ID  & gpr.key=PERSONAL_ACCESS_TOKEN**/
                        username = (githubProperties["gpr.usr"] ?: System.getenv("GPR_USER")).toString()
                        password = (githubProperties["gpr.key"] ?: System.getenv("GPR_API_KEY")).toString()
                    }
                }.onFailure { it.printStackTrace() }
            }
        }
    }
}