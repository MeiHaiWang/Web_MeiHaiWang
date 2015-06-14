// セッション情報を取得する関数
var getSessionInfo = (function(){
    return function() {
        var response = $.ajax({
            type: "POST",
            url: "./checkSession",
            async: false,
        }).responseText;

        response = JSON.parse(response);

        return response;
    };
})();

