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
        sh ' sudo cp /var/lib/jenkins/workspace/myfirstmaven/demo/target/student-services-0.0.1-SNAPSHOT.jar /root/docker-space/ '
      }
    }
    stage('build image') {
      steps {
        sh 'whoami'
        sh '''
                    echo "Multiline shell steps works toxob"
                    pwd
                    cd 
                    pwd
                    sudo -i
                '''
        sh 'sudo sh startplaybook.sh'
      }
    }
  }
}