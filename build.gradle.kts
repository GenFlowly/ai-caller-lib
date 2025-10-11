plugins {
    kotlin("multiplatform") version "2.0.21"
    kotlin("plugin.serialization") version "2.0.21"
    `maven-publish`
}

group = "com.genflowly"

version = "0.0.13"

repositories {
    mavenCentral()
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
            from(components["kotlin"])
        }
    }
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
    jvm {
        withJava()
    }

    
    sourceSets {
        val koinVersion = "3.5.6"
        val ktorVersion = "3.0.0"
        val commonMain by getting {
            dependencies {
                api("io.ktor:ktor-client-core:$ktorVersion")
                api("io.ktor:ktor-client-cio:$ktorVersion")
                api("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                api("io.insert-koin:koin-core")
                api("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.1")
                //Should match here https://central.sonatype.com/artifact/io.ktor/ktor-serialization-kotlinx-json
                api("io.github.microutils:kotlin-logging-jvm:3.0.5")
                api("org.slf4j:slf4j-simple:2.0.9")
                implementation("com.anthropic:anthropic-java:2.8.1")
                implementation("com.google.genai:google-genai:1.4.1")
                implementation("com.openai:openai-java:4.2.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("io.mockk:mockk:1.13.3")
                implementation("io.insert-koin:koin-test:$koinVersion")
                implementation("io.insert-koin:koin-test-junit4:$koinVersion")
                implementation("io.ktor:ktor-client-mock:$ktorVersion")
            }

        }
        val jvmMain by getting {
            dependencies {
                implementation(platform("io.insert-koin:koin-bom:$koinVersion"))
                implementation("io.insert-koin:koin-core")
            }
        }
    }

}
