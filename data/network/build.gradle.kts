plugins {
    id("com.android.library")
    kotlin("android")
   id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

 val BASE_URL: String by project
//val API_KEY : String by project
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
            buildConfigField("String", "APP_SECRET", "\"6033aa70d5ad0e5b102080e967d87740\"")
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
        }
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            buildConfigField("String", "APP_SECRET", "\"6033aa70d5ad0e5b102080e967d87740\"")
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
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
    buildTypes.forEach {
        it.buildConfigField("String", "baseUrl", BASE_URL)
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
    implementation(project(":data:common"))

    implementation(Kotlin.stdblib_jdk)
    implementation(AndroidXCore.core_ktx)

    //Networking
    implementation(OkHttp.client)
    implementation(OkHttp.logger)

    implementation(Retrofit.core)
    implementation(Retrofit.moshi_converter)

    //Scarlet
    implementation(Scarlet.scarlet)
    implementation(Scarlet.scarlet_websocket)
    implementation(Scarlet.scarlet_moshi)
    implementation(Scarlet.scarlet_lifecycle)
    implementation(Scarlet.scarlet_rxjava)
    //  implementation(Scarlet.scarlet_okhttp)
    // implementation(Scarlet.scarlet_coroutines)

    implementation(RxJava.rx_android)
    implementation(RxJava.rx_kotlin)

    moshi()

    //Dagger
    daggerHiltJvm()

    //Timber
    implementation(CommonLibs.timber)
    //TESTING
    testImplementation(CommonLibs.junit)
    androidTestImplementation(AndroidXTestExt.junit)
    androidTestImplementation(AndroidXTestExt.expresso)

}