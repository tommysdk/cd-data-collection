pipelineJob('service-1') {
  triggers {
    cron("* * * * *")
  }
  definition {
    cps {
        script("""\
          node('master') {
            def startTime = System.currentTimeMillis()
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
              def totalTime = System.currentTimeMillis() - startTime
              sh \"\"\"
                cat <<EOF | curl --data-binary @- http://pushgateway:9091/metrics/job/builds/instance/service-1
# TYPE pipeline_execution_time gauge
pipeline_execution_time \${totalTime}
EOF
             \"\"\"
            }
          }
        """.stripIndent())
        sandbox()
    }
  }
}
