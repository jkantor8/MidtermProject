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
  			<input type="password" id="password" name="password">
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

<!--  this container holds 3 recommended items  -->
<!--  random if not logged in user -->
<!--  focused on users favorite sport(S) if there are related posts -->
<div class="row p-2"><h3>Listings that may interest you...</h3></div>
<div class="row justify-content-center">
<c:if test="${not empty swapListing}">
  <div class="col-4">
    <div class="card">
    <c:if test="${not empty swapListing.getItems()}">
    <img src="${swapListing.getItems().get(0).getImageUrl()}" class="card-img-top" alt="...">
    </c:if>
      <div class="card-body">
        <h5 class="card-title">Swap Listing</h5>
        <p class="card-text">${swapListing.title}</p>
        <p class="card-text">${swapListing.description}</p>
        <p class="card-text">items:<br><ul>
        <c:forEach var="item" items="${swapListing.getItems()}">
        	<li>${item.name}</li>
        	</c:forEach>
        </ul></p>
        <a href="singleListing.do?id=${swapListing.getId()}&listing_type=swap" class="btn btn-outline-red">more</a>
      </div>
    </div>
  </div>
  </c:if>
  
  <c:if test="${not empty donationListing}">
   <div class="col-4">
    <div class="card">
     <c:if test="${not empty donationListing.getItems()}">
    <img src="${donationListing.getItems().get(0).getImageUrl()}" class="card-img-top" alt="...">
    </c:if>
      <div class="card-body">
        <h5 class="card-title">Donation Listing</h5>
         <p class="card-text">${donationListing.title}</p>
        <p class="card-text">${donationListing.description}</p>
        <p class="card-text">items:<br>
        <p class="card-text"><ul>
        <c:forEach var="item" items="${donationListing.getItems()}">
        	<li>${item.name}</li>
        	</c:forEach>
        </ul></p>
        <a href="singleListing.do?id=${donationListing.getId()}&listing_type=donation" class="btn btn-outline-red">more</a>
      </div>
    </div>
  </div>
  </c:if>
  
   <c:if test="${not empty saleListing}">
   <div class="col-4">
    <div class="card">
     <c:if test="${not empty saleListing.getItem()}">
    <img src="${saleListing.getItem().getImageUrl()}" class="card-img-top" alt="...">
    </c:if>
      <div class="card-body">
        <h5 class="card-title">Sale Listing</h5>
         <p class="card-text">${saleListing.title}</p>
        <p class="card-text">${saleListing.description}</p>
        <a href="singleListing.do?id=${saleListing.getId()}&listing_type=sale" class="btn btn-outline-red">more</a>
      </div>
    </div>
  </div>
</c:if>
 
  
</div>
</main>

<jsp:include page="footer.jsp" />

<!--  BOOTSTRAP JAVASCRIPT -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD" crossorigin="anonymous"></script>
<!--  end  -->
</body>
</html>