package com.book.service;

import com.book.entity.Book;
import com.book.entity.Borrow;
import com.book.entity.Student;
import com.book.entity.User;

import java.util.List;

public interface BookService {

    List<Borrow> getBorrowList();
    boolean returnBook(String id);
    List<Book> getBookList();
    List<Student> getStudentList();
    boolean borrowBook(String sid, String bid);
}
