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
                    	<h3>Contact Form</h3>
                        <form id="contact-form" method="post" enctype="multipart/form-data">                    
                            <fieldset>
                                  <label><span class="text-form">Your Name:</span><input type="text"></label>
                                  <label><span class="text-form">Your Email:</span><input type="text"></label>                              
                                  <div class="wrapper">
                                    <div class="text-form">Your Message:</div>
                                    <div class="extra-wrap">
                                        <textarea></textarea>
                                        <div class="clear"></div>
                                        <div class="buttons">
                                        	<span class="button-2">
                                                <a onClick="document.getElementById('contact-form').reset()"><strong>clear</strong></a>
                                            </span>
                                            <span class="button-2">
                                                <a onClick="document.getElementById('contact-form').submit()"><strong>send</strong></a>
                                            </span>
                                        </div> 
                                    </div>
                                  </div>                            
                            </fieldset>						
                        </form>
                    </article>
                    <article class="grid_3">
                    	<h3>Our Clients</h3>
                        <div class="wrapper img-indent-bot">
                            <figure class="map-border">
                                <iframe width="216" height="180" src="http://maps.google.com/maps?f=q&amp;source=s_q&amp;hl=en&amp;geocode=&amp;q=Brooklyn,+New+York,+NY,+United+States&amp;aq=0&amp;sll=37.0625,-95.677068&amp;sspn=61.282355,146.513672&amp;ie=UTF8&amp;hq=&amp;hnear=Brooklyn,+Kings,+New+York&amp;ll=40.649974,-73.950005&amp;spn=0.01628,0.025663&amp;z=14&amp;iwloc=A&amp;output=embed"></iframe>
                            </figure>
                        </div>
                        <dl>
                            <dt class="p2">USA 8901 Marmora Rd, Glasgow</dt>
                            <dd><span>Freephone:</span>  +1 800 559 6580</dd>
                            <dd><span>Telephone:</span>  +1 800 603 6035</dd>
                            <dd><span>Fax:</span>  +1 800 889 9898</dd>
                            <dd><span>Email:</span> <a class="link" href="#">mail@demolink.org</a></dd>
                        </dl>
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