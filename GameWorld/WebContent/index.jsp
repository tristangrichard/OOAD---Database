<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="error" class="java.lang.String" scope="request" />
<jsp:useBean id="message" class="java.lang.String" scope="request" />
<DTD HTML 4.01//EN">
<html>
<head>
<meta http-equiv="Content-type" content="text/html;charset=UTF-8">
<title>GameWorld</title>
<link rel="stylesheet" href="css/style.css">
</head>
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
      <form class="form-signin" method="POST" action="j_security_check">
        <h2 class="form-signin-heading">GameWorld</h2>
			<% if (request.getUserPrincipal() != null) { if (request.isUserInRole("administrator")) {%>
				<br><a class="btn btn-large btn-primary"href="admin/index.jsp">User Administration</a>
				<br><a class="btn btn-large btn-primary"href="database/index.jsp">Database Administration</a> <% } if (request.isUserInRole("game") || request.isUserInRole("administrator")) { %>
				<br><a class="btn btn-large btn-primary"href="game/index.jsp">Game Administration</a> <% } if (request.isUserInRole("user") || request.isUserInRole("administrator")) { %>
				<br><a class="btn btn-large btn-primary"href="mygames/index.jsp">My Games</a><% } if (request.isUserInRole("game") || request.isUserInRole("administrator") || request.isUserInRole("user")) { %>
				<br><a class="btn btn-large btn-primary"href="profile/index.jsp?action=updateUser">My Profile</a>
				<br><a class="btn btn-large btn-primary"href="stat/index.jsp">Statistics</a><%} %>
				<br><a class="btn btn-large btn-primary"href="login/logout.jsp">Logout</a> <% } else { %>
				<br><a class="btn btn-large btn-primary"href="login/index.jsp">Login</a> <% } if (!request.isUserInRole("game") && !request.isUserInRole("administrator") && !request.isUserInRole("user")) { %>
				<br><a class="btn btn-large btn-primary"href="create/index.jsp?action=List">Create User</a> <%} %>
			</form>
		</div>
		<div class="footer">Gruppe 8</div>
</body>
</html>