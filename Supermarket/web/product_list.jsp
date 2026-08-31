<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>商品管理</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
  <h2>商品列表</h2>
  <div class="mb-3">
    <a href="product_add.jsp" class="btn btn-primary">添加商品</a>
    <a href="inventory_list.jsp" class="btn btn-info">库存管理</a>
    <a href="sales_list.jsp" class="btn btn-success">销售记录</a>
  </div>

  <table class="table table-striped">
    <thead>
    <tr>
      <th>商品ID</th>
      <th>名称</th>
      <th>价格</th>
      <th>分类</th>
      <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${products}" var="p">
      <tr>
        <td>${p.productID}</td>
        <td>${p.productName}</td>
        <td>¥${p.price}</td>
        <td>${p.category}</td>
        <td>
          <a href="product?action=edit&id=${p.productID}" class="btn btn-sm btn-info">编辑</a>
          <a href="product?action=delete&id=${p.productID}"
             class="btn btn-sm btn-danger"
             onclick="return confirm('确认删除？')">删除</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>