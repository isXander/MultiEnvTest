package dev.isxander.multienv.publishing

import net.fabricmc.loom.task.RemapJarTask

plugins {
    id("dev.isxander.multienv.publishing.common")
    id("dev.isxander.multienv.base.fabric")
}

val remapJar = tasks.named<RemapJarTask>("remapJar")

multiEnvPublish {
    configureModmussPublishing {
        file = remapJar.flatMap { it.archiveFile }
        modLoaders.add("fabric")
    }
}