import org.gradle.api.JavaVersion.*

plugins {
    kotlin("jvm") version "1.4.10"
    kotlin("plugin.spring") version "1.4.10"
    kotlin("plugin.jpa") version "1.4.10"
    id("org.springframework.boot") version "2.3.4.RELEASE"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    id("org.unbroken-dome.xjc") version "2.0.0"
}

group = "name.oshurkov"
version = "20.1"
java.sourceCompatibility = VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

val jacksonVersion: String by rootProject

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:$jacksonVersion")
    implementation("com.positiondev.epublib:epublib-core:3.1") {
        exclude(group = "org.slf4j")
        exclude(group = "xmlpull")
    }
    implementation(files("lib/fb2parser.jar"))
    implementation("javax.xml.ws:jaxws-api:2.3.1")
    xjcClasspath("org.jvnet.jaxb2_commons:jaxb2-value-constructor:3.0")

    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.h2database:h2")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    testImplementation("org.springframework.boot:spring-boot-starter-test") { exclude(group = "org.junit.vintage", module = "junit-vintage-engine") }
}

xjc {
    packageLevelAnnotations.set(false)
    extraArgs.add("-Xvalue-constructor")
}

sourceSets.main {
    xjcTargetPackage.set("${project.group}.books.storage.fb2")
}

tasks {
    compileKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }
    wrapper { gradleVersion = "6.7" }
    withType<Test> { useJUnitPlatform() }
}
