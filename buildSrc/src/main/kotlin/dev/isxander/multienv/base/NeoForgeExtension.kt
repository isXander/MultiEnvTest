package dev.isxander.multienv.base

/**
 * The extension for configuring Neoforge-specific settings.
 */

import dev.isxander.multienv.*
import org.gradle.api.Project
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.configure
import javax.inject.Inject

open class NeoForgeExtension @Inject constructor(objects: ObjectFactory) {
    val neoForgeVersion: Property<String> = objects.property(String::class.java)

    /**
     * Creates two run configurations: one for the client and one for the server.
     * [namingConvention] is a function that takes a string (the side, Client or Server) and returns the IDE name of the run config.
     */
    fun Project.defaultRuns(namingConvention: (String) -> String = { "NeoForge $it" }) {
        configureNeoforge {
            runs {
                create("client") {
                    client()
                    ideName = namingConvention("Client")
                }
                create("server") {
                    server()
                    ideName = namingConvention("Server")
                }
            }
        }
    }
}

val Project.neoforge: NeoForgeExtension
    get() = extensions.getByType(NeoForgeExtension::class.java)
fun Project.neoforge(block: NeoForgeExtension.() -> Unit) {
    if (isNeoForge) {
        neoforge.block()
    }
}

fun Project.configureNeoforge(block: net.neoforged.moddevgradle.dsl.NeoForgeExtension.() -> Unit) {
    configure<net.neoforged.moddevgradle.dsl.NeoForgeExtension>(block)
}