<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Message</title>
</head>
<body>
    <h1>Create a new message</h1>
    <c:out value="${receiver.id}" />
    
    <form action="/new-message" method="post">
        <label for="receiverUsername">Recipient:</label>
        <input type="text" id="receiverUsername" name="receiverUsername" required>
        <br>
        <label for="content">Message:</label>
        <textarea id="content" name="content" required></textarea>
        <br>
            
        <button type="submit" value="Send">Send</button>
    </form>
    </body>
</html>