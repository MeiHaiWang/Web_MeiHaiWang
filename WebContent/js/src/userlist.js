$(function(){
	var ReservationList = React.createClass({displayName: "ReservationList",
	    getDefaultProps:function() {
	        return {
	          reservation_list: ['']
	        };
	      },
	    getInitialState:function() {
	      return {
	        reservation_list: [{
	            "t_reservation_id": "",
	            "t_reservation_date": "", 
	            "t_user_name" : "",
	            "t_user_gender":"",
	            "t_stylist_name":"",
	            "t_menu_name":"",
	            "t_seat_name":"",
	            "t_reservation_isFinished":"",
	            "t_reservation_memo":""
	          }]
	      };
	    },
	    render:function() {
	    	var index = 1;
	      var reservation = this.state.reservation_list.map(function(reservation) {
	        return React.createElement("tr", null, React.createElement("td", null, index++), 
	        React.createElement("td", null, reservation.t_reservation_date), 
	        React.createElement("td", null, reservation.t_user_name), 
	        React.createElement("td", null, reservation.t_user_gender), 
	        React.createElement("td", null, reservation.t_stylist_name), 
	        React.createElement("td", null, reservation.t_menu_name), 
	        React.createElement("td", null, reservation.t_seat_name), 
	        React.createElement("td", null, reservation.t_reservation_memo)
	        );
	      });
	      return (
	        React.createElement("div", null, 
	          React.createElement("table", null, 
	          	React.createElement("thead", null, 
	          	React.createElement("tr", null, React.createElement("th", null, "本日の予約一覧"))
	            ), 
	            React.createElement("tbody", null, 
	            React.createElement("tr", null, React.createElement("th", null, "服务名称"), React.createElement("th", null, "Time"), React.createElement("th", null, "Price"), React.createElement("th", null, "予約")), 
	            React.createElement("tr", null, React.createElement("td", null, "No."), React.createElement("td", null, "予約日時"), React.createElement("td", null, "顧客名"), React.createElement("td", null, "性別"), React.createElement("td", null, "担当者名"), React.createElement("td", null, "メニュー"), React.createElement("td", null, "座席"), React.createElement("td", null, "備考")), 	
	            reservation, 
	            React.createElement("tr", null)
	            )
	          )
	        )
	      );
	    }
	  });
	  
	  var component_reservation_list = React.render(React.createElement(ReservationList, null), document.getElementById('reservation_list_info'));
	
	  // セッションIDからサービス情報を取得する
	  var session_info = getSessionInfo();
	  //パラメータ
	  var parameter_info = getParam();
	  //パラメータ扱いやすいようにする関数
		function getParam() {
		    var url   = location.href;
		    parameters    = url.split("?");
		    params   = parameters[1].split("&");
		    var paramsArray = [];
		    for ( i = 0; i < params.length; i++ ) {
		        neet = params[i].split("=");
		        paramsArray.push(neet[0]);
		        paramsArray[neet[0]] = neet[1];
		    }
		    return paramsArray;
		    /*
		    var categoryKey = paramsArray["key"];
		    return categoryKey;
		    */
		}
	  var date = parameter_info["date"];
	  var res_list = getReservationList({
		　 t_reservation_salonId : session_info.t_hairSalonMaster_salonId,
		　 t_reservation_date: date
	  });
	  console.log(res_list.reservation_list.length);
	  component_reservation_list.setState({reservation_list: res_list.reservation_list}); 
	  
});
