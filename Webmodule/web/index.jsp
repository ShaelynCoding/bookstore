<%--
  Created by IntelliJ IDEA.
  User: lyn
  Date: 16-3-29
  Time: 上午10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Bookstore</title>
    <STYLE>
      body{
        background: #ebebeb;
        font-family: "Helvetica Neue","Hiragino Sans GB","Microsoft YaHei","\9ED1\4F53",Arial,sans-serif;
        color: #222;
        font-size: 12px;
      }
      *{padding: 0px;margin: 0px;}
      .top_div{
        background: #008ead;
        width: 100%;
        height: 400px;
      }
      .ipt{
        border: 1px solid #d3d3d3;
        padding: 10px 10px;
        width: 290px;
        border-radius: 4px;
        padding-left: 35px;
        -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
        box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
        -webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
        -o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
        transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s
      }
      .ipt:focus{
        border-color: #66afe9;
        outline: 0;
        -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6);
        box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6)
      }
      .u_logo{
        background: url("UI/images/username.png") no-repeat;
        padding: 10px 10px;
        position: absolute;
        top: 43px;
        left: 40px;

      }
      .p_logo{
        background: url("UI/images/password.png") no-repeat;
        padding: 10px 10px;
        position: absolute;
        top: 12px;
        left: 40px;
      }
      a{
        text-decoration: none;
      }
      .tou{
        background: url("UI/images/tou.png") no-repeat;
        width: 97px;
        height: 92px;
        position: absolute;
        top: -87px;
        left: 140px;
      }
      .left_hand{
        background: url("UI/images/left_hand.png") no-repeat;
        width: 32px;
        height: 37px;
        position: absolute;
        top: -38px;
        left: 150px;
      }
      .right_hand{
        background: url("UI/images/right_hand.png") no-repeat;
        width: 32px;
        height: 37px;
        position: absolute;
        top: -38px;
        right: -64px;
      }
      .initial_left_hand{
        background: url("UI/images/hand.png") no-repeat;
        width: 30px;
        height: 20px;
        position: absolute;
        top: -12px;
        left: 100px;
      }
      .initial_right_hand{
        background: url("UI/images/hand.png") no-repeat;
        width: 30px;
        height: 20px;
        position: absolute;
        top: -12px;
        right: -112px;
      }
      .left_handing{
        background: url("UI/images/left-handing.png") no-repeat;
        width: 30px;
        height: 20px;
        position: absolute;
        top: -24px;
        left: 139px;
      }
      .right_handinging{
        background: url("UI/images/right_handing.png") no-repeat;
        width: 30px;
        height: 20px;
        position: absolute;
        top: -21px;
        left: 210px;
      }

    </STYLE>
  </head>


  <body>
<form method="post" action="Login" id="form1">
  <div class="top_div"></div>
      <div style="background: rgb(255, 255, 255); margin: -100px auto auto; border: 1px solid rgb(231, 231, 231); border-image: none; width: 400px; height: 200px; text-align: center;">
      <div style="width: 165px; height: 96px; position: absolute;">
        <div class="tou"></div>
        <div class="initial_left_hand" id="left_hand"></div>
        <div class="initial_right_hand" id="right_hand"></div>
      </div>
      <p style="padding: 30px 0px 10px; position: relative;">
        <span class="u_logo"></span>
        <input class="ipt" type="text" name=userName placeholder="user name" value="" id="username">
      </p>
      <p style="position: relative;">
        <span class="p_logo"></span>
        <input class="ipt"  type="password" name=userPwd placeholder="password" value="" id="userpwd">
      </p>
      <div style="height: 50px; line-height: 50px; margin-top: 30px; border-top-color: rgb(231, 231, 231); border-top-width: 1px; border-top-style: solid;">
        <p style="margin: 0px 35px 20px 45px;">
          <span style="float: left;">
            <a style="color: rgb(204, 204, 204);"href="pwdmodi.jsp">
              forget password?
            </a>
          </span>
          <span style="float: right;">
           <a  class="active" data-toggle="modal"   data-target="#regModal" style="color: rgb(204, 204, 204); margin-right: 10px;"href="register.jsp">
              register
            </a>
            <input type="submit" name="submit" class="submit action-button" value="load" />
          </span>
        </p>
      </div>
    </div>
</form>
<div class="modal fade" id="regModal" tabindex="-1" role="dialog"
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
        User name<input type="text" id="regname"/>
        Password<input type="password" id="regpwd"/>
        Email<input type="text" id="regemail"/>
        <button onclick="re"></button>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default"
                data-dismiss="modal">Close
        </button>
        <button type="button" class="btn btn-primary" id="sub" onclick = "showRegister()">
          Submit
        </button>
      </div>
    </div>
  </div>
</div>
</body>
<script src="UI/js/jquery-2.1.4.js"></script>
<script src="UI/bootstrap/dist/css/bootstrap-theme.min.css"></script>
<script src="UI/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="UI/js/mycommon.js"></script>
<script src="UI/js/book.js"></script>
</html>
