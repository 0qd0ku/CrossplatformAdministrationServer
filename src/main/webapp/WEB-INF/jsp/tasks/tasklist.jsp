<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Управления задачами</title>
</head>
<body>
<table width="100%" height="100%">
    <tr>
        <td align="center">
            <table border="1" width="100%">
                <tr>
                    <td>Идентификатор</td>
                    <td>Имя</td>
                    <td>Тип</td>
                    <td>Версия</td>
                    <td>Операционная система</td>
                    <td>Архитектура</td>
                    <td>Путь к исполняемому файлу</td>
                    <td>Base64 Torrent</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                <c:forEach var="task" items="${tasklist}">
                    <tr>
                        <td>${task.id}</td>
                        <td>${task.name}</td>
                        <td>${task.taskType}</td>
                        <td>${task.version}</td>
                        <td>${task.os.value}</td>
                        <td>${task.osType.value}</td>
                        <td>${task.pathToRunFile}</td>
                        <td>${task.torrentFile}</td>
                        <td><spring:form method="POST" action="delete-task"><button name="id" value="${task.id}">Удалить</button></spring:form></td>
                        <td><spring:form method="GET"  action="edit-page"><button name="id" value="${task.id}">Изменить</button></spring:form></td>
                    </tr>
                </c:forEach>
            </table>
        <table>
            <tr>
                <td><button name="tasks" onClick='location.href="main"'>Назад</button></td>
            </tr>
        </table>
        </td>
    </tr>
</table>
</body>
</html>