package tests;

import model.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class RestTempleteTest {

    @Test
    public void checkStatusCode() throws IOException {

        Properties properties = new Properties();
        FileInputStream input = new FileInputStream("src/test/resources/base.properties");
        properties.load(input);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User[]> response = restTemplate.getForEntity(properties.getProperty("base.URI"), User[].class);
        int actualStatusCode = response.getStatusCode().value();
        Assert.assertEquals(actualStatusCode, 200);

    }

    @Test
    public void checkResponseHeader() throws IOException {

        Properties properties = new Properties();
        FileInputStream input = new FileInputStream("src/test/resources/base.properties");
        properties.load(input);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User[]> response = restTemplate.getForEntity(properties.getProperty("base.URI"),User[].class);
        HttpHeaders headers = response.getHeaders();
        String actualContentTypeValue = headers.getContentType().toString();
        Assert.assertEquals(actualContentTypeValue, "application/json;charset=utf-8");

    }

    @Test
    public void checkResponseBody() throws IOException {

        Properties properties = new Properties();
        FileInputStream input = new FileInputStream("src/test/resources/base.properties");
        properties.load(input);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User[]> response = restTemplate.getForEntity(properties.getProperty("base.URI"),User[].class);
        User[] actualUsers = response.getBody();
        Assert.assertEquals(actualUsers.length, 10);
    }

}
