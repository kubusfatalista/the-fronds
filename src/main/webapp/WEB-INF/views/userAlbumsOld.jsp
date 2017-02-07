<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>Albumy uzytkownika</title>
<style>
div.img {
	border: 1px solid #ccc;
}

div.img:hover {
	border: 1px solid #777;
}

div.desc {
	padding: 15px;
	text-align: center;
}

* {
	box-sizing: border-box;
}

.responsive {
	padding: 0 6px;
	float: left;
}

.clearfix:after {
	content: "";
	display: table;
	clear: both;
}
</style>
</head>
<body>
	<h2>Responsive Image Gallery</h2>
	<h4>Album uzyszkodnika ${user.firstName} ${user.lastName}</h4>

	<c:forEach items="${userPhotoList}" var="userPhoto">
		<div class="responsive">
			<div class="img">
				<a target="_blank"
					href="albums/imageDisplay/${userPhoto.imageSavedName}m"> <img
					src="albums/imageDisplay/${userPhoto.imageSavedName}m"
					alt="${userPhoto.imageTitle}">
				</a>
				<div class="desc">"${userPhoto.imageTitle}"</div>
			</div>
		</div>
		<div class="responsive">
			<div class="img">
				<a target="_blank"
					href="albums/imageDisplay/${userPhoto.imageSavedName}s"> <img
					src="albums/imageDisplay/${userPhoto.imageSavedName}s"
					alt="${userPhoto.imageTitle}">
				</a>
				<div class="desc">"${userPhoto.imageTitle}"</div>
			</div>
		</div>
		<div class="responsive">
			<div class="img">
				<a target="_blank"
					href="albums/imageDisplay/${userPhoto.imageSavedName}xs"> <img
					src="albums/imageDisplay/${userPhoto.imageSavedName}xs"
					alt="${userPhoto.imageTitle}">
				</a>
				<div class="desc">"${userPhoto.imageTitle}"</div>
			</div>
		</div>

	</c:forEach>


	<div class="clearfix"></div>

</body>
</html>