$(function(){
	
    $.ajax({
        type: "POST",
        url: API_PATH + "getCountryAreaList",
        dataType: "json",
        success: function(response){

           
        }
    });

    // エンターキー押下時
    $('#mail_address, #password').keypress(function(e) {
        if ( e.which == 13 ) {
            loginSystem($('#mail_address').val(), $('#password').val());
        }
    });

    // ログインボタン押下時
    $('#login_button').on('click', function() {
        loginSystem($('#mail_address').val(), $('#password').val());
    });

});


var loginSystem = (function(mail_address, password){
    return function(mail_address, password) {
        var data = {
            mail_address: mail_address,
            password: password
        };

        $.ajax({
            type: "POST",
            url: API_PATH + "checkLogin",
            data: data,
            dataType: "json",
            success: function(response){
                if (response.result === "false") {
                    alert("Login faild.");
                }
                else {
                    location.href = "./salon.html";
                }
            }
        });
    };
})();
