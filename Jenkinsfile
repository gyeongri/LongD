pipeline {
    agent any
    environment {
        DOCKER = 'sudo docker'
        // Dockerfile이 위치한 경로 
        DOCKERFILE_PATH = './Dockerfile'

        MAIN_IMAGE_BE = 'longd-back-image'// 생성할 Docker 이미지 이름 - BE,FE
        MAIN_CONTAINER_BE = 'longd-backend'// 생성할 Docker 컨테이너 이름 - BE,FE

        MAIN_IMAGE_FE = 'longd-front-image'
        MAIN_CONTAINER_FE = 'longd-frontend'

        CHAT_IMAGE_BE = 'longd-chat-image'
        CHAT_CONTAINER_BE = 'longd-chat'

        OPENVIDU_IMAGE_BE = 'longd-openvidu-image'
        OPENVIDU_CONTAINER_BE = 'longd-openvidu'



        DIRECTORY_BE = 'longD-BE' //BE 디렉터리명
        DIRECTORY_FE = 'longd-fe' //FE 디렉터리명
        DIRECTORY_CHAT = 'longD-chat' //CHAT 디렉터리명
        DIRECTORY_OPENVIDU = 'longd-openvidu-gradle' //OPENVIDU 디렉터리명


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


/////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////
//         stage('Build main openvidu image') {
//             steps {
//                 sh 'ls -al'
//                 dir("${DIRECTORY_OPENVIDU}") {
//                     sh 'ls -al'
//                     sh 'chmod +x ./gradlew'
//                     sh './gradlew build'
//                     sh "docker build -t ${OPENVIDU_IMAGE_BE} -f ${PROJECT_PATH}/longd-openvidu-gradle/Dockerfile ${PROJECT_PATH}/longd-openvidu"
//                 }
//                 echo 'Build openvidu image...'
//             }
//         }


//         //BE - 이전 컨테이너 삭제
//         stage('Remove Previous openvidu BE Container') {
//             steps {
//                 script {
//                     try {
//                         sh "docker stop ${OPENVIDU_CONTAINER_BE}"
//                         sh "docker rm ${OPENVIDU_CONTAINER_BE}"
//                     } catch (e) {
//                         echo 'fail to stop and remove openvidu container'
//                     }
//                 }
//             }
//         }

//       //새 BE 컨테이너 실행
//         stage('Run New main openvidu image') {
//             steps {
//                 sh "docker run --name ${OPENVIDU_CONTAINER_BE} -d -p 3003:3003 ${OPENVIDU_IMAGE_BE}"
//                 echo 'Run New openvidu BE image'
//             }
//         }

/////////////////////////////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////////////
        stage('Build main BE image') {
            steps {
                sh 'ls -al'
                dir("${DIRECTORY_BE}") {
                    sh 'ls -al'
                    sh 'chmod +x ./gradlew'
                    sh './gradlew build'
                    sh "docker build -t ${MAIN_IMAGE_BE} -f ${PROJECT_PATH}/longD-BE/Dockerfile ${PROJECT_PATH}/longD-BE"
                }
                echo 'Build main image...'
            }
        }


        //BE - 이전 컨테이너 삭제
        stage('Remove Previous main BE Container') {
            steps {
                script {
                    try {
                        sh "docker stop ${MAIN_CONTAINER_BE}"
                        sh "docker rm ${MAIN_CONTAINER_BE}"
                    } catch (e) {
                        echo 'fail to stop and remove main container'
                    }
                }
            }
        }

      //새 BE 컨테이너 실행
        stage('Run New main BE image') {
            steps {
                sh "docker run --name ${MAIN_CONTAINER_BE} -d -p 3000:3000 ${MAIN_IMAGE_BE}"
                echo 'Run New main BE image'
            }
        }

/////////////////////////////////////////////////////////////////////////////////////////////////////////
        stage('Build BE-chat image') {
            steps {
                sh 'ls -al'
                dir("${DIRECTORY_CHAT}") {

                    script {
                        def currentDir = pwd()
                        echo "Current Directory: ${currentDir}"
                    }


                    sh 'ls -al'
                    sh 'chmod +x ./gradlew'
                    sh './gradlew build'
                    sh "docker build -t ${CHAT_IMAGE_BE} -f ${PROJECT_PATH}/longD-chat/Dockerfile ${PROJECT_PATH}/longD-chat"
                }
                echo 'Build chat image...'
            }
        }


        //BE - 이전 컨테이너 삭제
        stage('Remove Previous BE-chat Container') {
            steps {
                script {
                    try {
                        sh "docker stop ${CHAT_CONTAINER_BE}"
                        sh "docker rm ${CHAT_CONTAINER_BE}"
                    } catch (e) {
                        echo 'fail to stop and remove chat container'
                    }
                }
            }
        }

      //새 BE 컨테이너 실행
        stage('Run New BE-chat image') {
            steps {
                sh "docker run --name ${CHAT_CONTAINER_BE} -d -p 5000:5000 ${CHAT_IMAGE_BE}"
                echo 'Run New BE chat image'
            }
        }

        stage('Build FE image'){
            steps {
                dir("${DIRECTORY_FE}"){
                    sh "ls"
                    sh "docker build -t ${MAIN_IMAGE_FE} -f ${PROJECT_PATH}/longd-fe/Dockerfile ${PROJECT_PATH}/longd-fe"

                }
            }
        }


        //FE - 이전 컨테이너 삭제
        stage('Remove Previous FE Container') {
            steps {
                script {
                    try {
                        sh "docker stop ${MAIN_CONTAINER_FE}"
                        sh "docker rm ${MAIN_CONTAINER_FE}"
                    } catch (e) {
                        echo 'fail to stop and remove container'
                    }
                }
            }
        }


      //새 FE 컨테이너 실행
        stage('Run New FE image') {
            steps {
                //컨테이너의 모든 디렉터리 home/ubuntu/nginx에 볼륨 마운트
                sh "docker run --name ${MAIN_CONTAINER_FE} -d -p 3001:5173 ${MAIN_IMAGE_FE}"
                // sh "docker cp /home/ubuntu/nginx longd-frontend:/usr/share/nginx"
                echo 'Run New FE image'
            }
        }
//////////////////////////////////////////////////////////////////////////////////////////





    }
}
