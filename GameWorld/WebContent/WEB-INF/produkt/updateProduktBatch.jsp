<%@include file="header.jsp"%>
<%@ page import="dto.ProduktBatchDTO"%>
<%@ page import="dto.ProduktBatchKompDTO"%>
<jsp:useBean id="PBToUpdate" class="dto.ProduktBatchDTO" scope="request" />
<h1>Update ProduktBatch</h1>
<form method="POST" action="index.jsp">
	<!-- This is for Updating -->
	<table>
		<tr>
			<td>ProduktBatch ID:</td>
			<td><%=PBToUpdate.getPbId()%></td>
			<td></td>
		</tr>
		<tr>
			<td>Recept ID:</td>
			<td><%=PBToUpdate.getReceptId()%></td>
			<td></td>
		</tr>
		<tr>
			<td>Status:</td>
			<td><input type="text" name="updStatus"
				value="<% if(request.getParameter("updStatus") != null) out.print(request.getParameter("updStatus")); else out.print(PBToUpdate.getStatus()); %>" onclick="this.select()" size="2"></td>
			<td class="expl">See below for status explanations.</td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input type="hidden" name="action"
				value="updateProduktBatchFilled"> <input type="hidden"
				name="PBIDToUpdate" value="<%=PBToUpdate.getPbId()%>"> <input
				type="hidden" name="updReceptID"
				value="<%=PBToUpdate.getReceptId()%>"><input type="submit"
				value="Update ProduktBatch"></td>
				<td></td>
		</tr>
	</table>
</form>
<p>
Explanation of different statusnumbers:
<ol start="0">
<li>produktbatch not started yet.</li>
<li>produktbatch started.</li>
<li>produktbatch finished.</li>
</ol>
</p>
<%@include file="footer.jsp"%>