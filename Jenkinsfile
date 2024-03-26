pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '', url: 'https://github.com/Muskankhoiya/SpringBoot-Test']]])
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
            post {
                success {
                    archiveArtifacts(artifacts: '**/target/*.jar', allowEmptyArchive: true)
                }
            }
        }

        stage('Deploy') {
            steps {
                // Add deployment steps here
            }
        }
    }
}
