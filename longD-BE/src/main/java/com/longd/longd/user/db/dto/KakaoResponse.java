package com.longd.longd.user.db.dto;

import java.util.Map;

public class KakaoResponse implements OAuth2Response {


    private final Map<String, Object> attribute;
    private final Map<String, Object> properties;

    public KakaoResponse(Map<String, Object> attribute) {

        this.attribute = attribute;
        this.properties = (Map<String, Object>) attribute.get("properties");

    }

    @Override
    public String getProvider() {

        return "kakao";
    }

    @Override
    public String getProviderId() {

        return attribute.get("id").toString();
    }

    @Override
    public String getEmail() {

        return attribute.get("email").toString();
    }

    @Override
    public String getName() {

        return properties.get("nickname").toString();
    }

    @Override
    public Map<String, Object> getAttribute() {
        return attribute;
    }
}
