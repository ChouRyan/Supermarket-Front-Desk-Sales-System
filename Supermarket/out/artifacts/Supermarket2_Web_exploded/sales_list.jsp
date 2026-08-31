<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
  <title>销售记录</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
  <h2>销售记录</h2>
  <div class="mb-3">
    <a href="sales?action=create" class="btn btn-primary">新建销售</a>
    <a href="product?action=list" class="btn btn-secondary">商品管理</a>
  </div>

  <table class="table table-striped">
    <thead>
    <tr>
      <th>销售ID</th>
      <th>商品ID</th>
      <th>商品名称</th>
      <th>会员ID</th>
      <th>会员名</th>
      <th>数量</th>
      <th>日期</th>
      <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${salesRecords}" var="record">
      <tr>
        <td>${record.salesID}</td>
        <td>${record.productID}</td>
        <td>
            <%-- 这里假设有ProductService可以获取商品名称 --%>
          <c:forEach items="${productList}" var="p">
            <c:if test="${p.productID == record.productID}">
              ${p.productName}
            </c:if>
          </c:forEach>
        </td>
        <td>${record.memberID}</td>
        <td>
            <%-- 这里假设有MemberService可以获取会员名 --%>
          <c:forEach items="${memberList}" var="m">
            <c:if test="${m.memberID == record.memberID}">
              ${m.memberName}
            </c:if>
          </c:forEach>
        </td>
        <td>${record.quantity}</td>
        <td><fmt:formatDate value="${record.saleDate}" pattern="yyyy-MM-dd"/></td>
        <td>
          <a href="sales?action=return&id=${record.salesID}" class="btn btn-sm btn-warning">退货</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>