<%
List<Industry > industries = (List<Industry>)pageContext . findAttribute("industries");
%>

<label><span class="text-form required">Industry:</span>
    <span class="fleft">
        <form:select path="industry" onchange="industryChanged(this);">
            <option value="none">-- Select Industry --</option>
             <c:forEach var="industry" items="${industries}">
             	<option value="${industry.ID}" selected="${f:areEquals(industry.ID, registration.industry)}">${industry.translation.name}</option>
             </c:forEach>
        </form:select>
    </span>
    <span class="errors"><form:errors path="industry"/></span>
</label>
<label><span class="text-form required">Category:</span>
    <select name="category" ID="cat_none" class="fleft">
        <option value="">-- Select Category --</option>
    </select>

    <c:forEach var="industry" items="${industries}">
        <select name="category" class="hidden fleft" disabled="true" ID="cat_${industry.ID}">
            <option value="none">-- Select Category --</option>
            <c:forEach var="category" items="${industry.categories}">
                <option value="${category.ID}" selected="${f:areEquals(category.ID, registration.category)}">${category.translation.name}</option>
            </c:forEach>
        </select>
    </c:forEach>

    <span class="errors"><form:errors path="category"/></span>
</label>



<script>

    var oldInd = "none";

    function loadCategories() {
        var industries = document.getElementById("industry");
        industryChanged(industries);
    }

    function industryChanged(industry) {
        var industryVal = industry.value;
        if(industryVal != "none") {
            var selectedCat = document.id('cat_' + industryVal);
            selectedCat.removeClass("hidden");
            selectedCat.set('disabled', false);

            var oldCat = document.id('cat_' + oldInd);
            oldCat.addClass("hidden");
            oldCat.set('disabled', true);

            oldInd = industry.value;
        }
    }

</script>





