import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.lutukai.convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class KotlinAndroidConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            pluginManager.apply("org.jetbrains.kotlin.android")

            when {
                pluginManager.hasPlugin("com.android.application") -> {
                    extensions.configure<ApplicationExtension> {
                        configureKotlinAndroid(this)
                    }
                }
                pluginManager.hasPlugin("com.android.library") -> {
                    extensions.configure<LibraryExtension>{
                        configureKotlinAndroid(this)
                    }
                }
            }
        }
    }
}