plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.devtools.ksp")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.travelplanerapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.travelplanerapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // VBPD
    implementation("com.github.kirich1409:viewbindingpropertydelegate-full:1.5.9")

    // Navigation
    val navigation = "2.8.4"
    implementation("androidx.navigation:navigation-fragment-ktx:$navigation")
    implementation("androidx.navigation:navigation-ui-ktx:$navigation")
    implementation("androidx.navigation:navigation-dynamic-features-fragment:$navigation")

    // dagger2 DI
    implementation("com.google.dagger:dagger:2.47")
    kapt("com.google.dagger:dagger-compiler:2.47")

    // retrofit networking
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    // Room
    val room_version = "2.5.0"
    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    ksp("androidx.room:room-compiler:$room_version")


    implementation("com.google.android.material:material:1.9.0")
}
