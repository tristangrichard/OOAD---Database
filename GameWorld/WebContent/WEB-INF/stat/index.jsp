<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">
<html>
<head>
<meta http-equiv="Content-type" content="text/html;charset=UTF-8">
<title>Statistics</title>
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
				<li><a href="../index.jsp">Back</a>
				<li><a href="index.jsp?action=stat">Get Stat</a>
					<hr>
				<li><a href="../login/logout.jsp">Logout</a>
			</ul>
		</div>
		<div id="main">
			<!-- Main content -->
			<h1>Statistics</h1>
			<p>From here you can generate statistics!</p>
			<form method="POST" action="index.jsp">
				<!-- This is for creating -->
				<table>
					<br><a class="btn btn-large btn-primary"href="../graphtest.jsp">20 Most Popular games</a>
<%-- 					<tr>
						<td>Games</td>

						<td>Sex:</td>
						<td><select name="newUserSex">
								<option value=1>20 Most Popular games</option>
								<option value=0> </option>
						</select></td>
						<td>Language:</td>
						<td><select name="newUserLang">
								<%
									for (int i = 0; i < langList.size(); i++) {
										LangDTO l = (LangDTO) langList.get(i);
								%>
								<option value=<%=l.getLangid()%>><%=l.getLang()%></option>
								<%
									}
								%>
						</select></td>
					</tr>
					<tr>
						<td colspan="2" align="right"><input type="hidden"
							name="action" value="userFilled"><input type="submit"
							value="Search >>"></td>
						<td></td>
					</tr> --%>
				</table>
			</form>
		</div>
		<div class="footer">Gruppe 8</div>
	</div>
</body>
</html>
