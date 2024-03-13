package com.mealtracker.exception.message;

public class ErrorMessage {

    public final static String RESOURCE_NOT_FOUND_EXCEPTION = "Resource with id %s is not found";
    public final static String ROLE_NOT_FOUND_EXCEPTION = "Role is not found";
    public final static String PRINCIPAL_NOT_FOUND_EXCEPTION = "User is not found";
    public static final String USER_NOT_FOUND_EXCEPTION = "User with e-mail %s is not found";
    public static final String JWT_TOKEN_ERROR_MESSAGE = "JWT Token Validation Error: %s";
    public static final String EMAIL_ALREADY_EXIST_MESSAGE = "Email already exists";
    public static final String MEAL_ENTRY_TIME_INCORRECT_MESSAGE = "Mail entry time is incorrect";
}
