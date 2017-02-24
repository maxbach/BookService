<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>BookService</title>
  </head>
  <body>
  <h2>What have I read?</h2>

  <c:if test="${!empty books}">
      <h4>Прочитанные книги:</h4>
    <ol>
      <c:forEach items="${books}" var="book">
          <li>${book.name} by ${book.author} ${book.rating}/10.
            <a href="/read/${book.id}">Читать обзор</a>
            <a href="/delete/${book.id}">Удалить из списка</a> </li>
      </c:forEach>
    </ol>
  </c:if>

  <form:form method="post" modelAttribute="bookEx" action="home">
      <h4>Добавить книгу:</h4>
    <table>
      <tr>
        <td><form:label path="name">
          Название книги
        </form:label></td>
        <td><form:input path="name" /></td>
        <td><form:errors path="name"/> </td>
      </tr>

      <tr>
        <td><form:label path="author">
          Автор
        </form:label></td>
        <td><form:input path="author" /></td>
        <td><form:errors path="author"/></td>
      </tr>

      <tr>
        <td><form:label path="description">
          Описание
        </form:label></td>
        <td><form:textarea path="description" rows="5" cols="30" /></td>
        <td><form:errors path="description"/> </td>
      </tr>

      <tr>
        <td><form:label path="rating">
          Оценка (от 1 до 10)
        </form:label></td>
        <td><form:select path="rating">
          <c:forEach var="i" begin="1" end="10">
            <form:option value="${i}"/>
          </c:forEach>
        </form:select> </td>
      </tr>

      <tr>
        <td colspan="2"><input type="submit" value="Добавить" /></td>
      </tr>

    </table>
  </form:form>
  </body>
</html>
