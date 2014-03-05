package org.freetime.ankit

import org.gradle.api.Project
import org.gradle.api.Plugin
import groovy.json.JsonSlurper

class JavaPropertiesPlugin implements Plugin<Project> {
    void apply(Project project){
        project.task("generateProperties") << {
            println "Plugin Running!!!"
            def jsonSlurper = new JsonSlurper()
            def defaults = jsonSlurper.parseText("{ \"key\": \"value\" }")
            def override = jsonSlurper.parseText("{ \"key\": \"override\" }")
            def propertyMap = new HashMap(defaults)
            propertyMap.putAll(override)
            println propertyMap
        }
    }
}
