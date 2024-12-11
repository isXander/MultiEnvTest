import java.nio.file.Path

rootProject.name = "MultiEnvTest"

fun createVersion(name: String) {
    include(name)
    val project = project(":$name")
    project.buildFileName = "../mod.gradle.kts"

    gradle.beforeProject {
        println("Configuring $name")
        if (project.path == path) {
            //configureSources()
        }
    }
}

createVersion("fabric")
createVersion("neoforge")


private fun Project.configureSources() {
    val formatter: (Path) -> Any = { src -> "../src/$src" }

    val parentDir = parent!!.projectDir.resolve("src").toPath()
    val thisDir = projectDir.resolve("src").toPath()

    fun applyChiseled(from: SourceDirectorySet, to: SourceDirectorySet = from) {
        from.sourceDirectories.mapNotNull {
            val relative = thisDir.relativize(it.toPath())
            if (relative.startsWith("..")) null
            else relative
        }.forEach {
            to.srcDir(formatter(it))
        }
    }

    for (it in property("sourceSets") as SourceSetContainer) {
        applyChiseled(it.allJava, it.java)
        applyChiseled(it.resources)
    }
}