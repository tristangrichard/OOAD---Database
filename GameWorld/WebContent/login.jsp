<!--<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<html lang="en"><head>
    <meta charset="utf-8">
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
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
	</div>
    <link href="../css/style1.css" rel="stylesheet">
  <body>

    <div class="container">

      <form class="form-signin" method="POST" action="j_security_check">
        <h2 class="form-signin-heading">Please login</h2>
        <input type="text" class="input-block-level" name="j_username" placeholder="Email address" onclick="this.select()">
        <input type="password" class="input-block-level" name="j_password" placeholder="Password" onclick="this.select()">
        <input class="btn btn-large btn-success" type="submit"
							name="handling" value="Login >>"><br>
				<br><p>
				The following test users are created in the system, the password for
				all of them are "<span id="password">1234</span>". This password
				is entered as default so just enter the applicable email and click
				Login.
			</p>
			<p>Admin: m.b@mail.dk</p>
			<p>Game: info@ea.dk</p>
			<p>User: ST92@dtu.dk</p>
      </form>

   		 
    </div>
<div class="footer">@Gruppe 8</div>
</body></html>