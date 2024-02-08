pipeline {
    agent any
    environment {
        DOCKER = 'sudo docker'
        // Dockerfile이 위치한 경로 
        DOCKERFILE_PATH = './Dockerfile'
        // 생성할 Docker 이미지 이름 - BE,FE
        DOCKER_IMAGE_NAME_BE = 'longd-back-image'
        DOCKER_IMAGE_NAME_FE = 'longd-front-image'
        // 생성할 Docker 컨테이너 이름 - BE,FE
        DOCKER_CONTAINER_NAME_BE = 'longd-backend'
        DOCKER_CONTAINER_NAME_FE = 'longd-frontend'


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
                    success { sh 'echo "Successfully Cloned Repository"'}
                    failure { sh 'echo "Fail Cloned Repository"'}
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
                echo 'Build image...'
            }
        }

//jenkins 컨테이너 내에 /home/nginx 폴더로 복사하기 위함.
        stage('Copy nginx conf to Jenkins Container') {
            steps {

                sh 'docker cp /home/ubuntu/jenkins-data/nginx jenkins:/var/jenkins_home/home/'
            }
        }

//          stage('ADD NginX conf to FE'){
//              steps {
//                  dir("${DIRECTORY_NAME2}"){
//                       sh "ls"
//                       sh "cp -r /home/ubuntu/nginx /var/jenkins_home/workspace/LongD-develop/longd-fe/"
// //                       sh "docker build -t ${DOCKER_IMAGE_NAME_FE} -f ${PROJECT_PATH}/longd-fe/Dockerfile ${PROJECT_PATH}/longd-fe"
//
//                  }
//              }
//          }

         stage('Build FE image'){
             steps {
                 dir("${DIRECTORY_NAME2}"){
                      sh "ls"
                      sh "docker build -t ${DOCKER_IMAGE_NAME_FE} -f ${PROJECT_PATH}/longd-fe/Dockerfile ${PROJECT_PATH}/longd-fe"

                 }
             }
         }



        //BE - 이전 컨테이너 삭제
        stage('Remove Previous BE Container') {
            steps {
                script {
                    try {
                        sh "docker stop ${DOCKER_CONTAINER_NAME_BE}"
                        sh "docker rm ${DOCKER_CONTAINER_NAME_BE}"
                    } catch (e) {
                        echo 'fail to stop and remove container'
                    }
                }
            }
        }

        //FE - 이전 컨테이너 삭제
        stage('Remove Previous FE Container') {
            steps {
                script {
                    try {
                        sh "docker stop ${DOCKER_CONTAINER_NAME_FE}"
                        sh "docker rm ${DOCKER_CONTAINER_NAME_FE}"
                    } catch (e) {
                        echo 'fail to stop and remove container'
                    }
                }
            }
        }
      //새 BE 컨테이너 실행
        stage('Run New BE image') {
            steps {
                sh "docker run --name ${DOCKER_CONTAINER_NAME_BE} -d -p 3000:3000 ${DOCKER_IMAGE_NAME_BE}"
                echo 'Run New BE image'
            }
        }

      //새 FE 컨테이너 실행
        stage('Run New FE image') {
            steps {
                //컨테이너의 모든 디렉터리 home/ubuntu/nginx에 볼륨 마운트
                sh "docker run --name ${DOCKER_CONTAINER_NAME_FE} -d -p 3001:3001 ${DOCKER_IMAGE_NAME_FE} -v /home/ubuntu/nginx:/usr longd-frontend"
                // sh "docker cp /home/ubuntu/nginx longd-frontend:/usr/share/nginx"
                echo 'Run New FE image'
            }
        }
    }
}
