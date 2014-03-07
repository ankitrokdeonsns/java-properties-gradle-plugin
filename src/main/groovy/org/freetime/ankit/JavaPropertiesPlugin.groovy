package org.freetime.ankit

import org.gradle.api.Project
import org.gradle.api.Plugin
import groovy.json.JsonSlurper

class JavaPropertiesPlugin implements Plugin<Project> {
    void apply(Project project){
        project.extensions.create("javaproperties", JavaPropertiesPluginConvention, project)

        project.task("generateProperties") << {
            def dataBagDir = new File("${project.javaproperties.dataBagDir}")

            dataBagDir.eachFile {
                if(!it.isFile()) {
                    def jsonSlurper = new JsonSlurper()
                    def defaults = jsonSlurper.parse(new FileReader("${it.canonicalPath}/default.json"))
                    def env = project.properties["env"] ?: "default"
                    def overrides = jsonSlurper.parse(new FileReader("${it.canonicalPath}/${env}.json"))

                    def configObject = new ConfigObject()
                    configObject.putAll(defaults)
                    configObject.putAll(overrides)
                    for(e in configObject)
                        if(e.value instanceof List)
                            e.value = e.value.join(",")

                    def propertyFileDir = project.javaproperties.propertyFileDir
                    new File(propertyFileDir).mkdirs()
                    File propertyFile = new File("${propertyFileDir}/${it.name}.properties")
                    configObject.toProperties().store(propertyFile.newWriter(), null)
                }
            }
        }
    }
}
