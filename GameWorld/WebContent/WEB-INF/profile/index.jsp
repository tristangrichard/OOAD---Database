<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">
<html>
<head>
<meta http-equiv="Content-type" content="text/html;charset=UTF-8">
<title>My Profile</title>
<link rel="stylesheet" href="../css/style1.css">

</head>

<body>
	<div id="header">
		<h1 id="headerh1">GameWorld</h1>
		<div id="messagecontainer">
			<div class="error">${error}</div>
			<div class="message">${message}</div>
		</div>
	</div>
	<div id="container">

		<div id="navigation">
			<!-- Site navigation menu -->
			<ul class="navbar">
				<li><a href="../index.jsp">Back</a>
					<hr>
					<% if (request.getUserPrincipal() != null) {if (request.isUserInRole("user")) {%>
				<li><a href="index.jsp?action=updateUser">Update Profile</a>
					<%} if (request.isUserInRole("game")) {%>
				<li><a href="index.jsp?action=updatePub">Update Profile</a>
					<%} }%>
					<hr>
				<li><a href="../login/logout.jsp">Logout</a>
			</ul>
		</div>
		<div id="main">
			<!-- Main content -->
			<h1>User administration</h1>
			<p></p>
		</div>
		<div id="footer">Gruppe 8</div>
	</div>
</body>
</html>
