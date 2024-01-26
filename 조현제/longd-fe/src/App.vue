<template>
  <div></div>
</template>

<script setup>
import { OpenVidu } from 'openvidu-browser';
import { ref } from 'vue';
const events = ref('');
const OV = ref(null);
const session = ref(null);
const sessionName = ref('SessionA');
const token = ref(null);
const numVideos = ref(0);

const pushEvent = function (event) {
  events.value += (!events.value ? '' : '\n') + event.type;
  document.getElementById('textarea-events').value = events.value;
};
function joinSession() {
  document.getElementById('join-btn').disabled = true;
  document.getElementById('join-btn').innerHTML = '참가 중...';

  getToken(() => {
    //객체생성
    OV.value = new OpenVidu();
    //색션 생성
    session.value = OV.value.initSession();

    //이벤트 출력하기
    session.value.on('connectionCreated', event => {
      pushEvent(event);
    });

    session.value.on('connectionDestroyed', event => {
      pushEvent(event);
    });
    session.value.on('streamCreated', event => {
      pushEvent(event);
      var subscriber = session.value.subscribe(event.stream, 'video-container');

      subscriber.on('videoElementCreated', event => {
        pushEvent(event);
        updateNumVideos(1);
      });

      subscriber.on('videoElementDestroyed', event => {
        pushEvent(event);
        updateNumVideos(-1);
      });

      subscriber.on('streamPlaying', event => {
        pushEvent(event);
      });
    });
    session.value.on('streamDestroyed', event => {
      pushEvent(event);
    });
    session.value.on('sessionDisconnected', event => {
      pushEvent(event);
      if (event.reason !== 'disconnect') {
        removeUser();
      }
      if (event.reason !== 'sessionClosedByServer') {
        session.value = null;
        numVideos.value = 0;
        document.getElementById('join').style.display = 'block';
        document.getElementById('session').style.display = 'none';
      }
    });
    session.value.on('recordingStarted', event => {
      pushEvent(event);
    });

    session.value.on('recordingStopped', event => {
      pushEvent(event);
    });
    //비동기 에러뜬거
    session.value.on('exception', exception => {
      console.warn(exception);
    });
    // --- 4) 세션에 연결하여 검색한 토큰 및 클라이언트에서 가져온 추가 데이터 전달 ---
    session.value
      .connect(token)
      .then(() => {
        // --- 5) 활성 통화를 위한 페이지 레이아웃 설정 ---
        document.getElementById('session-title').textContent = sessionName;
        document.getElementById('join').style.display = 'none';
        document.getElementById('session').style.display = 'block';

        // --- 6) 자신의 카메라 스트림을 가져오기 ---
        const publisher = OV.value.initPublisher('video-container', {
          audioSource: undefined,
          videoSource: undefined,
          publishAudio: true,
          publishVideo: true,
          resolution: '640x480',
          frameRate: 30,
          insertMode: 'APPEND',
          mirror: false,
        });

        publisher.on('streamCreated', () => {
          pushEvent({
            type: 'accessAllowed',
          });
        });
        publisher.on('accessDenied', event => {
          pushEvent(event);
        });
        publisher.on('accessDialogOpened', () => {
          pushEvent({
            type: 'accessDialogOpened',
          });
        });
        publisher.on('accessDialogClosed', () => {
          pushEvent({
            type: 'accessDialogClosed',
          });
        });
        // 발행자 스트림이 미디어 재생을 시작하면...
        publisher.on('streamCreated', event => {
          pushEvent(event);
        });
        // HTML 비디오가 DOM에 추가될 때...
        publisher.on('videoElementCreated', event => {
          pushEvent(event);
          updateNumVideos(1);
          event.element.prop('muted', true);
        });

        // HTML 비디오가 DOM에 추가되었을 때...
        publisher.on('videoElementDestroyed', event => {
          pushEvent(event);
          updateNumVideos(-1);
        });

        publisher.on('streamPlaying', event => {
          // handle stream playing event
        });

        session.value.publish(publisher);
      })
      .catch(error => {
        console.warn(
          '세션에 연결하는 중 오류가 발생했습니다:',
          error.code,
          error.message,
        );
        enableBtn();
      });

    return false;
  });
}

const updateNumVideos = function (i) {
  numVideos.value += i;
  const videoElement = document.querySelector('video');

  switch (numVideos.value) {
    case 1:
      videoElement.classList.add('two');
      break;
    case 2:
      videoElement.classList.add('two');
      break;
    case 3:
      videoElement.classList.add('three');
      break;
    case 4:
      videoElement.classList.add('four');
      break;
  }
};
</script>

<style scoped></style>
