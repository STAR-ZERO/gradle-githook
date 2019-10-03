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
    classpath "gradle.plugin.com.star-zero.gradle:githook:1.1.0"
  }
}

apply plugin: "com.star-zero.gradle.githook"
```

or (Gradle 2.1 and later)

```groovy
plugins {
  id "com.star-zero.gradle.githook" version "1.1.0"
}
```

## Configuration

```
githook {
    gradleCommand = file("gradle_test")
    hooksDir = file(new File(rootDir, "githook_test/hooks"))
    failOnMissingHooksDir = false
    createHooksDirIfNotExist = false
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
* failOnMissingHooksDir (optional)
    * Indicates if the build should fail if the hooks dir does not exist
    * Default `true`
* hooks
	* Git hook script file name. See [document](https://git-scm.com/docs/githooks).
	*  Gradle task or shell.
* createHooksDirIfNotExist
    * Indicates if hooks directory is missing, create the directory
    * Default `false`

## Run

Automatically create git hook script file when you run any gradle task.

## License

	Copyright 2018 Kenji Abe
	
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	
	    http://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.

