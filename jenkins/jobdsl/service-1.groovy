pipelineJob('service-1') {
  triggers {
    cron("* * * * *")
  }
  definition {
    cps {
        script("""\
          node('master') {
            stage('build') {
              sh '''
              #!/bin/bash
              sleep `echo -n \$(( \$(date +%s) % 10 ))`
              '''
              sh 'echo built'
            }
            stage('test') {
              sh '''
              #!/bin/bash
              sleep `echo -n \$(( \$(date +%s) % 10 ))`
              '''
              sh 'echo test'
            }
            stage('deploy') {
              sh '''
              #!/bin/bash
              sleep `echo -n \$(( \$(date +%s) % 10 ))`
              '''
              sh 'echo deploy'
            }
          }
        """.stripIndent())
        sandbox()
    }
  }
}
