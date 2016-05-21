/**
 * Created by lyn on 16-5-2.
 */
/**
 * 新书上架
 */
function addbook() {
    ajax("bookaction","post",{
        operation:"addbook",
        id:$("#bookid1").val(),
        name:$("#bookname1").val(),
        auth:$("#bookauth1").val(),
        num:$("#booknum1").val(),
        price:$("#bookprice1").val(),
        type:$("#booktype1").val()
    });    
}
/**
 * 查询书
 */
function querybook()
{
    ajax("bookaction","post",
        {
            operation:"querybook",
            bookISDN:$("#bid").val(),
            bookName:$("#bname").val()

        },
        function(jsonStr)
        {
            var json=JSON.parse(jsonStr)
            displayBook(json);
            changeClick(json);
        });
}

/**
 * 显示书
 * @param paperArray
 */
function displayBook(paperArray) {

    var out="<tr><td>ISBN</td><td>书名</td><td>作者</td><td>分类</td><td>库存</td><td>价格</td><td>操作</td></tr>";
    var len=0;
    if(paperArray!=undefined)
        len=paperArray.length;
    for(var i=0;i<len;i++)
    {
        out+=("<tr><td>"+paperArray[i].bookIsdn+"</td><td>"
        +paperArray[i].bookName+"</td><td>"+paperArray[i].bookAuth
        +"</td><td>"+paperArray[i].bookType+"</td><td>"+paperArray[i].bookNum
        +"</td><td>"+paperArray[i].bookPrice+
        "</td><td><button type='button' class='btn btn-default' id='change3' onclick=''>×</button>&nbsp;&nbsp;" +
        "<button type='button' class='btn btn-default' data-toggle='modal' data-target='#modiModal' id='change4' onclick = ''><span class='glyphicon glyphicon-pencil'></span></button></td></tr>");
    }
    $("#querybody").html(out);

}
function changeClick(paperArray) {
    var len=0;
    if(paperArray!=undefined)
        len=paperArray.length;
    for(var i=0;i<len;i++)
    {

        $("#querybody").find("#change3").eq(i).attr("onclick","delBook('"+paperArray[i].bookIsdn+"')");
        $("#querybody").find("#change4").eq(i).attr("onclick","trans('"+paperArray[i].bookIsdn+"')");


    }

}
/**
 * 下架书
 * @param bookid
 */
function delBook(bookid)
{
    ajax("bookaction","post",{
        operation:"delBook",
        bookISDN:bookid
    },function () {
        $("#queryModal").modal("hide");
    });
}
function trans(bookid)
{
    $('#queryModal').modal('hide');
    var paperHtml = $("#modibody");
    paperHtml.find("#isbn2").html(bookid);
    
}
/**
 * 修改书的信息
 */
function modiBook() 
{
    ajax("bookaction","post",{
        operation:"modiBook",
        bookid:$("#isbn2").text(),
        price:$("#price2").val(),
        num:$("#num2").val(),
        type:$("#type2").val()
    },function () {
        $("#modiModal").modal("hide");

    });
       
}
/**
 * 查询用户
 */
function queryuser()
{
    ajax("useraction","post",{
        operation:"queryuser",
        userName:$("#uame").val()
    },function (jsonStr) {
        var jsonArr=JSON.parse(jsonStr);
        displayUsers(jsonArr);
        changeRemove(jsonArr);
    });

}

function displayUsers(paperArray) {

    var out="<tr><td>ID</td><td>用户名</td><td>密码</td><td>邮箱</td><td>操作</td></tr>";
    var len=0;
    if(paperArray!=undefined)
        len=paperArray.length;
    for(var i=0;i<len;i++)
    {
        out+=("<tr><td>"+paperArray[i].userId+"</td><td>"
        +paperArray[i].userName+"</td><td>"+paperArray[i].userPwd+"</td><td>"+paperArray[i].userEmail
        +"</td><td><button type='button' class='btn btn-default' id='change5' onclick = ''>×</button></td></tr>");
    }
    $("#userbody").html(out);

}
function changeRemove(paperArray) {
    var len=0;
    if(paperArray!=undefined)
        len=paperArray.length;
    for(var i=0;i<len;i++)
    {

        $("#userbody").find("#change5").eq(i).attr("onclick","delUser('"+paperArray[i].userId+"')");
    }

}
/**
 * 删除用户
 * @param userId
 */
function delUser(userId)
{
    $('#userModal').modal('hide');
    ajax("useraction","post",
        {
            operation:"delUser",
            id:userId
        });
}
function displayStatic(paperArray) {

    var out="<tr><td>订单编号</td><td>用户ID</td><td>书本ISBN</td><td>数量</td><td>时间</td></tr>";
    var len=0;
    if(paperArray!=undefined)
        len=paperArray.length;
    for(var i=0;i<len;i++)
    {
        out+=("<tr><td>"+paperArray[i].infoId+"</td><td>"+paperArray[i].userId+"</td><td>"
        +paperArray[i].bookIsdn+"</td><td>"+paperArray[i].buyNum
        +"</td><td>"+paperArray[i].time+"</td></tr>");
    }
    $("#staticbody").html(out);

}

function dataByUser() {
    ajax("infoaction","post",{
        operation:"showData",
        username:$("#udname").val()
    },function (data) {
        var jsonArr=JSON.parse(data);
        displayStatic(jsonArr);
    });

}
function dataByTime() {
    ajax("infoaction","post",{
        operation:"dataByTime",
        beginTime:$("#begin").val(),
        endTime:$("#end").val()
    },function (data) {
        var jsonArr=JSON.parse(data);
        displayStatic(jsonArr);
    });
    
}
function showStatic() {
    ajax("infoaction","post",{
        operation:"showStatic",
        username:$("#udname").val(),
        beginTime:$("#begin").val(),
        endTime:$("#end").val(),
        bookType:$("#btype").val()
    },function (data) {
        var jsonArr=JSON.parse(data);
        displayStatic(jsonArr);
        
    });
    
}
