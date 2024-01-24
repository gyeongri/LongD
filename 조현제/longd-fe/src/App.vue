<script setup>
import axios from 'axios';
import { OpenVidu } from 'openvidu-browser';
import { onBeforeMount, ref } from 'vue';
import UserVideo from '@/components/UserVideo.vue';
axios.defaults.headers.post['Content-Type'] = 'application/json';
// const APPLICATION_SERVER_URL = process.env.NODE_ENV === 'production' ? '' : 'http://localhost:5000/';
var APPLICATION_SERVER_URL = 'http://localhost:5000/';

const OV = ref(undefined);
const session = ref(null);
const mainStreamManager = ref(null);
const publisher = ref(null);
const subscribers = ref([]);

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
    const subscriber = session.value.subscriber(stream);
    subscribers.value.push(subscriber);
  });
  //스트림이 파괴될 때마다 구독자 제거
  session.value.on('streamDestroyed', ({ stream }) => {
    const index = subscribers.value.indexOf(stream.streamManager, 0);
    if (index >= 0) {
      subscribers.value.splice(index, 1);
    }
  });

  // 모든 비동기 예외가 발생할 때마다...
  session.value.on('exception', ({ exception }) => {
    console.warn(exception);
  });
  //Openvidu 배포에서 토큰 가져오기
  const token = await getToken(mySessionId.value);
  session.value.connect(token, { clientData: myUserName.value });

  //사용자 웹캠 스트림을 얻고 메인 비디오로 표시
  let publisherInstance = OV.value.initPublisher(undefined, {
    audioSource: undefined,
    videoSource: undefined,
    publishAudio: true,
    publishVideo: true,
    resolution: '640x480',
    frameRate: 30,
    insertMode: 'APPEND',
    mirror: false,
  });

  //페이지에 자신의 웹캠 표시하고 발행자 저장
  mainStreamManager.value = publisherInstance;
  publisher.value = publisherInstance;

  //스트림 발행
  session.value.publish(publisher.value);

  //페이지 나가거나 새로 고침할 때 세션 나가도록 이벤트 리스너 추가
  window.addEventListener('beforeunload', leaveSession);
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
  subscribers.value = [];
  OV.value = undefined;

  //beforeunload 이벤트 리스너 제거
  window.removeEventListener('beforeunload', leaveSession);
};

//메인 비디오 스트림 매니저 업데이트 함수
const updateMainVideoStreamManager = stream => {
  if (mainStreamManager.value === stream) return;
  mainStreamManager.value = stream;
};

//세션 토크 가져오기 함수
const getToken = async mySessionId => {
  const sessionId = await createSession(mySessionId);
  return await createToken(sessionId);
};

//세션 생성 함수
const createSession = async sessionId => {
  const response = await axios.post(
    APPLICATION_SERVER_URL + 'api/sessions',
    { customSessionId: sessionId },
    {
      headers: { 'Content-Type': 'application/json' },
    },
  );
  return response.data; //세션 ID반환
};

//토큰 생성 함수
const createToken = async sessionId => {
  const response = await axios.post(
    APPLICATION_SERVER_URL + 'api/sessions/' + sessionId + '/connections',
    {},
    { headers: { 'Content-Type': 'application/json' } },
  );

  return response.data; //토큰 반환
};
//컴포넌트가 언마운트될 때 세션 나가기
onBeforeMount(() => {
  leaveSession();
  window.removeEventListener('beforeunload', () => leaveSession());
});
</script>

<template>
  <div id="main-container" class="container">
    <div v-if="!session" id="join">
      <div id="img-div">
        <img src="/resources/images/openvidu_grey_bg_transp_cropped.png" />
      </div>
      <div class="jumbotron vertical-center" id="join-dialog">
        <h1>비디오 세션 참여하기</h1>
        <div class="form-group">
          <p>
            <label>참여자</label>
            <input
              v-model="myUserName"
              class="form-control"
              type="text"
              required
            />
          </p>
          <p>
            <label>세션</label>
            <input
              v-model="mySessionId"
              class="form-control"
              type="text"
              required
            />
          </p>
          <p class="text-center">
            <button @click="joinSession" class="btn btn-lg btn-success">
              참여하기!
            </button>
          </p>
        </div>
      </div>
    </div>

    <div v-if="session" id="session">
      <div id="session-header">
        <h1 :id="session ? session.title : ''">{{ mySessionId }}</h1>
        <input
          @click="leaveSession"
          type="button"
          id="buttonLeaveSession"
          class="btn btn-large btn-danger"
          value="세션 나가기"
        />
      </div>
      <div class="col-md-6" id="main-video">
        <user-video :stream-manager="mainStreamManager" />
      </div>
      <div id="video-container" class="col-md-6">
        <user-video
          @click="updateMainVideoStreamManager(publisher)"
          :stream-manager="publisher"
        />
        <user-video
          v-for="sub in subscribers"
          :key="sub.stream.connection.connectionId"
          :stream-manager="sub"
          @click="updateMainVideoStreamManager(sub)"
        />
      </div>
    </div>
  </div>
</template>

<style scoped></style>
