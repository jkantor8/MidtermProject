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

<div class="row"></div>
<div class="col">
<h2>Update Listing</h2>

<form action="performUpdate.do" method="post">
    <input type="hidden" name="listing_type" value="${listing_type}">
    <input type="hidden" name="id" value="${listing.id}">

    <label for="title">Title:</label>
    <input type="text" id="title" name="title" value="${listing.title}">
    <br>
    <label for="description">Description:</label>
    <textarea id="description" name="description">${listing.description}</textarea>
    <br>

    <label for="active">Active:</label>
    <input type="radio" id="active" name="active" value="true" ${listing.active ? 'checked' : ''}>
    <br>
    <label for="inactive">Inactive:</label>
    <input type="radio" id="active" name="active" value="false" ${!listing.active ? 'checked' : ''}>
    <br>

    <c:choose>
        <c:when test="${listing_type == 'donation'}">
        	 <label for="donationAddress">Donation Address:</label>
            <input type="text" id="donationAddress" name="donationAddress" value="${listing.donationAddress}">
            <br>
            <label for="eventStart">Event Start:</label>
            <input type="datetime-local" id="eventStart" name="eventStart" value="${listing.eventStart}">
            <br>
            <label for="eventEnd">Event End:</label>
            <input type="datetime-local" id="eventEnd" name="eventEnd" value="${listing.eventEnd}">
            <br>
        </c:when>
        <c:when test="${listing_type == 'sale'}">

            <label for="price">Price:</label>
            <input type="text" id="price" name="price" value="${listing.price}">
            <br>
        </c:when>
        <c:when test="${listing_type == 'swap'}">
       <label for="swapAddress">Donation Address:</label>
            <input type="text" id="swapAddress" name="swapAddress" value="${listing.swapAddress}">
            <br>
        </c:when>
    </c:choose>

    <input type="submit" value="Update">
</form>
</div>

</main>

<jsp:include page="footer.jsp" />

<!--  BOOTSTRAP JAVASCRIPT -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD" crossorigin="anonymous"></script>
<!--  end  -->
</body>
</html>