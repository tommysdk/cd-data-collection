pipelineJob('service-1') {
  definition {
    cps {
        script("""\
          node('master') {
            stage('build') {
              sleep 3
              sh 'echo built'
            }
          }
        """.stripIndent())
        sandbox()
    }
  }
}
