package com.test;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.EnumTypeHandler;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.example.Application;
import org.example.entity.User;
import org.example.enums.GcType;
import org.example.mapper.UserMapper;
import org.example.mapper.UserMapper.RequestParam;
import org.example.typehandler.CustomEnumTypeHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class MybatisTest {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Autowired
    private UserMapper userMapper;

    @Test(expected = UncategorizedSQLException.class)
    public void testInsertZgcException() {
        User user = new User();
        user.setUu("user1");
        user.setType(GcType.ZGC);

        // this will throw:
        //   java.sql.SQLException: Incorrect integer value: 'ZGC' for column 'type' at row 1
        userMapper.insert(user);
    }

    @Test
    public void testEnumTypeHandler() {
        User user = new User();
        user.setUu("user1");
        user.setType(GcType.G1);

        // insert into user (uu, `type`) values ('user1', '1');
        userMapper.insert(user);

        TypeHandlerRegistry typeHandlerRegistry = sqlSessionFactory.getConfiguration().getTypeHandlerRegistry();

        TypeHandler<GcType> typeHandler = typeHandlerRegistry.getTypeHandler(GcType.class);

        // here
        assertEquals(typeHandler.getClass(), CustomEnumTypeHandler.class);

        RequestParam req = new RequestParam();
        req.setGcType(GcType.ZGC);

        // actual:   select count(*) from user where `type` = 'ZGC';
        // expected: select count(*) from user where `type` = 2;
        userMapper.selectCntByRequestParam(req);

        TypeHandler<GcType> typeHandler2 = typeHandlerRegistry.getTypeHandler(GcType.class);

        // here
        assertEquals(typeHandler2.getClass(), EnumTypeHandler.class);
    }
}
