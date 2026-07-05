pipeline {

    agent any

    tools {
        jdk 'JDK21'
        maven 'Maven3'
    }

    parameters {

        choice(
            name: 'BROWSER',
            choices: ['chromium','firefox','webkit'],
            description: 'Browser'
        )

        choice(
            name: 'ENV',
            choices: ['qa','stage','prod'],
            description: 'Environment'
        )

        booleanParam(
            name: 'HEADLESS',
            defaultValue: true,
            description: 'Headless Mode'
        )

    }

    ...
}