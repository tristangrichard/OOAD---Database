<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">
<!-- **
 * 
 * @author Tristan Richard
 *
 * -->
<html>
<head>
<meta http-equiv="Content-type" content="text/html;charset=UTF-8">
<title>Genre Administration</title>
<link rel="stylesheet" href="../css/style.css">
</head>
<body>
	<div class="container">
		<h1 id = "headerh1">GameWorld</h1>
		<div id="messagecontainer">
			<div class="error">${error}</div>
			<div class="message">${message}</div>
		</div>
		<div class="row">
			<div class="span2">
				<a class="btn btn-primary1 btn-large" href="../index.jsp?">Home</a>
				<a class="btn btn-primary1 btn-large" href="../login/logout.jsp">Logout</a>
			</div>
			
		<div class="span9">
				<!-- Main content -->
				<h2>Database administration</h2>
				<a class="btn btn-primary1 btn-large" href="index.jsp?action=createGenre">Create genre</a> 
				<a class="btn btn-primary1 btn-large" href="index.jsp?action=createDev">Create Developer</a>
				<a class="btn btn-primary1 btn-large" href="index.jsp?action=createLang">Create Language</a> 
				<a class="btn btn-primary1 btn-large" href="index.jsp?action=createPub">Create Publisher</a>
				<a class="btn btn-primary1 btn-large" href="index.jsp?action=listGenre">List <br> genre</a>
				<a class="btn btn-primary1 btn-large" href="index.jsp?action=listDev">List Developer</a>
				<a class="btn btn-primary1 btn-large" href="index.jsp?action=listLang">List Language</a> 
				<a class="btn btn-primary1 btn-large" href="index.jsp?action=listPub">List Publisher</a> 
			</div>
		</div>
	</div>
		<div class="footer">Gruppe 8</div>
</body>
</html>
