<%@include file="header.jsp"%>
<jsp:useBean id="userList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="compList" class="java.util.ArrayList" scope="request" />
<%@ page import="dto.UsersDTO" %>
<%@ page import="dto.PublisherDTO" %>
<h1>Registered publishers</h1>
<form method="POST" action="index.jsp">
	<!-- This is for creating -->
	<table class="list">
	<% for (int i = 0; i< userList.size(); i++) {
		UsersDTO user = (UsersDTO) userList.get(i);
		PublisherDTO comp = (PublisherDTO) compList.get(i); %>
		<tr><td> <%= user.getFname()+" "+user.getLname() %></td><td> <%= comp.getPublisher() %></td>
		<td><a href="index.jsp?action=updateUser&amp;userToUpdate=<%= user.getEmail() %>">Edit</a></td>
		</tr>
		<%}%>
	</table>
</form>
<%@include file="footer.jsp"%>