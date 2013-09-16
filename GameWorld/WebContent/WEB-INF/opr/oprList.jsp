<jsp:useBean id="oprList" class="java.util.ArrayList" scope="request" />
<%@ page import="dto.BrugerDTO" %>
<%@include file="header.jsp"%>
<h1>Operator List</h1>
	<table class="list">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Ini</th>
			<th>Rolle</th>
			<th colspan="2"></th>
		</tr>
		<% for (int i=0; i < oprList.size(); i++) { 
		BrugerDTO opr = (BrugerDTO) oprList.get(i);%>
		<tr<% if (i%2 != 0) out.print(" class=\"alt\""); %>>
			<td><%= opr.getOprId() %></td>
			<td><%= opr.getOprNavn() %></td>
			<td><%= opr.getIni() %></td>
			<td><%= opr.getRolle() %></td>
			<td><a
				href="index.jsp?action=updateOpr&amp;oprIDToUpdate=<%= opr.getOprId() %>">Edit</a></td>
			<td><% if (!"inaktiv".equals(opr.getRolle())) { %><a
				href="index.jsp?action=deleteOpr&amp;oprIDToDelete=<%= opr.getOprId() %>">Deactivate</a> <% } %></td>
		</tr>
		<% } %>
	</table>
<%@include file="footer.jsp"%>
