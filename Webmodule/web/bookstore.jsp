<%--
  Created by IntelliJ IDEA.
  User: lyn
  Date: 16-3-30
  Time: 上午11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>bookstore</title>
    <link href="UI/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .vertical-center{
            position: absolute;
            left: 50%;
            transform: translate(-50%,0);
        }
    </style>
</head>
<body>
<nav class="navbar navbar-default" role="navigation">

        <a class="navbar-brand" href="">Book Store</a>

        <ul class="nav navbar-nav navbar-left" style="padding: 15px;" >
            <div class="input-group">
                <select  id="searchChoice">
                    <option value="all">全部</option>
                    <option value="Isbn">ISBN</option>
                    <option value="name">书名</option>
                    <option value="auth">作者</option>
                    <option value="type">种类</option>
                </select>
                <input type = text id = "search" size =10px  />
                <button  onclick="searchBook()">
                    Search
                </button>
            </div>
        </ul>
        <ul class="nav navbar-nav navbar-right" >
            <li class="active" data-toggle="modal"   data-target="#cartModal"><a onclick="shopCart()"><span class="glyphicon glyphicon-shopping-cart"></span>shop cart</a></li>
            <li class="active" data-toggle="modal"   data-target="#dataModal"><a onclick="showData()"><span class = "glyphicon glyphicon-list" ></span>shop record</a></li>
            <li class="active"><a href="chatRoom.html"><span class="glyphicon glyphicon-comment"></span>Chat Room</a></li>
            <li class="active"><a onclick="logout()"><span class = "glyphicon glyphicon-user"></span>logout&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>

        </ul>

</nav>
<div id="books">
    <div class="vertical-center" style="width: 80%;">
        <table class="table table-hover">
            <thead>
                <tr>
                    <th>书名</th>
                    <th>作者</th>
                    <th>价格</th>
                </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
    </div>
</div>
<div class="modal fade" id="dataModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
            </div>
            <div class="modal-body" >
                <table class = "table table-bordered" id="databody" >

                </table>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="cartModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" >
        <div class="modal-content" id="thank" >
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>

            </div>
            <div class="modal-body" >
                <table class = "table table-bordered" id="cartbody" >

                </table>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">Close
                </button>
                <button type="button" class="btn btn-primary" id="sub" onclick = "buybook()">
                    Submit
                </button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="detailModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
            </div>
            <div class="modal-body" id="detailbody">
                <h3 class="title"></h3>
                <p class="author"></p>
                <p class="ISBN"></p>
                <p class="price"></p>
                <p class="type"></p>
                <p class="remain"></p>
                <div  class="input-group">
                    <input type="text" id="buyNum" class="col-xs-4">
                    <button type="button" onclick="addCart()" id="change2">+</button>

                </div>
            </div>
        </div>
    </div>
</div>

</body>
<script src="UI/js/jquery-2.1.4.js"></script>
<script src="UI/bootstrap/dist/css/bootstrap-theme.min.css"></script>
<script src="UI/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="logic/common.js"></script>
<script src="logic/book.js"></script>
<script src="logic/user.js"></script>
<script type="text/javascript">

    $(document).ready(function () {
        var cookie=getCookie("user");
        var tmp=new Array();
        tmp=cookie.split("@");
        var role=tmp[1];
        ajax("bookaction","post",{
            operation:"showBooks",
            role:role
        },function (jsonStr) {
            var jsonArr=JSON.parse(jsonStr);
            displayBooks(jsonArr);
            //cartInit();
        });


    });
</script>

</html>
