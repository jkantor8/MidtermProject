<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSP -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--  end -->
<!DOCTYPE html>
<html>
<head>
<!--  CSS (BOOTSTRAP) -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- end -->
<meta charset="UTF-8">
<title>SportSwap - Trade up!</title>
</head>
<body>
${listing_type }
<h1>Create a new item</h1>
<p>Please enter the item information.
<form action="item_create.do" method="POST">
	<label for="name">name:</label>
	<input type="text" id="name" name="name"><br>
	<label for="description">description:</label>
	<input type="text" id="description" name="description"><br>
	<label for="imageUrl">image url:</label>
	<input type="text" id="imageUrl" name="imageUrl"><br>
	<label for="brand">brand:</label>
	<input type="text" id="brand" name="brand"><br>
	<label for="sportId">sport:</label>
		<select name="sportId" id="sportId">
  			<option value="1">Boxing</option>
  			<option value="2">Hockey</option>
  			<option value="3">Golf</option>
  			<option value="4">Football</option>
  			<option value="5">Soccer</option>
  			<option value="6">Curling</option>

		</select><br>
	<label for="conditionId">condition:</label>
		<select name="conditionId" id="conditionId">
  			<option value="1">New</option>
  			<option value="2">Lightly Used</option>
  			<option value="3">Used</option>
  			<option value="4">Heavily Used</option>
		</select><br>
	<label for="gender">gender:</label>
		<select name="gender" id="gender">
  			<option value="Unisex">Unisex</option>
  			<option value="Female">Female</option>
  			<option value="Male">Male</option>
		</select><br>
		<label for="ageGroupId">age group:</label>
		<select name="ageGroupId" id="ageGroupId">
  			<option value="1">Youth</option>
  			<option value="2">Intermediate</option>
  			<option value="3">Adult</option>
		</select><br>
	
	<input type="submit" value="submit">
</form>


<jsp:include page="footer.jsp" />

<!--  BOOTSTRAP JAVASCRIPT -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD" crossorigin="anonymous"></script>
<!--  end  -->
</body>
</html>