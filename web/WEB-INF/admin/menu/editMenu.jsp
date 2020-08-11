<%--
  Created by IntelliJ IDEA.
  User: vuhaile
  Date: 8/8/20
  Time: 22:38
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

    <title>Title</title>
</head>
<body>
<header>
    <!--Navbar-->
    <div class="row fixed-top">
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
                        <li class="nav-item">
                            <a class="nav-link" href="/users">Quản lý người dùng <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="/menu">Quản lý menu <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item ">
                            <a class="nav-link" href="/orders">Quản lý Order <span class="sr-only">(current)</span></a>
                        </li>
                    </ul>

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
                                <a class="dropdown-item" href="/editprofile">Edit Profile</a>
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
    <div class="row" style="height: 120px"><p></p></div>

    <form>
        <div class="form-group">
            <label for="id">Mã món</label>
            <input name="id" disabled type="text" class="form-control" id="id"
                   placeholder="Nhập họ tên" value="${selectedMenu.getId()}">
        </div>
        <div class="form-group">
            <label for="menuType">Nhóm menu</label>
            <select class="form-control" id="menuType" name="menuType">
                <c:forEach items='${requestScope["menuTypeList"]}' var="menuType">
                    <option <c:if test="${menuType.getId() eq selectedMenu.getType()}"> selected </c:if>
                            value="${menuType.getId()}">${menuType.getName()}</option>
                </c:forEach>

            </select>
        </div>
        <div class="form-group">
            <label for="name">Tên món</label>
            <input name="name" type="text" class="form-control" id="name"
                   placeholder="Nhập tên món" value="${selectedMenu.getName()}">
        </div>
        <div class="form-group">
            <label for="unit">Đơn vị</label>
            <input name="unit" type="text" class="form-control" id="unit"
                   placeholder="Nhập đơn vị" value="${selectedMenu.getUnit()}">
        </div>
        <div class="form-group">
            <label for="price">Giá</label>
            <input name="price" type="text" class="form-control" id="price"
                   placeholder="Nhập giá" value="${selectedMenu.getPrice()}">
        </div>
        <div class="form-group">
            <label for="imageURL">Link ảnh</label>
            <input name="imageURL" type="text" class="form-control" id="imageURL"
                   placeholder="Nhập link ảnh" value="${selectedMenu.getImageURL()}">
        </div>
        <div class="form-group">
            <label for="showDate">Ngày tạo</label>
            <input name="showDate" disabled type="text" class="form-control" id="showDate"
                   value="${selectedMenu.getCreateDate()}">
        </div>
        <div class="form-group">
            <label for="status">Trạng thái</label>
            <select class="form-control" id="status" name="status">
                <option <c:if test="${selectedMenu.getStatus() eq 'selling'}"> selected </c:if> value="selling">Đang
                    bán
                </option>
                <option <c:if test="${selectedMenu.getStatus() eq 'stopped'}"> selected </c:if> value="stopped">Đã
                    ngừng
                </option>
            </select>
        </div>
        <div class="form-group">
            <label for="description">Mô tả</label>
            <input name="description" type="text" class="form-control" id="description"
                   placeholder="Nhập mô tả" value="${selectedMenu.getDescription()}">
        </div>

        <button type="submit" class="btn btn-primary" formmethod="post">Lưu</button>
        <button action="/menu" type="submit" class="btn btn-warning">Quay lại</button>

        <%--        hidden elements--%>
        <input hidden name="createDate" id="createDate" type="text" value="${selectedMenu.getCreateDate()}">
    </form>
</div>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>
</body>
</html>
