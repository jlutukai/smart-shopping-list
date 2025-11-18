import com.android.build.api.dsl.LibraryExtension
import com.lutukai.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureImplConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("app.android.library")
                apply("app.android.hilt")
                apply("app.android.compose")
                apply("app.android.test")
            }

            extensions.configure<LibraryExtension> {
                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
            }

            // Automatically depend on the API module if it exists
            val apiProjectPath = project.path.replace("-impl", "-api")
            if (rootProject.findProject(apiProjectPath) != null) {
                dependencies {
                    add("api", project(apiProjectPath))
                }
            }

            dependencies {
                // Common implementation dependencies
                add("implementation", libs.findLibrary("androidx.lifecycle.runtime.ktx").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.viewmodel.ktx").get())
                add("implementation", libs.findLibrary("timber").get())
            }
        }
    }
}