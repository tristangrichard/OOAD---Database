<%@include file="header.jsp"%>
<%@ page import="dto.ReceptDTO"%>
<%@ page import="dto.ReceptKompDTO"%>
<%@ page import="dto.RaavareDTO"%>
<jsp:useBean id="receptToView" class="dto.ReceptDTO" scope="request" />
<jsp:useBean id="receptKompList" class="java.util.ArrayList"
	scope="request" />
<jsp:useBean id="raavareList" class="java.util.ArrayList"
	scope="request" />
<h1>View Recept</h1>
<!-- This is for Updating -->
<table>
	<tr>
		<td>ID:</td>
		<td><%=receptToView.getReceptId()%></td>
	</tr>
	<tr>
		<td>Name:</td>
		<td><%=receptToView.getReceptNavn()%></td>
	</tr>
</table>

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
<%@include file="footer.jsp"%>