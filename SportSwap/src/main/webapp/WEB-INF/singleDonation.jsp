<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- JSP -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!--  end -->
<!DOCTYPE html>
<html>
<head>
<!--  FONTS GOOGLE -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Russo+One">
<!--  CSS (BOOTSTRAP) -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">
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
							<label for="username">username: </label> <input type="text"
								id="username" name="username"> <label for="password">password:
							</label> <input type="password" id="password" name="password"> <input
								type="submit" value="login" name="login">
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
	<h2>${listing.title}</h2><br>
	<h4>${listing.description}</h4>
	<h6>${listing.donationAddress}</h6>
	<h6>Donation Drive Start:<fmt:parseDate value="${listing.eventStart}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
					<fmt:formatDate pattern="MM.dd.yyyy HH:mm" value="${ parsedDateTime }" /></h6>
	<h6>Donation Drive End:<fmt:parseDate value="${listing.eventEnd}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
					<fmt:formatDate pattern="MM.dd.yyyy HH:mm" value="${ parsedDateTime }" /></h6>




		<div class="row">




			<c:forEach var="item" items="${listing.items}">
				<table class="table table-bordered table-light">
					<thead>
						<tr>
							<th>Item Name</th>
							<th>Description</th>
							<th>Gender</th>
							<th>Brand</th>
							
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${item.name}</td>
							<td>${item.description }</td>
							<td>${item.gender }</td>
							<td>${item.brand }</td>
							
						</tr>
					</tbody>
				</table>
				<br>
				<c:choose>
				<c:when test="${not empty item.imageUrl }">
				<blockquote>
					<img src="${item.imageUrl}" alt="${item.name}" width="150"
						height="150">
				</blockquote>
				</c:when>
				<c:otherwise>
				<p>no image provided<p>
				</c:otherwise>
				</c:choose>
			</c:forEach>
		
			
			<c:choose>
				<c:when test="${loggedInUser !=null}">
					<form action="makePost.do" method="POST">

						<textarea id="comment" name="comment" rows="4"></textarea>
						<br> <input type="hidden" name="user" value="${loggedInUser}">
						<input type="hidden" name="listingId" value="${listing.id}">
						<input type="submit" value="Post to Donation" name="commentType">
					</form>

				</c:when>
				<c:otherwise>

				</c:otherwise>
			</c:choose>



			<c:choose>
				<c:when test="${not empty listing.donationListingPosts}">
					<h3>Posts for this listing</h3>
					<c:forEach var="post" items="${listing.donationListingPosts}">
						<p>${post.postingUser.username}:		${post.comment}</p>
					</c:forEach>
				</c:when>
			</c:choose>


		</div>

	</main>

	<jsp:include page="footer.jsp" />

	<!--  BOOTSTRAP JAVASCRIPT -->
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
		integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"
		integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD"
		crossorigin="anonymous"></script>
	<!--  end  -->
</body>
</html>
