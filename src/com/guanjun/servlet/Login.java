package com.guanjun.servlet;

import com.guanjun.Encrypter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;

/**
 * Created by guanjun on 2016/7/13.
 */
public class Login extends DBServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("register") != null){
            resp.sendRedirect("register.jsp");
            return;
        }
        String page = "login.jsp";
        String userName = "";
        try {
            super.service(req,resp);
            userName = req.getParameter("username");
            String password = req.getParameter("password");
            String validationCode = req.getParameter("validation_code");
            if (userName == null || password == null || validationCode == null)
                return;
            if(userName.equals("") || password.equals("") || validationCode.equals(""))
                return;
            userName = new String(userName.getBytes("ISO-8859-1"), "UTF-8");
            if (!checkValidationCode(req, validationCode))
                return;
            String sql = "select user_name, password_md5 from t_users where user_name = ?";
            try{
                ResultSet rs = execSQL(sql, new Object[]{userName});
                if (rs.next() == false)
                    req.setAttribute("userError", userName + "不存在");
                else{
                    String passwordMD5 = Encrypter.md5Encrypt(password);
                    if (!rs.getString("password_md5").equals(passwordMD5))
                        req.setAttribute("passwordError", "密码不正确");
                    else
                        page = "main.jsp";
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }catch (Exception e){

        }finally {
            req.setAttribute("username", userName);
            RequestDispatcher rd = req.getRequestDispatcher(page);
            rd.forward(req, resp);
        }
    }
}