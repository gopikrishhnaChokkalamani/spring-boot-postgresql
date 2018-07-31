pipeline {
  agent any
  tools {
    maven 'localMaven'
  }
  triggers {
    pollSCM('* * * * *')
  }
  stages {
    stage('Build') {
      steps {
        sh 'mvn clean package'
      }
    }
    stage('Test') {
      steps {
        sh 'mvn test'
      }
      post {
        always {
          archiveArtifacts artifacts: '**/*.jar', fingerprint: true
          junit 'target/surefire-reports/*.xml'
        }
      }
    }
    stage('Deploy & Report') {
      parallel {
        stage('deploy to cloud foundry') {
          steps {
            pushToCloudFoundry(target: 'https://api.run.pivotal.io', organization: 'BitShift', cloudSpace: 'development', credentialsId: '6e3719ab-a9ae-4c90-b26b-33356b5c7671', pluginTimeout: '240')
          }
        }
        stage('code coverage report') {
          steps {
            build(job: 'code-coverage', quietPeriod: 12)
          }
        }
        stage('checkstyle report') {
          steps {
            checkstyle()
          }
        }
      }
    }
    stage('Deploy : Production') {
      steps {
        input(message: 'Do you want to deploy to Production?', ok: 'Ok', submitter: 'john_wayne')
      }
      post {
        success {
          pushToCloudFoundry(cloudSpace: 'development', credentialsId: '5238d35a-9e8e-49ec-b03d-9213a3a401fc', organization: 'miruthika86-org', pluginTimeout: '240', target: 'https://api.run.pivotal.io')

        }
      }
    }
  }
}
