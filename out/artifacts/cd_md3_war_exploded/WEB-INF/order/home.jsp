<%@ page import="model.Promotion" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: vuhaile
  Date: 8/5/20
  Time: 08:35
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <%-- Addition CSS --%>
    <script src="https://kit.fontawesome.com/dffada201a.js" crossorigin="anonymous"></script>
    <link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">

    <style>
        .navbar .navbar-right .dropdown-menu {
            width: 505px;
            padding: 20px;
            left: auto;
            right: 0;
            font-size: 14px;
        }

        body {
            background-color: rgba(104, 170, 177, 0.3);
        }

        .card:hover {
            border: darkblue solid 3px;
        }

    </style>
    <title>SEAFOOD ONLINE</title>
</head>
<body>

<header>
    <!--Navbar-->
    <div class="row">
        <nav class="navbar navbar-expand-lg  navbar-dark bg-dark col-12">
            <div class="container-fluid">
                <a href="/home" class="navbar-brand">
                    <!-- Logo Image -->
                    <img src="https://live.staticflickr.com/65535/50192445291_a30c4d3dee.jpg" width="90" alt=""
                         class="d-inline-block align-middle mr-2">
                    <!-- Logo Text -->
                    <span class="text-uppercase font-weight-bold" style="color: aqua">SEAFOOD ONLINE</span>
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li id="TATCA" class="nav-item active">
                            <a class="nav-link" href="/home?catagory=TATCA">Tất cả <span
                                    class="sr-only">(current)</span></a>
                        </li>
                        <li id="LAU" class="nav-item">
                            <a class="nav-link" href="/home?catagory=LAU">Lẩu <span class="sr-only">(current)</span></a>
                        </li>
                        <li id="CUA" class="nav-item ">
                            <a class="nav-link" href="/home?catagory=CUA">Cua <span class="sr-only">(current)</span></a>
                        </li>
                        <li id="TOM" class="nav-item ">
                            <a class="nav-link" href="/home?catagory=TOM">Tôm <span class="sr-only">(current)</span></a>
                        </li>
                        <li id="MUC" class="nav-item ">
                            <a class="nav-link" href="/home?catagory=MUC">Mực <span class="sr-only">(current)</span></a>
                        </li>
                        <li id="OCSONGAO" class="nav-item ">
                            <a class="nav-link" href="/home?catagory=OCSONGAO">Ốc - Sò - Ngao <span class="sr-only">(current)</span></a>
                        </li>
                        <li id="KHAC" class="nav-item ">
                            <a class="nav-link" href="/home?catagory=KHAC">Khác <span
                                    class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item ">
                            <a class="nav-link" href="/home?catagory=DOUONG">Đồ uống <span
                                    class="sr-only">(current)</span></a>
                        </li>

                    </ul>
                    <div style="margin-right: 5px"><input class="ml-4" type="checkbox" checked data-toggle="toggle"
                                                          data-on='<i class="fa fa-shipping-fast">&nbsp;Delivery</i>'
                                                          data-off='<i class="fa fa-store">&nbsp;Take away</i>'
                                                          data-onstyle="success"
                                                          data-offstyle="danger"
                                                          data-width="150" data-height="10" style="right: 20px"></div>
                    <form class="form-inline my-2 my-lg-0">
                        <input class="form-control mr-sm-2" type="search" placeholder="Tìm món" aria-label="Search"
                               style="right: 20px">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit" style="margin-right: 5px">
                            Tìm
                        </button>
                    </form>
                    <c:if test='${!isLogined}'>
                        <ul class="nav navbar-nav navbar-right">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><b>Login</b> <span
                                        class="caret"></span></a>
                                <ul id="login-dp" class="dropdown-menu">
                                    <li>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <form class="form" role="form" method="post" action="login"
                                                      accept-charset="UTF-8" id="login-nav">
                                                    <div class="form-group">
                                                        <label class="sr-only" for="username">Tên đăng nhập</label>
                                                        <input type="text" name="username" class="form-control"
                                                               id="username" placeholder="Tên đăng nhập" required>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="sr-only" for="password">Mật khẩu</label>
                                                        <input type="password" name="password" class="form-control"
                                                               id="password" placeholder="Mật khẩu" required>
                                                    </div>
                                                    <div class="form-group">
                                                        <button type="submit" class="btn btn-primary btn-block">Đăng
                                                            nhập
                                                        </button>
                                                    </div>
                                                </form>
                                                <div class="bottom text-center">
                                                    Chưa có tài khoản? <a href="#"><b>Đăng ký</b></a>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </c:if>
                    <c:if test="${isLogined}">
                        <div class="dropdown pmd-dropdown pmd-user-info ml-auto">
                            <a href="" class="btn-user dropdown-toggle media align-items-center" data-toggle="dropdown"
                               data-sidebar="true" aria-expanded="false">
                                <img class="mr-2" src="https://live.staticflickr.com/65535/50201688646_22907d971e_m.jpg"
                                     width="40" height="40" alt="avatar">
                                <div class="media-body">
                                </div>
                                <i class="material-icons md-light ml-2 pmd-sm">${loginedUser.getName()}</i>
                            </a>
                            <ul class="dropdown-menu dropdown-menu-right" role="menu">
                                <a class="dropdown-item" href="/profile">Edit Profile</a>
                                <a class="dropdown-item" href="/logout">Logout</a>
                            </ul>
                        </div>
                    </c:if>


                </div>
            </div>
        </nav>
    </div>
    <!--/.Navbar-->


