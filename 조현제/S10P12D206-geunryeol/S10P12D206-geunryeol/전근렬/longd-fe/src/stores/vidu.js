import { ref, computed } from 'vue';
import { defineStore } from 'pinia';
import { OpenVidu } from 'openvidu-browser';
import{ viduapi } from '@/api';
export const useCounterStore = defineStore('vidu', () => {
  const OV = ref(null)
  const session = ref(null);
  const sessionName = ref('SessionA');
  const token = ref(null);
  //세션들어가기
  const joinSession = function() {
    //토큰부터받아오고
    getToken()
    .then((token) => {
      OV.value = new OpenVidu();
      session.value = OV.value.initSession();
      //연결만들때 할거 생기면 적는용
      // session.value.on('connectionCreated', (event) => {});
      // 연결 끊겼을때 할거 생기면 적는용
      // session.value.on('connectionDestroyed', (event) => {});

      //새로운 스트림 생겼을 때 다른 참가자가 오디오,비디오 전송시작할 때
      session.value.on('streamCreated', (event) => {
        const subscriber = session.value.subscribe(event.stream, 'video-container');
        //세션에 비디오 엘리먼트가 생겼을 때
        subscriber.on('videoElementCreated', (event) => {
          updateNumVideos(1);
        });
        //세션에 비디오 엘리먼트 사라졌을때
        subscriber.on('videoElementDestroyed', (event) => {
          updateNumVideos(-1);
        });

        // 비디오스트림이 재생되기 시작할 때 넣고싶은 로직 넣기
      //   subscriber.on('streamPlaying', (event) => {});
      // });

      // 비디오 스트림 종료되었을때 넣고싶은 로직
      // session.value.on('streamDestroyed', (event) => {});

      //연결이 끝났을 떄
      session.value.on('sessionDisconnected', (event) => {
        if (event.reason !== 'disconnect') {
          removeUser();
        }
        if (event.reason !== 'sessionClosedByServer') {
          session.value = null;
          numVideos.value = 0;
          //서버에의해 샤따내렸을 때 칸안보이게
          document.getElementById('join').style.display = 'block';
          document.getElementById('session').style.display = 'none';
        }
      });

      //녹화시작할때 이벤트처리용
      // session.value.on('recordingStarted', (event) => {});
      //녹화 끝났을 때 처리용
      // session.value.on('recordingStopped', (event) => {});
      session.value.on('exception', console.warn);

      //토큰가지고 연결
      session.value.connect(token)
        .then(() => {
          // 활성 통화를 위한 페이지 레이아웃 설정
          document.getElementById('session-title').textContent = sessionName.value;
          document.getElementById('join').style.display = 'none';
          document.getElementById('session').style.display = 'block';

          // 자신의 카메라 스트림 가져오기
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

          //스트림이 만들어졌을떄
          // publisher.on('streamCreated', () => {});
          //요청이 거부당했을떄
          // publisher.on('accessDenied', (event) => {});
          //카메라, 마이크 사용권한부여 생길떄
          // publisher.on('accessDialogOpened', () => {});
          //카메라, 마이크 사용권한부여 닫길떄
          // publisher.on('accessDialogClosed', () => {});

          // publisher.on('streamCreated', () => {});
          publisher.on('videoElementCreated', (event) => {
            updateNumVideos(1);
            event.element.prop('muted', true);
          });
          publisher.on('videoElementDestroyed', (event) => {
            updateNumVideos(-1);
          });
          publisher.on('streamPlaying', () => {});

          session.value.publish(publisher);
        })
        .catch((error) => {
          console.error('세션에 연결하는 중 오류가 발생했습니다:', error);
          enableBtn();
        });

      return false;
    })
    ;
})};
  //버튼관리용 필요하려나
  const enableBtn =() => {
    document.getElementById("join-btn").disabled = false;
    document.getElementById("join-btn").innerHTML = "Join!  ";
  }

  return {  };

});
