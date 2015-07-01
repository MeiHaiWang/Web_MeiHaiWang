$(function(){

    $.ajax({
        type: "POST",
        url: API_PATH + "checkSession",
        dataType: "text",
        success: function(response){
            response = JSON.parse(response);

            if (response.status === "false") {
                location.href = "./index.html";
            }
            else {
            }
        },
        error: function() {
            location.href = "./index.html";
        }
    });
});
