plugins {
    id("com.star-zero.gradle.githook") version "1.2.1"
}

githook {
    gradleCommand = File("gradle_test")
    hooksDir = File(rootDir, "githook_test/hooks")
    hooks {
        "pre-commit" {
            task = "lint test"
            shell = "echo 1"
        }
        "pre-push" {
            task = "someTask"
            shell = "someShell"
        }
    }
}
