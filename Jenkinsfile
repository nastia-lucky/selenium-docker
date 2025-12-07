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
        environment{
        DOCKER_HUB=credentials('dockerhub')
        }
           steps{
           sh  'docker login -u ${DOCKER_HUB_USR} -p ${DOCKER_HUB_PSW}'
            sh "docker push nastialucky/selenium""
           }
        }
    }

    post {
    always {
    sh "docker logout"
    }
    }
}