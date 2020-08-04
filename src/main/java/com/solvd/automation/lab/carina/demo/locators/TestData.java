package com.solvd.automation.lab.carina.demo.locators;

public class TestData {

    public static final String LOGIN_FAILED_MESSAGE_EXPECTED = "Login failed.";
    public static final String LOGIN_ERROR_OF_EMAIL_MESSAGE_EXPECTED = "Адрес электронной почты должен содержать символ \"@\". В адресе \"sssss\" отсутствует символ \"@\".";
    public static final String LOGIN_ERROR_OF_PASSWORD_MESSAGE_EXPECTED = "Введите данные в указанном формате.";
    public static final String LOGIN_ERROR_OF_WRONG_PASSWORD_MESSAGE_EXPECTED = "Login failed."+"Reason: Wrong password.";;

    public static final String CORRECT_LOGIN = " s9rowa@mail.ru";
    public static final String INCORRECT_LOGIN = "aasg@dfgfdg.dsf";
    public static final String EXISTING_LOGIN = " s9rowa@mail.ru";
    public static final String INVALID_LOGIN = "eee@hhhhhhhhhhhhh";

    public static final String EXISTING_NICKNAME = "4333";
    public static final String SHORT_NICKNAME = "4";
    public static final String CORRECT_NICKNAME = "433322";

    public static final String CORRECT_PASSWORD ="changeme" ;
    public static final String INCORRECT_PASSWORD = "aasgihiiuiuhiuhiu";

    public static final String SIGNUP_SUCCESS_MESSAGE_EXPECTED = "Your account was created.";
    public static final String SIGNUP_EXISTING_EMAIL_MESSAGE_EXPECTED = "Reason: Email already used.";
    public static final String SIGNUP_INVALID_EMAIL_MESSAGE_EXPECTED = "Reason: You need to provide valid email. eee@hhhhhhhhhhhhh";
    public static final String SIGNUP_EXISTING_NICKNAME_MESSAGE_EXPECTED = "This nickname is already reserved.";
    public static final String SIGNUP_SHORT_NICKNAME_MESSAGE_EXPECTED = "Your nickname should have between 2 and 20 symbols.";


}
