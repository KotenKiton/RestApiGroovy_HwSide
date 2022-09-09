package com.github.KotenKiton;

import com.github.KotenKiton.models.UserData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    @Test
    void singleUser(){
        Specs.request
                .when()
                .get("/users/2")
                .then()
                .log().body();
    }
    // Все использованные спеки помогают манипулировать метаданными в тесте
    // Некоторые вещи повторяются от теста к тесу и гораздо проще закинуть всё в specs
    @Test
    void ListOfUsers(){
        Specs.request
                .when()
                .get("/users?page=2")
                .then()
                .log()
                .body();
    }
    @Test
    void singleUserWithModel(){
        UserData data = Specs.request
                .when()
                .get("/users/2")
                .then()
                .spec(Specs.responseSpec)
                .log().body()
                .extract().as(UserData.class);

        assertEquals(2, data.getData().getId());
    }

}
