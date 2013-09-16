<%@include file="header.jsp"%>
<jsp:useBean id="raavareList" class="java.util.ArrayList"
	scope="request" />
<%@ page import="dto.RaavareDTO"%>
<h1>Råvare List</h1>

<table class="list">
	<tr>
		<th>ID</th>
		<th>Name</th>
		<th>Deliverer</th>
		<th></th>
	</tr>
	<% for (int i=0; i < raavareList.size(); i++) { 
			RaavareDTO r = (RaavareDTO) raavareList.get(i);%>
	<tr <% if (i%2 != 0) out.print(" class=\"alt\""); %>>
		<td><%= r.getRaavareId() %></td>
		<td><%= r.getRaavareNavn() %></td>
		<td><%= r.getLeverandoer() %></td>
		<td><a
			href="index.jsp?action=updateRaavare&amp;raavareIDToUpdate=<%= r.getRaavareId() %>">Edit</a></td>
	</tr>
	<% } %>
</table>

<%@include file="footer.jsp"%>