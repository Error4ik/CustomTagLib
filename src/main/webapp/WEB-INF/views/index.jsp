<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="jsonTags" prefix="ex" %>
<%@ page isELIgnored="false" %>
<html>
<body>
Hello
<c:forEach var="user" items="${users}">
    <p>${user.name}</p>
    <p>${user.surname}</p>
    <p>${user.age}</p>
</c:forEach>
<%--<ex:jsonTags value="${data}">--%>
<%--    ${data}--%>
<%--</ex:jsonTags>--%>

<ex:timeTag/>

<ex:textBodyTag iterations="3" value="40" json="${user1}">
    <tr>
        <td>Some text</td>
        <td><ex:timeTag/></td>
        <td><%= 100 %></td>
    </tr>
</ex:textBodyTag>

<ex:expressionBodyTag>
    world - is our all.rabbit - is a just animal.java - best programming language
</ex:expressionBodyTag>
</body>
</html>
