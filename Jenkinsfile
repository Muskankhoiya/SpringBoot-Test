pipeline {
    agent any

    environment {
        JAVA_HOME = 'C:\\.Net & Java Software\\Open JKD -11' // Update this path to your JDK 11 installation path
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat """
                set PATH=%PATH%;%JAVA_HOME%\\bin
                mvn clean package
                """
            }

            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }

        stage('Test') {
            steps {
                bat """
                set PATH=%PATH%;%JAVA_HOME%\\bin
                mvn test
                """
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
