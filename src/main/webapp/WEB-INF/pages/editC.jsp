<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: maxbacinskiy
  Date: 11.03.17
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BookService</title>
</head>
<body>
<h2>Редактор комментариев</h2>
<form:form method="post" modelAttribute="editingComment" action="/editC/${objId}">
    <table>
        <tr>
            <td>Название книги</td>
            <td>${editingComment.book.name}</td>
        </tr>

        <tr>
            <td>Автор</td>
            <td>${editingComment.book.author}</td>
        </tr>
        <tr>
            <td><form:label path="text">
                Описание
            </form:label></td>
            <td><form:textarea path="text" rows="5" cols="30"/></td>
            <td><form:errors path="text"/></td>
        </tr>

        <tr>
            <td><form:label path="rating">
                Оценка (от 1 до 10)
            </form:label></td>
            <td><form:select path="rating">
                <c:forEach var="i" begin="1" end="10">
                    <form:option value="${i}"/>
                </c:forEach>
            </form:select></td>
        </tr>

        <tr>
            <td colspan="2"><input type="submit" value="Добавить"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>
