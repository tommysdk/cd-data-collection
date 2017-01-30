import jenkins.model.*
import javaposse.jobdsl.dsl.DslScriptLoader
import javaposse.jobdsl.plugin.JenkinsJobManagement
import groovy.io.FileType

inst = Jenkins.getInstance()

println "--> Creating jobs"
def dir = new File("/jobdsl");

jm = new JenkinsJobManagement(System.out, [:], new File('.'))
dir.eachFileRecurse(FileType.FILES) { file ->
	DslScriptLoader.runDslEngine(file.text, jm)
}
