plugins {
    id("java")
    id("org.openjfx.javafxplugin") version("0.0.9")
}

repositories {
    mavenCentral()
}

javafx {
    version = "22.0.2"
    modules = mutableListOf("javafx.base", "javafx.controls", "javafx.graphics", "javafx.fxml")
}

group = "org.example"
version = "0.1-SNAPSHOT"

dependencies {
    // tests
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.3")
    testImplementation("org.mockito:mockito-core:5.11.0")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}
