/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.manager;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author roberto.rodriguez
 */
@Service
public class AuthManager {

    @Autowired
    RestTemplate restTemplate;

    public String autoLogin(String url, String token) {
        System.out.println("Original url: " + url);

        if (url.contains("AMS")) {
            url = url.split("AMS")[0];
        }

        System.out.println("url after split: " + url);

        url += "/Front/auth/login";
        System.out.println("AuthManager:: url = " + url);

        URI uri = UriComponentsBuilder.fromHttpUrl(url).build().encode().toUri();

        Map request = new HashMap();
        request.put("token", token);

        try {
            return restTemplate.postForObject(uri, request, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
