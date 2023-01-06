import org.gradle.api.JavaVersion.*
import java.time.*
import java.time.format.DateTimeFormatter.*


val ktorVersion: String by project
val kotlinVersion: String by project
val jacksonVersion: String by project
val ktormVersion: String by project
val logbackVersion: String by project

plugins {
    kotlin("jvm") version "1.8.0"
    id("io.ktor.plugin") version "2.2.1"
    id("com.github.gmazzo.buildconfig") version "3.1.0"
    id("org.jlleitschuh.gradle.ktlint") version "10.3.0"
}

group = "name.oshurkov.books"
version = "23.1.${ZonedDateTime.now(ZoneId.of("Europe/Moscow"))!!.format(ofPattern("MMddHHmm"))}".also { println("Version: $it") }
java.sourceCompatibility = VERSION_17

application {
    mainClass.set("name.oshurkov.books.ApplicationKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-auto-head-response-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-status-pages-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-core-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-cors-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-default-headers-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-call-logging:$ktorVersion")
    implementation("io.ktor:ktor-server-swagger:$ktorVersion")
    implementation("io.ktor:ktor-server-thymeleaf-jvm:$ktorVersion")
    implementation("io.ktor:ktor-serialization-jackson-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-netty-jvm:$ktorVersion")
    implementation("io.ktor:ktor-utils:$ktorVersion")
    implementation("nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect:3.1.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:$jacksonVersion")
    implementation("org.ktorm:ktorm-core:$ktormVersion")
    implementation("org.ktorm:ktorm-jackson:$ktormVersion")
    implementation("org.ktorm:ktorm-support-postgresql:$ktormVersion")
    implementation("com.zaxxer:HikariCP:5.0.1")
    implementation("org.flywaydb:flyway-core:9.10.2")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("org.slf4j:slf4j-simple:2.0.6")
    implementation("org.apache.commons:commons-compress:1.22")
    implementation("com.positiondev.epublib:epublib-core:3.1") {
        exclude(group = "org.slf4j")
        exclude(group = "xmlpull")
    }
    runtimeOnly("org.tukaani:xz:1.9") // for commons-compress

    testImplementation("io.ktor:ktor-server-tests-jvm:$ktorVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
}

sourceSets {
    main { java.setSrcDirs(emptyList<Any>()) }
    test { java.setSrcDirs(emptyList<Any>()) }
}

buildConfig {
    packageName(project.group.toString())
    useKotlinOutput { topLevelConstants = true }
    buildConfigField("String", "BUILD_VERSION", "\"$version\"")
}

tasks {
    compileKotlin {
        dependsOn(ktlintFormat)
    }
    wrapper { gradleVersion = "7.6" }

    runKtlintCheckOverMainSourceSet { dependsOn(runKtlintFormatOverKotlinScripts, runKtlintFormatOverMainSourceSet) }
    runKtlintCheckOverTestSourceSet { dependsOn(runKtlintFormatOverKotlinScripts, runKtlintFormatOverTestSourceSet) }
    runKtlintCheckOverKotlinScripts { dependsOn(runKtlintFormatOverKotlinScripts) }
    processResources { dependsOn(runKtlintFormatOverKotlinScripts) }
}
