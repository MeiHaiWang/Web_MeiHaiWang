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

// サロン情報を取得する関数
var getSalonInfo = (function(data){
    return function() {
        var response = $.ajax({
            type: "POST",
            url: "./getSalonInfo",
            async: false,
            data: data,
        }).responseText;

        response = JSON.parse(response);
        return response;
    };
})();

// 国・地域情報を取得する関数
var getCountryAreaList = (function(){
    return function() {
        var response = $.ajax({
            type: "POST",
            url: "./getCountryAreaList",
            async: false,
        }).responseText;

        response = JSON.parse(response);
        return response;
    };
})();

// CountryIDからAreaの集合を取得する関数
var getAreasByCountryId = (function(country_area_info, country_id){
    return function(country_area_info, country_id) {
        for (var i = 0; i < country_area_info.length; i++) {
            if (country_area_info[i].t_country_id == country_id) {
                return country_area_info[i].area;
            }
        }
    };
})();

// CountryNameからAreaの集合を取得する関数
var getAreasByCountryName = (function(country_area_info, country_name){
    return function(country_area_info, country_name) {
        for (var i = 0; i < country_area_info.length; i++) {
            if (country_area_info[i].t_country_name == country_name) {
                return country_area_info[i].area;
            }
        }
        return new Array();
    };
})();

// サービス情報を取得する関数
var getMenuInfo = (function(data){
    return function() {
        var response = $.ajax({
            type: "POST",
            url: "./getMenuInfo",
            async: false,
            data: data,
        }).responseText;

        response = JSON.parse(response);
        return response;
    };
})();

// サービスカテゴリ一覧を取得する関数
var getServiceCategoryList = (function(){
    return function() {
        var response = $.ajax({
            type: "POST",
            url: "./getServiceCategoryList",
            async: false,
        }).responseText;

        response = JSON.parse(response);
        return response;
    };
})();

// クーポン情報を取得する関数
var getCouponInfo = (function(data){
    return function() {
        var response = $.ajax({
            type: "POST",
            url: "./getCouponInfo",
            async: false,
            data: data,
        }).responseText;

        response = JSON.parse(response);
        return response;
    };
})();

// リロードボタン押下時
$('#reload_button').on('click', function() {
    window.location.reload();
});
