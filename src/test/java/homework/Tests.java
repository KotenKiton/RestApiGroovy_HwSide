package homework;

import homework.lombokModels.RequestLogin;
import homework.lombokModels.ResponseLogin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import static specs.Specs.request;
import static specs.Specs.response;

public class Tests {

    @Test
    @DisplayName("200test")
    void postStatusCode200() {

        Integer exceptId = 4;
        String expectToken ="QpwL5tke4Pnpja7X4";

        RequestLogin body = new RequestLogin();
        body.setEmail("eve.holt@reqres.in");
        body.setPassword("pistol");

        ResponseLogin responseLogin = given()
                .spec(request).body(body)
                .when()
                .post("/register")
                .then().spec(response)
                .extract().as(ResponseLogin.class);

        Assertions.assertEquals(exceptId,responseLogin.getId());
        Assertions.assertEquals(expectToken,responseLogin.getToken());

    }


}
