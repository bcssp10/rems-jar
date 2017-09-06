<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<jsp:include page="../fragment/header.jsp" />
<body>
	<div class="container">

		<h1>
			M Rehan Enterprises<br>
		</h1>
		<c:choose>
			<c:when test="${account['new']}">
				<c:set var="action" value="/account/add" />
				<c:set var="button" value="Save" />
				<h2 style="text-align: center">
					Add Account<br> <br>
				</h2>
			</c:when>
			<c:otherwise>
				<c:set var="action" value="/account/update/${account.accountId}" />
				<c:set var="button" value="Update" />
				<h2 style="text-align: center">
					Edit Account<br> <br>
				</h2>
			</c:otherwise>
		</c:choose>

		<form:form class=".form-horizontal" action="${action}" method="post"
			modelAttribute="account">
			<form:hidden class="form-control" value="${account.accountId}"
				path="accountId" />

			<div class="col-xs-12">

				<div class="col-xs-3 form-group">
					<label for="name">Name</label>
					<form:input type="text" class="form-control" path="name" />
				</div>
				<div class="col-xs-3 form-group">
					<label for="phone">Detail</label>
					<form:input type="text" class="form-control" path="detail" />
				</div>

			</div>
			<div class="row">
					<div class="col-xs-3"></div>
					<div class="col-xs-3"></div>
					<div class="col-xs-3">
					<button type="submit"
							class="btn btn-lg btn-primary btn-sx">${button}</button>
						<button type="button" onclick="location.href='<c:url value="/party" />'"
							class="btn btn-lg btn-primary btn-sx">Cancel</button>
					</div>
				</div>

		</form:form>

	</div>
	 
</body>
<script type="text/javascript">
$(document).ready(function() {
    $("#dashboard_page").addClass('active');
} );
</script>

</html>