<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Albumy uzytkownika</title>
<style>
div.img {
	border: 1px solid #ccc;
}

div.img:hover {
	border: 1px solid #777;
}

div.img img {
	width: 100%;
	height: auto;
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
	width: 24.99999%;
}

@media only screen and (max-width: 700px) {
	.responsive {
		width: 49.99999%;
		margin: 6px 0;
	}
}

@media only screen and (max-width: 500px) {
	.responsive {
		width: 100%;
	}
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
				<a target="_blank" href="albums/imageDisplay/${userPhoto.imageSavedName}"> <img
					src="albums/imageDisplay/${userPhoto.imageSavedName}" alt="${userPhoto.imageTitle}" width="300"
					height="200">
				</a>
				<div class="desc">"${userPhoto.imageTitle}"</div>
			</div>
		</div>

	</c:forEach>


	<div class="clearfix"></div>

</body>
</html>