<%-- 
    Document   : market_page
    Created on : 2013-01-29, 14:08:56
    Author     : 
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Monster Mash</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <link rel="stylesheet" href="css/jquery-ui.css" />
        <link rel="stylesheet" href="css/reveal.css" />
	<link rel="stylesheet" href="css/style.css" type="text/css" media="screen" />
	<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
	<script type="text/javascript" src="js/menu.js"></script>
	<script type="text/javascript" src="js/custom.js"></script>
        <script type="text/javascript" src="js/jquery-ui.js"></script>
        <script type="text/javascript" src="js/jquery.reveal.js"></script>
        <script type="text/javascript">
            $(function() {
                $( "#buylist" ).menu();
                $( "#selllist" ).menu();
                <c:if test="${not empty alertMessage}">
                    alert("${alertMessage}");
                </c:if> 
            });
        </script>
        <script type="text/javascript">
        function validate(evt) {
            var theEvent = evt || window.event;
            var key = theEvent.keyCode || theEvent.which;
            key = String.fromCharCode( key );
            var regex = /[0-9]/;
            if( !regex.test(key) ) {
                theEvent.returnValue = false;
                if(theEvent.preventDefault)
                    theEvent.preventDefault();
            }
        }
        function setFriendLinks(userID, serverID){
                    document.getElementById("fightRequestLink").href = "fight?user="+userID+"&server="+serverID;
                    document.getElementById("removeFriendLink").href = "main?removeFriend="+userID;
                    $('a[data-reveal-id]').live('click', function(e) {
                        e.preventDefault();
                        var modalLocation = $(this).attr('data-reveal-id');
                        $('#'+modalLocation).reveal($(this).data());
                    });
                }
                function setRequestLinks(requestID){
                    document.getElementById("acceptRequest").href = "main?acceptFriendRequest="+requestID;
                    document.getElementById("cancelRequest").href = "main?cancelFriendRequest="+requestID;
                    $('a[data-reveal-id]').live('click', function(e) {
                        e.preventDefault();
                        var modalLocation = $(this).attr('data-reveal-id');
                        $('#'+modalLocation).reveal($(this).data());
                    });
                }
                function setFightLinks(requestID){
                    document.getElementById("acceptRequest").href = "fight/accept?fightID="+requestID;
                    document.getElementById("cancelRequest").href = "fight/reject?fightID="+requestID;
                    $('a[data-reveal-id]').live('click', function(e) {
                        e.preventDefault();
                        var modalLocation = $(this).attr('data-reveal-id');
                        $('#'+modalLocation).reveal($(this).data());
                    });
                }
                function showMonsterStats(monsterName, saleOffer, breedOffer, monsterStrength, monsterDefence, monsterHealth){
                    document.getElementById("monster-name").innerHTML = monsterName;
                    var status = "<span style=\"color:green;\">ready to fight</span>";
                    if(saleOffer > 0){
                        status = "<span style=\"color:orange;\">for sale</span>";
                    }else if(breedOffer > 0){
                        status = "<span style=\"color:blue;\">for breeding</span>";
                    }
                    document.getElementById("monster-status").innerHTML = "Status: "+status;
                    document.getElementById("monster-strength").style.width = Math.round(monsterStrength*100)+"%";
                    document.getElementById("monster-defence").style.width = Math.round(monsterDefence*100)+"%";
                    document.getElementById("monster-health").style.width = Math.round(monsterHealth*100)+"%";
                    $('a[data-reveal-id]').live('click', function(e) {
                        e.preventDefault();
                        var modalLocation = $(this).attr('data-reveal-id');
                        $('#'+modalLocation).reveal($(this).data());
                    });
                }
	</script>
