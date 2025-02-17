import com.android.build.api.artifact.SingleArtifact
import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.TaskAction

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
}

abstract class AarUploadTask : DefaultTask() {

    @get:InputFile abstract val aar: RegularFileProperty

    @TaskAction
    fun taskAction() {
        println("Uploading ${aar.get().asFile.absolutePath} to fantasy server...")
    }
}

android {
    compileSdkVersion(29)
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(29)
    }
}

androidComponents {
    onVariants { variant ->
        project.tasks.register<AarUploadTask>("${variant.name}AarUpload") {
            aar.set(variant.artifacts.get(SingleArtifact.AAR))
        }
    }
}
