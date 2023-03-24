<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSP -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--  end -->
<!DOCTYPE html>
<html>
<head>
<!--  FONTS GOOGLE -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Russo+One">
<!--  CSS (BOOTSTRAP) -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<link rel="stylesheet" href="css/main.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/main.css">
<!-- end -->
<meta charset="UTF-8">
<title>SportSwap - Trade Up!</title>
</head>

<body>
<header class="container">
<div class="row p-2">
	<h1>SportSwap</h1>
</div>
<div class="row justify-content-end">
<c:choose>
    <c:when test="${loggedInUser==null}">
   <div class="col-md-auto">
        <form method="POST" action="home.do">
			<label for="username">Username: </label>
  			<input type="text" id="username" name="username">
  			<label for="password">Password: </label>
  			<input type="password" id="password" name="password">
  			<input type="submit" value="Login" name="login">
		</form>
	</div>
    </c:when>    
    <c:otherwise>
		<div class="col-md-auto">
         <p>Welcome to SportSwap, ${loggedInUser.username}!</p>
         </div>
         <div class="col-md-auto">
         <form method="POST" action="home.do">
         <input type="submit" value="Logout" name="logout">
         </form>
         </div>
    </c:otherwise>
</c:choose>
</div>

<div class="row">
<jsp:include page="nav.jsp" />
</div>
</header>
<main class="container p-4">

<div class="row justify-content-center">
<div class="col-6">
<h2>Create an Account</h2>

<form action="createAccount.do" method="POST">
<div class="form-group">
	<label for="username">Username: </label>
  	<input type="text" id="username" name="username" required>
  	</div>
  	<div class="form-group">
  	<label for="password">Password: </label>
  	<input type="text" id="password" name="password" required>
  	</div>
  	<div class="form-group">
  	<label for="email">Email:</label>
 	<input type="email" id="email" name="email" required>
 	</div>
 	<div class="form-group">
 	<label for="address">Street Address:</label>
 	<input type="text" id="address" name="address" required>
 	</div>
 	<div class="form-group">
	<label for="address2">Apt, Unit, etc.:</label>
 	<input type="text" id="address2" name="address2">
 	</div>
 	<div class="form-group">
 	<label for="city">City: </label>
 	<input type="text" id="city" name="city" required>
 	</div>
	<div class="form-group">
 	<label for="state_province">State/Province Abbreviation: </label>
 	<input type="text" id="state_province" name="state_province" maxlength="2" required>
 	</div>
	<div class="form-group">
 	<label for="postalCode">Postal Code: </label>
 	<input type="text" id="postalCode" name="postalCode" required>
 	</div>
 	<div class="form-group">
 	<label for="country">Country Abbreviation:</label>
 	<input type="text" id="country" name="country" maxlength="2" required>
 	</div>
 	<div class="form-group">
 	<label for="sport1">Favorite Sport:</label>
		<select name="sport1" id="sport1" required>
  			<option value="1">Boxing</option>
  			<option value="2">Hockey</option>
  			<option value="3">Golf</option>
  			<option value="4">Football</option>
  			<option value="5">Soccer</option>
  			<option value="6">Curling</option>
  			<option value="7">Baseball</option>
  			<option value="8">Softball</option>
  			<option value="9">Basketball</option>
  			<option value="10">Weightlifting</option>
  			<option value="11">Track and Field</option>
  			<option value="12">Wrestling</option>
  			<option value="13">Bicycling</option>
  			<option value="14">Swimming</option>
		</select>
		</div>
		<div class="form-group">
		<label for="sport2">Favorite Sport:</label>
		<select name="sport2" id="sport2" required>
  			<option value="1">Boxing</option>
  			<option value="2">Hockey</option>
  			<option value="3">Golf</option>
  			<option value="4">Football</option>
  			<option value="5">Soccer</option>
  			<option value="6">Curling</option>
  			<option value="7">Baseball</option>
  			<option value="8">Softball</option>
  			<option value="9">Basketball</option>
  			<option value="10">Weightlifting</option>
  			<option value="11">Track and Field</option>
  			<option value="12">Wrestling</option>
  			<option value="13">Bicycling</option>
  			<option value="14">Swimming</option>

		</select>
		</div>
 	
 	<input type="submit" name="submit" value="Create Account" class="btn btn-outline-red">
</form>
</div>
</div>
</main>
<jsp:include page="footer.jsp" />
<!--  BOOTSTRAP JAVASCRIPT -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD" crossorigin="anonymous"></script>
<!--  end  -->
</body>
</html>