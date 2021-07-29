plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(BuildConfig.compileSdk)

    defaultConfig {
        minSdkVersion(BuildConfig.minSdk)
        targetSdkVersion(BuildConfig.targetSdk)
        versionCode = BuildConfig.versionCode
        versionName = BuildConfig.versionName

        testInstrumentationRunner  ="androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        getByName("release") {
            isMinifyEnabled = true
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
        pickFirst ("META-INF/LICENSE")
        pickFirst ("META-INF/io.netty.versions.properties")
        pickFirst ("META-INF/INDEX.LIST")
        pickFirst ("META-INF/common_debug.kotlin_module")
        exclude( "META-INF/MANIFEST.MF" )
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    //api ma ya tl
    api(project(":domain"))

    implementation(Kotlin.stdblib_jdk)
    implementation(AndroidXCore.core_ktx)
    implementation(KotlinCoroutine.android)
    //daggerPureJvm()
    daggerHiltJvm()
    implementation(CommonLibs.javaxInject)
    //Timber
    implementation(CommonLibs.timber)
    //rxJava
    implementation(RxJava.rxjava)

    //Scarlet
    implementation(Scarlet.scarlet)
    implementation(Scarlet.scarlet_websocket)

    //TESTING
    //TESTING
    testImplementation(CommonLibs.junit)
    androidTestImplementation(AndroidXTestExt.junit)
    androidTestImplementation(AndroidXTestExt.expresso)
}