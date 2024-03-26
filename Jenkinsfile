pipeline {
    agent any
    tools {
        jdk 'OpenJDK 11'
        maven 'maven'
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                bat script: 'mvnw.cmd clean package', tool: 'Maven'
            }
        }
        stage('Test') {
            steps {
                bat script: 'mvnw.cmd test', tool: 'Maven'
            }
        }
        stage('Deploy') {
            steps {
                bat script: 'mvnw.cmd deploy', tool: 'Maven'
            }
        }
    }
}
