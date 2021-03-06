// セッション情報を取得する関数
var getSessionInfo = (function(){
    return function() {
        var response = $.ajax({
            type: "POST",
            url: API_PATH + "checkSession",
            async: false,
            dataType: "text",
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
            url: API_PATH + "getSalonInfo",
            async: false,
            dataType: "text",
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
            url: API_PATH + "setSalonInfo",
            async: false,
            dataType: "text",
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
            url: API_PATH + "getCountryAreaList",
            async: false,
            dataType: "text",
        }).responseText;
        response = JSON.parse(response);

        return response;
    };
})();

// CountryIDからAreaの集合を取得する関数
var getAreasByCountryId = (function(country_area_info, area_id){
    return function(country_area_info, area_id) {
        for (var i = 0; i < country_area_info.length; i++) {
            if (country_area_info[i].t_area_id == area_id) {
                return country_area_info[i].area_slave;
            }
        }
    };
})();

// SlaveAreaNameからAreaIdを取得する関数
var getAreaNameBySlaveAreaName = (function(area_info, area_name){
    return function(area_info, area_name) {
        for (var i = 0; i < area_info.length; i++) {
            for (var j = 0; j < area_info[i].area_slave.length; j++) {
                if (area_info[i].area_slave[j].t_area_name == area_name) {
                    return area_info[i].t_area_name;
                }
            }
        }
        return null;
    };
})();

//SlaveAreaIdからParentAreaNameを取得する関数
var getAreaNameBySlaveAreaId = (function(area_info, area_id){
    return function(area_info, area_id) {
        for (var i = 0; i < area_info.length; i++) {
            for (var j = 0; j < area_info[i].area_slave.length; j++) {
                if (area_info[i].area_slave[j].t_area_id == area_id) {
                    return area_info[i].t_area_name;
                }
            }
        }
        return null;
    };
})();

// AreaからAreaSlaveの集合を取得する関数
var getSlaveAreasByAreaName = (function(area_info, area_name){
    return function(area_info, area_name) {
        for (var i = 0; i < area_info.length; i++) {
            if (area_info[i].t_area_name == area_name) {
                return area_info[i].area_slave;
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
            url: API_PATH + "getStaffInfo",
            async: false,
            data: data,
            dataType: "text",
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
            url: API_PATH + "setStaffInfo",
            async: false,
            data: data,
            dataType: "text",
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
            url: API_PATH + "deleteStaffInfo",
            async: false,
            data: data,
            dataType: "text",
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
            url: API_PATH + "setStaffMenu",
            async: false,
            data: data,
            dataType: "text",
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
            url: API_PATH + "deleteStaffMenu",
            async: false,
            data: data,
            dataType: "text",
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
            url: API_PATH + "getServiceList",
            async: false,
            dataType: "text",
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
            url: API_PATH + "getMenuInfo",
            async: false,
            dataType: "text",
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
            url: API_PATH + "setMenuInfo",
            async: false,
            dataType: "text",
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
            url: API_PATH + "deleteMenuInfo",
            async: false,
            dataType: "text",
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
            url: API_PATH + "getServiceCategoryList",
            async: false,
            dataType: "text",
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
            url: API_PATH + "getCouponInfo",
            async: false,
            dataType: "text",
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
            url: API_PATH + "setCouponInfo",
            async: false,
            dataType: "text",
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
            url: API_PATH + "deleteCouponInfo",
            async: false,
            dataType: "text",
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
            url: API_PATH + "getCouponKindList",
            async: false,
            dataType: "text",
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
            url: API_PATH + "getAlbumInfo",
            async: false,
            dataType: "text",
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
            url: API_PATH + "setAlbumInfo",
            async: false,
            dataType: "text",
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
            url: API_PATH + "deleteAlbumInfo",
            async: false,
            dataType: "text",
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
            url: API_PATH + "getStylistList",
            async: false,
            dataType: "text",
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
            url: API_PATH + "getHairTypeList",
            async: false,
            dataType: "text",
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
            url: API_PATH + "getMapInfo",
            async: false,
            dataType: "text",
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
            url: API_PATH + "setMapInfo",
            async: false,
            dataType: "text",
            data: data,
        }).responseText;
        response = JSON.parse(response);

        return response;
    };
})()

// 画像をアップロードしファイルURLを取得する関数
var uploadImage = (function(data){
    return function(data) {
        var response = $.ajax({
            type: "POST",
            url: API_PATH + "uploadImage",
            async: false,
            processData: false,
            data: data,
            dataType: 'text',
            contentType: false,
        });
        response = JSON.parse(response);
        return response;
    };
})()

