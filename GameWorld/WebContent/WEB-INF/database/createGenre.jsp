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
<div id="messagecontainer">
			<div class="error">${error}</div>
			<div class="message">${message}</div>
	</div>

	<div id="container">

		<div id="navigation">
			<!-- Site navigation menu -->
			<ul class="navbar">
				<li><a href="index.jsp?">Back</a>
				<li><a href="index.jsp?action=listGenre">List genre</a>
					<hr>
				<li><a href="../login/logout.jsp">Logout</a>
			</ul>
		</div>
		<div id="main">
			<!-- Main content -->
			<h1>Create genre</h1>
			<form method="POST" action="index.jsp">
				<!-- This is for creating -->
				<table>
					<tr>
						<td>Genre:</td>
						<td><input type="text" name="newGenre" required
							value="<%if (request.getParameter("newGenre") != null)
				out.print(request.getParameter("newGenre"));%>"
							onclick="this.select()"></td>
					</tr>
					<tr>
						<td colspan="2" align="right"><input type="hidden"
							name="action" value="genreFilled"><input type="submit"
							value="Create Genre"></td>
						<td></td>
					</tr>
				</table>
			</form>
		</div>
		<div class="footer">Gruppe 8</div>
	</div>
</body>
</html>