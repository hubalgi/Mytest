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
    stage('clear up existing deployments') {
      steps {
        sh 'ssh root@172.31.0.13 \'kubectl delete -f /root/k8s-ymls/ms-deployment-service.yaml || true\''
      }
    }
    stage('deploy into dev') {
      steps {
        sh 'ssh root@172.31.0.13 \'kubectl create -f /root/k8s-ymls/ms-deployment-service.yaml\''
      }
    }
    stage('test') {
      steps {
        sh 'sudo /root/scripts/runtestsuite.sh'
      }
    }
  }
}