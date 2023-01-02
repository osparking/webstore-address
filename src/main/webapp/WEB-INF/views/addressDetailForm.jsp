<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세주소 입력</title>
</head>
<body>
	<p>상세 주소를 입력하세요.</p>
	<p>도로명 주소: ${address.roadName}</p>
	<form action="/webstore/address/saveDetailAddr" method="post">
		<input type="hidden" name="mgmtNumber" value="${address.mgmtNumber}"/>
		<input type="hidden" name="newZipcode" value="${address.newZipcode}"/>
		<input type="hidden" name="roadName" value="${address.roadName}"/>
		<label for="detailAddr">상세주소:</label>
		<input type="text" id="detailAddr" name="detailAddr" value=""/>
		<input type="submit" value="저장"/>		
	</form>
</body>
</html>