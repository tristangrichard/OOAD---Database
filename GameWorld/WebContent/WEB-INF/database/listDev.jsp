<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="List" class="java.util.ArrayList" scope="request" />
<%@ page import="dto.DeveloperDTO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">
<html>
<head>
<meta http-equiv="Content-type" content="text/html;charset=UTF-8">
<title>Developer Administration</title>
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
				<li><a href="index.jsp?">Back</a>
				<li><a href="index.jsp?action=createDev">Create Developer</a>
				<li><a href="../
				login/logout.jsp">Logout</a>
			</ul>
		</div>
		<div id="main">
			<!-- Main content -->
			<h1>Developer List</h1>
			<form method="POST" action="index.jsp">
				<!-- This is for creating -->
				<table class="list">

					<%
						for (int i = 0; i < List.size(); i++) {
							DeveloperDTO dev = (DeveloperDTO) List.get(i);
					%>
					<tr>
						<td><%=dev.getDeveloper()%></td>
					</tr>

					<%
						}
					%>
				</table>
			</form>
		</div>
		<div id="footer">Gruppe 8</div>
	</div>
</body>
</html>