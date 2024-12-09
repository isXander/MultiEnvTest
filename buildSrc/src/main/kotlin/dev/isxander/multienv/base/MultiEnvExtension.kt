package dev.isxander.multienv.base

/**
 * The "common" extension for all loaders, as much configuration is done in this extension
 * such as configuring Minecraft version, mappings, mixins etc.
 */

import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import javax.inject.Inject

open class MultiEnvExtension @Inject constructor(objects: ObjectFactory) {
    val minecraftVersion: Property<String> = objects.property(String::class.java)
    val javaTarget: Property<Int> = objects.property(Int::class.java)


    val _implementation = objects.property(Configuration::class.java)
    val implementation: Configuration get() = _implementation.get()
    val _compileOnly = objects.property(Configuration::class.java)
    val compileOnly: Configuration get() = _compileOnly.get()
    val _runtimeOnly = objects.property(Configuration::class.java)
    val runtimeOnly: Configuration get() = _runtimeOnly.get()
    val _localRuntime = objects.property(Configuration::class.java)
    val localRuntime: Configuration get() = _localRuntime.get()
}

operator fun MultiEnvExtension.invoke(block: MultiEnvExtension.() -> Unit) = block()
val Project.multiEnv: MultiEnvExtension
    get() = extensions.getByType(MultiEnvExtension::class.java)

