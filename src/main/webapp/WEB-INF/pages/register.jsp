<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: maxbacinskiy
  Date: 28.02.17
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register new user</title>
</head>
<body>
<form:form method="post" modelAttribute="newUser" action="register">
    <h4>Регистрация:</h4>
    <table>
        <tr>
            <td><form:label path="login">
                Логин (от 6 до 32)
            </form:label></td>
            <td><form:input path="login"/></td>
            <td><form:errors path="login"/></td>
        </tr>

        <tr>
            <td><form:label path="password">
                Пароль (от 8)
            </form:label></td>
            <td><form:password path="password"/></td>
            <td><form:errors path="password"/></td>
        </tr>

        <tr>
            <td><form:label path="confirmPassword">
                Повторите пароль
            </form:label></td>
            <td><form:password path="confirmPassword"/></td>
            <td><form:errors path="confirmPassword"/></td>
        </tr>


        <tr>
            <td><input type="submit" value="Зарегистрироваться"/></td>
            <td><a href="/home">Назад в меню</a></td>
        </tr>

    </table>
</form:form>
</body>
</html>
