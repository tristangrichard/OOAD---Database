<%@include file="header.jsp"%>
<h1>Create new operator</h1>
<form method="POST" action="index.jsp">
	<!-- This is for creating -->
	<table>
		<tr>
			<td>Name:</td>
			<td><input type="text" name="newOprName"
				value="<% if (request.getParameter("newOprName") != null)
				out.print(request.getParameter("newOprName"));%>"
				onclick="this.select()"></td>
			<td class="expl">2-20 characters, at least 2 words.</td>
		</tr>
		<tr>
			<td>Operator-ID:</td>
			<td><input type="text" name="newOprID"
				value="<% if (request.getParameter("newOprID") != null)
				out.print(request.getParameter("newOprID"));%>"
				onclick="this.select()"></td>
			<td class="expl">Number between 1-9999999, needs to be unique.</td>
		</tr>
		<tr>
			<td>CPR-number:</td>
			<td><input type="text" name="newOprCpr"
				value="<% if (request.getParameter("newOprCpr") != null)
				out.print(request.getParameter("newOprCpr"));
			else {%>DDMMYY-XXXX <%}%>"
				onclick="this.select()"></td>
			<td class="expl">Valid CPR number.</td>
		</tr>
		<tr>
			<td>Role:</td>
			<td><select name="newOprRole">
					<option value="administrator">administrator</option>
					<option value="farmaceut">farmaceut</option>
					<option value="vaerkfoerer">værkfører</option>
					<option value="operatoer">operatør</option>
					<option value="inaktiv">inaktiv</option>
			</select></td>
			<td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input type="hidden" name="action"
				value="createOpr"><input type="submit"
				value="Create Operator"></td>
			<td></td>
		</tr>
	</table>
</form>
<%@include file="footer.jsp"%>