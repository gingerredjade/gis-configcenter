package com.gis.config.client.common;

/**
 * 配置信息更新的处理器接口
 *
 * @since 1.0.0 2018年08月18日
 * @author <a href="https://126.com">Hongyu Jiang</a>
 */
public interface ConfigManager {

    /**
     * 更新配置文件信息
     *
     * @param clientType 客户端类型
     * @throws Exception
     */
    void confUpdate(String clientType) throws Exception;
}
