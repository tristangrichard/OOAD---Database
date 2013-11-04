<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="gameList" class="java.util.ArrayList" scope="request" />
<%@ page import="dto.GameDTO"%>
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
				<a class="btn btn-primary1 btn-large" href="index.jsp?action=List">My Games</a>
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
						<td colspan="2" align="right">
						<input type="hidden" name="action" value="gameToAdd">
						<input class="btn btn-primary1 btn-large" type="submit" value="Add Game" onclick="{return confirmComplete();}"></td>
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