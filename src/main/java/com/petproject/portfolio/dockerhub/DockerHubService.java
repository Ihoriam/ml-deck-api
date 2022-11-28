package com.petproject.portfolio.dockerhub;

import com.petproject.portfolio.utils.resttemplates.PreparedRestTemplate;
import com.petproject.portfolio.utils.resttemplates.RestTemplateExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DockerHubService {

    public DockerHubInfo getInfoAboutDockerHubImageByName(String name) {
        DockerHubInfo dockerHubInfo = new DockerHubInfo();
        dockerHubInfo.setDockerHubImageExist(isDockerHubImageExist(name));
        return dockerHubInfo;
    }

    //todo: static? maybe its not service but some helper class
    public DockerHubApiResponse getInfoAboutDockerHubRepoFromApi(String dockerHubRepoName) {
        PreparedRestTemplate preparedRestTemplate = PreparedRestTemplate.builder()
                .url("https://hub.docker.com/v2/repositories/" + dockerHubRepoName)
                .httpMethod(HttpMethod.GET)
                .build();
        try {
            ResponseEntity<DockerHubApiResponse> response = RestTemplateExecutor.exchange(preparedRestTemplate, DockerHubApiResponse.class);
            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isDockerHubImageExist(String dockerHubRepoName) {
        DockerHubApiResponse infoAboutDockerHubRepo = getInfoAboutDockerHubRepoFromApi(dockerHubRepoName);
        return infoAboutDockerHubRepo != null
                && infoAboutDockerHubRepo.getStatusDescription() != null
                && infoAboutDockerHubRepo.getStatusDescription().equals("active");

    }
}
