android {
    kotlinOptions {
        jvmTarget = "1.8"
    }

    viewBinding {
        isEnabled = true
    }
    namespace = "com.iddevops.common"
}

plugins {
    kotlin("kapt")
    id("androidx.navigation.safeargs")
}

dependencies {
    api(project(path = ":core:common"))
    api(project(path = ":core:ui"))
    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:2.4.2")
}