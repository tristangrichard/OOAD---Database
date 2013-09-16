<%@include file="header.jsp"%>
<%@ page import="dto.ReceptDTO"%>
<%@ page import="dto.RaavareDTO"%>
<jsp:useBean id="raavareList" class="java.util.ArrayList"
	scope="request" />
<jsp:useBean id="receptToUpdate" class="dto.ReceptDTO" scope="request" />
<h1>Råvare Liste</h1>
<form method="POST" action="index.jsp">
	<p>Select a råvare:</p>
	<table class="list">
		<tr>
			<th>Name</th>
			<th>Deliverer</th>
			<th></th>
		</tr>
		<%
			for (int i = 0; i < raavareList.size(); i++) {
				RaavareDTO r = (RaavareDTO) raavareList.get(i);
		%>
		<tr>
			<td><%=r.getRaavareNavn()%></td>
			<td><%=r.getLeverandoer()%></td>
			<td><input type="radio" name="newRaavareID"
				value="<%=r.getRaavareId()%>" onclick="this.select()"
				<%if (request.getParameter("newRaavareID") != null && request.getParameter("newRaavareID").equals(
						Integer.toString(r.getRaavareId())))
					out.print(" checked");%>></td>
		</tr>
		<%
			}
		%>
	</table>
	<p>Then fill out the following:</p>
	<table>
		<tr>
			<td>NomNetto</td>
			<td><input type="text" name="newNomNetto"
				value="<%if (request.getParameter("newNomNetto") != null)
				out.print(request.getParameter("newNomNetto"));%>"></td>
			<td class="expl">Number in the range: 0,05 - 20,0 kg</td>
		</tr>
		<tr>
			<td>Tolerance</td>
			<td><input type="text" name="newTolerance"
				value="<%if (request.getParameter("newTolerance") != null)
				out.print(request.getParameter("newTolerance"));%>"></td>
			<td class="expl">Number in the range: 0,1 - 10,0%</td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input type="hidden"
				name="receptIDToUpdate" value="<%=receptToUpdate.getReceptId()%>">
				<input type="hidden" name="action" value="createReceptKompFilled"><input
				type="submit" name="create" value="OK"></td>
			<td></td>
		</tr>
	</table>
</form>

<%@include file="footer.jsp"%>