android {
    kotlinOptions {
        jvmTarget = "1.8"
    }

    viewBinding {
        isEnabled = true
    }

    externalNativeBuild {
        cmake {
            path("CMakeLists.txt")
        }
    }
    namespace = "com.iddevops.core.common"
}

dependencies {

    // CORE
    api("androidx.core:core-ktx:1.7.0")
    api("androidx.lifecycle:lifecycle-runtime-ktx:2.5.0-beta01")
    api("androidx.work:work-runtime:2.7.1")

    // JSON
    api("com.google.code.gson:gson:2.9.0")

    // UI
//    api("androidx.constraintlayout:constraintlayout-compose:1.0.0")
//    api("androidx.compose.ui:ui:1.2.0-alpha06")
//    api("androidx.compose.ui:ui-tooling:1.1.1")
//    api("androidx.compose.ui:ui-tooling-preview:1.1.1")
//    // material ui 2
//    api("androidx.compose.material:material:1.1.1")
//    // material ui 3 ( still in experimental )
//    // api("androidx.compose.material3:material3:1.0.0-alpha07")
//    api("androidx.activity:activity-compose:1.6.0-alpha01")
//    // image loader
//    api("io.coil-kt:coil-compose:2.0.0-rc02")
//    // System UI
//    api("com.google.accompanist:accompanist-systemuicontroller:0.17.0")
//
//    // NAVIGATION
//    api("androidx.navigation:navigation-compose:2.5.0-alpha03")

    // CORE
    api("androidx.core:core-ktx:1.7.0")
    api("androidx.appcompat:appcompat:1.4.1")
    api("com.google.android.material:material:1.5.0")
    api("androidx.constraintlayout:constraintlayout:2.1.3")
    api("junit:junit:4.13.2")
    api("androidx.test.ext:junit:1.1.3")
    api("androidx.test.espresso:espresso-core:3.4.0")

    // NAVIGATION
    val nav_version = "2.4.2"
    api("androidx.navigation:navigation-fragment-ktx:$nav_version")
    api("androidx.navigation:navigation-ui-ktx:$nav_version")
    // Feature module Support
    api("androidx.navigation:navigation-dynamic-features-fragment:$nav_version")
    androidTestApi("androidx.navigation:navigation-testing:$nav_version")

    // ASYNC
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1")

    // INJECTION
    val koinVersion = "3.1.5"
    api("io.insert-koin:koin-core:$koinVersion")
    api("io.insert-koin:koin-android:$koinVersion")
    api("io.insert-koin:koin-androidx-workmanager:$koinVersion")
    api("io.insert-koin:koin-androidx-navigation:$koinVersion")
    // api("io.insert-koin:koin-androidx-compose:$koinVersion")

    // RETROFIT
    api("com.squareup.retrofit2:retrofit:2.9.0")
    api("com.squareup.retrofit2:converter-gson:2.9.0")

    // ROOM
    val roomVersion = "2.4.2"
    api("androidx.room:room-runtime:$roomVersion")
    annotationProcessor("androidx.room:room-compiler:$roomVersion")
    // optional - Kotlin Extensions and Coroutines support for Room
    api("androidx.room:room-ktx:$roomVersion")
    // optional - Test helpers
    testApi("androidx.room:room-testing:$roomVersion")
    // optional - Paging 3 Integration
    api("androidx.room:room-paging:2.5.0-alpha01")

    // GSON
    api("com.google.code.gson:gson:2.9.0")

    // DEBUG
    debugApi("com.github.chuckerteam.chucker:library:3.5.2")
    releaseApi("com.github.chuckerteam.chucker:library-no-op:3.5.2")

    // TEST
    testApi("junit:junit:4.13.2")
    testApi("io.insert-koin:koin-test:$koinVersion")
    testApi("io.insert-koin:koin-test-junit4:$koinVersion")
    androidTestApi("androidx.test.ext:junit:1.1.3")
    androidTestApi("androidx.test.espresso:espresso-core:3.4.0")

    //  UI Test
    // androidTestApi("androidx.compose.ui:ui-test-junit4:1.1.1")
    // debugApi("androidx.compose.ui:ui-tooling:1.1.1")
}