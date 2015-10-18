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
	            React.createElement("tr", null, React.createElement("th", null, "No."), React.createElement("th", null, "予約日時"), React.createElement("th", null, "顧客名"), React.createElement("th", null, "性別"), React.createElement("th", null, "担当者名"), React.createElement("th", null, "メニュー"), React.createElement("th", null, "座席"), React.createElement("th", null, "備考")), 	
	            reservation
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
	  
	  var rList = res_list.reservation_list;

	  if(rList.length>0){
		  (function(){
			  for(var i=0;i<rList.length;i++){
				  if(rList[i].t_user_gender==0){
					  rList[i].t_user_gender="男性";
				  }else if(rList[i].t_user_gender==1){
					  rList[i].t_user_gender="女性";
				  }
			  }
		  })();
		  /*
		  console.log("Reservation Number: "+res_list.reservation_list.length);
		  component_reservation_list.setState({reservation_list: res_list.reservation_list});
		  */ 
		  console.log("Reservation Number: "+rList.length);
		  component_reservation_list.setState({reservation_list: rList});
	  }
	  
});
