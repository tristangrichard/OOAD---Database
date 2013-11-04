<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="user1" class="dto.UsersDTO" scope="request" />
<jsp:useBean id="role" class="dto.RoleDTO" scope="request" />
<jsp:useBean id="userLang" class="dto.UsersLangDTO" scope="request" />
<jsp:useBean id="langList" class="java.util.ArrayList" scope="request" />
<%@ page import="dto.LangDTO"%>
<%@ page import="dto.UsersDTO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">
<html>
<head>
<meta http-equiv="Content-type" content="text/html;charset=UTF-8">
<title>User Administration</title>
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
					<hr>
				<li><a href="../login/logout.jsp">Logout</a>
			</ul>
		</div>
		<div id="main">
			<!-- Main content -->
			<h1>Update User</h1>
			<form method="POST" action="index.jsp">
				<!-- This is for Updating -->
				<table>
					<tr>
						<td>First Name:</td>
						<td><input type="text" name="newFName" required="required"
							value="<%if (request.getParameter("userFName") != null)
				out.print(request.getParameter("userFName"));
			else
				out.print(user1.getFname());%>"
							onclick="this.select()"></td>
					</tr>
					<tr>
						<td>Last Name:</td>
						<td><input type="text" name="newLName" required="required"
							value="<%if (request.getParameter("userLName") != null)
				out.print(request.getParameter("userLName"));
			else
				out.print(user1.getLname());%>"
							onclick="this.select()"></td>
					</tr>
					<tr>
						<td>email:</td>
						<td><input type="email" name="newUserEmail"
							required="required"
							value="<%if (request.getParameter("newUserEmail") != null)
				out.print(request.getParameter("newUserEmail"));
			else
				out.print(user1.getEmail());%>"
							onclick="this.select()"></td>
					</tr>
					<tr>
						<td>Date of Birth:</td>
						<td><input type="text" name="newUserBirth"
							required="required"
							value="<%if (request.getParameter("newUserBirth") != null)
				out.print(request.getParameter("newUserBirth"));
			else
				out.print(user1.getDob());%>"
							onclick="this.select()"></td>
					</tr>
					<tr>
						<td>Sex:</td>
						<td><select name="newUserSex">
								<option value=1
									<%if (user1.getSex()) {
				out.print("selected");
			}%>>Male</option>
								<option value=0
									<%if (!user1.getSex()) {
				out.print("selected");
			}%>>Female</option>
						</select></td>
						<td>Language:</td>
						<td><select name="newUserLang">
								<%
									for (int i = 0; i < langList.size(); i++) {
										LangDTO l = (LangDTO) langList.get(i);
								%>
								<option value=<%=l.getLangid()%>
									<%if (l.getLangid() == userLang.getLangid()) {
					out.print("selected");
				}%>><%=l.getLang()%></option>
								<%
									}
								%>
						</select></td>
					</tr>
					<tr>
						<td>New Password:</td>
						<td><input type="password" name="updOprPass1"
							required="required" value="" onclick="this.select()"></td>
					</tr>
					<tr>
						<td>New Password Again:</td>
						<td><input type="password" name="updOprPass2"
							required="required" value="" onclick="this.select()"></td>
						<td></td>
					</tr>
					<td>Role:</td>
					<td><select name="newUserRole">
							<option value=user
								<%if (role.getRole().equalsIgnoreCase("user")) {
				out.print("selected");
			}%>>user</option>
							<option value=administrator
								<%if (role.getRole().equalsIgnoreCase("administrator")) {
				out.print("selected");
			}%>>administrator</option>
					</select></td>
					<tr>
						<td colspan="2" align="right"><input type="hidden"
							name="action" value="updateOprFilled"> <input
							type="hidden" name="userToUpdate" value="<%=user1.getEmail()%>"><input
							type="hidden" name="oldEmail" value="<%=user1.getEmail()%>">
							<input type="submit" value="Update Operator"></td>
						<td></td>
					</tr>
				</table>
			</form>
			<p>The password needs to contain between 7 and 8 characters of at
				least three of the following four categories: small letters ('a' -
				'z'), capital letters ('A' - 'Z'), digits ('0' - '9') and any of the
				following special characters: ('.', '-', '_', '+', '!', '?', '=').</p>
		</div>
		<div class="footer">Gruppe 8</div>
	</div>
</body>
</html>