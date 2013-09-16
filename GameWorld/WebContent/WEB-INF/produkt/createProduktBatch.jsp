<%@include file="header.jsp"%>
<h1>Create ProduktBatch</h1>
<form method="POST" action="index.jsp">
	<!-- This is for Updating -->
	<table>
		<tr>
			<td>ProduktBatch ID:</td>
			<td><input type="text" name="newPBID" value="<% if (request.getParameter("newPBID") != null)
				out.print(request.getParameter("newPBID"));%>"
				onclick="this.select()"></td>
			<td class="expl">Number between 1-9999999, needs to be unique</td>
		</tr>
		<tr>
			<td>Recept ID:</td>
			<td><input type="text" name="newReceptID" value="<% if (request.getParameter("newReceptID") != null)
				out.print(request.getParameter("newReceptID"));%>"
				onclick="this.select()"></td>
			<td class="expl">ID for the recept to make with this produktbatch. Must exist.</td>
		</tr>
		<tr>
			<td>Status:</td>
			<td><input type="text" name="newStatus" value="<% if (request.getParameter("newStatus") != null)
				out.print(request.getParameter("newStatus"));%>"
				onclick="this.select()"></td>
			<td class="expl">See below for status explanations.</td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input type="hidden" name="action"
				value="createProduktBatch"><input type="submit"
				value="Create"></td>
			<td></td>
		</tr>
	</table>
</form>
<p>
	Explanation of different statusnumbers:
	
<ol start="0">
	<li>produktbatch not started yet.</li>
<li>produktbatch started.</li>
<li>produktbatch finished.</li>
</ol>
</p>
<%@include file="footer.jsp"%>