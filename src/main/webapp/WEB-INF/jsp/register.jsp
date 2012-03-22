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
                    	<h3>Register Form</h3>
                    	<form:form method="Post" cssClass="forma" action="register.do" commandName="registration" enctype="multipart/form-data">
                            <fieldset>
                                  <label><span class="text-form required">Company Name:</span><form:input path="name"/>
                                  <span class="errors"><form:errors path="name"/></span>
                                  </label>
                                  <label><span class="text-form required">Email Address:</span><form:input path="email"/>
                                  <span class="errors"><form:errors path="email"/></span>
                                  </label>
                                  <label><span class="text-form required">Password:</span><form:password path="password"/>
                                  <span class="errors"><form:errors path="password"/></span>
                                  </label>
                                  <label><span class="text-form required">Re Password:</span><form:password path="repassword"/>
                                  <span class="errors"><form:errors path="repassword"/></span>
                                  </label>
                                  <%@ include file="/WEB-INF/jsp/includes/industry.inc" %>
                                  <label><span class="text-form">Web Site URL:</span><form:input path="websiteurl"/></label>
                                  <label><span class="text-form">Telephone:</span><form:input path="telephone"/></label>
                                  <label><span class="text-form">Fax:</span><form:input path="fax"/></label>
                                  <label><span class="text-form">Contact Person Name:</span><form:input path="contactperson"/></label>
                                  <label><span class="text-form required">Address:</span><form:input path="address"/>
                                  <span class="errors"><form:errors path="address"/></span>
                                  </label>
                                  <label><span class="text-form required">Country:</span>
    	                            <select name="country" class="fleft">
								    	<option value="none">-- Select Country --</option>
        								<c:forEach var="country" items="${countries}">
        									<option value="${country.ID}">${country.translation.name}</option>
        								</c:forEach>
									</select>
                                      <span class="errors"><form:errors path="country"/></span>
								  </label>
                                  <label><span class="text-form">Slogan:</span><form:input path="slogan"/></label>
                                  <label><span class="text-form">Employee Count:</span><form:input path="employeecount"/></label>
                                  <label><span class="text-form">Logo:</span><input type="file" name="logo"/>
                                  <span class="errors"><form:errors path="logo"/><%=StringUtil.getString(pageContext.findAttribute("logo")) %></span>
                                  </label>
                                  <div class="wrapper">
                                    <div class="text-form">Description:</div>
                                    <div class="extra-wrap">
                                        <textarea name="description"></textarea>
                                        <div class="clear"></div>
                                        <div class="buttons">
                                        	<span class="button-2">
                                                <a onClick="document.getElementById('registration').reset()"><strong>clear</strong></a>
                                            </span>
                                            <span class="button-2">
                                                <a onClick="document.getElementById('registration').submit()"><strong>register</strong></a>
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
