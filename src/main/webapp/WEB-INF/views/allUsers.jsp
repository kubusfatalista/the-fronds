<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Lobster&subset=latin-ext">
<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Sansita&subset=latin-ext">
<link rel="stylesheet" type="text/css" href="resources/mainStyle.css">
<link rel="icon" href="resources/images/moose_front.png">
<meta charset="UTF-8">
<title>Wszyscy zarejestrowani Przyjaciule</title>
<style>

div.one-big-column {
	display: flex;
	background-color: white;
    width: 1037px;
    height: auto;
    min-height: 100px;
	border: 2px solid;
	border-color: #4597ba;
	padding: 10px;
}

.card {
    box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
    transition: 0.3s;
    width: 200px;
    border-radius: 10px;
    margin-right: 10px;
}

.container {
    padding: 10px;;
}

img {
	border-radius: 10px;
	display: block;
}

h4 {
	font-family: Sansita;
	margin: 5px;
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
			<li><a class="topButton" href="#contact">Zaproszenia</a></li>
			<li><a class="topButton" href="#news">Wall</a></li>
			<li><a class="topButton" href="#home">Mój profil</a></li>
		</ul>
	</div>

	<div class="main-container container-width">
		Łośmiel się zalozyc u nas konto!
		<div class="one-big-column">
			<c:forEach items="${frondDtoList}" var="frond">
				<div class="card">
				  <img src="allUsers/imageDisplay/${frond.user.profilePicture}m" alt="Avatar">
				  <div class="container">
				    <h4><b>${frond.user.firstName} ${frond.user.lastName}</b></h4> 
				    <form method="GET" action="allUsers/sendFriendInvitation/${frond.user.userId}"
						enctype="text/plain">
	<!-- W user.userId 'userId' jest nazwa gettera, a nie pola! Jak pole bedzie userId, a getter getId(), to nie zadziala  -->
						<c:choose>
						    <c:when test="${frond.status=='ToTy'}">
						        <input type="submit" value="To ty" />
						    </c:when>
							<c:when test="${frond.status=='Przyjaciul'}">
						        <input type="submit" value="Przyjaciul" />
						    </c:when>
							<c:when test="${frond.status=='Nieznajomy'}">
						        <input type="submit" value="Wyślij zaproszenie" />
						    </c:when>
						    <c:when test="${frond.status=='Wyslano'}">
						        <input type="submit" value="Wysłano zaproszenie" />
						    </c:when>
						 	<c:when test="${frond.status=='Oczekuje'}">
						        <input type="submit" value="Zaakceptuj Przyjaciula" />
						    </c:when>
						</c:choose>
					</form>
				  </div>
				</div>
			</c:forEach>
		</div>
	</div>

</body>
<script>

	 var targetLeft = 0;
	 var targetTop = 0;
	 var $navBar = $('#navigationBar');
	 var $navContainer =$('#nav-container');
	 $(window).scroll(function() {
		if($navContainer.css('margin-left') == '0px') {
	    	var left = $(window).scrollLeft();
	    	var adj = targetLeft - left;
	    	$navBar.css({left: adj});
		}
	 });


	$(window).resize(function() {
		 if($navContainer.css('margin-left') != '0px') {
	         $navBar.css({left: 'auto'});
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