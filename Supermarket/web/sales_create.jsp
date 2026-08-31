<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>创建销售记录</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
  <h2>创建销售记录</h2>
  <form method="post" action="sales?action=create">
    <div class="mb-3">
      <label class="form-label">商品</label>
      <select name="productId" class="form-select" required>
        <option value="">选择商品</option>
        <c:forEach items="${products}" var="p">
          <option value="${p.productID}">${p.productName} (ID: ${p.productID})</option>
        </c:forEach>
      </select>
    </div>
    <div class="mb-3">
      <label class="form-label">会员</label>
      <select name="memberId" class="form-select">
        <option value="">非会员</option>
        <c:forEach items="${members}" var="m">
          <option value="${m.memberID}">${m.memberName} (ID: ${m.memberID})</option>
        </c:forEach>
      </select>
    </div>
    <div class="mb-3">
      <label class="form-label">数量</label>
      <input type="number" name="quantity" class="form-control" min="1" required>
    </div>
    <button type="submit" class="btn btn-success">确认销售</button>
    <a href="sales?action=list" class="btn btn-secondary">返回列表</a>
  </form>
</div>
</body>
</html>