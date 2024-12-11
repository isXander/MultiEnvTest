package dev.isxander.multienv.base

import dev.isxander.multienv.*

/**
 * This plugin should be applied when using the NeoForge Loader.
 * Configure the `minecraft` and `neoforge` extensions.
 */

plugins {
    id("dev.isxander.multienv.base.common")
    id("net.neoforged.moddev")
}

project.loader = Loader.NeoForge

val neoExt = extensions.create<NeoForgeExtension>("neoforge")

neoForge {
    version = neoExt.neoForgeVersion
    parchment {
        parchmentArtifact = multiEnv.parchment.parchmentArtifact
        enabled = multiEnv.parchment.enabled
    }

    validateAccessTransformers = true

    runs {
        configureEach {
            // Recommended practice per Neoforge MDK
            logLevel = org.slf4j.event.Level.DEBUG
        }
    }
}

// --------------------------------------------
// Setup abstracted mod-specific configurations
// --------------------------------------------
val localRuntime: Configuration by configurations.creating {
    configurations.runtimeClasspath.invoke { extendsFrom(this@creating) }
}
multiEnv._implementation = configurations.implementation
multiEnv._compileOnly = configurations.compileOnly
multiEnv._runtimeOnly = configurations.runtimeOnly
multiEnv._localRuntime = localRuntime

// --------------------------------------------------------
// Allow for generated resource to be included in the build
// --------------------------------------------------------
sourceSets.main.configure {
    resources {
        srcDir("src/generated/resources")
    }
}

// ---------------------------------------------------------------
// Generate mod metadata every project reload, instead of manually
// (see `generateModMetadata` task in `common.gradle.kts`)
// ---------------------------------------------------------------
neoForge.ideSyncTask(tasks.named("generateModMetadata"))


