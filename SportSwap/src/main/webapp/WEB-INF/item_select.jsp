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
<div class="row">
	<h1>SportSwap</h1>
</div>
<div class="row justify-content-end">
<c:choose>
    <c:when test="${loggedInUser==null}">
    <div class="col-md-auto">
   	<p><a href="createAccount.do">create an account</a></p>
   </div>
   <div class="col-md-auto">
        <form method="POST" action="home.do">
			<label for="username">username: </label>
  			<input type="text" id="username" name="username">
  			<label for="password">password: </label>
  			<input type="text" id="password" name="password">
  			<input type="submit" value="login" name="login">
		</form>
	</div>
    </c:when>    
    <c:otherwise>
		<div class="col-md-auto">
         <p>Welcome to SportSwap, ${loggedInUser.username}!</p>
         </div>
         <div class="col-md-auto">
         <form method="POST" action="home.do">
         <input type="submit" value="logout" name="logout">
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
<p>${message}</p>
${listing_type }
<form action="finish_listing.do" method="POST">
<c:forEach var="item" items="${items}">
<div class="selectable_item">
<div>
<input type="checkbox" id="${item.id}" name="selectable_item" value="${item.id}">
</div>
<div>
<p>${item.name }</p>
</div>
</div>
</c:forEach>
<input type="submit" value="submit">
</form>
</main>
</body>
</html>