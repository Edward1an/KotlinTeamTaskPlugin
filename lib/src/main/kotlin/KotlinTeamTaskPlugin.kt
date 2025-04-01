import org.gradle.api.Plugin
import org.gradle.api.Project

class KotlinTeamTaskPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val taskName = "outputKotlinFiles"
        project.tasks.register("outputKotlinFiles") { task ->
            task.group = "build"
            task.description = "Finds all Kotlin source files and writes them to a file"

            task.doLast {
                val kotlinPluginApplied = project.plugins.hasPlugin("org.jetbrains.kotlin.jvm") ||
                        project.plugins.hasPlugin("org.jetbrains.kotlin.android")

                if (!kotlinPluginApplied) {
                    println("Plugin is not applied. Skipping task.")
                    return@doLast
                }

                val kotlinFiles = project.fileTree("src")
                kotlinFiles.setIncludes(setOf("**/*.kt", "**/*.kts"))

                val ktFiles = kotlinFiles.files.filter { it.extension == "kt" }
                val ktsFiles = kotlinFiles.files.filter { it.extension == "kts" }
                val rootDir = project.rootDir
                val ktFilesText = if (ktFiles.isNotEmpty()) {
                    "# .KT files:\n" + ktFiles.joinToString("\n") {
                        "### " + it.relativeTo(rootDir).path
                    }
                } else {
                    "# .KT files:\n### There are no .kt files."
                }
                val ktsFilesText = if (ktsFiles.isNotEmpty()) {
                    "# .KTS files:\n" + ktsFiles.joinToString("\n") {
                        "### " + it.relativeTo(rootDir).path
                    }
                } else {
                    "# .KTS files:\n### There are no .kts files."
                }
                val outputFile =
                    project.layout.buildDirectory.file("kotlin_sources.md").get().asFile

                outputFile.parentFile.mkdirs()
                val repeatCommand: String = """
                    ### Repeat output
                    (since our gradle task is called from build folder, we're going to have an error. That s why we should call it from root)
                    ``` bash
                    cd .. 
                    gradle $taskName
                    ```
                """.trimIndent()
                outputFile.writeText("$ktFilesText\n\n$ktsFilesText\n$repeatCommand")
                println("Kotlin source files written to ${outputFile.absolutePath}")
            }
        }
    }
}