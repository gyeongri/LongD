package com.longd.longd.user.service;

import com.longd.longd.user.db.entity.CustomOAuth2User;
import com.longd.longd.user.db.entity.User;
import com.longd.longd.user.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    UserRepository userRepository;

    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User.getAttributes());

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        User user = new User();
        if (registrationId.equals("kakao")) {

            Map<String, Object> tmpAttribute = oAuth2User.getAttributes();
            Map<String, Object> tmpProperties = (Map<String, Object>) tmpAttribute.get("properties");
            user.setUserId(tmpAttribute.get("id").toString());
            if(userRepository.findByUserId(user.getUserId()).isPresent()) {
                //회원이 있음
                System.out.println("ddd");
            } else {
                //회원이 없음
                System.out.println("dddd");
                user.setProvider("kakao");
                user.setName(tmpProperties.get("nickname").toString());

                userRepository.save(user);
            }
            return new CustomOAuth2User(user, "ROLE_USER");

        } else if (registrationId.equals("naver")) {
            Map<String, Object> tmpAttribute = oAuth2User.getAttributes();
            Map<String, Object> tmpResponse = (Map<String, Object>) tmpAttribute.get("response");

            user.setUserId(tmpResponse.get("id").toString());
            if(userRepository.findByUserId(user.getUserId()).isPresent()) {
                //회원이 있음
            } else {
                //회원이 없음
                user.setProvider("naver");
                user.setName(tmpResponse.get("name").toString());
                user.setGender(tmpResponse.get("gender").toString());
                //전달값 예시 birthday=06-17, [0]에는 month, [1]에는 day
                String[] tmpBirth = tmpResponse.get("birthday").toString().split("-");
                user.setBirthMonth(tmpBirth[0]);
                user.setBirthDay(tmpBirth[1]);
                user.setBirthYear(tmpResponse.get("birthyear").toString());
                user.setBirth(tmpResponse.get("birthyear").toString() + tmpBirth[0]+ tmpBirth[1]);

                userRepository.save(user);
            }
            return new CustomOAuth2User(user, "ROLE_USER");

        } else if (registrationId.equals("google")) {

            Map<String, Object> tmpAttribute = oAuth2User.getAttributes();
            user.setUserId(tmpAttribute.get("sub").toString());

            if(userRepository.findByUserId(user.getUserId()).isPresent()) {
                //회원이 있음
            } else {
                //회원이 없음
                user.setProvider("google");
                user.setName(tmpAttribute.get("name").toString());
                user.setProfilePicture(tmpAttribute.get("picture").toString());

                //전달값이 풀 이메일이므로 나눠서 저장하기 위해 작성
                String[] email = tmpAttribute.get("email").toString().split("@");
                user.setEmailId(email[0]);
                user.setEmailDomain(email[1]);

                userRepository.save(user);
            }
            return new CustomOAuth2User(user, "ROLE_USER");
        }
        else {

            return null;
        }

        //추후 작성
    }
}
