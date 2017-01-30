
def createJob(final def dslFactory,
              final String projectName,
              final String stageName
              final List<String> downstreamJobs) {
    dslFactory.job("$projectName-$stageName") {
        description "$projectName / $stageName"
        parameters {
            stringParam('VERSION', null, null)
        }
        deliveryPipelineConfiguration("$stageName", "$stageName")
        logRotator(-1, 10, -1, -1)
        steps {
            shell(
              """
              sleep $(( ( RANDOM % 10 )  + 1 ))
              """)
            }
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
}
