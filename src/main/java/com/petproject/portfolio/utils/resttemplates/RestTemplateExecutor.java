package com.petproject.portfolio.utils.resttemplates;

import com.petproject.portfolio.exception.ValidationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class RestTemplateExecutor {
    private static RestTemplate restTemplate = new RestTemplate();

    public static <T> ResponseEntity<T> exchange(PreparedRestTemplate preparedRestTemplate, Class<T> responseClazz) throws RestClientException {
        HttpEntity<Object> request = getHttpEntity(preparedRestTemplate);

        if (preparedRestTemplate.getHttpMethod() == null) {
            throw new ValidationException("Http method cant be null");
        }

        if (preparedRestTemplate.getUriVariables() == null) {
            return restTemplate
                    .exchange(preparedRestTemplate.getUrl(), preparedRestTemplate.getHttpMethod(), request, responseClazz);
        }
        return restTemplate
                .exchange(preparedRestTemplate.getUrl(), preparedRestTemplate.getHttpMethod(), request, responseClazz, preparedRestTemplate.getUriVariables());
    }

    private static HttpEntity<Object> getHttpEntity(PreparedRestTemplate preparedRestTemplate) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(preparedRestTemplate.getContentType());
        if (preparedRestTemplate.getHeaders() != null && !preparedRestTemplate.getHeaders().isEmpty()) {
            httpHeaders.addAll(preparedRestTemplate.getHeaders());
        }
        return new HttpEntity<>(preparedRestTemplate.getBody(), httpHeaders);
    }
}
