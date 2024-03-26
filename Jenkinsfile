pipeline {
    agent any

    tools {
        jdk 'jdk 17'
        maven 'maven 3.8.1'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean package'
            }

            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }

            post {
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }

        stage('Deploy') {
            steps {
                bat 'echo "Deploying the application..."'
            }
        }
    }

    post {
        success {
            echo 'Build and test succeeded! Deploying the application...'
        }

        failure {
            echo 'Build or test failed! Check the Jenkins console output for more details.'
        }
    }
}
