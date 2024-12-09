package dev.isxander.multienv.base

import dev.isxander.multienv.Loader

/**
 * This plugin should be applied when using the NeoForge Loader.
 * Configure the `minecraft` and `neoforge` extensions.
 */

plugins {
    id("dev.isxander.multienv.base.common")
    id("net.neoforged.moddev")
}

project.extensions.extraProperties["appliedLoader"] = Loader.NeoForge.serialName

val neoExt = extensions.create<NeoForgeExtension>("neoforge")

neoForge {
    version = neoExt.neoForgeVersion

    validateAccessTransformers = true
}

val localRuntime: Configuration by configurations.creating {
    configurations.runtimeClasspath.invoke { extendsFrom(this@creating) }
}

multiEnv._implementation = configurations.implementation
multiEnv._compileOnly = configurations.compileOnly
multiEnv._runtimeOnly = configurations.runtimeOnly
multiEnv._localRuntime = localRuntime


