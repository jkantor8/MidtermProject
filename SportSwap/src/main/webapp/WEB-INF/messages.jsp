<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>Messages</title>
</head>
<body>
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
</body>
</html>