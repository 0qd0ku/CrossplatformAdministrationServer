<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Клиенты для задачи</title>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script type="text/javascript"><%@include file="../js/task/TaskForClient.js"%>
    </script>
</head>
<body>
<div>
    <table style="border: 1px solid black; border-collapse: collapse;">
        <tr>
            <td style="border: 1px solid black;">Номер:</td>
            <td style="border: 1px solid black;">Имя хоста:</td>
            <td style="border: 1px solid black;">OS:</td>
            <td style="border: 1px solid black;">OS arch:</td>
            <td style="border: 1px solid black;">Mac:</td>
            <td>&nbsp;</td>
        </tr>
        <c:forEach var="client" items="${clientlist}">
            <tr>
                <td style="border: 1px solid black;">${client.id}</td>
                <td style="border: 1px solid black;">${client.hostname}</td>
                <td style="border: 1px solid black;">${client.os.value}</td>
                <td style="border: 1px solid black;">${client.osType.value}</td>
                <td style="border: 1px solid black;">${client.macAddr}</td>
                <td style="border: 1px solid black;"><button onclick="deleteClient(${taskId}, ${client.id})">Отменить</button></td>
            </tr>
        </c:forEach>
    </table>
    <table>
        <tr>
            <td><button name="tasks" onClick='location.href="all-tasks"'>Назад</button></td>
        </tr>
    </table>
</div>
</body>
</html>