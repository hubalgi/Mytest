pipeline {
  agent any
  stages {
    stage('Build code') {
      steps {
        sh '''cd Mytest/demo/
'''
        sh 'mvn clean install'
      }
    }
  }
}