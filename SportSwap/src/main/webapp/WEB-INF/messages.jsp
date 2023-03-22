<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta charset="UTF-8">
    <title>Messages</title>
</head>
<body>

<jsp:include page="nav.jsp" />
    <h1>Your Messages</h1>
    <h2>Received Messages</h2>
    <table>
        <tr>
            <th>Sender</th>
            <th>Message</th>
            <th>Timestamp</th>
        </tr>
        <c:forEach items="${receivedMessages}" var="message">
            <tr>
                <td>${message.sender.username}</td>
                <td>${message.content}</td>
                <td>${message.created}</td>
            </tr>
        </c:forEach>
    </table>
    <h2> Sent Messages</h2>
    <table>
        <tr>
            <th>Receiver</th>
            <th>Message</th>
            <th>Timestamp</th>
        </tr>
        <c:forEach items="${sentMessages}" var="message">
            <tr>
                <td>${message.receiver.username}</td>
                <td>${message.content}</td>
                <td>${message.created}</td>
            </tr>
        </c:forEach>
    </table>
    <a href="/new-message">New Message</a>
    
    <jsp:include page="footer.jsp" />
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD" crossorigin="anonymous"></script>
    
</body>
</html>