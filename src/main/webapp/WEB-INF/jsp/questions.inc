                    <h3 class="prev-indent-bot2">Questions</h3>
                        
                    <%
                   	List<Question> questions = (List<Question>)pageContext.findAttribute("questions");

                   	for (Question question : questions) {             %>      	
                        	<div class="wrapper">
                        	<article class="grid_6 alpha">
                            	<div class="indent-right img-indent-bot">
                                    <div class="aligncenter text-2">
                                        <strong><%=question.getSubject() %></strong>
                                        <strong><%=question.getDate().toString() %></strong>
                                        <a href="answernew.do?questionID=<%=question.getID() %>">answer</a>
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
                    <%
                   	}
                   	%>