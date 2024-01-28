<template>
	<nav class="navbar navbar-default">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="/">
					<img class="demo-logo" src="" /> Recording Java</a>
				<a class="navbar-brand nav-icon" href="https://github.com/OpenVidu/openvidu-tutorials/tree/master/openvidu-recording-java"
				 title="GitHub Repository" target="_blank">
					<i class="fa fa-github" aria-hidden="true"></i>
				</a>
				<a class="navbar-brand nav-icon" href="http://www.docs.openvidu.io/en/stable/tutorials/openvidu-recording-java/" title="Documentation"
				 target="_blank">
					<i class="fa fa-book" aria-hidden="true"></i>
				</a>
			</div>
		</div>
	</nav>

	<div id="main-container" class="container">
		<div id="join" class="vertical-center">
			<div id="img-div">
				<img src="" />
			</div>
			<div id="join-dialog" class="jumbotron">
				<h1>Join a video session</h1>
				<form class="form-group" onsubmit="return false">
					<p>
						<label>Session</label>
						<input class="form-control" type="text" id="sessionName" value="SessionA" required>
					</p>
					<p class="text-center">
						<button class="btn btn-lg btn-success" id="join-btn" @click="joinSession()">Join!</button>
					</p>
				</form>
				<hr>
			</div>
		</div>

		<div id="session" style="display: none">
			<div id="session-header">
				<h1 id="session-title"></h1>
				<input class="btn btn-sm btn-danger" type="button" id="buttonCloseSession" @mouseup="closeSession()" value="Close session">
				<input class="btn btn-sm btn-danger" type="button" id="buttonLeaveSession" @mouseup="removeUser(); leaveSession()"
				 value="Leave session">
				<div class="vertical-separator-top"></div>
				<input class="form-control" id="forceValue" type="text" @keyup="checkBtnsForce()">
				<input class="btn btn-sm" type="button" id="buttonForceUnpublish" @mouseup="forceUnpublish()" value="Force unpublish"
				 disabled>
				<input class="btn btn-sm" type="button" id="buttonForceDisconnect" @mouseup="forceDisconnect()" value="Force disconnect"
				 disabled>
				<div class="vertical-separator-top"></div>
				<input class="btn btn-sm" type="button" id="buttonFetchInfo" @mouseup="fetchInfo()" value="Fetch info">
				<input class="btn btn-sm" type="button" id="buttonFetchAll" @mouseup="fetchAll()" value="Fetch all">
			</div>
			<div id="video-container" class="col-md-12"></div>
			<div id="recording-btns">
				<div class="btns">
					<input class="btn btn-md" type="button" id="buttonStartRecording" @mouseup="startRecording()" value="Start recording">
					<form>
						<label class="radio-inline">
							<input type="radio" name="outputMode" value="COMPOSED" id="radio-composed" checked>COMPOSED
						</label>
						<label class="radio-inline">
							<input type="radio" name="outputMode" value="INDIVIDUAL" id="radio-individual">INDIVIDUAL
						</label>
					</form>
					<form>
						<label class="checkbox-inline">
							<input type="checkbox" id="has-audio-checkbox" checked>Has audio
						</label>
						<label class="checkbox-inline">
							<input type="checkbox" id="has-video-checkbox" checked>Has video
						</label>
					</form>
				</div>
				<div class="btns">
					<input class="btn btn-md" type="button" id="buttonListRecording" @mouseup="listRecordings()" value="List recordings">
					<div class="vertical-separator-bottom"></div>
					<input class="btn btn-md" type="button" id="buttonGetRecording" @mouseup="getRecording()" value="Get recording"
					 disabled>
					<input class="btn btn-md" type="button" id="buttonStopRecording" @mouseup="stopRecording()" value="Stop recording"
					 disabled>
					<input class="btn btn-md" type="button" id="buttonDeleteRecording" @mouseup="deleteRecording()" value="Delete recording"
					 disabled>
					<input class="form-control" id="forceRecordingId" type="text" @keyup="checkBtnsRecordings()">
				</div>
				<div class="textarea-container" id="textarea-http-container">
					<button type="button" class="btn btn-outline-secondary" id="clear-http-btn" @mouseup="clearHttpTextarea()">Clear</button>
					<span>HTTP responses</span>
					<textarea id="textarea-http" readonly="true" class="form-control" name="textarea-http"></textarea>
				</div>
				<div class="textarea-container" id="textarea-events-container">
					<button type="button" class="btn btn-outline-secondary" id="clear-events-btn" @mouseup="clearEventsTextarea()">Clear</button>
					<span>OpenVidu events</span>
					<textarea id="textarea-events" readonly="true" class="form-control" name="textarea-events"></textarea>
				</div>
			</div>
		</div>
	</div>

	<footer class="footer">
		<div class="container">
			<div class="text-muted">OpenVidu © 2022</div>
			<a href="http://www.openvidu.io/" target="_blank">
				<img class="openvidu-logo" src="" />
			</a>
		</div>
	</footer>


