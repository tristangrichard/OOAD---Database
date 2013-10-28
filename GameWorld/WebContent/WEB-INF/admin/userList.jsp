<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="userList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="gameUrl" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="idList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="roles" class="java.util.ArrayList" scope="request" />
<%@ page import="dto.UsersDTO"%>
<%@ page import="dto.RoleDTO"%>
<%@ page import="daointerfaces.RoleIDAO"%>
<%@ page import="daoimpl.MySQLRoleDAO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">
<html>
<head>
<meta http-equiv="Content-type" content="text/html;charset=UTF-8">
<title>User Administration</title>
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
					<hr>
				<li><a href="index.jsp?action=createPublisher">Add
						Publisher</a>
				<li><a href="index.jsp?action=listPublisher">List Publisher</a>
					<hr>
				<li><a href="../login/logout.jsp">Logout</a>
			</ul>
		</div>
		<div id="main">
			<!-- Main content -->
			<h1>User List</h1>
			<form method="POST" action="index.jsp">
				<!-- This is for creating -->
				<table class="list">
					<%
						RoleIDAO ridao = new MySQLRoleDAO();
						for (int i = 0; i < userList.size(); i++) {
							UsersDTO user = (UsersDTO) userList.get(i);
							RoleDTO role = ridao.get(user.getEmail());
					%>
					<tr>
						<td><%=user.getFname() + " " + user.getLname()%></td>
						<td><%=user.getEmail()%></td>
						<td><%=role.getRole()%></td>
						<td><a
							href="index.jsp?action=updateUser&amp;userToUpdate=<%=user.getEmail()%>">Edit</a>
						</td>
						<td>
							<%
								if (!roles.get(i).toString().equalsIgnoreCase("inactive")
											&& !roles.get(i).toString()
													.equalsIgnoreCase("inactivePub")) {
							%> <a
							href="index.jsp?action=deactivateUser&amp;userToDeactivate=<%=user.getEmail()%>">Deactivate</a>
							<%
								} else
										out.print("Deactivated");
							%>
						</td>

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