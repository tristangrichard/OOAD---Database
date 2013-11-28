<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">
<!-- **
 * 
 * @author Tristan Richard, Thordur Birgisson
 *
 * -->
<html>
<head>
<meta http-equiv="Content-type" content="text/html;charset=UTF-8">
<title>User Administration</title>
<link rel="stylesheet" href="../css/style.css">
<style> .span2{height:360px} .span9{height:360px}</style>
</head>

<body>
<div class="container">
	<h1 id="headerh1">GameWorld</h1>
	<div id="messagecontainer">
			<div class="error">${error}</div>
			<div class="message">${message}</div>
	</div>

		<div class="row">
		<div class="span2">
			<!-- Site navigation menu -->
				<a class="btn btn-primary1 btn-large" href="../index.jsp">Back</a>
				<a class="btn btn-primary1 btn-large" href="index.jsp?action=createPublisher">Add Publisher</a>
				<a class="btn btn-primary1 btn-large" href="index.jsp?action=listPublisher">List Publisher</a>
				<a class="btn btn-primary1 btn-large" href="index.jsp?action=listUsers">List Users</a>
				<a class="btn btn-primary1 btn-large" href="../login/logout.jsp">Logout</a>
		</div>
		<div class="span9">
			<!-- Main content -->
			<h2>User administration</h2>
			<p>This is only for administrators</p>
		</div>
		</div>
		<div class="footer">Gruppe 8</div>
	</div>
</body>
</html>
