package pruebas;

import com.jayway.restassured.RestAssured;
import org.junit.Before;

public class FunctionalTest {

    @Before
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/api/";
    }


}