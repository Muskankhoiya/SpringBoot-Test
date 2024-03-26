pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean package -Dmaven.compiler.source=1.8 -Dmaven.compiler.target=1.8'
            }

            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test -Dmaven.compiler.source=1.8 -Dmaven.compiler.target=1.8'
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
            // Add your deployment steps here
        }

        failure {
            echo 'Build or test failed! Check the Jenkins console output for more details.'
        }
    }
}
