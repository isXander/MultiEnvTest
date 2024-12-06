package dev.isxander.multienv

import net.fabricmc.loom.api.LoomGradleExtensionAPI
import net.neoforged.moddevgradle.dsl.NeoForgeExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

val Project.isFabric: Boolean
    get() = project.extensions.extraProperties["fabric"] as? Boolean ?: false
val Project.isNeoForge: Boolean
    get() = project.extensions.extraProperties["neoForge"] as? Boolean ?: false

fun Project.configureLoom(block: LoomGradleExtensionAPI.() -> Unit) {
    configure<LoomGradleExtensionAPI>(block)
}

fun Project.configureNeoforge(block: NeoForgeExtension.() -> Unit) {
    configure<NeoForgeExtension>(block)
}