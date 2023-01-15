package com.book.servlet.pages;

import com.book.entity.Book;
import com.book.entity.Student;
import com.book.entity.User;
import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import com.book.utils.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.java.Log;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.util.List;

@WebServlet("/borrow")
public class BorrowServlet extends HttpServlet {

    BookService service;

    @Override
    public void init() throws ServletException {
        service = new BookServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Book> books = service.getBookList();
        List<Student> students = service.getStudentList();
        Context context = new Context();
        context.setVariable("books", books);
        context.setVariable("students", students);
        if (books.isEmpty() || students.isEmpty()){
            context.setVariable("no_borrow", true);
        }

        ThymeleafUtil.process("borrow.html", context, resp.getWriter());
    }
}
