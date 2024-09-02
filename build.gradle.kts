import java.util.*

plugins {
    kotlin("multiplatform") version "1.9.0"
    kotlin("plugin.serialization") version "1.9.0"
    `maven-publish`
}

group = "com.genflowly"

val versionPropertiesFile = file("version.properties")
val versionProperties = Properties().apply {
    load(versionPropertiesFile.inputStream())
}

val currentVersion = versionProperties["version"] as String
version = currentVersion

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
            groupId = "com.genflowly"
            artifactId = "ai-caller-lib"
            version = currentVersion
        }
    }
}

tasks.withType<PublishToMavenRepository> {
    doLast {
        val versionParts = currentVersion.split(".")
        val newPatchVersion = versionParts.last().toInt() + 1
        val newVersionString = versionParts.dropLast(1).joinToString(".") + ".$newPatchVersion"

        versionProperties["version"] = newVersionString
        versionProperties.store(versionPropertiesFile.outputStream(), null)

        println("Version incremented to $newVersionString")
    }
}

kotlin {
    jvm {
        jvmToolchain(8)
        withJava()
        testRuns.named("test") {
            executionTask.configure {
                useJUnitPlatform()
            }
        }
    }

    
    sourceSets {
        val koinVersion = "3.5.6"
        val ktorVersion = "2.3.9"
        val commonMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-cio:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation(platform("io.insert-koin:koin-bom:$koinVersion"))
                implementation("io.insert-koin:koin-core")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.1")
                //Should match here https://central.sonatype.com/artifact/io.ktor/ktor-serialization-kotlinx-json
                implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")
                implementation("org.slf4j:slf4j-simple:2.0.9")
            }
        }
        val commonTest by getting
        val jvmMain by getting
    }

}
