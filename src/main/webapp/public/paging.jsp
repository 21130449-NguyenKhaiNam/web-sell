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
    <jsp:include page="/public/commonLink.jsp" />
    <title>Paging</title>

</head>
<body>
<ul class="paging">
    <c:if test="${requestScope.quantityPage != 0}">
        <%
            if ((int) request.getAttribute("currentPage") > 3) {
        %>
        <a class="page access_page_quickly">...</a>
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
                    <a class="page page--current">${pageNumber}</a>
                </c:when>
                <c:otherwise>
                    <a class="page">${pageNumber}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </c:if>
</ul>
</body>

<!--tippy tooltip-->
<script src="https://unpkg.com/popper.js@1"></script>
<script src="https://unpkg.com/tippy.js@5/dist/tippy-bundle.iife.js"></script>


<%
    int minPage = (int) request.getAttribute("quantityPageMin");
    Object linkPaging = request.getAttribute("requestURL");
%>
<script>
    const div = document.createElement('div')
    div.style.width = '200px'
    div.style.height = '50px'
    div.style.overflow = 'auto'

    for (let i = 1; i < <%=minPage%>; i++) {
        const a = document.createElement('a')
        a.innerText = i
        a.href = "<%=linkPaging%>page=" + i
        a.classList.add("page")
        div.appendChild(a)
    }

    tippy('.access_page_quickly', {
        content : div,
        placement : 'top-start',
        interactive : true,
        duration : [ 500, 250 ],
        arrow : true,
    });
</script>
</html>