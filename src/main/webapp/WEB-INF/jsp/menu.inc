		
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
            </div>
        </div>