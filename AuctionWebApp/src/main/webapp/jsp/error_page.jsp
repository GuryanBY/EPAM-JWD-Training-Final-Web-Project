<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/base.css">
<link rel="stylesheet" href="css/table.css">
<link rel="stylesheet" href="css/skeleton.css">
<link rel="stylesheet" href="css/screen.css">
<link rel="shortcut icon" href="images/favicon.png">
<link href='http://fonts.googleapis.com/css?family=Oswald:400,300,700'
	rel='stylesheet' type='text/css'>
<title>Error Page</title>
</head>
<body>
<%@ include file="../WEB-INF/jspf/header.jspf"%>
<pre>
OOOOpppsss... Some problem. Try it later.

<a href="<c:url value="/index.jsp"/>"> return to start page</a>


    </pre>

	

	
	<br />
	<pre>


    </pre>
	<div id="copyright">
		<div class="container">
			<p class="copyright">
				&copy; Copyright 2017. "Альфа Аукцион" разработано <a
					href="http://www.facebook.com/user/" rel="nofollow">Guryan</a>.
			</p>
		</div>
	</div>

</body>
</html>