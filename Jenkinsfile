pipeline {
  agent any
  stages {
    stage('checkout and build code') {
      steps {
        sh 'mvn clean install'
      }
    }
    stage('copy the artifact to docker space') {
      steps {
        sh 'whoami'
      }
    }
    stage('build image and push to hub') {
      steps {
        sh 'whoami'
        sh 'sh /var/lib/jenkins/scripts/sample.sh'
      }
    }
    stage('Deploy into dev') {
      steps {
        sh 'kubectl'
      }
    }
  }
}