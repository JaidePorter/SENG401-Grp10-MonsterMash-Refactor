<%-- 
    Document   : login_page
    Created on : 29-Nov-2012, 11:53:45
    Author     : sjk4
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Monster Mash</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="description" content="" />
	<meta name="keywords" content="" />
	<link rel="stylesheet" href="css/style.css" type="text/css" media="screen" />
</head>
<body>
	<div id="login-box">
		<div id="login-left">
			<div class="login-header">
				Sign-in to your account
			</div>
			<form class="login" action="login" method="post">
				<p>Email:<input type="text" name="email" maxlength="255" /></p>
				<p style="padding:0;">Password:<input type="password" name="password" maxlength="255" /></p>
                                <c:choose>
                                    <c:when test="${not empty message}">
                                           <p style="height:10px;line-height:10px;color:green;text-align:center;">${message}</p> 
                                    </c:when>
                                    <c:when test="${not empty errorMessage}">
                                            <p style="height:10px;line-height:10px;color:red;text-align:center;">${errorMessage}</p> 
                                    </c:when>
                                    <c:otherwise>
                                            <p style="height:10px;text-align:center;"></p> 
                                    </c:otherwise>
                                </c:choose>                        
				<div style="margin-top:0px;">
					<button type="submit" class="forgot-password">
						<img src="images/sign_in.png" width="101" height="32" alt="submit" />
					</button>
				</div>
			</form>
		</div>
		<div id="login-right">
			<div class="login-header">
				Create an account
			</div>
                    <p style="text-align: justify;">Monster Mash is a fun educational game, in which you and your friends battle and breed
                    monsters while learning about evolution and genetics. 
                        A fun new world awaits!</p>
                        <p style="text-align: center;"><a href="create-account"><img style="border:none;" src="images/create_account.png" width="125" height="32" alt="submit" /></a></p>
		</div>
	</div>
</body>
</html>
