package com.book.servlet;

import com.book.service.UserService;
import com.book.service.impl.UserServiceImpl;
import com.book.utils.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    UserService service;

    @Override
    public void init() throws ServletException {
        service = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Context context = new Context();
        // 如果登录失败
        if(req.getSession().getAttribute("login-failure") != null){
            context.setVariable("failure", true);
            req.getSession().removeAttribute("login-failure");
        }
        ThymeleafUtil.process("login.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取请求中的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember-me");
        // 2. 调用service, 从数据库中查询, 比较
        // 3. 若正确, 向session中添加一个user; 否则, 向session中添加登录失败标记, 重定向到login， login的get通过标记，动态展示内容
        boolean flag = service.auth(username, password, req.getSession());
        if (flag){
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().write("登录成功！");
        }
        else {
            req.getSession().setAttribute("login-failure", new Object());
            this.doGet(req, resp);
        }
    }
}
