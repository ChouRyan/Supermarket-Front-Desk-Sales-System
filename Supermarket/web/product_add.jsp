<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>添加商品</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
  <h2>添加新商品</h2>
  <%--  ID自动生成--%>
  <form method="post" action="product_list.jsp">
    <input type="hidden" name="action" value="add">
    <div class="mb-3">
      <label class="form-label">商品名称</label>
      <input type="text" name="name" class="form-control" required>
    </div>
    <div class="mb-3">
      <label class="form-label">价格</label>
      <input type="number" step="0.01" name="price" class="form-control" required>
    </div>
    <div class="mb-3">
      <label class="form-label">分类</label>
      <input type="text" name="category" class="form-control" required>
    </div>
    <button type="submit" class="btn btn-success">保存</button>
    <a href="product?action=list" class="btn btn-secondary">返回列表</a>
  </form>
</div>
</body>
</html>