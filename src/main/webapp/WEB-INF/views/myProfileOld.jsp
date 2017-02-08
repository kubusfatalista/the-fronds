<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>Profil twojej japy</title>
<style>
body {
	margin: 0;
}

ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	background-color: #4a0561;
	position: fixed;
	top: 0;
	width: 100%;
}

li {
	float: right;
}

li a {
	display: block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

li a.topButton:hover {
    background-color: #610561;
}

#myImg {
	border-radius: 5px;
	cursor: pointer;
	transition: 0.3s;
}

#myImg:hover {
	opacity: 0.7;
}

/* The Modal (background) */
.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	padding-top: 100px; /* Location of the box */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.9); /* Black w/ opacity */
}

/* Modal Content (image) */
.modal-content {
	margin: auto;
	display: block;
	width: 80%;
	max-width: 700px;
}

/* Caption of Modal Image */
#caption {
	margin: auto;
	display: block;
	width: 80%;
	max-width: 700px;
	text-align: center;
	color: #ccc;
	padding: 10px 0;
	height: 150px;
}

/* Add Animation */
.modal-content, #caption {
	-webkit-animation-name: zoom;
	-webkit-animation-duration: 0.6s;
	animation-name: zoom;
	animation-duration: 0.6s;
}

@
-webkit-keyframes zoom {
	from {-webkit-transform: scale(0)
}

to {
	-webkit-transform: scale(1)
}

}
@
keyframes zoom {
	from {transform: scale(0)
}

to {
	transform: scale(1)
}

}

/* The Close Button */
.close {
	position: absolute;
	top: 15px;
	right: 35px;
	color: #f1f1f1;
	font-size: 40px;
	font-weight: bold;
	transition: 0.3s;
}

.close:hover, .close:focus {
	color: #bbb;
	text-decoration: none;
	cursor: pointer;
}

/* 100% Image Width on Smaller Screens */
@media only screen and (max-width: 700px) {
	.modal-content {
		width: 100%;
	}
}
</style>
</head>
<body>
	<ul>
	<li style="float:left"><a href="#about">Przyjaciule</a></li>
	<li style="float:left"><a href="#about">Todo szukajka</a></li>
		<li><a class="topButton" href="#home">Mój profil</a></li>
		<li><a class="topButton" href="#news">Wall</a></li>
		<li><a class="topButton" href="#contact">Zaproszenia</a></li>
		<li><a class="topButton" href="#about">Wiadomości</a></li>
		<li><a class="topButton" href="#about">Powiadomienia</a></li>
		<li><a class="topButton" href="#about">Ustawienia</a></li>
	</ul>
	<h1>Twoj profil</h1>
	<c:out value="${user.login}" />
	<br />
	<c:out value="${user.firstName}" />
	<c:out value="${user.lastName}" />
	<img id="myImg" alt="${user.firstName}"
		src="myProfile/imageDisplay${content}" width="250" height="250" />

	<!-- The Modal -->
	<div id="myModal" class="modal">
		<span class="close">&times;</span> <img class="modal-content"
			id="img01">
		<div id="caption"></div>
	</div>
	<form action="myProfile/albums">
		<input type="submit" value="Otworz albumy" />
	</form>
	<sf:form method="POST" action="myProfile/addPhoto"
		enctype="multipart/form-data">
		<input type="file" name="addedPhoto"
			accept="image/jpeg,image/png,image/gif" />
		<br />
		<input type="submit" value="Dodaj do galerii" />
	</sf:form>
	<form method="GET" action="myProfile/sendFriendInvitation"
		enctype="text/plain">
		<input type="text" name="friendId" /> <br /> <input type="submit"
			value="Wyslij zaproszenie" />
	</form>
	<a href="myProfileTest"> a moze to? </a>
	<script>
		// Get the modal
		var modal = document.getElementById('myModal');

		// Get the image and insert it inside the modal - use its "alt" text as a caption
		var img = document.getElementById('myImg');
		var modalImg = document.getElementById("img01");
		var captionText = document.getElementById("caption");
		img.onclick = function() {
			modal.style.display = "block";
			modalImg.src = this.src;
			captionText.innerHTML = this.alt;
		}

		// Get the <span> element that closes the modal
		var span = document.getElementsByClassName("close")[0];

		// When the user clicks on <span> (x), close the modal
		span.onclick = function() {
			modal.style.display = "none";
		}
	</script>

</body>
</html>