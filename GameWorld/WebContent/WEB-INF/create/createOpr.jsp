<jsp:useBean id="langList" class="java.util.ArrayList"
	scope="request" />
<%@ page import="dto.LangDTO" %>
<link rel="stylesheet" href="../css/style1.css">
	
 	 <div class="container">
      <form class="form-signin" method="POST" action="index.jsp">

		<h2 class="form-signin-heading">GameWorld</h2>
		<div id="messagecontainer">
			<div class="error">
				${error}
			</div>
			<div class="message">
				${message}
			</div>
		</div>



			<!-- Site navigation menu -->

				<br><a class="btn btn-large btn-success" href="../index.jsp">Cancel >></a>
				<br><a class="btn btn-large btn-success"href="../login/index.jsp">Login >></a>
		
			 <h2 class="form-signin-heading">Create User</h2>

	<!-- This is for creating -->
	<table>
		<tr>
			<td>First Name:</td>
			<td><input type="text" class="input-block-level" name="newFName" required value="<% if (request.getParameter("newFName") != null)
				out.print(request.getParameter("newFName"));%>"
				onclick="this.select()"></td>
		
			<td>Last Name:</td>
			<td><input type="text" class="input-block-level" name="newLName" required
				value="<% if (request.getParameter("newLName") != null)
				out.print(request.getParameter("newLName"));%>"
				onclick="this.select()"></td>
		</tr>
		
		<tr>
			<td>Email:</td>
			<td><input type="email" class="input-block-level" name="newUserEmail"
				value="<% if (request.getParameter("newUserEmail") != null)
				out.print(request.getParameter("newUserEmail"));%>"
				placeholder="Email address" onclick="this.select()"></td>	
		</tr>
		
		<tr>
			<td>Date of Birth:</td>
			<td><input type="text" class="input-block-level" name="newUserBirth"
				value="<% if (request.getParameter("newUserBirth") != null)
				out.print(request.getParameter("newUserBirth"));
			else {%>DD-MM-YYYY <%}%>"
				placeholder="DD-MM-YYYY" onclick="this.select()"></td>
		</tr>
		<tr>
			<td>Sex:</td>
			<td><select name="newUserSex">
					<option value= 1 >Male</option>
					<option value= 0 >Female</option>
			</select></td>
				<td>Language:</td>
			<td><select name="newUserLang">
				<% for (int i = 0; i< langList.size(); i++) {
				LangDTO l = (LangDTO) langList.get(i); %>
					<option value= <%= l.getLangid() %> ><%= l.getLang() %></option>
				<%} %>
			</select></td>
		</tr>
		<tr>
			<td><input type="hidden" name="action"
				value="userFilled">
				<input class="btn btn-large btn-success1" type="submit"
				value="Create User >>"></td>
			<td></td>
		</tr>
	</table>
</form>
<%@include file="footer.jsp"%>