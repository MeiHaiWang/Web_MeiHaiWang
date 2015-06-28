const API_PATH = '/cgi-bin/';

// セッション情報を取得する関数
var getSessionInfo = (function(){
    return function() {
        var response = $.ajax({
            type: "POST",
            url: API_PATH + "GetCheckSessionAction.java",
            async: false,
        }).responseText;

        response = JSON.parse(response);
        return response;
    };
})();

// サロン情報を取得する関数
var getSalonInfo = (function(data){
    return function(data) {
        var response = $.ajax({
            type: "POST",
            url: API_PATH + "GetSalonInfoAction.java",
            async: false,
            data: data,
        }).responseText;

        response = JSON.parse(response);
        return response;
    };
})();

// サロン情報を登録する関数
var setSalonInfo = (function(data){
    return function(data) {
        var response = $.ajax({
            type: "POST",
            url: API_PATH + "SetSalonInfoAction.java",
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
            url: API_PATH + "GetCountryAreaListAction.java",
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

// Areaの集合からnameと一致するidを取得する関数
var getAreaIdByAreaName = (function(area_name, areas){
    return function(area_name, areas) {
        for (var i = 0; i < areas.length; i++) {
            if (areas[i].t_area_name == area_name) {
                return areas[i].t_area_id;
            }
        }
        return null;
    };
})();

// スタッフ情報を取得する関数
var getStaffInfo = (function(data){
    return function(data) {
        var response = $.ajax({
            type: "POST",
            url: API_PATH + "GetStaffInfoAction.java",
            async: false,
            data: data,
        }).responseText;

        response = JSON.parse(response);
        return response;
    };
})();

// スタッフ情報を登録する関数
var setStaffInfo = (function(data){
    return function(data) {
        var response = $.ajax({
            type: "POST",
            url: API_PATH + "SetStaffInfoAction.java",
            async: false,
            data: data,
        }).responseText;

        response = JSON.parse(response);
        return response;
    };
})()

// スタッフ情報を削除する関数
var deleteStaffInfo = (function(data){
    return function(data) {
        var response = $.ajax({
            type: "POST",
            url: API_PATH + "DeleteStaffInfoAction.java",
            async: false,
            data: data,
        }).responseText;

        response = JSON.parse(response);
        return response;
    };
})();

// スタッフのサービス情報を登録する関数
var setStaffMenu = (function(data){
    return function(data) {
        var response = $.ajax({
            type: "POST",
            url: API_PATH + "SetStaffMenuAction.java",
            async: false,
            data: data,
        }).responseText;

        response = JSON.parse(response);
        return response;
    };
})()

// スタッフのサービス情報を削除する関数
var deleteStaffMenu = (function(data){
    return function(data) {
        var response = $.ajax({
            type: "POST",
            url: API_PATH + "DeleteStaffMenuAction.java",
            async: false,
            data: data,
        }).responseText;

        response = JSON.parse(response);
        return response;
    };
})();


// MysqlのDateTime型から年月日のオブジェクトを取得する関数
var getYearMonthDayByDateTime = (function(date_time){
    return function(date_time) {
        var tmp = date_time.split(' ');
        var tmp_ymd = tmp[0].split('-');
        return {'year': tmp_ymd[0], 'month': tmp_ymd[1], 'day': tmp_ymd[2]};
    };
})();

// サービス情報を取得する関数
var getServiceList = (function(data){
    return function(data) {
        var response = $.ajax({
            type: "POST",
            url: API_PATH + "GetServiceListAction.java",
            async: false,
            data: data,
        }).responseText;

        response = JSON.parse(response);
        return response;
    };
})();


// サービス情報を取得する関数
var getMenuInfo = (function(data){
    return function(data) {
        var response = $.ajax({
            type: "POST",
            url: API_PATH + "GetMenuInfoAction.java",
            async: false,
            data: data,
        }).responseText;

        response = JSON.parse(response);
        return response;
    };
})();

// サービス情報を登録する関数
var setMenuInfo = (function(data){
    return function(data) {
        var response = $.ajax({
            type: "POST",
            url: API_PATH + "SetMenuInfoAction.java",
            async: false,
            data: data,
        }).responseText;

        response = JSON.parse(response);
        return response;
    };
})();

// アルバム情報を削除する関数
var deleteMenuInfo = (function(data){
    return function(data) {
        var response = $.ajax({
            type: "POST",
            url: API_PATH + "DeleteMenuInfoAction.java",
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
            url: API_PATH + "GetServiceCategoryListAction.java",
            async: false,
        }).responseText;

        response = JSON.parse(response);
        return response;
    };
})();

// クーポン情報を取得する関数
var getCouponInfo = (function(data){
    return function(data) {
        var response = $.ajax({
            type: "POST",
            url: API_PATH + "GetCouponInfoAction.java",
            async: false,
            data: data,
        }).responseText;

        response = JSON.parse(response);
        return response;
    };
})();

// クーポン情報を登録する関数
var setCouponInfo = (function(data){
    return function(data) {
        var response = $.ajax({
            type: "POST",
            url: API_PATH + "SetCouponInfoAction.java",
            async: false,
            data: data,
        }).responseText;

        response = JSON.parse(response);
        return response;
    };
})();

// クーポン情報を削除する関数
var deleteCouponInfo = (function(data){
    return function(data) {
        var response = $.ajax({
            type: "POST",
            url: API_PATH + "DeleteCouponInfoAction.java",
            async: false,
            data: data,
        }).responseText;

        response = JSON.parse(response);
        return response;
    };
})();

// クーポン種別一覧を取得する関数
var getCouponKindList = (function(){
    return function() {
        var response = $.ajax({
            type: "POST",
            url: API_PATH + "GetCouponKindListAction.java",
            async: false,
        }).responseText;

        response = JSON.parse(response);
        return response;
    };
})();

// アルバム情報を取得する関数
var getAlbumInfo = (function(data){
    return function(data) {
        var response = $.ajax({
            type: "POST",
            url: API_PATH + "GetAlbumInfoAction.java",
            async: false,
            data: data,
        }).responseText;

        response = JSON.parse(response);
        return response;
    };
})();

// アルバム情報を登録する関数
var setAlbumInfo = (function(data){
    return function(data) {
        var response = $.ajax({
            type: "POST",
            url: API_PATH + "SetAlbumInfoAction.java",
            async: false,
            data: data,
        }).responseText;

        response = JSON.parse(response);
        return response;
    };
})();

// アルバム情報を削除する関数
var deleteAlbumInfo = (function(data){
    return function(data) {
        var response = $.ajax({
            type: "POST",
            url: API_PATH + "DeleteAlbumInfoAction.java",
            async: false,
            data: data,
        }).responseText;

        response = JSON.parse(response);
        return response;
    };
})();

// スタイリスト一覧情報を取得する関数
var getStylistList = (function(data){
    return function(data) {
        var response = $.ajax({
            type: "POST",
            url: API_PATH + "GetStylistListAction.java",
            async: false,
            data: data,
        }).responseText;

        response = JSON.parse(response);
        return response;
    };
})();

// ヘアスタイルタイプ一覧を取得する関数
var getHairTypeList = (function(){
    return function() {
        var response = $.ajax({
            type: "POST",
            url: API_PATH + "GetHairTypeListAction.java",
            async: false,
        }).responseText;

        response = JSON.parse(response);
        return response;
    };
})();

// 地図情報を取得する関数
var getMapInfo = (function(data){
    return function(data) {
        var response = $.ajax({
            type: "POST",
            url: API_PATH + "GetMapInfoAction.java",
            async: false,
            data: data,
        }).responseText;

        response = JSON.parse(response);
        return response;
    };
})();

// 地図情報を登録する関数
var setMapInfo = (function(data){
    return function(data) {
        var response = $.ajax({
            type: "POST",
            url: API_PATH + "SetMapInfoAction.java",
            async: false,
            data: data,
        }).responseText;

        response = JSON.parse(response);
        return response;
    };
})()

// リロードボタン押下時
$('#reload_button').on('click', function() {
    window.location.reload();
});
