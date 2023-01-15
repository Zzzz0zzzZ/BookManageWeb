package com.book.service.impl;

import com.book.dao.BookMapper;
import com.book.entity.Book;
import com.book.entity.Borrow;
import com.book.entity.Student;
import com.book.entity.User;
import com.book.service.BookService;
import com.book.utils.DateFormatUtil;
import com.book.utils.MybatisUtil;
import lombok.Cleanup;
import org.apache.ibatis.session.SqlSession;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BookServiceImpl implements BookService {

    @Override
    public List<Borrow> getBorrowList() {

        @Cleanup
        SqlSession session = MybatisUtil.getSession(true);

        BookMapper mapper = session.getMapper(BookMapper.class);

        return mapper.getBorrowList();
    }

    @Override
    public boolean returnBook(String id) {

        @Cleanup
        SqlSession session = MybatisUtil.getSession(true);
        BookMapper mapper = session.getMapper(BookMapper.class);
        int res_i = mapper.deleteOneBorrow(id);
        return res_i > 0;
    }

    @Override
    public List<Book> getBookList() {

        @Cleanup
        SqlSession session = MybatisUtil.getSession(true);
        BookMapper mapper = session.getMapper(BookMapper.class);
        return mapper.getBookList();
    }

    @Override
    public List<Student> getStudentList() {
        @Cleanup
        SqlSession session = MybatisUtil.getSession(true);
        BookMapper mapper = session.getMapper(BookMapper.class);
        return mapper.getStudentList();
    }

    @Override
    public boolean borrowBook(String sid, String bid) {
        @Cleanup
        SqlSession session = MybatisUtil.getSession(true);
        BookMapper mapper = session.getMapper(BookMapper.class);
        return mapper.addOneBorrow(sid, bid, DateFormatUtil.getFormatDate(new Date()));
    }
}
