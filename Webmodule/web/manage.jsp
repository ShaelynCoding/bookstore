<%--
  Created by IntelliJ IDEA.
  User: lyn
  Date: 16-5-2
  Time: 下午6:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Bookstore Admin</title>
    <link href="UI/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="UI/bootstrap/dist/css/bootstrap-theme.min.css"></script>
    <script src="UI/js/jquery-2.1.4.js"></script>
    <script src="UI/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="http://echarts.baidu.com/build/dist/echarts-all.js"></script>
    <script src="UI/date/bootstrap-datepicker.js"></script>
    <script src="logic/common.js"></script>
    <script src="logic/manage.js"></script>
    <script src="logic/user.js"></script>

</head>
<body>

<div class="modal fade" id="staticModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
            </div>
            <div class="modal-body">
                <table class = "table table-bordered" id="staticbody">

                </table>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="GUIModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
            </div>

            <div class="modal-body" id="GUIbody">

            </div>
            <div id="cate" style="height:400px"></div>

        </div>
    </div>
</div>
<div class="modal fade" id="modiModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                信息修改
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
            </div>
            <div class="modal-body" id="modibody">
                <ul >ISBN: <div id="isbn2"></div></ul>
                <ul>价格<input type="text" id="price2"/></ul>
                <ul>库存<input type="text" id="num2"/></ul>
                <ul>类别<input type="text" id="type2"/></ul>
                <button type="button" class="btn btn-default" onclick="modiBook()">确定</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="queryModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div id="hidehead" class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
            </div>
            <div class="modal-body" >
                <table class = "table table-bordered" id="querybody">




                </table>


            </div>

        </div>

    </div>

</div>

<div class="modal fade" id="userModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
            </div>
            <div class="modal-body">
                <table class = "table table-bordered" id="userbody">
                    
                </table>
            </div>
        </div>
    </div>
</div>

<ul id="myTab" class="nav nav-tabs">
    <li class="active"><a href="#add" data-toggle="tab">Add Book</a></li>
    <li><a href="#query" data-toggle="tab">Book Query</a></li>
    <li><a href="#user" data-toggle="tab">User Manage</a></li>
    <li><a href="#static" data-toggle="tab">Static</a></li>
    <li class="nav navbar-nav navbar-right">
        <a onclick="logout()">
            <span class = "glyphicon glyphicon-user"></span>
            logout
        </a>
    </li>
</ul>

<div id="myTabContent" class="tab-content">
    <div class="tab-pane fade in active" id="add">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Book Information </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>Book ISBN</td>
                <td><INPUT TYPE=TEXT NAME=bookISDN id="bookid1" SIZE=20 ></td>
            </tr>
            <tr>
                <td>Book Name</td>
                <td><INPUT TYPE=TEXT NAME=bookName id="bookname1" SIZE=20 ></td>
            </tr>
            <tr>
                <td>Book Author</td>
                <td><INPUT TYPE=TEXT NAME=bookAuth id="bookauth1" SIZE=20></td>
            </tr>
            <tr>
                <td>Book Price</td>
                <td><INPUT TYPE=TEXT NAME=bookPrice id="bookprice1" SIZE=20></td>
            </tr>
            <tr>
                <td>Book Number</td>
                <td><INPUT TYPE=TEXT NAME=bookNum id="booknum1" SIZE=20 ></td>
            </tr>
            <tr>
                <td>Book Category</td>
                <td> <INPUT TYPE=TEXT NAME=bookType id="booktype1" SIZE=20></td>
            </tr>
            <tr>
                <td><INPUT TYPE=SUBMIT value = "submit" onclick="addbook()"></td>
            </tr>
            </tbody>
        </table>

    </div>
    <div class="tab-pane fade" id="query">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Book Information<BR></th>

            </tr>
            </thead>
            <tbody>
            <tr>
                <td>Book ISDN</td>
                <td><INPUT TYPE=TEXT id="bid"  SIZE=20 ></td>
            </tr>
            <tr>
                <td>Book Name</td>
                <td><INPUT TYPE=TEXT id="bname"  SIZE=20 ></td>
            </tr>
            <tr>
                <td><button type="button" class="btn btn-default" data-toggle="modal" data-target="#queryModal"  onclick="querybook()" >Submit</button></td>
            </tr>
            </tbody>
        </table>
        <p>You can input the id or name of book that you want to query and modify.
            What's more, you can input the book name to query then delete if don't know
            the ID for some reasons.(If you want all the information, input nothing but submit).</p>

    </div>
    <div class="tab-pane fade" id="user">
        <FORM>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>User Information<BR></th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>User Name</td>
                    <td><INPUT TYPE=TEXT id = "uame" SIZE=20 ></td>
                </tr>
                <tr>
                    <td><button type="button" class="btn btn-default" data-toggle="modal" data-target="#userModal"  onclick="queryuser()" >Submit</button></td>
                </tr>
                </tbody>
            </table>
        </FORM>
        <p>You can input the User name that you want to query or remove.
            (If you want all the information, input nothing but submit).</p>
    </div>
    <div class="tab-pane fade" id="static">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Static</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>User Name</td>
                    <td><INPUT TYPE=TEXT id = "udname" SIZE=20 ></td>
                </tr>

                <tr>
                    <td>
                        <div class="input-daterange input-group" id="datepicker">
                            <span class="input-group-addon">From</span>
                            <input type="text" class="input-sm form-control" name="begin" id= "begin" data-date-format="yyyy-mm-dd" />
                            <span class="input-group-addon">to</span>
                            <input type="text" class="input-sm form-control" name="end"  id="end" data-date-format="yyyy-mm-dd" />
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>Book Category</td>
                    <td><INPUT TYPE=TEXT id= "btype" SIZE=20 ></td>
                </tr>
                <tr>
                    <td><button type="button" class="btn btn-default" data-toggle="modal" data-target="#staticModal"  onclick="showStatic()" >Submit</button></td>
                    <%--<td><button type="button" class="btn btn-default" data-toggle="modal" data-target="#GUIModal"  onclick="cateGUI()" ><span class="glyphicon glyphicon-picture"></span>  All information group by category ( GUI )</button></td>--%>

                </tr>
            </tbody>
        </table>
     </div>
    <%--<div class="tab-pane fade" id="tt">--%>

            <%--<table class="table table-striped">--%>
                <%--<thead>--%>
                <%--<tr>--%>
                    <%--<th>Shop Time</th>--%>
                <%--</tr>--%>
                <%--</thead>--%>
                <%----%>
            <%--</table>--%>

        <%--<p>  (Attention! time format: yyyy-mm-dd ,eg. 2015-05-10 )</p>--%>
    <%--</div>--%>
    <%--<div class="tab-pane fade" id="cc">--%>
        <%--<FORM METHOD=POST ACTION=datacate>--%>
            <%--<table class="table table-striped">--%>
                <%----%>
            <%--</table>--%>
        <%--</FORM>--%>
    <%--</div>--%>
</div>
<script>
    $('#begin').datepicker();
    $('#end').datepicker();
    //error();
</script>
</body>
</html>
