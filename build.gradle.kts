plugins {
    id("java")
    id("org.jetbrains.intellij") version "1.11.0"
    id("org.jetbrains.kotlin.jvm") version "1.8.0"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "com.glycin.funfinal"
version = "1.1.2"

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
}

// Configure Gradle IntelliJ Plugin - read more: https://github.com/JetBrains/gradle-intellij-plugin
intellij {
    version.set("2021.2")
    type.set("IC") // Target IDE Platform

    plugins.set(listOf(/* Plugin Dependencies */))
}

dependencies {
    // https://mvnrepository.com/artifact/javazoom/jlayer
    implementation("javazoom:jlayer:1.0.1")
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }

    patchPluginXml {
        version.set("${project.version}")
        sinceBuild.set("212")
        untilBuild.set("223.*")
    }

    signPlugin {
        certificateChain.set(File(System.getenv("CERTIFICATE_CHAIN") ?: "./signing/chain.crt").readText(Charsets.UTF_8))
        privateKey.set(File(System.getenv("PRIVATE_KEY") ?: "./signing/private.pem").readText(Charsets.UTF_8))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }

    compileKotlin {
        kotlinOptions.jvmTarget = "11"
    }

    compileTestKotlin {
        kotlinOptions.jvmTarget = "11"
    }
}
