import javaposse.jobdsl.dsl.DslFactory

new GroovyShell().parse(new File("jobs.groovy")).with {
    [
            [name: "happy-service-build", stage: "Commit stage", downstreams: ["happy-service-test"]],
            [name: "happy-service-test", stage: "Test", downstreams: ["happy-service-deploy"]],
            [name: "happy-service-deploy", stage: "Deploy",
            [name: "sad-service-build", stage: "Commit stage", downstreams: ["sad-service-test"]],
            [name: "sad-service-test", stage: "Test", downstreams: ["sad-service-deploy"]],
            [name: "sad-service-deploy", stage: "Deploy"]
    ].each { Map config ->
        createJob(this as DslFactory, config.name, config.stage, config.downstreams)
    }
}
