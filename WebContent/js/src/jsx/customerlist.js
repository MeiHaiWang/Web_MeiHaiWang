$(function(){
	var ButtonToolbar = ReactBootstrap.ButtonToolbar;
	var Button = ReactBootstrap.Button;
	var Label = ReactBootstrap.Label;
	var CustomerList = React.createClass({
	    getDefaultProps() {
	        return {
	          customer_list: ['']
	        };
	      },
	    getInitialState() {
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
	    render() {
	    	var index = 1;
	      var customer = this.state.customer_list.map(function(customer) {
	        return <tr><td>{index++}</td>
	        <td>{customer.t_user_name}</td>
	        <td>{customer.t_user_phoneNumber}</td>
	        <td>{customer.t_user_age}</td>
	        <td>{customer.t_user_gender}</td>
	        <td>{customer.t_stylist_name}</td>
	        <td>{customer.t_reservation_memo}</td>
	        <td>{customer.salon_traffic}</td>
	        <td>{customer.total_payment}</td>
	        <td>
	        <Label bsStyle="success">前回：{customer.t_reservation_previous}</Label>
	        <Label bsStyle="danger">次回：{customer.t_reservation_next}</Label>
	        </td>
	        </tr>;
	      });
	      return (
	  	    	/*
	  	    	 * 顧客一覧	
	  No,	顧客名	携帯番号	年齢層	性別	担当者	備考	来店数	消費額	予約状況	
	  	    	 */
	        <div>
        	  <table>
	            <tr><th>No.</th><th style={style1.container}>顾客名</th><th>电话号码</th><th>年龄层</th><th>性別</th><th>专人</th><th>记录</th><th>到店数</th><th>消費額</th><th>预约状況</th></tr>	
	            {customer}
	            <tr></tr>
	          </table>
	        </div>
	      );
	    }
	  });
	  
	var style1 = {
			  container: {
			    backgroundColor: "#ddd",
			    width: 150
			  }
			}
	
	  var component_customer_list = React.render(<CustomerList />, document.getElementById('customer_list_info'));
	
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
					  cList[i].t_user_gender="男士";
				  }else if(cList[i].t_user_gender==1){
					  cList[i].t_user_gender="女士";
				  }
			  }
		  })();
		  console.log("customerList number: "+cList.length);
		  component_customer_list.setState({customer_list: cList}); 
//		  console.log(customer_list.user_lists.length);
//		  component_customer_list.setState({customer_list: customer_list.user_lists}); 
	  }	  

});
