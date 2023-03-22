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
<title>SportSwap - Trade Up!</title>
</head>
<body>
<header>
<h1>Welcome to SportSwap</h1>
<jsp:include page="nav.jsp" />
</header>


<h2>Welcome, ${loggedInUser.username}!</h2>
<div class="container">
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
 	<p>favorite sports:</p>
 	<ul>
 	<c:forEach var="sport" items="${favSports}">
 	<li>${sport.getName()}</li>
 	</c:forEach>
 	</ul>
 	<input type="submit" name="submit" value="update_account">
 	<input type="submit" name="delete" value="delete account">
</form>
<br>


<a href= "/messages">Show Messages</a><br>
<a href= "viewUserSaleListings.do">Show My Sales Listings</a><br>
<a href= "viewUserDonationListings.do">Show My Donation Listings</a><br>
<a href= "viewUserSwapListings.do">Show My Swap Listings</a>
<p>${result }</p>
</div>
<jsp:include page="footer.jsp" />
<!--  BOOTSTRAP JAVASCRIPT -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD" crossorigin="anonymous"></script>
<!--  end  -->
</body>
</html>