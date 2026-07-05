pipeline {
    agent any

    tools {
        jdk 'JDK21'
        maven 'MAVEN'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                bat 'mvn clean verify -Dheadless=true'
            }
        }
    }

    post {
        always {
            junit '**/surefire-reports/*.xml'
        }
    }
}