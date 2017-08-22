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
<link href='http://fonts.googleapis.com/css?family=Oswald:400,300,700'
	rel='stylesheet' type='text/css'>
<title>${blitz_title }</title>
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
		<h3>${blitz_maintitle }</h3>
	</div>
	<pre>
	</pre>
	<div>
	<h4>${blitz_rule_title }:</h4>
	<h5>${blitz_rule_content }</h5>
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
		<c:set var="message" scope="request" value="${BuyingAccept }" />
		<c:if test="${not empty message }">
			<div>${message }</div>
		</c:if>
	</div>
	<c:choose>
		<c:when test="${empty ActiveLots }">
			<p>${blitz_noactivelots }</p>
		</c:when>
		<c:when test="${not empty ActiveLots }">
			<table>
				<tr>
					<th>${blitz_table_name }</th>
					<th>${blitz_table_startdate }</th>
					<th>${blitz_table_price }</th>
					<th>${blitz_table_buy }</th>
				</tr>
				<c:forEach items="${ActiveLots }" var="lot">
					<tr>
						<td>${lot.lotName }</td>
						<fmt:parseDate value="${lot.startSellingDate}" var="parsedCurrentDate"  pattern="yyyy-MM-dd'T'HH:mm" />
						<td> <fmt:formatDate type = "both" pattern = "HH:mm dd MMMM yyyy" value = "${parsedCurrentDate}" /></td>
						<td><fmt:formatNumber value="${lot.getEndPrice(locale)}" type="currency" pattern="#,##0.00;-#,##0.00"/></td>

						<td><form action="<c:url value="/Controller"/>" method="POST">
								<input type="hidden" name="command" value="buy_blitz_lot">
								<input type="hidden" name="lot_id" value="${lot.id }"> 
								<input type="submit" value="${blitz_table_buttom }">
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
	</c:choose>
	<%@ include file="../WEB-INF/jspf/footer.jspf"%>
</body>
</html>