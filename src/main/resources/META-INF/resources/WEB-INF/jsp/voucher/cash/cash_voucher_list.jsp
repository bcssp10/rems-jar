<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<jsp:include page="../../fragment/header.jsp" />

<head>
        <link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
	     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://cdn.datatables.net/1.10.13/css/dataTables.bootstrap.min.css">
		<link rel="stylesheet" href="https://cdn.datatables.net/responsive/2.1.1/css/responsive.bootstrap.min.css">
		<!-- Google Fonts -->
		<link href="https://fonts.googleapis.com/css?family=Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i&subset=latin-ext" rel="stylesheet">

<style>
.table-striped > tbody > tr:nth-child(2n) > td, .table-striped > tbody > tr:nth-child(2n) > th {
   background-color: lightblue;
}
</style>

</head>
<body>
<br>

	<div class="container">
		<a href='<c:url value="/voucher/cash/add"/>'><button type="button"
				class="btn btn-primary btn-sx pull-right">Add New Cash Voucher</button> <br><br>
		</a>
        <div class="row">
			<div class="col-md-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">Cash Voucher List</h3>						
					</div>
					<br>
        <div class="table-responsive">
		<table class="table table-bordered dt-responsive nowrap table table-striped" id="rec_table">
			<thead style="background-color:#689efd">
				<tr>
				    <th>Actions</th>
					<th>No.</th>
					<th>Date</th>
					<th>Party</th>
					<th>Account</th>
					<th>Amount</th>
					<th>Payment Type</th>
                    <!-- babat -->
   					<th>For Payment of</th>
					<!--<th>Bank</th>
					<th>Branch</th>
					<th>Cheque No</th>
					 ReferenceName
					<th>Paid By</th> -->
				</tr>
			</thead>
			<tbody>
				<c:forEach var="cashVoucher" items="${cash_vouchers}">
					<tr>
					    <td><a href="/voucher/cash/${cashVoucher.cashVoucherId}">
						 <span title="Edit" class="glyphicon glyphicon-pencil" ></span></a>&nbsp;
				         <a href="/voucher/cash/delete/${cashVoucher.cashVoucherId}"> 
				         <span title="Delete" class="glyphicon glyphicon-trash"></span></a>&nbsp;
						<a href="/voucher/cash/print/${cashVoucher.cashVoucherId}">
						<span title="Print" class="glyphicon glyphicon-print"></span></a>
						</td>
						<td> 
						CP-<fmt:formatNumber minIntegerDigits="4" pattern="#" value="${cashVoucher.cashVoucherId}" /> 
						</td>
						<fmt:formatDate pattern="dd/MM/yyyy" value="${cashVoucher.date}"
							var="date" />
						<td>${date}</td>
						<td><a href="/voucher/cash/party/${cashVoucher.party.partyId}">${cashVoucher.party.name}</a></td>
						<td>${cashVoucher.account.name}</td>
						<td>${cashVoucher.amount}</td>
						<td>${cashVoucher.paymentType}</td>
						<td>${cashVoucher.forPaymentOf}</td>
						<%-- <td>${cashVoucher.bankName}</td>
						<td>${cashVoucher.bankBranch}</td>
						<td>${cashVoucher.chequeNo}</td>
						<td>${cashVoucher.cashReceivedBy}</td> --%>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
		</div>
		</div>
	</div>
	</div>
</body>

<script type="text/javascript">
$(document).ready(function() {
    $('#rec_table').DataTable();
    $("#cash_voucher_page").addClass('active');
} );
</script>
    
     <script src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
     <script src="https://cdn.datatables.net/1.10.13/js/dataTables.bootstrap.min.js"></script> 
     <script src="https://cdn.datatables.net/responsive/2.1.1/js/dataTables.responsive.min.js"></script>
     <script src="https://cdn.datatables.net/responsive/2.1.1/js/responsive.bootstrap.min.js"></script> 

</html>