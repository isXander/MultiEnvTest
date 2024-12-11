package dev.isxander.multienv.publishing

import org.gradle.jvm.tasks.Jar

plugins {
    id("dev.isxander.multienv.publishing.common")
    id("dev.isxander.multienv.base.neoforge")
}

val jar = tasks.named<Jar>("jar")

multiEnvPublish {
    configureModmussPublishing {
        file = jar.flatMap { it.archiveFile }
        modLoaders.add("neoforge")
    }
}