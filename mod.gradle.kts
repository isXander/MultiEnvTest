import dev.isxander.multienv.*

minecraft {
    minecraftVersion = "1.21.4"
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
    neoForgeVersion = "21.4.0-beta"

    repositories {
        maven("https://thedarkcolour.github.io/KotlinForForge/")
    }

    // You can configure ModDevGradle as usual from here
    configureNeoforge {
        runs {
            create("client") {
                client()
                ideName = "NeoForge Client"
            }
        }
    }
}


dependencies {
    fabric { // using the same extension blocks as a way to only add dependencies under specific platforms
        minecraft.implementation("net.fabricmc.fabric-api:fabric-api:0.110.5+1.21.4")
    }

    neoforge {
        // using the `minecraft.` extension to access configurations uses correct configurations per-platform
        // for example, Loom's `modRuntimeOnly` or `runtimeOnly` for ModDevGradle
        /*minecraft.implementation("thedarkcolour:kotlinforforge-neoforge:4.10.0")*/
    }
}

