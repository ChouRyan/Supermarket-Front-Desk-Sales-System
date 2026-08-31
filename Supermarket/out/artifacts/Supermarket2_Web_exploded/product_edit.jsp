<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>编辑商品</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
  <h2>编辑商品</h2>
  <form method="post" action="product?action=update">
    <input type="hidden" name="id" value="${product.productID}">
    <div class="mb-3">
      <label class="form-label">商品名称</label>
      <input type="text" name="name" class="form-control" value="${product.productName}" required>
    </div>
    <div class="mb-3">
      <label class="form-label">价格</label>
      <input type="number" step="0.01" name="price" class="form-control" value="${product.price}" required>
    </div>
    <div class="mb-3">
      <label class="form-label">分类</label>
      <input type="text" name="category" class="form-control" value="${product.category}" required>
    </div>
    <button type="submit" class="btn btn-primary">保存修改</button>
    <a href="product?action=list" class="btn btn-secondary">返回列表</a>
  </form>
</div>
</body>
</html>