import { ref, computed } from 'vue';
import { defineStore } from 'pinia';
import { OpenVidu } from 'openvidu-browser';
import { viduapi } from '@/utils/api/index.js';
import Swal from 'sweetalert2';
export const useViduStore = defineStore('vidu', () => {
  const OV = ref(null);
  const session = ref(null);
  const sessionName = ref('aa');
  const token = ref(null);
  const forceRecordingId = ref(null);
  const publisher = ref(null);
  const subscriber = ref(null);
  const hasSubscriber = computed(() => !!subscriber.value);
  const test1 = ref(null);
  const test2 = ref(null);
  const videoContainer = ref(null);
  const publisherTest = ref(false);
  const isAudioMuted = ref(false);
  const isVideoOff = ref(false);
  const volume = ref(50);
  const toggleAudio = () => {
    if (publisher.value) {
      isAudioMuted.value = !isAudioMuted.value;
      publisher.value.publishAudio(!isAudioMuted.value);
    }
  };

  const toggleVideo = () => {
    if (publisher.value) {
      isVideoOff.value = !isVideoOff.value;
      publisher.value.publishVideo(!isVideoOff.value);
    }
  };

  const adjustVolume = () => {
    if (publisher.value && publisher.value.element) {
      publisher.value.element.volume = volume.value / 100; // 볼륨을 0~1 사이 값으로 조절
    }
  };
  //세션들어가기
  const joinSession = function (coupleid) {
    //토큰부터받아오고
    getToken(coupleid).then(token => {
      OV.value = new OpenVidu();
      console.log('hihi');
      console.log('Token:', token);

      session.value = OV.value.initSession();
      console.log('session', session.value);
      //연결만들때 할거 생기면 적는용
      // session.value.on('connectionCreated', (event) => {});
      // 연결 끊겼을때 할거 생기면 적는용
      // session.value.on('connectionDestroyed', (event) => {});

      //새로운 스트림 생겼을 때 다른 참가자가 오디오,비디오 전송시작할 때
      session.value.on('streamCreated', event => {
        console.log('되니되니되니?');
        subscriber.value = session.value.subscribe(
          event.stream,
          videoContainer.value,
        );
        // console.log(subscriber.value.addVideoElement(test2.value));
        // console.log(event.stream.addVideoElement(test1.value));

        //세션에 비디오 엘리먼트가 생겼을 때
        subscriber.value.on('videoElementCreated', event => {
          console.log('떠라떠라떠라');
          // updateNumVideos(1);
        });
        //세션에 비디오 엘리먼트 사라졌을때
        subscriber.value.on('videoElementDestroyed', event => {
          // updateNumVideos(-1);
        });

        // 비디오스트림이 재생되기 시작할 때 넣고싶은 로직 넣기
        //   subscriber.on('streamPlaying', (event) => {});
        // });

        // 비디오 스트림 종료되었을때 넣고싶은 로직
        // session.value.on('streamDestroyed', (event) => {});
      });
      //연결이 끝났을 떄
      session.value.on('sessionDisconnected', event => {
        if (event.reason !== 'disconnect') {
          removeUser();
        }
        if (event.reason !== 'sessionClosedByServer') {
          session.value = null;
          // numVideos.value = 0;
          //서버에의해 샤따내렸을 때 칸안보이게
          // document.getElementById('join').style.display = 'block';
          // document.getElementById('session').style.display = 'none';
        }
      });

      //녹화시작할때 이벤트처리용
      // session.value.on('recordingStarted', (event) => {});
      //녹화 끝났을 때 처리용
      // session.value.on('recordingStopped', (event) => {});
      session.value.on('exception', console.warn);

      //토큰가지고 연결
      session.value
        .connect(token)
        .then(() => {
          // 활성 통화를 위한 페이지 레이아웃 설정
          // document.getElementById('session-title').textContent =
          //   sessionName.value;
          // document.getElementById('join').style.display = 'none';
          // document.getElementById('session').style.display = 'block';

          console.log('dsada412421214d', publisher.value);
          // 자신의 카메라 스트림 가져오기
          publisher.value = OV.value.initPublisher('video-container', {
            audioSource: undefined,
            videoSource: undefined,
            publishAudio: true,
            publishVideo: true,
            resolution: '640x480',
            frameRate: 30,
            insertMode: 'APPEND',
            mirror: false,
          });
          console.log('dsadasdasdad', publisher.value);

          // 스트림이 만들어졌을떄
          publisher.value.on('streamCreated', () => {
            console.log('어디지?');
          });
          //요청이 거부당했을떄
          // publisher.on('accessDenied', (event) => {});
          //카메라, 마이크 사용권한부여 생길떄
          // publisher.on('accessDialogOpened', () => {});
          //카메라, 마이크 사용권한부여 닫길떄
          // publisher.on('accessDialogClosed', () => {});

          // publisher.on('streamCreated', () => {});
          publisher.value.on('videoElementCreated', event => {
            // updateNumVideos(1);
            console.log('여기가뜨는건가?');
            event.element.prop('muted', true);
          });
          publisher.value.on('videoElementDestroyed', event => {
            // updateNumVideos(-1);
          });
          publisher.value.on('streamPlaying', () => {});

          session.value.publish(publisher.value);
        })
        .then(res => {
          publisherTest.value = true;
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
    console.log('hihibyby', coupleid);
    sessionName.value = coupleid;
    return viduapi
      .post('/get-token', { sessionName: sessionName.value })
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
    //일단 COMPOSED Mode로
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
        // 녹화 성공 알림
        const Toast = Swal.mixin({
          toast: true,
          position: 'top-end',
          showConfirmButton: false,
          timer: 1500,
          didOpen: toast => {
            toast.onmouseenter = Swal.stopTimer;
            toast.onmouseleave = Swal.resumeTimer;
          },
        });
        Toast.fire({
          icon: 'success',
          title: '녹화가 시작되었습니다.',
        });
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
        // 녹화 완료 알림
        const Toast = Swal.mixin({
          toast: true,
          position: 'top-end',
          showConfirmButton: false,
          timer: 1500,
          didOpen: toast => {
            toast.onmouseenter = Swal.stopTimer;
            toast.onmouseleave = Swal.resumeTimer;
          },
        });
        Toast.fire({
          icon: 'success',
          title: '녹화가 완료되었습니다.',
        });
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
    test1,
    test2,
    publisherTest,
    joinSession,
    // enableBtn,
    getToken,
    closeSession,
    fetchInfo,
    startRecording,
    stopRecording,
    removeUser,
    leaveSession,
    toggleAudio,
    toggleVideo,
    adjustVolume,
  };
});
