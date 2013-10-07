<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">
<html>
<head>
<meta http-equiv="Content-type" content="text/html;charset=UTF-8">
<title>GameWorld</title>
<link rel="stylesheet" href="../css/style1.css">

</head>

<body>
	<div id="header">
		<h1 id="headerh1">Game Administration</h1>
		<div id="messagecontainer">
			<div class="error">
				${error}
			</div>
			<div class="message">
				${message}
			</div>
		</div>
	</div>
	<div id="container">

		<div id="navigation">
			<!-- Site navigation menu -->
			<ul class="navbar">
				<li><a href="javascript:history.back(-1);">Back</a>
				<hr> 
				<li><a href="index.jsp?action=CreateGame">Add Game</a> <% if (request.isUserInRole("game")){ %>
				<li><a href="index.jsp?action=List">Our Games</a><%} %>
				<li><a href="index.jsp?action=Stat">Statistics</a>
				<hr>
				<li><a href="../login/logout.jsp">Logout</a>
			</ul>
		</div>
		<div id="main">
			<!-- Main content -->