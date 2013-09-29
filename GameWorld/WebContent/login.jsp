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
			<h1>Login</h1>
			<form method="POST" action="j_security_check">
				<table>
					<tr>
						<td align="left">ID:</td>
						<td><input type="text" name="j_username" value="Enter ID"
							onclick="this.select()"></td>
					</tr>
					<tr>
						<td align="left">Password:</td>
						<td><input type="password" name="j_password" value="1234"
							onclick="this.select()"></td>
					</tr>
					<tr>
						<th colspan="2" align="right"><input type="submit"
							name="handling" value="Login"></th>
					</tr>
				</table>
			</form>
			<p>
				The following test users are created in the system, the password for
				all of them are "<span id="password">1234</span>". This password
				is entered as default so just enter the applicable id and click
				Login.
			</p>
			<p>!!!!!!!</p>
		</div>
		<div id="footer">Gruppe 8</div>
	</div>
</body>
</html>