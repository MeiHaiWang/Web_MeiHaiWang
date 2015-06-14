$(function(){

    var session_id = getSessionId();

    if (session_id !== null) {
        location.href = "./salon.html";
    }
});



// セッションIDを取得する関数
function getSessionId()
{
    var session_id = null;

    var cookies = new Array();
    cookies = document.cookie.split(";");

    var exp = new RegExp(" ", "g");

    // Cookieを分けてセッションIDだけを取得する
    for( var index = 0; index < cookies.length; index++ )
    {
        var cookie = cookies[index].split("=");
        cookie[0] = cookie[0].replace(exp, "");

        if( cookie[0] == "Session_id")
        {
            session_id = cookie[1];
            break;
        }
    }

    return session_id;
}