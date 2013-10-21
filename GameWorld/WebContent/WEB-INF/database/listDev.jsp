<%@include file="header.jsp"%>
<jsp:useBean id="List" class="java.util.ArrayList" scope="request" />
<%@ page import="dto.DeveloperDTO" %>
<h1>Developer List</h1>
<form method="POST" action="index.jsp">
	<!-- This is for creating -->
	<table class="list">
	
		<%
		for(int i = 0; i < List.size(); i++) { 
		DeveloperDTO dev = (DeveloperDTO) List.get(i);
		%>
			<tr>
			<td> <%= dev.getDeveloper() %></td>
			</tr>
		
		<%}%>
	</table>
</form>
<%@include file="footer.jsp"%>