</template>

<script setup>
import { OpenVidu } from 'openvidu-browser';
import { ref } from 'vue';
import axios from 'axios';
const events = ref('');
const OV = ref(null);
const session = ref(null);
const sessionName = ref('SessionA');
const token = ref(null);
const numVideos = ref(0);
const textareaHttp = ref('');
const textareaEvents = ref('')
const Api = axios.create({
  baseURL: 'https://localhost:5000/recording-java/api/',
  headers: {
    'Content-Type': 'application/json',
  },
});
const pushEvent = function (event) {
  events.value += (!events.value ? '' : '\n') + event.type;
  textareaEvents.value = events.value;
};
const joinSession =function() {
  document.getElementById('join-btn').disabled = true;
  document.getElementById('join-btn').innerHTML = '참가 중...';

  getToken()
  .then((token) => {
    //객체생성
    console.log(`진짜여기${token}`)
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
        document.getElementById('session-title').textContent = sessionName.value;
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

        // HTML 비디오가 DOM에서 제거될 때...
        publisher.on('videoElementDestroyed', event => {
          pushEvent(event);
          // 사용자의 이름 및 닉네임에 대한 새로운 HTML 요소 추가
          updateNumVideos(-1);
        });

        // 발행자 스트림이 미디어 재생을 시작할 때...
        publisher.on('streamPlaying', event => {
          pushEvent(event);
        });
        // --- 8) 자신의 스트림을 게시합니다 ---
        session.value.publish(publisher);
      })
      .catch(error => {
        console.error(
          '세션에 연결하는 중 오류가 발생했습니다:',error
        );
        enableBtn();
      });

    return false;
  });
}

const enableBtn = () => {
  document.getElementById("join-btn").disabled = false;
  document.getElementById("join-btn").innerHTML = "Join!  ";
};



// 오픈비듀 api
const getToken = function() {
  sessionName.value = document.getElementById("sessionName").value;

  return Api.post('get-token', {'sessionName': sessionName.value})
    .then((res) => {
      const tokenMatch = res.data[0].match(/token=([^&]*)/);
      console.log(`여기${tokenMatch[1]}`)
      token.value = res.data[0];
      console.warn(`TOKEN 요청 성공 (TOKEN: ${token.value})`);
      return token.value;
      

    })
    .catch((error) => {
      console.error('TOKEN 요청 실패:', error);
      throw error;
    });
};

const removeUser = function() {
  Api.post('remove-user', {
    'sessionName': sessionName.value,
    'token':token.value
  })
  .then((res)=> {
    console.warn("You have been removed from session " + sessionName.value);
  })
  .catch((error)=>{
    console.error('User couldn\'t be removed from session',
)
  })
}

const closeSession = function() {
  Api.delete('close-session',{
    'sessionName': sessionName.value
  })
  .then((res)=>{
    console.warn(`Session ${sessionName} has been closed`)
  })
  .catch((error)=>{
    console.error('Session couldn\'t be closed')
  })
}

const fetchInfo = function() {
  Api.post('fetch-info', {
    'sessionName': sessionName.value
  })
  .then((res)=>{
    console.warn("Session info has been fetched");
    textareaHttp.value = JSON.stringify(res.data, null, '\t');
  })
  .catch((error)=>{
    console.error('Session couldn\'t be fetched')
  })
}

