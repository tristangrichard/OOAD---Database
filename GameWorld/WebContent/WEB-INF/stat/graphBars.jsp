<%@ page import="java.util.ArrayList"%>
<html>
	<!--  Purely test data meant for understanding  ------------------------------------------------------------------------>
	<%
		//int[] names = new int[20];
		//	int[] array = new int[20];
		//	for (int b = 0; b < array.length; b++) {
		//array[b] = (int) (Math.random() * 200) + 1;
		//names[b] = (int) (Math.random() * 200) + 1;
		//	}
		ArrayList<Integer> array = new ArrayList<Integer>();
		int a = (Integer) request.getAttribute("array");
		array.add(a);
		ArrayList<String> names = new ArrayList<String>();
		String b = (String) request.getAttribute("names");
		names.add(b);
	%>
 
<div class="container">
<div class="row">
	<div class="span13" style="height:250px;">
	<!---------------------------------------------------------------------------------------------------------------------->
	<!-- This section sets the values of Y axis dynamically, based on largest value of data -------------------------------->
	<% 
	int max = 0;
	for(int c = 0; c < array.size() ; c++) {
		int current = (Integer)array.get(c);
		if(current>=max){max=current;}
	}
	 %>
		<div class="values" style="margin-left:<%=400-(array.size()*20.2) %>px;">
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
	<div class="graph" style="width:<%=array.size()*40.7%>px;">
	<!---------------------------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------------------------->
	<!--  This plots the bars on the graph  -------------------------------------------------------------------------------->	
			<div class="barspace"></div>
			<%for (int i = 0; i < array.size(); i++) {	%>
			<div style="height: <%if(max != 0){ out.print(200*((Integer)array.get(i))/max); }%>px;" class="bar"></div>
			<%}%>
	<!---------------------------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------------------------->
	<!--  This plots the data set values on the X-axis under the bars  ----------------------------------------------------->
		</div><br>
	<div class="space" style="margin-left:<%=400-(array.size()*20.2) %>px;">.</div>
	<%for (int i =0;i<names.size();i++){%>
	<div class="names"><%=names.get(i)%></div>
	<%}	%><br>
	</div> 
	</div> 
	</div> 
</html>