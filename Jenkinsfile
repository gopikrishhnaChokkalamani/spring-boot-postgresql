pipeline {
  agent any
  stages {
    stage('Build') {
      parallel {
        stage('Build') {
          steps {
            sh 'mvn clean package'
            tool(name: 'localMaven', type: 'maven')
          }
        }
        stage('code coverage') {
          steps {
            jacoco(buildOverBuild: true)
          }
        }
        stage('checkstyle') {
          steps {
            checkstyle()
          }
        }
      }
    }
    stage('error') {
      steps {
        pushToCloudFoundry(target: 'https://api.run.pivotal.io', organization: 'YetAnotherTechGuy', cloudSpace: 'development', credentialsId: 'Username: gopikrishhna@gmail.com, Password: Slytherin3#')
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