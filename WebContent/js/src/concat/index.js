$(function(){

    var session_id = getSessionId();

    if (session_id === null) {
        location.href = "./index.html";
    }
});
