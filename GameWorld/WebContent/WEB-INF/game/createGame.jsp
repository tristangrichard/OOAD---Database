<%@include file="header.jsp"%>
<jsp:useBean id="langList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="genreList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="osList" class="java.util.ArrayList" scope="request" />
<%@ page import="dto.LangDTO" %>
<%@ page import="dto.GenreDTO" %>
<%@ page import="dto.OSDTO" %>

<h1>Create Game</h1>
<form method="POST" action="index.jsp">
	<!-- This is for creating -->
	<table>
		<tr>
			<td>Title:</td>
			<td><input type="text" name="newTitle" required
				value="<% if (request.getParameter("newTitle") != null)
				out.print(request.getParameter("newTitle"));%>"
				onclick="this.select()"></td>
		</tr>
		<tr>
			<td>Pictue URL:</td>
			<td><input type="text" name="newUrl" required
				value="<% if (request.getParameter("newUrl") != null)
				out.print(request.getParameter("newUrl"));%>"
				onclick="this.select()"></td>
		</tr>
	<tr>
			<td>Release date:</td>
			<td><input type="text" name="newRelease"
				value="DD-MM-YYYY"
				onclick="this.select()"></td>
			<td class="expl"></td>
		</tr>
		<tr>
			<td>Genre:</td>
			<td><select name="newGenre" multiple="multiple">
			<% for (int i = 0; i< genreList.size(); i++) {
				GenreDTO g = (GenreDTO) genreList.get(i); %>
					<option value= <%= g.getGenreid() %> ><%= g.getGenre() %></option>
				<%} %>
			</select></td>
		</tr>
		<tr>
			<td>Language:</td>
			<td><select name="newLang" multiple="multiple">
				<% for (int i = 0; i< langList.size(); i++) {
				LangDTO l = (LangDTO) langList.get(i); %>
					<option value= <%= l.getLangid() %> ><%= l.getLang() %></option>
				<%} %>
			</select></td>
		</tr>
		<tr>
			<td>Operating System:</td>
			<td><select name="newOS" multiple="multiple">
				<% for (int i = 0; i< osList.size(); i++) {
				OSDTO o = (OSDTO) osList.get(i); %>
					<option value= <%= o.getOsid() %> ><%= o.getOs() %></option>
				<%} %>
			</select></td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input type="hidden" name="action"
				value="gameFilled"><input type="submit"
				value="Create Game"></td>
			<td></td>
		</tr>
	</table>
</form>
<%@include file="footer.jsp"%>