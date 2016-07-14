package com.guanjun.servlet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by guanjun on 2016/7/12.
 * 数据库访问
 */
public class DBServlet extends HttpServlet{
    protected Connection conn = null;

    //执行各种SQL语句
    protected ResultSet execSQL(String sql, Object... args) throws Exception {
        System.out.println("执行数据库插入操作前");
        PreparedStatement pStmt = conn.prepareStatement(sql);
        for (int i = 0; i < args.length; i++){
            pStmt.setObject(i + 1, args[i]);
        }
        pStmt.execute();
        System.out.println("执行数据库插入操作后");
        return pStmt.getResultSet();
    }

    //核对用户输入的验证码是否有效
    protected boolean checkValidationCode(HttpServletRequest request, String validationCode) {
        String validationCodeSession = (String) request.getSession().getAttribute("validation-code");
        if (validationCodeSession == null){
            //设置result.jsp需要的结果信息
            request.setAttribute("info", "验证码过期");

            //设置login.jsp需要的错误信息
            request.setAttribute("codeError", "验证码过期");
            return false;
        }
        if (!validationCode.equalsIgnoreCase(validationCodeSession)){
            //设置result.jsp需要的结果信息
            request.setAttribute("info", "验证码不正确");

            //设置login.jsp需要的错误信息
            request.setAttribute("codeError", "验证码不正确");
            return false;
        }
        return true;
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            System.out.println(conn + "conn建立数据库连接");
            if (conn == null){
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webdb", "zhaoguanjun", "19911156");
                Context ctx = new InitialContext();
                DataSource ds = (DataSource) ctx.lookup("jdbc:mysql://localhost:3306/webdb");
                conn = ds.getConnection();
            }
            System.out.println(conn + "conn状态");
        }catch (Exception e){
            System.out.println("conn链接出错");
        }
    }

    @Override
    public void destroy() {
        try {
            if (conn != null){
                conn.close();
            }
        }catch (Exception e){

        }
    }
}
