rootProject.name = "MultiEnvTest"

fun createVersion(name: String) {
    include(name)
    val project = project(":$name")
    project.buildFileName = "../mod.gradle.kts"
}

createVersion("fabric")
createVersion("neoforge")
//createVersion("lexforge")