// 検索情報を取得する関数
var getSearchConditionList = (function(data){
    return function(data) {
        var response = $.ajax({
            type: "POST",
            url: API_PATH + "getSearchConditionList",
            async: false,
            dataType: "text",
            data: data,
        }).responseText;
        response = JSON.parse(response);

        return response;
    };
})();

//画像をアップロードフォルダから削除する関数
var deleteImage = (function(data){
    return function(data) {
        var response = $.ajax({
            type: "POST",
            url: API_PATH + "deleteImage",
            async: false,
            dataType: "text",
            data: data,
        }).responseText;
        response = JSON.parse(response);

        return response;
    };
})();

//予約情報を登録する関数
var setReservationInfo = (function(data){
    return function(data) {
        var response = $.ajax({
            type: "POST",
            url: API_PATH + "setReservationInfo",
            async: false,
            dataType: "text",
            data: data,
        }).responseText;
        response = JSON.parse(response);

        return response;
    };
})();

//予約情報を登録する関数
var getReservationInfo = (function(data){
    return function(data) {
        var response = $.ajax({
            type: "POST",
            url: API_PATH + "getReservationInfo",
            async: false,
            dataType: "text",
            data: data,
        }).responseText;
        response = JSON.parse(response);

        return response;
    };
})();

//予約情報一覧を取得する関数
var getReservationList = (function(data){
    return function(data) {
        var response = $.ajax({
            type: "POST",
            url: API_PATH + "getReservationList",
            async: false,
            dataType: "text",
            data: data,
        }).responseText;
        response = JSON.parse(response);

        return response;
    };
})();

//ユーザ予約一覧取得関数
var getUserReservationList = (function(data){
    return function(data) {
        var response = $.ajax({
            type: "POST",
            url: API_PATH + "getUserReservationList",
            async: false,
            dataType: "text",
            data: data,
        }).responseText;
        response = JSON.parse(response);

        return response;
    };
})();

//予約していた内容の終了関数
var setReservationFinish = (function(data){
    return function(data) {
        var response = $.ajax({
            type: "POST",
            url: API_PATH + "setReservationFinish",
            async: false,
            dataType: "text",
            data: data,
        }).responseText;
        response = JSON.parse(response);

        return response;
    };
})();

//予約したことある顧客一覧関数
var getCustomerList = (function(data){
    return function(data) {
        var response = $.ajax({
            type: "POST",
            url: API_PATH + "getCustomerList",
            async: false,
            dataType: "text",
            data: data,
        }).responseText;
        response = JSON.parse(response);

        return response;
    };
})();

//クレーム登録関数
var setClaim = (function(data){
    return function(data) {
        var response = $.ajax({
            type: "POST",
            url: API_PATH + "setClaim",
            async: false,
            dataType: "text",
            data: data,
        }).responseText;
        response = JSON.parse(response);

        return response;
    };
})();

//ユーザ情報取得関数
var getUserInfo = (function(data){
    return function(data) {
        var response = $.ajax({
            type: "POST",
            url: API_PATH + "getUserInfo",
            async: false,
            dataType: "text",
            data: data,
        }).responseText;
        response = JSON.parse(response);

        return response;
    };
})();

//ユーザ情報登録関数
var setRegistUser = (function(data){
    return function(data) {
        var response = $.ajax({
            type: "POST",
            url: API_PATH + "setRegistUser",
            async: false,
            dataType: "text",
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

// サニタイズ
var sanitaize = {
  encode : function (obj) {
    for(var key in obj){
      if(obj[key] instanceof Array || obj[key] instanceof Object){
        return arguments.callee(obj[key]);
      }
      else {
        if (obj[key] === null) {
          obj[key] = "";
        }
        else if (typeof obj[key] !== "undefined") {
          obj[key] = obj[key].toString();
          obj[key] = obj[key].replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, '&quot;').replace(/'/g, '&#39;');
        }
        else if (typeof obj[key] === "undefined") {
          obj[key] = "";
        }
      }
    }
    return obj;
  },

  decode : function (obj) {
    for(var key in obj){
      if(obj[key] instanceof Array || obj[key] instanceof Object){
        return arguments.callee(obj[key]);
      }
      else {
        if (typeof obj[key] !== "undefined") {
          obj[key] = obj[key].toString();
          obj[key] = obj[key].replace(/&lt;/g, '<').replace(/&gt;/g, '>').replace(/&quot;/g, '"').replace(/&#39;/g, '\'').replace(/&amp;/g, '&');
        }
      }
    }
    return obj;
  }
};
