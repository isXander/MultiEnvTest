package dev.isxander.multienv.base

import dev.isxander.multienv.*

plugins {
    `java-library`
    idea
}
project.appliedExtensions += "base"

val mcExt = extensions.create<MultiEnvExtension>("multiEnv")

// ---------------------
// Generic project setup
// ---------------------
base {
    archivesName = mcExt.manifest.modId
}

// project version does not support `Provider`s, so we have to set it after the project is evaluated
afterEvaluate {
    group = mcExt.manifest.modGroup.get()
    version = mcExt.manifest.modVersion.get()
}

repositories {
    mavenCentral()
    maven("https://maven.parchmentmc.org") { name = "ParchmentMC" }
}

// -----------------------
// Configure java settings
// -----------------------

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

java.toolchain.languageVersion.set(mcExt.javaTarget.map { JavaLanguageVersion.of(it) })

// ------------------------------------------
// Configure mod metadata string replacements
// ------------------------------------------

// An alternative to the traditional `processResources` setup that is compatible with
// IDE-managed runs (e.g. IntelliJ non-delegated build)
val generateModMetadata by tasks.registering(ProcessResources::class) {
    val manifest = mcExt.manifest

    val baseProperties = mapOf<String, Provider<String>>(
        "minecraft_version" to mcExt.minecraftVersion,
        "mod_version" to manifest.modVersion,
        "mod_name" to manifest.modName,
        "mod_id" to manifest.modId,
        "mod_license" to manifest.modLicense,
        "mod_description" to manifest.modDescription,
    )

    // Combine the lazy-valued base properties with the replacement properties, lazily
    val allProperties = manifest.replacementProperties.map { replacementProperties ->
        baseProperties.mapValues { (_, value) -> value.get() } + replacementProperties
    }

    // Lazily provide the inputs
    inputs.property("allProperties", allProperties)

    // Expand lazily resolved properties only during execution
    doFirst {
        val resourcedProperties = allProperties.get()
        expand(resourcedProperties)
    }

    from("src/main/templates")
    into("build/generated/sources/modMetadata")
}
// Include the output of "generateModMetadata" as an input directory for the build
// This allows the funny dest dir (`generated/sources/modMetadata`) to be included in the root of the build
sourceSets.main.configure { resources.srcDir(generateModMetadata) }

// ----------
// IDEA setup
// ----------

// Ensure that IDEA downloads sources/javadoc jars for dependencies
idea {
    module {
        isDownloadSources = true
        isDownloadJavadoc = true
    }
}
