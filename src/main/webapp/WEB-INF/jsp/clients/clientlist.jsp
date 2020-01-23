<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Управления задачами</title>
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
            <td style="border: 1px solid black;">Blocked:</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
        </tr>
        <c:forEach var="client" items="${clientlist}">
            <tr>
                <td style="border: 1px solid black;">${client.id}</td>
                <td style="border: 1px solid black;">${client.hostname}</td>
                <td style="border: 1px solid black;">${client.os.value}</td>
                <td style="border: 1px solid black;">${client.osType.value}</td>
                <td style="border: 1px solid black;">${client.macAddr}</td>
                <td style="border: 1px solid black;"><spring:checkbox path="${client.isBlocked}"/></td>
                <td style="border: 1px solid black;"><spring:form method="POST" action="delete-client"><button name="id" value="${client.id}">Удалить</button></spring:form></td>
                <td style="border: 1px solid black;"><spring:form method="GET"  action="client-tasks"><button name="clientId" value="${client.id}">Список задач</button></spring:form></td>
                <td style="border: 1px solid black;"><spring:form method="GET"  action="add-task-to-client-page"><button name="clientId" value="${client.id}">Добавить задачу</button></spring:form></td>
            </tr>
        </c:forEach>
    </table>
    <table>
        <tr>
            <td><button name="tasks" onClick='location.href="main"'>Назад</button></td>
        </tr>
    </table>
</div>
</body>
</html>