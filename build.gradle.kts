import org.gradle.api.JavaVersion.*
import java.time.*
import java.time.format.DateTimeFormatter.*

plugins {
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.spring") version "1.5.31"
    kotlin("plugin.jpa") version "1.5.31"
    id("org.springframework.boot") version "2.5.6"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.0"
    id("com.github.gmazzo.buildconfig") version "3.0.1"
}

val jacksonVersion: String by rootProject

group = "name.oshurkov"
version = "22.02.${ZonedDateTime.now(ZoneId.of("Europe/Moscow"))!!.format(ofPattern("MMddHHmm"))}".also { println("Version: $it") }
java.sourceCompatibility = VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("io.projectreactor:reactor-core:3.4.11")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:$jacksonVersion")
    implementation("org.apache.commons:commons-compress:1.21")
    implementation("com.positiondev.epublib:epublib-core:3.1") {
        exclude(group = "org.slf4j")
        exclude(group = "xmlpull")
    }
    implementation("io.ktor:ktor-client-cio:1.6.7")
    implementation("org.webjars:swagger-ui:4.1.3")
    implementation("org.webjars:webjars-locator:0.42")

    runtimeOnly("org.postgresql:postgresql:42.3.1")
    runtimeOnly("org.tukaani:xz:1.9") // for commons-compress
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    testImplementation("org.springframework.boot:spring-boot-starter-test") { exclude(group = "org.junit.vintage", module = "junit-vintage-engine") }
}

sourceSets {
    main { java.setSrcDirs(emptyList<Any>()) }
    test { java.setSrcDirs(emptyList<Any>()) }
}

buildConfig {
    buildConfigField("String", "APP_VER", "\"$version\"")
}

tasks {
    compileKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = java.sourceCompatibility.majorVersion
        }
        dependsOn(ktlintFormat)
    }
    wrapper { gradleVersion = "7.1" }
    jar { enabled = false }
    test { useJUnitPlatform() }

    runKtlintCheckOverMainSourceSet { dependsOn(runKtlintFormatOverKotlinScripts, runKtlintFormatOverMainSourceSet) }
    runKtlintCheckOverTestSourceSet { dependsOn(runKtlintFormatOverKotlinScripts, runKtlintFormatOverTestSourceSet) }
    runKtlintCheckOverKotlinScripts { dependsOn(runKtlintFormatOverKotlinScripts) }
    processResources { dependsOn(runKtlintFormatOverKotlinScripts) }
}
