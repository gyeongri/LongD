package io.openvidu.recording.java;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

//import com.google.gson.stream.JsonReader;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import io.openvidu.recording.java.entity.Gallery;
import io.openvidu.recording.java.repository.GalleryRepository;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import io.openvidu.java.client.ConnectionProperties;
import io.openvidu.java.client.ConnectionType;
import io.openvidu.java.client.OpenVidu;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import io.openvidu.java.client.OpenViduRole;
import io.openvidu.java.client.Recording;
import io.openvidu.java.client.RecordingProperties;
import io.openvidu.java.client.Session;

@RestController
@RequestMapping("/api")
public class MyRestController {

	// OpenVidu object as entrypoint of the SDK
	private final OpenVidu openVidu;

	// Collection to pair session names and OpenVidu Session objects
	private final Map<String, Session> mapSessions = new ConcurrentHashMap<>();
	// Collection to pair session names and tokens (the inner Map pairs tokens and
	// role associated)
	private final Map<String, Map<String, OpenViduRole>> mapSessionNamesTokens = new ConcurrentHashMap<>();
	// Collection to pair session names and recording objects
	private Map<String, Boolean> sessionRecordings = new ConcurrentHashMap<>();

	// URL where our OpenVidu server is listening
	private final String OPENVIDU_URL;
	// Secret shared with our OpenVidu server
	private String SECRET;

	private String sName,sId;
	private AmazonS3 amazonS3;
	private String bucketName;
	private GalleryRepository galleryRepository;

	public MyRestController(@Value("${openvidu.secret}") String secret, @Value("${openvidu.url}") String openviduUrl) {
		this.SECRET = secret;
		this.OPENVIDU_URL = openviduUrl;
		this.openVidu = new OpenVidu(OPENVIDU_URL, SECRET);
	}



	/*******************/
	/*** Session API ***/
	/*******************/
//	@CrossOrigin
	@RequestMapping(value = "/get-token", method = RequestMethod.POST)
	public ResponseEntity<JsonObject> getToken(@RequestBody Map<String, Object> sessionNameParam) {
		System.out.println("Getting sessionId and token | {sessionName}=" + sessionNameParam);
		// The video-call to connect ("TUTORIAL")
		String sessionName = (String) sessionNameParam.get("sessionName");
		// Role associated to this user
		OpenViduRole role = OpenViduRole.PUBLISHER;
		System.out.println("[get-token] sessionName : " + sessionName);
		// Build connectionProperties object with the serverData and the role
		ConnectionProperties connectionProperties = new ConnectionProperties.Builder().type(ConnectionType.WEBRTC)
				.role(role).data("user_data").build();

		System.out.println("[get-token] connectionProperties : " + connectionProperties.getData().toString() + "\n[get-token] role : "+connectionProperties.getRole());
		JsonObject responseJson = new JsonObject();

		if (this.mapSessions.get(sessionName) != null) {
			// Session already exists
			System.out.println("Existing session " + sessionName);
			try {
				// Generate a new token with the recently created connectionProperties
				String token = this.mapSessions.get(sessionName).createConnection(connectionProperties).getToken();
				// Update our collection storing the new token
				this.mapSessionNamesTokens.get(sessionName).put(token, role);
				// Prepare the response with the token
				responseJson.addProperty("0", token);
				// Return the response to the client
				System.out.println(sessionName);
				return new ResponseEntity<>(responseJson, HttpStatus.OK);
			} catch (OpenViduJavaClientException e1) {
				// If internal error generate an error message and return it to client
				return getErrorResponse(e1);
			} catch (OpenViduHttpException e2) {
				if (404 == e2.getStatus()) {
					// Invalid sessionId (user left unexpectedly). Session object is not valid
					// anymore. Clean collections and continue as new session
					this.mapSessions.remove(sessionName);
					this.mapSessionNamesTokens.remove(sessionName);
				}
			}
		}
		// New session
		System.out.println("[get-token] New session : " + sessionName);
		try {
			System.out.println("createSession 생성하기");
			// Create a new OpenVidu Session
			Session session = this.openVidu.createSession();
			System.out.println("createSession 생성완료");
			System.out.println("[get-token] session : " + session.getSessionId());
			// Generate a new token with the recently created connectionProperties
			String token = session.createConnection(connectionProperties).getToken();
			// Store the session and the token in our collections
			this.mapSessions.put(sessionName, session);
			this.mapSessionNamesTokens.put(sessionName, new ConcurrentHashMap<>());
			this.mapSessionNamesTokens.get(sessionName).put(token, role);
//			System.out.println("[get-token] mapSessionNames : ");
			// Prepare the response with the sessionId and the token
			responseJson.addProperty("0", token);
			// Return the response to the client
			return new ResponseEntity<>(responseJson, HttpStatus.OK);
		} catch (Exception e) {
			// If error generate an error message and return it to client
			return getErrorResponse(e);
		}
	}

