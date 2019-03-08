package com.harmonycloud.result;


public class CodeMsg {
    private String msg;

    public CodeMsg() {
    }

    public CodeMsg(String msg) {
        this.msg = msg;
    }

    /**
     * service
     */

    public static CodeMsg SERVICE_ERROR = new CodeMsg("Service error");
    public static CodeMsg PARAMETER_ERROR = new CodeMsg("Parameter error");
    public static CodeMsg OTHER_PERSON = new CodeMsg("The clinical note has been updated by another user");


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "CodeMsg{" + msg + '\'' +
                '}';
    }
}
