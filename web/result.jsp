<%--
  Created by IntelliJ IDEA.
  User: guanjun
  Date: 2016/7/13
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>结果</title>
</head>
<body>
<form id="form" name="form" action="${requestScope.page}" method="post"></form>
<script type="text/javascript">
    <c:if test="${requestScope.info != null}">
    alert('${requestScope.info}');
    document.getElementById("form").submit();
    </c:if>
</script>
</body>
</html>
