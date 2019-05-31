package com.gis.config.server.controller.bean;

import java.util.Map;

public class CFFileInfo implements java.io.Serializable {

    private String configname;
    private Map<String,String> configinfo;

    public CFFileInfo(){

    }

    public CFFileInfo(String name,Map<String,String> info){
        super();
        this.configname=name;
        this.configinfo=info;
    }

    public String getConfigname() {
        return configname;
    }

    public void setConfigname(String configname) {
        this.configname = configname;
    }


    public Map<String, String> getConfiginfo() {
        return configinfo;
    }

    public void setConfiginfo(Map<String, String> configinfo) {
        this.configinfo = configinfo;
    }

}
