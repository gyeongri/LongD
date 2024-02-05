pipeline {
    agent any
    environment {
        DOCKER = 'sudo docker'

        // Dockerfile이 위치한 경로
        DOCKERFILE_PATH = './Dockerfile'

        // 생성할 Docker 이미지 이름 - BE
        DOCKER_IMAGE_NAME_BE = 'longd-back-image'

        // 생성할 Docker 이미지 이름 - FE
        DOCKER_IMAGE_NAME_FE = 'longd-front-image'

        //BE 디렉터리명
      DIRECTORY_NAME1 = 'longD-BE'

      //FE 디렉터리명
        DIRECTORY_NAME2 = 'longd-fe'

        PROJECT_PATH = '/var/jenkins_home/workspace/LongD-develop'
       
       //현재 위치
       // currentDir = pwd()

    }

    stages {
        
   //레포지토리 클론받기            
        stage('Clone Repository') {
            steps {
                checkout scm
                echo 'Checkout Scm'
            }

            post {
                    success { 
                        sh 'echo "Successfully Cloned Repository"'
                    }
                    failure {
                        sh 'echo "Fail Cloned Repository"'
                    }
                }
        }
 
    //현재 디렉토리 위치 출력
        stage('Print Current Directory') {
            steps {
                script {
                    def currentDir = pwd()
                    echo "Current Directory: ${currentDir}"
                }
            }
        }        

        stage('Build BE image') {
            steps {
                sh 'ls -al'
                dir("${DIRECTORY_NAME1}") {
                    sh 'ls -al'
                    sh 'chmod +x ./gradlew'
                    sh './gradlew build'
                    sh "docker build -t ${DOCKER_IMAGE_NAME_BE} -f ${PROJECT_PATH}/longD-BE/Dockerfile ${PROJECT_PATH}/longD-BE"
                }
                echo 'Build BE image...'
            }

            steps {
                     dir("${DIRECTORY_NAME2}"){
                          sh "docker build -t ${DOCKER_IMAGE_NAME_FE} -f ${PROJECT_PATH}/longd-fe/Dockerfile ${PROJECT_PATH}/longd-fe"
                     }
                 }
                 echo 'Build FE image...'
        }


        //이전 이미지로 만든 컨테이너 삭제
        stage('Remove Previous BE Container') {
            steps {
                script {
                    try {
                        sh "docker stop ${DOCKER_IMAGE_NAME_BE}"
                        sh "docker rm ${DOCKER_IMAGE_NAME_BE}"
                    } catch (e) {
                        echo 'fail to stop and remove BE container'
                    }
                }
            }

            steps {
                script {
                    try {
                        sh "docker stop ${DOCKER_IMAGE_NAME_FE}"
                        sh "docker rm ${DOCKER_IMAGE_NAME_FE}"
                    } catch (e) {
                        echo 'fail to stop and remove FE container'
                    }
                }
            }
        }

      //새 컨테이너 실행
        stage('Run New BE image') {
            steps {
                sh "docker run --name ${DOCKER_IMAGE_NAME_BE} -d -p 3000:3000 ${DOCKER_IMAGE_NAME_BE}"
                echo 'Run New BE image'
            }
            steps {
                sh "docker run --name ${DOCKER_IMAGE_NAME_FE} -d -p 3001:3001 ${DOCKER_IMAGE_NAME_FE}"
                echo 'Run New FE image'
            }
        }

    }
}
