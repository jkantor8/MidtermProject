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
<div class="container">

<c:choose>
    <c:when test="${loggedInUser==null}">
    <p><a href="createAccount.do">create an account</a></p>
        <form method="POST" action="home.do">
<label for="username">username: </label>
  <input type="text" id="username" name="username">
  <label for="password">password: </label>
  <input type="text" id="password" name="password">
  <input type="submit" value="login" name="login">
</form>
  
    </c:when>    
    <c:otherwise>
         <p>Welcome to SportSwap, ${loggedInUser.username}!</p>
         <form method="POST" action="home.do">
         <input type="submit" value="logout" name="logout">
         </form>
    </c:otherwise>
</c:choose>
</div>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">SportSwap</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Browse</a>
        </li>
         <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Post
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="#">Item for Sale</a></li>
            <li><a class="dropdown-item" href="#">Donation Listing</a></li>
          </ul>
        </li>
         <li class="nav-item">
          <a class="nav-link" href="#">Account</a>
        </li>
       
       
      </ul>
      <form class="d-flex" role="search">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>
</header>

<div class="row">
  <div class="col-3">
    <div class="card">
      <div class="card-body">
        <h5 class="card-title">Bat</h5>
        <p class="card-text">This baseball bat is awesome. Awe-Inspring.</p>
        <a href="#" class="btn btn-primary">(go to listing)</a>
      </div>
    </div>
  </div>
  <div class="col-3">
    <div class="card">
      <div class="card-body">
        <h5 class="card-title">Kids Baseball Glove</h5>
        <p class="card-text">Barely used. My kid decided to be a nerd.</p>
        <a href="#" class="btn btn-primary">(got to listing)</a>
      </div>
    </div>
    </div>
    <div class="col-3">
    <div class="card">
      <div class="card-body">
        <h5 class="card-title">Set of Golf Clubs</h5>
        <p class="card-text">Great for self-defense!.</p>
        <a href="#" class="btn btn-primary">(go to listing)</a>
      </div>
    </div>
  </div>
</div>


<jsp:include page="footer.jsp" />

<!--  BOOTSTRAP JAVASCRIPT -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD" crossorigin="anonymous"></script>
<!--  end  -->
</body>
</html>