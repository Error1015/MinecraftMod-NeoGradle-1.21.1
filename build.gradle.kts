plugins {
    `java-library`
    idea
    `maven-publish`
    // id("net.neoforged.moddev") version "1.0.11"
    id("net.neoforged.gradle.userdev") version "7.0.184"
    kotlin("jvm") version "2.1.10"
    kotlin("plugin.serialization") version "2.1.10"
}

version = project.property("mod_version") as String
group = project.property("mod_group_id") as String

base {
    archivesName = project.property("mod_name") as String
}

val modid = project.property("mod_id") as String

minecraft {
    version = project.property("minecraft_version") as String
    // accessTransformers.file("src/main/resources/META-INF/accesstransformer.cfg")
}

runs {
    create("client") {
        client()
        workingDirectory(file("run"))
        systemProperties(
            mapOf(
                "forge.logging.markers" to "REGISTRIES", "forge.logging.console.level" to "debug"
            )
        )
        modSource(sourceSets.main.get())
    }

    create("server") {
        server()
        workingDirectory(file("run/server"))
        programArgument("--nogui")
        systemProperty("neoforge.enabledGameTestNamespaces", modid)
        modSource(sourceSets.main.get())
    }

    create("gameTestServer") {
        gameTest()
        workingDirectory(file("run/server"))
        systemProperty("neoforge.enabledGameTestNamespaces", modid)
        modSource(sourceSets.main.get())
    }

    create("data") {
        dataGenerator()
        workingDirectory(file("run"))
        programArguments.addAll(
            "--mod",
            modid,
            "--all",
            "--output",
            file("src/generated/resources/").absolutePath,
            "--existing",
            file("src/main/resources/").absolutePath
        )
        modSource(sourceSets.main.get())
    }
}

subsystems {
    parchment {
        minecraftVersion = project.property("minecraft_version") as String
        mappingsVersion = project.property("parchment_version") as String
    }
}

sourceSets {
    main {
        resources {
            srcDir("src/generated/resources")
        }
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}


kotlin {
    jvmToolchain(21)

    compilerOptions {
        freeCompilerArgs.add("-Xwhen-guards") // 使用When表达式的if语句
    }
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://thedarkcolour.github.io/KotlinForForge/") {
        name = "Kotlin For Forge"
        content {
            includeGroup("thedarkcolour")
        }
    }
    maven("https://bmclapi2.bangbang93.com/maven") { name = "BMCL" }
}

dependencies {
    implementation("net.neoforged:neoforge:${property("neo_version")}")
    implementation("thedarkcolour:kotlinforforge-neoforge:${property("kff_version")}")
}

tasks.withType<ProcessResources>().configureEach {
    val replaceProperties = mapOf(
        "minecraft_version" to project.property("minecraft_version") as String,
        "minecraft_version_range" to project.property("minecraft_version_range") as String,
        "neo_version" to project.property("neo_version") as String,
        "neo_version_range" to project.property("neo_version_range") as String,
        "loader_version_range" to project.property("loader_version_range") as String,
        "mod_id" to modid,
        "mod_name" to project.property("mod_name") as String,
        "mod_license" to project.property("mod_license") as String,
        "mod_version" to project.property("mod_version") as String,
        "mod_authors" to project.property("mod_authors") as String,
        "mod_description" to project.property("mod_description") as String
    )
    inputs.properties(replaceProperties)

    filesMatching(listOf("META-INF/neoforge.mods.toml")) {
        expand(replaceProperties)
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["kotlin"])
        }
    }

    repositories {
        maven {
            mavenLocal()
        }
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

idea {
    module {
        isDownloadSources = true
        isDownloadSources = true
    }
}