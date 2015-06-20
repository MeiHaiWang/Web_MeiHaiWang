$(function(){

    $.ajax({
        type: "POST",
        url: "./checkSession",
        success: function(response){
            response = JSON.parse(response);

            if (response.status === "false") {
                location.href = "./index.html";
            }
            else {
            }
        }
    });
});
