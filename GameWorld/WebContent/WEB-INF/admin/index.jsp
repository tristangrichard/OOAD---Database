<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">
<html>
<head>
<meta http-equiv="Content-type" content="text/html;charset=UTF-8">
<title>User Administration</title>
<link rel="stylesheet" href="../css/style1.css">

</head>

<body>
	<div id="messagecontainer">
			<div class="error">${error}</div>
			<div class="message">${message}</div>
	</div>

	<div id="container">

		<div id="navigation">
			<!-- Site navigation menu -->
			<ul class="navbar">
				<li><a href="../index.jsp">Back</a>
					<hr>
				<li><a href="index.jsp?action=createPublisher">Add
						Publisher</a>
				<li><a href="index.jsp?action=listPublisher">List Publisher</a>
				<li><a href="index.jsp?action=listUsers">List Users</a>
					<hr>
				<li><a href="../login/logout.jsp">Logout</a>
			</ul>
		</div>
		<div id="main">
			<!-- Main content -->
			<h1>User administration</h1>
			<p></p>
		</div>
		<div class="footer">Gruppe 8</div>
	</div>
</body>
</html>
