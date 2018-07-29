pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'mvn clean package'
        tool(name: 'localMaven', type: 'maven')
      }
    }
  }
}