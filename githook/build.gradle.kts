plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `maven-publish`
    id("org.jlleitschuh.gradle.ktlint") version "4.1.0"
    id("com.gradle.plugin-publish") version "0.9.10"
}

group = "com.star-zero.gradle"
version = "1.1.0"

gradlePlugin {
    (plugins) {
        "githook" {
            id = "com.star-zero.gradle.githook"
            implementationClass = "com.star_zero.gradle.githook.GithookPlugin"
        }
    }
}

publishing {
    repositories {
        maven(url = "build/repo")
    }
}

// How to publish
// $ ./gradlew clean publishPlugins -Pgradle.publish.key=<key> -Pgradle.publish.secret=<secret>
pluginBundle {
    website = "https://github.com/STAR-ZERO/gradle-githook"
    vcsUrl = "https://github.com/STAR-ZERO/gradle-githook.git"

    (plugins) {
        "githook" {
            id = "com.star-zero.gradle.githook"
            displayName = "Automatically create git hook scripts."
            description = "Automatically create git hook scripts."
            tags = listOf("git")
        }
    }
}
