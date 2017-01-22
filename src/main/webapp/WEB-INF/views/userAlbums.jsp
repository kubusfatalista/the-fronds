<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Albumy uzytkownika</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/style.css" />">
</head>
<body>
	<h1>Zdjecie dodane, elo</h1>
	<c:forEach items="${userPhotoList}" var="userPhoto">
		<!-- <li id="user_<c:out value="user.id"/>">  -->
		<div>
			<span class="userPhotoTitle"><c:out value="${userPhoto.imageTitle}" /></span>
			<img id="myImg" alt="${userPhoto.imageTitle}"
				src="albums/imageDisplay/${userPhoto.imageSavedName}" width="250" height="250" />
		</div>
		<!-- </li>  -->
	</c:forEach>
</body>
</html>