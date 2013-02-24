<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/imports.inc" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title></title>
    <meta charset="utf-8">
    <%@ include file="/WEB-INF/jsp/includes/jsimports.inc" %>
	<!--[if lt IE 7]>
    <div style=' clear: both; text-align:center; position: relative;'>
        <a href="http://windows.microsoft.com/en-US/internet-explorer/products/ie/home?ocid=ie6_countdown_bannercode">
        	<img src="http://storage.ie6countdown.com/assets/100/images/banners/warning_bar_0000_us.jpg" border="0" height="42" width="820" alt="You are using an outdated browser. For a faster, safer browsing experience, upgrade for free today." />
        </a>
    </div>
	<![endif]-->
    <!--[if lt IE 9]>
   		<script type="text/javascript" src="js/html5.js"></script>
        <link rel="stylesheet" href="css/ie.css" type="text/css" media="screen">
	<![endif]-->
</head>
<%
List<Country> countries = (List<Country>)pageContext.findAttribute("countries");
%>
<body id="page4" onload="loadCategories()">
	<!--==============================header=================================-->
    <header>
    	<%@ include file="/WEB-INF/jsp/menu.inc" %>
        <div class="slider-wrapper">
        	<div class="slider">
            	<div class="banner">
                    <strong>LET US <strong>COOPERATE!</strong></strong>
                    <em>Let’s create your company’s growth strategy together!</em>
                </div>
            </div>
        </div>
    </header>
    
	<!--==============================content================================-->
    <section id="content"><div class="ic"><div class="inner_copy">All <a href="http://www.magentothemesworld.com" title="Best Magento Templates">premium Magento themes</a> at magentothemesworld.com!</div></div>
        <div class="main">
            <div class="container_12">
                <div class="wrapper">
                	<article class="grid_9">
                    	<h3>Reset Password</h3>
                    	<form:form method="Post" cssClass="forma" action="resetpassword.do" commandName="resetPassword">
                            <fieldset>
                            	<label><span class="text-form required">New Password:</span><form:password path="newpassword"/>
                                	<span class="errors"><form:errors path="newpassword"/></span>
                                </label>
                                <label><span class="text-form required">Confirm New Password:</span><form:password path="renewpassword"/>
                                	<span class="errors"><form:errors path="renewpassword"/></span>
                                </label>                                
                                <form:hidden path="sessionID"/>
                                <form:hidden path="email"/>
                                <form:hidden path="token"/>
                                <form:hidden path="id"/>
                                <div class="wrapper">                                    
                                        <div class="clear"></div>
                                        <div class="buttons">
                                        	<span class="button-2">
                                                <a onClick="document.getElementById('resetPassword').reset()"><strong>clear</strong></a>
                                            </span>
                                            <span class="button-2">
                                                <a onClick="document.getElementById('resetPassword').submit()"><strong>reset</strong></a>
                                            </span>
                                        </div> 
                                    </div>
                                  </div>            
                            </fieldset>						
                        </form:form>
                    </article>
                </div>
            </div>
        </div>
    </section>
    
	<!--==============================footer=================================-->
	<%@ include file="/WEB-INF/jsp/footer.inc" %>    
	<script type="text/javascript"> Cufon.now(); </script>
</body>
</html>
