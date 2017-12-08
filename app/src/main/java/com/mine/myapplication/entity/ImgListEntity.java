package com.mine.myapplication.entity;

import java.util.List;

/**
 * Created by lun.zhang on 12/6/2017.
 */

public class ImgListEntity {
    /**
     * status : 0
     * listImgInfo : [{"id":1,"img_name":"123","img_size":"8k","img_url":"http://47.100.23.175:8080/static/123.jpg"},{"id":2,"img_name":"ph1310-p00279","img_size":"1325k","img_url":"http://47.100.23.175:8080/static/ph1310-p00279.jpg"},{"id":3,"img_name":"img_home_background","img_size":"103k","img_url":"http://47.100.23.175:8080/static/img_home_background.png"},{"id":4,"img_name":"timg","img_size":"26k","img_url":"http://47.100.23.175:8080/static/timg.jpg"}]
     */

    private String status;
    private List<ListImgInfoBean> listImgInfo;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ListImgInfoBean> getListImgInfo() {
        return listImgInfo;
    }

    public void setListImgInfo(List<ListImgInfoBean> listImgInfo) {
        this.listImgInfo = listImgInfo;
    }

    public static class ListImgInfoBean {
        /**
         * id : 1
         * img_name : 123
         * img_size : 8k
         * img_url : http://47.100.23.175:8080/static/123.jpg
         */

        private int id;
        private String img_name;
        private String img_size;
        private String img_url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg_name() {
            return img_name;
        }

        public void setImg_name(String img_name) {
            this.img_name = img_name;
        }

        public String getImg_size() {
            return img_size;
        }

        public void setImg_size(String img_size) {
            this.img_size = img_size;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }
    }
}
