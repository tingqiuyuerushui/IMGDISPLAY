package com.mine.myapplication.net;

/**
 *
 */
public class BasicResponse<T> {

    private int code;
    private String message;
    private T listImgInfo;
    private boolean error;

    public T getListImgInfo() {
        return listImgInfo;
    }

    public void setListImgInfo(T listImgInfo) {
        this.listImgInfo = listImgInfo;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
