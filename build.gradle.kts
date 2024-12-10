plugins {
    kotlin("jvm") version "2.0.21"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

}

tasks.test {
    useJUnitPlatform()
}
application {
    mainClass.set("MainKt")
}

tasks.test {
    useJUnitPlatform()
}s