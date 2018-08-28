package com.gis.config.client.service.test;

import com.gis.config.client.model.test.User;

/**
 * 业务层接口-测试业务
 *
 * @since 1.0.0 2018年08月18日
 * @author <a href="https://gisnci.com">Hongyu Jiang</a>
 */
public interface TestInterface {
    public User testPutUser(User user);
    public User testGetUser();
}
