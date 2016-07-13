<%--
  Created by IntelliJ IDEA.
  User: guanjun
  Date: 2016/7/13
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<script type="text/javascript">
    function refresh() {
        var img = document.getElementById("img_validation_code");
        img.src = "com/guanjun/servlet/validation_code?" + Math.random();
    }

    function checkLogin() {
        var username = document.getElementById("username");
        if (username.value == "") {
            alert("必须输入用户名！");
            username.focus();
            return;
        }

        var password = document.getElementById("password");
        if (password.value == ""){
            alert("必须输入密码！");
            password.focus();
            return;
        }

        var validation_code = document.getElementById("validation_code");
        if (validation_code.value == ""){
            alert("验证码必须输入！");
            validation_code.focus();
            return;
        }
        register_form.submit();
    }
</script>
<form name="login_form" action="/scom/guanjun/servlet/login" method="post">
    用户名：
    <input type="text" id="username" value="${requestScope.username}" name="username">
    <font color="aqua">${requestScope.userError}</font>
    密码：
    <input type="password" id="password" name="password">
    <font color="aqua">${requestScope.passwordError}</font>
    验证码：
    <input type="text" id="validation_code" name="validation_code">
    <img src="/validation_code" id="img_validation_code">
    <font color="aqua">${requestScope.codeError}</font>
    <input type="button" value="刷新" onclick="refresh()">
    <input type="button" value="登录" onclick="checkLogin()">
</form>
</body>
</html>
