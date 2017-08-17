<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="customtags" prefix="ctag"  %>
<!DOCTYPE html>
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
<title>Профиль пользователя</title>
</head>
<body>
	<%@ include file="../WEB-INF/jspf/header.jspf"%>
	<a href="<c:url value="/index.jsp"/>">На главную страницу
		администратора</a>
	<hr>
	<table>
		<tr>
			<td class="noborder">
				<h3>Подробности профиля:</h3>
				<p>Id пользователя: ${UserInfo.id }</p>
				<p>Имя: ${UserInfo.firstName }</p>
				<p>Фамилия: ${UserInfo.lastName }</p>
				<p>Email: ${UserInfo.email }</p>
				<p>Телефон: ${UserInfo.phone }</p>
				<p>Дата регистрации: ${UserInfo.registrationDate }</p>
				<p>Текущий статус блокировки :
					<c:choose>
						<c:when test="${UserInfo.statusId == 1 }">Заблокирован</c:when>
						<c:when test="${UserInfo.statusId == 2 }">Не заблокирован</c:when>
					</c:choose>
				</p>
			    <p>
			    <form action="<c:url value="/Controller"/>" method="POST">
						<input type="hidden" name="command" value="change_user_status">
						<input type="hidden" name="user_id" value="${UserInfo.id }">
						<c:if test="${UserInfo.statusId == 1 }">
							<input type="submit" value="Разблокировать">
						</c:if>
						<c:if test="${UserInfo.statusId == 2 }">
							<input type="submit" value="Заблокировать">
						</c:if>
				</form>
		    </p>
		</td>
	</table>
	
	 
	<hr>
	<h3>Лоты, которые разместил пользователь:</h3>

	<c:set var="lots" scope="session" value="${SellerLots }" />
	<c:choose>
		<c:when test="${empty SellerLots }">
			<p>У пользователя нет выставленных лотов</p>
		</c:when>
		<c:when test="${not empty SellerLots }">
			<table>
				<tr>
					<th>Название лота</th>
					<th>Количество</th>
					<th>Дата начала торгов</th>
					<th>Стоимость</th>
					<th>Управление</th>
				</tr>
				<c:forEach items="${SellerLots }" var="lot">
					<tr>
						<td>${lot.lotName }</td>
						<td>${lot.goodAmount }</td>
						<td>${lot.startSellingDate }</td>
						<td>${lot.endPrice}</td>
						<td><form action="<c:url value="/Controller"/>" method="POST">
								<input type="hidden" name="command" value="delete_lot">
								<input type="hidden" name="lot_id" value="${lot.id }"> <input
									type="hidden" name="user_id" value="${UserInfo.id }">
								<button type="submit">Удалить лот</button>
							</form></td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
	</c:choose>
	<hr>
	<h3>Лоты, в которых участвует пользователь:</h3>
	<c:set var="boughtLots" scope="session" value="${BuyerLots }" />
	<c:choose>
		<c:when test="${empty BuyerLots }">
			<p>У пользователя нет купленных лотов</p>
		</c:when>
		<c:when test="${not empty BuyerLots }">
			<table>
				<tr>
					<th>Название лота</th>
					<th>Количество</th>
					<th>Дата окончания торгов</th>
					<th>Стоимость</th>
				</tr>
				<c:forEach items="${BuyerLots }" var="lot">
					<tr>
						<td>${lot.lotName }</td>
						<td>${lot.goodAmount }</td>
						<td>${lot.endSellingDate }</td>
						<td>${lot.endPrice}</td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
	</c:choose>

<ctag:footer/>

</body>
</html>