<%
List thread = (List)pageContext.findAttribute("thread");

if (thread != null && !thread.isEmpty()) { %>
<div class="wrapper">
	
	<%
	 for (Object obj : thread) {
	 	if (obj instanceof Question) {
	 		Question question = (Question) obj;	 	
	 	 %>
                        	<div class="wrapper">
                        	<article class="grid_6 alpha">
                            	<div class="indent-right img-indent-bot">
                                    <div class="aligncenter text-2">
                                        <strong><%=question.getDate().toString() %></strong>
                                    </div>
                                    <blockquote>
                                        <div class="quote">
                                            <div class="padding">
                                                &quot;<%=question.getText() %>&quot;
                                            </div>
                                        </div>
                                    </blockquote>
                                </div>
                            </article>
                       	</div>
	 <%	}   
	    if (obj instanceof Answer) {
	 		Answer answer = (Answer) obj;	 	
	 	 %>
                        	<div class="wrapper">
                        	<article class="grid_6 alpha">
                            	<div class="indent-right img-indent-bot">
                                    <div class="aligncenter text-2">
                                        <strong><%=answer.getDate().toString() %></strong>
                                    </div>
                                    <blockquote>
                                        <div class="quote">
                                            <div class="padding">
                                                &quot;<%=answer.getText() %>&quot;
                                            </div>
                                        </div>
                                    </blockquote>
                                </div>
                            </article>
                       	</div>	 	
	 <%	}   
	 
	 }
	
	 %>
                                  	
</div>
<%
}
%>