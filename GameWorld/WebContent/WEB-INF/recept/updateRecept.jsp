<%@include file="header.jsp"%>
<%@ page import="dto.ReceptDTO"%>
<%@ page import="dto.ReceptKompDTO"%>
<%@ page import="dto.RaavareDTO"%>
<jsp:useBean id="receptToUpdate" class="dto.ReceptDTO" scope="request" />
<jsp:useBean id="receptKompList" class="java.util.ArrayList"
	scope="request" />
<jsp:useBean id="raavareList" class="java.util.ArrayList"
	scope="request" />
<h1>Update Recept</h1>
<form method="POST" action="index.jsp">
	<!-- This is for Updating -->
	<table>
		<tr>
			<td>ID:</td>
			<td><%=receptToUpdate.getReceptId()%></td>
		</tr>
		<tr>
			<td>Name:</td>
			<td><input type="text" name="updReceptName"
				value="<%if (request.getParameter("updReceptName") != null)
				out.print(request.getParameter("updReceptName"));
			else
				out.print(receptToUpdate.getReceptNavn());%>"
				onclick="this.select()"></td>
			<td class="expl">Needs to be between 2-20 characters.</td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input type="hidden" name="action"
				value="updateReceptFilled"> <input type="hidden"
				name="receptIDToUpdate" value="<%=receptToUpdate.getReceptId()%>"><input
				type="submit" value="Update Recept"></td>
			<td></td>
		</tr>
	</table>
</form>

<table class="list">
	<tr>
		<th>Råvare Name</th>
		<th>Råvare Deliverer</th>
		<th>NomNetto[kg]</th>
		<th>Tolerance[%]</th>
	</tr>
	<%
		for (int i = 0; i < receptKompList.size(); i++) {
			ReceptKompDTO rk = (ReceptKompDTO) receptKompList.get(i);
			RaavareDTO r = (RaavareDTO) raavareList.get(i);
	%>
	<tr <%if (i % 2 != 0)
					out.print(" class=\"alt\"");%>>
		<td><%=r.getRaavareNavn()%></td>
		<td><%=r.getLeverandoer()%></td>
		<td><%=rk.getNomNetto()%></td>
		<td><%=rk.getTolerance()%></td>
	</tr>
	<%
		}
	%>
</table>
<p>
	<a
		href="index.jsp?action=createReceptKomp&amp;receptIDToUpdate=<%=receptToUpdate.getReceptId()%>">Add
		New Recept Komp</a>
</p>
<%@include file="footer.jsp"%>