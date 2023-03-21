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
<h1>Select Item(s)</h1>
<jsp:include page="nav.jsp" />
</header>


<main>
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