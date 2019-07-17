pipeline {
  agent any
  stages {
    stage('Build code') {
      parallel {
        stage('Build code') {
          steps {
            sh 'cd  demo'
          }
        }
        stage('') {
          steps {
            sh 'mvn clean install'
          }
        }
      }
    }
  }
}