
function shopCart()
{
    ajax("cart","get",
        {
            operation:"showCart"
        },function (jsonStr) {
            var paperArray = eval("(" + jsonStr + ")");
            displayCart(paperArray);
        });

}
function displayCart(paperArray) {
    var papersDoc = $("#cartModal");
    var singlePaper = papersDoc.html();
    papersDoc.html("");
    var len = 0;
    if (paperArray != undefined)
        len = paperArray.length;
    for (var i = 0; i < len; i++) {
        papersDoc.append(singlePaper);
    }
    for (i = 0; i < len; i++) {
        var paperHtml = papersDoc.find(".bookCart").eq(i);
        paperHtml.find(".id").html(paperArray[i].bookISBN);
        paperHtml.find(".name").html(paperArray[i].bookName);
        paperHtml.find(".num").html( paperArray[i].buyNum);
        paperHtml.find("#remove").attr("onclick","delCart('"+paperArray[i].bookISBN+"')");


    }

}
function cartInit() {
    ajax("cart","post",{
        operation:"cartInit"
    });

}
function delCart(bookid) {
    ajax("cart","get",{
        operation:"delCart",
        bookid:bookid
    },function (data) {
        alert(data);
        shopCart();
    });

}
function showDetail(bookid)
{
    alert("showDetail: "+bookid);
    ajax("bookaction","get",{
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
function buybook()
{
    
  ajax("cart","post",{
      operation:"buybook"
  },function (data) {
      $("#thank").html(data);
  });
}
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

function displayBooks(paperArray) {
    //init
    var papersDoc = $("#books");
    var singlePaper = papersDoc.html();
    papersDoc.html("");
    var len = 0;
    if (paperArray != undefined)
        len = paperArray.length;
    for (var i = 0; i < len; i++) {
        papersDoc.append(singlePaper);
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

function logout()
{

    $.post("logout",
        {

        });
}
function searchbook()
{

    $.post("searchbook",
        {
            searchContext:$("#search").val()
        },
        function(data,status)
        {
            $("#searchbody").html(data);
        });
}
function showdata()
{

    $.post("showdata",
        {
        },
        function(data,status)
        {
            $("#databody").html(data);
        });
}