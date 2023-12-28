<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!--Các thư viện hỗ trợ-->
    <!--Font Awesome-->
    <link rel="stylesheet" href="assets/fontIcon/fontawesome-free-6.4.2-web/css/all.min.css">
    <!--Bootstrap-->
    <link rel="stylesheet" href="assets/bootstrap/bootstrap-grid.min.css">
    <!--Favicon-->
    <link rel="apple-touch-icon" sizes="180x180" href="assets/favicon/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="assets/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="assets/favicon/favicon-16x16.png">
    <link rel="manifest" href="assets/favicon/site.webmanifest">
    <!--Web font-->
    <link rel="stylesheet" href="assets/font/webfonts/Montserrat.css">
    <!--CSS-->
    <link rel="stylesheet" href="assets/css/reset.css">
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/css/admin/admin.css">
    <link rel="stylesheet" href="assets/css/admin/adminProducts.css">
    <title>Quản lý sản phẩm</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<main id="main">
    <nav class="navbar">
        <div class="container-xl">
            <ul class="navbar__list">
                <li
                        class="navbar__item"><a href="./adminProducts.html"
                                                class="navbar__link button button button--hover navbar__link--clicked">Sản
                                                                                                                       phẩm</a>
                </li>
                <li class="navbar__item"><a href="adminOrders.jsp"
                                            class="navbar__link button button button--hover ">Đơn hàng</a>
                </li>
                <li class="navbar__item"><a href="adminUsers.jsp"
                                            class="navbar__link button button button--hover ">Khách
                                                                                              hàng</a>
                </li>
            </ul>
        </div>
    </nav>
    <section class="content">
        <div class="container-xl">
            <div class="row">
                <div class="col-3">
                    <aside class="filler">
                        <form action="#" class="form" id="form-search">
                            <!--Search-->
                            <article class="form__search-block filler__block">
                                <i class="search__icon fa-solid fa-magnifying-glass"></i>
                                <input type="text" name="keyword">
                                <select name="searchType" class="search__select">
                                    <option value="id" name="type" class="search__option">Mã sản phẩm</option>
                                    <option value="name" name="type" class="search__option">Tên sản phẩm</option>
                                    <option value="categoryId" name="type" class="search__option">Tên phân loại</option>
                                </select>
                            </article>

                            <article class="filler__block">
                                <h2 class="filler__heading">Thời gian cập nhập</h2>
                                <div>
                                    <div class="filter__date">
                                        <span>Từ:</span>

                                        <input type="date" name="date"
                                               id="date-start">

                                    </div>
                                    <div class="filter__date">
                                        <span>Đến:</span>

                                        <input type="date" name="date"
                                               id="date-end">

                                    </div>

                                </div>
                            </article>

                            <div class="filter__buttons">
                                <input type="reset" class="button filter__button button--hover" value="Chọn lại">
                                <button class="button filter__button button--hover filter__submit" type="submit">Lọc
                                </button>
                            </div>
                        </form>
                    </aside>
                </div>
                <div class="col-9">
                    <div>
                        <h1>Danh sách sản phẩm</h1>
                        <a href="adminAddProduct.jsp" id="button-add-product" class="button button__add">
                            <i class="fa-solid fa-plus"></i>
                            Thêm sản phẩm
                        </a>
                        <div id="button-remove-product" class="button button__delete">
                            <i class="fa-solid fa-minus"></i>
                            Xóa sản phẩm
                        </div>
                    </div>
                    <div class="table__wrapper">
                        <table class="table">
                            <thead>
                            <tr class="table__row">
                                <th class="table__checkbox">

                                </th>

                                <th class="table__head">Chỉnh sửa</th>
                                <th class="table__head">Mã sản phẩm</th>
                                <th class="table__head">Tên sản phẩm</th>
                                <th class="table__head">
                                    Phân loại sản phẩm
                                </th>
                                <th class="table__head">Giá gốc</th>
                                <th class="table__head">Giá giảm</th>
                            </tr>
                            </thead>
                            <tbody>
                            <!--                            <tr class="table__row">-->
                            <!--                                <td class="table__date-checkbox">-->
                            <!--                                    <label class="check">-->
                            <!--                                        <input type="checkbox" name="passing" class="filter__input" hidden="true">-->
                            <!--                                    </label>-->
                            <!--                                </td>-->
                            <!--                                <td class="table__data-edit">-->
                            <!--                                    <label class="" for="">-->
                            <!--                                        <i class="fa-solid fa-pen-to-square"></i>-->
                            <!--                                    </label>-->
                            <!--                                </td>-->
                            <!--                                <td class="table__data">-->
                            <!--                                    <p class="table__cell">Lorem ipsum dolor sit amet, consectetur adipisicing elit.-->
                            <!--                                                           Culpa, ullam.</p>-->
                            <!--                                </td>-->
                            <!--                                <td class="table__data">-->
                            <!--                                    <p class="table__cell">Lorem ipsum dolor sit amet, consectetur adipisicing elit.-->
                            <!--                                                           Iure, quasi!</p>-->
                            <!--                                </td>-->
                            <!--                                <td class="table__data">-->
                            <!--                                    <p class="table__cell">Lorem ipsum dolor sit amet, consectetur adipisicing elit.-->
                            <!--                                                           Ipsam, nobis?</p>-->
                            <!--                                </td>-->
                            <!--                                <td class="table__data">-->
                            <!--                                    <p class="table__cell">Lorem ipsum dolor sit amet, consectetur adipisicing elit.-->
                            <!--                                                           Autem, consequatur?</p>-->
                            <!--                                </td>-->
                            <!--                            </tr>-->
                            </tbody>
                        </table>
                    </div>
                    <!--Paging-->
                    <ul class="paging"></ul>
                </div>
            </div>
        </div>
    </section>
