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
			<label for="username">username: </label>
  			<input type="text" id="username" name="username">
  			<label for="password">password: </label>
  			<input type="text" id="password" name="password">
  			<input type="submit" value="login" name="login" class="btn btn-outline-red">
		</form>
	</div>
    </c:when>    
    <c:otherwise>
		<div class="col-md-auto">
         <p>Welcome to SportSwap, ${loggedInUser.username}!</p>
         </div>
         <div class="col-md-auto">
         <form method="POST" action="home.do">
         <input type="submit" value="logout" name="logout" class="btn btn-outline-red">
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
<h2>${loggedInUser.username}'s Account Details</h2>

<form action="account.do" method="POST">
<label for="username">username: </label>
  	<input type="text" id="username" name="username" value="${loggedInUser.username}"><br>
  	<label for="password">password: </label>
  	<input type="text" id="password" name="password" value="${loggedInUser.password}"><br>
  	<label for="email">email:</label>
 	<input type="email" id="email" name="email" value="${loggedInUser.email}"><br>
 	<label for="address">address:</label>
 	<input type="text" id="address" name="address" value="${loggedInUser.getUserAddress().getStreet() }"><br>
	<label for="address2">address (Apt, Unit, etc.):</label>
 	<input type="text" id="address2" name="address2" value="${loggedInUser.getUserAddress().getStreet2()}"><br>
 	<label for="city">city: </label>
 	<input type="text" id="city" name="city" value="${loggedInUser.getUserAddress().getCity()}"><br>
 	<label for="state_province">state/province: </label>
 	<input type="text" id="state_province" name="state_province" value="${loggedInUser.getUserAddress().getState()}"><br>
 	<label for="postalCode">postal code: </label>
 	<input type="text" id="postalCode" name="postalCode" value="${loggedInUser.getUserAddress().getPostalCode()}"><br>
 	<label for="country">country: </label>
 	<input type="text" id="country" name="country" value="${loggedInUser.getUserAddress().getCountryCode()}"><br>
 	<h4>favorite sports:</h4>
 	<ul>
 	<c:forEach var="sport" items="${favSports}">
 	<li>${sport.getName()}</li>
 	</c:forEach>
 	</ul>
 	<input type="submit" name="submit" value="Update Account" class="btn btn-outline-red">
 	<input type="submit" name="logout" value="Delete Account" class="btn btn-outline-red">
</form>
</div>

<div class="col">
<h2>Messages and Listings</h2>
<a class="btn btn-outline-red" href= "/messages">Show Messages</a><br>
<a class="btn btn-outline-red" href= "viewUserSaleListings.do">Show My Sales Listings</a><br>
<a class="btn btn-outline-red" href= "viewUserDonationListings.do">Show My Donation Listings</a><br>
<a class="btn btn-outline-red" href= "viewUserSwapListings.do">Show My Swap Listings</a>
<p>${result }</p>
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