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
  			<input type="text" id="password" name="password">
  			<input type="submit" value="Login" name="login" class="btn btn-outline-red">
		</form>
	</div>
    </c:when>    
    <c:otherwise>
		<div class="col-md-auto">
         <p>Welcome to SportSwap, ${loggedInUser.username}!</p>
         </div>
         <div class="col-md-auto">
         <form method="POST" action="home.do">
         <input type="submit" value="Logout" name="logout" class="btn btn-outline-red">
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

<div class="row">
<div class="col">
<h2>Create a New Address</h2>
<h4>Please enter address information below</h4>

<form action="address_create.do" method="POST">
	<div class="form-group">
	<label for="street">Street:</label>
	<input type="text" id="street" name="street" required>
	</div>
	<div class="form-group">
	<label for="street2">Apt #, Unit, etc.:</label>
	<input type="text" id="street2" name="street2" required>
	</div>
	<div class="form-group">
	<label for="city">City:</label>
	<input type="text" id="city" name="city" required>
	</div>
	<div class="form-group">
	<label for="stateProvince">State/Province Abbreviation:</label>
	<input type="text" id="stateProvince" name="stateProvince" maxLength="2" required>
	</div>
	<div class="form-group">
	<label for="postalCode">Postal Code:</label>
	<input type="text" id="postalCode" name="postalCode" required>
	</div>
	<div class="form-group">
	<label for="countryCode">Country Abbreviation:</label>
	<input type="text" id="countryCode" name="countryCode" maxLength="2" required>
	</div>
	<input type="submit" value="submit" class="btn btn-outline-red">
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