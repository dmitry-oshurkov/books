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


val buildMode by extra { (System.getenv("BUILD_MODE") ?: "DEV").also { println("Build mode: $it") } }

group = "name.oshurkov.books"
version = "23.5.${ZonedDateTime.now(ZoneId.of("Europe/Moscow"))!!.format(ofPattern("MMddHHmm"))}${if (buildMode == "DEV") "-dev" else ""}".also { println("Version: $it") }
java.sourceCompatibility = VERSION_17


application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}


repositories {
    mavenCentral()
    maven("https://jitpack.io")
}


dependencies {
    implementation("io.ktor:ktor-server-auto-head-response-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-status-pages-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-core-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-cors-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-default-headers-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-call-logging:$ktorVersion")
    implementation("io.ktor:ktor-server-thymeleaf-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-html-builder:$ktorVersion")
    implementation("io.ktor:ktor-serialization-jackson-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-netty-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-compression-jvm:$ktorVersion")
    implementation("io.ktor:ktor-utils:$ktorVersion")
    implementation("io.github.smiley4:ktor-swagger-ui:1.1.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:$jacksonVersion")
    implementation("org.ktorm:ktorm-core:$ktormVersion")
    implementation("org.ktorm:ktorm-jackson:$ktormVersion")
    implementation("org.ktorm:ktorm-support-postgresql:$ktormVersion")
    implementation("com.zaxxer:HikariCP:5.0.1")
    implementation("org.flywaydb:flyway-core:9.10.2")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("org.apache.commons:commons-compress:1.22")
    implementation("jakarta.xml.bind:jakarta.xml.bind-api:4.0.0")
    implementation("com.sun.xml.bind:jaxb-impl:4.0.1")
    runtimeOnly("org.tukaani:xz:1.9") // for commons-compress
}


sourceSets {
    test { java.setSrcDirs(emptyList<Any>()) }
}


buildConfig {
    packageName(project.group.toString())
    useKotlinOutput { topLevelConstants = true }
    buildConfigField("${project.group}.core.BuildMode", "BUILD_MODE", "BuildMode.$buildMode")
    buildConfigField("String", "BUILD_VERSION", "\"$version\"")
}


tasks {
    compileKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = java.sourceCompatibility.majorVersion
        }
        dependsOn(ktlintFormat)
    }
    compileTestKotlin { kotlinOptions.jvmTarget = compileKotlin.get().kotlinOptions.jvmTarget }
    wrapper { gradleVersion = "7.6" }
    jar { enabled = false }

    runKtlintCheckOverMainSourceSet { dependsOn(runKtlintFormatOverKotlinScripts, runKtlintFormatOverMainSourceSet) }
    runKtlintCheckOverTestSourceSet { dependsOn(runKtlintFormatOverKotlinScripts, runKtlintFormatOverTestSourceSet) }
    runKtlintCheckOverKotlinScripts { dependsOn(runKtlintFormatOverKotlinScripts) }
    processResources { dependsOn(runKtlintFormatOverKotlinScripts) }
}
