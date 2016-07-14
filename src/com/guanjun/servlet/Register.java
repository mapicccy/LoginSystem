package com.guanjun.servlet;

import com.guanjun.Encrypter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by guanjun on 2016/7/13.
 */
public class Register extends DBServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = null;
        if (req.getParameter("login") != null){
            resp.sendRedirect("login.jsp");
            return;
        }
        try {
            super.service(req, resp);
            System.out.print("注册");
            userName = req.getParameter("username");
            String password = req.getParameter("password");
            String email = req.getParameter("email");
            String validationCode = req.getParameter("validation_code");
            System.out.println(userName + " " + password + " " + email + " " + validationCode);
            if (userName == null || password == null || validationCode == null)
                return;
            if (userName.equals("") || password.equals("") || validationCode.equals(""))
                return;
            userName = new String(userName.getBytes("ISO-8859-1"), "UTF-8");
            req.setAttribute("page", "register.jsp");
            if (!checkValidationCode(req, validationCode))
                return;
            email = (email == null) ? "" : email;
            String passwordMD5 = Encrypter.md5Encrypt(password);
            System.out.println("注册");
            String sql = "insert into t_users (user_name, password_md5, email) values(?, ?, ?)";
            execSQL(sql, userName, passwordMD5, email);
            req.setAttribute("info", "用户注册成功！");
            System.out.println("注册");
        }catch (Exception e){
            System.out.println(e.getMessage() + "数据库插入出错");
            req.setAttribute("info", userName + "已经被占用！");
        }finally {
            RequestDispatcher rd = req.getRequestDispatcher("result.jsp");
            rd.forward(req, resp);
        }
    }
}
