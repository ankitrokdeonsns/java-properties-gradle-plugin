package org.freetime.ankit

import org.gradle.api.Project
import org.gradle.api.Plugin

class JavaPropertiesPlugin implements Plugin<Project> {
    void apply(Project project){
        project.task("generateProperties") << {
            println "Plugin Running!!!"
        }
    }
}
