<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>库存管理</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    .low-stock {
      background-color: #fff3cd;
    }
  </style>
</head>
<body>
<div class="container mt-4">
  <h2>库存列表</h2>
  <div class="mb-3">
    <a href="product?action=list" class="btn btn-primary">商品管理</a>
    <a href="inventory?action=warning" class="btn btn-warning">库存预警</a>
  </div>

  <table class="table table-striped">
    <thead>
    <tr>
      <th>库存ID</th>
      <th>商品ID</th>
      <th>商品名称</th>
      <th>库存数量</th>
      <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${inventoryList}" var="item">
      <tr>
        <td>${item.inventoryID}</td>
        <td>${item.productID}</td>
        <td>
            <%-- 这里假设有ProductService可以获取商品名称 --%>
          <c:forEach items="${productList}" var="p">
            <c:if test="${p.productID == item.productID}">
              ${p.productName}
            </c:if>
          </c:forEach>
        </td>
        <td>${item.quantity}</td>
        <td>
          <a href="inventory?action=edit&id=${item.inventoryID}" class="btn btn-sm btn-info">编辑</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>