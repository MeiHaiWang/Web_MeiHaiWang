$(function(){
	var CustomerList = React.createClass({displayName: "CustomerList",
	    getDefaultProps:function() {
	        return {
	          customer_list: ['']
	        };
	      },
	    getInitialState:function() {
	      return {
	        customer_list: [{
	        	　 "t_user_name":"",
	        	   "t_user_phoneNumber":"",
	        	   "t_user_age":"",
	        	   "t_user_gender":"",
	        	   "t_stylist_name":"",
	        	   "t_reservation_memo":"",
	        	   "salon_traffic":"",
	        	   "total_payment":""
	        	　}]
	      };
	    },
	    render:function() {
	    	var index = 1;
	      var customer = this.state.customer_list.map(function(customer) {
	        return React.createElement("tr", null, React.createElement("td", null, index++), 
	        React.createElement("td", null, customer.t_user_name), 
	        React.createElement("td", null, customer.t_user_phoneNumber), 
	        React.createElement("td", null, customer.t_user_age), 
	        React.createElement("td", null, customer.t_user_gender), 
	        React.createElement("td", null, customer.t_stylist_name), 
	        React.createElement("td", null, customer.t_reservation_memo), 
	        React.createElement("td", null, customer.salon_traffic), 
	        React.createElement("td", null, customer.total_payment)
	        );
	      });
	      return (
	  	    	/*
	  	    	 * 顧客一覧	
	  No,	顧客名	携帯番号	年齢層	性別	担当者	備考	来店数	消費額	予約状況	
	  	    	 */
	        React.createElement("div", null, 
	          React.createElement("table", null, 
	          	React.createElement("thead", null, 
	          	React.createElement("tr", null, React.createElement("th", null, "本日の予約一覧"))
	            ), 
	            React.createElement("tbody", null, 
	            React.createElement("tr", null, React.createElement("th", null, "服务名称"), React.createElement("th", null, "Time"), React.createElement("th", null, "Price"), React.createElement("th", null, "予約")), 
	            React.createElement("tr", null, React.createElement("td", null, "No."), React.createElement("td", null, "顧客名"), React.createElement("td", null, "携帯番号"), React.createElement("td", null, "年齢層"), React.createElement("td", null, "性別"), React.createElement("td", null, "担当者"), React.createElement("td", null, "備考"), React.createElement("td", null, "来店数"), React.createElement("td", null, "消費額"), React.createElement("td", null, "メニュー"), React.createElement("td", null, "予約状況")), 	
	            customer, 
	            React.createElement("tr", null)
	            )
	          )
	        )
	      );
	    }
	  });
	  
	  var component_customer_list = React.render(React.createElement(CustomerList, null), document.getElementById('customer_list_info'));
	
	  // セッションIDからサービス情報を取得する
	  var session_info = getSessionInfo();
	  /*
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
		}
		*/
	  //var date = parameter_info["date"];
	  var customer_list = getCustomerList({
		　 t_reservation_salonId : session_info.t_hairSalonMaster_salonId
	  });
	  console.log(customer_list.user_lists.length);
	  component_customer_list.setState({customer_list: customer_list.user_lists}); 
	  
});
