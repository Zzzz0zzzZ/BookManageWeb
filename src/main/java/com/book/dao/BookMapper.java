package com.book.dao;

import com.book.entity.Borrow;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BookMapper {

    @Results({
        @Result(column = "id", property = "id"),
        @Result(column = "bid", property = "book_id"),
        @Result(column = "title", property = "book_name"),
        @Result(column = "borrow_time", property = "time"),
        @Result(column = "name", property = "student_name"),
        @Result(column = "sid", property = "student_id")
    })
    @Select("SELECT id, borrow.bid, title, borrow_time, `name`, borrow.sid FROM borrow " +
            "LEFT JOIN student ON borrow.sid = student.sid " +
            "LEFT JOIN book ON borrow.bid = book.bid")
    List<Borrow> getBorrowList();

    @Delete("delete from borrow where id = #{id}")
    int deleteOneBorrow(String id);
}
