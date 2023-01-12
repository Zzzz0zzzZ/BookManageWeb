package com.book.filter;

import com.book.entity.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class MainFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        // 获取当前url
        String url = req.getRequestURL().toString();

        // 未登录状态，只允许访问静态资源和登录页面
        if(!url.contains("/static/") && !url.endsWith("login")){
            HttpSession httpSession = req.getSession();
            User user = (User) httpSession.getAttribute("user");
            if(user == null){
                res.sendRedirect("login");
                return;
            }
        }

        chain.doFilter(req, res);
    }
}
