<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<jsp:include page="../../../fragment/header.jsp" />
<body>
	<div class="container">

		<h1>
			M Rehan Enterprises<br>
		</h1>
		<form class=".form-horizontal" action="/voucher/general/ledger" method="post">

			<div class="col-xs-12">
				<div class="col-xs-3 form-group">
					<label for="date">From</label>
					<input id="from" name="from" type="date" class=" form-control" />
				</div>

				<div class="col-xs-3 form-group">
					<label for="date">To</label>
					<input id="to" name="to" type="date" class=" form-control" />
				</div>

				<div class="col-xs-3 form-group">
					<label for="mainParty">Main Party</label> 
					<select id="mainPartyId" name="mainPartyId" class="form-control">
						<c:forEach var="party" items="${partyList}">
							<option value="${party.partyId}">${party.name}</option>
						</c:forEach>
					</select>
				</div>

				<div class="col-xs-3 form-group">
					<label for="referenceParty">Reference Party</label> <select id="referencePartyId" name="referencePartyId"
						class="form-control">
						<c:forEach var="party" items="${partyList}">
							<option value="${party.partyId}">${party.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-3"></div>
				<div class="col-xs-3"></div>
				<div class="col-xs-3">
					<button type="submit" class="btn btn-lg btn-primary btn-sx">Generate</button>
					<button type="button"
						onclick="location.href='<c:url value="/party" />'"
						class="btn btn-lg btn-primary btn-sx">Cancel</button>
				</div>
			</div>

		</form>

	</div>

</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#dashboard_page").addClass('active');
	});
</script>

</html>

