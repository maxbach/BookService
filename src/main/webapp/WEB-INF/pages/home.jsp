<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BookService</title>
</head>
<body>
<h2>What have I read?</h2>

<sec:authorize access="isAnonymous()">
    <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
        <font color="red"> Ошибка входа
            : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} </font>
    </c:if>
    <form method="POST" action="<c:url value="/login" />">
        <table>
            <tr>
                <td align="right">Логин</td>
                <td><input type="text" name="j_username"/></td>
            </tr>
            <tr>
                <td align="right">Пароль</td>
                <td><input type="password" name="j_password"/></td>
            </tr>
            <tr>
                <td align="right">Запомнить меня</td>
                <td><input type="checkbox" name="_spring_security_remember_me"/></td>
            </tr>
            <tr>
                <td colspan="3" align="right"><input type="submit" value="Вход"/>
                    <input type="reset" value="Сброс"/> <a href="/register">Регистрация</a></td>
            </tr>
        </table>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <sec:authentication var="user" property="principal.username"/>
    <form method="post" action="/logout">
        <input type="submit" value="Выйти из аккаунта ${user}"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</sec:authorize>

<c:if test="${!empty books}">
    <h4>Прочитанные книги:</h4>
    <ol>
        <c:forEach items="${books}" var="book">
            <li>${book.name} by ${book.author} ${book.rating}/10.
            <a href="/read/${book.id}">Читать обзор</a>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <a href="/edit/${book.id}">Редактировать рецензию </a>
                <a href="/delete/${book.id}">Удалить из списка</a></li>
            </sec:authorize>
        </c:forEach>
    </ol>
</c:if>

<sec:authorize access="hasRole('ROLE_ADMIN')">
    <form:form method="post" modelAttribute="bookEx" action="add">
        <h4>Добавить книгу:</h4>
        <table>
            <tr>
                <td><form:label path="name">
                    Название книги
                </form:label></td>
                <td><form:input path="name"/></td>
                <td><form:errors path="name"/></td>
            </tr>

            <tr>
                <td><form:label path="author">
                    Автор
                </form:label></td>
                <td><form:input path="author"/></td>
                <td><form:errors path="author"/></td>
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
</sec:authorize>
</body>
</html>