	@RequestMapping(value = "/remove-user", method = RequestMethod.POST)
	public ResponseEntity<JsonObject> removeUser(@RequestBody Map<String, Object> sessionNameToken) throws Exception {
		System.out.println("Removing user | {sessionName, token}=" + sessionNameToken);
		// Retrieve the params from BODY
		String sessionName = (String) sessionNameToken.get("sessionName");
		String token = (String) sessionNameToken.get("token");
		// If the session exists
		if (this.mapSessions.get(sessionName) != null && this.mapSessionNamesTokens.get(sessionName) != null) {
			// If the token exists
			if (this.mapSessionNamesTokens.get(sessionName).remove(token) != null) {
				// User left the session
				if (this.mapSessionNamesTokens.get(sessionName).isEmpty()) {
					// Last user left: session must be removed
					this.mapSessions.remove(sessionName);
				}
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				// The TOKEN wasn't valid
				System.out.println("Problems in the app server: the TOKEN wasn't valid");
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			// The SESSION does not exist
			System.out.println("Problems in the app server: the SESSION does not exist");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/close-session", method = RequestMethod.DELETE)
	public ResponseEntity<JsonObject> closeSession(@RequestBody Map<String, Object> sessionName) throws Exception {

		System.out.println("Closing session | {sessionName}=" + sessionName);

		// Retrieve the param from BODY
		String session = (String) sessionName.get("sessionName");

		// If the session exists
		if (this.mapSessions.get(session) != null && this.mapSessionNamesTokens.get(session) != null) {
			Session s = this.mapSessions.get(session);
			s.close();
			this.mapSessions.remove(session);
			this.mapSessionNamesTokens.remove(session);
			this.sessionRecordings.remove(s.getSessionId());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			// The SESSION does not exist
			System.out.println("Problems in the app server: the SESSION does not exist");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/fetch-info", method = RequestMethod.POST)
	public ResponseEntity<JsonObject> fetchInfo(@RequestBody Map<String, Object> sessionName) {
		try {
			System.out.println("Fetching session info | {sessionName}=" + sessionName);

			// Retrieve the param from BODY
			String session = (String) sessionName.get("sessionName");

			// If the session exists
			if (this.mapSessions.get(session) != null && this.mapSessionNamesTokens.get(session) != null) {
				Session s = this.mapSessions.get(session);
				boolean changed = s.fetch();
				System.out.println("Any change: " + changed);
				return new ResponseEntity<>(this.sessionToJson(s), HttpStatus.OK);
			} else {
				// The SESSION does not exist
				System.out.println("Problems in the app server: the SESSION does not exist");
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (OpenViduJavaClientException | OpenViduHttpException e) {
			e.printStackTrace();
			return getErrorResponse(e);
		}
	}

	@RequestMapping(value = "/fetch-all", method = RequestMethod.GET)
	public ResponseEntity<?> fetchAll() {
		try {
			System.out.println("Fetching all session info");
			boolean changed = this.openVidu.fetch();
			System.out.println("Any change: " + changed);
			JsonArray jsonArray = new JsonArray();
			for (Session s : this.openVidu.getActiveSessions()) {
				jsonArray.add(this.sessionToJson(s));
			}
			return new ResponseEntity<>(jsonArray, HttpStatus.OK);
		} catch (OpenViduJavaClientException | OpenViduHttpException e) {
			e.printStackTrace();
			return getErrorResponse(e);
		}
	}

	@RequestMapping(value = "/force-disconnect", method = RequestMethod.DELETE)
	public ResponseEntity<JsonObject> forceDisconnect(@RequestBody Map<String, Object> params) {
		try {
			// Retrieve the param from BODY
			String session = (String) params.get("sessionName");
			String connectionId = (String) params.get("connectionId");

			// If the session exists
			if (this.mapSessions.get(session) != null && this.mapSessionNamesTokens.get(session) != null) {
				Session s = this.mapSessions.get(session);
				s.forceDisconnect(connectionId);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				// The SESSION does not exist
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (OpenViduJavaClientException | OpenViduHttpException e) {
			e.printStackTrace();
			return getErrorResponse(e);
		}
	}

	@RequestMapping(value = "/force-unpublish", method = RequestMethod.DELETE)
	public ResponseEntity<JsonObject> forceUnpublish(@RequestBody Map<String, Object> params) {
		try {
			// Retrieve the param from BODY
			String session = (String) params.get("sessionName");
			String streamId = (String) params.get("streamId");

			// If the session exists
			if (this.mapSessions.get(session) != null && this.mapSessionNamesTokens.get(session) != null) {
				Session s = this.mapSessions.get(session);
				s.forceUnpublish(streamId);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				// The SESSION does not exist
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (OpenViduJavaClientException | OpenViduHttpException e) {
			e.printStackTrace();
			return getErrorResponse(e);
		}
	}

	/*******************/
	/** Recording API **/
	/*******************/

	@RequestMapping(value = "/recording/start", method = RequestMethod.POST)
	public ResponseEntity<?> startRecording(@RequestBody Map<String, Object> params) {
		String sessionId = (String) params.get("session");
		Recording.OutputMode outputMode = Recording.OutputMode.valueOf((String) params.get("outputMode"));
		boolean hasAudio = (boolean) params.get("hasAudio");
		boolean hasVideo = (boolean) params.get("hasVideo");

		String currentWorkingDir = System.getProperty("user.dir");
		System.out.println("Current working directory : " + currentWorkingDir);

		RecordingProperties properties = new RecordingProperties.Builder()
				.hasAudio(hasAudio)
				.hasVideo(hasVideo)
				.outputMode(outputMode).build();

		System.out.println("Starting recording for session " + sessionId + " with properties {outputMode=" + outputMode
				+ ", hasAudio=" + hasAudio + ", hasVideo=" + hasVideo + "}");
		try {
			Recording recording = this.openVidu.startRecording(sessionId, properties);
			this.sessionRecordings.put(sessionId, true);

			return new ResponseEntity<>(recording, HttpStatus.OK);
		} catch (OpenViduJavaClientException | OpenViduHttpException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

//	@RequestMapping(value = "/recording/stop", method = RequestMethod.POST)
//	public ResponseEntity<?> stopRecording(@RequestBody Map<String, Object> params) throws IOException {
//		String recordingId = (String) params.get("recording");
//		String connectionId= (String) params.get("connectionId");
//		String coupleId=(String) params.get("coupleId");
////		System.out.println("Stoping recording | {recordingId}=" + recordingId);
////		System.out.println("Stoping recording | {connectionId}=" + connectionId);
////		System.out.println("Stoping recording | {coupleId}=" + coupleId);
//
//		try {
//			Recording recording = this.openVidu.stopRecording(recordingId);
//			System.out.println("stop recording - url : "+recording.getUrl());
//
//			String sessionId=recording.getSessionId();
//			System.out.println("stop recording - sessionId : "+sessionId);
//
////			//unzip
////			//C:\SSAFY
//			//지금은 다운로드 위치를 C:\SSAFY로 고정
//			//상대경로를 사용할 수 없기때문에 C:\SSAFY
//			String zipPath= "C:"+File.separator+"SSAFY"+File.separator+"testVideo"+File.separator+sessionId+File.separator+sessionId+".zip";
//			System.out.println("이건 zip파일 위치!"+zipPath);
//			File zipFile=new File(zipPath);
//			//압축 해제한 위치
//			String upzipDir="C:"+File.separator+"SSAFY"+File.separator+"testVideo"+File.separator+sessionId+File.separator;
//
//			ZipInputStream zis = new ZipInputStream(new FileInputStream(zipPath));
//			ZipEntry ze = zis.getNextEntry();
//
//			while (ze != null) {
//				String entryName = ze.getName();
//				if (entryName.endsWith(".webm")) { // .mp4 확장자 파일만 처리
//					System.out.print("Extracting " + entryName + " -> " + upzipDir + File.separator +  entryName + "...");
//					File f=new File(".."+File.separator+"myVideo"+File.separator+entryName);
////					File f = new File(upzipDir + File.separator + entryName);
//					f.getParentFile().mkdirs(); // 필요한 경우 디렉토리 생성
//
//					FileOutputStream fos = new FileOutputStream(f);
//					int len;
//					byte buffer[] = new byte[1024];
//					while ((len = zis.read(buffer)) > 0) {
//						fos.write(buffer, 0, len);
//					}
//					fos.close();
//					System.out.println("OK!");
//				} else {
//					System.out.println(entryName + "는 .webm 확장자가 아니므로 건너뜁니다.");
//				}
//
//				ze = zis.getNextEntry();
//			}
//			zis.closeEntry();
//			zis.close();
//
//			this.sessionRecordings.remove(recording.getSessionId());
//			this.openVidu.deleteRecording(recordingId);
//			return new ResponseEntity<>(recordingId, HttpStatus.OK);
//		} catch (OpenViduJavaClientException | OpenViduHttpException e) {
//			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//		}
//    }
@RequestMapping(value = "/recording/stop", method = RequestMethod.POST)
public ResponseEntity<?> stopRecording(@RequestBody Map<String, Object> params) {
	String recordingId = (String) params.get("recording");
	System.out.println("Stopping recording | {recordingId}=" + recordingId);
	int coupleListId=Integer.parseInt((String) params.get("coupleListId"));
	try {
		Recording recording = this.openVidu.stopRecording(recordingId);
		System.out.println("stop recording - url : " + recording.getUrl());

		String sessionId = recording.getSessionId();
		System.out.println("stop recording - sessionId : " + sessionId);

		//압축파일 경로 -> 도커에서 지정해준 위치로 바꿀것
//		String zipPath = "C:" + File.separator + "SSAFY" + File.separator + "testVideo" + File.separator + sessionId + File.separator + sessionId + ".zip";
		String zipPath = File.separator + "opt" + File.separator + "openvidu" + File.separator + "recordings" + File.separator + sessionId + File.separator + sessionId + ".zip";;

		//압축파일 하나씩 읽음
		try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipPath))) {
			ZipEntry ze = zis.getNextEntry();

			while (ze != null) {
				String entryName = ze.getName();
				if (entryName.endsWith(".webm")) {	//webm파일만 확인함
					//파일을 메모리에 임시 저장
					try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
						byte[] buffer = new byte[1024];
						int len;
						while ((len = zis.read(buffer)) > 0) {
							baos.write(buffer, 0, len);
						}
						byte[] fileContent = baos.toByteArray();

						//S3업로드를 위한 메타데이터 만들기
						String fileKey = sessionId + "/" + entryName;
						ObjectMetadata metadata = new ObjectMetadata();
						metadata.setContentLength(fileContent.length);
						metadata.setContentType("video/webm");

						try (InputStream inputStream = new ByteArrayInputStream(fileContent)) {
							//S3에 파일 업로드하기
							amazonS3.putObject(new PutObjectRequest(bucketName, fileKey, inputStream, metadata)
									.withCannedAcl(CannedAccessControlList.PublicRead));

							String fileUrl = amazonS3.getUrl(bucketName, fileKey).toString();

							// 파일 URL과 coupleListId를 Gallery 테이블에 저장
							Gallery gallery = new Gallery();
							gallery.setPathUrl(fileUrl);
							gallery.setCoupleListId(coupleListId);
							galleryRepository.save(gallery);
						}
					}
				} else {
					System.out.println(entryName + " webm파일이 아닙니다");
				}
				ze = zis.getNextEntry();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing the zip file");
		}
		this.sessionRecordings.remove(sessionId);
//		this.openVidu.deleteRecording(recordingId);
		return ResponseEntity.ok(recordingId);
	} catch (OpenViduJavaClientException | OpenViduHttpException e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}
}
	private void uploadToS3(String fileName, byte[] fileContent) throws IOException {
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(fileContent.length);
		metadata.setContentType("video/webm");
		InputStream inputStream = new ByteArrayInputStream(fileContent);

		amazonS3.putObject(new PutObjectRequest(bucketName, fileName, inputStream, metadata)
				.withCannedAcl(CannedAccessControlList.PublicRead));
		System.out.println("Uploaded " + fileName + " to S3 bucket " + bucketName);
	}

	@RequestMapping(value = "/recording/delete", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteRecording(@RequestBody Map<String, Object> params) {
		String recordingId = (String) params.get("recording");

		System.out.println("Deleting recording | {recordingId}=" + recordingId);

		try {
			this.openVidu.deleteRecording(recordingId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (OpenViduJavaClientException | OpenViduHttpException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/recording/get/{recordingId}", method = RequestMethod.GET)
	public ResponseEntity<?> getRecording(@PathVariable(value = "recordingId") String recordingId) {

		System.out.println("Getting recording | {recordingId}=" + recordingId);

		try {
			Recording recording = this.openVidu.getRecording(recordingId);
			return new ResponseEntity<>(recording, HttpStatus.OK);
		} catch (OpenViduJavaClientException | OpenViduHttpException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/recording/list", method = RequestMethod.GET)
	public ResponseEntity<?> listRecordings() {

		System.out.println("Listing recordings");

		try {
			List<Recording> recordings = this.openVidu.listRecordings();

			return new ResponseEntity<>(recordings, HttpStatus.OK);
		} catch (OpenViduJavaClientException | OpenViduHttpException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	private ResponseEntity<JsonObject> getErrorResponse(Exception e) {
		JsonObject json = new JsonObject();
		json.addProperty("cause", e.getCause().toString());
		json.addProperty("error", e.getMessage());
		json.addProperty("exception", e.getClass().getCanonicalName());
		return new ResponseEntity<>(json, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	protected JsonObject sessionToJson(Session session) {
		Gson gson = new Gson();
		JsonObject json = new JsonObject();
		json.addProperty("sessionId", session.getSessionId());
		json.addProperty("customSessionId", session.getProperties().customSessionId());
		json.addProperty("recording", session.isBeingRecorded());
		json.addProperty("mediaMode", session.getProperties().mediaMode().name());
		json.addProperty("recordingMode", session.getProperties().recordingMode().name());
		json.add("defaultRecordingProperties",
				gson.toJsonTree(session.getProperties().defaultRecordingProperties()).getAsJsonObject());
		JsonObject connections = new JsonObject();
		connections.addProperty("numberOfElements", session.getConnections().size());
		JsonArray jsonArrayConnections = new JsonArray();
		session.getConnections().forEach(con -> {
			JsonObject c = new JsonObject();
			c.addProperty("connectionId", con.getConnectionId());
			c.addProperty("role", con.getRole().name());
			c.addProperty("token", con.getToken());
			c.addProperty("clientData", con.getClientData());
			c.addProperty("serverData", con.getServerData());
			JsonArray pubs = new JsonArray();
			con.getPublishers().forEach(p -> {
				pubs.add(gson.toJsonTree(p).getAsJsonObject());
			});
			JsonArray subs = new JsonArray();
			con.getSubscribers().forEach(s -> {
				subs.add(s);
			});
			c.add("publishers", pubs);
			c.add("subscribers", subs);
			jsonArrayConnections.add(c);
		});
		connections.add("content", jsonArrayConnections);
		json.add("connections", connections);
		return json;
	}

}
