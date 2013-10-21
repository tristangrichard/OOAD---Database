<%@include file="header.jsp"%>
<h1>Create genre</h1>
<form method="POST" action="index.jsp">
	<!-- This is for creating -->
	<table>
		<tr>
			<td>Genre:</td>
			<td><input type="text" name="newGenre" required
				value="<% if (request.getParameter("newGenre") != null)
				out.print(request.getParameter("newGenre"));%>"
				onclick="this.select()"></td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input type="hidden" name="action"
				value="genreFilled"><input type="submit"
				value="Create Genre"></td>
			<td></td>
		</tr>
	</table>
</form>
<%@include file="footer.jsp"%>