pipeline {
  agent any
  stages {
    stage('Build code') {
      steps {
        sh '''cd /var/lib/jenkins/jobs/Mytest/branches/master/workspace/demo/
'''
      }
    }
    stage('call maven') {
      steps {
        sh 'mvn clean install'
      }
    }
  }
}