import org.gradle.api.JavaVersion.*
import java.util.*

plugins {
    kotlin("jvm") version "1.5.20"
    kotlin("plugin.spring") version "1.5.20"
    kotlin("plugin.jpa") version "1.5.20"
    id("org.springframework.boot") version "2.5.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("com.bmuschko.docker-spring-boot-application") version "7.0.1"
    id("org.jlleitschuh.gradle.ktlint") version "10.1.0"
}

group = "name.oshurkov"
version = "21.1"
java.sourceCompatibility = VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

val jacksonVersion: String by rootProject
val localProperties = Properties().also { it.load(project.rootProject.file("local.properties").inputStream()) }
val dockerHubUsername = localProperties.getProperty("docker.hub.username")!!
val dockerHubPassword = localProperties.getProperty("docker.hub.password")!!

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect:2.5.1")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:$jacksonVersion")
    implementation("org.apache.commons:commons-compress:1.20")
    implementation("com.positiondev.epublib:epublib-core:3.1") {
        exclude(group = "org.slf4j")
        exclude(group = "xmlpull")
    }

    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("org.tukaani:xz:1.8") // for commons-compress
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    testImplementation("org.springframework.boot:spring-boot-starter-test") { exclude(group = "org.junit.vintage", module = "junit-vintage-engine") }
}

docker {
    registryCredentials {
        username.set(dockerHubUsername)
        password.set(dockerHubPassword)
    }
    springBootApplication {
        maintainer.set("DM")
        baseImage.set("openjdk:15-alpine")
        ports.set(listOf(8080))
        images.add("dmitryoshurkov/books:latest")
        jvmArgs.set(listOf("-Dspring.profiles.active=docker", "-Xmx2048m"))
    }
}

tasks {
    compileKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
        dependsOn(ktlintFormat)
    }
    wrapper { gradleVersion = "7.1" }
    withType<Test> { useJUnitPlatform() }
}
