package com.petproject.portfolio.utils.resttemplates;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;

import java.util.Map;

@Builder
@Getter
public class PreparedRestTemplate {
    // todo add to separate dependency and upload to nexus
    private HttpMethod httpMethod;
    private String url;
    private MediaType contentType;
    private Object body;
    private MultiValueMap<String, String> headers;
    private Map<String, String> uriVariables;
}
