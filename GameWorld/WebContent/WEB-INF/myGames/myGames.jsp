<%@include file="header.jsp"%>
<jsp:useBean id="gameList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="gameUrl" class="java.util.ArrayList" scope="request" />
<h1>My Games</h1>
<form method="POST" action="index.jsp">
	<!-- This is for creating -->
	<table class="list">
		<tr width="100">
			<th>Title</th><th>Preview</th>
			<!--  <th>Råvare ID</th>
			<th>Mængde[kg]</th>
			<th></th>   -->
		</tr>
		
		<%
			for (int i = 0; i < gameList.size(); i++) {
				String name = gameList.get(i).toString();
				String url = gameUrl.get(i).toString();
		%>
		<tr <%if (i % 2 != 0)
					out.print(" class=\"alt\"");%>>
					
		<td><%= name %></td>
		<td>	
		<a href="<%= url %>" class="image" title="<%= name %>">
		<img alt="<%= name %>" src="<%= url %>" width="100" height="145"></a>
		</td>
		</tr> 
		<% 
			}
		%>
	</table>
</form>
<%@include file="footer.jsp"%>