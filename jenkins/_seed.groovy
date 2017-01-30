import jenkins.model.*
import javaposse.jobdsl.dsl.DslScriptLoader
import javaposse.jobdsl.plugin.JenkinsJobManagement
import groovy.io.FileType

inst = Jenkins.getInstance()

println "--> Creating jobs"
def dir = new File("/jobdsl");

jm = new JenkinsJobManagement(System.out, [:], new File('.'))
dslScriptLoader = new DslScriptLoader(jm)
dir.eachFileRecurse(FileType.FILES) { file ->
	dslScriptLoader.runScript(file.text)
}
