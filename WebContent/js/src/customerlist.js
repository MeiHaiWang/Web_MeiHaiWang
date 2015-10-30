$(function(){
	var ButtonToolbar = ReactBootstrap.ButtonToolbar;
	var Button = ReactBootstrap.Button;
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
	        React.createElement("td", null, customer.total_payment), 
	        React.createElement("td", null, 
	        React.createElement(ButtonToolbar, null, 
	        React.createElement(Button, {className: "previous"}, "前回"), 
	        React.createElement(Button, {className: "next"}, "次回")
	        )
	        )
	        );
	      });
	      return (
	  	    	/*
	  	    	 * 顧客一覧	
	  No,	顧客名	携帯番号	年齢層	性別	担当者	備考	来店数	消費額	予約状況	
	  	    	 */
	        React.createElement("div", null, 
        	  React.createElement("table", null, 
	            React.createElement("tr", null, React.createElement("th", null, "No."), React.createElement("th", {style: style1.container}, "顾客名"), React.createElement("th", null, "电话号码"), React.createElement("th", null, "年龄层"), React.createElement("th", null, "性別"), React.createElement("th", null, "专人"), React.createElement("th", null, "记录"), React.createElement("th", null, "到店数"), React.createElement("th", null, "消費額"), React.createElement("th", null, "预约状況")), 	
	            customer, 
	            React.createElement("tr", null)
	          )
	        )
	      );
	    }
	  });
	  
	var style1 = {
			  container: {
			    backgroundColor: "#ddd",
			    width: 150
			  }
			}
	
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
	  
	  var cList = customer_list.user_lists;
	  if(cList.length>0){
		  (function(){
			  for(var i=0;i<cList.length;i++){
				  if(cList[i].t_user_gender==0){
					  cList[i].t_user_gender="男性";
				  }else if(cList[i].t_user_gender==1){
					  cList[i].t_user_gender="女性";
				  }
			  }
		  })();
		  console.log("customerList number: "+cList.length);
		  component_customer_list.setState({customer_list: cList}); 
//		  console.log(customer_list.user_lists.length);
//		  component_customer_list.setState({customer_list: customer_list.user_lists}); 
	  }	  

});
