import dev.isxander.multienv.*
import dev.isxander.multienv.base.*

group = "dev.isxander"

multiEnv {
    minecraftVersion = project.property("multienv.minecraft") as String
    javaTarget = 21

//    parchment {
//        mappingsVersion = "2024.12.07"
//        minecraftVersion = "1.21.4"
//    }

    manifest {
        modName = "MultiEnv Testing Mod"
        modVersion = "1.0.0+${loader.friendlyName}"
        modId = "multienv-test"
        modDescription = "A test mod for MultiEnv"
        modLicense = "ARR"
    }
}

afterEvaluate {
    println("Hello, world!")
}

// Fabric block is only evaluated if the fabric plugin is applied
fabric {
    fabricLoaderVersion = "0.16.9"

    // You can configure loom as-usual from here
    configureLoom {
        runs {
            getByName("client") {
                name("Fabric Client")
            }
            getByName("server") {
                ideConfigGenerated(false)
            }
        }
    }
}

// NeoForge block is only evaluated if the neoforge plugin is applied
neoforge {
    neoForgeVersion = project.property("multienv.neoforge") as String

    repositories {
        maven("https://thedarkcolour.github.io/KotlinForForge/")
    }

    // creates two run configurations: `client` and `server`
    defaultRuns { "NeoForge $it" }

    // You can configure ModDevGradle as usual from here
    configureNeoforge {
        parchment {
            // ...
        }
    }
}


dependencies {
    fabric { // using the same extension blocks as a way to only add dependencies under specific platforms
        multiEnv.implementation("net.fabricmc.fabric-api:fabric-api:0.110.5+1.21.4")
    }

    neoforge {
        // using the `minecraft.` extension to access configurations uses correct configurations per-platform
        // for example, Loom's `modRuntimeOnly` or `runtimeOnly` for ModDevGradle
        /*minecraft.implementation("thedarkcolour:kotlinforforge-neoforge:4.10.0")*/
    }
}
