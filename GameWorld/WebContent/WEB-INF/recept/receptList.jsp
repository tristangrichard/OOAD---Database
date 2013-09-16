<%@include file="header.jsp"%>
<jsp:useBean id="receptList" class="java.util.ArrayList" scope="request" />
<%@ page import="dto.ReceptDTO"%>
<h1>Recept List</h1>
<table class="list">
	<tr>
		<th>ID</th>
		<th>Name</th>
		<th colspan="2"></th>
	</tr>
	<%
		for (int i = 0; i < receptList.size(); i++) {
			ReceptDTO r = (ReceptDTO) receptList.get(i);
	%>
	<tr <%if (i % 2 != 0)
					out.print(" class=\"alt\"");%>>
		<td><%=r.getReceptId()%></td>
		<td><%=r.getReceptNavn()%></td>
		<td><a
			href="index.jsp?action=updateRecept&amp;receptIDToUpdate=<%=r.getReceptId()%>">Edit</a></td>
		<td><a
			href="index.jsp?action=viewRecept&amp;receptIDToView=<%=r.getReceptId()%>">View</a></td>
	</tr>
	<%
		}
	%>
</table>
<%@include file="footer.jsp"%>