<%--
  Created by IntelliJ IDEA.
  User: lyn
  Date: 16-5-1
  Time: 下午4:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="UI/js/jquery-2.1.4.js"></script>
    <script src="UI/bootstrap/dist/css/bootstrap-theme.min.css"></script>
    <script src="UI/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="UI/js/mycommon.js"></script>
    <script src="UI/js/book.js"></script>
    <script>
        $(document).ready(function() {
            ajax("cart","get",
                    {
                operation:"showCart"
            },function (jsonStr) {
                var paperArray=JSON.parse(jsonStr);
                var papersDoc = $("#cartbody");
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
                    paperHtml.find(".remove").html()

                }


            });
        });
    </script>
</head>
<body>

    <table class = "table table-bordered">
        <tr>
            <td>ISBN</td>
            <td>书名</td>
            <td>数量</td>
        </tr>
        <div id="cartbody">
            <tr class="bookCart">
                <td class="id"></td>
                <td class="name"></td>
                <td class="num"></td>
                <td class="remove"></td>
            </tr>
        </div>
    </table>

</body>

</html>
