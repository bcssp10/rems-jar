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
			<c:when test="${party['new']}">
				<c:set var="action" value="/party/add" />
				<c:set var="button" value="Save" />
				<h2 style="text-align: center">
					Add Party<br> <br>
				</h2>
			</c:when>
			<c:otherwise>
				<c:set var="action" value="/party/update/${party.partyId}" />
				<c:set var="button" value="Update" />
				<h2 style="text-align: center">
					Edit Party<br> <br>
				</h2>
			</c:otherwise>
		</c:choose>

		<form:form class=".form-horizontal" action="${action}" method="post"
			modelAttribute="party">
			<form:hidden class="form-control" value="${party.partyId}"
				path="partyId" />

			<div class="col-xs-12">

				<div class="col-xs-3 form-group">
					<label for="name">Party Name</label>
					<form:input type="text" class="form-control" path="name" />
				</div>
				<div class="col-xs-3 form-group">
					<label for="phone">Phone</label>
					<form:input type="text" class="form-control" path="phone" />
				</div>
				<div class="col-xs-3 form-group">
					<label for="address">Address</label>
					<form:input type="text" class="form-control" path="address" />
				</div>
				<div class="col-xs-3 form-group">
					<label for="debitOB">Debit OB</label>
					<form:input type="text" class="form-control" path="debitOB" />
				</div>
				<div class="col-xs-3 form-group">
					<label for="creditOB">Credit OB</label>
					<form:input type="text" class="form-control" path="creditOB" />
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