<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script type="text/javascript"><%@include file="../js/edit-task.js"%>
    </script>
</head>
<body>
<table>
    <tr>
        <td>
            <form:form modelAttribute="task">
                <table>
                    <tr>
                        <td><form:label path="id">id:</form:label> </td>
                        <td><form:label path="name">Логин:</form:label></td>
                        <td><form:label path="taskType">Тип:</form:label></td>
                        <td><form:label path="version">Версия:</form:label></td>
                        <td><form:label path="os">Операционная система:</form:label></td>
                        <td><form:label path="osType">Архитектура:</form:label></td>
                        <td><form:label path="pathToRunFile">Путь к исполняемому файлу:</form:label></td>
                        <td><form:label path="torrentFile">BASE64 Torrent:</form:label></td>
                    </tr>
                    <tr>
                        <td><form:input id="id" path="id" readonly="true"/></td>
                        <td><form:input id="name" path="name"/></td>
                        <td><form:input id="taskType" path="taskType"/></td>
                        <td><form:input id="version" path="version"/></td>
                        <td><form:select path="os">
                            <form:option value="WINDOWS">Windows</form:option>
                            <form:option value="LINUX">Linux</form:option>
                            <form:option value="MACOS">MacOS</form:option>
                        </form:select></td>
                        <td><form:select path="osType">
                            <form:option value="X64">x64</form:option>
                            <form:option value="X86">x86</form:option>
                        </form:select></td>
                        <td><form:input id="pathToRunFile" path="pathToRunFile"/></td>
                        <td><form:input type="file" id="torrentFile" path="torrentFile"/></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="button" value="Изменить" onclick="edittask()"></td>
                    </tr>
                </table>
            </form:form>
        </td>
    </tr>
</table>
</body>
</html>
