<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../WEB-INF/jspf/locale.jspf"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../css/base.css">
<link rel="stylesheet" href="../css/table.css">
<link rel="stylesheet" href="../css/skeleton.css">
<link rel="stylesheet" href="../css/screen.css">
<link rel="shortcut icon" href="../images/favicon.png">
<link href='http://fonts.googleapis.com/css?family=Oswald:400,300,700'
	rel='stylesheet' type='text/css'>
<title>${login_success_title }</title>
</head>
<body>
	<%@ include file="../WEB-INF/jspf/header.jspf"%>
	<pre>
	</pre>
	<h2>${login_success_message }</h2>
	<pre>
	</pre>

	<form action="<c:url value="/Controller"/>" method="GET">
		<input type="hidden" name="command" value="account_main_page">
		<input type="submit" value="${login_success_button }">
	</form>
	<pre>
	
	</pre>

	<%@ include file="../WEB-INF/jspf/footer.jspf"%>
</body>
</html>