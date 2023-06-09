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

<!--  users -->
<div class="row"><h2>Admin Menu</h2></div>
<div class="row">
<div class="col">
<h3>Users</h3>
<table class="mb-4">
<tr><th>Activate/Deactivate</th><th>Username</th><th>Active Status</th></tr>
<c:forEach var="user" items="${users}">
<tr>
<td>
<!--  do not include admin in possible deletes! -->
	<c:if test="${user.id!=1 and user.active==true}">
	<a href="deactivate.do?entity=user&id=${user.id}" class="btn btn-outline-red">Deactivate</a>
	</c:if>
	<c:if test="${user.id!=1 and user.active==false }">
	<a href="reactivate.do?entity=user&id=${user.id}" class="btn btn-outline-red">Reactivate</a>
	</c:if>
	</td>
<td>${user.username}</td>
<td>
<c:if test="${user.active==true}">

</c:if>
<c:if test="${user.active==false }">
DEACTIVATED
</c:if>
</td>
</tr>
</c:forEach>
</table>
</div>
</div>

<!-- donation listings -->
<div class="row">
<div class="col">
<h3>Donation Listings</h3>
<table class="mb-4">
<tr><th>Activate/Deactivate</th><th>Title</th><th>User</th><th>Active Status</th></tr>
<c:forEach var="donation" items="${donations}">
<tr>
<td>
	<c:if test="${donation.active==true}">
	<a href="deactivate.do?entity=donation&id=${donation.id}" class="btn btn-outline-red">Deactivate</a>
	</c:if>
	<c:if test="${donation.active==false }">
	<a href="reactivate.do?entity=donation&id=${donation.id}" class="btn btn-outline-red">Reactivate</a>
	</c:if>
	</td>
	<td>${donation.title}</td>
	<td>${donation.user.username}</td>
	<td>
	<c:if test="${donation.active==true}">
	
	</c:if>
	<c:if test="${donation.active==false}">
	DEACTIVATED
	</c:if>
	</td>
	</tr>
	<!-- nested for posts -->
	<c:if test="${donation.donationListingPosts.size()>0}">
	<tr>
	<td colspan="4">
	<h5>Posts For: ${donation.title}</h5>
	<ul>
	<c:forEach var="post" items="${donation.donationListingPosts }">
	<li> --
		<c:if test="${post.active==true}">
		<a href="deactivate.do?entity=post&id=${post.id}" class="btn btn-outline-red">Deactivate </a>${post.comment} - ${post.postingUser.username}
		
		</c:if>
		<c:if test="${post.active==false }">
		<a href="reactivate.do?entity=post&id=${post.id}" class="btn btn-outline-red">Reactivate </a>${post.comment} - ${post.postingUser.username}
		</c:if>
	</li>
	</c:forEach>
	</ul>
	
	</td>
	</tr>	
	</c:if>
</c:forEach>
</table>
</div>
</div>

<!-- sale listings -->
<div class="row">
<div class="col">
<h3>Sale Listings</h3>
<table class="mb-4">
<tr><th>Activate/Deactivate</th><th>Title</th><th>User</th><th>Active Status</th></tr>
<c:forEach var="sale" items="${sales}">
<tr>
<td>
	<c:if test="${sale.active==true}">
	<a href="deactivate.do?entity=sale&id=${sale.id}" class="btn btn-outline-red">Deactivate</a>
	</c:if>
	<c:if test="${sale.active==false }">
	<a href="reactivate.do?entity=sale&id=${sale.id}" class="btn btn-outline-red">Reactivate</a>
	</c:if>
	</td>
	<td>${sale.title}</td>
	<td>${sale.sellingUser.username}</td>
	<td>
	<c:if test="${sale.active==true}">
	
	</c:if>
	<c:if test="${sale.active==false}">
	DEACTIVATED
	</c:if>
	</td>
	</tr>
	<!-- nested for posts -->
	<c:if test="${sale.saleListingPosts.size()>0}">
	<tr>
	<td colspan="4">
	<h5>Posts For: ${sale.title}</h5>
	<ul>
	<c:forEach var="post" items="${sale.saleListingPosts }">
	<li> --
		<c:if test="${post.active==true}">
		<a href="deactivate.do?entity=post&id=${post.id}" class="btn btn-outline-red">Deactivate </a>${post.comment} - ${post.postingUser.username}
		
		</c:if>
		<c:if test="${post.active==false }">
		<a href="reactivate.do?entity=post&id=${post.id}" class="btn btn-outline-red">Reactivate </a>${post.comment} - ${post.postingUser.username}
		</c:if>
	</li>
	</c:forEach>
	</ul>
	
	</td>
	</tr>	
	</c:if>
</c:forEach>
</table>
</div>
</div>

<!-- sale listings -->
<div class="row">
<div class="col">
<h3>Swap Listings</h3>
<table class="mb-4">
<tr><th>Activate/Deactivate</th><th>Title</th><th>User</th><th>Active Status</th></tr>
<c:forEach var="swap" items="${swaps}">
<tr>
<td>
	<c:if test="${swap.active==true}">
	<a href="deactivate.do?entity=swap&id=${swap.id}" class="btn btn-outline-red">Deactivate</a>
	</c:if>
	<c:if test="${swap.active==false }">
	<a href="reactivate.do?entity=swap&id=${swap.id}" class="btn btn-outline-red">Reactivate</a>
	</c:if>
	</td>
	<td>${swap.title}</td>
	<td>${swap.swappingUser.username}</td>
	<td>
	<c:if test="${swap.active==true}">
	
	</c:if>
	<c:if test="${swap.active==false}">
	DEACTIVATED
	</c:if>
	</td>
	</tr>
	<!-- nested for posts -->
	<c:if test="${swap.swapListingPosts.size()>0}">
	<tr>
	<td colspan="4">
	<h5>Posts For: ${swap.title}</h5>
	<ul>
	<c:forEach var="post" items="${swap.swapListingPosts }">
	<li> --
		<c:if test="${post.active==true}">
		<a href="deactivate.do?entity=post&id=${post.id}" class="btn btn-outline-red">Deactivate </a>${post.comment} - ${post.postingUser.username}
		
		</c:if>
		<c:if test="${post.active==false }">
		<a href="reactivate.do?entity=post&id=${post.id}" class="btn btn-outline-red">Reactivate </a>${post.comment} - ${post.postingUser.username}
		</c:if>
	</li>
	</c:forEach>
	</ul>
	
	</td>
	</tr>	
	</c:if>
</c:forEach>
</table>
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