package com.system.manager;
 
import java.net.URI;
import java.util.HashMap;
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
 * @author @Roberto Rodriguez :: <RobertoSoftwareEngineer@gmail.com>
 */
@Service
public class ReportManager {

    @Autowired
    RestTemplate restTemplate;

    public HashMap getReport(String url, String appIdx, Integer id, String idx, String reportType, String params) { 
        
        url = url + "/Front/"+id+"/" + idx +"/report/"+appIdx+"/"+reportType;
        
        if(params != null){
            url += "?params=" + params;
        }
           
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        URI uri =  UriComponentsBuilder.fromHttpUrl(url).build().encode().toUri();
        System.out.println("uri = " + uri.toString());
        
        ParameterizedTypeReference<HashMap<String, Object>> responseType = 
               new ParameterizedTypeReference<HashMap<String, Object>>(){};
           
        ResponseEntity<HashMap<String, Object>> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType);
                
        HashMap<String, Object> map = response.getBody();
        
        return map;
    } 
}
