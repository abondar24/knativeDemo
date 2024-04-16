import org.gradle.toolchains.foojay.architecturesArm64Bit

plugins {
    kotlin("jvm") version "1.9.23"
    id("com.google.cloud.tools.jib") version "3.1.1"
}

apply (plugin = "application")

group = "org.abondar.experimental"
version = "1.1-SNAPSHOT"

var mainClassName = "org.abondar.experimental.knativedemo.DemoFuncKt"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    implementation("io.ktor:ktor-server-netty:2.3.10")
    implementation("ch.qos.logback:logback-classic:1.4.14")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = mainClassName
    }

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}


var registry = System.getenv("DOCKER_REGISTRY")

jib {
    from {
        image = "eclipse-temurin:17-jre"
        platforms {
//            platform {
//                architecture = "amd64"
//                os = "linux"
//            }
            platform {
                architecture = "arm64"
                os = "linux"
            }
        }

    }
    to {
        image = "$registry/knativedemo:$version"
        auth {
            username = System.getenv("DOCKER_USERNAME")
            password = System.getenv("DOCKER_PWD")
        }
    }

    container {
        mainClass = mainClassName
        ports = listOf("8080","8090","8010","8020")
    }
}



tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}