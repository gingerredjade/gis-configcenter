package com.gis.config.client.service.test.impl;

import com.gis.config.client.model.test.User;
import com.gis.config.client.service.test.TestInterface;
import org.springframework.stereotype.Service;

/**
 * 业务层接口实现类-测试业务
 *
 * @since 1.0.0 2018年08月18日
 * @author <a href="https://126.com">Hongyu Jiang</a>
 */
@Service
public class TestInterfaceImpl implements TestInterface {

    @Override
    public User testPutUser(User user) {
        return user;
    }

    @Override
    public User testGetUser() {
        User user = new User();
        user.setId(Long.valueOf(100));
        user.setName("jianghongyu0001");
        return user;
    }
}
