<%@include file="header.jsp"%>
<%@ page import="dto.RaavareDTO"%>
<jsp:useBean id="raavareToUpdate" class="dto.RaavareDTO"
	scope="request" />
<h1>Update Råvare</h1>
<form method="POST" action="index.jsp">
	<!-- This is for Updating -->
	<table>
		<tr>
			<td>Name:</td>
			<td><input type="text" name="updRaavareName"
				value="<% if(request.getParameter("updRaavareName") != null) out.print(request.getParameter("updRaavareName")); else out.print(raavareToUpdate.getRaavareNavn()); %>"
				onclick="this.select()"></td>
				<td class="expl">Needs to be between 2-20 characters.</td>
		</tr>
		<tr>
			<td>Deliverer:</td>
			<td><input type="text" name="updRaavareLeverandoer"
				value="<% if(request.getParameter("updRaavareLeverandoer") != null) out.print(request.getParameter("updRaavareLeverandoer")); else out.print(raavareToUpdate.getLeverandoer()); %>"
				onclick="this.select()"></td>
				<td class="expl">Needs to be between 2-20 characters.</td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input type="hidden" name="action"
				value="updateRaavareFilled"> <input type="hidden"
				name="raavareIDToUpdate" value="<%=raavareToUpdate.getRaavareId()%>"><input
				type="submit" value="Update Raavare"></td>
				<td></td>
		</tr>
	</table>
</form>

<%@include file="footer.jsp"%>