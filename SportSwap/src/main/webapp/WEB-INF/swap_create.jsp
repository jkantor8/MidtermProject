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
<title>Insert title here</title>
</head>
<body>
<header>
<h1>Create swap listing</h1>
<jsp:include page="nav.jsp" />
</header>
<main>
<form action="submit_swap.do" method="POST">
<label for="title">title: </label>
<input type="text" id="title" name="title" value="[title]"><br>
<label for="description">title: </label>
<input type="text" id="description" name="description" value="[description]"><br>
<h3>items for listing</h3>
<ul>
<c:forEach var="item" items="${itemsToAdd}">

	<li>${item.name}</li>

</c:forEach>
</ul>


<input type="submit" value="submit">
</form>


</main>

<jsp:include page="footer.jsp" />
<!--  BOOTSTRAP JAVASCRIPT -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD" crossorigin="anonymous"></script>
<!--  end  -->
</body>
</html>