</header>
<div class="container-fluid">
    <div class="row"><p></p></div>
    <div class="row">
        <div class="col-10"></div>
        <select class="form-select-button btn-warning col-2">
            <option value="" disabled selected>Sắp xếp</option>
            <option value="1">Mới nhất</option>
            <option value="2">Cũ nhất</option>
            <option value="3">Giá thấp nhất</option>
            <option value="4">Giá cao nhất</option>
            <option value="5">Đang khuyến mại</option>
        </select>
    </div>
</div>
<div class="container-fluid">

    <div class="row">

        <%-- Tạo lưới sản phẩm --%>
        <c:forEach items='${requestScope["menuList"]}' var="menuItem">
            <div class="col-lg-3 col-md-4 mb-3">
                <div class="card h-100">
                    <a href="#"><img class="card-img-top" src="${menuItem.getImageURL()}" height="200" alt=""></a>
                    <div class="card-body">
                        <h4 class="card-title">
                            <a href="#">${menuItem.getName()}</a>
                        </h4>
                        <h5><i>${menuItem.getUnit()}</i></h5>
                        <c:if test="${menuItem.discount > 0}">
                            <img src="https://live.staticflickr.com/65535/50197698118_801cb7b61c_m.jpg" width="75"
                                 height="75" style="position: absolute;right: 5px;top:200px">
                            <h6 style="color: darkred"><s>${menuItem.getFormattedPrice()} đ</s></h6>
                        </c:if>
                        <h6 style="color: limegreen">${menuItem.getFormattedDiscountPrice()} đ</h6>
                    </div>
                    <p class="card-text" style="margin-left: 20px;margin-right: 20px">${menuItem.getDescription()}</p>
                    <div class="card-footer">
                        <form style="width: 100%" method="post" action="/cart?action=additem&menuID=${menuItem.getId()}">
                            <div class="row">
                                <a href="/menu=info&id=${menuItem.getId()}" style="width: 50%">
                                    <button type="button" class="btn btn-primary"
                                            style="margin-right: 5px;margin-left: 10px;width: 40%"><i
                                            class="fa fa-info-circle"></i>
                                    </button>
                                </a>

                                <button type="submit" class="btn btn-info"
                                        style="margin-right: 5px;margin-left: 10px; width: 40%"><i
                                        class="fa fa-cart-plus"></i>
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <%-- /Tạo lưới sản phẩm --%>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.js"
        integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>
<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
<script src="WEB-INF/js/js.js"></script>

</body>
</html>
