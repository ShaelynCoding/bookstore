<%@ page contentType="text/html;charset=gb2312" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0" />
    <title>Bookstore</title>
    <link href="UI/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        html,body {
            height: 100%;
        }
        .box {
            filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#6699FF', endColorstr='#6699FF'); /*  IE */
            background-image:linear-gradient(bottom, #6699FF 0%, #6699FF 100%);
            background-image:-o-linear-gradient(bottom, #6699FF 0%, #6699FF 100%);
            background-image:-moz-linear-gradient(bottom, #6699FF 0%, #6699FF 100%);
            background-image:-webkit-linear-gradient(bottom, #6699FF 0%, #6699FF 100%);
            background-image:-ms-linear-gradient(bottom, #6699FF 0%, #6699FF 100%);

            margin: 0 auto;
            position: relative;
            width: 100%;
            height: 100%;
        }
        .login-box {
            width: 100%;
            max-width:500px;
            height: 400px;
            position: absolute;
            top: 50%;

            margin-top: -200px;
            /*设置负值，为要定位子盒子的一半高度*/

        }
        @media screen and (min-width:500px){
            .login-box {
                left: 50%;
                /*设置负值，为要定位子盒子的一半宽度*/
                margin-left: -250px;
            }
        }

        .form {
            width: 100%;
            max-width:500px;
            height: 275px;
            margin: 25px auto 0px auto;
            padding-top: 25px;
        }
        .login-content {
            height: 300px;
            width: 100%;
            max-width:500px;
            background-color: rgba(255, 250, 2550, .6);
            float: left;
        }


        .input-group {
            margin: 0px 0px 30px 0px !important;
        }
        .form-control,
        .input-group {
            height: 40px;
        }

        .form-group {
            margin-bottom: 0px !important;
        }
        .login-title {
            padding: 20px 10px;
            background-color: rgba(0, 0, 0, .6);
        }
        .login-title h1 {
            margin-top: 10px !important;
        }
        .login-title small {
            color: #fff;
        }

        .link p {
            line-height: 20px;
            margin-top: 30px;
        }
        .btn-sm {
            padding: 8px 24px !important;
            font-size: 16px !important;
        }
    </style>


</head>

<body>

<div class="box">
    <div class="text-right"><br>
        <a onclick="switchLanguage('zh')" style="color: #3c3c3c">中文</a>&nbsp;
        <a onclick="switchLanguage('en')" style="color: #3c3c3c">English</a>&nbsp;&nbsp;&nbsp;
    </div>
    <div class="login-box">
        <div class="login-title text-center">
            <h1><small class="Login_Text">登录</small></h1>
        </div>
        <div class="login-content ">
            <div class="form">

                    <div class="form-group">
                        <div class="col-xs-12  ">
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                                <input type="text" id="username" name="username" class="form-control Username_Text" placeholder="用户名">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12  ">
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                                <input type="password" id="password" name="password" class="form-control Password_Text" placeholder="密码">
                            </div>
                        </div>
                    </div>
                    <div class="form-group form-actions">
                        <div class="col-xs-4 col-xs-offset-4 ">
                            <button class="btn btn-sm btn-info " onclick="login()">
                                <span class="glyphicon glyphicon-off"></span>&nbsp;&nbsp;
                                <nobr class="Login_Text">登录</nobr>
                            </button>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-6 link">
                            <p class="text-center remove-margin" data-toggle="modal"   data-target="#modiModal" >
                                <small class="PsdModi_Text">修改密码</small>
                            </p>
                        </div>
                        <div class="col-xs-6 link">
                            <p class="text-center remove-margin" data-toggle="modal"   data-target="#regModal">
                                <small class="Register_Text">注册</small>
                            </p>
                        </div>
                    </div>

            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="regModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header Register_Text">
                注册
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
            </div>
            <div class="modal-body" >
                <ul><nobr class="col-md-3 Username_Text">用户名</nobr><input type="text" id="regname"/></ul>
                <ul><nobr class="col-md-3 Password_Text">密码 </nobr><input type="password" id="regpwd"/></ul>
                <ul><nobr class="col-md-3 Email_Text">邮件 </nobr><input type="text" id="regemail"/></ul>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary Submit_Text" onclick = "register()">
                    提交
                </button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="modiModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header PsdModi_Text">
                修改密码
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
            </div>
            <div class="modal-body" >
                <ul><nobr class="col-md-3 Username_Text" st>用户名</nobr><input type="text" id="modiname"/></ul>
                <ul><nobr class="col-md-3 OldPsd_Text">原密码</nobr><input type="password" id="oldpwd"/></ul>
                <ul><nobr class="col-md-3 NewPsd_Text">新密码</nobr><input type="password" id="newpwd"/></ul>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary Submit_Text" onclick = "modiPwd()">
                    提交
                </button>
            </div>
        </div>
    </div>
</div>
</body>
<script src="UI/js/jquery-2.1.4.js"></script>
<script src="UI/bootstrap/dist/css/bootstrap-theme.min.css"></script>
<script src="UI/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="logic/common.js"></script>
<script src="logic/user.js"></script>
<script>
    $(document).ready(function () {
       switchLanguage("en");
    });
        

</script>
</html>