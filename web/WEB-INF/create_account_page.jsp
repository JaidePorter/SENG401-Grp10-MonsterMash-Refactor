<%-- 
    Document   : create_account_page
    Created on : 29-Nov-2012, 11:57:56
    Author     : sjk4
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Create an account - Monster Mash</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="description" content="" />
	<meta name="keywords" content="" />
	<link rel="stylesheet" href="css/style.css" type="text/css" media="screen" />
</head>
<body>
    <div class="box">
        <div class="login-header">
            Create new account
	</div>
        <form class="login" action="create-account" method="post">
            <p><b>E-Mail</b> <i>(5 to 255 char.)</i>:<input type="text" name="email" maxlength="255" /></p>
            <p><b>Username</b> <i>(5 to 255 char.)</i>:<input type="text" name="username" maxlength="255" /></p>
            <p><b>Monster name</b> <i>(5 to 32 char.)</i>:<input type="text" name="monster" maxlength="32" /></p>
            <p><b>Password</b> <i>(5 to 255 char.)</i>:<input type="password" name="password" maxlength="255" /></p>
            <p><b>Confirm Password</b> <i>(repeat)</i>:<input type="password" name="cpassword" maxlength="255" /></p>
            <c:choose>
                <c:when test="${not empty errorMessage}">
                        <p style="height:10px;line-height:10px;color:red;text-align:center;"><i>${errorMessage}</i></p> 
                </c:when>
                <c:otherwise>
                        <p style="height:10px;text-align:center;"></p> 
                </c:otherwise>
            </c:choose>
            <div>
                <a class="forgotten-password" href="login"><b>Already registered? Login.</b></a>
                <button type="submit" class="forgot-password">
                    <img src="images/create_account.png" width="125" height="32" alt="submit" />
                </button>
            </div>
	</form>
    </div>
</body>
</html>