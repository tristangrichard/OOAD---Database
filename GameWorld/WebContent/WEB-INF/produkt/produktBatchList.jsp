<%@include file="header.jsp"%>
<jsp:useBean id="produktBatchList" class="java.util.ArrayList"
	scope="request" />
<%@ page import="dto.ProduktBatchDTO"%>
<h1>ProduktBatch List</h1>
<table class="list">
	<tr>
		<th>ProduktBatch ID</th>
		<th>Recept ID</th>
		<th>Status</th>
		<th colspan="2"></th>
	</tr>
	<% for (int i=0; i < produktBatchList.size(); i++) { 
			ProduktBatchDTO p = (ProduktBatchDTO) produktBatchList.get(i);%>
	<tr <% if (i%2 != 0) out.print(" class=\"alt\""); %>>
		<td><%= p.getPbId() %></td>
		<td><%= p.getReceptId() %></td>
		<td><%= p.getStatus() %></td>
		<td><a
			href="index.jsp?action=updateProduktBatch&amp;PBIDToUpdate=<%= p.getPbId() %>">Edit</a></td>
		<td><a
			href="index.jsp?action=viewProduktBatch&amp;PBIDToView=<%= p.getPbId() %>">View</a></td>
	</tr>
	<% } %>
</table>

<%@include file="footer.jsp"%>