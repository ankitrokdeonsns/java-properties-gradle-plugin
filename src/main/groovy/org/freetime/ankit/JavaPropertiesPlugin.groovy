package org.freetime.ankit

import org.gradle.api.Project
import org.gradle.api.Plugin
import groovy.json.JsonSlurper

class JavaPropertiesPlugin implements Plugin<Project> {
    void apply(Project project){
        project.task("generateProperties") << {
            println "Plugin Running!!!"
            def jsonSlurper = new JsonSlurper()
            def defaults = jsonSlurper.parseText("{ \"a\": 1, \"b\": [\"p\", \"q\"], \"c\": { \"d\": \"e\"} }")
            def override = jsonSlurper.parseText("{ \"a\": 2, \"b\": [\"p\", \"q\"], \"c\": { \"d\": \"e\"} }")
            def configObject = new ConfigObject()
            configObject.putAll(defaults)
            configObject.putAll(override)
            for(e in configObject)
                if(e.value instanceof List)
                    e.value = e.value.join(",")
            println configObject.toProperties()
        }
    }
}
