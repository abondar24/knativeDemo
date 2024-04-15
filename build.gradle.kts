plugins {
    kotlin("jvm") version "1.9.23"
    id("com.google.cloud.tools.jib") version "3.1.1"
}

apply (plugin = "application")

group = "org.abondar.experimental"
version = "1.0-SNAPSHOT"


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
        attributes["Main-Class"] = "org.abondar.experimental.knativedemo.DemoFuncKt"
    }

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}


var registry = System.getenv("DOCKER_REGISTRY")

jib {
    from {
        image = "openjdk:17-alpine"
    }
    to {
        image = "$registry/knativedemo:$version"
        auth {
            username = System.getenv("DOCKER_USERNAME")
            password = System.getenv("DOCKER_PWD")
        }
    }
}


tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}