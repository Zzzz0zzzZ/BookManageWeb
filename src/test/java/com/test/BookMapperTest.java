package com.test;

import com.book.dao.BookMapper;
import com.book.dao.UserMapper;
import com.book.entity.Book;
import com.book.entity.User;
import com.book.utils.MybatisUtil;
import lombok.extern.java.Log;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Log
public class BookMapperTest {

    static BookMapper bookMapper;

    @BeforeAll
    static void getMapper(){
        SqlSession session = MybatisUtil.getSession(true);
        bookMapper = session.getMapper(BookMapper.class);
    }

    @Test
    public void testBookMapper(){
        List<Book> book = bookMapper.getBookList();
        book.forEach(System.out::println);
    }

    @Test
    void testFormat(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(new Date());
        System.out.println(date);

        String a = "";
        System.out.println(a.equals(""));
    }

}
