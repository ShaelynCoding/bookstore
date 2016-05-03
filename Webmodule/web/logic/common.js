
/**
 * callback function demo for successful ajax
 * @param json : response information
 */
function defaultSuccess(json)
{
    alert("success!  " + (typeof json == "string" ? json : JSON.stringify(json)));
}


/**
 * callback function demo for bad ajax
 * @param error : response information
 */
function defaultError(error)
{
    alert("error " + error.responseText);
}

/**
 * 包装ajax
 * @param url
 * @param type
 * @param data
 * @param goodCallBack
 * @param errorCallBack
 * @param isAsync
 */
function ajax(url, type, data, goodCallBack, errorCallBack, isAsync)
{
    if (goodCallBack == undefined) goodCallBack = defaultSuccess;
    if (errorCallBack == undefined) errorCallBack = defaultError;
    if (isAsync == undefined) isAsync = true;

    $.ajax({
        type: type,
        url: url,
        //dataType: "json",
        data: data,
        async: isAsync,
        success: goodCallBack,  //set success callback function
        error: errorCallBack
    });
}

/**
 * 设置cookie
 * @param name
 * @param value
 * @param time
 * @param path
 */
function setCookie(name, value, time, path)
{
    var strsec = getSec(time);
    var exp = new Date();
    exp.setTime(exp.getTime() + strsec * 1);

    if (typeof value != 'string')
        value = JSON.stringify(value);

    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString() + ";path=" + path;
}


/**
 * 获取cookie
 * @param name
 * @returns cookie
 */
function getCookie(name)
{
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");  //or indexof


    if (arr = document.cookie.match(reg))
        return (unescape(arr[2]));
    else
        return null;
}


function getSec(str)
{
    var str1 = str.substring(1, str.length) * 1;
    var str2 = str.substring(0, 1);
    if (str2 == "s")
    {
        return str1 * 1000;
    }
    else if (str2 == "h")
    {
        return str1 * 60 * 60 * 1000;
    }
    else if (str2 == "d")
    {
        return str1 * 24 * 60 * 60 * 1000;
    }
}

