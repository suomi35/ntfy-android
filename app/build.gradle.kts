plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.google.services)
    alias(libs.plugins.ksp)
}

android {

    namespace = "io.heckel.ntfy"
    compileSdk = 37

    defaultConfig {
        applicationId = "io.heckel.ntfy"
        minSdk = 21
        targetSdk = 37

        versionCode = 33
        versionName = "1.17.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                file("proguard-rules.pro")
            )

            // Use debug config for testing proguard
            signingConfig = signingConfigs.getByName("debug")
        }

        debug {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        buildConfig = true
    }

//    room {
//        schemaDirectory("$projectDir/schemas")
//    }
}

dependencies {
    // AndroidX, The Basics
    implementation(libs.appcompat)
    implementation(libs.core.ktx)
    implementation(libs.constraintlayout)
    implementation(libs.activity.ktx)
    implementation(libs.fragment.ktx)
    implementation(libs.work.runtime.ktx)
    implementation(libs.preference.ktx)

    implementation(libs.compose.runtime)
    implementation(platform(libs.androidx.compose.bom))

    // JSON serialization
    implementation(libs.gson)

    // Room (SQLite)
    implementation(libs.androidx.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    // OkHttp (HTTP library)
    implementation(libs.okhttp)

    // Firebase, sigh ... (only Google Play)
    implementation(libs.firebase.messaging)

    // RecyclerView
    implementation(libs.recyclerview)

    // Swipe down to refresh
    implementation(libs.swiperefreshlayout)

    // Material design
    implementation(libs.material)

    // LiveData
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.legacy.support.v4)

    // Image viewer
    implementation(libs.stfalconimageviewer)
}
