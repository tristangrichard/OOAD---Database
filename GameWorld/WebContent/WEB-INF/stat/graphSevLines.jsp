<!-- **
 * 
 * @author Mikkel Barfred, Tristan Richard
 *
 * -->
<html>
	<!--  Purely test data meant for understanding  ------------------------------------------------------------------------>
	<%
		int[] names = new int[20];
		int[] blue=new int[20]; int[] red=new int[20]; int[] green=new int[20]; int[] orange=new int[20];
			for (int b = 0; b < blue.length; b++) {
		blue[b] = (int) (Math.random() * 200) + 1;
		red[b] = (int) (Math.random() * 200) + 1;
		green[b] = (int) (Math.random() * 200) + 1;
		orange[b] = (int) (Math.random() * 200) + 1;
		names[b] = (int) (Math.random() * 200) + 1;
			}
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
		int cblue = blue[c];
		int cred = red[c];
		int cgreen = green[c];
		int corange = orange[c];
		if(cblue>=max){max=cblue;}
		if(cred>=max){max=cred;}
		if(cgreen>=max){max=cgreen;}
		if(corange>=max){max=corange;}
	}
	 %>
		<div class="values" style="margin-left:<%=400-(blue.length*20)%>px;">
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
	<svg class="svgChart"style="width:<%=(blue.length*40.7)%>;">
	<!---------------------------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------------------------->
	<!--  This plots the lines on the graph  -------------------------------------------------------------------->
		<% int blueoldX = 0; int blueoldY = 0;
		for (int line = 0 ; line < blue.length ; line++){	int newX; if(line==0){ newX=20;}else{newX=blueoldX+41;};int newY=blue[line];%>
		<line x1="<%=blueoldX%>" y1="<%=200-200*blueoldY/max%>" x2="<%=newX%>" y2="<%=200-200*newY/max%>" style="stroke:blue;stroke-width:2px"/>
		<%blueoldX=newX;blueoldY=newY;}%>
		<line x1="<%=blueoldX%>" y1="<%=200-200*blueoldY/max%>" x2="<%=814%>" y2="<%=200%>" style="stroke:blue;stroke-width:2px"/>
		
		<% int redoldX = 0; int redoldY = 0;
		for (int line = 0 ; line < red.length ; line++){	int newX; if(line==0){ newX=20;}else{newX=redoldX+41;};int newY=red[line];%>
		<line x1="<%=redoldX%>" y1="<%=200-200*redoldY/max%>" x2="<%=newX%>" y2="<%=200-200*newY/max%>" style="stroke:red;stroke-width:2px"/>
		<%redoldX=newX;redoldY=newY;}%>
		<line x1="<%=redoldX%>" y1="<%=200-200*redoldY/max%>" x2="<%=814%>" y2="<%=200%>" style="stroke:red;stroke-width:2px"/>
		
		<% int greenoldX = 0; int greenoldY = 0;
		for (int line = 0 ; line < green.length ; line++){	int newX; if(line==0){ newX=20;}else{newX=greenoldX+41;};int newY=green[line];%>
		<line x1="<%=greenoldX%>" y1="<%=200-200*greenoldY/max%>" x2="<%=newX%>" y2="<%=200-200*newY/max%>" style="stroke:green;stroke-width:2px"/>
		<%greenoldX=newX;greenoldY=newY;}%>
		<line x1="<%=greenoldX%>" y1="<%=200-200*greenoldY/max%>" x2="<%=814%>" y2="<%=200%>" style="stroke:green;stroke-width:2px"/>
		
		<% int orangeoldX = 0; int orangeoldY = 0;
		for (int line = 0 ; line < orange.length ; line++){	int newX; if(line==0){ newX=20;}else{newX=orangeoldX+41;};int newY=orange[line];%>
		<line x1="<%=orangeoldX%>" y1="<%=200-200*orangeoldY/max%>" x2="<%=newX%>" y2="<%=200-200*newY/max%>" style="stroke:orange;stroke-width:2px"/>
		<%orangeoldX=newX;orangeoldY=newY;}%>
		<line x1="<%=orangeoldX%>" y1="<%=200-200*orangeoldY/max%>" x2="<%=814%>" y2="<%=200%>" style="stroke:orange;stroke-width:2px"/>
	</svg><br>
	<!---------------------------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------------------------->
	<!--  This plots the data set values on the X-axis under the bars  ----------------------------------------------------->	
	<div class="space" style="margin-left:<%=400-(blue.length*20.2) %>px;">.</div>
	<%for (int i =0;i<names.length;i++){%>
	<div class="names" style="width:<%=40%>px;"><%=names[i]%></div>
	<%}%><br>
	<!---------------------------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------------------------->
	<!--  This plots the names of the comparison data sets, under the bars  ------------------------------------------------>	
		<br> <%if(datapoint != null){ %><div class="space" style="margin-left:<%=400-(blue.length*20) %>px;">.</div>
		<%for ( int t = 0; t < datapoint.length; t++){
			
		String type = ""; switch(t){case 0: {type="primary";break;} case 1: {type="danger";break;}
			case 2: {type="success";break;} case 3 : {type="warning"; break;}}%>
			
	<div class="btn btn-small btn-<%=type%>" style="display:inline"> <%=datapoint[t]%></div>
	<% }} %> 	
	</div> 
	</div> 
	</div> 
</html>