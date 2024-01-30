package com.longd.longd.user.db.entity;

import com.longd.longd.user.db.dto.OAuth2Response;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class User implements OAuth2Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    private int coupleListId = 0;
    private String userId = "NULL방지";
    private String emailId = "NULL방지";
    private String emailDomain = "NULL방지";
    private String password = "NULL방지";    //보안처리 필요 or 필요할까 ?
    private String provider = "NULL방지";
    private String name = "NULL방지";
    private String nickname = "NULL방지";
    private String addressNation = "NULL방지";
    private String addressCity;
    private String address_detail;
    private String birth = "NULL방지";
    private String birthYear = "NULL방지";
    private String birthMonth = "NULL방지";
    private String birthDay = "NULL방지";
    private String gender = "NULL방지";
    private String profilePicture;
    private String profileMessage;
    private String position;
    private String passwordSimple = "NULL방지";  //보안처리 필요
    //private String accessToken;
    //private String refreshToken;


    @Override
    public String getProviderId() {
        return getUserId();
    }

    @Override
    public String getEmail() {
        return getEmailId() + "@" + getEmailDomain();
    }

    @Override
    public Map<String, Object> getAttribute() {
        return null;
    }
}
