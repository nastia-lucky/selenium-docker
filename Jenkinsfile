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
             sh "docker build -t=nastialucky/selenium:latest ."
           }
        }

        stage('Push image '){
        environment{
        DOCKER_HUB=credentials('dockerhub')
        }
           steps{
           sh  'echo ${DOCKER_HUB_PSW} | docker login -u ${DOCKER_HUB_USR} --password-stdin'
           sh "docker push nastialucky/selenium:latest"
           sh "docker tag nastialucky/selenium:latest nastialucky/selenium:${env.BUILD_NUMBER}"
           sh "docker push nastialucky/selenium:${env.BUILD_NUMBER}"
           }
        }
    }

    post {
    always {
    sh "docker logout"
    }
    }
}