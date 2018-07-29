pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'mvn clean package'
      }
    }
    stage('Cloud Foundry') {
      parallel {
        stage('Cloud Foundry') {
          steps {
            pushToCloudFoundry(target: 'https://api.run.pivotal.io', organization: 'YetAnotherTechGuy', cloudSpace: 'development', credentialsId: '6e3719ab-a9ae-4c90-b26b-33356b5c7671', pluginTimeout: '240')
          }
        }
        stage('code-coverage') {
          steps {
            build(job: 'code-coverage', quietPeriod: 12, wait: true)
          }
        }
        stage('checkstyle') {
          steps {
            checkstyle()
          }
        }
      }
    }
  }
  tools {
    maven 'localMaven'
  }
  triggers {
    pollSCM('* * * * *')
  }
}