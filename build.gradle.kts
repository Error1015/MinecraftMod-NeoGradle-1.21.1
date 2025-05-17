plugins {
    `java-library`
    idea
    `maven-publish`
    id("net.neoforged.gradle.userdev") version "7.0.184"
    kotlin("jvm") version "2.1.10"
    kotlin("plugin.serialization") version "2.1.10"
}

// Mod信息
val modName = project.property("mod_name") as String
val modVersion = project.property("mod_version") as String
val modGroup = project.property("mod_group_id") as String
val modId = project.property("mod_id") as String
val modLicense = project.property("mod_license") as String
val modAuthors = project.property("mod_authors") as String
val modDescription = project.property("mod_description") as String
// 基本环境
val mcVersion = project.property("minecraft_version") as String
val mcVersionRange = project.property("minecraft_version_range") as String
val neoVersion = project.property("neo_version") as String
val neoVersionRange = project.property("neo_version_range") as String
val loaderVersionRange = project.property("loader_version_range") as String
val parchmentVersion = project.property("parchment_version") as String
// 依赖信息
val kffVersion: String by project

version = modVersion
group = modGroup

base.archivesName = modName

minecraft {
    version = mcVersion
    accessTransformers.file("src/main/resources/META-INF/accesstransformer.cfg")
}

runs {
    configureEach {
        modSource(sourceSets.main.get())
    }

    create("client") {
        client()
        workingDirectory(file("run"))
        systemProperties(
            mapOf(
                "forge.logging.markers" to "REGISTRIES", "forge.logging.console.level" to "debug"
            )
        )
    }

    create("server") {
        server()
        workingDirectory(file("run/server"))
        // programArgument("--nogui")
        systemProperty("neoforge.enabledGameTestNamespaces", modId)
    }

    create("gameTestServer") {
        gameTest()
        workingDirectory(file("run/server"))
        systemProperty("neoforge.enabledGameTestNamespaces", modId)
    }

    create("data") {
        dataGenerator()
        workingDirectory(file("run"))
        arguments.addAll(
            "--mod", modId, "--all", "--output", file("src/generated/resources/").absolutePath, "--existing", file("src/main/resources/").absolutePath
        )
    }
}

subsystems.parchment {
    minecraftVersion = mcVersion
    mappingsVersion = parchmentVersion
}

sourceSets.main.get().resources.srcDir("src/generated/resources")

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

kotlin {
    jvmToolchain(21)

    compilerOptions {
        freeCompilerArgs.add("-Xwhen-guards") // when的卫语句
    }
}

repositories {
    mavenLocal()
    mavenCentral()
    mavenKff
    mavenBmcl
}

dependencies {
    implementation("net.neoforged:neoforge:$neoVersion")
    implementation("thedarkcolour:kotlinforforge-neoforge:$kffVersion")
}

tasks.withType<ProcessResources>().configureEach {
    val replaceProperties = mapOf(
        "minecraft_version" to mcVersion,
        "minecraft_version_range" to mcVersionRange,
        "neo_version" to neoVersion,
        "neo_version_range" to neoVersionRange,
        "loader_version_range" to loaderVersionRange,
        "mod_id" to modId,
        "mod_name" to modName,
        "mod_license" to modLicense,
        "mod_version" to modVersion,
        "mod_authors" to modAuthors,
        "mod_description" to modDescription
    )
    inputs.properties(replaceProperties)

    filesMatching(listOf("META-INF/neoforge.mods.toml")) {
        expand(replaceProperties)
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenKotlin") {
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

tasks.withType<JavaExec> {
    jvmArgs("-Dfile.encoding=UTF-8", "-Dsun.stdout.encoding=UTF-8", "-Dsun.stderr.encoding=UTF-8")
}

idea {
    module {
        isDownloadSources = true
        isDownloadSources = true
    }
}