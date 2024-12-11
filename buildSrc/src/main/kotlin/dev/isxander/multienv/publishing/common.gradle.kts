package dev.isxander.multienv.publishing

import dev.isxander.multienv.*
import dev.isxander.multienv.base.*

plugins {
    id("me.modmuss50.mod-publish-plugin")
    `maven-publish`
}

project.appliedExtensions += "publishing"
extensions.create<PublishingExtension>("multiEnvPublish")

publishMods {
    displayName = multiEnv.manifest.modName
    version = multiEnv.manifest.modVersion
}