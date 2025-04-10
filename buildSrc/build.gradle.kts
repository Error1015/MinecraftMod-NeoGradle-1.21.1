plugins {
    kotlin("jvm") version "2.1.10"
    `kotlin-dsl`
}

group = "org.error1015"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    this?.let {
        useJUnitPlatform()
    }
}

kotlin {
    jvmToolchain(21)
}