package com.gis.config.server.controller;

import com.gis.config.server.controller.Exception.GisConfigException;
import com.gis.config.server.controller.bean.CFFileInfo;
import com.gis.config.server.controller.bean.CFFileKV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

/**
 * put config Controller.
 *  该类负责写入、更新配置本地配置文件的内容
 *
 * @since 1.0.0 2018年08月18日
 * @author <a href="https://126.com">Hongyu Jiang</a>
 */
@RestController
@RequestMapping("/config")
@CrossOrigin
//@EnableConfigurationProperties(RemoteProperties.class)
public class ConfigManageController {

    private static final Logger logger = LoggerFactory.getLogger(ConfigManageController.class);

    @Value("${spring.cloud.config.server.native.search-locations}")
    private String filePath;

    /**
     *  通过配置文件名称，创建配置文件
     * @param key  配置文件名称
     * @return 成功或者失败标识
     */
    @RequestMapping(method = RequestMethod.GET ,value = "/addfile")
    public Boolean addConfigFile(@RequestParam(value = "key", required = true) String key){
        File file = null;
        try {
            file = ResourceUtils.getFile(filePath);
        } catch (FileNotFoundException e) {
            logger.info(GisConfigException.intFilePathError+":"+GisConfigException.strFilePathError);
        }
        if(file.exists()){
            File[] files = file.listFiles();
            if(files != null){
                for(File childFile:files){
                    if(childFile.getName().equals(key)){
                        logger.info(GisConfigException.intExistFileError+":"+GisConfigException.strExistFileError);
                        return false;
                    }
                }
            }
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath+"/"+"test"+".properties"));
            OutputStreamWriter outwriter = new OutputStreamWriter(fileOutputStream,"UTF-8");
            BufferedWriter bugwriter = new BufferedWriter(outwriter);
            //TODO 写入配置信息
//                            bugwriter.write("1.sfouwuwr=1"+"\r\n");
//                            bugwriter.write("2"+"\r\n");
//                            bugwriter.write("3"+"\r\n");
//                            bugwriter.write("4"+"\r\n");
//                            bugwriter.write("5"+"\r\n");
            bugwriter.close();
            outwriter.close();
            fileOutputStream.close();
        } catch (IOException e) {
            logger.info(GisConfigException.intBuildFileError+":"+GisConfigException.strBuildFileError);
            return false;
        }
        return true;

    }

    /**
     *  通过配置文件名称，删除配置文件
     * @param key 配置文件名称
     * @return 删除成功或者失败标识
     */
    @RequestMapping(method = RequestMethod.GET,value = "/removefile")
    public Boolean removeConfigFile(@RequestParam(value = "key",required = true)String key){
        File file = null;
        try {
            file = ResourceUtils.getFile(filePath);
        } catch (FileNotFoundException e) {
            logger.info(GisConfigException.intFilePathError+":"+GisConfigException.strFilePathError);
            return false;
        }
        if(file.exists()){
            File[] files = file.listFiles();
            if(files != null){
                for(File childFile:files){
                    if(childFile.getName().equals(key)){
                        childFile.delete();
                        return true;
                    }
                }
            }
        }
        logger.info(GisConfigException.intNonentityFileError+":"+GisConfigException.strNonentityFileError);
        return false;
    }

    /**
     *  获取全部的配置文件名称
     * @return 配置文件名称列表
     */
    @RequestMapping(method = RequestMethod.GET,value = "/getfilename")
    public List  getConfigFileName(){

        System.out.println("########################***************************%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("########################***************************%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("########################***************************%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("########################***************************%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");


        File file = null;
        List list = new ArrayList();
        try {
            file = ResourceUtils.getFile(filePath);
        } catch (FileNotFoundException e) {
            logger.info(GisConfigException.intFilePathError+":"+GisConfigException.strFilePathError);
        }
        if(file.exists()){
            File[] files = file.listFiles();
            if(files != null){
                for(File childFile:files){
                        list.add(childFile.getName());
                }
            }
        }
        return list;
    }

    /**
     *  通过配置文件名称，更新配置文件
     * @param fileinfo
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/updatafile")
    @ResponseBody
    public Boolean updateConfigInfo(CFFileInfo fileinfo) {
        Map<String,String> tempmap = fileinfo.getConfiginfo();
        File file = null;
        try {
            file = ResourceUtils.getFile(filePath);
        } catch (FileNotFoundException e) {
            logger.info(GisConfigException.intFilePathError+":"+GisConfigException.strFilePathError);
        }
        //检测配置文件是否存在
        Boolean isexistfile = false;
        if(file.exists()){
            File[] files = file.listFiles();
            if(files != null){
                for(File childFile:files){
                    if(childFile.getName().equals(fileinfo.getConfigname())){
                        isexistfile= true;
                    }
                }
            }
        }
        if (isexistfile){
            //写入配置信息
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath+"/"+fileinfo.getConfigname()));
                OutputStreamWriter outwriter = new OutputStreamWriter(fileOutputStream,"UTF-8");
                BufferedWriter bugwriter = new BufferedWriter(outwriter);
                for (Entry<String,String> entry :tempmap.entrySet() ){
                    bugwriter.write(entry.getKey()+"="+entry.getValue()+"\r\n");
                }
                bugwriter.close();
                outwriter.close();
                fileOutputStream.close();
                return true;
            } catch (IOException e) {
                logger.info(GisConfigException.intUpdataFileError+":"+GisConfigException.strUpdataFileError);
                return false;
            }
        }else {
            logger.info(GisConfigException.intUpdataFileError+":"+GisConfigException.strUpdataFileError);
            return false;
        }
    }

    @RequestMapping(method = RequestMethod.GET,value = "/getfileinfo")
    public List getConfigInfo(@RequestParam(value = "key", required = true) String filename){
        File file = null;
        try {
            file = ResourceUtils.getFile(filePath);
        } catch (FileNotFoundException e) {
            logger.info(GisConfigException.intFilePathError+":"+GisConfigException.strFilePathError);
        }
        List<CFFileKV> list = new ArrayList<>();
        if(file.exists()){
            File[] files = file.listFiles();
            if(files != null){
                for(File childFile:files){
                    if (childFile.getName().equals(filename)){
                        try {
                            File refile = new File(filePath+"/"+filename);
                            FileReader fileReader = new FileReader(refile);
                            Properties properties = new Properties();
                            properties.load(fileReader);
                            //LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
                            Set<Entry<Object, Object>> entrys = properties.entrySet();
                            for (Entry<Object,Object> entry:entrys){
                                CFFileKV kv = new CFFileKV();
                                kv.setKey(entry.getKey().toString());
                                kv.setValue(entry.getValue().toString());
                                list.add(kv);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return list;
    }
}
/*        StringBuilder pathRes = new StringBuilder();
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
        InputStream is = fileRource.getInputStream();

        File file = org.springframework.util.ResourceUtils.getFile("classpath:mswss-146-prod.properties");
        InputStream is = new FileInputStream(file);
        FileOutputStream fileOutputStream = new FileOutputStream(new File("E:/1111.properties"));
        byte[] bs = new byte[is.available()];
        fileOutputStream.write(bs);
        fileOutputStream.close();

        Resource resource = new ClassPathResource("mswss-146-prod.properties");
        BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
        StringBuffer message=new StringBuffer();
        String line = null;
        while((line = br.readLine()) != null) {
            message.append(line);
        }
        String defaultString=message.toString();
        String result=defaultString.replace("\r\n", "").replaceAll(" +", "");
        System.out.println(result);*/