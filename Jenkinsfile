pipeline {
  agent any
  stages {
    stage('Build code') {
      steps {
        sh 'cd  demo'
        sh 'mvn clean install'
      }
    }
  }
}