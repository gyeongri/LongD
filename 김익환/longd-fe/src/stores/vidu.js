import { ref, computed } from 'vue';
import { defineStore } from 'pinia';
import { OpenVidu } from 'openvidu-browser';
import { viduapi } from '@/api/index.js';
export const useViduStore = defineStore('vidu', () => {
  const OV = ref(null);
  const session = ref(null);
  const sessionName = ref('aa');
  const token = ref(null);
  const forceRecordingId = ref(null);
  const publisher = ref(null);
  const subscriber = ref(null);
  const hasSubscriber = computed(() => !!subscriber.value);
  //세션들어가기
  const joinSession = function (coupleid) {
    console.log();
    //토큰부터받아오고
    getToken(coupleid).then(token => {

      //openvidu 객체 생성
      OV.value = new OpenVidu();
      console.log('1. openvidu 객체 생성 완료');

      //세션 생성
      session.value = OV.value.initSession();
      console.log('2. OV객체 초기화 후 session 변수에 할당', session.value);



      //새로운 스트림 생겼을 때 다른 참가자가 오디오,비디오 전송시작할 때
      session.value.on('streamCreated', event => {
        
        //캠 화면이 video-container에 추가됨
        subscriber.value = session.value.subscribe(
          event.stream,
          'videoElement',
        );
        // var subscriber = session.value.subscribe(event.stream, 'videoElement');
        console.log('subscriber의 캠화면을 video-container에 추가하기');

        //세션에 비디오 엘리먼트가 생겼을 때
        subscriber.value.on('videoElementCreated', event => {
          console.log('비디오 엘리먼트 생성');
          // updateNumVideos(1);
        });
        //세션에 비디오 엘리먼트 사라졌을때
        subscriber.value.on('videoElementDestroyed', event => {
          // updateNumVideos(-1);
        });
      });


      //연결이 끝났을 떄
      session.value.on('sessionDisconnected', event => {
        if (event.reason !== 'disconnect') {
          removeUser();
        }
        if (event.reason !== 'sessionClosedByServer') {
          session.value = null;
          // numVideos.value = 0;
          //서버에의해 종료됐을때
          document.getElementById('join').style.display = 'block';
          document.getElementById('session').style.display = 'none';
        }
      });

      session.value.on('exception', console.warn);

      //검색된 토큰과 클라이언트의 추가 데이터를 전달하여 세션에 연결하기
      session.value
        .connect(token)
        .then(() => {
          // 활성 통화를 위한 페이지 레이아웃 설정
          // document.getElementById('session-title').textContent =
          //   sessionName.value;
          // document.getElementById('join').style.display = 'none';
          // document.getElementById('session').style.display = 'block';


          // 자신의 카메라 스트림 가져오기
          publisher.value = OV.value.initPublisher('videoElement', {
            audioSource: undefined,
            videoSource: undefined,
            publishAudio: true,
            publishVideo: true,
            resolution: '640x480',
            frameRate: 30,
            insertMode: 'APPEND',
            mirror: false,
          });
          console.log('6-1. publisher 설정 : ' + publisher.value);

          // 스트림이 만들어졌을떄
          publisher.value.on('streamCreated', () => {
            console.log('스트림 생성 : ', session.value.sessionId);
          });

          // publisher.on('streamCreated', () => {});
          publisher.value.on('videoElementCreated', event => {
            // updateNumVideos(1);
            event.element.prop('muted', true);
          });
          publisher.value.on('videoElementDestroyed', event => {
            // updateNumVideos(-1);
          });
          publisher.value.on('streamPlaying', () => {});


          //publish my stream
          //내 스트림을 현재 세션에 보여줌
          session.value.publish(publisher.value);
          console.log('내화면 보여줄게');
        })
        .catch(error => {
          console.error('세션에 연결하는 중 오류가 발생했습니다:', error);
          // enableBtn();
        });

      return false;
    });
  };

  //버튼관리용 필요하려나
  // const enableBtn = () => {
  //   document.getElementById('join-btn').disabled = false;
  //   document.getElementById('join-btn').innerHTML = 'Join!';
  // };

  //토큰받아오기
  const getToken = function (coupleid) {
    console.log('getToken하기 ', coupleid);
    sessionName.value = coupleid;
    return viduapi
      .post('get-token', { sessionName: sessionName.value })
      .then(res => {
        console.log(res.data[0]);
        token.value = res.data[0];
        console.warn(`Token 요청 성공 (TOKEN: ${token.value})`);
        return token.value;
      })
      .catch(error => {
        console.error('TOKEN 요청 실패:', error);
        throw error;
      });
  };

  //세션닫기
  const closeSession = function () {
    viduapi
      .delete('close-session', {
        sessionName: sessionName.value,
      })
      .then(res => {
        console.warn(`Session ${sessionName.value} has been closed`);
      })
      .catch(error => {
        console.error("Session couldn't be closed");
      });
  };

  //세션정보불러오기
  const fetchInfo = function () {
    viduapi
      .post('fetch-info', {
        sessionName: sessionName.value,
      })
      .then(res => {
        console.warn('Session info has been fetched', res.data);
      })
      .catch(error => {
        console.error('세션 못찾음'.error);
      });
  };
  //녹화하기
  const startRecording = function () {
    //절대 INDIVIDUAL Mode로
    var outputMode = 'INDIVIDUAL';
    var hasAudio = true;
    var hasVideo = true;
    console.log(session.value.sessionId);
    viduapi
      .post('recording/start', {
        session: session.value.sessionId,
        outputMode,
        hasAudio,
        hasVideo,
      })
      .then(res => {
        console.log('녹화시작성공');
        forceRecordingId.value = res.data.id;
        console.log(`forceRecordingId : ${res.data.id}`);
      })
      .catch(error => {
        console.error(error);
        throw error;
      });
  };

  //녹화 끝
  const stopRecording = function () {
    viduapi
      .post('recording/stop', {
        recording: forceRecordingId.value,
      })
      .then(res => {
        //나중에 녹화가 완료되었습니다 알림같은거 뜨게하기
        console.log('녹화완료');
      })
      .catch(error => {
        console.error(error);
        throw error;
      });
  };

  //세션에서 나가기
  const removeUser = function () {
    viduapi
      .post('remove-user', {
        sessionName: sessionName.value,
        token: token.value,
      })
      .then(res => {
        console.warn('You have been removed from session ' + sessionName.value);
      })
      .catch(error => {
        console.error("User couldn't be removed from session");
      });
  };

  //세션나가기
  const leaveSession = function () {
    session.value.disconnect();
    // enableBtn();
  };
  //browser 메서드

  window.onbeforeunload = function () {
    if (session.value) {
      removeUser();
      leaveSession();
    }
  };

  return {
    OV,
    session,
    sessionName,
    token,
    forceRecordingId,
    publisher,
    subscriber,
    hasSubscriber,
    joinSession,
    // enableBtn,
    getToken,
    closeSession,
    fetchInfo,
    startRecording,
    stopRecording,
    removeUser,
    leaveSession,
  };
});
