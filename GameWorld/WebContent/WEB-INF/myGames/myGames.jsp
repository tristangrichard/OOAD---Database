<%@include file="header.jsp"%>
<jsp:useBean id="gameList" class="java.util.ArrayList"
	scope="request" />
<%@ page import="dto.GameDTO" %>
<h1>My Games</h1>
<form method="POST" action="index.jsp">
	<!-- This is for creating -->
	<table class="list">
		<tr>
			<th>Title</th>
			<!--  <th>Råvare ID</th>
			<th>Mængde[kg]</th>
			<th></th>   -->
		</tr>
		<%
			for (int i = 0; i < gameList.size(); i++) {
				GameDTO g = (GameDTO) gameList.get(i);
		%>
		<tr <%if (i % 2 != 0)
					out.print(" class=\"alt\"");%>>
			<td><%=g.getGname()%></td>
		<!-- <td></td>
			<td></td>
			<td><a
				href="index.jsp?action=updateRaavareBatch&amp;raavareBatchIDToUpdate=>Edit</a></td>
		</tr> -->	
		<%
			}
		%>
	</table>
</form>
<%@include file="footer.jsp"%>