package dev.isxander.multienv

import org.gradle.api.Project

enum class Loader(val friendlyName: String) {
    Fabric("fabric"),
    NeoForge("neoforge");

    companion object {
        fun fromSerialName(name: String): Loader? {
            return values().firstOrNull { it.friendlyName == name }
        }
    }
}

val Project.loaderOrNull: Loader?
    get() = (project.extensions.extraProperties["appliedLoader"] as String?)?.let { Loader.fromSerialName(it) }
var Project.loader: Loader
    get() = loaderOrNull ?: throw IllegalStateException("Loader not set")
    internal set(value) {
        project.extensions.extraProperties["appliedLoader"] = value.friendlyName
    }


val Project.isFabric: Boolean
    get() = loader == Loader.Fabric
val Project.isNeoForge: Boolean
    get() = loader == Loader.NeoForge

const val FABRIC_MANIFEST = "fabric.mod.json"
const val NEOFORGE_MANIFEST = "META-INF/neoforge.mods.toml"
const val OLD_NEOFORGE_MANIFEST = "META-INF/mods.toml"
val modManifests = listOf(FABRIC_MANIFEST, NEOFORGE_MANIFEST, OLD_NEOFORGE_MANIFEST)
