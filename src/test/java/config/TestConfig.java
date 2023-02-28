package config;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;


public class TestConfig {

    public static RequestSpecification requestSpecification;
    public static ResponseSpecification responseSpecification;

    @BeforeEach
    public void setup() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://poisondrop.ru")
                .setBasePath("/catalog/")
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();

        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();

        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = responseSpecification;
    }

    private static Dotenv dotenv = Dotenv.load();

    public static final String SITE_URL = dotenv.get("SITE_URL");
    public static final String COM_URL = dotenv.get("COM_URL");

}
