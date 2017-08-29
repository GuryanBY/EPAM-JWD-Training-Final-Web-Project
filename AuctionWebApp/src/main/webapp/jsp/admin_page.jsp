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
<link href='http://fonts.googleapis.com/css?family=Oswald:400,300,700'
	rel='stylesheet' type='text/css'>
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
		<input type="hidden" name="command" value="log_out"> <input
			type="submit" value="Выйти">
	</form>


	<pre>
	
	</pre>
	<div align = center>
	<h3>Приветствую, Администратор!</h3>
  
	<h4>Статистика системы аукцион:</h4>
	<h5>Всего зарегистрировано пользователей: ${numberOfUsers }</h5>
	
	<h5>Из них заблокировано: ${numberOfBlockedUsers }</h5>
    <pre>
	</pre>
	<form action="<c:url value="/Controller"/>" method="GET">
		<input type="hidden" name="command" value="show_all_users"> <input
			type="submit" value="Все пользователи системы">
	</form>
	<pre>
	</pre>
	<div>
		<h5>Всего размещено лотов: ${numberOfLots }</h5>
	</div>
	<div >
		<h5>Из них участвуют в Интернет аукционах: ${internetLots }</h5>
	</div>
	<div>
		<h5>Из них участвуют в Английских аукционах: ${englandLots }</h5>
	</div>
	<div>
		<h5>Из них участвуют в Блиц аукционах: ${blitzLots }</h5>
	</div>
	<pre>
	</pre>
	<form action="<c:url value="/Controller"/>" method="GET">
		<input type="hidden" name="command" value="show_lots_details">
		<input type="submit" value="Просмотреть лоты системы">
	</form>
	<pre>
	</pre>
	<form action="<c:url value="/Controller"/>" method="GET">
		<input type="hidden" name="command"
			value="show_lots_details_pagination"> <input type="submit"
			value="Лоты - постраничный вывод">
	</form>
	<pre>
	</pre>
	</div>
	<ctag:footer />
</body>
</html>