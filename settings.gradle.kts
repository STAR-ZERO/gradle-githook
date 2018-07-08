include("githook")

pluginManagement {
    repositories {
        maven { url = uri("https://plugins.gradle.org/m2/") }
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "org.jlleitschuh.gradle.ktlint") {
                useModule("gradle.plugin.org.jlleitschuh.gradle:ktlint-gradle:${requested.version}")
            }
        }
    }
}
