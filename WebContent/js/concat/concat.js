$(function(){

    $.ajax({
        type: "POST",
        url: API_PATH + "checkSession",
        dataType: "json",
        success: function(response){
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
