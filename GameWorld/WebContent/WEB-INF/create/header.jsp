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
		<h1 id="headerh1">GameWorld</h1>
		<div id="messagecontainer">
			<div class="error">
				${error}
			</div>
			<div class="message">
				${message}
			</div>
		</div>
	</div>


			<!-- Site navigation menu -->
			<ul class="navbar">
				<li><a class="btn btn-large btn-success" href="../index.jsp">Cancel >></a>
				<li><a class="btn btn-large btn-success"href="../login/index.jsp">Login >></a>
			</ul>
