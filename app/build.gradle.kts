plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.jetbrains.kotlin.android)
  alias(libs.plugins.dagger.hilt)
  alias(libs.plugins.androidx.navigation.saveargs.kotlin)
  id("kotlin-parcelize")
  kotlin("kapt")
}

android {
  namespace = "com.example.movieapp"
  compileSdk = 34
  
  defaultConfig {
    applicationId = "com.example.movieapp"
    minSdk = 24
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"
    
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }
  
  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
  // Core
  implementation(libs.androidx.core.ktx)
  
  // Architecture components
  implementation(libs.androidx.activity)
  implementation(libs.androidx.fragment)
  implementation(libs.lifecycle.livedata.ktx)
  implementation(libs.lifecycle.viewmodel.ktx)
  implementation(libs.lifecycle.runtime.ktx)
  implementation(libs.lifecycle.common)
  
  // UI
  implementation(libs.androidx.appcompat)
  implementation(libs.material)
  implementation(libs.androidx.constraintlayout)
  implementation(libs.androidx.viewpager2)
  implementation(libs.flexbox)
  implementation(libs.androidx.swiperefreshlayout)
  
  // Navigation component
  implementation(libs.androidx.navigation.runtime.ktx)
  implementation(libs.androidx.navigation.fragment.ktx)
  implementation(libs.androidx.navigation.ui.ktx)
  
  // Dagger-Hilt
  implementation(libs.dagger.hilt.android)
  kapt(libs.dagger.hilt.compiler)
  
  // Gson
  implementation(libs.gson)
  
  // Testings
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  
  // Glide
  implementation(libs.glide)
  implementation(libs.glide.transformations)
}