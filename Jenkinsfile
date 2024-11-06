pipeline{

    agent any

    stages {

    stage('Build Jar'){
        steps{
        bat "mvn clean package -DskipTests"
        }
    }

    stage('Build Image') {
        steps{
        bat "docker build -t=adityajha1/selenium:latest ."
        }
    }

     stage('Push Image') {
     environment {
            DOCKER_HUB = credentials('dockerhub-creds')
     }
        steps{
        bat 'docker login -u %DOCKER_HUB_USR% -p %DOCKER_HUB_PSW%'
        bat "docker push adityajha1/selenium:latest"
        bat "docker tag adityajha1/selenium:latest adityajha1/selenium:${env.BUILD_NUMBER}"
        bat "docker push adityajha1/selenium:${env.BUILD_NUMBER}"
        }
    }
    }
    post {
        always {
        bat "docker logout"
        }
    }
    }