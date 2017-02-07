<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Lobster&subset=latin-ext">
<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Sansita&subset=latin-ext">
<link rel="stylesheet" type="text/css" href="resources/mainStyle.css">
<link rel="stylesheet" type="text/css" href="resources/modalGallery.css">
<meta charset="UTF-8">
<title>Przyjaciule - Twoje albumy</title>
<style>

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

</style>
</head>
<body class="body-nomargins">

	<div id="nav-container" class="nav-container container-width">
		<ul id="navBar" class="container-width">
			<li style="float: left"><a href="#about">Przyjaciule</a></li>
			<li style="float: left"><a href="#about">Todo szukajka</a></li>
			<li><a class="topButton" href="#about">Ustawienia</a></li>
			<li><a class="topButton" href="#about">Powiadomienia</a></li>
			<li><a class="topButton" href="#about">Wiadomości</a></li>
			<li><a class="topButton" href="#contact">Zaproszenia</a></li>
			<li><a class="topButton" href="#news">Wall</a></li>
			<li><a class="topButton" href="#home">Mój profil</a></li>
		</ul>
	</div>

	<div class="main-container container-width">
	
		<div class="left-column">
			<div >
				<img class="profilePhoto" alt="${user.firstName}" src="myProfile/imageDisplay${content}" />
				<h1 class="name">
					${user.firstName} ${user.lastName}
				</h1>
				<hr>
				<h1 class="menu">
					<p class="menu"><a href="myProfile">Łoś czasu</a></p>
					<p class="menu"><a href="myInfo">Informacje</a></p>
					<p class="menu"><a href="myFronds">Twoi przyjaciule</a></p>
					<p class="menu"><a href="myAlbums">Zdjęcia twojej japy</a></p>
				</h1>
			</div>
		</div>
	
		<div class="center-column">
			<c:forEach items="${userPhotoList}" var="userPhoto">
				<img class="photoInAlbum" src="myAlbums/imageDisplay/${userPhoto.imageSavedName}m" 
				alt="Title" onclick="showModalWindow()" />
			</c:forEach>
		</div>
		
		<div class="right-column">
		
		</div>
	
	</div>

	<div id="myModal" class="modal">
		<span class="close">&times;</span>
		<div class="image-background">
		
			<c:forEach items="${userPhotoList}" var="userPhoto">
				<div class="mySlides image-container">
					<img class="img-slide" src="myAlbums/imageDisplay/${userPhoto.imageSavedName}" 
					alt=${userPhoto.imageTitle} onclick="showModalWindow()" />
					<div class="text">${userPhoto.imageTitle}</div>
				</div>
			</c:forEach>
	
			<a class="prev" onclick="plusSlides(-1)">&lt;</a> <a class="next"
				onclick="plusSlides(1)">&gt;</a>
	
		</div>
	</div>

</body>
<script>

	 var targetLeft  = 0;
	 var targetTop = 0;
	 var $navBar = $('#navBar');
	 var $navContainer =$('#nav-container');
	 var $leftColumn = $('.left-column');
	 var $rightColumn = $('.right-column');
	 $(window).scroll(function() {
		if($navContainer.css('margin-left') == '0px') {
	    	var left = $(window).scrollLeft();
	    	var adj = targetLeft - left;
	    	$navBar.css({left: adj});
            $leftColumn.css({left: adj});
            $rightColumn.css({left: adj});
		}
	 });


	$(window).resize(function() {
		 if($navContainer.css('margin-left') != '0px') {
	         $navBar.css({left: 'auto'});
            $leftColumn.css({left: 'auto'});
            $rightColumn.css({left: 'auto'});
		 }
 	});

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
</html>