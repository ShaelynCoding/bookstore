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
        "</td><td><button type='button' class='btn btn-primary' id='change3' onclick=''>×</button></td><td>" +
        "<button type='button' class='btn btn-primary' data-toggle='modal' data-target='#modiModal' id='change4' onclick = ''><span class='glyphicon glyphicon-pencil'></span></button></td></tr>");
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
        +"</td><td><button type='button' class='btn btn-primary' id='change5' onclick = ''>×</button></td></tr>");
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

// function logout()
// {
//
//     $.post("logout",
//         {
//
//         });
// }
// function cateGUI()
// {
//     $.post("cateGUI",
//         {
//
//         },
//         function(data,status)
//         {
//
//             var strs = new Array();
//             var allType = new Array();
//             var all = new Array();
//             strs = data.split(";");
//             //alert(strs.length);
//             for (var i =0;i<strs.length-1;i++)
//             {
//                 var temp = strs[i].split(",");
//                 allType[i] = temp[0];
//                 //alert(allType);
//                 all[i] = {value:parseInt(temp[1]),name:temp[0]};
//                 //alert(all);
//
//
//             }
//             var option = {
//                 tooltip : {
//                     trigger: 'item',
//                     formatter: "{a} <br/>{b} : {c} ({d}%)"
//                 },
//                 legend: {
//                     orient : 'vertical',
//                     x : 'left',
//                     data:allType
//                 },
//                 toolbox: {
//                     show : true,
//                     feature : {
//                         mark : {show: true},
//                         dataView : {show: true, readOnly: false},
//                         magicType : {
//                             show: true,
//                             type: ['pie', 'funnel'],
//                             option: {
//                                 funnel: {
//                                     x: '25%',
//                                     width: '50%',
//                                     funnelAlign: 'center',
//                                     max: 1548
//                                 }
//                             }
//                         },
//                         restore : {show: true},
//                         saveAsImage : {show: true}
//                     }
//                 },
//                 calculable :false,
//                 series : [
//                     {
//                         name:'Book Category',
//                         type:'pie',
//                         radius : ['50%', '70%'],
//                         itemStyle : {
//                             normal : {
//                                 label : {
//                                     show : false
//                                 },
//                                 labelLine : {
//                                     show : false
//                                 }
//                             },
//                             emphasis : {
//                                 label : {
//                                     show : true,
//                                     position : 'center',
//                                     textStyle : {
//                                         fontSize : '15',
//                                         fontWeight : 'bold'
//                                     }
//                                 }
//                             }
//                         },
//                         data:all
//                     }
//                 ]
//             };
//             var myChart = echarts.init(document.getElementById('cate'));
//             myChart.setOption(option);
//
//         });
//
// }
//
//
// function datauser()
// {
//
//     $.post("datauser",
//         {
//
//             userName:$("#udname").val()
//
//         },
//         function(data,status)
//         {
//
//             $("#udatabody").html(data);
//         });
// }
// function datacate()
// {
//     $.post("datacate",
//         {
//
//             bookType:$("#btype").val()
//
//         },
//         function(data,status)
//         {
//             $("#catebody").html(data);
//
//         });
//
// }
//
// function dataTime()
// {
//     $.post("datatime",
//         {
//             beginTime:$("#begin").val(),
//             endTime:$("#end").val()
//
//         },
//         function(data,status)
//         {
//             $("#timebody").html(data);
//         });
// }
