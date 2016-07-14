<%--
  Created by IntelliJ IDEA.
  User: guanjun
  Date: 2016/7/13
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<script type="text/javascript">
    function refresh() {
        var img = document.getElementById("img_validation_code");
        img.src = "/validation_code?" + Math.random();
    }

    function checkRegister() {
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

        var repassword = document.getElementById("repassword");
        if (password.value != repassword.value){
            alert("输入的密码不一致");
            repassword.focus();
            return;
        }

        var email = document.getElementById("email");
        if (email.value != ""){
            //if (!checkEmail(email)) return;
        }

        var validation_code = document.getElementById("validation_code");
        if (validation_code.value == ""){
            alert("验证码必须输入！");
            validation_code.focus();
            return;
        }
        document.getElementById("register_form").submit();
    }
</script>
<body>
    <form name="register_form" id="register_form" action="/register" method="post">
        <span class="require">*</span> 用户名 ：
        <input type="text" id="username" name="username" size="30" maxlength="30"><br>
        <span class="require">*</span> 密码 ：
        <input type="password" id="password" name="password" size="30" maxlength="30"><br>
        <span class="require">*</span> 请再次输入密码 ：
        <input type="password" id="repassword" name="repassword" size="30" maxlength="30"><br>
        邮箱地址 ：
        <input type="text" id="email" name="email" size="30" maxlength="30"><br>
        <span class="require">*</span> 验证码 ：
        <input type="text" id="validation_code" name="validation_code" style="width: 60px;margin-top: 2px" size="30" maxlength="30">
        <img id="img_validation_code" src="/validation_code">
        <input type="button" value="刷新" onclick="refresh()"><br>
        <input type="button" value="注册" onclick="checkRegister()">
        <input type="submit" value="登录" name="login">
    </form>
</body>
</html>
