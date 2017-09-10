<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../fragment/header.jsp" />
<body>

<div class="container">
	<h1>Something Went Wrong! Please contact system administrator.</h1>
</div>

<%-- 
<h4>
<c:forEach items="${exception.stackTrace}" var="element">
    <c:out value="${element}" />
</c:forEach>
</h4>
--%>

</body>
</html>