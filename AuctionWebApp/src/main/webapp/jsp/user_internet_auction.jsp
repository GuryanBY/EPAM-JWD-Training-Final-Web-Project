<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../WEB-INF/jspf/locale.jspf"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/base.css">
<link rel="stylesheet" href="css/table.css">
<link rel="stylesheet" href="css/skeleton.css">
<link rel="stylesheet" href="css/screen.css">
<link rel="shortcut icon" href="images/favicon.png">
<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro"
	rel="stylesheet">
<title>${internet_title }</title>
</head>
<body>
	<%@ include file="../WEB-INF/jspf/header.jspf"%>
	<form action="<c:url value="/Controller"/>" method="GET">
		<input type="hidden" name="command" value="account_main_page">
		<input type="submit" value="${operation_button_continue }">
	</form>
	<pre>
	</pre>
	
	<div>
		<h3>${internet_maintitle }</h3>
	</div>
	<pre>
	</pre>
	<div>

		<h4>${internet_rule_title }:</h4>
		<h5>${internet_rule_content }</h5>
	</div>
	<pre>
	</pre>
	<div>
		<c:set var="message" scope="request" value="${UserIsBlocked }" />
		<c:if test="${not empty message }">
			<div>${message }</div>
		</c:if>
	</div>

	<div>
		<c:set var="message" scope="request" value="${BidAccept }" />
		<c:if test="${not empty message }">
			<div>${message }</div>
		</c:if>
	</div>
	<div>
		<c:set var="message" scope="request" value="${invalidInputData }" />
		<c:if test="${not empty message }">
			<div>${message }</div>
		</c:if>
	</div>


	<c:choose>
		<c:when test="${empty ActiveInternetLots }">
			<p>${internet_emptylots }</p>
		</c:when>
		<c:when test="${not empty ActiveInternetLots }">
			<table>
				<tr>
					<th>${internet_table_name }</th>
					<th>${internet_table_startdate }</th>
					<th>${internet_table_enddate }</th>
					<th>${internet_bid }</th>
				</tr>
				<c:forEach items="${ActiveInternetLots }" var="lot">
					<tr>
						<td>${lot.lotName }</td>
						<fmt:parseDate value="${lot.startSellingDate}" var="parsedCurrentDate"  pattern="yyyy-MM-dd'T'HH:mm" />
						<td><fmt:formatDate type = "both" pattern = "HH:mm dd MMMM yyyy" value = "${parsedCurrentDate}" /></td>
						<fmt:parseDate value="${lot.endSellingDate}" var="parsedCurrentDate"  pattern="yyyy-MM-dd'T'HH:mm" />
						<td><fmt:formatDate type = "both" pattern = "HH:mm dd MMMM yyyy" value = "${parsedCurrentDate}" /></td>
						<td><form action="<c:url value="/Controller"/>" method="POST">
								<input type="hidden" name="command" value="make_internet_bid">
								<input type="hidden" name="lot_id" value="${lot.id }"> 
								<input type="text" name="price">
								<input type="submit" value="${internet_makebid }"><br>
							</form></td>
						<c:if test="${lot.buyerId == User.id }">
							<td>Ставка уже сделана</td>
						</c:if>

					</tr>
				</c:forEach>
			</table>
		</c:when>
	</c:choose>
	<%@ include file="../WEB-INF/jspf/footer.jspf"%>
</body>
</html>