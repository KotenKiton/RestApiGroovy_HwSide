package homework;

import homework.lombokModels.RequestLogin;
import homework.lombokModels.ResponseMorpheusJobIdCreatedAt;
import homework.lombokModels.ResponseLogin;
import homework.lombokModels.RequestMorpheus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.is;
import static specs.Specs.*;

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

        RequestMorpheus requestMorpheus = new RequestMorpheus(); // Инициализируем - создаем обьект класса.
        requestMorpheus.setJob("leader"); // установили значение Body,которое мы отправляем.
        requestMorpheus.setName("morpheus");

        ResponseMorpheusJobIdCreatedAt responseMorpheus = given()
                .spec(request).body(requestMorpheus)
                .when()
                .post("/users")
                .then().spec(response201)
                .extract().as(ResponseMorpheusJobIdCreatedAt.class);

        Assertions.assertEquals(requestMorpheus.getJob(), responseMorpheus.getJob());
        Assertions.assertEquals(responseMorpheus.getName(), responseMorpheus.getName());
        Assertions.assertNotNull(responseMorpheus.getId());
    }






}

