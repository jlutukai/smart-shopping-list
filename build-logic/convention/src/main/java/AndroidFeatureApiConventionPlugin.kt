import com.android.build.api.dsl.LibraryExtension
import com.lutukai.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureApiConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("app.android.library")
            }

            extensions.configure<LibraryExtension> {
                defaultConfig {
                    consumerProguardFiles("consumer-rules.pro")
                }

            }

            dependencies {
                // API modules typically need these core dependencies
                add("implementation", libs.findLibrary("javax.inject").get())
                add("implementation", libs.findLibrary("kotlinx.coroutines.core").get())
            }
        }
    }
}