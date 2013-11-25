<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="userList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="compList" class="java.util.ArrayList" scope="request" />
<%@ page import="dto.UsersDTO"%>
<%@ page import="dto.PublisherDTO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">
<!-- **
 * 
 * @author Tristan Richard, Thordur Birgisson
 *
 * -->
<html>
<head>
<meta http-equiv="Content-type" content="text/html;charset=UTF-8">
<title>User Administration</title>
<link rel="stylesheet" href="../css/style.css">

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
				<a class="btn btn-primary1 btn-large" href="index.jsp?action=listUsers">List Users</a> 
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
				<h1>Registered publishers</h1>
				<form method="POST" action="index.jsp">
					<!-- This is for creating -->
					<table class="list">
						<%
							for (int i = 0; i < userList.size(); i++) {
								UsersDTO user = (UsersDTO) userList.get(i);
								PublisherDTO comp = (PublisherDTO) compList.get(i);
						%>
						<tr>
							<td><%=user.getFname() + " " + user.getLname()%></td>
							<td><%=comp.getPublisher()%></td>
							<td><a class = "btn btn-primary1 btn-small" href="index.jsp?action=updateUser&amp;userToUpdate=<%=user.getEmail()%>">Edit</a></td>
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