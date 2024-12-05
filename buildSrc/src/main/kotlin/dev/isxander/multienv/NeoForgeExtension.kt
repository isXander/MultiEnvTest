package dev.isxander.multienv

/**
 * The extension for configuring Neoforge-specific settings.
 */

import org.gradle.api.Project
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import javax.inject.Inject

open class NeoForgeExtension @Inject constructor(objects: ObjectFactory) {
    val neoForgeVersion: Property<String> = objects.property(String::class.java)
}

val Project.neoforge: NeoForgeExtension
    get() = extensions.getByType(NeoForgeExtension::class.java)
fun Project.neoforge(block: NeoForgeExtension.() -> Unit) {
    if (isNeoForge) {
        neoforge.block()
    }
}