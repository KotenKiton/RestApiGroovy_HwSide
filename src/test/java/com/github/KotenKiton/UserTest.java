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
