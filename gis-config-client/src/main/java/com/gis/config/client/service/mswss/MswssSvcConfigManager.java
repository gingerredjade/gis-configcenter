package com.gis.config.client.service.mswss;

import com.gis.config.client.common.ConfigManager;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * MswssSvc ConfigManager.
 *
 * @since 1.0.0 2018年08月18日
 * @author <a href="https://gisnci.com">Hongyu Jiang</a>    @RefreshScope
 */

@Component
@Order(value = 1)

public class MswssSvcConfigManager implements ApplicationRunner {

    private  static Logger logger = LoggerFactory.getLogger(MswssSvcConfigManager.class);
    /*
从对应的配置中心找到文件并把属性注入到value值中
 */
    // 应用服务器配置
    @Value("${proxy4j.noAppServers}")
    private String proxy4j_noAppServers;

    @Value("${proxy4j.proxy.request.reqCount}")
    private String proxy4j_proxyRequestReqCount;

    @Value("${proxy4j.zkEnsembleAddress}")
    private String proxy4j_zkEnsembleAddress;

    // Hbase配置
    @Value("${hbase.hbase_rootdir}")
    private String hbase_hbaseRootdir;

    @Value("${hbase.hbase_zookeeper_quorum}")
    private String hbase_zookeeperQuorum;

    @Value("${hbase.hbase_zookeeper_property_clientPort}")
    private String hbase_zookeeperPropertyClientPort;

    // Hadoop配置
    @Value("${hadoop.dfs.namenodeHttpAddress}")
    private String hadoop_dfsNamenodeHttpAddress;

    @Value("${hadoop.fs.defaultFS}")
    private String hadoop_fsDefaultFS;

    @Value("${hadoop.permissionUser}")
    private String hadoop_permissionUser;

    // Redis配置
    @Value("${redis.server.ip}")
    private String redis_serverIp;

    @Value("${redis.server.port}")
    private String redis_serverPort;

    @Value("${redis.server.requirepass}")
    private String redisServerRequirepass;

    // 目录服务
    @Value("${catalog.mdProvider}")
    private String catalog_mdProvider;

    @Value("${catalog.isCacheMDInfo}")
    private String catalog_isCacheMDInfo;

    // geoquery服务
    @Value("${query.clusterName}")
    private String query_clusterName;

    @Value("${query.esIP}")
    private String query_esIP;

    @Value("${query.transDataPort}")
    private String query_transDataPort;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("开始执行修改配置文件");
        Map<String, List<String[]>> configitemFileMap = ConfigItemFileImpl.CONFIGITEM_FILE_MAP;
        for(String key : configitemFileMap.keySet()){
            logger.info("修改配置文件："+key);
            List<String[]> list  = configitemFileMap.get(key);
            try {
                Document doc = new SAXReader().read(new File(key));
                Element elm = doc.getRootElement();
                for (int i=0;i<list.size();i++){
                    String[] info = list.get(i);
                    Element node = (Element) elm.selectSingleNode(info[0]);
                    Field field = this.getClass().getDeclaredField(info[2]);
                    field.setAccessible(true);
                    if (info[1].equals(ConfigItemFileImpl.Attribute)){
                        if (field.get(this)!=null){
                            node.addAttribute("value", (String) field.get(this));
                            logger.info("配置项"+info[0]+":"+(String) field.get(this));
                        }else {
                            logger.info("配置项"+info[0]+"获取失败");
                        }
                    }
                    if (info[1].equals(ConfigItemFileImpl.Node)){
                        if (field.get(this)!=null){
                            node.setText((String) field.get(this));
                            logger.info("配置项"+info[0]+":"+(String) field.get(this));
                        }else {
                            logger.info("配置项"+info[0]+"获取失败");
                        }
                    }
                }
                FileOutputStream outputStream = new FileOutputStream(key);
                OutputFormat format = org.dom4j.io.OutputFormat.createPrettyPrint();
                format.setEncoding("UTF-8");
                XMLWriter writer = new XMLWriter(outputStream,format);
                writer.write(doc);
                writer.close();
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
    }
}
