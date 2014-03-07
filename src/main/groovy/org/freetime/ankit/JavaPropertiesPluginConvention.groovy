package org.freetime.ankit

import org.gradle.api.Project

class JavaPropertiesPluginConvention {
    def String dataBagDir
    def String propertyFileDir

    def JavaPropertiesPluginConvention(Project project) {
        this.dataBagDir = "${project.projectDir}/conf/data-bags"
        this.propertyFileDir = "${project.projectDir}/src/main/resources"
    }
}

