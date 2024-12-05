package dev.isxander.multienv

plugins {
    `java-library`
}

extensions.extraProperties["fabric"] = false
extensions.extraProperties["neoForge"] = false

val mcExt = extensions.create<MinecraftExtension>("minecraft")