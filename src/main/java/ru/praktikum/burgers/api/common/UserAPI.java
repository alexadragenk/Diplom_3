package ru.praktikum.burgers.api.common;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserAPI extends BaseSettings {
    private static final String REGISTER_PATH = "/api/auth/register";
    private static final String LOGIN_PATH = "/api/auth/login";
    private static final String UPDATE_PATH = "/api/auth/user";

    @Step("Регистрация пользователя")
    public static ValidatableResponse register(User user) {
        return given()
                .spec(getBaseSettings())
                .body(user)
                .post(REGISTER_PATH)
                .then();
    }

    @Step("Авторизация пользователя")
    public static ValidatableResponse login(Credentials credentials) {
        return given()
                .spec(getBaseSettings())
                .body(credentials)
                .post(LOGIN_PATH)
                .then();
    }

    @Step("Удаление пользователя")
    public static ValidatableResponse delete(String token) {
        return given()
                .spec(getBaseSettings())
                .header("Authorization", token)
                .delete(UPDATE_PATH)
                .then();
    }
}
