<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ include file="../WEB-INF/jspf/locale.jspf"%>
<!DOCTYPE html>  
<html>
<head>
<meta charset="utf-8">
<title>${add_good_title }</title>

<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">

<link rel="stylesheet" href="<c:url value="/css/base.css"/>">
<link rel="stylesheet" href="<c:url value="/css/skeleton.css"/>">
<link rel="stylesheet" href="<c:url value="/css/screen.css"/>">
<link rel="shortcut icon" href="<c:url value="../images/favicon.png"/>">
<link href='http://fonts.googleapis.com/css?family=Oswald:400,300,700'
	rel='stylesheet' type='text/css'>
</head>
<body>
	<%@ include file="../WEB-INF/jspf/header.jspf"%>
	<form action="<c:url value="/Controller"/>" method="GET">
		<input type="hidden" name="command" value="account_main_page">
		<input type="submit" value="${operation_button_continue }">
	</form>
	<pre>


    </pre>
	<div class="reg-form">
		<form action="<c:url value="/Controller"/>" method="post"
			class="login" name="testForm">
			<div>
				<span class="in"> ${add_good_name }:</span>
				<input type="text" name="name">
			</div>
			<div>
				<span class="in">${add_good_category }:</span>
				<select name="category">
                <option value="1">${add_good_category1 }</option>
                <option value="2">${add_good_category2 }</option>
                <option value="3">${add_good_category3 }</option>
                <option value="4">${add_good_category4 }</option>
                <option value="5">${add_good_category5 }</option>
                <option value="6">${add_good_category6 }</option>
                <option value="7">${add_good_category7 }</option>
                <option value="8">${add_good_category8 }</option>
                <option value="19">${add_good_category19 }</option>
            </select>
			</div>
			<div>
				<span class="in">${add_good_condition }:</span>
				<input type="radio" name="condition" value="1" checked> ${add_good_condition1 }<Br>
                <input type="radio" name="condition" value="2"> ${add_good_condition2 }<Br>
                <input type="radio" name="condition" value="3"> ${add_good_condition3 }<Br>
                <input type="radio" name="condition" value="4"> ${add_good_condition4 }<Br>
			</div>
			<div>
				<span class="in">${add_good_type }:</span>
				<input type="radio" name="auction_type" value="1" checked> ${add_good_type1 }<Br>
                <input type="radio" name="auction_type" value="2"> ${add_good_type2 }<Br>
                <input type="radio" name="auction_type" value="3"> ${add_good_type3 }<Br>
             </div>
             <div>
				<span class="in">${add_good_duration }:</span>
				<select name="duration">
                  <option value="7">7 ${add_good_days }</option>
                  <option value="15">15 ${add_good_days }</option>
                  <option value="30">30 ${add_good_days }</option>
                </select>
			</div>
			<div>
				<span class="in">${add_good_amount }:</span>
				<select name="good_amount">
                  <option value="1">1</option>
                  <option value="2">2</option>
                  <option value="3">3</option>
                  <option value="4">4</option>
                  <option value="5">5</option> 
                </select>
			</div>
			<div>
				<span class="in"> ${add_good_description }:</span>
				<textarea name="description" cols="33" rows="3"></textarea>
			</div>
			<div>
				<span class="in"> ${add_good_price}:</span>
				<input type="text" name="start_price">
			</div>
			
			<c:set var="message" scope="request" value="${invalidInputDataAddGood }" />
		<c:if test="${not empty message }">
			<p>${message }</p>
		</c:if>
			
			<input type="submit" value="${add_good_submit } "> 
			<input type="hidden" name="command" value="add_good" />
		</form>
	</div>
	<hr class="separator2">
	<%@ include file="../WEB-INF/jspf/footer.jspf"%>
	<script src="../js/reg.js"></script>
</body>
</html>