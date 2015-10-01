$(function(){
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
	        </tr>;
	      });
	      return (
	  	    	/*
	  	    	 * 顧客一覧	
	  No,	顧客名	携帯番号	年齢層	性別	担当者	備考	来店数	消費額	予約状況	
	  	    	 */
	        <div>
	          <table>
	          	<thead>
	          	<tr><th>本日の予約一覧</th></tr>
	            </thead>
	            <tbody>
	            <tr><th>服务名称</th><th>Time</th><th>Price</th><th>予約</th></tr>
	            <tr><td>No.</td><td>顧客名</td><td>携帯番号</td><td>年齢層</td><td>性別</td><td>担当者</td><td>備考</td><td>来店数</td><td>消費額</td><td>メニュー</td><td>予約状況</td></tr>	
	            {customer}
	            <tr></tr>
	            </tbody>
	          </table>
	        </div>
	      );
	    }
	  });
	  
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
	  console.log(customer_list.user_lists.length);
	  component_customer_list.setState({customer_list: customer_list.user_lists}); 
	  
});
