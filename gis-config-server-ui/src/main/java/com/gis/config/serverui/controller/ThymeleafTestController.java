package com.gis.config.serverui.controller;

import com.alibaba.fastjson.JSON;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * ThymeleafTestController
 *
 * @since 1.0.0 2018年08月18日
 * @author <a href="https://126.com">Hongyu Jiang</a>
 */
@RestController
@Controller
@CrossOrigin
@RequestMapping("/info")
public class ThymeleafTestController {


    @Value("${eureka.client.serviceUrl.defaultZone}")
    private String filePath;

    @RequestMapping(method = RequestMethod.GET ,value = "/eurekaip")
    public  String getEurekaip(){
        return filePath;
    }

    @RequestMapping(method = RequestMethod.GET ,value = "/list")
    public String page3(){
        String applist = getList();
        List<Map<String,String>> map = new ArrayList<>();
        try {
            Document document = DocumentHelper.parseText(applist);
            Element elem = document.getRootElement();
            List element = elem.selectNodes("//application/instance/metadata");
            Iterator iter = element.iterator();
            while(iter.hasNext())
            {
                Element ele1 = (Element)iter.next();
                List<Element> nodes = ele1.elements();
                String svcname = "";
                Map<String,String> tempmap = new HashMap<>();
                for (Element e:nodes){
                    if (e.getName()=="name"){
                        svcname = e.getTextTrim();
                    }
                    if (e.getName()=="management.port"){
                        tempmap.put(e.getName().replace(".",""),e.getTextTrim());
                    }else {
                        tempmap.put(e.getName(),e.getTextTrim());
                    }

                    System.out.println(e.getName());
                    System.out.println(e.getTextTrim());
                }
                if (svcname == ""){
                    continue;
                }
                map.add(tempmap);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(map);
    }

    private String getList(){
        String urlNameString= filePath+"apps";
        String result = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        BufferedReader in = null;
        try {
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("content-type","text/html;charset=UTF-8");
            connection.setConnectTimeout(5000);
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
            String line;
            while((line = in.readLine())!=null)
            {
                result +=line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try{
                if(in!=null)
                {
                    in.close();
                }
            }
            catch(Exception e1)
            {
                e1.printStackTrace();
            }
        }
        return result;
    }
}



