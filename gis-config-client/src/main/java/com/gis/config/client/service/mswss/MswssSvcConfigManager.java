package com.gis.config.client.service.mswss;

import com.gis.config.client.common.ConfigManager;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * MswssSvc ConfigManager.
 *
 * @since 1.0.0 2018年08月18日
 * @author <a href="https://gisnci.com">Hongyu Jiang</a>
 */
public class MswssSvcConfigManager implements ConfigManager {

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


    /**
     * 通过变量字符串获取对应的配置文件路径、属性块、属性名称信息
     *
     * @param variStr 变量字符串
     * @return
     */
    private static List<String> getConfigItemFile(String variStr) {
        List list = ConfigItemFileImpl.CONFIGITEM_FILE_MAP.get(variStr);
        return list;
    }


    /**
     * 更新MSWSS客户端的配置文件信息
     *
     * @param clientType 客户端类型
     * @throws Exception
     */
    @Override
    public void confUpdate(String clientType) throws Exception {

        // TODO.. 修改mswss的配置信息   通过变量名获取mswss配置文件的路径和配置属性进行修改 批量更改
        System.out.println("修改mswss的配置信息");

        System.out.println(getConfigItemFile("query_esIP").get(0));

        update("query_esIP");

    }

    private static boolean update(String variStr) {
        System.out.println("更新query_esIP对应的配置成功");
        return true;
    }



}
