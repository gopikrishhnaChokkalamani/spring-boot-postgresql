pipeline {
  agent any
  tools {
    maven 'localMaven'
  }
  triggers {
    pollSCM('* * * * *')
  }
  stages {
    stage('Build & Package') {
      parallel {
        stage('Build & Package') {
          steps {
            sh 'mvn clean package'
          }
        }
        stage('Test') {
          steps {
            sh 'mvn test'
          }
        }
      }
    }
    stage('Deploy & Report') {
      parallel {
        stage('Deploy to Cloud Foundry') {
          steps {
            pushToCloudFoundry(target: 'https://api.run.pivotal.io', organization: 'BitShift', cloudSpace: 'development', credentialsId: '6e3719ab-a9ae-4c90-b26b-33356b5c7671', pluginTimeout: '240')
          }
        }
        stage('Code Coverage Report') {
          steps {
            build(job: 'code-coverage', quietPeriod: 12)
          }
        }
        stage('Checkstyle Report') {
          steps {
            checkstyle()
          }
        }
      }
    }
    stage('Approval') {
      post {
        success {
          pushToCloudFoundry(cloudSpace: 'development', credentialsId: '5238d35a-9e8e-49ec-b03d-9213a3a401fc', organization: 'miruthika86-org', pluginTimeout: '240', target: 'https://api.run.pivotal.io')

        }

      }
      steps {
        input(message: 'Deploy to Production', ok: 'Ok')
      }
    }
  }
  post {
        always {
            archiveArtifacts artifacts: 'build/libs/**/*.jar', fingerprint: true
            junit 'build/reports/**/*.xml'
        }
    }
}
