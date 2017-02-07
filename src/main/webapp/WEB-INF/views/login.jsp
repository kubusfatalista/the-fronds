<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Lobster&subset=latin-ext">
<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Sansita&subset=latin-ext">
<link rel="stylesheet" type="text/css" href="resources/mainStyle.css">
<meta charset="UTF-8">
<title>Przyjaciule</title>
<style>

.login-box {
	margin: auto;
	margin-top: 50px;
	background-color: white;
	border-radius: 15px;
	border-color: #4597ba;
	border-style: solid;
	border-width: 5px;
	width: 400px;
	height: 300px;
	padding: 10px;

}

p {
	font-size: 16px;
	font-family: Sansita;
	width: 60px;
	float:left;
}

input[type=text], input[type=password] {
	border-radius: 5px;
	width: 300px;
	height: 30px;
	font-size: 16px;
	margin: 10px;
}

input[type=submit] {
	font-family: Sansita;
	font-size: 16px;
	border-radius: 5px;
	height: 40px;
	width: 150px;
	margin-right: 25px;
	float: right;
}

h1 {
	float: left;
	font-family:Lobster;
	margin-left: 90px
}

image {
	position: relative;
	left: 40px;
	bottom: 10px;
}

div.error-div {
	background-color:red;
	height: 40px;
	width: 160px;
	float:left;
	display:none;
	
}

</style>
</head>
<body class="body-nomargins">

	<div class="login-box">
		<h1> Zaloguj się </h1>
		<image src="resources/images/los_przebiega.png" width=100 height=100/>
		<form method="POST" action="login">
			<p>Login</p>
			<input type="text" name="login" />
			<input type="password" name="password" />
			<p>Hasło</p>
			<div class="error-div"> </div>
			<input type="submit" value="Zaloguj się" />
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>		
	</div>

</body>
</html>