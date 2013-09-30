<%@include file="header.jsp"%>
<jsp:useBean id="gameList" class="java.util.ArrayList" scope="request" />
<%@ page import="dto.GameDTO"%>
<h1>Add new game</h1>
<form method="POST" action="index.jsp">
	<!-- This is for creating -->
	<table>
		<td>Select game</td>
		<td><select name="gameToAdd">
				<% for (int i = 0; i< gameList.size(); i++) {
				GameDTO g = (GameDTO) gameList.get(i); %>
				<option value=<%= g.getGid() %>><%= g.getGname() %></option>
				<%} %>
			</select>
		</td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input type="hidden" name="action"
				value="gameToAdd"><input type="submit" value="Add Game"></td>
			<td></td>
		</tr>
	</table>
</form>
<%@include file="footer.jsp"%>