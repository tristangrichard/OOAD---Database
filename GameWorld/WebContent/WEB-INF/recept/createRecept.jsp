<%@include file="header.jsp"%>
<h1>Create Recept</h1>
<form method="POST" action="index.jsp">
	<!-- This is for Updating -->
	<table>
		<tr>
			<td>Recept ID:</td>
			<td><input type="text" name="newReceptID"
				value="<%if (request.getParameter("newReceptID") != null)
				out.print(request.getParameter("newReceptID"));%>"
				onclick="this.select()"></td>
			<td class="expl">Number between 1-9999999, needs to be unique.</td>
		</tr>
		<tr>
			<td>Name:</td>
			<td><input type="text" name="newReceptName"
				value="<%if (request.getParameter("newReceptName") != null)
				out.print(request.getParameter("newReceptName"));%>"
				onclick="this.select()"></td>
			<td class="expl">Needs to be between 2-20 characters.</td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input type="hidden" name="action"
				value="createRecept"><input type="submit" value="Create"></td>
			<td></td>
		</tr>
	</table>
</form>

<%@include file="footer.jsp"%>