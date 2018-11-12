package com.gis.config.server.controller.test.bean;

public class CFFileKV implements java.io.Serializable  {
    private String key;
    private String value;

    public CFFileKV(){

    }

    public CFFileKV(String name,String info){
        super();
        this.key=name;
        this.value=info;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
