plugins {
    java
    `maven-publish`
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
    maven { url = uri("https://repo.spongepowered.org/maven") }
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("com.github.Minestom:Minestom:-SNAPSHOT")
    // implementation("com.github.KrystilizeNevaDies:JLSL:local")
    implementation(files("libs/JLSL.jar"))
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}