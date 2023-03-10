package com.book.servlet.pages;

import com.book.entity.User;
import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import com.book.utils.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {

    BookService service;

    @Override
    public void init() throws ServletException {
        service = new BookServiceImpl();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        User user = (User) req.getSession().getAttribute("user");
        context.setVariable("nickname", user.getNickname());
        context.setVariable("borrow_list", service.getBorrowList());

        int student_num = service.getStudentList().size();
        int book_num = service.getAllBookListAndStatus().size();
        int borrow_num = service.getBorrowList().size();
        int aval_num = book_num - borrow_num;

        context.setVariable("student_num", student_num);
        context.setVariable("book_num", book_num);
        context.setVariable("borrow_num", borrow_num);
        context.setVariable("aval_num", aval_num);

        if(req.getSession().getAttribute("return-failure")!=null)
            context.setVariable("return_failure", true);
        ThymeleafUtil.process("index.html", context, resp.getWriter());
    }
}
