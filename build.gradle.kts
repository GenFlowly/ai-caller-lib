plugins {
    kotlin("jvm") version "2.0.21"
    kotlin("plugin.serialization") version "2.0.21"
    `maven-publish`
    `java-library`
}

group = "com.genflowly"
version = "0.0.18"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
    withSourcesJar()
    withJavadocJar()
}

val koinVersion = "3.5.6"
val ktorVersion = "3.0.0"

dependencies {
    // Main dependencies
    api("io.ktor:ktor-client-core:$ktorVersion")
    api("io.ktor:ktor-client-cio:$ktorVersion")
    api("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    api("io.insert-koin:koin-core:$koinVersion")
    api("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.1")
    api("io.github.microutils:kotlin-logging-jvm:3.0.5")
    api("org.slf4j:slf4j-simple:2.0.9")
    implementation("com.anthropic:anthropic-java:2.8.1")
    implementation("com.google.genai:google-genai:1.4.1")
    implementation("com.openai:openai-java:4.2.0")
    implementation(platform("io.insert-koin:koin-bom:$koinVersion"))

    // Test dependencies
    testImplementation(kotlin("test"))
    testImplementation("io.mockk:mockk:1.13.3")
    testImplementation("io.insert-koin:koin-test:$koinVersion")
    testImplementation("io.insert-koin:koin-test-junit4:$koinVersion")
    testImplementation("io.ktor:ktor-client-mock:$ktorVersion")
}

tasks.test {
    enabled = false
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/genflowly/ai-caller-lib")
            credentials {
                username = System.getenv("USERNAME")
                password = System.getenv("TOKEN")
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = "ai-caller-lib"
            version = project.version.toString()
            artifact(tasks["jar"])

            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])

            // Optional: Add more metadata
            pom {
                name.set("AI Caller Library")
                description.set("A library for calling AI APIs")
                url.set("https://github.com/genflowly/ai-caller-lib")
            }
        }
    }
}