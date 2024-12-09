package dev.isxander.multienv.base

plugins {
    `java-library`
}

val mcExt = extensions.create<MultiEnvExtension>("multiEnv")

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

java.toolchain.languageVersion.set(mcExt.javaTarget.map { JavaLanguageVersion.of(it) })
