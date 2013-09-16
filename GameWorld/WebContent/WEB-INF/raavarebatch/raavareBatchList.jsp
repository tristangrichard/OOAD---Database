<%@include file="header.jsp"%>
<jsp:useBean id="raavareBatchList" class="java.util.ArrayList"
	scope="request" />
<%@ page import="dto.RaavareBatchDTO"%>
<h1>R�vare Batch List</h1>
<form method="POST" action="index.jsp">
	<table class="list">
		<tr>
			<th>R�vare Batch ID</th>
			<th>R�vare ID</th>
			<th>M�ngde[kg]</th>
			<th></th>
		</tr>
		<%
			for (int i = 0; i < raavareBatchList.size(); i++) {
				RaavareBatchDTO r = (RaavareBatchDTO) raavareBatchList.get(i);
		%>
		<tr <%if (i % 2 != 0)
					out.print(" class=\"alt\"");%>>
			<td><%=r.getRbId()%></td>
			<td><%=r.getRaavareId()%></td>
			<td><%=r.getMaengde()%></td>
			<td><a
				href="index.jsp?action=updateRaavareBatch&amp;raavareBatchIDToUpdate=<%=r.getRbId()%>">Edit</a></td>
		</tr>
		<%
			}
		%>
	</table>
</form>

<%@include file="footer.jsp"%>