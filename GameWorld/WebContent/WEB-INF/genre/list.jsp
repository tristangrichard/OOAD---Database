<%@include file="header.jsp"%>
<jsp:useBean id="genreList" class="java.util.ArrayList" scope="request" />
<%@ page import="dto.GenreDTO" %>
<h1>Genre List</h1>
<form method="POST" action="index.jsp">
	<!-- This is for creating -->
	<table class="list">
	
		<%for(int i = 0; i < genreList.size(); i++) { 
		GenreDTO genre = (GenreDTO) genreList.get(i);
		%>
			<tr>
			<td> <%= genre.getGenre() %></td>
			</tr>
		
		<% } %>
	</table>
</form>
<%@include file="footer.jsp"%>