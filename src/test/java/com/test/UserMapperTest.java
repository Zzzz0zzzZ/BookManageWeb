package com.test;

import com.book.dao.UserMapper;
import com.book.entity.User;
import com.book.utils.MybatisUtil;
import lombok.Cleanup;
import lombok.extern.java.Log;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@Log
public class UserMapperTest {

    static UserMapper userMapper;

    @BeforeAll
    static void getMapper(){
        SqlSession session = MybatisUtil.getSession(true);
        userMapper = session.getMapper(UserMapper.class);
    }

    @Test
    public void testUserMapper(){
        User user = userMapper.getUser("admin", "1234567");
        if(user != null){
            log.info("UserMapper成功查询到了用户信息!");
        }
        else {
            log.warning("UserMapper查询用户信息失败!");
        }
    }
}
