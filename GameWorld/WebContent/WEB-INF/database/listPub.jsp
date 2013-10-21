<%@include file="header.jsp"%>
<jsp:useBean id="List" class="java.util.ArrayList" scope="request" />
<%@ page import="dto.PublisherDTO" %>
<h1>Publisher List</h1>
<form method="POST" action="index.jsp">
	<!-- This is for creating -->
	<table class="list">
	
		<%
		for(int i = 0; i < List.size(); i++) { 
		PublisherDTO pub = (PublisherDTO) List.get(i);
		%>
			<tr>
			<td> <%= pub.getPublisher() %></td>
			</tr>
		
		<%}%>
	</table>
</form>
<%@include file="footer.jsp"%>