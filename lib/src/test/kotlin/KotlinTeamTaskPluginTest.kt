import kotlin.test.*
import org.gradle.testfixtures.ProjectBuilder
import org.gradle.api.Project

class KotlinTeamTaskPluginTest {

    private lateinit var project: Project

    @BeforeTest
    fun setup() {
        project = ProjectBuilder.builder().build()
        project.pluginManager.apply("org.edwardian.kotlinteamtaskplugin")
        assertTrue(
            project.plugins.hasPlugin("org.edwardian.kotlinteamtaskplugin"),
            "Plugin should be applied"
        )
    }

    @Test
    fun `test task is registered`() {
        val task = project.tasks.findByName("outputKotlinFiles")
        assertNotNull(task, "Task should be registered")
    }

    @Test
    fun `test task executes and check actions`() {
        val task = project.tasks.findByName("outputKotlinFiles")
        assertNotNull(task, "Task should be registered")
        if (task.actions.isNullOrEmpty()) {
            fail("Task has no actions, it may not be configured correctly!")
        }
        try {
            task.actions.forEach {
                it.execute(task)
            }
        } catch (e: Exception) {
            fail("Task execution failed with exception: ${e.message}")
        }
    }
}