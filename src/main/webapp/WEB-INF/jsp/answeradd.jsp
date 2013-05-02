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
<body id="page5">
	<!--==============================header=================================-->
    <header>
    	<%@ include file="/WEB-INF/jsp/adminmenu.inc" %>
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
                    	
                    	                    	
                    	<form:form method="Post" cssClass="forma" action="answeradd.do" commandName="answer">
                            <fieldset>    
						<h3>Answer Form</h3>                            
                                  <div class="wrapper">
                                  	<form:hidden path="questionID"/>
                                  	<%@ include file="/WEB-INF/jsp/thread.inc" %>
                                    <div class="text-form required">Text:</div>
                                    <div class="extra-wrap">
                                        <textarea name="text"></textarea>
                                        <span class="errors"><form:errors path="text"/></span>
                                        <div class="clear"></div>
                                        <div class="buttons">
                                        	<span class="button-2">
                                                <a onClick="document.getElementById('answer').reset()"><strong>clear</strong></a>
                                            </span>
                                            <span class="button-2">
                                                <a onClick="document.getElementById('answer').submit()"><strong>send</strong></a>
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
