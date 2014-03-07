package org.freetime.ankit

import org.gradle.api.Project
import org.gradle.api.Plugin
import groovy.json.JsonSlurper

class JavaPropertiesPlugin implements Plugin<Project> {
    void apply(Project project){
        project.task("generateProperties") << {
            println "Plugin Running!!!"
            def jsonSlurper = new JsonSlurper()
            def defaults = jsonSlurper.parse(new FileReader("${project.projectDir}/conf/data-bags/application/default.json"))
            def env = project.properties["env"] ?: "default"
            def override = jsonSlurper.parse(new FileReader("${project.projectDir}/conf/data-bags/application/${env}.json"))
            def configObject = new ConfigObject()
            configObject.putAll(defaults)
            configObject.putAll(override)
            for(e in configObject)
                if(e.value instanceof List)
                    e.value = e.value.join(",")
            File propertyFile = new File("application.properties")
            configObject.toProperties().store(propertyFile.newWriter(), null)
            println configObject.toProperties()
        }
    }
}
