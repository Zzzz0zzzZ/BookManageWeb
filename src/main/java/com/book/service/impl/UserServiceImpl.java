package com.book.service.impl;

import com.book.dao.UserMapper;
import com.book.entity.User;
import com.book.service.UserService;
import com.book.utils.MybatisUtil;
import jakarta.servlet.http.HttpSession;
import lombok.Cleanup;
import org.apache.ibatis.session.SqlSession;

public class UserServiceImpl implements UserService {

    @Override
    public boolean auth(String username, String password, HttpSession session) {
        @Cleanup
        SqlSession sqlSession = MybatisUtil.getSession(true);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.getUser(username, password);

        if (user == null)   return false;
        session.setAttribute("user", user);
        return true;
    }
}
