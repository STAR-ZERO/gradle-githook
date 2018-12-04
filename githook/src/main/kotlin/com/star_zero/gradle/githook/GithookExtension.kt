package com.star_zero.gradle.githook

import org.gradle.api.Action
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import java.io.File

open class GithookExtension(project: Project) {

    var gradleCommand: File? = null
    var hooksDir: File? = null
    var failOnMissingHooksDir: Boolean = true

    val hooks = project.container(Githook::class.java) { name ->
        Githook(name)
    }

    fun hooks(action: Action<NamedDomainObjectContainer<Githook>>) {
        action.execute(hooks)
    }
}

data class Githook(
    val name: String,
    var task: String? = null,
    var shell: String? = null
) {
    companion object {
        private const val CHECK_EXIT_STATUS = "[ \$? -gt 0 ] && exit 1"
    }

    fun taskScript(gradleCommand: String): String {
        return if (task.isNullOrBlank()) {
            ""
        } else {
            "$gradleCommand $task\n$CHECK_EXIT_STATUS"
        }
    }

    fun shellScript(): String {
        return if (shell.isNullOrBlank()) {
            ""
        } else {
            "$shell\n$CHECK_EXIT_STATUS"
        }
    }
}
