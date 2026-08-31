<%--
  Created by IntelliJ IDEA.
  User: Ryan Chou
  Date: 2025/6/24
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>超市管理系统</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .card {
            margin: 20px;
            padding: 20px;
            text-align: center;
        }
        .card-title {
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
<div class="container mt-4">
    <h2 class="mb-4">超市管理系统</h2>

    <%-- 显示当前用户 --%>
    <c:if test="${not empty user}">
        <div class="alert alert-success">
            欢迎, ${user.memberName}!
            <a href="logout" class="btn btn-sm btn-secondary">退出</a>
        </div>
    </c:if>

    <%-- 导航卡片 --%>
    <div class="row">
        <div class="col-md-3">
            <div class="card bg-light">
                <div class="card-body">
                    <h5 class="card-title">商品管理</h5>
                    <a href="product_list.jsp" class="btn btn-primary">进入</a>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="card bg-light">
                <div class="card-body">
                    <h5 class="card-title">销售管理</h5>
                    <a href="sales_list.jsp" class="btn btn-success">进入</a>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="card bg-light">
                <div class="card-body">
                    <h5 class="card-title">库存管理</h5>
                    <a href="inventory_list.jsp" class="btn btn-warning">进入</a>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="card bg-light">
                <div class="card-body">
                    <h5 class="card-title">会员管理</h5>
                    <a href="member_list.jsp" class="btn btn-info">进入</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
