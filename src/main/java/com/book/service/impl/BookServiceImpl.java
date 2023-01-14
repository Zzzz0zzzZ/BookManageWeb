package com.book.service.impl;

import com.book.dao.BookMapper;
import com.book.entity.Borrow;
import com.book.service.BookService;
import com.book.utils.MybatisUtil;
import lombok.Cleanup;
import org.apache.ibatis.session.SqlSession;

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
}
