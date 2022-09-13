package homework;

import homework.lombokModels.RequestLogin;
import homework.lombokModels.RequestMorpheusJobIdCreatedAt;
import homework.lombokModels.ResponseLogin;
import homework.lombokModels.ResponseMorpheus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.is;
import static specs.Specs.request;
import static specs.Specs.response;

public class Tests {

    @Test
    @DisplayName("200test")
    void postStatusCode200() {

        Integer exceptId = 4;
        String expectToken = "QpwL5tke4Pnpja7X4";

        RequestLogin body = new RequestLogin();
        body.setEmail("eve.holt@reqres.in");
        body.setPassword("pistol");

        ResponseLogin responseLogin = given()
                .spec(request).body(body)
                .when()
                .post("/register")
                .then().spec(response)
                .extract().as(ResponseLogin.class);

        Assertions.assertEquals(exceptId, responseLogin.getId());
        Assertions.assertEquals(expectToken, responseLogin.getToken());
    }

    @Test
    @DisplayName("201test")
    void postStatusCode201() {

        String exceptName = "morpheus";
        String expectJob = "leader";

        RequestMorpheusJobIdCreatedAt body = new RequestMorpheusJobIdCreatedAt();
        body.setName("morpheus");
        body.setJob("leader");
        body.setId(484);
        body.setCreatedAt("2022-09-13T09:16:11.144Z");

        ResponseMorpheus responseMorpheus = given()
                .spec(request).body(body)
                .when()
                .post("/register")
                .then().spec(response)
                .extract().as(ResponseMorpheus.class);

        Assertions.assertEquals(exceptName, responseMorpheus.getName());
        Assertions.assertEquals(expectJob, responseMorpheus.getJob());
    }

//    @Test
//    @DisplayName("201test")
//    void postStatusCode201() {
//        String body = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";
//
//        given()
//                .body(body)
//                .contentType(JSON)
//                .when()
//                .post("https://reqres.in/api/users")
//                .then()
//                .log().all()
//                .statusCode(201)
//                .body("name", is("morpheus"))
//                .body("job", is("leader"));
}
