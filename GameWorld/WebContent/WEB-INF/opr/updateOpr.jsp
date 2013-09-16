<jsp:useBean id="userToUpdate" class="dto.OperatoerDTO" scope="session" />
<%@include file="header.jsp"%>
<h1>Update Operator</h1>
<form method="POST" action="index.jsp">
	<!-- This is for Updating -->
	<table>
		<tr>
			<td>ID:</td>
			<td><%=userToUpdate.getOprId()%></td>
			<td class="expl"></td>
		</tr>
		<tr>
			<td>Name:</td>
			<td><input type="text" name="updOprName"
				value="<% if (request.getParameter("updOprName") != null) out.print(request.getParameter("updOprName")); else out.print(userToUpdate.getOprNavn()); %>" onclick="this.select()"></td>
			<td class="expl">2-20 characters, at least 2 words.</td>
		</tr>
		<tr>
			<td>CPR-number:</td>
			<td><input type="text" name="updOprCpr"
				value="<% if (request.getParameter("updOprCpr") != null) out.print(request.getParameter("updOprCpr")); else out.print(userToUpdate.getCpr()); %>" onclick="this.select()"></td>
			<td class="expl">Valid CPR number.</td>
		</tr>
		<tr>
			<td>Role:</td>
			<td><select name="updOprRole">
					<option value="administrator"
						<%if (userToUpdate.getRolle().equals("administrator")) {
				out.print("selected");
			}%>>administrator</option>
					<option value="farmaceut"
						<%if (userToUpdate.getRolle().equals("farmaceut")) {
				out.print("selected");
			}%>>farmaceut</option>
					<option value="vaerkfoerer"
						<%if (userToUpdate.getRolle().equals("vaerkfoerer")) {
				out.print("selected");
			}%>>vaerkfoerer</option>
					<option value="operatoer"
						<%if (userToUpdate.getRolle().equals("operatoer")) {
				out.print("selected");
			}%>>operatoer</option>
					<option value="inaktiv"
						<%if (userToUpdate.getRolle().equals("inaktiv")) {
				out.print("selected");
			}%>>inaktiv</option>
			</select></td>
		</tr>
		<tr>
			<td>New Password:</td>
			<td><input type="password" name="updOprPass1" value=""
				onclick="this.select()"></td>
			<td class="expl">See below for password rules.</td>
		</tr>
		<tr>
			<td>New Password Again:</td>
			<td><input type="password" name="updOprPass2" value=""
				onclick="this.select()"></td>
			<td></td>
		</tr>
		<tr>
			<td>Old Password:</td>
			<td><input type="password" name="updOprPassOld" value=""
				onclick="this.select()"></td>
			<td></td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input type="hidden" name="action"
				value="updateOprFilled"> <input type="hidden"
				name="oprIDToUpdate" value="<%=userToUpdate.getOprId()%>"> <input
				type="submit" value="Update Operator"></td>
			<td></td>
		</tr>
	</table>
</form>
<p>The password needs to contain between 7 and 8 characters of at
	least three of the following four categories: small letters ('a' -
	'z'), capital letters ('A' - 'Z'), digits ('0' - '9') and any of the
	following special characters: ('.', '-', '_', '+', '!', '?', '=').</p>
<%@include file="footer.jsp"%>