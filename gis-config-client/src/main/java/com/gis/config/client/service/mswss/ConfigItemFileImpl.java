package com.gis.config.client.service.mswss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ConfigItemFileImpl.配置变量与配置文件路径、配置属性的封装
 *
 * @since 1.0.0 2018年08月18日
 * @author <a href="https://gisnci.com">Hongyu Jiang</a>
 */
public class ConfigItemFileImpl {

    public final static Map<String, List<String>> CONFIGITEM_FILE_MAP = new HashMap<String, List<String>>();

    // 先使用绝对路径测试
    private static String FILEPATH_PROXY4J = "D:\\apache-tomcat-8.0.30-xinfu\\webapps\\mswss\\WEB-INF\\config\\proxy4j\\grid_local.xml";
    private static String FILEPATH_HBASE = "D:\\apache-tomcat-8.0.30-xinfu\\webapps\\mswss\\WEB-INF\\config\\gridweb\\store\\hbase\\hbase-config.xml";
    private static String FILEPATH_HADOOP = "D:\\apache-tomcat-8.0.30-xinfu\\webapps\\mswss\\WEB-INF\\config\\gridweb\\store\\hdfs\\hdfs-config.xml";
    private static String FILEPATH_REDIS = "D:\\apache-tomcat-8.0.30-xinfu\\webapps\\mswss\\WEB-INF\\config\\gridweb\\store\\rediscache\\rediscache-config.xml";
    private static String FILEPATH_CATALOG = "D:\\apache-tomcat-8.0.30-xinfu\\webapps\\mswss\\WEB-INF\\config\\services\\catalog\\catalog-config.xml";
    private static String FILEPATH_QUERY = "D:\\apache-tomcat-8.0.30-xinfu\\webapps\\mswss\\WEB-INF\\config\\services\\geoquery\\query-config.xml";


    /**
     * 构建配置项与文件级属性的对应关系
     *
     * @param oriFilePath 配置文件路径
     * @param block 配置文件中配置项的分块名称 如redis-server
     * @param attriFlag 配置项属性名称 如redis-server里面的ip
     * @return
     */
    private static List<String> buildMap(String oriFilePath, String block, String attriFlag) {
        List<String> list = new ArrayList<String>();
        list.add(oriFilePath);
        list.add(block);
        list.add(attriFlag);
        return list;
    }

    static {
        CONFIGITEM_FILE_MAP.put("proxy4j_noAppServers", buildMap(FILEPATH_PROXY4J, "app-params", "noAppServers"));
        CONFIGITEM_FILE_MAP.put("proxy4j_proxyRequestReqCount", buildMap(FILEPATH_PROXY4J, "app-params", "proxy.request.reqCount"));
        CONFIGITEM_FILE_MAP.put("proxy4j_zkEnsembleAddress", buildMap(FILEPATH_PROXY4J, "app-params", "zkEnsembleAddress"));

        CONFIGITEM_FILE_MAP.put("hbase_hbaseRootdir", buildMap(FILEPATH_HBASE, null, "hbase_rootdir"));
        CONFIGITEM_FILE_MAP.put("hbase_zookeeperQuorum", buildMap(FILEPATH_HBASE, null, "hbase_zookeeper_quorum"));
        CONFIGITEM_FILE_MAP.put("hbase_zookeeperPropertyClientPort", buildMap(FILEPATH_HBASE, null, "hbase_zookeeper_property_clientPort"));

        CONFIGITEM_FILE_MAP.put("hadoop_dfsNamenodeHttpAddress", buildMap(FILEPATH_HADOOP, "hadoop-server", "dfs.namenodeHttpAddress"));
        CONFIGITEM_FILE_MAP.put("hadoop_fsDefaultFS", buildMap(FILEPATH_HADOOP, "hadoop-server", "fs.defaultFS"));
        CONFIGITEM_FILE_MAP.put("hadoop_permissionUser", buildMap(FILEPATH_HADOOP, "hadoop-server", "permissionUser"));

        CONFIGITEM_FILE_MAP.put("redis_serverIp", buildMap(FILEPATH_REDIS, "redis-server","ip"));
        CONFIGITEM_FILE_MAP.put("redis_serverPort", buildMap(FILEPATH_REDIS, "redis-server","port"));
        CONFIGITEM_FILE_MAP.put("redisServerRequirepass", buildMap(FILEPATH_REDIS, "redis-server","requirepass"));

        CONFIGITEM_FILE_MAP.put("catalog_mdProvider", buildMap(FILEPATH_CATALOG, null, "mdProvider"));
        CONFIGITEM_FILE_MAP.put("catalog_isCacheMDInfo", buildMap(FILEPATH_CATALOG, null, "isCacheMDInfo"));

        CONFIGITEM_FILE_MAP.put("query_clusterName", buildMap(FILEPATH_QUERY, null,  "clusterName"));
        CONFIGITEM_FILE_MAP.put("query_esIP", buildMap(FILEPATH_QUERY, null, "esIP"));
        CONFIGITEM_FILE_MAP.put("query_transDataPort", buildMap(FILEPATH_QUERY, null, "transDataPort"));
    }


}
