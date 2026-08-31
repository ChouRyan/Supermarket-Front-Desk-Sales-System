<%--
  Created by IntelliJ IDEA.
  User: Ryan Chou
  Date: 2025/6/24
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>会员注册</title>
    <style>
        .register-form {
            max-width: 500px;
            margin: 50px auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="register-form">
        <h2 class="text-center mb-4">会员注册</h2>

        <%-- 显示错误信息 --%>
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <form method="post" action="member_register.jsp">
            <div class="mb-3">
                <label class="form-label">用户名</label>
                <input type="text" name="name" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">密码</label>
                <input type="password" name="password" class="form-control" required>
            </div>
            <div class="d-grid gap-2">
                <button type="submit" class="btn btn-primary">注册</button>
            </div>
        </form>

        <div class="mt-3 text-center">
            <a href="login.jsp">已有账号？立即登录</a>
        </div>
    </div>
</div>
</body>
</html>
