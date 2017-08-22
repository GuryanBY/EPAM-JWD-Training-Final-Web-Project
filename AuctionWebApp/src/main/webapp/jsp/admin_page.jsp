<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="customtags" prefix="ctag"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="css/base.css">
<link rel="stylesheet" href="css/table.css">
<link rel="stylesheet" href="css/skeleton.css">
<link rel="stylesheet" href="css/screen.css">
<link rel="shortcut icon" href="images/favicon.png">
<link href='http://fonts.googleapis.com/css?family=Oswald:400,300,700' rel='stylesheet' type='text/css'>
<title>Администратор</title>
</head>
<body>
	<div id="header">
		<div class="container">
			<div class="logo">
				<a href="index.jsp"><img src="images/logo.png" alt="Logo" /></a>
			</div>
		</div>
	</div>
	<!-- header ends here -->
	<form action="<c:url value="/Controller"/>" method="GET">
		<input type="hidden" name="command" value="log_out">
		<input type="submit" value="Выйти">
	</form>
	<form action="<c:url value="/Controller"/>" method="GET">
		<input type="hidden" name="command" value="show_lots_details">
		<input type="submit" value="Просмотреть лоты системы">
	</form>
	<pre>
	
	</pre>
	<h3>Приветствую, Администратор!</h3>
	<h2>Пользователи системы</h2>
	<table>
		<tr>
			<th>Номер ID</th>
			<th>Имя</th>
			<th>Фамилия</th>
			<th>Email</th>
			<th>Дополнительно</th>
			
		</tr>
		<c:forEach items="${Users }" var="user">
			<tr>
				<td>${user.id }</td>
				<td>${user.firstName }</td>
				<td>${user.lastName }</td>
				<td>${user.email }</td>
				<td>
					<form action="<c:url value="/Controller"/>" method="GET">
						<input type="hidden" name="command"	value="show_user_details">
						<input type="hidden" name="user_id" value="${user.id }">
						<button type="submit">Показать подробности профиля пользователя</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<ctag:footer/>
</body>
</html>