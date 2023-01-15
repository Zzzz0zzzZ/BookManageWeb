package com.book.servlet.manage;

import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.java.Log;

import java.io.IOException;
import java.util.Timer;

@Log
@WebServlet("/borrow-book")
public class BorrowServlet extends HttpServlet {

    BookService service;

    @Override
    public void init() throws ServletException {
        service = new BookServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String bid = req.getParameter("book");
        String sid = req.getParameter("sid");
        /*
          str == null 是有必要存在的.
          如果 String 类型为null, 而去进行 equals(String) 或 length() 等操作会抛出java.lang.NullPointerException.
          并且str ==null 的顺序必须出现在前面，不然同样会抛出java.lang.NullPointerException.
        */

        if (!service.borrowBook(sid, bid))
            req.getSession().setAttribute("borrow-failure", new Object());

        resp.sendRedirect("index");
    }
}
