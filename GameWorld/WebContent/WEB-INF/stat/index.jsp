<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">
<html>
<head>
<meta http-equiv="Content-type" content="text/html;charset=UTF-8">
<title>Statistics</title>
<link rel="stylesheet" href="../css/style.css">

</head>

<body>
<div class="container">
		<h1 id = "headerh1">GameWorld</h1>
		<div id="messagecontainer">
			<div class="error">${error}</div>
			<div class="message">${message}</div>
		</div>
		<div class="row">
			<div class="span2">
				<a class="btn btn-primary1 btn-large" href="index.jsp?">Back</a>
				<a class="btn btn-primary1 btn-large" href="index.jsp?action=stat">Get Stat</a>
				<a class="btn btn-primary1 btn-large" href="../login/logout.jsp">Logout</a>
			</div>
			<div class="span9">
				<!-- Main content -->
				<h2>Statistics</h2>
			<p>From here you can generate statistics!</p>
			<a class="btn btn-large btn-primary" href="index.jsp?action=mostPopular">20 Most Popular games</a>
			</div>
		</div>
	</div>
<!--<%// if(graphBars.booleanValue()){ %><jsp:include page="graphBars.jsp"/><%//}%>
	<%// if(graphSevBars.booleanValue()){ %><jsp:include page="graphSevBars.jsp"/><%//}%>
	<%// if(graphSevLines.booleanValue()){ %><jsp:include page="graphSevLines.jsp"/><%//}%>
 -->	
	<div class="footer">Gruppe 8</div>
</body>
</html>
