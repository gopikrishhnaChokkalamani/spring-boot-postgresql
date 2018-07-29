pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'mvn clean package'
      }
    }
  }
  environment {
    tools = 'maven \'localMaven\''
  }
}