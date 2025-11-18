import com.android.build.gradle.LibraryExtension
import com.lutukai.convention.configureKotlinAndroid
import com.lutukai.convention.getIntVersion
import com.lutukai.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.support.kotlinCompilerOptions

class AndroidLibraryConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            with(pluginManager){
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension>{
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = getIntVersion("targetSdk")
            }

            dependencies {
                add("implementation", libs.findLibrary("kotlinx.coroutines.android").get())
                add("coreLibraryDesugaring", libs.findLibrary("android.desugarJdkLibs").get())
            }
        }
    }
}