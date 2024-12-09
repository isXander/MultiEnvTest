package dev.isxander.multienv.base

import dev.isxander.multienv.*

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

    modImplementation(fabricExt.fabricLoaderVersion.map { "net.fabricmc:fabric-loader:${it}" })
}