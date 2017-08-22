<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../WEB-INF/jspf/locale.jspf"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<c:url value="/css/base.css"/>">
<link rel="stylesheet" href="<c:url value="/css/skeleton.css"/>">
<link rel="stylesheet" href="<c:url value="/css/screen.css"/>">
<link rel="shortcut icon" href="<c:url value="../images/favicon.png"/>">
<link href='http://fonts.googleapis.com/css?family=Oswald:400,300,700'
	rel='stylesheet' type='text/css'>
<title>${operation_title }</title>
</head>
<body>
	<%@ include file="../WEB-INF/jspf/header.jspf"%>
	<pre>
	</pre>
			
	<div class="container">
		<p class="header">${operation_text_success }</p>
		<p class="text">${operation_text_continue }</p>
		<form action="<c:url value="/Controller"/>" method="GET">
			<input type="hidden" name="command" value="account_main_page">
			<input type="submit" value="${operation_button_continue }">
		</form>
	</div>
	
</body>
</html>