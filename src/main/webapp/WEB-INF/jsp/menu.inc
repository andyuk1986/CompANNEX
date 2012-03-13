		
		<div class="main">
        	<div class="prev-indent-bot2">
                <h1><a href="index.do">CompANNEX</a></h1>
                <nav>
                    <ul class="menu">
                        <li><a class='<%= StringUtil.equals((String)pageContext.findAttribute("activeTab"), "home") ? "active" : "" %>' href="home.do">home</a></li>
                        <li><a class='<%= StringUtil.equals((String)pageContext.findAttribute("activeTab"), "company") ? "active" : "" %>' href="company.do">company</a></li>
                        <li><a class='<%= StringUtil.equals((String)pageContext.findAttribute("activeTab"), "services") ? "active" : "" %>' href="services.do">services</a></li>
                        <li><a class='<%= StringUtil.equals((String)pageContext.findAttribute("activeTab"), "clients") ? "active" : "" %>' href="clients.do">clients</a></li>
                        <li><a class='<%= StringUtil.equals((String)pageContext.findAttribute("activeTab"), "contacts") ? "active" : "" %>' href="contacts.do">contacts</a></li>
                    </ul>
                </nav>
                <div class="clear"></div>
                <% 
                Company loginCompany = (Company)request.getSession().getAttribute("loginCompany"); 
                %>
                <c:choose>
					<c:when test="${loginCompany != null}">
					<div>
	                	<span>
	                		Welcome ${loginCompany.translation.name}! 
	                	</span>
	                </div>
					</c:when>
					<c:otherwise>
					<div class="menu-buttons">
                	<span class="button">
                    	<a href="loginnew.do"><strong>Login</strong></a>
                    </span>
                	<span class="button">
                    	<a href="registernew.do"><strong>Register</strong></a>
                    </span>
                	</div>
					</c:otherwise>
				</c:choose>
            </div>
        </div>