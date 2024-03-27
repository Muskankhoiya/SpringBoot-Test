pipeline {
    agent any
    tools {
        jdk 'JDK 11' // Use the name you provided for the JDK in Jenkins Global Tool Configuration
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                bat 'mvnw.cmd clean package'
            }
        }
        stage('Test') {
            steps {
                bat 'mvnw.cmd test'
            }
        }
        stage('Deploy') {
            steps {
                bat 'mvnw.cmd deploy'
            }
        }
    }
}
