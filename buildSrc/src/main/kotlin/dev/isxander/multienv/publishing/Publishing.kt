package dev.isxander.multienv.publishing

import dev.isxander.multienv.appliedExtensions
import dev.isxander.multienv.base.FabricExtension
import dev.isxander.multienv.base.multiEnv
import dev.isxander.multienv.isFabric
import me.modmuss50.mpp.ModPublishExtension
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.property
import javax.inject.Inject

open class PublishingExtension @Inject constructor(objects: ObjectFactory, project: Project) {
    val mavenGroup = objects.property<String>().convention(project.provider { project.multiEnv.manifest.modGroup }.flatMap { it })
    val mavenArtifact = objects.property<String>().convention(project.provider { project.multiEnv.manifest.modId.map { it.replace('_', '-') } }.flatMap { it })
}

val Project.multiEnvPublish: PublishingExtension
    get() = extensions.getByType(PublishingExtension::class.java)
fun Project.multiEnvPublish(block: PublishingExtension.() -> Unit) {
    if ("publishing" in appliedExtensions) {
        multiEnvPublish.block()
    }
}

fun Project.configureModmussPublishing(action: ModPublishExtension.() -> Unit) {
    configure<ModPublishExtension>(action)
}
