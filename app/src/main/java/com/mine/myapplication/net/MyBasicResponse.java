package com.mine.myapplication.net;

/**
 * Created by lun.zhang on 12/7/2017.
 */

public class MyBasicResponse<T> {
    private String status;
    private  T listImgPageInfo;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getListImgPageInfo() {
        return listImgPageInfo;
    }

    public void setListImgPageInfo(T listImgPageInfo) {
        this.listImgPageInfo = listImgPageInfo;
    }
}
