pipeline {

    agent any

    tools {
        jdk 'JDK21'
        maven 'MAVEN'
    }

    parameters {

        choice(
            name: 'BROWSER',
            choices: ['chromium', 'firefox', 'webkit'],
            description: 'Select browser'
        )

        choice(
            name: 'ENV',
            choices: ['qa', 'stage', 'prod'],
            description: 'Select environment'
        )

        booleanParam(
            name: 'HEADLESS',
            defaultValue: true,
            description: 'Run in headless mode'
        )
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                bat """
                mvn clean verify ^
                -Dbrowser=%BROWSER% ^
                -Denv=%ENV% ^
                -Dheadless=%HEADLESS%
                """
            }
        }
    }

    post {

        always {

            junit 'target/surefire-reports/*.xml'

            allure(
                includeProperties: false,
                jdk: '',
                results: [[path: 'target/allure-results']]
            )

            archiveArtifacts artifacts: 'target/**/*.jar', fingerprint: true

            cleanWs()
        }

        success {
            echo 'Build completed successfully.'
        }

        failure {
            echo 'Build failed.'
        }
    }
}