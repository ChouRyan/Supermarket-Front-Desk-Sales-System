<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>库存预警</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    .low-stock {
      background-color: #fff3cd;
    }
  </style>
</head>
<body>
<div class="container mt-4">
  <h2 class="text-danger">库存预警（库存量<10）</h2>
  <div class="mb-3">
    <a href="inventory_list.jsp" class="btn btn-primary">返回库存列表</a>
  </div>

  <table class="table table-striped low-stock">
    <thead>
    <tr>
      <th>商品ID</th>
      <th>商品名称</th>
      <th>当前库存</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${lowStockItems}" var="item">
      <tr>
        <td>${item.productID}</td>
        <td>
            <%-- 这里假设有ProductService可以获取商品名称 --%>
          <c:forEach items="${productList}" var="p">
            <c:if test="${p.productID == item.productID}">
              ${p.productName}
            </c:if>
          </c:forEach>
        </td>
        <td class="text-danger">${item.quantity}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>