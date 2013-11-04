	<!--  Purely test data meant for understanding  ------------------------------------------------------------------------>
	<%
		int[] red = {50,100,20};
		int[] green = {100,20,80};
		int[] orange ={5, 25, 70};
		String[] modifiedNames = {"samlign1", "samlign2", "samlign3"};
		String[] datapoint = {"blue", "red", "green", "orange"};
	%>
	
	
<div class="container">
<div class="row">
	<div class="span13" style="height:250px;">
	<!---------------------------------------------------------------------------------------------------------------------->
	<!-- This section sets the values of Y axis dynamically, based on largest value of data -------------------------------->
	<% 
	int max = 0;
	for(int c = 0; c < red.length ; c++) {
		int current = red[c]+green[c]+orange[c];
		if(current>=max){max=current;}
	}
	 %>
		<div class="values" style="margin-left:<%=400-(datapoint.length*modifiedNames.length)*20 %>px;">
			<div class="valueMax"><%=max%></div>
			<div class="value"style="height:25px"></div>
			<div class="value"><%=(max/4)*3%></div>
			<div class="value"style="height:25px"></div>
			<div class="value"><%=max/2%></div>
			<div class="value"style="height:25px"></div>
			<div class="value"><%=max/4%></div>
			<div class="value"style="height:25px"></div>
			<div class="valueMin">0</div>
		</div>
	<!---------------------------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------------------------->
	<!--  This defines the width of the graphing table  -------------------------------------------------------------------->
	<div class="graph" style="width:<%=(datapoint.length*modifiedNames.length)*42%>px;">
	<!---------------------------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------------------------->
	<!--  This plots the bars on the graph, also checking if we are comparing 2 or 3 sets of data  ------------------------->
		<div style="height: 200px" class="barspace"></div>
			<%for ( int i = 0; i < red.length; i++){%>
			<%if(orange!=null){ %><div style="height: <%=200*(red[i]+green[i]+orange[i])/max%>px;" class="bar0"></div><%}
				else{ %><div style="height: <%=200*(red[i]+green[i])/max%>px;" class="bar0"></div><%} %>
			<div style="height: <%=200*(red[i])/max%>px;" class="bar1"></div>
			<div style="height: <%=200*(green[i])/max%>px;" class="bar2"></div>
			<%if(orange!=null){ %><div style="height: <%=200*(orange[i])/max%>px;" class="bar3"></div><%} %>
			<div style="height: 200px" class="barspace"></div>
			<%}%>
		</div><br>
	<!---------------------------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------------------------->
	<!--  This plots the data set values on the X-axis under the bars  ----------------------------------------------------->	
	<div class="space" style="margin-left:<%=400-(datapoint.length*modifiedNames.length)*20%>px;">.</div>
	<%for (int i =0;i<modifiedNames.length;i++){%>
	<div class="names" style="width:<%=datapoint.length*38+10%>px;"><%=modifiedNames[i]%></div>
	<%}%><br>
	<!---------------------------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------------------------->
	<!--  This plots the names of the comparison data sets, under the bars  ------------------------------------------------>	
		<%if(datapoint != null){ %><div class="space" style="margin-left:<%=400-(datapoint.length*modifiedNames.length)*20%>px;">.</div>
		<%for ( int t = 0; t < datapoint.length; t++){
			
		String type = ""; switch(t){case 0: {type="primary";break;} case 1: {type="danger";break;}
			case 2: {type="success";break;} case 3 : {type="warning"; break;}}%>
			
	<div class="btn btn-small btn-<%=type%>" style="display:inline"> <%=datapoint[t]%></div>
	<% }} %>	
	</div> 
	</div> 
	</div> 
</html>