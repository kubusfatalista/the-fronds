<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Lobster&subset=latin-ext">
<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Sansita&subset=latin-ext">
<link rel="stylesheet" type="text/css" href="resources/mainStyle.css">
<link rel="stylesheet" href="resources/font-awesome/css/font-awesome.min.css">
<link rel="icon" href="resources/images/moose_front.png">
<meta charset="UTF-8">
<title>Przyjaciule - Twój profil</title>

<style>

textarea {
    width: 485px;
    height: 100px;
    padding: 6px;
    box-sizing: border-box;
    border: 2px solid #ccc;
    border-radius: 4px;
    background-color: #f8f8f8;
    font-size: 16px;
    resize: none;
    margin: 10px;
    float: left;
}

textarea:focus {
    border: 2px solid #555;
}

div.feed-the-moose {
	text-align: center;
	position: relative;
	float: right;
	width: 96px;
	height: 96px;
	border: 2px solid #9ad3de;
	background-color: white;
	border-radius: 10px;
	bottom: 8px;
	margin-right: 10px;
	transition-duration: 0.3s;
}

div.feed-the-moose:hover {
	cursor: pointer;
	width: 96px;
	height: 96px;
	background-color: #9ad3de;
	border: 2px solid #89bdd3;
	transition-duration: 0.3s;
}

img.feed-the-moose {
	position:relative;
	bottom: 10px;
	width: 85px; 
	height: 85px;
}

p.feed-the-moose {
	position: relative;
	font-family: Sansita;
	font-size: 16px;
	bottom: 40px;
}

div.time-mooose-status-div {
	width: 600px;
	float:left;
	margin: 10px;
	border: 2px solid #89bdd3;
	border-radius: 10px;
}

p.time-moose-status-user {
	float:left;
	margin:5px;
	width: 300px;
	font-family: Sansita;
	font-size: 16px;
}

p.time-moose-status-date {
	float:right;
	margin:5px;
	font-family: Sansita;
	font-size: 16px;
}

p.time-moose-status-location {
	float:left;
	margin:5px;
	font-family: Sansita;
	font-size: 14px;
}

div.time-moose-status-content {
	float:left;
	margin: 5px;
    width: 580px;
    height: auto;
    word-wrap: break-word;
    padding: 5px;
    font-family: Sansita;
	font-size: 18px;
}

.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 360px;
    overflow: auto;
    max-height: 200px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
}

