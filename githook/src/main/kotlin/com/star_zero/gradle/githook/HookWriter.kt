package com.star_zero.gradle.githook

import java.io.File

class HookWriter(private val extension: GithookExtension) {

    companion object {
        private const val IDENTIFIER = "# gradle-githook"
        private val hookFiles = listOf(
            "applypatch-msg",
            "pre-applypatch",
            "post-applypatch",
            "pre-commit",
            "prepare-commit-msg",
            "commit-msg",
            "post-commit",
            "pre-rebase",
            "post-checkout",
            "post-merge",
            "pre-push",
            "pre-receive",
            "update",
            "post-receive",
            "post-update",
            "push-to-checkout",
            "pre-auto-gc",
            "post-rewrite",
            "sendemail-validate",
            "fsmonitor-watchman"
        )
    }

    fun write() {
        hookFiles.forEach(this::deleteFile)
        extension.hooks.forEach(this::writeFile)
    }

    private fun deleteFile(filename: String) {
        val file = File(extension.hooksDir, filename)

        if (file.exists() && !isUserCreated(file)) {
            log.debug("Delete file: $filename")
            file.delete()
        }
    }

    private fun writeFile(hook: Githook) {
        log.debug("hook: $hook")

        if (!hookFiles.contains(hook.name)) {
            log.warn("${hook.name} is not hook file")
            return
        }

        val file = File(extension.hooksDir, hook.name)

        if (isUserCreated(file)) {
            log.warn("User hook already exists: ${hook.name}")
            return
        }

        log.debug("Create file: ${hook.name}")
        file.writeText(getScript(extension.gradleCommand!!.absolutePath, hook))
        file.setExecutable(true)
    }

    private fun getScript(gradleCommand: String, hook: Githook): String {
        return """
        |#!/bin/sh
        |$IDENTIFIER
        |
        |${hook.taskScript(gradleCommand)}
        |
        |${hook.shellScript()}
        |
        |exit 0
        """.trimMargin()
    }

    private fun isUserCreated(file: File): Boolean {
        if (!file.exists()) {
            return false
        }
        return file.readText().indexOf(IDENTIFIER) == -1
    }
}
