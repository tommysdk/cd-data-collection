deliveryPipelineView("pipelines") {
    pipelineInstances(3)
    showAggregatedPipeline(false)
    columns(1)
    updateInterval(2)
    enableManualTriggers(true)
    showAvatars(false)
    showChangeLog(true)
    configure { node ->
        node << {
            theme('contrast')
            allowRebuild('true')
            allowPipelineStart('true')
        }
    }
    pipelines {
        component("happy-service", "happy-service-build"),
				component("sad-service", "sad-service-build")
    }
}
