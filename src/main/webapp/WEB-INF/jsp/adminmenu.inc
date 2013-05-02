		<div class="main">
        	<div class="prev-indent-bot2">
                <h1><a href="adminhome.do">CompANNEX</a></h1>
                <nav>
                    <ul class="menu">
                        <li><a class='<%= StringUtil.equals((String)pageContext.findAttribute("activeTab"), "home") ? "active" : "" %>' href="adminhome.do">home</a></li>
                        <li><a class='<%= StringUtil.equals((String)pageContext.findAttribute("activeTab"), "contacts") ? "active" : "" %>' href="adminquestions.do">question</a></li>
                    </ul>
                </nav>
                <div class="clear"></div>
            </div>
        </div>