const fetchAll = function() {
  Api.get('fetch-all')
  .then((res) =>{
    console.warn("All session info has been fetched");
    textareaHttp.value = JSON.stringify(response.data, null, '\t');
  })
  .catch((error)=>{
    console.error('All session info couldn\'t be fetched')
  })
}

const forceDisconnect= function() {
  Api.delete('force-disconnect',{
    'sessionName':sessionName.value,
    'connectionId':document.getElementById('forceValue').value
  })
  .then((res)=>{
    console.warn("Connection has been closed");
  })
  .catch((error)=>{
    console.error('Connection couldn\'t be closed',
)
  })
}

const forceUnpublish = function() {
  Api.delete('force-unpublish',{
    'sessionName': sessionName.value,
    'streamId': document.getElementById('forceValue').value
  })
  .then((res)=>{
    console.warn("Stream has been closed");
  })
  .catch((error)=>{
    console.error('Stream couldn\'t be closed')
  })
}

const startRecording = function(){
  var outputMode = document.querySelector('input[name=outputMode]:checked').value;
  var hasAudio = document.getElementById('has-audio-checkbox').checked;
  var hasVideo = document.getElementById('has-video-checkbox').checked;
  Api.post('recording/start',{
    session: session.value.sessionId,
    outputMode: outputMode,
    hasAudio: hasAudio,
    hasVideo: hasVideo
  })
  .then((res)=>{
    console.log(res);
			document.getElementById('forceRecordingId').value = res.data.id;
			checkBtnsRecordings();
      document.getElementById('textarea-http').textContent = JSON.stringify(res.data, null, "\t");
  })
}

const stopRecording = function() {
  var forceRecordingId = document.getElementById('forceRecordingId').value;
  Api.post('recording/stop',{
    recording: forceRecordingId
  })
  .then((res)=>{
    console.log(res);
    textareaHttp.value = JSON.stringify(res.data, null, "\t");
  })
}

const deleteRecording = function() {
  var forceRecordingId = document.getElementById('forceRecordingId').value;
  Api.delete('recording/delete',{
    recording: forceRecordingId
  })
  .then((res)=>{
    console.log("DELETE ok");
    textareaHttp.value = 'DELETE ok';
  })
}

const getRecording = function() {
  var forceRecordingId = document.getElementById('forceRecordingId').value;
  Api.get(`recording/get/${forceRecordingId}`,
  {})
  .then((res)=>{
    console.log(res);
    textareaHttp.value =JSON.stringify(res.data, null, "\t");
  })
  .error((error)=>{
    console.error('Get recording WRONG')
  })
}

const listRecordings= function() {
  Api.get('recording/list',{})
  .then((res)=>{
    console.log(res);
    textareaHttp.value=JSON.stringify(res.data, null, "\t");
  })
}

//browser 메서드
window.onbeforeunload = function () { // Gracefully leave session
	if (session) {
		removeUser();
		leaveSession();
	}
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
//입력값이 있을때만 버튼활성화
const checkBtnsForce =function() {
	if (document.getElementById("forceValue").value === "") {
		document.getElementById('buttonForceUnpublish').disabled = true;
		document.getElementById('buttonForceDisconnect').disabled = true;
	} else {
		document.getElementById('buttonForceUnpublish').disabled = false;
		document.getElementById('buttonForceDisconnect').disabled = false;
	}
}
// 입력값이 있을때만 녹화버튼 활성화
const checkBtnsRecordings =function() {
	if (document.getElementById("forceRecordingId").value === "") {
		document.getElementById('buttonGetRecording').disabled = true;
		document.getElementById('buttonStopRecording').disabled = true;
		document.getElementById('buttonDeleteRecording').disabled = true;
	} else {
		document.getElementById('buttonGetRecording').disabled = false;
		document.getElementById('buttonStopRecording').disabled = false;
		document.getElementById('buttonDeleteRecording').disabled = false;
	}
}

const clearHttpTextarea = function() {
	textareaHttp.value=''
}

const clearEventsTextarea = function() {
  textareaEvents.value = ''
  events.value=''
}


</script>

<style scoped></style>
