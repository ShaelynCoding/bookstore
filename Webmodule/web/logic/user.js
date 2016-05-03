/**
 * Created by lyn on 16-5-3.
 */
function login()
{
    ajax("useraction","get",{
        operation:"login",
        userName:$("#username").val(),
        userPwd:$("#password").val()
    },function (role) {
        alert(role);
        // setCookie("user",role,"d1","/");
        //
        // if(role.indexOf("admin"))
        //     self.location="manage.jsp";
        // else self.location="bookstore.jsp";
        
    },function () {
        alert("error");
    });
}
function register() {
    ajax("useraction","get",{
        operation:"register",
        regName:$("#regname").val(),
        regPwd:$("#regpwd").val(),
        regEmail:$("#regemail").val()
    },function (data) {
        alert(data);
        $("#regModal").modal("hide");

    });

}
function modiPwd() {
    ajax("useraction","get",{
        operation:"modiPwd",
        modiName:$("#modiname").val(),
        oldPwd:$("#oldpwd").val(),
        newPwd:$("#newpwd").val()
    },function (data) {
        alert(data);
        $("#modiModal").modal("hide");
    })

}
function logout() {
    setCookie("user","","s0","/");
    ajax("useraction","post",{
        operation:"logout"
    },function () {
        self.location="index.jsp";

    })
}
function getUserName() {
    ajax("useraction","get",{
        operation:"getUserName"
    },
    function (data) {
        return data;
        
    });
    
}