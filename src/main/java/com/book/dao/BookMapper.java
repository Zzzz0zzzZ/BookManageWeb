package com.book.dao;

import com.book.entity.Book;
import com.book.entity.Borrow;
import com.book.entity.Student;
import com.book.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.Date;
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

    @Select("SELECT * FROM book WHERE bid NOT IN (SELECT bid FROM borrow)")
    List<Book> getBookList();

    @Select("select * from student")
    List<Student> getStudentList();

    @Insert("insert into borrow(sid, bid, borrow_time) values(#{sid}, #{bid}, #{borrow_time})")
    boolean addOneBorrow(@Param("sid") String sid, @Param("bid") String bid, @Param("borrow_time")String borrow_time);

    @Select("select * from book")
    List<Book> getAllBooks();

    @Delete("delete from book where bid = #{bid}")
    boolean deleteOneBook(String bid);

    @Insert("insert into book(title, `desc`, price) values(#{title}, #{desc}, #{price})")
    boolean addOneBook(Book book);

}
