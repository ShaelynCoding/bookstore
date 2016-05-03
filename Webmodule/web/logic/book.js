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
    alert("showDetail: "+bookid);
    ajax("bookaction","post",{
        operation:"showDetail",
        bookISBN:bookid
    },function(jsonStr){
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


    });
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
    var papersDoc = $("#books");
    single = single=="" ? papersDoc.html() : single;
    papersDoc.html("");
    var len = 0;
    if (paperArray != undefined)
        len = paperArray.length;
    for (var i = 0; i < len; i++) {
        papersDoc.append(single);
    }
    for (i = 0; i < len; i++) {
        var paperHtml = papersDoc.find(".bookInfo").eq(i);
            paperHtml.find(".title").html(paperArray[i].bookName);
        paperHtml.find(".price").html("价格：" + paperArray[i].bookPrice);
        paperHtml.find(".author").html("作者：" + paperArray[i].bookAuth);
        paperHtml.find("#change").attr("onclick","showDetail('"+paperArray[i].bookIsdn+"')");
        // var formDoc=$("#change2");
        // var form =formDoc .html();
        // form="<input type='hidden' name='bookISBN' value='"+paperArray[i].bookIsdn+"'"+form;
        // paperHtml.find("#change2").html(form);



    }
}

//-----------------------------------------

// function logout()
// {
//
//     $.post("logout",
//         {
//
//         });
// }
// function searchbook()
// {
//
//     $.post("searchbook",
//         {
//             searchContext:$("#search").val()
//         },
//         function(data,status)
//         {
//             $("#searchbody").html(data);
//         });
// }
// function showdata()
// {
//
//     $.post("showdata",
//         {
//         },
//         function(data,status)
//         {
//             $("#databody").html(data);
//         });
// }