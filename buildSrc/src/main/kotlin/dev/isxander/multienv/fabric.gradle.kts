package dev.isxander.multienv

/**
 * This plugin should be applied when using the Fabric Loader.
 * Configure the `minecraft` and `fabric` extensions.
 */

plugins {
    id("dev.isxander.multienv.common")
    id("fabric-loom")
}

project.extensions.extraProperties["fabric"] = true

val fabricExt = extensions.create<FabricExtension>("fabric")

minecraft._implementation = configurations.modImplementation
minecraft._compileOnly = configurations.modCompileOnly
minecraft._runtimeOnly = configurations.modRuntimeOnly
minecraft._localRuntime = configurations.modLocalRuntime

repositories {
    maven("https://maven.fabricmc.net/")
}

dependencies {
    minecraft(minecraft.minecraftVersion.map { "com.mojang:minecraft:${it}" })
    mappings(loom.officialMojangMappings())

    modImplementation(fabricExt.fabricLoaderVersion.map { "net.fabricmc:fabric-loader:${it}" })
}