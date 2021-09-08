package tests;

import model.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RestTempleteTest {

    @Test
    public void checkStatusCode() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User[]> response = restTemplate.getForEntity("http://jsonplaceholder.typicode.com/users", User[].class);
        int actualStatusCode = response.getStatusCode().value();
        Assert.assertEquals(actualStatusCode, 200);

    }

    @Test
    public void checkResponseHeader() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User[]> response = restTemplate.getForEntity("http://jsonplaceholder.typicode.com/users",User[].class);
        HttpHeaders headers = response.getHeaders();
        String actualContentTypeValue = headers.getContentType().toString();
        Assert.assertEquals(actualContentTypeValue, "application/json;charset=utf-8");

    }

    @Test
    public void checkResponseBody() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User[]> response = restTemplate.getForEntity("http://jsonplaceholder.typicode.com/users",User[].class);
        User[] actualUsers = response.getBody();
        Assert.assertEquals(actualUsers.length, 10);
    }

}
