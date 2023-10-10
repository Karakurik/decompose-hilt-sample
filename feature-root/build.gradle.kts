plugins {
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.example.myapplication.root"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidx.composeCompiler.get()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = libs.versions.jvmTarget.get()
    }
}

dependencies {
    implementation(project(":feature-list"))
    implementation(project(":feature-details"))
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.decompose.decompose)
    implementation(libs.decompose.extensionsCompose)
    implementation(libs.dagger.dagger)
    kapt(libs.dagger.daggerCompiler)

    testImplementation(project(":repository"))
    testImplementation(libs.kotlin.test)
}
