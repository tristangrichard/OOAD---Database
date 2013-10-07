<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">
<html>
<head>
<meta http-equiv="Content-type" content="text/html;charset=UTF-8">
<title>GameWorld</title>
<link rel="stylesheet" href="css/style1.css">
	    <!-- Le styles -->
    <link href="../css/style1.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }

      .form-signin {
        max-width: 300px;
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }

    </style>
</head>
		<div id="messagecontainer">
			<jsp:useBean id="error" class="java.lang.String" scope="request" />
			<jsp:useBean id="message" class="java.lang.String" scope="request" />
			<div class="error">
				<%
					if (error != null)
						out.println(error);
				%>
			</div>
			<div class="message">
				<%
					if (message != null)
						out.println(message);
				%>
			</div>
		</div> 
  <body>
    <div class="container">
      <form class="form-signin" method="POST" action="j_security_check">
        <h2 class="form-signin-heading">GameWorld</h2>
				<br><a class="btn btn-large btn-success1"href="index.jsp">Home >></a><% if (request.getUserPrincipal() != null) {if (request.isUserInRole("administrator")) {%>			
				<br><a class="btn btn-large btn-success1"href="admin/index.jsp">User Administration >></a>
				<br><a class="btn btn-large btn-success1"href="genre/index.jsp">Genre Administration >></a> <% } if (request.isUserInRole("game") || request.isUserInRole("administrator")) { %>
				<br><a class="btn btn-large btn-success1"href="game/index.jsp">Game Administration >></a> <% } if (request.isUserInRole("user") || request.isUserInRole("administrator")) { %>
				<br><a class="btn btn-large btn-success1"href="mygames/index.jsp">My Games >></a><% }%>
				<br><a class="btn btn-large btn-success1"href="login/logout.jsp">Logout >></a> <% } else { %>
				<br><a class="btn btn-large btn-success1"href="login/index.jsp">Login >></a> <% } if (!request.isUserInRole("game") && !request.isUserInRole("administrator") && !request.isUserInRole("user")) { %>
				<br><a class="btn btn-large btn-success1"href="create/index.jsp?action=List">Create User >></a> <%} %>
				
		<br><p>Welcome to GameWorld.</p>
      </form>

   		 
    </div>
