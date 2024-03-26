pipeline {
    agent any

    environment {
        JAVA_HOME = 'C:\\.Net & Java Software\\Open JKD -11'
        PATH = "${JAVA_HOME}\\bin;C:\\.Net & Java Software\\apache-maven-3.6.3-bin\\apache-maven-3.6.3\\bin;${env.PATH}"
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
