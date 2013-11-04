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
<link rel="stylesheet" href="../css/style.css">
<style> .btn-small{margin-left:20px;} </style>
</head>

<body>
	<div class="container">
		<h1 id="headerh1">GameWorld</h1>
		<div id="messagecontainer">
			<div class="error">${error}</div>
			<div class="message">${message}</div>
		</div>
		<div class="row">
			<div class="span2">
				<a class="btn btn-primary1 btn-large" href="index.jsp?">Back</a> 
				<a class="btn btn-primary1 btn-large" href="index.jsp?action=createPublisher">Create Publisher</a> 
				<a class="btn btn-primary1 btn-large" href="index.jsp?action=listPublisher">List Publisher</a>
				<a class="btn btn-primary1 btn-large" href="../login/logout.jsp">Logout</a>
			</div>
			<div class="span9">
				<!-- Main content -->
				<h2>User administration</h2>
				<p></p>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="span13">
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
						<td><a class = "btn btn-primary1 btn-small"
							href="index.jsp?action=updateUser&amp;userToUpdate=<%=user.getEmail()%>">Edit</a>
						</td>
						<td>
							<%
								if (!roles.get(i).toString().equalsIgnoreCase("inactive")
											&& !roles.get(i).toString()
													.equalsIgnoreCase("inactivePub")) {
							%> <a class = "btn btn-primary1 btn-small"
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
		</div>
		<div class="footer">Gruppe 8</div>
	</div>
</body>
</html>