</main>
<div id="dialog-product-add" class="modal">
    <article class="modal__content modal__product">
        <div>
            <h1>Thêm sản phẩm</h1>
            <i class="modal__product-close fa-solid fa-xmark"></i>
        </div>
        <form action="#!" class="product__form">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-7">
                        <label class="form__label">
                            <span class="form__title">Mã sản phẩm
                                <i class="form__detail fa-solid fa-circle-info"></i>
                                <p>Đây là mã sản phẩm, mã sản phẩm này không được trùng với mã sản phẩm khác!</p>
                            </span>

                            <input type="text" name="id" class="form__input">
                        </label>
                        <label class="form__label">
                            <span class="form__title">Tên đơn hàng
                                <i class="form__detail fa-solid fa-circle-info"></i>
                                <p>Đây là tên của sản phẩm</p>
                            </span>

                            <input type="text" name="name" class="form__input">
                        </label>

                        <label class="form__label">
                            <span class="form__title">Phân loại sản phẩm
                                <i class="form__detail fa-solid fa-circle-info"></i>
                                <p>Lựa chọn phân loại cho sản phẩm!</p>
                            </span>
                            <select type="text" name="idCategory" class="form__select">
                                <!-- <option name="category" value="" class="form__option">Áo dài tay</option>-->
                            </select>
                        </label>

                        <label class="form__label">
                            <span class="form__title">Giá bán
                                <i class="form__detail fa-solid fa-circle-info"></i>
                                <p>Giá bán của sản phẩm</p>
                            </span>

                            <input type="text" name="originalPrice" class="form__input">
                        </label>

                        <label class="form__label">
                            <span class="form__title">Giá giảm
                                <i class="form__detail fa-solid fa-circle-info"></i>
                                <p>Giá giảm của sản phẩm!</p>
                            </span>
                            <input type="text" name="salePrice" class="form__input">
                        </label>

                        <div class="form__label">
                            <span class="form__title">Kích cỡ
                                <i class="form__detail fa-solid fa-circle-info"></i>
                                <p>Kích thước mặc định có sẵn</p>
                            </span>
                            <div>
                                <div class="form__sizes">
                                    <div class="form__size">
                                        <input type="text" name="size" class="form__size-input">
                                    </div>
                                </div>
                                <span class="form__add-size">Thêm kích cỡ</span>
                            </div>
                        </div>

                        <div class="form__label">
                            <span class="form__title">Màu sắc
                                <i class="form__detail fa-solid fa-circle-info"></i>
                                <p>Màu sắc mặc định có sẵn</p>
                            </span>
                            <div>
                                <div class="form__colors">
                                    <div class="form__color">
                                        <input type="color" name="color" class="form__color-input">
                                    </div>
                                </div>
                                <span class="form__add-color">Thêm màu sắc</span>
                            </div>
                        </div>

                    </div>
                    <div class="col-5">
                        <label class="form__preview-img">
                            <img src="" alt="">
                            <i class="form__preview-icon fa-solid fa-image"></i>
                            <input type="file" class="form__file" hidden="true">
                        </label>
                    </div>

                    <div class="col-12">
                        <label class="form__desc">
                            <span class="form__title">Mô tả
                                <i class="form__detail fa-solid fa-circle-info"></i>
                                <p>Mô tả của sản phẩm</p>
                            </span>
                            <textarea class="form__textarea" name="description"></textarea>
                        </label>
                        <button class="button">Thêm sản phẩm</button>
                    </div>
                </div>
            </div>
        </form>
    </article>
    <div class="modal__blur"></div>
