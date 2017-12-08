package com.mine.myapplication.entity;

import java.util.List;

/**
 * Created by lun.zhang on 12/6/2017.
 */

public class ImgListPageEntity {

    /**
     * status : 0
     * listImgPageInfo : {"content":[{"id":1,"img_name":"123","img_size":"8k","img_url":"http://47.100.23.175:8080/static/123.jpg"},{"id":2,"img_name":"ph1310-p00279","img_size":"1325k","img_url":"http://47.100.23.175:8080/static/ph1310-p00279.jpg"}],"last":false,"totalPages":2,"totalElements":4,"size":2,"number":0,"sort":[{"direction":"ASC","property":"id","ignoreCase":false,"nullHandling":"NATIVE","descending":false,"ascending":true}],"first":true,"numberOfElements":2}
     */

    private String status;
    private ListImgPageInfoBean listImgPageInfo;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ListImgPageInfoBean getListImgPageInfo() {
        return listImgPageInfo;
    }

    public void setListImgPageInfo(ListImgPageInfoBean listImgPageInfo) {
        this.listImgPageInfo = listImgPageInfo;
    }

    public static class ListImgPageInfoBean {
        /**
         * content : [{"id":1,"img_name":"123","img_size":"8k","img_url":"http://47.100.23.175:8080/static/123.jpg"},{"id":2,"img_name":"ph1310-p00279","img_size":"1325k","img_url":"http://47.100.23.175:8080/static/ph1310-p00279.jpg"}]
         * last : false
         * totalPages : 2
         * totalElements : 4
         * size : 2
         * number : 0
         * sort : [{"direction":"ASC","property":"id","ignoreCase":false,"nullHandling":"NATIVE","descending":false,"ascending":true}]
         * first : true
         * numberOfElements : 2
         */

        private boolean last;
        private int totalPages;
        private int totalElements;
        private int size;
        private int number;
        private boolean first;
        private int numberOfElements;
        private List<ContentBean> content;
        private List<SortBean> sort;

        public boolean isLast() {
            return last;
        }

        public void setLast(boolean last) {
            this.last = last;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(int totalElements) {
            this.totalElements = totalElements;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public boolean isFirst() {
            return first;
        }

        public void setFirst(boolean first) {
            this.first = first;
        }

        public int getNumberOfElements() {
            return numberOfElements;
        }

        public void setNumberOfElements(int numberOfElements) {
            this.numberOfElements = numberOfElements;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public List<SortBean> getSort() {
            return sort;
        }

        public void setSort(List<SortBean> sort) {
            this.sort = sort;
        }

        public static class ContentBean {
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

        public static class SortBean {
            /**
             * direction : ASC
             * property : id
             * ignoreCase : false
             * nullHandling : NATIVE
             * descending : false
             * ascending : true
             */

            private String direction;
            private String property;
            private boolean ignoreCase;
            private String nullHandling;
            private boolean descending;
            private boolean ascending;

            public String getDirection() {
                return direction;
            }

            public void setDirection(String direction) {
                this.direction = direction;
            }

            public String getProperty() {
                return property;
            }

            public void setProperty(String property) {
                this.property = property;
            }

            public boolean isIgnoreCase() {
                return ignoreCase;
            }

            public void setIgnoreCase(boolean ignoreCase) {
                this.ignoreCase = ignoreCase;
            }

            public String getNullHandling() {
                return nullHandling;
            }

            public void setNullHandling(String nullHandling) {
                this.nullHandling = nullHandling;
            }

            public boolean isDescending() {
                return descending;
            }

            public void setDescending(boolean descending) {
                this.descending = descending;
            }

            public boolean isAscending() {
                return ascending;
            }

            public void setAscending(boolean ascending) {
                this.ascending = ascending;
            }
        }
    }
}
