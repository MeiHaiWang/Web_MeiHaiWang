$(function(){

    $.ajax({
        type: "POST",
        url: API_PATH + "checkSession",
        dataType: "text",
        success: function(response){
            response = JSON.parse(response);

            if (response.status === "false") {

            }
            else {
                location.href = "./salon.html";
            }
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
            dataType: "text",
            success: function(response){
                response = JSON.parse(response);

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
