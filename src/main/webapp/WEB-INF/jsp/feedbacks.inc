                    <h3 class="prev-indent-bot2">Testimonials</h3>
                        
                    <%
                   	List<Feedback> feedbacks = (List<Feedback>)pageContext.findAttribute("feedbacks");
                   	int i = 1;
                   	boolean isLeft = false;
                   	for (Feedback feedback : feedbacks) {
                   		isLeft = (i % 2 == 1); 
                   	
                   		if (isLeft) { %>
                        	<div class="wrapper">
                        <% } %>
                        	<article class='<%=(isLeft ? "grid_6 alpha" : "grid_6 omega") %>'>
                            	<div class='<%=(isLeft ? "indent-right img-indent-bot" : "indent-left") %>'>
                                    <blockquote>
                                        <div class="quote">
                                            <div class="padding">
                                                &quot;<%=feedback.getText() %>&quot;
                                            </div>
                                        </div>
                                    </blockquote>
                                    <div class="aligncenter text-2">
                                        <strong><%=feedback.getPerson() %></strong>
                                        <p class="p0 color-1"><%=feedback.getPosition() %>&#44;&nbsp;<%=feedback.getCompany().getTranslation().getName() %></p>
                                    </div>
                                </div>
                            </article>
                            
                       <% if (!isLeft) { %>
                       	</div>
                       <% }
                    	i++;
                   	}
                   	%>