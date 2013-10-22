<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="langList" class="java.util.ArrayList" scope="request" />
<%@ page import="dto.LangDTO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">
<html>
<head>
<meta http-equiv="Content-type" content="text/html;charset=UTF-8">
<title>Publisher Administration</title>
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
				<li><a href="index.jsp?action=listPub">List Publisher</a>
				<li><a href="../
				login/logout.jsp">Logout</a>
			</ul>
		</div>
		<div id="main">
			<!-- Main content -->

			<h1>Create Publisher</h1>
			<form method="POST" action="index.jsp">
				<!-- This is for creating -->
				<table>
					<tr>
						<td>Publisher:</td>
						<td><input type="text" name="newPub" required
							value="<%if (request.getParameter("newPub") != null)
				out.print(request.getParameter("newPub"));%>"
							onclick="this.select()"></td>
					</tr>
					<tr>
						<td>Nationality:</td>
						<td><select name="newLang">
								<%
									for (int i = 0; i < langList.size(); i++) {
										LangDTO l = (LangDTO) langList.get(i);
								%>
								<option value=<%=l.getLang()%>
									<%if (l.getLang().equalsIgnoreCase(
						request.getParameter("newLang"))) {
					out.print("selected");
				}%>><%=l.getLang()%></option>
								<%
									}
								%>
						</select></td>
					</tr>
					<tr>
						<td colspan="2" align="right"><input type="hidden"
							name="action" value="pubFilled"><input type="submit"
							value="Create Publisher"></td>
						<td></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="footer">Gruppe 8</div>
	</div>
</body>
</html>