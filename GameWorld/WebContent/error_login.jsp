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
		<div id="main">
			<!-- Main content -->
			<h1>Login error - GAME OVER!!!</h1>
			<p>Login failed.</p>
			<p>
				<a href="index.jsp">Try again</a>
			</p>
		</div>
		<div id="footer">Gruppe 8</div>
	</div>
</body>
</html>