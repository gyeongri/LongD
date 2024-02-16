package com.longd.longd.user.db.entity;

import com.longd.longd.user.db.dto.OAuth2Response;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
public class User implements OAuth2Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = null;

    private Integer coupleListId = null;
    private String userId;
    private String email;
    private String provider;
    private String name;
    private String nickname;
    private String address;
    private String birth;
    private String birthYear;
    private String birthMonth;
    private String birthDay;
    private String gender;
    private String profilePicture;
    private String profileMessage;
    private String position;
    private Integer passwordSimple;  //보안처리 필요
    private String state_message;
    private Integer code;
    //private String accessToken;
    //private String refreshToken;


    @Override
    public String getProviderId() {
        return getUserId();
    }

    @Override
    public Map<String, Object> getAttribute() {
        return null;
    }
}
