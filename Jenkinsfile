pipeline {
  agent any
  stages {
    stage('checkout and build code') {
      steps {
        sh 'mvn clean install'
      }
    }
    stage('copy the artifact to docker space') {
      parallel {
        stage('copy the artifact to docker space') {
          steps {
            sh 'sudo cp /var/lib/jenkins/jobs/Mytest/branches/master/workspace/target/student-services-0.0.1-SNAPSHOT.jar /root/docker-space/'
          }
        }
        stage('run sonar') {
          steps {
            sh '''cd /var/lib/jenkins/jobs/Mytest/branches/master/workspace 

mvn sonar:sonar   -Dsonar.host.url=http://13.235.123.183:9000   -Dsonar.login=68d01057ee15c9e15f1a3e811e9f229fb4e64303'''
          }
        }
        stage('run coverage') {
          steps {
            sh 'mvn clean install sonar:sonar'
          }
        }
      }
    }
    stage('build image and push to hub') {
      steps {
        sh 'whoami'
        sh 'sudo sh /root/scripts/startplaybook.sh'
      }
    }
    stage('Deploy into dev') {
      steps {
        sh 'kubectl'
      }
    }
  }
}