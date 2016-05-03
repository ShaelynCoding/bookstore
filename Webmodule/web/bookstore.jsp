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
</head>
<body>
<nav class="navbar navbar-default" role="navigation">

        <a class="navbar-brand" href="">Book Store</a>

        <ul class="nav navbar-nav navbar-left" >

            <li class="active"><INPUT type = text id = "search" size =10px /></li>
            <li class="active" data-toggle="modal"   data-target="#searchModal"><button type="button" onclick="searchbook()">Search</button></li>

        </ul>
        <ul class="nav navbar-nav navbar-right" >
            <li class="active" data-toggle="modal"   data-target="#cartModal"><a onclick="shopCart()"><span class="glyphicon glyphicon-shopping-cart"></span>shop cart</a></li>
            <li class="active" data-toggle="modal"   data-target="#dataModal"><a onclick="showdata()"><span class = "glyphicon glyphicon-list" ></span>shop record</a></li>
            <li class="active"><a href='jj.jsp' onclick="logout()"><span class = "glyphicon glyphicon-user"></span>logout&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>

        </ul>

</nav>
<div id="books">
    <div class="bookInfo">
        <nav class="navbar navbar-default">
            <ul class="nav navbar-nav navbar-left">
                <h4 class="title"></h4>
                <p class="author"></p>
                <p class="price"></p>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <br>
                <div class="active" data-toggle="modal"   data-target="#detailModal">

                    <button type="button" class="btn btn-default btn-sm" onclick="" id="change">
                        &nbsp;&nbsp;&nbsp;&nbsp;查看详情 &nbsp;&nbsp;&nbsp;&nbsp;
                    </button>
                    &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
                </div>
                <%--<div  class="input-group">--%>
                    <%--<input type="text" id="initNum" class="col-xs-4">--%>
                    <%--<button type="button" onclick="addCart()" id="change2">+</button>--%>
                    <%--&nbsp;&nbsp;&nbsp;&nbsp;--%>
                <%--</div>--%>
            </ul>
        </nav>
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
            <div class="modal-body" id="databody">

            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="searchModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
            </div>
            <div class="modal-body" id="searchbody">

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
            <div class="modal-body" id="cartbody" >
                <table class = "table table-bordered">
                    <tr>
                        <td>ISBN</td>
                        <td>书名</td>
                        <td>数量</td>
                    </tr>
                    <div >
                        <tr class="bookCart">
                            <td class="id"></td>
                            <td class="name"></td>
                            <td class="num"></td>
                            <td>
                                <button type="button" class="btn btn-primary" id="remove" onclick = "delCart()">
                                    ×
                                </button>
                            </td>
                        </tr>
                    </div>
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
<script type="text/javascript">

    $(document).ready(function () {

        ajax("bookaction","get",{
            operation:"showBooks"
        },function (jsonStr) {
            var jsonArr=JSON.parse(jsonStr);
            displayBooks(jsonArr);
            //cartInit();
        });


    });
</script>

</html>
