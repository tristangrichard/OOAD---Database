<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">
<html>
<head>
	<meta http-equiv="Content-type" content="text/html;charset=UTF-8">
	<title>GameWorld</title>
	<link rel="stylesheet" href="../css/style.css">
</head>
<body>
	<div id="messagecontainer">
			<div class="error">${error}</div>
			<div class="message">${message}</div>
	</div>
	<div id="container" class="form-signin" >
		<form method="POST" action="j_security_check">
			  <h2 class="form-signin-heading">GameWorld</h2>
			<h1 align="center">Login</h1>
			
				<table>
					<tr>
						<td align="left">Email:</td>
						<td><input type="text" name="j_username" value="Enter ID"
							onclick="this.select()"></td>
					</tr>
					<tr>
						<td align="left">Password:</td>
						<td><input type="password" name="j_password" value="1234"
							onclick="this.select()"></td>
					</tr>
					<tr>
						<th colspan="2" align="right"><input class="btn btn-large btn-primary" type="submit"
							name="handling" value="Login"></th>
					</tr>
				</table>
			</form>
			<p>
				The following test users are created in the system, the password for
				all of them are "<span id="password">1234</span>". This password
				is entered as default so just enter the applicable email and click
				Login.
			</p>
			<p>Admin: m.b@mail.dk</p>
			<p>Publisher: info@ea.dk</p>
			<p>User: ST92@dtu.dk</p>
		</div>
		<div class="footer">Gruppe 8</div>
</body>
</html>