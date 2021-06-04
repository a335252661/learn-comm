<%@page contentType="text/html; UTF-8" pageEncoding="utf-8" isELIgnored="false" %>

<%--添加shiro标签--%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<h1>系统主页</h1>

<h3>你好： <shiro:principal></shiro:principal> </h3>
<br>
<shiro:authenticated>
    认证之后展示的内容
</shiro:authenticated>

<br>
<shiro:notAuthenticated>
    没有认证之后展示的内容
</shiro:notAuthenticated>


<a href="${pageContext.request.contextPath}/user/logout">用户退出</a>
<br>
<a href="${pageContext.request.contextPath}/order/save">保存订单，编程方式判断权限</a>

<br>
<a href="${pageContext.request.contextPath}/order/save2">保存订单，注解方式判断权限</a>

<br>


    <ul>
        <li>用户管理</li>
        <ul>
            <shiro:hasPermission name="user:query:*">
                <li>查询  user:query:*</li>
            </shiro:hasPermission>
            <shiro:hasPermission name="user:update:*">
                <li>修改  user:update:*</li>
            </shiro:hasPermission>
            <shiro:hasPermission name="user:delete:*">
                <li>删除  user:delete:*</li>
            </shiro:hasPermission>
            <shiro:hasPermission name="user:add:*">
                <li>添加  user:add:*</li>
            </shiro:hasPermission>

        </ul>


        <shiro:hasRole name="admin">
            <li>商品管理</li>
            <li>订单管理</li>
            <li>物流管理</li>
        </shiro:hasRole>

        <shiro:hasRole name="product">
            <li>product</li>
        </shiro:hasRole>

        <shiro:hasRole name="user">
            <li>user</li>
        </shiro:hasRole>

        <shiro:hasRole name="admin">
            <li>admin</li>
        </shiro:hasRole>

    </ul>

</body>
</html>