<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: maxbacinskiy
  Date: 24.02.17
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book service</title>
</head>
<body>
<a href="/home"><-- Back to menu</a>
<h2>${book.name}</h2>
<h3>Author: ${book.author}</h3>
<p>${book.text}</p>

<c:if test="${!empty myComment}">

</c:if>

<c:choose>
    <c:when test="${!empty myComment}">
        <br>
        <h3>Ваш комментарий:</h3>
        <c:forEach items="${comments}" var="comment">
            <h4>${comment.author.login} - ${comment.rating}/10</h4>
            <p>${comment.text}</p>
            <a href="/edit/${myComment.id}">Редактировать свой коммент </a>
            <a href="${book.id}/removeSelfComment/">Удалить свой комментарий</a>
            <br>
        </c:forEach>
    </c:when>

    <c:otherwise>
        <sec:authorize access="hasRole('ROLE_USER')">
            <form:form method="post" modelAttribute="comment" action="${book.id}/newComment">
                <h4>Оставить свой отзыв на книгу:</h4>
                <table>
                    <tr>
                        <td><form:label path="text">
                            Отзыв:
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
        </sec:authorize>
    </c:otherwise>

</c:choose>

<c:if test="${!empty comments}">
    <br>
    <h3>Комментарии:</h3>
    <c:forEach items="${comments}" var="comment">
        <h4>${comment.author.login} - ${comment.rating}/10</h4>
        <p>${comment.text}</p>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <a href="/deleteComment/${comment.id}">Удалить из списка</a>
        </sec:authorize>
        <br>
    </c:forEach>
</c:if>
</body>
</html>
