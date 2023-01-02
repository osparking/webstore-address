<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<title>주소 검색</title>
</head>
<body>
	<div class="container h-100 w-auto">
		<div
			class="row h-100 w-auto justify-content-center align-items-center">
			<div class="col-10 col-md-8 col-lg-6">
				<form class="form-example" action="" method="get">
					<label for="addr">검색 키 입력 형태 =>
						<p>
							-도로명(예, 덕영대로895)")<br> -도로명 건물번호(예, 덕영대로 89)")<br>
							-건물명(예, 세진)")
						</p>
					</label><br> 
					<input type="text" id="addr" name="addressSearchKey"
						value="덕영대로895"> 
					<input type="submit" value="검색" />
					<c:if test="${addressList ne null}">
					<table class="table-bordered w-auto">
						<tr>
							<th>번호</th>
							<th>우편번호</th>
							<th>도로명 주소</th>
						</tr>
						<c:forEach items="${addressList}" var="address" varStatus="loop">
							<tr>
								<td>${loop.index +1}</td>
								<td>${address.newZipcode}</td>
								<td><a href="detail?mgmtNumber=${address.mgmtNumber}
									&roadName=${address.roadName}
									&newZipcode=${address.newZipcode}">
								${address.roadName}</a></td>
							</tr>
						</c:forEach>
					</table>					
					</c:if>
				</form>
			</div>
		</div>
	</div>
</body>
</html>