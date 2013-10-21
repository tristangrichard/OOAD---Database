<%@include file="header.jsp"%>
<jsp:useBean id="List" class="java.util.ArrayList" scope="request" />
<%@ page import="dto.GenreDTO" %>
<h1>Genre List</h1>
<form method="POST" action="index.jsp">
	<!-- This is for creating -->
	<table class="list">
	
		<%
		for(int i = 0; i < List.size(); i++) { 
		GenreDTO genre = (GenreDTO) List.get(i);
		%>
			<tr>
			<td> <%= genre.getGenre() %></td>
			</tr>
		
		<%}%>
	</table>
</form>
<%@include file="footer.jsp"%>