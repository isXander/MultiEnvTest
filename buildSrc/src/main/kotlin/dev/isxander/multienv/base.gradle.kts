package dev.isxander.multienv

childProjects.forEach { _, childProject ->
    childProject.run {
        applyMultienv()
    }
}

private fun Project.applyMultienv() {
    val desiredLoader = project.findProperty("multienv.loader")?.toString()

    when {
        desiredLoader == null -> println("Subproject `${project.name}` is missing 'multienv.loader' property. No loader configuration will be applied")
        else -> when (desiredLoader.let { Loader.fromSerialName(it) }) {
            Loader.Fabric -> apply(plugin = "dev.isxander.multienv.base.fabric")
            Loader.NeoForge -> apply(plugin = "dev.isxander.multienv.base.neoforge")
            null -> error("Unknown loader on project `${project.name}`: $desiredLoader")
        }
    }
}