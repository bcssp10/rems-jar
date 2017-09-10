<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<jsp:include page="../fragment/header.jsp" />
<head>
<style>
p {
	text-decoration: none;
	text-align: center;
	border-bottom: 1px solid black;
	font-size: 110%;
}
label{
font-size: 110%;
}
@page {
	size: auto;
	margin: 5mm;
}

h1,h4,h5 {
	text-align: center;
}
</style>

</head>

<body>
	<h4>M Rehan Enterprises</h4>
	<h5>G-47, AL-Latif Center, Main Boulevard Gulberg-3 Lahore</h5>
	<h5>0300-9676164/0300-8676164</h5>
	<br>
	<br>
	<div class="container">
		<form:form class="form-inline" modelAttribute="receipt">
			<form:hidden class="form-control" value="${receipt.receiptId}"
				path="receiptId" />



			<div class="col-xs-12">
				<div class="col-xs-4 form-group row">
					<label for="date" class="col-sm-2 col-form-label">Date</label>
					<fmt:formatDate pattern="dd/MM/yyyy" value="${receipt.date}"
						var="date" />
					<div class="col-sm-10">
						<p>
							<c:out value="${date}" />
							&nbsp;
						</p>
					</div>
				</div>
				<div class="col-xs-4 form-group row">
					<h3 style="text-align: center">Cash Receipt</h3>
				</div>

				<div class="col-xs-4 form-group row">
					<label for="receiptId" class="col-sm-2 col-form-label">No</label>
					<div class="col-sm-10">
						<p>
							<c:out value="${receipt.receiptId}" />
							&nbsp;
						</p>
						<br>
					</div>
				</div>
				<div class="col-xs-10 form-group row">
					<label for="cashReceivedFrom" class="col-sm-2 col-form-label">Received
						From</label>
					<div class="col-sm-4">
						<p>
							<c:out value="${receipt.party.name}" />
							&nbsp;
						</p>
					</div>
				</div>

				<div class="col-xs-10 form-group row">
					<label for="amount" class="col-sm-2 col-form-label">Amount</label>
					<div class="col-sm-4">
						<p>
							<c:out value="Rs:${receipt.amount}/-" />
							&nbsp;
						</p>
					</div>
				</div>

				<div class="col-xs-10 form-group row">
					<label for="paymentType" class="col-sm-2 col-form-label">Payment
						Type</label>
					<div class="col-sm-4">
						<p>
							<c:out value="${receipt.paymentType}" />
							&nbsp;
						</p>
					</div>
				</div>

				<div class="col-xs-10 form-group row">
					<label for="forPaymentOf" class="col-sm-2 col-form-label">For
						Payment of</label>
					<div class="col-sm-10">
						<p>
							<c:out value="${receipt.forPaymentOf}" />
							&nbsp;
						</p>
						<br>
					</div>
				</div>

				<div class="col-xs-3 form-group">
					<label for="bankName">Bank</label>
					<p>
						<c:out value="${receipt.bankName}" />
						&nbsp;
					</p>
				</div>
				<div class="col-xs-3 form-group">
					<label for="bankBranch">Branch</label>
					<p>
						<c:out value="${receipt.bankBranch}" />
						&nbsp;
					</p>
				</div>
				<div class="col-xs-3 form-group">
					<label for="chequeNo">Cheque No</label>
					<p>
						<c:out value="${receipt.chequeNo}" />
						&nbsp;
					</p>
				</div>
				<div class="col-xs-3 form-group">
					<label for="cashReceivedBy">Received By</label>
					<p>
						<c:out value="${receipt.cashReceivedBy}" />
						&nbsp;
					</p>
					<br> <br>
				</div>
			</div>
				<div class="col-xs-4">
					<label class="col-sm-2 col-form-label">Receiver</label>
					<div class="col-sm-3">
					</div>
				</div>
				<div class="col-xs-4">
					<label class="col-sm-2 col-form-label">Accountant</label>
				</div>
				<div class="col-xs-4">
					<label class="col-sm-2 col-form-label">Proprietor</label>
				</div>
				<div class="col-xs-4"></div>
				<button id="printpagebutton" type="button"
					class="btn btn-primary btn-sx pull-right" onclick="myFunction()">Print
					Receipt</button>
			
		</form:form>
	</div>

</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#receipt_page").addClass('active');
	});
	function myFunction() {

		var printButton = document.getElementById("printpagebutton");
		printButton.style.visibility = 'hidden';
		document.title = "M Rehan Enterprises";
		window.print();
		printButton.style.visibility = 'visible';
	}
</script>

</html>

