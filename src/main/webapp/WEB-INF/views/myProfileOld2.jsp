<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Profil twojej japy</title>
<style>
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

div.cycki2 {
	min-height: 400px;
	width: 675px;
	display: inline-block;
	border-style: solid;
	border-color: darkviolet;
	border-width: 1px;
}

img.photoInAlbum {
	border: 1px solid #ddd; /* Gray border */
	border-radius: 4px; /* Rounded border */
	padding: 4px; /* Some padding */
	margin: 4px;
	cursor: pointer;
}

img.photoInAlbum:hover {
	box-shadow: 0 0 2px 1px rgba(0, 140, 186, 0.5);
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

/* Add Animation */
.image-background {    
    -webkit-animation-name: zoom;
    -webkit-animation-duration: 0.6s;
    animation-name: zoom;
    animation-duration: 0.6s;
}

@-webkit-keyframes zoom {
    from {-webkit-transform:scale(0)} 
    to {-webkit-transform:scale(1)}
}

@keyframes zoom {
    from {transform:scale(0)} 
    to {transform:scale(1)}
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

* {
	box-sizing: border-box
}

.mySlides {
	display: none
}

.image-background {
	display: table;
	width: 100%;
	height: 100%;
	position: relative;
}

.image-container {
	display: table-cell;
	vertical-align: middle;
	text-align: center;
	margin: auto;
	height: 500px;
}

.img-slide {
	display: block;
	height: 100%;
	max-height: 100%;
	max-width: 100%;
	margin: auto;
}

/* Next & previous buttons */
.prev, .next {
	cursor: pointer;
	position: absolute;
	top: 50%;
	width: auto;
	padding: 16px;
	margin-top: -22px;
	color: white;
	font-weight: bold;
	font-size: 18px;
	transition: 0.6s ease;
	border-radius: 0 3px 3px 0;
}

/* Position the "next button" to the right */
.next {
	right: 0;
	border-radius: 3px 0 0 3px;
}

/* On hover, add a black background color with a little bit see-through */
.prev:hover, .next:hover {
	background-color: rgba(0, 0, 0, 0.8);
}

/* Caption text */
.text {
	color: #f2f2f2;
	font-size: 15px;
	padding: 8px 12px;
	position: absolute;
	bottom: 8px;
	width: 100%;
	text-align: center;
	font-family: Verdana, sans-serif;
}
</style>
</head>
<body>
	<ul>
		<li style="float: left"><a href="#about">Przyjaciule</a></li>
		<li style="float: left"><a href="#about">Todo szukajka</a></li>
		<li><a class="topButton" href="#about">Ustawienia</a></li>
		<li><a class="topButton" href="#about">Powiadomienia</a></li>
		<li><a class="topButton" href="#about">Wiadomości</a></li>
		<li><a class="topButton" href="#contact">Zaproszenia</a></li>
		<li><a class="topButton" href="#news">Wall</a></li>
		<li><a class="topButton" href="#home">Mój profil</a></li>
	</ul>
	<div style="width: 1060px; margin-top: 50px">
		<!-- Left Column -->
		<div style="width: 250px; display: inline-block; float: left">

			<div>
				<div>
					<img alt="${user.firstName}" 
						src="myProfile/imageDisplay${content}" />
					<div>
						<h1>Twoj profil</h1>
						<c:out value="${user.login}" />
						<br />
						<c:out value="${user.firstName}" />
						<c:out value="${user.lastName}" />
					</div>
				</div>
				<div>
					<p>Łoś czasu</p>
					<p>Informacje</p>
					<p>Twoi przyjaciule (170)</p>
					<a href="myProfile/albums">Zdjęcia twojej japy</a>
					<hr>
					<br>
				</div>
			</div>
			<br>

			<!-- End Left Column -->
		</div>
		<div class="cycki2">
			<p>Nic do pokazania</p>
		</div>
		<!-- The Modal -->
		<div id="myModal" class="modal">
			<span class="close">&times;</span>
			<div class="image-background">

				<div class="mySlides image-container">
					<img class="img-slide" src="C:/test files/1.jpg" />
					<div class="text">Caption Text</div>
				</div>

				<div class="mySlides image-container">
					<img class="img-slide" src="C:/test files/2.jpg" />
					<div class="text">Caption Two</div>
				</div>

				<div class="mySlides image-container">
					<img class="img-slide" src="C:/test files/3.jpg" />
					<div class="text">Caption Three</div>
				</div>

				<a class="prev" onclick="plusSlides(-1)">&lt;</a> <a class="next"
					onclick="plusSlides(1)">&gt;</a>

			</div>
		</div>
	</div>
	<script>
		
	var modal = document.getElementById('myModal');
		// Get the <span> element that closes the modal
		var span = document.getElementsByClassName("close")[0];

		// When the user clicks on <span> (x), close the modal
		span.onclick = function() {
			
			modal.style.display = "none";
		}


		var slideIndex = 1;
		showSlides(slideIndex);

		function showModalWindow() {
			modal.style.display = "block";
		}

		function plusSlides(n) {
		  showSlides(slideIndex += n);
		}

		function currentSlide(n) {
		  showSlides(slideIndex = n);
		}

		function showSlides(n) {
	      var i;
	      var slides = document.getElementsByClassName("mySlides");
	      if (n > slides.length) {slideIndex = 1}    
	      if (n < 1) {slideIndex = slides.length}
	      for (i = 0; i < slides.length; i++) {
	          slides[i].style.display = "none";  
	      }
	      slides[slideIndex-1].style.display = "block";  
		}
	</script>
</body>
</html>