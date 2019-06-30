package com.ragul.adsplayapi.util;

public enum HttpResponse {


    ERROR("E1000", "Unknown error occurred in operation"),
    MISSING_REQUIRED_PARAMS("E1001", "One or more required parameters are missing"),
    DB_ERROR("E1002", "Unknown error occurred in database operation"),
    NO_ENTRY_FOUND("E1003", "Empty results from database"),
    USER_ALREADY_EXISTS("E1004", "User Already Exists"),
    NO_USERS_FOUND("E1005", "No Users Found"),
    NOT_FOUND("E1006", "Not found"),
    PASSWORD_NOT_FOUND("E1007", "Password Not Match"),

    TOKEN_EXPIRED("E0498", "Access token expired"),
    TOKEN_ERROR("E0499", "Access token error"),
    UNAUTHORIZED("E0401", "Unauthorized request"),
    ROLE_IS_NOT_DEFINED("E0402", "No Role is Defined");

    private final String statusCode;
    private final String statusDescription;

    HttpResponse(String statusCode, String successDescription) {
        this.statusCode = statusCode;
        this.statusDescription = successDescription;
    }


    public String getStatusCode() {
        return statusCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

}

