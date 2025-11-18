package com.lutukai.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidCompose(commonExtension: CommonExtension<*, *, *, *, *, *>) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = libs.findVersion("androidxComposeCompiler").get().toString()
        }
    }

    dependencies{
        val bom = libs.findLibrary("androidx-compose-bom").get()
        add("implementation", platform(bom))
        add("implementation", libs.findLibrary("androidx.compose.ui").get())
        add("implementation", libs.findLibrary("androidx.compose.ui.graphics").get())
        add("implementation", libs.findLibrary("androidx.compose.ui.tooling.preview").get())
        add("implementation", libs.findLibrary("androidx.compose.material3").get())
        add("implementation", libs.findLibrary("androidx.lifecycle.viewmodel.compose").get())
        add("implementation", libs.findLibrary("androidx.hilt.navigation.compose").get())

        add("debugImplementation", libs.findLibrary("androidx.compose.ui.tooling").get())
        add("debugImplementation", libs.findLibrary("androidx.compose.ui.test.manifest").get())
    }
}

internal fun Project.configureAndroidTests() {
    dependencies {
        add("testImplementation", libs.findLibrary("junit").get())
        add("testImplementation", libs.findLibrary("mockk").get())
        add("testImplementation", libs.findLibrary("turbine").get())
        add("testImplementation", libs.findLibrary("kotlinx.coroutines.test").get())
        add("testImplementation", libs.findLibrary("truth").get())

        add("androidTestImplementation", libs.findLibrary("androidx.test.ext.junit").get())
        add("androidTestImplementation", libs.findLibrary("androidx.test.espresso.core").get())
        add("androidTestImplementation", libs.findLibrary("androidx.compose.ui.test.junit4").get())
    }
}