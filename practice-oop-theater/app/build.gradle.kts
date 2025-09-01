plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.assertj.core)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation(libs.guava)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

application {
    mainClass = "org.example.App"
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
