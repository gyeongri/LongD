package io.openvidu.recording.java;


import io.openvidu.java.client.Recording;

public class RecordUrlDto {
    Recording recording;
    String coupleId;

    public RecordUrlDto(Recording recording,String coupleId){
        super();
        this.recording=recording;
        this.coupleId=coupleId;
    }

}
