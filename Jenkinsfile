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
        sh 'sudo cp /var/lib/jenkins/workspace/myfirstmaven/demo/target/student-services-0.0.1-SNAPSHOT.jar /root/docker-space/ '
      }
    }
    stage('build a docker image from artifacr') {
      steps {
        sh 
       ' cd /root/docker-space '
      }
      steps {
        sh 
       ' pwd '
      }
        
  
      }
    }
  }

