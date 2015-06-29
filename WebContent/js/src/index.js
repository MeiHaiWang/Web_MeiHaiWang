$(function(){

    $.ajax({
        type: "POST",
        url: API_PATH + "GetCheckSessionAction.java",
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
    return function() {
        var data = {
            mail_address: mail_address,
            password: password
        };

        $.ajax({
            type: "POST",
            url: API_PATH + "CheckLoginAction.java",
            data: data,
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
