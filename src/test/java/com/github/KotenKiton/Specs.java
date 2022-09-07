package com.github.KotenKiton;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.with;

public class Specs {

    public static RequestSpecification request = with().baseUri("https://reqres.in/");
}
