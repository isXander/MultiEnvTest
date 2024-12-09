package dev.isxander.multienv

import net.fabricmc.loom.api.LoomGradleExtensionAPI
import net.neoforged.moddevgradle.dsl.NeoForgeExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

enum class Loader(val serialName: String) {
    Fabric("fabric"),
    NeoForge("neoforge");

    companion object {
        fun fromSerialName(name: String): Loader? {
            return values().firstOrNull { it.serialName == name }
        }
    }
}

val Project.loaderOrNull: Loader?
    get() = (project.extensions.extraProperties["loader"] as String?)?.let { Loader.fromSerialName(it) }
val Project.loader: Loader
    get() = loaderOrNull ?: throw IllegalStateException("Loader not set")


val Project.isFabric: Boolean
    get() = loader == Loader.Fabric
val Project.isNeoForge: Boolean
    get() = loader == Loader.NeoForge
