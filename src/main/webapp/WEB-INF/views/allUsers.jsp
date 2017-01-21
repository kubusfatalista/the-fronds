<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
    <head>
        <title>Wszyscy uzytkownicy</title>
        <link rel="stylesheet"
            type="text/css"
            href="<c:url value="/resources/style.css" />" >
    </head>
    <body>
        <h1>Wszyscy userzy w systemie</h1>
        <c:forEach items="${userList}" var="user" >
            <li id="user_<c:out value="user.id"/>">
                <div class="user.login">
                    <c:out value="${user.login}" />
                </div>
                <div>
                    <span class="userPassword"><c:out value="${user.password}"/></span>
                    <span class="userFirstAndLastName">
                        (<c:out value="${user.firstName}" />,
                        <c:out value="${user.lastName}" />)
                     </span>
                </div>
            </li>
        </c:forEach>
     </body>
</html>