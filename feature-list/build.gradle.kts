plugins {
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.example.myapplication.list"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidx.composeCompiler.get()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    kotlinOptions {
        jvmTarget = libs.versions.jvmTarget.get()
    }
}

dependencies {
    implementation(project(":repository"))
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material)
    implementation(libs.decompose.decompose)
    implementation(libs.decompose.extensionsCompose)
    implementation(libs.dagger.dagger)
    kapt(libs.dagger.daggerCompiler)
}
