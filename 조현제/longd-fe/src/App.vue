<script setup>
import axios from 'axios';
import { OpenVidu } from 'openvidu-browser';
import { ref } from 'vue';

axios.defaults.headers.post['Content-Type'] = 'application/json';
// const APPLICATION_SERVER_URL = process.env.NODE_ENV === 'production' ? '' : 'http://localhost:5000/';
var APPLICATION_SERVER_URL = 'http://localhost:5000/';


const OV = ref(undefined);
var session;
var mySessionId = document.getElementById('sessionId').value;

OV = new OpenVidu();
session = OV.initSession();

session.on('streamCreated', function (event) {
  session.subscribe(event.stream, 'subscriber');
});


const OV = ref(undefined);
const session = ref(undefined);
const mainStreamManager = ref(undefined);
const publisher = ref(undefined);
const subscribers = reactive([]);


function createToken(sessionId) {
  return new Promise((resolve, reject) => {
    $.ajax({
      type: 'POST',
      url:
        APPLICATION_SERVER_URL + 'api/sessions/' + sessionId + '/connections',
      date: JSON.stringify({}),
      headers: { 'Content-Type': 'application/json' },
      success: response => resolve(response), // the token
      error: error => reject(error),
    });
  });
}

const mySessionId = ref('SessionA');
const myUserName = ref('Participant' + Math.floor(Math.random() * 100));

const joinSession = async () => {
  //openvidu 객체생성
  OV.value = new OpenVidu();
  //세션 초기화
  session.value = OV.value.initSession();
  // 세션이벤트 처리지정
  //새로운 스트림이 생길때 마다 구독자 추가
  session.value.on('streamCreated', ({ stream }) => {
    const subscriber = session.value.subscribe(stream);
    subscribers.push(subscriber);
  });
  //스트림이 파괴될 때마다 구독자 제거
  session.value.on('streamDestroyed', ({ stream }) => {
    const index = subscribers.indexOf(stream.streamManager, 0);
    if (index >= 0) {
      subscribers.splice(index, 1);
    }
  });

  // 모든 비동기 예외가 발생할 때마다...
  session.value.on('exception', ({ exception }) => {
    console.warn(exception);
  });
  //Openvidu 배포에서 토큰 가져오기
  const token = await getToken(mySessionId.value);
  session.value
    .connect(token, { clientData: myUserName.value })
    .then(() => {
      //사용자 웹캠 스트림을 얻고 메인 비디오로 표시
      let publisherInstance = {
        audioSource: undefined,
        videoSource: undefined,
        publishAudio: true,
        publishVideo: true,
        resolution: '640x480',
        frameRate: 30,
        insertMode: 'APPEND',
        mirror: false,
      };
      let pub = OV.value.initPublisher(undefined, publisherInstance);
      //페이지에 자신의 웹캠 표시하고 발행자 저장
      mainStreamManager.value = pub;
      publisher.value = pub;
      //스트림 발행
      session.value.publish(publisher.value);
    })
    .catch(error => {
      console.error(
        'There was an error connecting to the session:',
        error.code,
        error.message,
      );
    });

  //페이지 나가거나 새로 고침할 때 세션 나가도록 이벤트 리스너 추가
  // window.addEventListener('beforeunload', leaveSession);
};
//세션 나가기 함수
const leaveSession = () => {
  // Session 객체의 disconnect 메서드 호출로 세션 나가기
  if (session.value) {
    session.value.disconnect();
  }

  //모든 속성 초기화
  session.value = undefined;
  mainStreamManager.value = undefined;
  publisher.value = undefined;
  subscribers.splice(0, subscribers.length);
  OV.value = undefined;

  //beforeunload 이벤트 리스너 제거
  // window.removeEventListener('beforeunload', leaveSession);
};

//메인 비디오 스트림 매니저 업데이트 함수
const updateMainVideoStreamManager = stream => {
  if (mainStreamManager.value === stream) return;
  mainStreamManager.value = stream;
};

//세션 토크 가져오기 함수
const getToken = async sessionId => {
  const createdSessionId = await createSession(sessionId);
  console.log(`이거다 ${createdSessionId}`);
  return await createToken(createdSessionId);
};

//세션 생성 함수
const createSession = async sessionId => {
  const response = await axios.post(
    APPLICATION_SERVER_URL + 'api/sessions',
    { customSessionId: sessionId },
    // {
    //   headers: { 'Content-Type': 'application/json' },
    // },
  );
  return response.data; //세션 ID반환
};

//토큰 생성 함수
const createToken = async sessionId => {
  const response = await axios.post(
    APPLICATION_SERVER_URL + 'api/sessions/' + sessionId + '/connections',
    { headers: { 'Content-Type': 'application/json' } },
  );

  return response.data; //토큰 반환
};
//컴포넌트가 언마운트될 때 세션 나가기
onBeforeMount(() => {
  leaveSession();
  window.removeEventListener('beforeunload', () => leaveSession());
});

const startRecord = async sessionId => {
  const response = await axios.post(
    APPLICATION_SERVER_URL + 'api/sessions/' + 'start',
    {
      session: 'as',
      name: 'MyRecording',
      hasAudio: true,
      hasVideo: true,
      outputMode: 'COMPOSED',
      recordingLayout: 'CUSTOM',
      customLayout: 'mySimpleLayout',
      resolution: '1280x720',
      frameRate: 25,
      shmSize: 536870912,
      ignoreFailedStreams: false,
      mediaNode: {
        id: 'media_i-0c58bcdd26l11d0sd',
      },
    },
  );
  console.log(`여기${response}`);
  return response.data;
};
const stopRecord = async sessionId => {
  const response = await axios.post(
    APPLICATION_SERVER_URL + 'api/sessions/' + 'stop',
  );
  return response.data;
};

</script>

<style scoped></style>
