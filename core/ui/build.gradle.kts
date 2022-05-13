android {
    kotlinOptions {
        jvmTarget = "1.8"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0-alpha04"
    }
    namespace = "com.iddevops.core.ui"
}

dependencies {
    api(project(path = ":core:common"))
}