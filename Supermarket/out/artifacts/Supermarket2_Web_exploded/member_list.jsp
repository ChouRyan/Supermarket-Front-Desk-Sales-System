<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>会员管理</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
  <h2>会员列表</h2>
  <div class="mb-3">
    <a href="member_register.jsp" class="btn btn-primary">添加会员</a>
    <a href="product_add.jsp" class="btn btn-secondary">返回商品管理</a>
  </div>

  <table class="table table-striped">
    <thead>
    <tr>
      <th>会员ID</th>
      <th>用户名</th>
      <th>积分</th>
      <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${members}" var="m">
      <tr>
        <td>${m.memberID}</td>
        <td>${m.memberName}</td>
        <td>${m.points}</td>
        <td>
          <a href="member?action=edit&id=${m.memberID}" class="btn btn-sm btn-info">编辑</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>