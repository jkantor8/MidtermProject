<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSP -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
<!--  show a selectable list of all active items associated with the user -->
<div class="row">
<div class="col">
<h2>${fn:toUpperCase(fn:substring(listing_type, 0, 1))}${fn:substring(listing_type, 1, fn:length(listing_type))} Listing - Item Selection</h2>

<p>${message}</p>
<form action="finish_listing.do" method="POST">
<table>
<tr>
<th>Select</th><th>Image</th><th>Name</th><th>Description</th></tr>
<c:forEach var="item" items="${items}">
<c:if test="${item.active}">
<tr>
<td><input type="checkbox" id="${item.id}" name="selectable_item" value="${item.id}"></td>
<td><img src="${item.imageUrl}" class="selectable_item_image"></td>
<td>${item.name}</td>
<td>${item.description}</td>
</tr>
</c:if>
</c:forEach>
</table>
<input type="submit" value="Submit" class="btn btn-outline-red">
<a href="item_create.do" class="btn btn-outline-red">Create Another Item</a>
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