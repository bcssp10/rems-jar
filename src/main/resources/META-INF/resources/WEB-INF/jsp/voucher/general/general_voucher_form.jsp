<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<jsp:include page="../../fragment/header.jsp" />
<body>
	<div class="container">

		<h1>
			M Rehan Enterprises<br>
		</h1>
		<c:choose>
			<c:when test="${general_voucher['new']}">
				<c:set var="action" value="/voucher/general/add" />
				<c:set var="button" value="Save" />
				<h2 style="text-align: center">
					Add General Voucher<br> <br>
				</h2>
			</c:when>
			<c:otherwise>
				<c:set var="action" value="/voucher/general/update/${general_voucher.generalVoucherId}" />
				<c:set var="button" value="Update" />
				<h2 style="text-align: center">
					Edit General Voucher<br> <br>
				</h2>
			</c:otherwise>
		</c:choose>

		<form:form class=".form-horizontal" action="${action}" method="post"
			modelAttribute="general_voucher">
			<form:hidden class="form-control" value="${general_voucher.generalVoucherId}"
				path="generalVoucherId" />

			<div class="col-xs-12">

				<div class="col-xs-3 form-group">
					<label for="date">Date</label>
					<fmt:formatDate pattern="dd/MM/yyyy" value="${general_voucher.date}"
						var="date" />
					<form:input type="text" path="date" class=" form-control"
						value="${date}" />
				</div>

				<div class="col-xs-3 form-group">
					<label for="cashPaidTo.partyId">Debit Paid To</label>
					<form:select path="cashPaidTo.partyId" class="form-control">
						<form:options itemValue="partyId" itemLabel="name" items="${partyList}" />
					</form:select>
				</div>
				<div class="col-xs-3 form-group">
					<label for="account.accountId">Account</label>
					<form:select path="account.accountId" class="form-control">
						<form:options itemValue="accountId" itemLabel="name" items="${accountList}" />
					</form:select>
				</div>
				<div class="col-xs-3 form-group">
					<label for="amount">Amount</label>
					<form:input type="text" class="form-control" path="amount" />
				</div>
				<div class="col-xs-3 form-group">
					<label for="cashPaidBy.partyId">Credit Paid By</label>
					<form:select path="cashPaidBy.partyId" class="form-control">
						<form:options itemValue="partyId" itemLabel="name" items="${partyList}" />
					</form:select>
				</div>
				<div class="col-xs-6 form-group">
					<label for="detalis">Details</label>
					<form:input type="text" class="form-control" path="details" />
				    <br>
					<br>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-3"></div>
				<div class="col-xs-3"></div>
				<div class="col-xs-3">
					<button type="submit" class="btn btn-lg btn-primary btn-sx">${button}</button>
					<button type="button"
						onclick="location.href='<c:url value="/voucher/general" />'"
						class="btn btn-lg btn-primary btn-sx">Cancel</button>
				</div>
			</div>

		</form:form>

	</div>

</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#general_voucher_page").addClass('active');
	});
</script>

</html>

