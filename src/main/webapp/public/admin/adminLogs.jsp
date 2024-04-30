<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="services.LogService" %>
<%@ page import="models.Log" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/public/commonLink.jsp"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/admin.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/productBuying.css"/> ">
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/adminProducts.css" />">
    <title>Quản lý sản phẩm</title>
</head>
<body>
<main id="main">
    <section class="content">
        <div class="container-xl">
            <div class="row">
                <div class="col-lg-12">
                    <div>
                        <h1>Thông tin</h1>
                        <span class="reload__btn">
                            <i class="reload__icon fa-solid fa-rotate"></i>
                        </span>

                    </div>
                    <div class="table__wrapper">
                        <table id="table" class="table">
                            <thead>
                            <tr class="table__row">
                                <th class="table__head">Mã số</th>
                                <c:if test="${sessionScope.auth.role == '2'}">
                                    <th class="table__head">IP</th>
                                </c:if>
                                <th class="table__head">Mức độ</th>
                                <th class="table__head">Tác động</th>
                                <th class="table__head">Ngày tạo</th>
                                <th class="table__head">Giá trị trước</th>
                                <th class="table__head">Giá trị hiện tại</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                    <!--Paging-->
                    <ul class="paging">
                        <c:if test="${requestScope.quantityPage != 0}">
                            <c:forEach var="pageNumber" begin="1" end="${requestScope.quantityPage}">
                                <c:url var="linkPaing" value="${requestScope.requestURL}">
                                    <c:param name="page" value="${pageNumber}"/>
                                </c:url>
                                <c:choose>
                                    <c:when test="${pageNumber == requestScope.currentPage}">
                                        <a class="page page--current" href="${linkPaing}">${pageNumber}</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="page" href="${linkPaing}">${pageNumber}</a>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:if>
                    </ul>
                </div>
            </div>
        </div>
    </section>
</main>
<script>
    var json = []
    <%
    List<Log> logs = LogService.getINSTANCE().getLog(10);
    for(int i = 0; i < logs.size(); i++) {
        Log log = logs.get(i);
        String previous = log.getPrevious();
        String current = log.getCurrent();
        if(previous != null) {

        }

        if(current != null) {

        }
    %>
        json.push({
            "id": '<%= log.getId() %>',
            "ip": '<%= log.getIp() %>',
            "level": '<%= log.getLevel() %>',
            "resource": '<%= log.getResource() %>',
            "date create": '<%= log.getDateCreated().toString() %>',
            "previous": '<%= previous %>',
            "current": '<%= current %>'
        })
    <%}%>
    $('#table').DataTable({
        data: json,
        columns: [
            {data: 'id'},
            {data: 'ip'},
            {data: 'level'},
            {data: 'resource'},
            {data: 'date created'},
            {data: 'previous'},
            {data: 'current'},
        ],
        createdRow: (row, data, index) => {
            row.querySelector(':nth-child(3)').dataset.level = data['level'];
            row.querySelector(':nth-child(6)').classList.add('data');
            row.querySelector(':nth-child(7)').classList.add('data');
        }
    })
    $('td.data').each(function (index) {
        let text = $(this)[0].innerHTML
        if(text.length > 30) {
            tippy(this, {
                theme: 'light',
                content: text,
                interactive: true,
            })
            $(this)[0].innerHTML = text.substring(0, 29) + "..."
        }
    })
</script>
</body>
</html>
