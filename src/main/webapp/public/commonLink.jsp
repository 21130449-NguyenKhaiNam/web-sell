<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<meta name="viewport"
      content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<!--Các thư viện hỗ trợ-->
<%--Animate css--%>
<link
        rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"
/>
<!-- Hover.css -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/hover.css/2.3.1/css/hover-min.css"
      integrity="sha512-csw0Ma4oXCAgd/d4nTcpoEoz4nYvvnk21a8VA2h2dzhPAvjbUIK6V3si7/g/HehwdunqqW18RwCJKpD7rL67Xg=="
      crossorigin="anonymous" referrerpolicy="no-referrer"/>
<!--Font Awesome-->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
      integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
      crossorigin="anonymous" referrerpolicy="no-referrer"/>
<!--Bootstrap-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
      crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
<!--Favicon-->
<link rel="apple-touch-icon" sizes="180x180" href=<c:url value="/assets/favicon/apple-touch-icon.png"/>>
<link rel="icon" type="image/png" sizes="32x32" href=<c:url value="/assets/favicon/favicon-32x32.png"/>>
<link rel="icon" type="image/png" sizes="16x16" href=<c:url value="/assets/favicon/favicon-16x16.png"/>>
<link rel="icon" type="image/png" sizes="192x192" href=
<c:url value="/assets/favicon/android-chrome-192x192.png"/>
        <link rel="manifest" href=<c:url value="/assets/favicon/site.webmanifest"/>>
<!--Web font-->
<%--jquery--%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"
        integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<!--Font-->
<link rel="stylesheet" href="<c:url value="/assets/font/webfonts/Montserrat.css" />">
<!--CSS-->
<link rel="stylesheet" href=<c:url value="/assets/css/reset.css"/>>
<link rel="stylesheet" href=<c:url value="/assets/css/base.css"/>>
<!-- Tippy -->
<script src="https://unpkg.com/@popperjs/core@2/dist/umd/popper.min.js"></script>
<script src="https://unpkg.com/tippy.js@6/dist/tippy-bundle.umd.js"></script>
<link href="https://unpkg.com/tippy.js@6/animations/scale.css"/>
<link rel="stylesheet" href="https://unpkg.com/tippy.js@6/themes/light.css"/>
<!-- Datatable + Select-->
<link href="https://cdn.datatables.net/v/dt/dt-2.0.8/sl-2.0.3/datatables.min.css" rel="stylesheet">
<!--gasparesganga-->
<script src="https://cdn.jsdelivr.net/npm/gasparesganga-jquery-loading-overlay@2.1.7/dist/loadingoverlay.min.js"></script>
<%--Sweetalert--%>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.11.0/dist/sweetalert2.all.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.11.0/dist/sweetalert2.min.css" rel="stylesheet">
<!-- Google tag (gtag.js) -->
<script async src="https://www.googletagmanager.com/gtag/js?id=G-8C4K8R0X5G"></script>
<script>
    window.dataLayer = window.dataLayer || [];

    function gtag() {
        dataLayer.push(arguments);
    }

    gtag('js', new Date());

    gtag('config', 'G-8C4K8R0X5G');
</script>
