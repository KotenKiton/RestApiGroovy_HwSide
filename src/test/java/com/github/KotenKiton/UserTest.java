package com.github.KotenKiton;

import org.junit.jupiter.api.Test;

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

}
