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
    </header>
    
	<!--==============================content================================-->
    <section id="content"><div class="ic"><div class="inner_copy">All <a href="http://www.magentothemesworld.com" title="Best Magento Templates">premium Magento themes</a> at magentothemesworld.com!</div></div>
        <div class="main">
            <div class="container_12">
                <div class="wrapper">
                <%
                Company company = (Company)pageContext.findAttribute("client");
                %>
					<article class="grid_6">
                    	<figure class="p2"><% if (!StringUtil.isBlank(company.getLogo())) { %><img class="img-border" src="<%=company.getLogo() %>" alt=""><% } %></figure>
                        <strong><%=company.getTranslation().getName() %></strong>
                        <p class="p0"><%=company.getTranslation().getSlogan() %></p>
                        <p class="p0"><%=company.getTranslation().getContacts() %></p>
                        <label><span class="text-form">Address: </span><span class="text-form"><%=company.getTranslation().getAddress() %></span></label>
						<% if (!StringUtil.isBlank(company.getZipCode())) { %>
                        	<label><span class="text-form">Zip Code: </span><span class="text-form"><%=company.getZipCode() %></span></label>
						<% } %>
						<% if (!StringUtil.isBlank(company.getTranslation().getCity())) { %>
                        	<p class="p0"><%=company.getTranslation().getCity() %></p>
						<% } %>
						<% if (!StringUtil.isBlank(company.getTranslation().getRegion())) { %>
                        	<p class="p0"><%=company.getTranslation().getRegion() %></p>
						<% } %>
                        <p class="p0"><%=company.getEmail() %></p>
                        <p class="p0"><%=company.getWebsite() %></p>
                        <p class="p0"><%=company.getTelephone() %></p>
                        <p class="p0"><%=company.getFax() %></p>
                        <p class="p0"><%=company.getEmployeeCount() %></p>
                        <p class="p0"><%=company.getTranslation().getDescription() %></p>
                        
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
