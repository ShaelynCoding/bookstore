/**
 * Created by lyn on 16-5-2.
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
function querybook()
{
    ajax("bookaction","get",
        {
            operation:"querybook",
            bookISDN:$("#bid").val(),
            bookName:$("#bname").val()

        },
        function(jsonStr)
        {
            var json=JSON.parse(jsonStr)
            displayBook(json);
        });
}
function displayBook(paperArray) {
    var papersDoc = $("#querybody");
    var singlePaper = papersDoc.html();
    papersDoc.html("");
    var len = 0;
    if (paperArray != undefined)
        len = paperArray.length;
    for (var i = 0; i < len; i++) {
        papersDoc.append(singlePaper);
    }
    for (i = 0; i < len; i++) {
        var paperHtml = papersDoc.find(".bookShow").eq(i);
        paperHtml.find(".id").html(paperArray[i].bookIsdn);
        paperHtml.find(".name").html(paperArray[i].bookName);
        paperHtml.find(".num").html( paperArray[i].bookNum);
        paperHtml.find(".auth").html(paperArray[i].bookAuth);
        paperHtml.find(".type").html(paperArray[i].bookType);
        paperHtml.find(".price").html(paperArray[i].bookPrice);
        paperHtml.find("#change3").attr("onclick","delBook('"+paperArray[i].bookISDN+"')");
        paperHtml.find("#change4").attr("onclick","trans('"+paperArray[i].bookISDN+"')");
        
    }
}
function delBook(bookid)
{
    ajax("bookaction","post",{
        operation:"delBook",
        bookISDN:bookid
    });
}
function trans(bookid)
{
    $('#queryModal').modal('hide');
    var paperHtml = $("#modibody");
    paperHtml.find("#isbn2").html(bookid);
    
}
function modiBook() 
{
    ajax("modiBook","post",{
        bookid:$("#isbn2").text(),
        price:$("#price2").val(),
        num:$("#num2").val(),
        type:$("#type2").val()
    });
       
}
function queryuser()
{
    ajax("useraction","get",{
        operation:"queryuser",
        userName:$("#uame").val()
    },function (jsonStr) {
        var jsonArr=JSON.parse(jsonStr);
        
    });

}
function displayUser(paperArray) {
    var papersDoc = $("#userModal");
    var singlePaper = papersDoc.html();
    papersDoc.html("");
    var len = 0;
    if (paperArray != undefined)
        len = paperArray.length;
    for (var i = 0; i < len; i++) {
        papersDoc.append(singlePaper);
    }
    for (i = 0; i < len; i++) {
        var paperHtml = papersDoc.find(".userShow").eq(i);
        paperHtml.find(".id").html(paperArray[i].userId);
        paperHtml.find(".name").html(paperArray[i].userName);
        paperHtml.find(".password").html( paperArray[i].userPwd);
        paperHtml.find(".email").html( paperArray[i].userEmail);

        paperHtml.find("#change5").attr("onclick","delUser('"+paperArray[i].userId+"')");

    }
}

function delUser(userId)
{
    $('#userModal').modal('hide');
    ajax("useraction","get",
        {
            operation:"delUser",
            id:userId
        });
}

function logout()
{

    $.post("logout",
        {

        });
}
function cateGUI()
{
    $.post("cateGUI",
        {

        },
        function(data,status)
        {

            var strs = new Array();
            var allType = new Array();
            var all = new Array();
            strs = data.split(";");
            //alert(strs.length);
            for (var i =0;i<strs.length-1;i++)
            {
                var temp = strs[i].split(",");
                allType[i] = temp[0];
                //alert(allType);
                all[i] = {value:parseInt(temp[1]),name:temp[0]};
                //alert(all);


            }
            var option = {
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient : 'vertical',
                    x : 'left',
                    data:allType
                },
                toolbox: {
                    show : true,
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        magicType : {
                            show: true,
                            type: ['pie', 'funnel'],
                            option: {
                                funnel: {
                                    x: '25%',
                                    width: '50%',
                                    funnelAlign: 'center',
                                    max: 1548
                                }
                            }
                        },
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                calculable :false,
                series : [
                    {
                        name:'Book Category',
                        type:'pie',
                        radius : ['50%', '70%'],
                        itemStyle : {
                            normal : {
                                label : {
                                    show : false
                                },
                                labelLine : {
                                    show : false
                                }
                            },
                            emphasis : {
                                label : {
                                    show : true,
                                    position : 'center',
                                    textStyle : {
                                        fontSize : '15',
                                        fontWeight : 'bold'
                                    }
                                }
                            }
                        },
                        data:all
                    }
                ]
            };
            var myChart = echarts.init(document.getElementById('cate'));
            myChart.setOption(option);

        });

}


function datauser()
{

    $.post("datauser",
        {

            userName:$("#udname").val()

        },
        function(data,status)
        {

            $("#udatabody").html(data);
        });
}
function datacate()
{
    $.post("datacate",
        {

            bookType:$("#btype").val()

        },
        function(data,status)
        {
            $("#catebody").html(data);

        });

}

function dataTime()
{
    $.post("datatime",
        {
            beginTime:$("#begin").val(),
            endTime:$("#end").val()

        },
        function(data,status)
        {
            $("#timebody").html(data);
        });
}
