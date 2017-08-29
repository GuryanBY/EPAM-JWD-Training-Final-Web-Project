<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../WEB-INF/jspf/locale.jspf"%>
<!DOCTYPE html>
<!--[if lt IE 9]>
	<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
<!--[if lt IE 9]>
    <script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
<![endif]-->
<html>
<head>
<meta charset="utf-8">
<title>${reg_title }</title>

<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">

<link rel="stylesheet" href="<c:url value="/css/base.css"/>">
<link rel="stylesheet" href="<c:url value="/css/skeleton.css"/>">
<link rel="stylesheet" href="<c:url value="/css/screen.css"/>">
<link rel="shortcut icon" href="<c:url value="images/favicon.png"/>">
<link href='http://fonts.googleapis.com/css?family=Oswald:400,300,700'
	rel='stylesheet' type='text/css'>
</head>
<style>
.errorMessage{
	font-color: red;
	text-align: center;
	font: 2.1em Arial, Helvetica, sans-serif;
	
}
</style>

<body>
	<%@ include file="../WEB-INF/jspf/header.jspf"%>
	<pre>


    </pre>


	<div class="reg-form">
		<form action="<c:url value="/Controller"/>" method="post"
			class="login" onsubmit="return validateForm()" name="testForm">
			<div>
				<span class="in">${reg_firstname }:</span> <input type="text"
					name="firstName"> <span class="err" id="err-first-name"></span>
			</div>
			<div>
				<span class="in">${reg_lastname }:</span> <input type="text"
					name="lastName"> <span class="err" id="err-last-name"></span>
			</div>
			<div>
				<span class="in">${reg_age }:</span> <input type="text" name="age">
				<span class="err" id="err-age"></span>
			</div>
			<div>
				<span class="in"> ${reg_phone }:</span> <input type="text"
					name="phone"
					placeholder="${reg_placeholder_format }: (XXX) XXX-XX-XX">
				<span class="err" id="err-phone"></span>
			</div>
			<div>
				<span class="in"> ${reg_login }:</span> <input type="text"
					name="login" placeholder="${reg_placeholder }: redmi123"> <span
					class="err" id="err-login"></span>
			</div>
			<div id="parentId">
				<div>
					<span class="in">E-mail:</span> <input type="text" name="eMail1">
					<span class="err" id="err-e-mail1"></span>
				</div>
			</div>
			<div>
				<span class="in"> ${reg_password }:</span> <input type="password"
					name="pwd1" placeholder="${reg_placeholder }: asdfG123*"> <span
					class="err" id="err-pwd1"></span>
			</div>
			<div>
				<span class="in">${reg_password_again }:</span> <input
					type="password" name="pwd2"> <span class="err"
					id="err-pwd2"></span>
			</div>
			<input type="submit" value="${reg_submit }"> <input
				type="hidden" name="command" value="registration" />
		</form>
	</div>

	<div class="errorMessage">
		<c:set var="message" scope="request" value="${invalidInputData }" />
		<c:if test="${not empty message }">
			<p>${message }</p>
		</c:if>

		<c:set var="message2" scope="request" value="${loginOrEmailNotUniq }" />
		<c:if test="${not empty message2 }">
			<p class="fail">${message2 }</p>
		</c:if>
	</div>

	<hr class="separator2">
	<div class="socialsblock">
		<div class="container socialize">
			<h3>${reg_more }:</h3>
			<section class="socials">
				<ul class="socials">
					<li><a href="#"><img src="<c:url value="/images/socials/twitter.png"/>"
							alt="" /></a></li>
					<li><a href="#"><img src="<c:url value="/images/socials/facebook.png"/>"
							alt="" /></a></li>
					<li><a href="#"><img src="<c:url value="/images/socials/dribbble.png"/>"
							alt="" /></a></li>
					<li><a href="#"><img src="<c:url value="/images/socials/google+.png"/>"
							alt="" /></a></li>
					<li><a href="#"><img src="<c:url value="/images/socials/linkedin.png"/>"
							alt="" /></a></li>
					<li><a href="#"><img src="<c:url value="/images/socials/youtube.png"/>"
							alt="" /></a></li>
				</ul>
			</section>
		</div>
	</div>
	

	<%@ include file="../WEB-INF/jspf/footer.jspf"%>
	<script src="../js/reg.js"></script>
</body>
</html>