/**
 * 显示购物车
 */
var singlePaper="";
var single="";
function shopCart()
{
    ajax("cart","post",
        {
            operation:"showCart"
        },function (jsonStr) {
            var paperArray = eval("(" + jsonStr + ")");
            displayCart(paperArray);
            changeClick(paperArray);
        });

}
function displayCart(paperArray) {

    var out="<tr><td>ISBN</td><td>书名</td><td>数量</td><td>操作</td></tr>";


    var len=0;
    if(paperArray!=undefined)
        len=paperArray.length;
    for(var i=0;i<len;i++)
    {
        out+=("<tr><td>"+paperArray[i].bookISBN+"</td><td>"
        +paperArray[i].bookName+"</td><td>"+paperArray[i].buyNum
        +"</td><td><button type='button' class='btn btn-primary' id='remove' onclick = ''>×</button></td></tr>");
    }
    $("#cartbody").html(out);

}
function changeClick(paperArray) {
    var len=0;
    if(paperArray!=undefined)
        len=paperArray.length;
    for(var i=0;i<len;i++)
    {

        $("#cartbody").find("#remove").eq(i).attr("onclick","delCart('"+paperArray[i].bookISBN+"')");
    }

}
/**
 * 展示上架的书籍
 */
function showData()
{
    var cookie=getCookie("user");
    var tmp=cookie.split("@");
    // ajax("infoaction","post",{
    //     operation:"showData",
    //     username:tmp[0]
    // },function (data) {
    //     var jsonArr=JSON.parse(data);
    //     displayData(jsonArr);
    // });
    ajax("InfoServiceREST/showData/"+tmp[0],"GET",{

    },function (data) {
        var jsonArr=JSON.parse(data);
        displayData(jsonArr);
    });
}
function displayData(paperArray) {

    var out="<tr><td>订单编号</td><td>书本ISBN</td><td>数量</td><td>时间</td></tr>";


    var len=0;
    if(paperArray!=undefined)
        len=paperArray.length;
    for(var i=0;i<len;i++)
    {
        out+=("<tr><td>"+paperArray[i].infoId+"</td><td>"
        +paperArray[i].bookIsdn+"</td><td>"+paperArray[i].buyNum
        +"</td><td>"+paperArray[i].time+"</td></tr>");
    }
    $("#databody").html(out);

}
/**
 * 从购物车删除记录
 * @param bookid
 */
function delCart(bookid) {
    ajax("cart","post",{
        operation:"delCart",
        bookid:bookid
    },function (data) {
        alert(data);
        shopCart();
    });

}
/**
 * 查看某本书的细节
 * @param bookid
 */
function showDetail(bookid)
{
    var dataXml="<?xml version='1.0' encoding='UTF-8'?>\
        <soap:Envelope xmlns:soap='http://schemas.xmlsoap.org/soap/envelope/' \
         xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns:xsd='http://www.w3.org/2001/XMLSchema' xmlns:ser='service'>\
         <soap:Body><ser:showDetail><ser:bookISBN>"+bookid+"</ser:bookISBN></ser:showDetail></soap:Body></soap:Envelope>"
    alert("showDetail: "+bookid);
    // ajax("bookaction","post",{
    //     operation:"showDetail",
    //     bookISBN:bookid
    // },function(jsonStr){
    //     var json = eval("(" + jsonStr + ")");
    //     var papersDoc = $("#detailModal");
    //     var paperHtml=papersDoc.find("#detailbody");
    //     paperHtml.find(".title").html("书名： "+json.bookName);
    //     paperHtml.find(".ISBN").html("ISBN： "+json.bookIsdn);
    //     paperHtml.find(".author").html("作者： "+json.bookAuth);
    //     paperHtml.find(".type").html("类型： "+json.bookType);
    //     paperHtml.find(".remain").html("库存： "+json.bookNum);
    //     paperHtml.find(".price").html("价格： "+json.bookPrice);
    //     paperHtml.find("#change2").attr("onclick","addCart('"+json.bookIsdn+"')");
    //
    //
    // });
    $.ajax({
        url:"BookServiceSOAP",
        type:"post",
        dataType:"xml",
        data:dataXml,
        complete:showBookDetail,
        contentType:"text/xml;charset='utf-8'"
    });
}
function showBookDetail(xmlHttpRequest,status)
{
    //alert(xmlHttpRequest.responseText);
    var jsonStr=$(xmlHttpRequest.responseXML).find('return').text();
    var json = eval("(" + jsonStr + ")");
    var papersDoc = $("#detailModal");
    var paperHtml=papersDoc.find("#detailbody");
    paperHtml.find(".title").html("书名： "+json.bookName);
    paperHtml.find(".ISBN").html("ISBN： "+json.bookIsdn);
    paperHtml.find(".author").html("作者： "+json.bookAuth);
    paperHtml.find(".type").html("类型： "+json.bookType);
    paperHtml.find(".remain").html("库存： "+json.bookNum);
    paperHtml.find(".price").html("价格： "+json.bookPrice);
    paperHtml.find("#change2").attr("onclick","addCart('"+json.bookIsdn+"')");

}
/**
 * 购买
 */
function buybook()
{
    var cookie=getCookie("user");
    var tmp=cookie.split("@");
    ajax("cart","post",{
          operation:"buybook",
          username:tmp[0]
      },function () {
          $("#cartModal").modal("hide");


          //$("#thank").html(data);
    });
}
/**
 * 添加到购物车
 * @param bookid
 */
function addCart(bookid) {

    ajax("cart","post",{
        operation:"addCart",
        bookISBN:bookid,
        buyNum:$("#buyNum").val()

    },function () {
        //alert(data);
        $("#detailModal").modal("hide");

    });

}
/**
 * 显示上架的书
 * @param paperArray
 */
function displayBooks(paperArray) {
    //init
    var papersDoc = $("#books").find("tbody");
    var len=0;
    if(paperArray!=undefined) len=paperArray.length;
    var out="";
    for(var i=0;i<len;i++)
    {
        out+=("<tr><td>"+paperArray[i].bookName+"</td><td>"
        +paperArray[i].bookAuth+"</td><td>"+paperArray[i].bookPrice
        +"</td><td><div class='active' data-toggle='modal'   data-target='#detailModal'>" +
        "<button type='button' class='btn btn-default btn-sm' onclick='showDetail(\""+paperArray[i].bookIsdn
        +"\")'>&nbsp;&nbsp;&nbsp;&nbsp;查看详情 &nbsp;&nbsp;&nbsp;&nbsp;</button></td></tr>");
    }
    papersDoc.html(out);
}
function searchBook() {
    var cookie=getCookie("user");
    var tmp=cookie.split("@");
    ajax("bookaction","post",{
        operation:"searchBook",
        search:$("#search").val(),
        searchChoice:$("#searchChoice").val(),
        role:tmp[1]
    },function (data) {
        var jsonArr=JSON.parse(data);
        displayBooks(jsonArr);
    });
    
}
