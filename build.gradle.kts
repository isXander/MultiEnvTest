import dev.isxander.multienv.*

plugins {
    id("dev.isxander.multienv.fabric")
}

minecraft {
    minecraftVersion = "1.21.4"
}

fabric {
    fabricLoaderVersion = "0.16.9"

    loom {
        runConfigs.getByName("server") {
            ideConfigGenerated(false)
        }
    }
}

neoforge {
    neoForgeVersion = "21.4.0-beta"

    repositories {
        maven("https://thedarkcolour.github.io/KotlinForForge/")
    }
}


dependencies {
    fabric {
        minecraft.implementation("net.fabricmc.fabric-api:fabric-api:0.110.5+1.21.4")
    }

    neoforge {
        minecraft.runtimeOnly("thedarkcolour:kotlinforforge-neoforge:4.10.0")
    }
}

