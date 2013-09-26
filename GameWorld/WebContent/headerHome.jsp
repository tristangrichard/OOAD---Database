<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">
<html>
<head>
<meta http-equiv="Content-type" content="text/html;charset=UTF-8">
<title>Scale Application</title>
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
				<li><a href="admin/index.jsp">Operator Admin</a> <% } if (request.isUserInRole("farmaceut") || request.isUserInRole("administrator")) { %>
				<li><a href="raavare/index.jsp">Råvare Admin</a>
				<li><a href="recept/index.jsp">Recept Admin</a> <% } if (request.isUserInRole("vaerkfoerer") || request.isUserInRole("farmaceut") || request.isUserInRole("administrator")) { %>
				<li><a href="raavarebatch/index.jsp">Råvarebatch Admin</a>
				<li><a href="produkt/index.jsp">Produktbatch Admin</a>  <% }%>
				<hr><li><a href="login/logout.jsp">Logout</a> <% } else { %>
				<hr><li><a href="login/index.jsp">Login</a> <% } %>
				<hr><li><a href="createOpr.jsp">Create User</a>
			</ul>
		</div>
		<div id="main">
			<!-- Main content -->