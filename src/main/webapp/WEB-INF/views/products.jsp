<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>범이비누 상품</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>상품 목록</h1>
				<p>우리 상점의 모든 상품들</p>
				<p>${tagline}</p>
			</div>
		</div>
	</section>

	<section class="container">
		<div class="row">
			<c:forEach items="${products}" var="product">
				<div class="col-sm-6 col-md-3">
					<div class="thumbnail">
						<div class="caption">
							<h3>${product.name}</h3>
							<p>${product.description}</p>
							<p>₩${product.unitPriceStr}</p>
							<p>제고 수량 : ${product.unitsInStockStr}</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</section>
	<section>
		<c:if test="${updatedRowCount > 0}">
			상품 ${updatedRowCount} 건의 재고가 1000씩 증가되었습니다.
		</c:if>
	</section>
</body>
</html>