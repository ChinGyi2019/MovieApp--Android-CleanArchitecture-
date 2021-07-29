import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id ("java-library")
    id ("kotlin")
}

dependencies {
    implementation(fileTree(mapOf("dir" to "Libs", "include" to listOf("*.jar"))))
    implementation(Kotlin.stdblib_jdk)
    implementation(KotlinCoroutine.core)

    implementation(CommonLibs.javaxInject)
    //rxJava
    implementation(RxJava.rxjava)
    //Test
    testImplementation(CommonLibs.junit)

}



java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}