plugins {
    id("dev.isxander.multienv.fabric") apply false
    id("dev.isxander.multienv.neoforge") apply false
}

subprojects {
    when (project.property("multienv.loader")) {
        "fabric" -> apply(plugin = "dev.isxander.multienv.fabric")
        "neoforge" -> apply(plugin = "dev.isxander.multienv.neoforge")
    }
}

