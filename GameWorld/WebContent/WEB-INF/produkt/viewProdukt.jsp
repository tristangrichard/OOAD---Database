<%@include file="header.jsp"%>
<%@ page import="dto.ProduktBatchLinjeDTO"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.NumberFormat"%>
<%@ page import="java.util.TimeZone" %>
<jsp:useBean id="pblList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="status" class="java.lang.String" scope="request" />
<jsp:useBean id="pbToView" class="dto.ProduktBatchDTO" scope="request" />
<h1>View Produkt</h1>
<%
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	DateFormat dateFormatLong = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	dateFormatLong.setTimeZone(TimeZone.getTimeZone("CET"));
	Date now = new Date();
	NumberFormat nf1 = NumberFormat.getInstance();
	nf1.setMaximumFractionDigits(3);
	nf1.setMinimumFractionDigits(3);
%>
<form method="POST" action="index.jsp">
	<table>
		<tr>
			<td>Udskrevet</td>
			<td><%=dateFormat.format(now)%></td>
		</tr>
		<tr>
			<td>Produkt Batch nr.</td>
			<td><%=pbToView.getPbId()%></td>
		</tr>
		<tr>
			<td>Recept nr.</td>
			<td><%=pbToView.getReceptId()%></td>
		</tr>
	</table>
	<table class="produkt">
		<%
			double tara = 0;
			double netto = 0;
			for (int i = 0; i < pblList.size(); i++) {
				ProduktBatchLinjeDTO pbl = (ProduktBatchLinjeDTO) pblList
						.get(i);
				tara = tara + pbl.getTara();
				netto = netto + pbl.getNetto();
		%>
		<tr class="raa topr">
			<td>Råvare nr.</td>
			<td><%=pbl.getRaavareid()%></td>
			<td colspan="6"></td>
		</tr>
		<tr class="raa">
			<td>Råvare navn:</td>
			<td><%=pbl.getRaavarenavn()%></td>
			<td colspan="6"></td>
		</tr>
		<tr class="baa topr">
			<td>Del</td>
			<td>Mængde</td>
			<td>Tolerance</td>
			<td>Tara</td>
			<td>Netto (Kg)</td>
			<td>Batch ID</td>
			<td>Operator</td>
			<td>Terminal</td>
		</tr>
		<tr class="baa">
			<td>???</td>
			<!-- Mangler i databasen -->
			<td><%= nf1.format(pbl.getMaengde()) %></td>
			<td>&plusmn;<%=pbl.getTolerance()%> %
			</td>
			<td>
				<%
					if (pbl.getTara() != 0)
							out.print(nf1.format(pbl.getTara()));
				%>
			</td>
			<td>
				<%
					if (pbl.getNetto() != 0)
							out.print(nf1.format(pbl.getNetto()));
				%>
			</td>
			<td>
				<%
					if (pbl.getRaavarebatch() != 0)
							out.print(pbl.getRaavarebatch());
				%>
			</td>
			<td>
				<%
					if (pbl.getOpr() != null)
							out.print(pbl.getOpr());
				%>
			</td>
			<td>1</td>
			<!-- Mangler i databasen -->
		</tr>
		<tr class="midr">
			<td colspan="8"></td>
		</tr>
		<%
			}
		%>
		<tr class="topr">
			<td>Sum Tara:</td>
			<td colspan="2"></td>
			<td><%=nf1.format(tara)%></td>
			<td colspan="4"></td>
		</tr>
		<tr>
			<td>Sum Netto:</td>
			<td colspan="3"></td>
			<td><%=nf1.format(netto)%></td>
			<td colspan="3"></td>
		</tr>
	</table>
	<table>
		<tr>
			<td>Produktion Status:</td>
			<td><%=status%></td>
		</tr>
		<tr>
			<td>Produktion Startet:</td>
			<td><% if (pbToView.getStart().getTime() != 0)  out.print(dateFormatLong.format(pbToView.getStart())); %></td>
		</tr>
		<tr>
			<td>Produktion Slut:</td>
			<td><% if (pbToView.getSlut().getTime() != 0) out.print(dateFormatLong.format(pbToView.getSlut())); %></td>
		</tr>
	</table>
</form>
<%@include file="footer.jsp"%>