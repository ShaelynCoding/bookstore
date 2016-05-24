/**
 * Created by lyn on 16-5-3.
 */
/**
 * login
 */
function login()
{
    ajax("useraction","post",{
        operation:"login",
        username:$("#username").val(),
        password:$("#password").val()
    },function (role) {
        alert(role);
        setCookie("user",role,"d1","/");
        var i=role.indexOf("admin");
        if(role.indexOf("admin")>=0)
            self.location="manage.jsp";
        else self.location="bookstore.jsp";
        
    });
}
/**
 * 注册
 */
function register() {
    ajax("useraction","post",{
        operation:"register",
        regName:$("#regname").val(),
        regPwd:$("#regpwd").val(),
        regEmail:$("#regemail").val()
    },function (data) {
        alert(data);
        $("#regModal").modal("hide");

    });

}
/**
 * 修改密码
 */
function modiPwd() {
    ajax("useraction","post",{
        operation:"modiPwd",
        modiName:$("#modiname").val(),
        oldPwd:$("#oldpwd").val(),
        newPwd:$("#newpwd").val()
    },function (data) {
        alert(data);
        $("#modiModal").modal("hide");
    })

}
/**
 * 注销登录
 */
function logout() {
    setCookie("user","","s0","/");
    ajax("useraction","post",{
        operation:"logout"
    },function () {
        
        self.location="index.jsp";

    })
}
/**
 * 获取登录用户名
 * @returns {*}
 */
function getUserName() {
    var cookie=getCookie("user");
    var tmpArr=new Array();
    tmpArr=cookie.split("@");
    return tmpArr[0];

}
function switchLanguage(str) {
    ajax("International","post",{
        operation:"switchLanguage",
        Language:str
    },function (jsonStr) {
        var resources=JSON.parse(jsonStr);
        for(var key in resources) {
            var fill = $("." + key + "_Text");

            fill.attr("placeholder",resources[key]);
            fill.text(resources[key]);

        }
    });

}
