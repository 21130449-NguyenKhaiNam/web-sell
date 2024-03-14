<%@ page import="java.util.List"%>
<%@ page import="models.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="productFactory" class="utils.ProductFactory"
	scope="session" />
<fmt:setLocale value="vi_VN" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<!--Các thư viện hỗ trợ-->
<!--Font Awesome-->
<link rel="stylesheet"
	href="assets/fontIcon/fontawesome-free-6.4.2-web/css/all.min.css">
<!--Bootstrap-->
<link rel="stylesheet" href="assets/bootstrap/bootstrap-grid.min.css">
<!--Jquery-->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"
	integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<!--Favicon-->
<link rel="apple-touch-icon" sizes="180x180"
	href="assets/favicon/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32"
	href="assets/favicon/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16"
	href="assets/favicon/favicon-16x16.png">
<link rel="manifest" href="assets/favicon/site.webmanifest">
<!--Web font-->
<link rel="stylesheet" href="assets/font/webfonts/Montserrat.css">
<!--CSS-->
<link rel="stylesheet" href="assets/css/reset.css">
<link rel="stylesheet" href="assets/css/base.css">
<link rel="stylesheet" href="assets/css/admin/admin.css">
<link rel="stylesheet" href="assets/css/productBuying.css">
<link rel="stylesheet" href="assets/css/admin/adminProducts.css">
<title>Paging</title>
</head>
<body>
	<ul class="paging">
		<c:if test="${requestScope.quantityPage != 0}">
			<%
			int currentPage = 1;
			if (request.getAttribute("currentPage") == null) {
				currentPage = 1;
			}
			else{
				currentPage = (int)request.getAttribute("currentPage");
			}

			if (currentPage > 3) {
			%>
			<c:url var="linkPaging" value="${link}">
				<c:param name="page" value="${pageNumber}" />
			</c:url>
			<a class="page" href="${linkPaging}1">1</a>
			<%
			}
			%>

			<c:forEach var="pageNumber" begin="${requestScope.quantityPageMin}"
				end="${requestScope.quantityPageMax}">
				<c:url var="linkPaging" value="${requestScope.requestURL}">
					<c:param name="page" value="${pageNumber}" />
				</c:url>
				<c:choose>
					<c:when test="${pageNumber == requestScope.currentPage}">
						<a class="page page--current" href="${linkPaging}">${pageNumber}</a>
					</c:when>
					<c:otherwise>
						<a class="page" href="${linkPaging}">${pageNumber}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</c:if>
	</ul>
</body>
</html>