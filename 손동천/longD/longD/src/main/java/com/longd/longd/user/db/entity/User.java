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
    private String userId = "NULL방지";
    private String email = "NULL방지";
    private String provider = "NULL방지";
    private String name = "NULL방지";
    private String nickname = "NULL방지";
    private String addressNation = "NULL방지";
    private String addressCity;
    private String addressDetail;
    private String birth = "NULL방지";
    private String birthYear = "NULL방지";
    private String birthMonth = "NULL방지";
    private String birthDay = "NULL방지";
    private String gender = "NULL방지";
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
