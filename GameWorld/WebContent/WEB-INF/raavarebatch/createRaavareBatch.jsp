<%@include file="header.jsp"%>
<h1>Create Råvarebatch</h1>
<form method="POST" action="index.jsp">
	<!-- This is for Updating -->

	<table>
		<tr>
			<td>Raavare Batch ID:</td>
			<td><input type="text" name="newRbId" value="<%if (request.getParameter("newRbId") != null)
				out.print(request.getParameter("newRbId"));%>"
				onclick="this.select()"></td>
			<td class="expl">Number between 1-9999999, needs to be unique.</td>
		</tr>
		<tr>
			<td>Raavare ID:</td>
			<td><input type="text" name="newRaavareId" value="<%if (request.getParameter("newRaavareId") != null)
				out.print(request.getParameter("newRaavareId"));%>"
				onclick="this.select()"></td>
			<td class="expl">Number between 1-9999999, needs to exist.</td>
		</tr>
		<tr>
			<td>Maengde:</td>
			<td><input type="text" name="newMaengde" value="<%if (request.getParameter("newMaengde") != null)
				out.print(request.getParameter("newMaengde"));%>"
				onclick="this.select()"></td>
			<td class="expl">Mass in kg.</td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input type="hidden" name="action"
				value="createRaavareBatch"><input type="submit"
				value="Create Raavare Batch"></td>
			</td>
		</tr>
	</table>
</form>

<%@include file="footer.jsp"%>