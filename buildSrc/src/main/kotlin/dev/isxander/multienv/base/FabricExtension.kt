package dev.isxander.multienv.base

/**
 * The extension for configuring Fabric-specific settings.
 */

import dev.isxander.multienv.*
import net.fabricmc.loom.api.LoomGradleExtensionAPI
import org.gradle.api.Project
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import org.gradle.kotlin.dsl.configure
import javax.inject.Inject

open class FabricExtension @Inject constructor(objects: ObjectFactory) {
    val fabricLoaderVersion: Property<String> = objects.property(String::class.java)
}

val Project.fabric: FabricExtension
    get() = extensions.getByType(FabricExtension::class.java)
fun Project.fabric(block: FabricExtension.() -> Unit) {
    if (isFabric) {
        fabric.block()
    }
}

fun Project.configureLoom(block: LoomGradleExtensionAPI.() -> Unit) {
    configure<LoomGradleExtensionAPI>(block)
}