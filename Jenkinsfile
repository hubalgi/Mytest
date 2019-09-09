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

ssh root@172.31.0.193 \'sudo sh /root/scripts/runtestsuite.sh\'
'''
      }
    }
    stage('run regression test suite') {
      steps {
         catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
        sh ''' 'sudo /opt/SmartBear/SoapUI-5.5.0/bin/testrunner.sh -h "`cat /var/lib/jenkins/abc.txt`" -r -a -j -f /var/lib/jenkins/jobs/Mytest/branches/master/reports /root/soaptests/REST-Project-1-readyapi-project.xml'
     '''
         echo "Post-Build currentResult: ${currentBuild.currentResult}"
	script {env.prev_stage_outcome = "SUCCESS"}
         }
    }
    }
    stage('PostDeploymentCheck') {
      parallel {
        stage ('Rollbacking the deployment') {
        when {
            expression { env.prev_stage_outcome == "FAILURE" }
        
      steps {
        sh 'ssh root@172.31.0.193 \'kubectl delete -f /root/k8s-ymls/ms-deployment-service-test.yaml || true\''
      }
    }
    stage('Deployment into prod') {
      when {
            expression { env.prev_stage_outcome == "SUCCESS" }
      
	  }
      steps {
        sh 'ssh root@172.31.0.193 \'kubectl create -f /root/k8s-ymls/ms-deployment-service-test.yaml\''
      }
    }
  }
  parameters {
    string(name: 'prev_stage_outcome', defaultValue: 'FAILURE')
  }
}
