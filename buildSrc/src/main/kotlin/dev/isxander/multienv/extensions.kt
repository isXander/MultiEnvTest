package dev.isxander.multienv

import net.fabricmc.loom.api.LoomGradleExtensionAPI
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

val Project.isFabric: Boolean
    get() = project.extensions.extraProperties["fabric"] as? Boolean ?: false
val Project.isNeoForge: Boolean
    get() = project.extensions.extraProperties["neoForge"] as? Boolean ?: false

fun Project.loom(block: LoomGradleExtensionAPI.() -> Unit) {
    if (isFabric) {
        configure<LoomGradleExtensionAPI>(block)
    }
}