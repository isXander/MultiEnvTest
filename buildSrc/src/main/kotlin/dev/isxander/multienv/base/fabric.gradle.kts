package dev.isxander.multienv.base

import dev.isxander.multienv.*
import net.fabricmc.loom.api.mappings.layered.spec.LayeredMappingSpecBuilder

/**
 * This plugin should be applied when using the Fabric Loader.
 * Configure the `minecraft` and `fabric` extensions.
 */

plugins {
    id("dev.isxander.multienv.base.common")
    id("fabric-loom")
}

project.loader = Loader.Fabric

val fabricExt = extensions.create<FabricExtension>("fabric")

multiEnv._implementation = configurations.modImplementation
multiEnv._compileOnly = configurations.modCompileOnly
multiEnv._runtimeOnly = configurations.modRuntimeOnly
multiEnv._localRuntime = configurations.modLocalRuntime

repositories {
    maven("https://maven.fabricmc.net/")
}

dependencies {
    minecraft(multiEnv.minecraftVersion.map { "com.mojang:minecraft:${it}" })
    mappings(loom.officialMojangMappings())
//    mappings(
//        multiEnv.parchment.parchmentArtifact.orElse("").map { parchmentArtifact ->
//            loom.layered {
//                officialMojangMappings()
//                if (parchmentArtifact.isNotEmpty()) {
//                    parchment(parchmentArtifact)
//                }
//            }
//        }
//    )

    modImplementation(fabricExt.fabricLoaderVersion.map { "net.fabricmc:fabric-loader:$it" })
}

afterEvaluate {
    if (multiEnv.parchment.enabled.get()) {
        error("Parchment is not supported in Fabric yet.")
    }
}

// ---------------------------------------------------------------
// Generate mod metadata every project reload, instead of manually
// (see `generateModMetadata` task in `common.gradle.kts`)
// ---------------------------------------------------------------
val generateModMetadata by tasks.getting(ProcessResources::class) {
    exclude(modManifests - FABRIC_MANIFEST)
}

tasks.ideaSyncTask {
    dependsOn("generateModMetadata")
}