.dropdown-content a:hover {background-color: #f1f1f1}

.show {display:block;}

div.invitation-counter {
	font-family: Sansita;
	font-size: 11px;
	color: white;
	text-align: center;
	height: 15px;
	width: 15px;
	background-color: red;
	position: absolute;
	top: 5px;
	right: 5px;
	border-radius: 50%;
}

div.new-invitation {
	display: flex;
	position: relative;
	padding: 3px;
	border: 1px solid #9ad3de;
	border-radius: 5px;
	margin-top: 2px;
	max-height: 50px;
}

.new-invitation p {
	font-family: Sansita;
	font-size: 18px;
	margin-top: auto;
	margin-left: 5px;
	padding: 0px;
	word-wrap: break-word;
	max-width: 200px;
}

.new-invitation img {
	display: block;
	border-radius: 5px;
	width: 50px;
	height: 50px;
}

.checkIcon {
	position: absolute;
	color: #a9a9a9;	
	cursor: pointer;
	transition-duration: 0.3s;
	display: block;
	height: 30px;
	width: 30px;
	top: 10px;
	right: 50px;
}

.crossIcon {
	position: absolute;
	color: #a9a9a9;	
	cursor: pointer;
	transition-duration: 0.3s;
	display: block;
	height: 30px;
	width: 30px;
	top: 10px;
	right: 10px;
}

.checkIcon:hover {
	color: lightgreen;
}

.crossIcon:hover {
	color: tomato;
}

</style>

</head>
<body class="body-nomargins">

	<div id="nav-container" class="nav-container container-width">
		<ul id="navigationBar" class="container-width">
			<li style="float: left"><a href="#about">Przyjaciule</a></li>
			<li style="float: left"><a href="#about">Todo szukajka</a></li>
			<li><a class="topButton" href="#about">Ustawienia</a></li>
			<li><a class="topButton" href="#about">Powiadomienia</a></li>
			<li><a class="topButton" href="#about">Wiadomości</a></li>
			<li><a id="new-invitations-button" onclick="frondInvitationDropDown()" class="topButton">Zaproszenia</a>
				<div class="invitation-counter">
				${newInvitations}
				</div>
				<div id="myDropdown" class="dropdown-content">
					<c:forEach items="${relationshipList}" var="invite">
						<div class="new-invitation">
							<img alt="${invite.user.firstName}" src="myProfile/miniatureInInv/${invite.user.profilePicture}xs">
							<p>${invite.user.firstName} ${invite.user.lastName}</p>
							<i id="declineId${invite.relationshipId}" onclick="invitationDecline(${invite.relationshipId})" 
								class="fa fa-times-circle-o  fa-2x crossIcon" aria-hidden="true"></i>
							<i id="acceptId${invite.relationshipId}" onclick="invitationAccept(${invite.relationshipId})" 
								class="fa fa-check-circle-o fa-2x checkIcon" aria-hidden="true"></i>
						</div>
					</c:forEach>
				</div>
			</li>
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
			<form id="feed-the-moose-form" method="GET" action="myProfile/createTimeMooseStatus" enctype="text/plain">
				<textarea name="statusText" placeholder="Max 255 znaków bo bieda"></textarea>
				<br/> 
				<div id="feed-the-moose-div" class="feed-the-moose">
					<img class="feed-the-moose" src="resources/images/moose_front.png"></img>
					<p class="feed-the-moose">Nakarm Łoś!</p>
				</div>
			</form>
			<c:forEach items="${timeMooseStatusList}" var="timeMooseStatus">
				<div class="time-mooose-status-div">
					<img alt="${user.firstName}" src="myProfile/miniatureDisplay${content}" style="float:left;margin:5px">
					<p class="time-moose-status-user">${user.firstName} ${user.lastName}</p>
					<p class="time-moose-status-date">${timeMooseStatus.creationDate}</p>
					<p class="time-moose-status-location">TODO lokacja</p>
					<div class="time-moose-status-content">${timeMooseStatus.text}</div>
				</div>
			</c:forEach>
			
		</div>
		
		<div class="right-column">
			
		</div>
	
	</div>

</body>
<script>

	 var targetLeft  = 0;
	 var targetTop = 0;
	 var $navBar = $('#navigationBar');
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

	var form = document.getElementById("feed-the-moose-form");

	document.getElementById("feed-the-moose-div").addEventListener("click", function () {
	  form.submit();
	});

	function invitationAccept(relationshipId) {
		$.get("myProfile/invitationAccepted/"+relationshipId);
		//$(event.target).css({display : 'none'});
		$('#acceptId'+relationshipId).css({display : 'none'});
		$('#declineId'+relationshipId).css({display : 'none'});
		
	}

	function invitationDecline(relationshipId) {
		$.get("myProfile/invitationDeclined/"+relationshipId);
		$('#acceptId'+relationshipId).css({display : 'none'});
		$('#declineId'+relationshipId).css({display : 'none'});
	}

	
	function frondInvitationDropDown() {
	    document.getElementById("myDropdown").classList.toggle("show");
	}

	$(window).click(function() {
		
		if (!$(event.target).is('#new-invitations-button')) {
			// hehe
			var parts = '.dropdown-content div, .checkIcon, .crossIcon, .new-invitation p, .new-invitation img, .dropdown-content';
			if (!($(event.target).is(parts))) {
			    var dropdowns = document.getElementsByClassName("dropdown-content");
			    var i;
			    for (i = 0; i < dropdowns.length; i++) {
			      var openDropdown = dropdowns[i];
			      if (openDropdown.classList.contains('show')) {
			        openDropdown.classList.remove('show');
			      }
			    }
			}
		  }
	});
	
 	/* 
 	podjebane kolory - dwa szare, dwa morsko-niebieskie
 	
		#c9c9c9
		#e3e3e3
		#9ad3de
		#89bdd3
 	*/

</script>
</html>