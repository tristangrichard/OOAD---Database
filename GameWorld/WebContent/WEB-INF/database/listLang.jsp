<%@include file="header.jsp"%>
<jsp:useBean id="List" class="java.util.ArrayList" scope="request" />
<%@ page import="dto.LangDTO" %>
<h1>Language List</h1>
<form method="POST" action="index.jsp">
	<!-- This is for creating -->
	<table class="list">
	
		<%
		for(int i = 0; i < List.size(); i++) { 
		LangDTO lang = (LangDTO) List.get(i);
		%>
			<tr>
			<td> <%= lang.getLang() %></td>
			</tr>
		
		<%}%>
	</table>
</form>
<%@include file="footer.jsp"%>