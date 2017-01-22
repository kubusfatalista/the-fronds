<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" contentType="text/html ; charset=UTF-8" %>

<html>
     <head>
         <title>Zarejestruj swoje japsko!</title>
         <link rel="stylesheet"
             type="text/css"
             href="<c:url value="/resources/style.css" />" >
     </head>
     <body>
        <h1>Rejestracja</h1>
        <sf:form method="POST" commandName="user" enctype="multipart/form-data">
            <sf:errors path="*" element="div" cssClass="errors" />
            <sf:label path="firstName" cssErrorClass="error">Imię:</sf:label>
            <sf:input path="firstName" cssErrorClass="error" /><br/>
            <sf:label path="lastName" cssErrorClass="error">Nazwisko:</sf:label>
            <sf:input path="lastName" cssErrorClass="error" /><br/>
            <sf:label path="login" cssErrorClass="error">Login:</sf:label>
            <sf:input path="login" cssErrorClass="error" /><br/>
            <sf:label path="password" cssErrorClass="error">Hasło:</sf:label>
            <sf:password path="password" cssErrorClass="error" /><br/>
            <input type="file" name="profilePicture" accept="image/jpeg,image/png,image/gif" /><br/>
            <input type ="submit" value="Zarejestruj" />
        </sf:form>
      </body>
 </html>