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
<body id="page2">
	<!--==============================header=================================-->
    <header>
    	<%@ include file="/WEB-INF/jsp/menu.inc" %>
        <div class="slider-wrapper">
        	<div class="slider">
            	<div class="banner">
                    <strong>need <strong>fresh ideas?</strong></strong>
                    <em>Let’s create your company’s growth strategy together!</em>
                </div>
            </div>
        </div>
    </header>
    
	<!--==============================content================================-->
    <section id="content"><div class="ic"><div class="inner_copy">All <a href="http://www.magentothemesworld.com" title="Best Magento Templates">premium Magento themes</a> at magentothemesworld.com!</div></div>
        <div class="main">
            <div class="container_12">
                <div class="wrapper p2">
                    <article class="grid_12">
                    	<h3>How We Work</h3>
                        <p class="indent-bot">CompANNEX is a team of creative, motivated and enthusiastic people who are eager to work and do their best for finding best solutions/partners for their clients’ business. Our business analysts  will continuously research the market and make business offers to companies to join to CompANNEX and register in the system.</p>
                        <div class="wrapper">
                        	<div class="col-1">
                            	<div class="wrapper">
                                	<strong class="circle">01</strong>
                                    You register your company in our system, provide all the necessary information about your business, your needs and your expectations. If you need some products for your business or you need clients for your products, we will find them for you.
                                </div>
                            </div>
                            <div class="col-1">
                            	<div class="wrapper">
                                	<strong class="circle">02</strong>
                                    Our business consultants will find possible partners for your business based on info provided by you, and will start discussions with companies which are most suitable to your business.   
                                </div>
                            </div>
                            <div class="col-3">
                            	<div class="indent-top2">
                                	<strong class="text-1">Your Business is Prospered and You Have New Partners </strong>
                                </div>
                            </div>
                        </div>
                    </article>
                </div>
                <div class="wrapper">
                	<article class="grid_12">
                    	<%@ include file="/WEB-INF/jsp/feedbacks.inc" %>
                		<span class="button-2">
                        	<a href="feedbacks.do"><strong>All Testimonials</strong></a>
                        </span>
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
