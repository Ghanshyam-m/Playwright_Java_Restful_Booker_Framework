pipeline {
    agent any

    tools {
        jdk 'JDK21'          // Change to your configured JDK name
        maven 'MAVEN'       // Change to your configured Maven name
    }

    options {
        timestamps()
        disableConcurrentBuilds()
        buildDiscarder(logRotator(
                numToKeepStr: '20',
                artifactNumToKeepStr: '20'
        ))
    }

    environment {
        MAVEN_OPTS = '-Xmx1024m'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Verify Java & Maven') {
            steps {
                bat 'java -version'
                bat 'mvn -version'
            }
        }

        stage('Clean Project') {
            steps {
                bat 'mvn clean'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Package') {
            steps {
                bat 'mvn package -DskipTests'
            }
        }

        stage('Archive Build Artifact') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar',
                        fingerprint: true
            }
        }
    }

    post {

        always {

            echo 'Publishing Test Results...'

            junit(
                testResults: 'target/surefire-reports/*.xml',
                allowEmptyResults: true
            )

            allure(
                includeProperties: false,
                jdk: '',
                results: [[path: 'allure-results']]
            )

            archiveArtifacts(
                artifacts: 'allure-report.zip',
                allowEmptyArchive: true
            )

            archiveArtifacts(
                artifacts: 'allure-summary.json',
                allowEmptyArchive: true
            )

            archiveArtifacts(
                artifacts: 'playwright-report/**/*',
                allowEmptyArchive: true
            )

            archiveArtifacts(
                artifacts: 'allure-results/**/*',
                allowEmptyArchive: true
            )

            archiveArtifacts(
                artifacts: 'test-results/**/*',
                allowEmptyArchive: true
            )

            archiveArtifacts(
                artifacts: 'screenshots/**/*',
                allowEmptyArchive: true
            )

            archiveArtifacts(
                artifacts: 'videos/**/*',
                allowEmptyArchive: true
            )

            archiveArtifacts(
                artifacts: 'traces/**/*',
                allowEmptyArchive: true
            )

            archiveArtifacts(
                artifacts: 'logs/**/*',
                allowEmptyArchive: true
            )

            cleanWs()
        }

        success {
            echo 'Build completed successfully.'
        }

        unstable {
            echo 'Build completed with unstable test results.'
        }

        failure {
            echo 'Build failed.'
        }

        aborted {
            echo 'Build was aborted.'
        }
    }
}