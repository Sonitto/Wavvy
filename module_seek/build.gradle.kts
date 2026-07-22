plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.kapt")
}

android {

    namespace = "com.example.module_seek"
    compileSdk {
        version = release(36)
    }
    viewBinding{
        enable=true
    }

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}
kapt {
    arguments {
        arg("AROUTER_MODULE_NAME", project.name)
    }
}

dependencies {
    // 播放器UI控件 PlayerView
    implementation("androidx.media3:media3-ui:1.4.1")
    // HLS/m3u8 直播支持（短视频、流媒体必加）
    implementation("androidx.media3:media3-exoplayer-hls:1.4.1")
    // 网络数据源
    implementation("androidx.media3:media3-datasource:1.4.1")
    implementation("androidx.media3:media3-exoplayer:1.4.1")
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation(libs.arouter.api)
    kapt(libs.arouter.compiler)
    implementation(project(":lib_net"))
    implementation(project(":lib_route"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}