package dev.isxander.multienv

/**
 * This plugin should be applied when using the NeoForge Loader.
 * Configure the `minecraft` and `neoforge` extensions.
 */

plugins {
    id("dev.isxander.multienv.common")
    id("net.neoforged.moddev")
}

project.extensions.extraProperties["neoForge"] = true

val neoExt = extensions.create<NeoForgeExtension>("neoforge")

minecraft._implementation = configurations.implementation
minecraft._compileOnly = configurations.compileOnly
minecraft._runtimeOnly = configurations.runtimeOnly

neoForge {
    version = neoExt.neoForgeVersion

    validateAccessTransformers = true
}



