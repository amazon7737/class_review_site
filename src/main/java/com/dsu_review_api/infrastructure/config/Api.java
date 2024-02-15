package com.dsu_review_api.infrastructure.config;

import java.util.List;

/* Api Util? */
public class Api {
    private String status;

    private String server;

    private List<String> data;

    public String getStatus(){
        return status;
    }

    public String getServer(){
        return server;
    }

    public  List<String> getData(){
        return data;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public void setServer(String server){
        this.server = server;
    }

    public void setData(List<String> data){
        this.data = data;
    }


}
