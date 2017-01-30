import jenkins.model.*
import javaposse.jobdsl.dsl.DslScriptLoader
import javaposse.jobdsl.dsl.DslFactory
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

def createJob(final def dslFactory,
              final String projectName,
              final String stageName,
              final List<String> downstreamJobs) {
dslScriptLoader.runScript("""\
  job('$projectName-$stageName') {
    description '$projectName / $stageName'
    parameters {
      stringParam('VERSION', null, null)
    }
    deliveryPipelineConfiguration('$stageName', '$stageName')
    logRotator(-1, 10, -1, -1)
    steps {
      shell(
        '''
        sleep \$(( ( RANDOM % 10 )  + 1 ))
        '''
      )
    }
    publishers {
      downstreamParameterized {
        configure { node ->
          downstreamJobs.each() { name ->
            node << {
              trigger(name) {
                delegate.condition('SUCCESS')
                delegate.parameters {
                  currentBuild()
                }
              }
            }
          }
        }
      }
    }
  }
  """.stripIndent())
}

[
        [name: "happy-service-build", stage: "Commit stage", downstreams: ["happy-service-test"]],
        [name: "happy-service-test", stage: "Test", downstreams: ["happy-service-deploy"]],
        [name: "happy-service-deploy", stage: "Deploy"],
        [name: "sad-service-build", stage: "Commit stage", downstreams: ["sad-service-test"]],
        [name: "sad-service-test", stage: "Test", downstreams: ["sad-service-deploy"]],
        [name: "sad-service-deploy", stage: "Deploy"]
].each { Map config ->
    createJob(this as DslFactory, config.name, config.stage, config.downstreams)
}
