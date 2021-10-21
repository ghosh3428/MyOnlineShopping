<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="col-md-3">
	<p class="lead">Online Shopping</p>
	<div class="list-group">
		<c:forEach items="${categories}" var="category">
			<a href="${contextRoot}/product/show/category/${category.id}/products" class="list-group-item">${category.categoryName}</a>
		</c:forEach>

	</div>
</div>