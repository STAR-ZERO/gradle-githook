package com.star_zero.gradle.githook

import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

class GithookPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val extension = project.extensions.create("githook", GithookExtension::class.java, project)

        project.afterEvaluate {
            setupExtensions(project, extension)
            HookWriter(extension).write()
        }
    }

    private fun setupExtensions(project: Project, extension: GithookExtension) {
        if (extension.gradleCommand == null) {
            extension.gradleCommand = File(project.rootProject.rootDir, "gradlew")
        }
        log.debug("gradleCommand: ${extension.gradleCommand?.absolutePath}")

        if (extension.hooksDir == null) {
            extension.hooksDir = File(project.rootProject.rootDir, ".git/hooks")
        }
        log.debug("hooksDir: ${extension.hooksDir}")

        if (!extension.hooksDir!!.isDirectory) {
            throw GradleException("Can't find hooks directory: ${extension.hooksDir}")
        }
    }
}
