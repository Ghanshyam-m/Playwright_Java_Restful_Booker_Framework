pipeline {

    agent any

    tools {
        jdk 'JDK21'
        maven 'Maven3'
    }

    options {
        timeout(time: 30, unit: 'MINUTES')

        buildDiscarder(logRotator(
                numToKeepStr: '20',
                artifactNumToKeepStr: '10'
        ))

        timestamps()
    }

    parameters {

        choice(
            name: 'BROWSER',
            choices: ['chromium', 'firefox', 'webkit'],
            description: 'Select Browser'
        )

        choice(
            name: 'ENV',
            choices: ['qa', 'stage', 'prod'],
            description: 'Select Environment'
        )

        choice(
            name: 'SUITE',
            choices: ['UI', 'API'],
            description: 'Select Test Suite'
        )

        booleanParam(
            name: 'HEADLESS',
            defaultValue: true,
            description: 'Run browser in headless mode'
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

            junit '**/surefire-reports/*.xml'

        }

    }

}