</div>
<div id="dialog-product-edit" class="modal">
    <article class="modal__content modal__product">
        <div>
            <h1>Chỉnh sửa thông tin</h1>
            <i class="modal__product-close fa-solid fa-xmark"></i>
        </div>
        <form action="#!" class="product__form">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-7">
                        <label class="form__label">
                            <span class="form__title">Mã sản phẩm
                                <i class="form__detail fa-solid fa-circle-info"></i>
                                <p>Đây là mã sản phẩm, mã sản phẩm này không được trùng với mã sản phẩm khác!</p>
                            </span>

                            <input type="text" name="id" class="form__input">
                        </label>
                        <label class="form__label">
                            <span class="form__title">Tên đơn hàng
                                <i class="form__detail fa-solid fa-circle-info"></i>
                                <p>Đây là tên của sản phẩm</p>
                            </span>

                            <input type="text" name="name" class="form__input">
                        </label>
                        <label class="form__label">
                            <span class="form__title">Phân loại sản phẩm
                                <i class="form__detail fa-solid fa-circle-info"></i>
                                <p>Lựa chọn phân loại cho sản phẩm!</p>
                            </span>
                            <select type="text" name="idCategory" class="form__select">
                                <!--<option name="category" value="" class="form__option">Áo dài tay</option>-->
                            </select>
                        </label>

                        <label class="form__label">
                            <span class="form__title">Giá bán
                                <i class="form__detail fa-solid fa-circle-info"></i>
                                <p>Giá bán của sản phẩm</p>
                            </span>

                            <input type="text" name="originalPrice" class="form__input">
                        </label>

                        <label class="form__label">
                            <span class="form__title">Giá giảm
                                <i class="form__detail fa-solid fa-circle-info"></i>
                                <p>Giá giảm của sản phẩm!</p>
                            </span>
                            <input type="text" name="salePrice" class="form__input">
                        </label>

                        <label class="form__label">
                            <span class="form__title">Kích cỡ cho trước
                                <i class="form__detail fa-solid fa-circle-info"></i>
                                <p>Kích thước mặc định có sẵn</p>
                            </span>
                            <div>
                                <div class="form__sizes">
                                    <div class="form__size">
                                        <input type="text" name="size" class="form__size-input">
                                    </div>
                                </div>
                                <span class="form__add-size">Thêm kích cỡ</span>
                            </div>
                        </label>

                        <div class="form__label">
                            <span class="form__title">Màu sắc
                                <i class="form__detail fa-solid fa-circle-info"></i>
                                <p>Màu sắc mặc định có sẵn</p>
                            </span>
                            <div>
                                <div class="form__colors">
                                    <div class="form__color">
                                        <input type="color" name="color" class="form__color-input">
                                    </div>
                                </div>
                                <span class="form__add-color">Thêm màu sắc</span>
                            </div>
                        </div>
                    </div>
                    <div class="col-5">
                        <label class="form__preview-img">
                            <img src="" alt="">
                            <i class="form__preview-icon fa-solid fa-image"></i>
                            <input type="file" class="form__file" hidden="true">
                        </label>
                    </div>

                    <div class="col-12">
                        <label class="form__desc">
                            <span class="form__title">Mô tả
                                <i class="form__detail fa-solid fa-circle-info"></i>
                                <p>Mô tả của sản phẩm</p>
                            </span>
                            <textarea class="form__textarea" name="description"></textarea>
                        </label>
                        <button class="button">Cập nhập</button>
                    </div>
                </div>
            </div>
        </form>
    </article>
    <div class="modal__blur"></div>
</div>

<form method="post" enctype="multipart/form-data" action="upload">
    <input type="file" name="file">
    <input type="submit" value="Upload">
</form>
<script src="js/data.js"></script>
<script src="js/paging.js"></script>
<script src="js/admin/adminProducts.js"></script>

</body>
</html>
