<%@include file="header.jsp"%>
<jsp:useBean id="userList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="gameUrl" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="idList" class="java.util.ArrayList" scope="request" />
<%@ page import="dto.UsersDTO" %>
<h1>User List</h1>
<form method="POST" action="index.jsp">
	<!-- This is for creating -->
	<table class="list">
		<% for (int i = 0; i< userList.size(); i++) {
		UsersDTO user = (UsersDTO) userList.get(i); %>
		<tr><td> <%= user.getFname()+" "+user.getLname() %></td><td><%= user.getEmail() %></td>
		<td><a href="index.jsp?action=updateUser&amp;userToUpdate=<%= user.getEmail() %>">Edit</a></td>
		</tr>
		<%}%>

	</table>
</form>
<%@include file="footer.jsp"%>