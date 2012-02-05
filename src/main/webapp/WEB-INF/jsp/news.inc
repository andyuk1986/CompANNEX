
                   <h3>News</h3>
                   <%
                   	List<News> newsList = (List<News>)pageContext.findAttribute("news");
                   	
                   	for (News news : newsList) {
                   		%>
                    			
                    	<div class="indent-bot">
                        	<time class="tdate-1" datetime="<%= DateUtil.getDateAsString(news.getDate()) %>"><strong><%= DateUtil.getDay(news.getDate()) %></strong><%= DateUtil.getMonth(news.getDate()) %></time>
                            <div class="extra-wrap">
                            	<h6><%=news.getTranslation().getHeader()%></h6>
                                <%=news.getTranslation().getText()%> 
                            </div>
                            <div class="clear"></div>
                        </div>		
                    			
                   		<%
                   	}
                   	
                   %>
                        