<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">
<html>
<head>
<meta http-equiv="Content-type" content="text/html;charset=UTF-8">
<title>GameWorld</title>
<link rel="stylesheet" href="css/style1.css">

</head>

<body>
	<div id="header">
		<h1 id="headerh1">GameWorld</h1>
		<div id="messagecontainer">
			<jsp:useBean id="error" class="java.lang.String" scope="request" />
			<jsp:useBean id="message" class="java.lang.String" scope="request" />
			<div class="error">
				<%
					if (error != null)
						out.println(error);
				%>
			</div>
			<div class="message">
				<%
					if (message != null)
						out.println(message);
				%>
			</div>
		</div>
	</div>
	<div id="container">

		<div id="navigation">
			<!-- Site navigation menu -->
			<ul class="navbar">
				<li><a href="index.jsp">Home</a><hr> <% if (request.getUserPrincipal() != null) {
					if (request.isUserInRole("administrator")) {%>
				<li><a href="admin/index.jsp">User Administration</a>
				<li><a href="database/index.jsp">Database Administration</a> <% } if (request.isUserInRole("game") || request.isUserInRole("administrator")) { %>
				<li><a href="game/index.jsp">Game Administration</a> <% } if (request.isUserInRole("user") || request.isUserInRole("administrator")) { %>
				<li><a href="mygames/index.jsp">My Games</a><% } if (request.isUserInRole("game") || request.isUserInRole("administrator") || request.isUserInRole("user")) { %>
				<li><a href="profile/index.jsp?updateUser">My Profile</a><%} %>
				<hr><li><a href="login/logout.jsp">Logout</a> <% } else { %>
				<hr><li><a href="login/index.jsp">Login</a> <% } if (!request.isUserInRole("game") && !request.isUserInRole("administrator") && !request.isUserInRole("user")) { %>
				<hr><li><a href="create/index.jsp?action=List">Create User</a> <%} %>
			</ul>
		</div>
		<div id="main">
			<!-- Main content -->