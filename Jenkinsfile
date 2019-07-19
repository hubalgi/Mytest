pipeline {
  agent any
  stages {
    stage('checkout and build code') {
      steps {
        sh 'mvn clean install sonar:sonar'
      }
    }
    stage('copy the artifact to docker space') {
      steps {
        sh 'sudo cp /var/lib/jenkins/jobs/Mytest/branches/master/workspace/target/student-services-0.0.1-SNAPSHOT.jar /root/docker-space/'
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
        sh '''whoami

bash sudo -i
kubectl create -f /root/k8s-ymls/nginx-deployment-service.yaml'''
      }
    }
  }
}