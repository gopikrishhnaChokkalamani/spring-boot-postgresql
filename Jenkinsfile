pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'mvn clean package'
        tool(name: 'localMaven', type: 'maven')
      }
    }
    stage('code analysis') {
      parallel {
        stage('checkstyle report') {
          steps {
            checkstyle()
          }
        }
        stage('code coverage') {
          steps {
            build(job: 'jacoco-coverage-test', quietPeriod: 30)
          }
        }
      }
    }
    stage('Push to PCF') {
      steps {
        pushToCloudFoundry(target: 'https://api.run.pivotal.io', organization: 'YetAnotherTechGuy', cloudSpace: 'development', credentialsId: '6e3719ab-a9ae-4c90-b26b-33356b5c7671')
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