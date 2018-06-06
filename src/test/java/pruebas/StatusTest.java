package pruebas;

import com.jayway.restassured.http.ContentType;
import es.pruebas.models.Community;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class StatusTest  extends FunctionalTest{


    @Test
    public void checkingStatusTest() {
        given().
        when().
            get("/communities").
        then().
            assertThat().
            statusCode(200).
        and().
            contentType(ContentType.JSON);
    }

    @Test
    public void verifyNameOfCommunity() {
        given().
        when().
            get("/communities").
        then().
            assertThat().
            body(containsString("QA & Testing"));
    }

    @Test
    public void verifyNameOfCommunityQA() {
        String community_qa = "4";

        given().
           pathParam("communityId",community_qa).
        when().
                get("/communities/{communityId}").
                then().
                assertThat().
                body("name", equalTo("QA & Testing"));

    }

    @Test
    public void addCommunity() {
        Community community = new Community();
        community.setName("bbbb");
        community.setDescription("description bbbb");

        given()
                .contentType("application/json")
                .body(community)
                .when().post("/communities").then()
                .statusCode(201);
    }


}
