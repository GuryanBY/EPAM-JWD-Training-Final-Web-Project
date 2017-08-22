<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:choose>
	<c:when test="${User.roleId == 1}">
		<jsp:include page="jsp/admin_page.jsp"></jsp:include>
	</c:when>
	<c:when test="${User.roleId == 2}">
		<jsp:include page="jsp/user_page.jsp"></jsp:include>
	</c:when>
	<c:otherwise>
		<jsp:include page="jsp/login.jsp"></jsp:include>
	</c:otherwise>
</c:choose>