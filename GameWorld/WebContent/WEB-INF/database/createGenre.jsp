<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">
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
				<a class="btn btn-primary1 btn-large" href="index.jsp?">Back</a>
				<a class="btn btn-primary1 btn-large" href="index.jsp?action=listGenre">List genre</a>
				<a class="btn btn-primary1 btn-large" href="../login/logout.jsp">Logout</a>
			</div>
			
		<div class="span9">
				<!-- Main content -->
				<h2>Genre administration</h2>
			</div>
		</div>
	</div>
<div class="container">
		<div class="row">
			<div class="span13">
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
						<td colspan="2" align="right">
						<input type="hidden" name="action" value="genreFilled">
						<input class="btn btn-primary1 btn-large" type="submit" value="Create Genre"></td>
						<td></td>
					</tr>
				</table>
			</form>
		</div>
		</div>
		<div class="footer">Gruppe 8</div>
	</div>
</body>
</html>