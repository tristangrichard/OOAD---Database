<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="gameList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="gameUrl" class="java.util.ArrayList" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">
<html>
<head>
<meta http-equiv="Content-type" content="text/html;charset=UTF-8">
<title>GameWorld</title>
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
				<a class="btn btn-primary1 btn-large" href="index.jsp?action=Add">Add new game</a>
				<a class="btn btn-primary1 btn-large" href="../login/logout.jsp">Logout</a>
			</div>
			<div class="span9">
				<!-- Main content -->
				<h2>My Games</h2>
				<p>From here you can manage your games!</p>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="span13">
			<h1>My Games</h1>
			<form method="POST" action="index.jsp">
				<!-- This is for creating -->
				<table class="list" align="center">
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
		</div>
		<div class="footer">Gruppe 8</div>
	</div>
</body>
</html>