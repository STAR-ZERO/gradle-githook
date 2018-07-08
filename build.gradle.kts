allprojects {
    repositories {
        jcenter()
    }
}

tasks {
    val githook by creating(GradleBuild::class) {
        dir = file("githook")
        tasks = listOf("publish")
    }

    // $ ./gradlew clean sampleBuild
    val sampleBuild by creating(GradleBuild::class) {
        dir = file("sample")
        tasks = listOf("build")
    }
    sampleBuild.dependsOn(githook)

    // $ ./gradlew clean sampleBuildKts
    val sampleBuildKts by creating(GradleBuild::class) {
        dir = file("sampleKts")
        tasks = listOf("build")
    }

    sampleBuildKts.dependsOn(githook)
}

// Show debug log
// $ ./gradlew clean sampleBuild --debug | grep "\[githook\]"
