<%@include file="header.jsp"%>
<%@ page import="dto.RaavareBatchDTO"%>
<jsp:useBean id="raavareBatchToUpdate" class="dto.RaavareBatchDTO"
	scope="request" />
<h1>Update Råvare Batch</h1>
<form method="POST" action="index.jsp">
	<!-- This is for Updating -->
	<table>
		<tr>
			<td>Raavare Batch ID:</td>
			<td><%=raavareBatchToUpdate.getRbId()%></td>
		</tr>
		<tr>
			<td>Raavare ID:</td>
			<td><input type="text" name="updRaavareID"
				value="<% if (request.getParameter("updRaavareID") != null)
				out.print(request.getParameter("updRaavareID")); else out.print(raavareBatchToUpdate.getRaavareId());%>"
				onclick="this.select()"></td>
			<td class="expl">Number between 1-9999999, needs to exist.</td>
		</tr>
		<tr>
			<td>Maengde:</td>
			<td><input type="text" name="updMaengde"
				value="<% if (request.getParameter("updMaengde") != null)
				out.print(request.getParameter("updMaengde")); else out.print(raavareBatchToUpdate.getMaengde()); %>"
				onclick="this.select()"></td>
			<td class="expl">Needs to be between 2-20 characters.</td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input type="hidden" name="action"
				value="updateRaavareBatchFilled"> <input type="hidden"
				name="raavareBatchIDToUpdate" value="<%=raavareBatchToUpdate.getRbId()%>"><input
				type="submit" value="Update Raavare Batch"></td>
			<td></td>
		</tr>
	</table>
</form>

<%@include file="footer.jsp"%>