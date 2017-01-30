import javaposse.jobdsl.dsl.DslFactory

new GroovyShell().parse(new File("jobs.groovy")).with {
    [
            [name: "happy-service-build", stage: "Commit stage", downstreams: ["happy-service-test"]],
            [name: "happy-service-test", stage: "Test", downstreams: ["happy-service-test"]],
            [name: "happy-service-deploy", stage: "Deploy", downstreams: ["happy-service-test"]],
            [name: "sad-service-build", stage: "Commit stage", downstreams: ["happy-service-test"]],
            [name: "sad-service-test", stage: "Test", downstreams: ["happy-service-test"]],
            [name: "sad-service-deploy", stage: "Deploy", downstreams: ["happy-service-test"]]
    ].each { Map config ->
        createJob(this as DslFactory, config.name, config.stage, config.downstreams)
    }
}
