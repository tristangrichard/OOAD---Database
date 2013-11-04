<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="gameList" class="java.util.ArrayList" scope="request" />
<%@ page import="dto.GameDTO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">
<html>
<head>
<meta http-equiv="Content-type" content="text/html;charset=UTF-8">
<title>GameWorld</title>
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
				<li><a href="index.jsp?action=List">My Games</a>
					<hr>
				<li><a href="../login/logout.jsp">Logout</a>
			</ul>
		</div>
		<div id="main">
			<!-- Main content -->
			<h1>Add new game</h1>
			<script>
				function confirmComplete() {
					var answer = confirm("Are you sure you want to add this game?");
					if (answer == true) {
						return true;
					} else {
						return false;
					}
				}
			</script>
			<form method="POST" action="index.jsp">
				<!-- This is for creating -->
				<table>
					<tr>
						<td>Select game</td>
						<td><select name="gameToAdd">
								<%
									for (int i = 0; i < gameList.size(); i++) {
										GameDTO g = (GameDTO) gameList.get(i);
								%>
								<option value=<%=g.getGid()%>><%=g.getGname()%></option>
								<%
									}
								%>
						</select></td>
					</tr>
					<tr>
						<td colspan="2" align="right"><input type="hidden"
							name="action" value="gameToAdd"><input type="submit"
							value="Add Game" onclick="{return confirmComplete();}"></td>
						<td></td>
					</tr>
				</table>
			</form>
		</div>
		<div class="footer">Gruppe 8</div>
	</div>
</body>
</html>