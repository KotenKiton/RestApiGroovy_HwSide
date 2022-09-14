package homework;

import homework.lombokModels.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.is;
import static specs.Specs.*;

public class ApiWithLombokTests {

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

    @Test
    @DisplayName("NullValue200")
    void putUpdatedAtNotNull() {

        RequestMorpheus requestMorpheus = new RequestMorpheus();
        requestMorpheus.setName("morpheus");
        requestMorpheus.setJob("zion resident");

        ResponseNameJobUpdatedAt resp = given()
                .spec(request)
                .body(requestMorpheus)
                .when()
                .put("/users/2")
                .then().spec(response)
                .extract().as(ResponseNameJobUpdatedAt.class);// Всё содержимое поместим в этот класс.

        Assertions.assertEquals(requestMorpheus.getName(), resp.getName());
        Assertions.assertEquals(requestMorpheus.getJob(), resp.getJob());
        LocalDate date = LocalDate.now();
        System.out.println(date);
        Assertions.assertTrue(resp.getUpdatedAt().contains(date.toString()));
    }

    @Test
    @DisplayName("MissingPassword400")
    void postMissingPassword() {

        RequestLogin requestLogin = new RequestLogin();
        requestLogin.setEmail("sydney@fife");

        given()
                .spec(request)
                .body(requestLogin)
                .when()
                .post("/register")
                .then().spec(missingPassword400)
                .body("error", is("Missing password"));
    }
}

