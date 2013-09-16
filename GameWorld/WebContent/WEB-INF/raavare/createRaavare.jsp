<%@include file="header.jsp"%>
<h1>Create Råvare</h1>
<form method="POST" action="index.jsp">
	<!-- This is for Updating -->
	<table>
		<tr>
			<td>ID:</td>
			<td><input type="text" name="newRaavareID" value="<% if (request.getParameter("newRaavareID") != null) out.print(request.getParameter("newRaavareID")); %>"
				onclick="this.select()"></td>
			<td class="expl">Number between 1-9999999, needs to be unique</td>
		</tr>
		<tr>
			<td>Name:</td>
			<td><input type="text" name="newRaavareName" value="<% if (request.getParameter("newRaavareName") != null) out.print(request.getParameter("newRaavareName")); %>"
				onclick="this.select()"></td>
			<td class="expl">Needs to be between 2-20 characters.</td>
		</tr>
		<tr>
			<td>Deliverer:</td>
			<td><input type="text" name="newRaavareLeverandoer" value="<% if (request.getParameter("newRaavareLeverandoer") != null) out.print(request.getParameter("newRaavareLeverandoer")); %>"
				onclick="this.select()"></td>
			<td class="expl">Needs to be between 2-20 characters.</td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input type="hidden" name="action"
				value="createRaavare"><input type="submit"
				value="Create Raavare"></td>
				<td>
		</tr>
	</table>
</form>
<%@include file="footer.jsp"%>