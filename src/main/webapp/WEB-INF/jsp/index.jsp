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
<body id="page1">
	<!--==============================header=================================-->
    <header>
    	<%@ include file="/WEB-INF/jsp/menu.inc" %>
        <div class="slider-wrapper">
        	<div class="slider">
            	<ul class="items">
                	<li>
                    	<img src="images/slider-img1.gif" alt="">
                        <div class="banner">
                        	<strong>need <strong>fresh ideas?</strong></strong>
                            <em>Let’s create your company’s growth strategy together!</em>
                            <span class="button">
                            	<a href="contacts.do"><strong>Ask a Question</strong></a>
                            </span>
                        </div>
                    </li>
                    <li>
                    	<img src="images/slider-img2.jpg" alt="">
                        <div class="banner">
                        	<strong>good <strong>solutions!</strong></strong>
                            <em>Make your company a world wide known name with us!</em>
                            <span class="button">
                            	<a href="index.jsp#"><strong>Read More</strong></a>
                            </span>
                        </div>
                    </li>
                    <li>
                    	<img src="images/slider-img3.jpg" alt="">
                        <div class="banner">
                        	<strong>LET US <strong>COOPERATE!</strong></strong>
                            <em>Let’s create your company’s growth strategy together!</em>
                            <span class="button">
                            	<a href="index.jsp#"><strong>Read More</strong></a>
                            </span>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </header>
    
	<!--==============================content================================-->
    <section id="content"><div class="ic"><div class="inner_copy">All <a href="http://www.magentothemesworld.com" title="Best Magento Templates">premium Magento themes</a> at magentothemesworld.com!</div></div>
        <div class="main">
            <div class="container_12">
                <div class="wrapper">
                	<article class="grid_6">
                    	<%@ include file="/WEB-INF/jsp/news.inc" %>
                    	<span class="button-2">
                            <a href="news.do"><strong>News Archive</strong></a>
                        </span>
                    </article>
                    <article class="grid_6">
                    	<div class="indent-top">
                            <div class="wrapper indent-bot2">
                                <figure class="img-indent"><img src="images/page1-img1.jpg" alt=""></figure>
                                <div class="extra-wrap">
                                    <h4>We Know <strong>What It Takes</strong><strong class="color-3">to be the <em>Leader!</em></strong></h4>
                                </div>
                            </div>
                            <p class="p1">CompANNEX is a consulting, marketing and researching company which is able to offer the most suitable and reliable business partners for your business.</p>
                            <p class="img-indent-bot">It will make your life easier, and your business more efficient and profitable by handling the communication and negotiations with partners on it’s own.</p>
                            <span class="button-2">
                                <a href="company.do"><strong>aBOUT cOMPANY</strong></a>
                            </span>
                        </div>                        
                    </article>
                </div>
            </div>
        </div>
    </section>
    
	<!--==============================footer=================================-->
    <%@ include file="/WEB-INF/jsp/footer.inc" %>
	<script type="text/javascript"> Cufon.now(); </script>
    <script type="text/javascript">
		$(window).load(function(){
			$('.slider')._TMS({
				duration:800,
				easing:'easeOutQuad',
				preset:'simpleFade',
				pagination:true,//'.pagination',true,'<ul></ul>'
				pagNums:false,
				slideshow:7000,
				banners:'fade',// fromLeft, fromRight, fromTop, fromBottom
				waitBannerAnimation:false
			})
		})
	</script>
</body>
</html>
	