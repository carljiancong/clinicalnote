package com.harmonycloud.enums;


public enum ErrorMsgEnum {

    SERVICE_ERROR("Internal service error"),
    PARAMETER_ERROR("Parameter error"),
    OTHER_PERSON("The clinical note has been updated by another user"),
    QUERY_ERROR("query error"),
    SAVE_ERROR("save error"),
    UPDATE_ERROR("update error");

    private String message;

    ErrorMsgEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
