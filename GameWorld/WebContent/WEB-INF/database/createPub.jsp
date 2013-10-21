<%@include file="header.jsp"%>
<jsp:useBean id="langList" class="java.util.ArrayList" scope="request" />
<%@ page import="dto.LangDTO" %>
<h1>Create Publisher</h1>
<form method="POST" action="index.jsp">
	<!-- This is for creating -->
	<table>
		<tr>
			<td>Publisher:</td>
			<td><input type="text" name="newPub" required
				value="<% if (request.getParameter("newPub") != null)
				out.print(request.getParameter("newPub"));%>"
				onclick="this.select()"></td>
		</tr>
		<tr>
			<td>Nationality:</td>
			<td><select name="newLang">
				<% for (int i = 0; i< langList.size(); i++) {
				LangDTO l = (LangDTO) langList.get(i); %>
					<option value= <%= l.getLang() %> <%if (l.getLang().equalsIgnoreCase(request.getParameter("newLang"))) { out.print("selected");}%>><%= l.getLang() %></option>
				<%} %>
			</select></td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input type="hidden" name="action"
				value="pubFilled"><input type="submit"
				value="Create Publisher"></td>
			<td></td>
		</tr>
	</table>
</form>
<%@include file="footer.jsp"%>