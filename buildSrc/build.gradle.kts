plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    gradlePluginPortal()
    maven("https://maven.fabricmc.net/")
    maven("https://maven.neoforged.net/releases/")
}

dependencies {
    fun plugin(id: String, version: String) = "$id:$id.gradle.plugin:$version"

    implementation("net.fabricmc:fabric-loom:1.9.+")
    implementation(plugin("net.neoforged.moddev", "1.0.23"))
    implementation(plugin("me.modmuss50.mod-publish-plugin", "0.8.1"))
}
