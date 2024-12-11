package dev.isxander.multienv

import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

internal fun Project.applyMultienv(idResolver: (Loader) -> String?) {
    childProjects.forEach { _, childProject ->
        childProject.run configure@{
            val desiredLoaderStr = project.findProperty("multienv.loader")?.toString() ?: run {
                println("Subproject `${project.name}` is missing 'multienv.loader' property. No loader configuration will be applied")
                return@configure
            }

            val desiredLoader = Loader.fromSerialName(desiredLoaderStr)
                ?: error("Unknown loader on project `${project.name}`: $desiredLoaderStr")

            idResolver(desiredLoader)?.let { apply(plugin = it) }
        }
    }
}
internal fun Project.applyMultienv(extensionId: String) = applyMultienv { "dev.isxander.multienv.$extensionId.${it.friendlyName}" }


val Project.appliedExtensions: MutableSet<String>
    get() {
        val extraProperties = project.extensions.extraProperties
        if (!extraProperties.has("appliedExtensions")) {
            extraProperties["appliedExtensions"] = mutableSetOf<String>()
        }
        return extraProperties["appliedExtensions"] as MutableSet<String>
    }

internal fun Project.assertHasBase() {
    pluginManager.hasPlugin("dev.isxander.multienv.base") || error("MultiEnv base plugin (`dev.isxander.multienv.base`) has not been applied.")
}