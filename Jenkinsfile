pipeline {
  agent any
  stages {
    stage('checkout and build code and run code coverage') {
      steps {
        sh 'mvn clean install sonar:sonar'
      }
    }
    stage('copy the artifact to docker workspacneb') {
      steps {
        sh 'sudo cp /var/lib/jenkins/workspace/Mytest_master/target/student-services-0.0.1-SNAPSHOT.jar /root/docker-space/'
      }
    }
    stage('build image and push to private repo in docker hub') {
      steps {
        sh 'whoami'
        sh 'sudo sh /root/scripts/startplaybook.sh'
      }
    }
    stage('clean up existing deployments') {
      steps {
        sh 'ssh root@172.31.0.193 \'kubectl delete -f /root/k8s-ymls/ms-deployment-service.yaml || true\''
      }
    }
    stage('deploy in test') {
      steps {
        sh '''ssh root@172.31.0.193 \'kubectl create -f /root/k8s-ymls/ms-deployment-service.yaml\'

sleep 1m'''
      }
    }
    stage('run regression test suite') {
      steps {
        sh 'ssh root@172.31.0.193 \'/root/scripts/runtestsuite.sh\''
      }
    }
    stage('cleanup existing prod deployments') {
      steps {
        sh 'ssh root@172.31.0.193 \'kubectl delete -f /root/k8s-ymls/ms-deployment-service-test.yaml\''
      }
    }
    stage('deploy in prod') {
      steps {
        sh 'ssh root@172.31.0.193 \'kubectl create -f /root/k8s-ymls/ms-deployment-service-test.yaml\''
      }
    }
  }
}
