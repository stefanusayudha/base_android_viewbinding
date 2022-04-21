import com.android.build.gradle.BaseExtension

plugins {
    id("java-platform")
}

group = "android"
version = "1.0.0"

subprojects {
    apply(plugin = "com.android.library")
    apply(plugin = "kotlin-android")
    apply(plugin = "kotlin-parcelize")
    apply(plugin = "kotlin-kapt")

    plugins.withType(BasePlugin::class.java).configureEach {
        configure<BaseExtension> {
            compileSdkVersion(32)
            defaultConfig {
                minSdk = 23
                targetSdk = 32
                versionCode = 1
                versionName = project.version.toString()

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                consumerProguardFiles("consumer-rules.pro")
            }
            buildTypes {
                getByName("debug") {
                    isMinifyEnabled = false
                    isDebuggable = true
                    proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
                }
                getByName("release") {
                    isMinifyEnabled = true
                    isDebuggable = false
                    proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
                }
            }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }
        }
    }
}

dependencies {
    constraints {
        api(project(":modules:common"))
    }
}