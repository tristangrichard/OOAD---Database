<%@include file="header.jsp"%>
<jsp:useBean id="gameList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="gameUrl" class="java.util.ArrayList" scope="request" />
<h1>My Games</h1>
<form method="POST" action="index.jsp">
	<!-- This is for creating -->
	<table class="list">
		<tr width="100">
			<!--  <th>Råvare ID</th>
			<th>Mængde[kg]</th>
			<th></th>   -->
		</tr>
		
		<%
		int i = 0;
		int j = 0;
		int a = 0;
		while (j < gameList.size()){
			a = 0;
		
			while (i < gameList.size()) {
				String name = gameList.get(i).toString();
				
		%>
		<!-- <tr <%if (i % 4 != 0)
					out.print(" class=\"alt\"");%>> -->
					
		<td><%= name %></td>
		
		
		<% 
			a++;
			i++;
			if (a == 6)break;
		} %>
		</tr>
		<tr>
		<%	
			a = 0;
			while (j < gameList.size()) {
			String url = gameUrl.get(j).toString();	%>
			<td>	
			<img alt="" src="<%= url %>" width="100" height="145">
			</td>
			

		<% 
			a++;
			j++;
			if (a == 6)break;
			}
		%>
		</tr>
		<% } %>
	</table>
</form>
<%@include file="footer.jsp"%>