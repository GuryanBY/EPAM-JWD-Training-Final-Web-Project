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
	<h2>Лоты, размещённые в системе</h2>
	<table>
		<tr>
			<th>ID лота</th>
			<th>Название лота</th>
			<th>Тип аукциона</th>
			<th>Дата начала торгов</th>
			<th>Дата окончания торгов</th>
			<th>Стоимость</th>
			<th>Текущий статус блокировки</th>
			<th>Управление блокировкой</th>
		</tr>
		<c:forEach items="${lotList }" var="lotItem">
			<tr>
				<td>${lotItem.id }</td>
				<td>${lotItem.lotName }</td>
				<c:choose>
					<c:when test="${lotItem.auctionTypeId.equals(\"1\")}">
						<td>Интернет аукцион</td>
					</c:when>
					<c:when test="${lotItem.auctionTypeId.equals(\"2\") }">
						<td>Английский аукцион</td>
					</c:when>
					<c:when test="${lotItem.auctionTypeId.equals(\"3\") }">
						<td>Блиц аукцион</td>
					</c:when>
				</c:choose>
				<td>${lotItem.startSellingDate }</td>
				<c:choose>
					<c:when test="${lotItem.buyingDate == null }">
						<td>Лот ещё в продаже</td>
					</c:when>
					<c:when test="${lotItem.buyingDate != null }">
						<td>${lotItem.buyingDate }</td>
					</c:when>
				</c:choose>

				<td>${lotItem.endPrice}</td>
				<c:choose>
					<c:when test="${lotItem.statusId == 1 }">
						<td>Лот заблокирован</td>
					</c:when>
					<c:when test="${lotItem.statusId != 1 }">
						<td>Лот НЕ заблокирован</td>
					</c:when>
				</c:choose>

				<td>
					<form action="<c:url value="/Controller"/>" method="POST">
						<input type="hidden" name="command" value="change_lot_status">
						<input type="hidden" name="lot_id" value="${lotItem.id }">
						<c:if test="${lotItem.statusId == 1 }">
							<input type="submit" value="Разблокировать">
						</c:if>

						<c:if test="${lotItem.statusId == 2 }">
							<input type="submit" value="Заблокировать">
						</c:if>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>

	<c:if test="${currentPage != 1}">
		<td><a href="<c:url value="Controller"/>?command=show_lots_details_pagination&page=${currentPage - 1}">Предыдущая</a></td>
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
							href="<c:url value="Controller"/>?command=show_lots_details_pagination&page=${i}">${i}</a></td>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</tr>
	</table>
	<c:if test="${currentPage lt noOfPages}">
		<td><a href="<c:url value="Controller"/>?command=show_lots_details_pagination&page=${currentPage + 1}">Следующая</a></td>
	</c:if>
	<ctag:footer />
</body>
</html>