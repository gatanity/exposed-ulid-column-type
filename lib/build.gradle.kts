object Versions {
    const val exposed = "0.31.1"
    const val kulid = "1.1.2.0"
    const val guava = "30.1.1-jre"
}

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.4.31"
}

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

    // Google Guava
    implementation("com.google.guava:guava:${Versions.guava}")

    // Exposed
    api("org.jetbrains.exposed:exposed-core:${Versions.exposed}")
    api("org.jetbrains.exposed:exposed-dao:${Versions.exposed}")

    // kULID
    api("com.github.guepardoapps:kulid:${Versions.kulid}")
}
