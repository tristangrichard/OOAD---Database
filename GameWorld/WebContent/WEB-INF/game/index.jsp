<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">
<!-- **
 * 
 * @author Tristan Richard
 *
 * -->
<html>
<head>
<title>GameWorld</title>
<link rel="stylesheet" href="../css/style.css">
</head>
<body>
<div class="container">
		<h1 id = "headerh1">GameWorld</h1>
		<div id="messagecontainer">
			<div class="error">${error}</div>
			<div class="message">${message}</div>
		</div>
		<div class="row">
			<div class="span2">
				<a class="btn btn-primary1 btn-large" href="../index.jsp?">Home</a>
				<a class="btn btn-primary1 btn-large" href="index.jsp?action=CreateGame">Add Game</a>
				<% if (request.isUserInRole("game")) { %>
				<a class="btn btn-primary1 btn-large" href="index.jsp?action=List">Our Games</a> <%}%>
				<a class="btn btn-primary1 btn-large" href="../login/logout.jsp">Logout</a>
			</div>
			<div class="span9">
				<!-- Main content -->
				<h2>Game administration</h2>
				<p>From here you can add games to the database!</p>
			</div>
		</div>
	</div>
		<div class="footer">Gruppe 8</div>
</body>
</html>
