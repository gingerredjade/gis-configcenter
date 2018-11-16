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
    // 先使用绝对路径测试
    private final static String FILEPATH_PROXY4J = "D:\\apache-tomcat-8.0.30-xinfu\\webapps\\mswss\\WEB-INF\\config\\proxy4j\\grid_local.xml";
    private final static String FILEPATH_HBASE = "D:\\apache-tomcat-8.0.30-xinfu\\webapps\\mswss\\WEB-INF\\config\\gridweb\\store\\hbase\\hbase-config.xml";
    private final static String FILEPATH_HADOOP = "D:\\apache-tomcat-8.0.30-xinfu\\webapps\\mswss\\WEB-INF\\config\\gridweb\\store\\hdfs\\hdfs-config.xml";
    private final static String FILEPATH_REDIS = "D:\\apache-tomcat-8.0.30-xinfu\\webapps\\mswss\\WEB-INF\\config\\gridweb\\store\\rediscache\\rediscache-config.xml";
    private final static String FILEPATH_CATALOG = "D:\\apache-tomcat-8.0.30-xinfu\\webapps\\mswss\\WEB-INF\\config\\services\\catalog\\catalog-config.xml";
    private final static String FILEPATH_QUERY = "D:\\apache-tomcat-8.0.30-xinfu\\webapps\\mswss\\WEB-INF\\config\\services\\geoquery\\query-config.xml";
    public final static String Attribute = "attribute";
    public final static String Node = "node";

    public static Map<String, List<String[]>> CONFIGITEM_FILE_MAP = new HashMap<String, List<String[]>>(){
        {
            //grid_local
            List<String[]> listproxy4j = new ArrayList();
            listproxy4j.add(buildMap("/application/app-params/param[@name='noAppServers']", Attribute, "proxy4j_noAppServers"));
            listproxy4j.add(buildMap("/application/app-params/param[@name='proxy.request.reqCount']", Attribute, "proxy4j_proxyRequestReqCount"));
            listproxy4j.add(buildMap("/application/app-params/param[@name='zkEnsembleAddress']", Attribute, "proxy4j_zkEnsembleAddress"));
            put(FILEPATH_PROXY4J, listproxy4j);
            //hbase
            List<String[]> listhbase = new ArrayList();
            listhbase.add(buildMap("/configuration/hbase_rootdir", Node, "hbase_hbaseRootdir"));
            listhbase.add(buildMap("/configuration/hbase_zookeeper_quorum", Node, "hbase_zookeeperQuorum"));
            listhbase.add(buildMap("/configuration/hbase_zookeeper_property_clientPort", Node, "hbase_zookeeperPropertyClientPort"));
            put(FILEPATH_HBASE, listhbase);
            //hadoop
            List<String[]> listhadoop = new ArrayList();
            listhadoop.add(buildMap("/configuration/hadoop-server/param[@name='dfs.namenodeHttpAddress']", Attribute, "hadoop_dfsNamenodeHttpAddress"));
            listhadoop.add(buildMap("/configuration/hadoop-server/param[@name='fs.defaultFS']", Attribute, "hadoop_fsDefaultFS"));
            listhadoop.add(buildMap("/configuration/hadoop-server/param[@name='permissionUser']", Attribute, "hadoop_permissionUser"));
            put(FILEPATH_HADOOP, listhadoop);
            //redis
            List<String[]> listredis = new ArrayList();
            listredis.add(buildMap("/configuration/redis-server/param[@name='ip']", Attribute,"redis_serverIp"));
            listredis.add( buildMap("/configuration/redis-server/param[@name='port']", Attribute,"redis_serverPort"));
            listredis.add(buildMap("/configuration/redis-server/param[@name='requirepass']", Attribute,"redisServerRequirepass"));
            put(FILEPATH_REDIS, listredis);
            //catalog
            List<String[]> listcatalog = new ArrayList();
            listcatalog.add(buildMap("/configuration/mdProvider", Node, "catalog_mdProvider"));
            listcatalog.add( buildMap("/configuration/isCacheMDInfo", Node, "catalog_isCacheMDInfo"));
            put(FILEPATH_CATALOG, listcatalog);
            //query
            List<String[]> listquery = new ArrayList();
            listquery.add(buildMap("/configuration/param[@name='clusterName']", Attribute,  "query_clusterName"));
            listquery.add(buildMap("/configuration/param[@name='esIP']", Attribute, "query_esIP"));
            listquery.add(buildMap("/configuration/param[@name='transDataPort']", Attribute, "query_transDataPort"));
            put(FILEPATH_QUERY, listquery);
        }
    };
    /**
     * 构建配置项与文件级属性的对应关系
     *
     * @param nodepath 节点path
     * @param type Attribute 类型
     * @param key Attribute key
     * @return
     */
    private static String[] buildMap(String nodepath, String type, String key) {
        String[] info = new String[3];
        info[0] = nodepath;
        info[1] = type;
        info[2] = key;
        return info;
    }
}
