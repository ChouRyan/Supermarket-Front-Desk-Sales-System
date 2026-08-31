<%--
  Created by IntelliJ IDEA.
  User: Ryan Chou
  Date: 2025/6/24
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>登录</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    .login-form {
      max-width: 400px;
      margin: 100px auto;
      padding: 20px;
      border: 1px solid #ddd;
      border-radius: 5px;
    }
  </style>
</head>
<body>
<div class="container">
  <div class="login-form">
    <h2 class="text-center mb-4">超市管理系统登录</h2>

    <%-- 显示错误信息 --%>
    <c:if test="${not empty error}">
      <div class="alert alert-danger">${error}</div>
    </c:if>

    <form method="post" action="main.jsp">
      <div class="mb-3">
        <label class="form-label">用户名</label>
        <input type="text" name="username" class="form-control" required>
      </div>
      <div class="mb-3">
        <label class="form-label">密码</label>
        <input type="password" name="password" class="form-control" required>
      </div>
      <div class="d-grid gap-2">
        <button type="submit" class="btn btn-primary" >登录</button>
      </div>
    </form>

    <div class="mt-3 text-center">
      <a href="member_register.jsp">没有账号？立即注册</a>
    </div>
  </div>
</div>
</body>
</html>
