package com.gis.config.server.controller.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.*;

/**
 * put config Controller.
 *  该类负责写入、更新配置本地配置文件的内容
 *
 * @since 1.0.0 2018年08月18日
 * @author <a href="https://gisnci.com">Hongyu Jiang</a>
 */
@RestController
@RequestMapping("/config")
//@EnableConfigurationProperties(RemoteProperties.class)
public class ConfigManageController {

    /*@Autowired
    RemoteProperties remoteProperties;

    @RequestMapping("/test")
    public void test(){
        String str = remoteProperties.getAuther();
        System.out.println(str);
    }*/
      
      /*@Value(value="classpath:mswss-146-prod.properties")
    private static Resource resource;*/

    /**
     * you can access this interface as follows
     *      http://localhost:8888/config/put?key=hadoop.ip&val=192.168.56.222
     * @param name
     * @param profile
     * @param key
     * @param value
     * @return
     */
    @RequestMapping(value = "/put", method = RequestMethod.GET)
    public static String putConfigInfo(@RequestParam(value = "name", required = true) String name,
                                       @RequestParam(value = "profile", required = true) String profile,
                                       @RequestParam(value = "key", required = true) String key,
                                       @RequestParam(value = "val", required = true) String value) throws IOException {

        /*
          ResourceUtils.getFile()方法可以获取到resources下的文件
         */
        File file = ResourceUtils.getFile("classpath:properties");
        if(file.exists()){
            File[] files = file.listFiles();
            if(files != null){
                for(File childFile:files){
                    System.out.println(childFile.getName());
                }
            }
        }

        /*StringBuilder pathRes = new StringBuilder();
        pathRes.append(File.separator);
        pathRes.append("properties");
        pathRes.append(File.separator);
        pathRes.append(name);
        pathRes.append("-");
        pathRes.append(profile);
        pathRes.append(".properties");
        org.springframework.core.io.Resource fileRource = new ClassPathResource("/properties/mswss-config-dev.txt");
        //ClassPathResource classPathResource = new ClassPathResource(pathRes.toString());
        System.out.println(((ClassPathResource) fileRource).getPath());
        InputStream is = fileRource.getInputStream();*/

        /*File file = org.springframework.util.ResourceUtils.getFile("classpath:mswss-146-prod.properties");
        InputStream is = new FileInputStream(file);
        FileOutputStream fileOutputStream = new FileOutputStream(new File("E:/1111.properties"));
        byte[] bs = new byte[is.available()];
        fileOutputStream.write(bs);
        fileOutputStream.close();*/

        /*Resource resource = new ClassPathResource("mswss-146-prod.properties");
        BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
        StringBuffer message=new StringBuffer();
        String line = null;
        while((line = br.readLine()) != null) {
            message.append(line);
        }
        String defaultString=message.toString();
        String result=defaultString.replace("\r\n", "").replaceAll(" +", "");
        System.out.println(result);*/

        return "put the config info ["+ key + "]：["+ value +"] success!";
    }

    /**
     * you can access this interface as follows
     *      http://localhost:8888/config/update?key=hadoop.ip&val=192.168.56.222
     * @param key
     * @param value
     * @return
     */
    @RequestMapping("/update")
    public String updateConfigInfo(@RequestParam(value = "key", required = true) String key,
                                @RequestParam(value = "val", required = true) String value) {



        return "update the config info ["+ key + "]：["+ value +"] success!";
    }
}
