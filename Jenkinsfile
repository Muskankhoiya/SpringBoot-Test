pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from the SCM
                checkout scm
            }
        }
        stage('Build') {
            steps {
                // Build the Spring Boot application
                bat 'mvnw.cmd clean package'
            }
        }
        stage('Test') {
            steps {
                // Run the tests
                bat 'mvnw.cmd test'
            }
        }
        stage('Deploy') {
            steps {
                // Deploy the application
                bat 'mvnw.cmd deploy'
            }
        }
    }
}
