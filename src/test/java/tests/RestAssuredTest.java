package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class RestAssuredTest {

    public static final String PATH = "/users";

    @BeforeTest
    public void initTest() throws IOException {

        Properties properties = new Properties();
        FileInputStream input = new FileInputStream("src/test/resources/base.properties");
        properties.load(input);

        RestAssured.baseURI = properties.getProperty("base.URL");
    }

    @Test
    public void checkStatusCode() {
        Response response = RestAssured.when()
                        .get(PATH)
                    .andReturn();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void checkResponseHeader() {
        Response response = RestAssured.when()
                        .get(PATH)
                .andReturn();
        String rpContentTypeHeadere = response.getHeader("Content-type");
        Assert.assertEquals(rpContentTypeHeadere,"application/json; charset=utf-8");

    }

    @Test
    public void checkResponseBody() {
        Response response = RestAssured.when()
                        .get(PATH)
                    .andReturn();
        ResponseBody responseBody = response.getBody();
        User[] users = responseBody.as(User[].class);
        Assert.assertEquals(users.length, 10);

    }

}
