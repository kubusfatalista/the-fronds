<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page session="false" %>
<html>

    <head>
        <title>Eloszeczka</title>
        <link rel="stylesheet"
            type="text/css"
            href="<c:url value="/resources/style.css" />" >
    </head>
    <body>
        <h1>dziewczyna rolnika</h1>
        <a href="<s:url value="/login" />">Zaloguj<a/>
        <a href="<s:url value="/logout" />">Wyloguj</a>
        <a href="<s:url value="/register" />">Rejestracja</a>
    </body>
</html>