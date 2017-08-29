<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link href='http://fonts.googleapis.com/css?family=Oswald:400,300,700'
	rel='stylesheet' type='text/css'>
<title>Лоты системы</title>
</head>
<body>
	<%@ include file="../WEB-INF/jspf/header.jspf"%>
	<a href="<c:url value="/index.jsp"/>">На главную страницу
		администратора</a>
	<pre>
	</pre>
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

	<c:if test="${currentPage != 1}">
		<td><a href="<c:url value="Controller"/>?command=show_all_users&page=${currentPage - 1}">Предыдущая</a></td>
	</c:if>
	<table border="1" cellpadding="5" cellspacing="5">
		<tr>
			<c:forEach begin="1" end="${noOfPages}" var="i">
				<c:choose>
					<c:when test="${currentPage eq i}">
						<td>${i}</td>
					</c:when>
					<c:otherwise>
						<td><a
							href="<c:url value="Controller"/>?command=show_all_users&page=${i}">${i}</a></td>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</tr>
	</table>
	<c:if test="${currentPage lt noOfPages}">
		<td><a href="<c:url value="Controller"/>?command=show_all_users&page=${currentPage + 1}">Следующая</a></td>
	</c:if>
	<ctag:footer />
</body>
</html>