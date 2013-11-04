<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="error" class="java.lang.String" scope="request" />
<jsp:useBean id="message" class="java.lang.String" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">
<html>
<head>
<meta http-equiv="Content-type" content="text/html;charset=UTF-8">
<title>GameWorld</title>
<link rel="stylesheet" href="css/style.css">
<style>
.form-signin {
	max-width: 300px;
}
</style>
</head>
<body>
	<div id="messagecontainer">
		<div class="error">${error}</div>
		<div class="message">${message}</div>
	</div>
	<div class="container">
		<form class="form-signin">
			<h2 class="form-signin-heading">GameWorld</h2>
			<h1>Page not found</h1>
			<p>The requested page does not exist.</p>
			<p>
				<a class="btn btn-large btn-primary" href="index.jsp">Go back</a>
			</p>
		</form>
		<div class="footer">Gruppe 8</div>
	</div>
</body>
</html>