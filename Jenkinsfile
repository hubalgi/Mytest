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

sleep 5m'''
      }
    }
    stage('run regression test suite') {
      steps {
        catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
          sh '''ssh root@172.31.0.193 \'kubectl describe services ms|grep "LoadBalancer Ingress" | cut -d ":" -f 2| xargs >>abc.txt\'
echo "echo running test cases"
sudo /opt/SmartBear/SoapUI-5.5.0/bin/testrunner.sh -h "`cat abc.txt`"  -r -a -j -f /var/lib/jenkins/jobs/Mytest/branches/master/reports /root/soaptests/REST-Project-1-readyapi-project.xml'''
          echo "Post-Build currentResult: ${currentBuild.currentResult}"
          script {
            env.prev_stage_outcome = "SUCCESS"
          }

        }

      }
    }
    stage('cleanup existing prod deployments') {
      steps {
        sh 'ssh root@172.31.0.193 \'kubectl delete -f /root/k8s-ymls/ms-deployment-service-test.yaml || true\''
      }
    }
    stage('deploy in prod') {
      steps {
        sh 'ssh root@172.31.0.193 \'kubectl create -f /root/k8s-ymls/ms-deployment-service-test.yaml\''
      }
    }
  }
  parameters {
    string(name: 'prev_stage_outcome', defaultValue: 'FAILURE')
  }
}