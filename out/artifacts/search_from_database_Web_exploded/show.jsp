<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="./css/show.css">
    <title>搜索结果</title>
</head>
<body>
<h1>以下是关键词“${keyword}”的搜索结果</h1>
<table class="responsive-table">
    <tr>
        <td>学号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>性别</td>
        <td>专业</td>
    </tr>
    <c:forEach var="row" items="${dataList}">
        <tr>
            <td><c:out value="${row.SID}"/></td>
            <td><c:out value="${row.name}"/></td>
            <td><c:out value="${row.age}"/></td>
            <td><c:out value="${row.gender}"/></td>
            <td><c:out value="${row.major}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>