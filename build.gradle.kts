plugins {
    kotlin("jvm") version "2.0.21"
    kotlin("plugin.serialization") version "2.0.21"
    `maven-publish`
    `java-library`
}

group = "com.genflowly"
version = "0.0.24"

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
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    api("io.insert-koin:koin-core:$koinVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.1")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")
    implementation("org.slf4j:slf4j-simple:2.0.9")
    api("com.anthropic:anthropic-java:2.8.1")
    api("com.google.genai:google-genai:1.4.1")
    api("com.openai:openai-java:4.2.0")
    api(platform("io.insert-koin:koin-bom:$koinVersion"))

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
            from(components["java"])

            // Optional: Add more metadata
            pom {
                name.set("AI Caller Library")
                description.set("A library for calling AI APIs")
                url.set("https://github.com/genflowly/ai-caller-lib")
            }
        }
    }
}

tasks.jar {
    from(sourceSets.main.get().output)
}