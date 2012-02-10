<%
List<Industry> industries = (List<Industry>)pageContext.findAttribute("industries");



%>

<label><span class="text-form required">Industry:</span>
	<select name="industry" onchange="industryChanged(this);">
    	<option value="none">-- Select Industry --</option>
        
        <c:forEach var="industry" items="${industries}">
        	<option value="${industry.ID}">${industry.translation.name}</option>
        </c:forEach>
        
	</select>
</label>
<label><span class="text-form required">Category:</span>
	<select name="category" ID="cat_none">
    	<option value="">-- Select Category --</option>
	</select>    		
    		
	<c:forEach var="industry" items="${industries}">
    	<select name="category" class="hidden" ID="cat_${industry.ID}">
    		<option value="">-- Select Category --</option>
        	<c:forEach var="category" items="${industry.categories}">
        		<option value="${category.ID}">${category.translation.name}</option>
        	</c:forEach>
        </select>
	</c:forEach>

</label>



<script>

var oldInd = "none";

function industryChanged(industry) {

	var selectedCat = document.id('cat_' + industry.value);
	selectedCat.removeClass("hidden");
	
	var oldCat = document.id('cat_' + oldInd);
	oldCat.addClass("hidden");

	oldInd = industry.value;
}

</script>





