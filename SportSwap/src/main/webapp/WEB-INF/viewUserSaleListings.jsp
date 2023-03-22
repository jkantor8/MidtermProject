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

	<h1>Your Sale Listings</h1>

	<table>
		<thead>
			<tr>
				<th>ID</th>

				<th>Price</th>
				
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${userSaleListings}" var="listing">
				<tr>
					<td>${listing.id}</td>

					<td>${listing.price}</td>
<td><form action="singleListing.do" method="get">
  <input type="hidden" name="id" value="${listing.id}">
  <input type="hidden" name="listing_type" value="sale">
  <button type="submit">View Sale Listing</button>
</form></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>