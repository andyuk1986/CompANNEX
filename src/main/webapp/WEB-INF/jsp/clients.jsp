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
<body id="page4">
	<!--==============================header=================================-->
    <header>
    	<%@ include file="/WEB-INF/jsp/menu.inc" %>
        <div class="slider-wrapper">
        	<div class="slider">
            	<div class="banner">
                    <strong>LET US <strong>COOPERATE!</strong></strong>
                    <em>Let’s create your company’s growth strategy together!</em>
                    <span class="button">
                    	<a href="newpartner.do"><strong>Become a Partner</strong></a>
                    </span>
                </div>
            </div>
        </div>
    </header>
    
	<!--==============================content================================-->
    <section id="content"><div class="ic"><div class="inner_copy">All <a href="http://www.magentothemesworld.com" title="Best Magento Templates">premium Magento themes</a> at magentothemesworld.com!</div></div>
        <div class="main">
            <div class="container_12">
                <div class="wrapper">
                	<article class="grid_3">
                    	<h3 class="p0">Conditions</h3>
                        <ul class="list-2">
                        	<li><a href="#">At vero eos ccusamus</a></li>
                            <li><a href="#">Odio digsis ducimus</a></li>
                            <li><a href="#">Blanditiis aesentiutatum</a></li>
                            <li><a href="#">Deleniti atque corrupti</a></li>
                            <li><a href="#">Rolores et qs molestias</a></li>
                            <li><a href="#">Sint occaecatiupiditate</a></li>
                            <li><a href="#">Provident silique</a></li>
                            <li><a href="#">Qui eseruntollitia animi</a></li>
                            <li><a href="#">Laborum etolorum</a></li>
                            <li><a href="#">Et harum quim</a></li>
                            <li><a href="#">Est et expeditdistinctio</a></li>
                            <li><a href="#">Nam libero tpore</a></li>
                            <li><a href="#">Cum soluta nis est</a></li>
                            <li><a href="#">Eligendi optio mque nihil</a></li> 
                        </ul>
                    </article>
                    <article class="grid_9">
                    	<h3>Our Clients</h3>
                    	
                    <%
                   	List<Company> clients = (List<Company>)pageContext.findAttribute("clients");
                   	int i = 1;
                   	boolean isFirst = false;
                   	boolean isLast = false;	
                   	for (Company company : clients) {
                   		
                   		isFirst = (i % 3 == 1);
                   		isLast = (i % 3 == 0);
                   		
                   		String articleclass = "grid_3";
                   		if (isFirst) articleclass += " alpha";
                   		if (isLast) articleclass += " omega";
                   		
                   		if (isFirst) { %>
                   		<div class="wrapper indent-bot">
                   	<%	}
                   	%>
                        	<article class="<%=articleclass %>">
                            	<figure class="p2"><a href="#"><img class="img-border" src="<%=company.getLogo() %>" alt=""></a></figure>
                                <strong><%=company.getTranslation().getName() %></strong>
                                <p class="p0"><%=company.getTranslation().getSlogan() %></p>
                                <a class="link" href="#">Learn More</a>
                            </article>
                            
                   <%   if (isLast || i == clients.size()) { %>         
                        </div>
                   <%	}
                   i++;
                   }
                   %>
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
