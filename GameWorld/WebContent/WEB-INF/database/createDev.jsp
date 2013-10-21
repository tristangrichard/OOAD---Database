<%@include file="header.jsp"%>
<jsp:useBean id="langList" class="java.util.ArrayList" scope="request" />
<%@ page import="dto.LangDTO" %>
<h1>Create Developer</h1>
<form method="POST" action="index.jsp">
	<!-- This is for creating -->
	<table>
		<tr>
			<td>Developer:</td>
			<td><input type="text" name="newDev" required
				value="<% if (request.getParameter("newDev") != null)
				out.print(request.getParameter("newDev"));%>"
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
				value="devFilled"><input type="submit"
				value="Create Developer"></td>
			<td></td>
		</tr>
	</table>
</form>
<%@include file="footer.jsp"%>