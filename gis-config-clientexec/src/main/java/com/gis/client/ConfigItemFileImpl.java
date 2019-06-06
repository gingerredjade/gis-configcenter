package com.gis.client;

import com.gis.utils.ReadProperties;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigItemFileImpl {

    private static String CONFIGSTORE_FILE_NAME = BuildConfigFile.CONFIGSTORE_FILE_NAME;
    private static String TOMCAT_DIR_KEY = "tomcat.dir";
    private static String TOMCAT_DIR = "";

    static {
        TOMCAT_DIR = ReadProperties.getKey(TOMCAT_DIR_KEY, CONFIGSTORE_FILE_NAME);
        if( !TOMCAT_DIR.endsWith("/") ) {
            TOMCAT_DIR = TOMCAT_DIR + File.separator;
        }
    }

    private final static String FILEPATH_PROXY4J = TOMCAT_DIR + "webapps/mswss/WEB-INF/config/proxy4j/grid_local.xml";
    private final static String FILEPATH_HBASE = TOMCAT_DIR + "webapps/mswss/WEB-INF/config/gridweb/store/hbase/hbase-config.xml";
    private final static String FILEPATH_HADOOP = TOMCAT_DIR + "webapps/mswss/WEB-INF/config/gridweb/store/hdfs/hdfs-config.xml";
    private final static String FILEPATH_REDIS = TOMCAT_DIR + "webapps/mswss/WEB-INF/config/gridweb/store/rediscache/rediscache-config.xml";
    private final static String FILEPATH_CATALOG = TOMCAT_DIR + "webapps/mswss/WEB-INF/config/services/catalog/catalog-config.xml";
    private final static String FILEPATH_QUERY = TOMCAT_DIR + "webapps/mswss/WEB-INF/config/services/geoquery/query-config.xml";
    public final static String Attribute = "attribute";
    public final static String Node = "node";

    public static Map<String, List<String[]>> CONFIGITEM_FILE_MAP = new HashMap<String, List<String[]>>(){
        {
            //grid_local
            List<String[]> listproxy4j = new ArrayList<String[]>();
            listproxy4j.add(buildMap("/application/app-params/param[@name='noAppServers']", Attribute, "proxy4j.noAppServers"));
            listproxy4j.add(buildMap("/application/app-params/param[@name='proxy.request.reqCount']", Attribute, "proxy4j.proxy.request.reqCount"));
            listproxy4j.add(buildMap("/application/app-params/param[@name='zkEnsembleAddress']", Attribute, "proxy4j.zkEnsembleAddress"));
            put(FILEPATH_PROXY4J, listproxy4j);
            //hbase
            List<String[]> listhbase = new ArrayList<String[]>();
            listhbase.add(buildMap("/configuration/hbase_rootdir", Node, "hbase.hbase_rootdir"));
            listhbase.add(buildMap("/configuration/hbase_zookeeper_quorum", Node, "hbase.hbase_zookeeper_quorum"));
            listhbase.add(buildMap("/configuration/hbase_zookeeper_property_clientPort", Node, "hbase.hbase_zookeeper_property_clientPort"));
            put(FILEPATH_HBASE, listhbase);
            //hadoop
            List<String[]> listhadoop = new ArrayList<String[]>();
            listhadoop.add(buildMap("/configuration/hadoop-server/param[@name='dfs.namenodeHttpAddress']", Attribute, "hadoop.dfs.namenodeHttpAddress"));
            listhadoop.add(buildMap("/configuration/hadoop-server/param[@name='fs.defaultFS']", Attribute, "hadoop.fs.defaultFS"));
            listhadoop.add(buildMap("/configuration/hadoop-server/param[@name='permissionUser']", Attribute, "hadoop.permissionUser"));
            put(FILEPATH_HADOOP, listhadoop);
            //redis
            List<String[]> listredis = new ArrayList<String[]>();
            listredis.add(buildMap("/configuration/redis-server/param[@name='ip']", Attribute,"redis.server.ip"));
            listredis.add( buildMap("/configuration/redis-server/param[@name='port']", Attribute,"redis.server.port"));
            listredis.add(buildMap("/configuration/redis-server/param[@name='requirepass']", Attribute,"redis.server.requirepass"));
            put(FILEPATH_REDIS, listredis);
            //catalog
            List<String[]> listcatalog = new ArrayList<String[]>();
            listcatalog.add(buildMap("/configuration/mdProvider", Node, "catalog.mdProvider"));
            listcatalog.add( buildMap("/configuration/isCacheMDInfo", Node, "catalog.isCacheMDInfo"));
            put(FILEPATH_CATALOG, listcatalog);
            //query
            List<String[]> listquery = new ArrayList<String[]>();
            listquery.add(buildMap("/configuration/param[@name='clusterName']", Attribute,  "query.clusterName"));
            listquery.add(buildMap("/configuration/param[@name='esIP']", Attribute, "query.esIP"));
            listquery.add(buildMap("/configuration/param[@name='transDataPort']", Attribute, "query.transDataPort"));
            put(FILEPATH_QUERY, listquery);
        }
    };

    private static String[] buildMap(String nodepath, String type, String key) {
        String[] info = new String[3];
        info[0] = nodepath;
        info[1] = type;
        info[2] = key;
        return info;
    }
}
