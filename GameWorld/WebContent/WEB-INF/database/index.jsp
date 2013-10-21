<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">
<html>
<head>
<meta http-equiv="Content-type" content="text/html;charset=UTF-8">
<title>Genre Administration</title>
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
				<li><a href="../index.jsp">Home</a>
					<hr>
				<li><a href="index.jsp?action=createGenre">Create genre</a>
				<li><a href="index.jsp?action=listGenre">List genre</a>
					<hr>
				<li><a href="index.jsp?action=createLang">Create Language</a>
				<li><a href="index.jsp?action=listLang">List Language</a>
					<hr>
				<li><a href="index.jsp?action=createDev">Create Developer</a>
				<li><a href="index.jsp?action=listDev">List Developer</a>
					<hr>
				<li><a href="index.jsp?action=createPub">Create Publisher</a>
				<li><a href="index.jsp?action=listPub">List Publisher</a>
					<hr>
				<li><a href="../login/logout.jsp">Logout</a>
			</ul>
		</div>
		<div id="main">
			<!-- Main content -->
			<h1>Database</h1>
			<p></p>
		</div>
		<div id="footer">Gruppe 8</div>
	</div>
</body>
</html>
