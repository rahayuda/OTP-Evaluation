plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.otp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.otp"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
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
        languageVersion = "2.1"  // Menggunakan Kotlin versi terbaru
        apiVersion = "2.1"       // Menggunakan Kotlin versi terbaru
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Firebase BOM untuk mengelola versi dependensi Firebase secara otomatis
    implementation(platform("com.google.firebase:firebase-bom:33.8.0"))

    // Firebase dependensi
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth-ktx:23.1.0")

    // Kotlin standar library dengan versi terbaru
    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.1.0")

    // Google Play Services
    implementation("com.google.android.gms:play-services-auth:21.3.0")
    implementation("com.google.android.gms:play-services-measurement-api:22.2.0")

    // Profile Installer untuk optimasi
    implementation("androidx.profileinstaller:profileinstaller:1.4.1")
}
