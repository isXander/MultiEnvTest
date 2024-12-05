package dev.isxander.multienv

/**
 * The extension for configuring Fabric-specific settings.
 */

import org.gradle.api.Project
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
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