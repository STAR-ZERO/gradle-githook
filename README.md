Gradle Git Hook
===

This plugin automatically create git hook script file.

## Install

Add to build.gradle.

```groovy
buildscript {
  repositories {
    maven {
      url "https://plugins.gradle.org/m2/"
    }
  }
  dependencies {
    classpath "gradle.plugin.com.star-zero.gradle:githook:1.0.1"
  }
}

apply plugin: "com.star-zero.gradle.githook"
```

or (Gradle 2.1 and later)

```groovy
plugins {
  id "com.star-zero.gradle.githook" version "1.0.1"
}
```

## Configuration

```
githook {
    gradleCommand = file("gradle_test")
    hooksDir = file(new File(rootDir, "githook_test/hooks"))
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
```

* gradleCommand (optional)
	* Gradle Command file.
	* Default: `<rood_dir>/gradlew`
* hooksDir (optional)
	* Git Hooks directory
	* Default: `<root_dir>/.git/hooks`
* hooks
	* Git hook script file name. See [document](https://git-scm.com/docs/githooks).
	*  Gradle task or shell.

## Run

Automatically create git hook script file when you run any gradle task.
