<%@ page import="java.util.ArrayList"%>
<html>
	<!--  Purely test data meant for understanding  ------------------------------------------------------------------------>
	<%
		String[] names = (String[])request.getAttribute("names");
		int[] array = (int[])request.getAttribute("array");
	%>
 
<div class="container">
<div class="row">
	<div class="span13" style="height:250px;">
	<!---------------------------------------------------------------------------------------------------------------------->
	<!-- This section sets the values of Y axis dynamically, based on largest value of data -------------------------------->
	<% 
	int max = 0;
	for(int c = 0; c < array.length ; c++) {
		int current = (Integer)array[c];
		if(current>=max){max=current;}
	}
	 %>
		<div class="values" style="margin-left:<%=400-(array.length*42) %>px;">
			<div class="valueMax"><%if (max > 0){out.print(max);} else{ out.print("1"); }%></div>
			<div class="value"style="height:25px"></div>
			<div class="value"><%if (max < 10){} else{ out.print((max/4)*3); }%></div>
			<div class="value"style="height:25px"></div>
			<div class="value"><%if (max < 10){} else{ out.print(max/2); }%></div>
			<div class="value"style="height:25px"></div>
			<div class="value"><%if (max < 10){} else{ out.print(max/4); }%></div>
			<div class="value"style="height:25px"></div>
			<div class="valueMin">0</div>
		</div>	
	<!---------------------------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------------------------->
	<!--  This defines the width of the graphing table  -------------------------------------------------------------------->
	<div class="graph" style="width:<%=array.length*84%>px;">
	<!---------------------------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------------------------->
	<!--  This plots the bars on the graph  -------------------------------------------------------------------------------->	
			<%for (int i = 0; i < array.length; i++) {	%>
			<div style="height: <%if(max != 0){ out.print(200*(array[i])/max); }%>px;" class="bar"></div>
			<%}%>
	<!---------------------------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------------------------->
	<!--  This plots the data set values on the X-axis under the bars  ----------------------------------------------------->
		</div><br>
	<div class="space" style="margin-left:<%=400-(array.length*42) %>px;">.</div>
	<%for (int i =0;i<names.length;i++){%>
	<div class="names"><%=names[i]%></div>
	<%}	%><br>
	</div> 
	</div> 
	</div> 
</html>