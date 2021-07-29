plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(BuildConfig.compileSdk)

    defaultConfig {
        minSdkVersion(BuildConfig.minSdk)
        targetSdkVersion(BuildConfig.targetSdk)
        versionCode = BuildConfig.versionCode
        versionName = BuildConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf("room.schemaLocation" to "$projectDir/schemas")
            }
        }
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false

        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")

        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    kapt {
        javacOptions {
            // These options are normally set automatically via the Hilt Gradle plugin, but we
            // set them manually to workaround a bug in the Kotlin 1.5.20
            option("-Adagger.fastInit=ENABLED")
            option("-Adagger.hilt.android.internal.disableAndroidSuperclassValidation=true")
        }
    }
    packagingOptions{
//        pickFirst ("META-INF/*")
        pickFirst ("META-INF/LICENSE")
        pickFirst ("META-INF/io.netty.versions.properties")
        pickFirst ("META-INF/INDEX.LIST")
        pickFirst ("META-INF/common_debug.kotlin_module")
        exclude( "META-INF/MANIFEST.MF" )


    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(":data:common"))
    implementation(Kotlin.stdblib_jdk)
    implementation(AndroidXCore.core_ktx)

    //Paging
    implementation(AndroidXPaging.common)
    //Room
    implementation(Room.ktx)
    implementation(Room.runtime)
    kapt(Room.compiler)

    //dagger
    daggerHiltWithViewModel()

    //TESTING
    testImplementation(CommonLibs.junit)
    androidTestImplementation(AndroidXTestExt.junit)
    androidTestImplementation(AndroidXTestExt.expresso)



}


