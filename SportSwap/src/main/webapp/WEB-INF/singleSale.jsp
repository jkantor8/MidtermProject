<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<title>${item.name}</title>
</head>
<body>

<jsp:include page="nav.jsp" />


	
	
		<table class="table">
			<thead> 
				<tr>
					<th> Item Name </th>
					<th> Price </th>
					<th> Description </th>
					<th> Gender </th>
					<th> Brand </th>
				</tr>
				</thead>
				<tbody>
				<tr>
					<td> ${item.name }</td>	
					<td> ${item.price }</td>	
					<td> ${item.description }</td>	
					<td> ${item.Gender }</td>	
					<td> ${item.Brand }</td>	
				</tr>
				
				</tbody>
	
		</table>
				
		<a class="btn btn-light" href="update.do?saleListingId=${saleListing.id}"
				role="button">Update</a>
		<form action="deactivate.do" method="POST">
				<label for="saleListingId"></label> <input type="hidden" name="saleListingId"
					value="${saleListing.id}" /> <input type="submit" class="btn btn-dark"
					value="Delete" />
					</form>
	
	



<jsp:include page="footer.jsp" />
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD" crossorigin="anonymous"></script>
</body>
</html>