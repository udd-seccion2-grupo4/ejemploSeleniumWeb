pipeline {
    agent any
    stages {
        stage('Clean') {
            steps {
                script {
                        sh  "./mvnw clean"           
                }
            }
        }

        stage('Compile') {
            steps {
                script {
                        sh  "./mvnw compile -e"           
                }
            }
        }

        stage('Test') {
            steps {
                 script {
                    sh  "./mvnw test"           
                }
            }
        }
    }
}