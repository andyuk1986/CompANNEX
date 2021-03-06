		
		<div class="main">
        	<div class="prev-indent-bot2">
                <h1><a href="index.do">CompANNEX</a></h1>
                                <% 
                Company loginCompany = (Company)request.getSession().getAttribute("loginCompany"); 
                %>
                <c:choose>
					<c:when test="${loginCompany != null}">
					<div class="menu-buttons">
	                	<span>
	                		Welcome ${loginCompany.translation.name}! 
	                	</span>
	                	<span class="button">
                    		<a href="logout.do"><strong>Logout</strong></a>
                    	</span>
	                	<span class="button">
                    		<a href="editcompanynew.do"><strong>Change Settings</strong></a>
                    	</span>
                    	<span class="button">
                    		<a href="changepasswordnew.do"><strong>Change Password</strong></a>
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
                <nav>
                    <ul class="menu">
                        <li><a class='<%= StringUtil.equals((String)pageContext.findAttribute("activeTab"), "home") ? "active" : "" %>' href="home.do">home</a></li>
                        <li><a class='<%= StringUtil.equals((String)pageContext.findAttribute("activeTab"), "company") ? "active" : "" %>' href="company.do">company</a></li>
                        <li><a class='<%= StringUtil.equals((String)pageContext.findAttribute("activeTab"), "services") ? "active" : "" %>' href="services.do">services</a></li>
                        <li><a class='<%= StringUtil.equals((String)pageContext.findAttribute("activeTab"), "clients") ? "active" : "" %>' href="clients.do">clients</a></li>
                        <li><a class='<%= StringUtil.equals((String)pageContext.findAttribute("activeTab"), "contacts") ? "active" : "" %>' href="contacts.do">question</a></li>
                    </ul>
                </nav>
                <div class="clear"></div>
            </div>
        </div>