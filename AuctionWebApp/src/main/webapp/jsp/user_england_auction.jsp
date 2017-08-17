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
<title>${eng_title }</title>
</head>
<body>
	<%@ include file="../WEB-INF/jspf/header.jspf"%>
	<form action="<c:url value="/Controller"/>" method="GET">
		<input type="hidden" name="command" value="account_main_page">
		<input type="submit" value="${operation_button_continue }">
	</form>
	<pre>
	</pre>
	<div> <h3> ${eng_maintitle }</h3></div>
	<pre>
	</pre>
	<div>
	
	<h4>${eng_rule_title }:</h4>
	<h5>${eng_rule_content }</h5>
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


	<c:choose>
		<c:when test="${empty ActiveEnglandLots }">
			<p>${eng_noactivelots }</p>
		</c:when>
		<c:when test="${not empty ActiveEnglandLots }">
			<table>
				<tr>
					<th>${eng_table_name }</th>
					<th>${eng_table_startdate }</th>
					<th>${eng_table_winbid }</th>
					<th>${eng_table_bid }</th>
				</tr>
				<c:forEach items="${ActiveEnglandLots }" var="lot">
					<tr>
						<td>${lot.lotName }</td>
						<fmt:parseDate value="${lot.startSellingDate}" var="parsedCurrentDate"  pattern="yyyy-MM-dd'T'HH:mm" />
						<td> <fmt:formatDate type = "both" pattern = "HH:mm dd MMMM yyyy" value = "${parsedCurrentDate}" /></td>
						<td><fmt:formatNumber value="${lot.getEndPrice(locale)}" type="currency" pattern="#,##0.00;-#,##0.00"/></td>
						<td><form action="<c:url value="/Controller"/>" method="POST">
								<input type="hidden" name="command" value="make_bid"> 
								<input type="hidden" name="lot_id" value="${lot.id }"> 
								<input type="submit" value="${eng_table_suggestbid }"><br>
								<input type="text" name="new_price">
							</form></td>
						<c:if test="${lot.buyerId == User.id }">
							<td>${eng_table_currentwin }</td>
						</c:if>

					</tr>
				</c:forEach>
			</table>
		</c:when>
	</c:choose>

	<%@ include file="../WEB-INF/jspf/footer.jspf"%>
</body>
</html>