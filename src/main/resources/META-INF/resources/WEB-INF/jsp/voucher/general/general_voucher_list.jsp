<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org">

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
p {
	text-align: center
}
</style>

</head>
<body>
<br>

	<div class="container">
	<c:choose>
			<c:when test="${successMsg ne null}">
				<div id="msg" th:if="${successMsg}"
					class="alert alert-success fade in" role="alert">
					<p th:text="">${successMsg}</p>
				</div>
			</c:when>
			<c:otherwise></c:otherwise>
		</c:choose>
		<a href='<c:url value="/voucher/general/add"/>'><button type="button"
				class="btn btn-primary btn-sx pull-right">Add New General Voucher</button><br><br>
		</a>
        <div class="row">
			<div class="col-md-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">General Voucher List</h3>						
					</div>
					<br>
        <div class="table-responsive">
		<table class="table table-bordered dt-responsive nowrap table table-striped" id="rec_table">
			<thead style="background-color:#689efd">
				<tr>
				    <th>Actions</th>
					<th>No.</th>
					<th>Date</th>
					<th>Debit</th>
					<th>Amount</th>
					<th>Credit</th>
					<th>Details</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="generalVoucher" items="${general_vouchers}">
					<tr>
					    <td><a href="/voucher/general/${generalVoucher.generalVoucherId}">
						 <span title="Edit" class="glyphicon glyphicon-pencil" ></span></a>&nbsp;
				         <a href="/voucher/general/delete/${generalVoucher.generalVoucherId}"> 
				         <span title="Delete" class="glyphicon glyphicon-trash"></span></a>&nbsp;
						<a href="/voucher/general/print/${generalVoucher.generalVoucherId}">
						<span title="Print" class="glyphicon glyphicon-print"></span></a>
						</td>
						<td> 
						${generalVoucher.generalVoucherId}
						</td>
						<fmt:formatDate pattern="dd/MM/yyyy" value="${generalVoucher.date}"
							var="date" />
						<td>${date}</td>
						<td><a href="/voucher/general/paidTo/${generalVoucher.cashPaidTo.partyId}">${generalVoucher.cashPaidTo.name}</a></td>
						<td>${generalVoucher.amount}</td>
						<td><a href="/voucher/general/paidBy/${generalVoucher.cashPaidBy.partyId}">${generalVoucher.cashPaidBy.name}</a></td>
						<td>${generalVoucher.details}</td>
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
    $("#general_voucher_page").addClass('active');
} );
$('#msg').delay(2000).fadeOut(1000);
</script>
    
     <script src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
     <script src="https://cdn.datatables.net/1.10.13/js/dataTables.bootstrap.min.js"></script> 
     <script src="https://cdn.datatables.net/responsive/2.1.1/js/dataTables.responsive.min.js"></script>
     <script src="https://cdn.datatables.net/responsive/2.1.1/js/responsive.bootstrap.min.js"></script> 

</html>