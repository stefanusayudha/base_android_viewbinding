android {
    kotlinOptions {
        jvmTarget = "1.8"
    }

    viewBinding {
        isEnabled = true
    }
}

dependencies {
    api(project(path = ":core:common"))
    api(project(path = ":core:ui"))
    api(project(path = ":common:domain"))
}