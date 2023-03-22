<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>Your Donation Listings</h1>

	<table>
		<thead>
			<tr>
				<th>ID</th>

				<th>Address</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${userDonationListings}" var="listing">
				<tr>
					<td>${listing.id}</td>

					<td>${listing.donationAddress}</td>

					<td><form action="singleListing.do" method="get">
  <input type="hidden" name="id" value="${listing.id}">
  <input type="hidden" name="listing_type" value="donation">
  <button type="submit">View Donation Listing</button>
</form></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>