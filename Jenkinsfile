pipeline {
    agent any
    stages {
        stage('Clean') {
            steps {
                script {
                        bat  "./mvnw.cmd clean"           
                }
            }
        }

        stage('Compile') {
            steps {
                script {
                        bat  "./mvnw.cmd compile -e"           
                }
            }
        }

        stage('Test') {
            steps {
                 script {
                    bat  "./mvnw.cmd test"           
                }
            }
        }
    }
}