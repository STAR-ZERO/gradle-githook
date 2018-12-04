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
            if (extension.hooksDir!!.isDirectory) {
                HookWriter(extension).write()
            } else if (extension.failOnMissingHooksDir) {
                throw GradleException("Can't find hooks directory: ${extension.hooksDir}")
            } else {
                log.info("Can't find hooks directory ${extension.hooksDir}, no hooks written")
            }
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
    }
}
