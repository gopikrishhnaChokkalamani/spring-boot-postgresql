pipeline {
  agent any
  tools{
      maven 'localMaven'
    }
  triggers {
        pollSCM('* * * * *')
    }
  stages {
    stage('Build') {
      steps {
        sh 'mvn clean package'
        tool(name: 'localMaven', type: 'maven')
      }
    }
  }
}