</head>
<body>
    <div id="friendLinks" class="reveal-modal">
        <a id="fightRequestLink" href=""><img src="images/request_fight.png" width="125" height="32" alt="Send Fight Reqest" /></a>
        <a id="removeFriendLink" href=""><img src="images/remove_friend.png" width="125" height="32" alt="Remove Friend" /></a>
    </div>
    <div id="requestLinks" class="reveal-modal">
        <a id="acceptRequest" href=""><img src="images/accept_request.png" width="125" height="32" alt="Accept request" /></a>
        <a id="cancelRequest" href=""><img src="images/reject_request.png" width="125" height="32" alt="Reject request" /></a>
    </div>
    <div class="reveal-modal" id="monsterStats" >
        <h1 id="monster-name">Monster Name</h1>
        <hr>
        <h3 id="monster-status">Status:</h3> 
        <hr>
        <h2>Strength:</h2>
        <div class="meter red nostripes">
            <span id="monster-strength" style="width: 25%"></span>
        </div>
        <h2>Defence:</h2>
        <div class="meter nostripes">
            <span id="monster-defence" style="width: 75%"></span>
        </div>
        <h2>Health:</h2>
        <div class="meter orange nostripes">
            <span id="monster-health" style="width: 45%"></span>
        </div>		
     </div>
	<div id="friendslist">
		<ul class="list">
                    <c:forEach items="${requestList}" var="request">
                        ${request}
                    </c:forEach>
                    <c:forEach items="${friendList}" var="friend">
                        <li>
                            <a href="#" onclick="setFriendLinks('${friend.userID}', '${friend.serverID}')" data-reveal-id="friendLinks" >${friend.username}</a>
                        </li>
                    </c:forEach>
		</ul>
	</div>
	<div id="friend-request">
		<form action="main" method="post">
			<p>
                            <input id="friendRequestName" type="text" onblur="if (this.value == '') {this.value = 'Send email...';}" onfocus="if(this.value == 'Send email...') {this.value = '';}" value="Send email..." name="email" maxlength="255" />
                            <br />
                            <button type="submit" class="formbutton">
                                <img src="images/add_friend.png" width="125" height="32" alt="submit" />
                            </button>
                        </p>
		</form>
	</div>
	<div id="monsterlist">
            <ul class="list">
                <c:forEach items="${monsterRequestList}" var="monsterRequest">
                    <li class="fight-request" style="background-color: #ff9d9d; border-top-left-radius: 5px;"><a href="#" onclick="setFightLinks('${monsterRequest.fightID}')" data-reveal-id="requestLinks">Fight with <span style="color:#5988ff">${monsterRequest.senderID}</span></a></li>
                </c:forEach>
                <c:forEach items="${monsterList}" var="monster">
                    <li><a href="#" onclick="showMonsterStats('${monster.name}', ${monster.saleOffer}, ${monster.breedOffer}, ${monster.currentStrength}, ${monster.currentDefence}, ${monster.currentHealth})" data-reveal-id="monsterStats">${monster.name}</a></li>
                </c:forEach>
            </ul>
        </div>
	<ul class="menu">
		<li id="home"><a href="main">Home</a></li>
		<li id="market"><a href="market">Market</a></li>
		<li id="mating"><a href="mating">Mating</a></li>
		<li id="highscores"><a href="highscores">Highscores</a></li>
		<li id="logout"><a href="logout">Logout</a></li>
	</ul>
        <div class="content">
            Logged in as: ${user.username} (${user.userID}) | Money: $${user.money} | <a href="unregister">Unregister</a>
            <hr />
            <form action="" method="post">
                <h1><span style="font-size: 25px;">B</span>UY <span style="font-size: 25px;">F</span>RIEND'S <span style="font-size: 25px;">M</span>ONSTERS:</h1>
                <ul id="buylist">
                    <c:if test="${empty monstersForSale}">
                        <li style="text-align:center;">No offers at the moment.</li>
                    </c:if>
                    <c:forEach items="${monstersForSale}" var="monster">
                        ${monster}
                    </c:forEach>
                </ul>
                <hr />
                <h1><span style="font-size: 25px;">Y</span>OUR <span style="font-size: 25px;">M</span>ONSTERS <span style="font-size: 25px;">F</span>OR <span style="font-size: 25px;">S</span>ALE :</h1>
                <ul id="selllist">
                    <c:set var="anyOffer" value="0" />
                    <c:forEach items="${monsterList}" var="monster">
                        <c:if test="${monster.saleOffer != 0}" >
                            <c:set var="anyOffer" value="1" />
                            <li><a href="market?cancelOffer=${monster.id}" title="Cancel your offer"><b>Name:</b> ${monster.name} | <b>Price:</b> ${monster.saleOffer}$</a></li>
                        </c:if>
                    </c:forEach>
                    <c:if test="${anyOffer == 0}">
                        <li style="text-align:center;">You are not selling any monsters at the moment.</li>
                    </c:if>
                </ul><br />
                <hr />
                <h1><span style="font-size: 25px;">S</span>ELL <span style="font-size: 25px;">Y</span>OUR <span style="font-size: 25px;">M</span>ONSTER:</h1>
                <form action="market" method="POST">
                    <c:set var="anyAvailableMonsters" value="0" />
                        <c:forEach items="${monsterList}" var="monster">
                            <c:if test="${(monster.saleOffer == 0) && (monster.breedOffer == 0)}" >
                                <c:set var="anyAvailableMonsters" value="1" />
                            </c:if>
                        </c:forEach>
                    <c:if test="${anyAvailableMonsters == 1}">
                    <p style="text-align:center">Choose monster:</p>
                    <p style="text-align:center">  
                        <select name="monsterID">
                            <c:forEach items="${monsterList}" var="monster">
                                <c:if test="${(monster.saleOffer == 0) && (monster.breedOffer == 0)}" >
                                    <option value="${monster.id}">${monster.name}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </p>
                    <p style="text-align:center">Your offer: <input type="text" name="offerAmount" size="1" onkeypress="validate(event)" />$</p>
                    <p style="text-align:center">
                        <button type="submit" class="formbutton">
                            <img src="images/add_to_market.png" width="125" height="32" alt="submit" />
                        </button>
                    </p>
                    </c:if>
                    <c:if test="${anyAvailableMonsters == 0}">
                        <p style="font-weight:bold;color:red;font-style:italic;text-align:center">You have no monsters.</p>
                    </c:if>
                </form>
            </form>
        </div>
    </body>
</html>
