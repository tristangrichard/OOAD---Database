<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="gameList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="gameUrl" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="idList" class="java.util.ArrayList" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">
<html>
<head>
<meta http-equiv="Content-type" content="text/html;charset=UTF-8">
<title>GameWorld</title>
<link rel="stylesheet" href="../css/style1.css">
</head>

<body>
	<div id="header">
		<h1 id="headerh1">Game Administration</h1>
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
					<hr>
				<li><a href="index.jsp?action=CreateGame">Add Game</a> <%
 				if (request.isUserInRole("game")) { %>
				<li><a href="index.jsp?action=List">Our Games</a> <%}%>
				<li><a href="index.jsp?action=Stat">Statistics</a>
					<hr>
				<li><a href="../login/logout.jsp">Logout</a>
			</ul>
		</div>
		<div id="main">
			<!-- Main content -->
			<h1>Our Games</h1>
			<form method="POST" action="index.jsp">
				<!-- This is for creating -->
				<table class="list">
					<%
						int i = 0;
						int j = 0;
						int a = 0;
						while (j < gameList.size()) {
							a = 0;
					%>
					<tr>
						<%
							while (i < gameList.size()) {
									String name = gameList.get(i).toString();
						%>
						<td width="115" align="center"><%=name%></td>
						<%
							a++;
									i++;
									if (a == 6)
										break;
								}
						%>
					</tr>
					<tr>
						<%
							a = 0;
								while (j < gameList.size()) {
									String url = gameUrl.get(j).toString();
						%>
						<td align="center"><img alt="" src="<%=url%>" width="100"
							height="145"></td>
						<%
							a++;
									j++;
									if (a == 6)
										break;
								}
						%>
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