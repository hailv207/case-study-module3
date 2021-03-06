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
    <%--    Data table css--%>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>
    <title>Menu Manager</title>
</head>
<body>
<div class="container-fluid">
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
                                <a class="nav-link" href="/users">Quản lý người dùng <span
                                        class="sr-only">(current)</span></a>
                            </li>
                            <li class="nav-item ">
                                <a class="nav-link" href="/menu">Quản lý menu <span class="sr-only">(current)</span></a>
                            </li>
                            <li class="nav-item ">
                                <a class="nav-link active" href="/orders">Quản lý Order <span
                                        class="sr-only">(current)</span></a>
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
    <div class="row" style="height: 120px"><p></p></div>
    <form>
        <div class="row">
            <div class="form-group col-4">
                <label for="fakeorderID">Mã đơn hàng</label>
                <input name="fakeorderID" disabled type="text" class="form-control" id="fakeorderID"
                       value="${selectedOrder.getId()}">
            </div>

            <div class="form-group col-4">
                <label for="customerID">Mã khách hàng</label>
                <input name="customerID" disabled type="text" class="form-control" id="customerID"
                       value="${selectedOrder.getCustomerID()}">
            </div>

            <div class="form-group col-4">
                <label for="date">Ngày tạo</label>
                <input name="date" disabled type="text" class="form-control" id="date"
                       value="${selectedOrder.getDate()}">
            </div>

            <div class="form-group col-4">
                <label for="total">Tổng tiền</label>
                <input name="total" disabled type="text" class="form-control" id="total"
                       value="${selectedOrder.getFormattedTotal()}">
            </div>

            <div class="form-group col-4">
                <label for="type">Loại đơn hàng</label>
                <input name="type" disabled type="text" class="form-control" id="type"
                       value="${selectedOrder.getType()}">
            </div>

            <div class="form-group col-4">
                <label for="shipperID">Mã shipper</label>
                <input name="shipperID" disabled type="text" class="form-control" id="shipperID"
                       value="${selectedOrder.getShipperID()}">
            </div>
        </div>
    </form>
    <form action="" method="post">
        <input name="orderID" hidden type="text" class="form-control" id="orderID" value="${selectedOrder.getId()}">

        <div class="form-group">
            <label for="newStatus">Trạng thái</label>
            <select class="form-control" id="newStatus" name="newStatus">
                <c:forEach items='${requestScope["availableStatus"]}' var="status">
                    <option value="${status}">${status}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="comment">Nội dung cập nhật</label>
            <input name="comment" type="text" class="form-control" id="comment" placeholder="Nhập nội dung cập nhật">
        </div>
        <input class="btn btn-primary" type="submit" value="Cập nhật">
    </form>
    <table id="example" class="table table-striped table-bordered dt-responsive nowrap">
        <thead>
        <tr>
            <th>Ngày</th>
            <th>Trạng thái</th>
            <th>Nội dung cập nhật</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items='${requestScope["orderStatusList"]}' var="orderStatus">
            <tr>
                <td style="width: 15%">${orderStatus.getDate()}</td>
                <td style="width: 15%">${orderStatus.getStatus()}</td>
                <td style="width: 70%">${orderStatus.getComment()}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>


<script>
    $(document).ready(function () {
        $('#example').DataTable();
    });

</script>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
        crossorigin="anonymous"></script>
<script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.21/js/dataTables.bootstrap4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>

</body>
</html>
