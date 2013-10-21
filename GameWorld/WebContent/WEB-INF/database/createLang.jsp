<%@include file="header.jsp"%>
<h1>Create Language</h1>
<form method="POST" action="index.jsp">
	<!-- This is for creating -->
	<table>
		<tr>
			<td>Language:</td>
			<td><input type="text" name="newLang" required
				value="<% if (request.getParameter("newGenre") != null)
				out.print(request.getParameter("newGenre"));%>"
				onclick="this.select()"></td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input type="hidden" name="action"
				value="langFilled"><input type="submit"
				value="Create Language"></td>
			<td></td>
		</tr>
	</table>
</form>
<%@include file="footer.jsp"%>