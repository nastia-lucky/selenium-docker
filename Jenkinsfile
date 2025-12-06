pipeline {

    agent any

    stages {
        stage('Build jar'){
           steps{
             sh "mvn clean package -DskipTests"
           }
        }

         stage('Build image'){
           steps{
             sh "docker build -t=nastialucky/selenium ."
           }
        }

        stage('Push image '){
           steps{
            sh "docker push nastialucky/selenium""
           }
        }
    }
}