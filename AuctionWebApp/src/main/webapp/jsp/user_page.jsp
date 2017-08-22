<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../WEB-INF/jspf/locale.jspf"%>
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
<title>${user_main_title }</title>
</head>
<body>
	<%@ include file="../WEB-INF/jspf/header.jspf"%>


	<form action="<c:url value="/Controller"/>" method="GET">
		<input type="hidden" name="command" value="log_out"> <input
			type="submit" value="${user_main_logout }">
	</form>
	<pre>


    </pre>
	<div class="center">
		<h2>${user_main_hello}${User.firstName } ${User.lastName } !</h2>
		<pre>
        </pre>
		<form action="<c:url value="/Controller"/>" method="GET">
			<input type="hidden" name="command" value="internet_auction">
			<input type="submit" value="${user_main_internetauction } ">
		</form>
		<form action="<c:url value="/Controller"/>" method="GET">
			<input type="hidden" name="command" value="england_auction">
			<input type="submit" value="${user_main_englandauction }">
		</form>
		<form action="<c:url value="/Controller"/>" method="GET">
			<input type="hidden" name="command" value="blitz_auction"> 
			<input type="submit" value="${user_main_blitzauction }">
		</form>

		<h3>${user_main_selling }:</h3>

		<c:set var="lots" scope="session" value="${Lots }" />
		<c:choose>
			<c:when test="${empty Lots }">
				<p>${user_main_empty }</p>
			</c:when>
			<c:when test="${not empty Lots }">
				<table>
					<tr>
						<th>${user_main_lotname }</th>
						<th>${user_main_goodamount }</th>
						<th>${user_main_startdate }</th>
						<th>${user_main_cost }</th>
					</tr>
					<c:forEach items="${Lots }" var="lot">
						<tr>
							<td>${lot.lotName }</td>
							<td>${lot.goodAmount }</td>
							<fmt:parseDate value="${lot.startSellingDate}" var="parsedCurrentDate"  pattern="yyyy-MM-dd'T'HH:mm" />
							<td> <fmt:formatDate type = "both" pattern = "HH:mm dd MMMM yyyy" value = "${parsedCurrentDate}" /></td>
							<td><fmt:formatNumber value="${lot.getEndPrice(locale)}" type="currency"
                                              pattern="#,##0.00;-#,##0.00"/></td>
						</tr>
					</c:forEach>
				</table>
			</c:when>
		</c:choose>
		<pre>
        </pre>
			<a href="jsp/add_good.jsp"><button> ${reg_add_good }</button></a>
		<pre>
        </pre>
		<h3>${user_main_bying }:</h3>
		<c:set var="boughtLots" scope="session" value="${BoughtLots }" />
		<c:choose>
			<c:when test="${empty BoughtLots }">
				<p>${user_main_emptybying }</p>
			</c:when>
			<c:when test="${not empty BoughtLots }">
				<table>
					<tr>
						<th>${user_main_lotname }</th>
						<th>${user_main_goodamount }</th>
						<th>${user_main_enddate }</th>
						<th>${user_main_cost }</th>
					</tr>
					<c:forEach items="${BoughtLots }" var="lot">
						<tr>
							<td>${lot.lotName }</td>
							<td>${lot.goodAmount }</td>
							<fmt:parseDate value="${lot.endSellingDate}" var="parsedCurrentDate"  pattern="yyyy-MM-dd'T'HH:mm" />
							<td> <fmt:formatDate type = "both" pattern = "HH:mm dd MMMM yyyy" value = "${parsedCurrentDate}" /></td>
							<td><fmt:formatNumber value="${lot.getEndPrice(locale)}" type="currency"
                                              pattern="#,##0.00;-#,##0.00"/></td>
						</tr>
					</c:forEach>
				</table>
			</c:when>
		</c:choose>
	</div>
	<pre>


    </pre>
	<%@ include file="../WEB-INF/jspf/footer.jspf"%>
</body>
</html>