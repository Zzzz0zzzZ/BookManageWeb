package com.book.servlet.manage;

import com.book.entity.Book;
import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/add_new_book")
public class AddNewBookServlet extends HttpServlet {
    BookService service;

    @Override
    public void init() throws ServletException {
        service = new BookServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BigDecimal bigDecimal = new BigDecimal(req.getParameter("book_price"));

        Book book = new Book();
        book.setTitle(req.getParameter("book_name"));
        book.setDesc(req.getParameter("book_desc"));
        book.setPrice(bigDecimal);

        if(service.addOneBook(book))
            resp.sendRedirect("book");
    }
}
