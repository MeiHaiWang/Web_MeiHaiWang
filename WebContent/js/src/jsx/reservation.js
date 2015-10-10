$(function(){
  /*
    Component for React
  */
  //ReactBootstrap
	var Input = ReactBootstrap.Input;
	
  // コンポーネントの定義
  var ReservationStartTime = React.createClass({
    getDefaultProps() {
      return {
        t_reservation_start: ['']
      };
    },
    getInitialState() {
      return {
        t_reservation_start: '',
      };
    },
    onChangeSelectValue(e) {
      this.setState({t_reservation_start: e.target.value});
    },
    render() {
    	return (
    			<Input type="text" value={this.state.t_reservation_start} onChange={this.changeText} />
    			);
	    }
	  });
  
  var ReservationEndTime = React.createClass({
	    getDefaultProps() {
	      return {
	        t_reservation_end: ['']
	      };
	    },
	    getInitialState() {
	      return {
	        t_reservation_end: '',
	      };
	    },
	    onChangeSelectValue(e) {
	      this.setState({t_reservation_end: e.target.value});
	    },
	    render() {
	    	return(
	        <Input type="text" value={this.state.t_reservation_end} onChange={this.changeText} disabled/>
	        );
	    }
	  });
  /*
  var ReservationUserInfo = React.createClass({
	    getDefaultProps() {
		      return {
		        t_user_id: ""
		      };
		    },
	    getInitialState() {
	      return {
    	    t_user_id: "",
	        t_user_tel: "",
	        t_user_name: "",
	        t_user_gender: "",
	        t_user_age: ""
	      };
	    },
	    changeText(e) {
	      this.setState({t_user_id: e.target.value});
	    },
	    render() {
	      return (
            <tr>
            <td>
              顧客No.:
            </td>
            <td>
		        <div>
		          <input type="text" value={this.state.t_user_tel} onChange={this.changeText} placeholder = "電話番号" />
		        </div>
		        <div>
		        	>>
              	</div>
              <button id = "search_button">検索</button>
            </td>
          </tr>
          <tr>
            <td>
              顧客名:
            </td>
            <td>
	            <div>
	            <input type="text" value={this.state.t_user_name} onChange={this.changeText} />
	          </div>
            </td>
          </tr>
          <tr>
            <td>
              年齢層＆性別:
            </td>
            <td>
		        <div>
		          <input type="text" value={this.state.t_user_age} onChange={this.changeText} />
		        </div>
		        <div>
		          <input type="text" value={this.state.t_user_gender} onChange={this.changeText} />
		        </div>
            </td>
          </tr>
	      );
	    }
	  });
   */
  
  var ReservationUserTel = React.createClass({
	    getInitialState() {
	      return {
	        t_user_tel: ""
	      };
	    },
	    changeText(e) {
	      this.setState({t_user_tel: e.target.value});
	    },
	    render() {
	      return (
	        <div>
	          <Input type="text" value={this.state.t_user_tel} onChange={this.changeText} placeholder = "電話番号" />
	        </div>
	      );
	    }
	  });
	  
  var ReservationUserName = React.createClass({
    getInitialState() {
      return {
    	  t_user_id: "",
        t_user_name: ""
      };
    },
    changeText(e) {
      this.setState({t_user_name: e.target.value});
    },
    render() {
      return (
        <div>
          <Input type="text" value={this.state.t_user_name} onChange={this.changeText} />
        </div>
      );
    }
  });

  /*
  var ReservationUserGender = React.createClass({
	    getInitialState() {
	      return {
	        t_user_gender: ""
	      };
	    },
	    changeText(e) {
	      this.setState({t_user_gender: e.target.value});
	    },
	    render() {
	      return (
	        <div>
	          <input type="text" value={this.state.t_user_gender} onChange={this.changeText} />
	        </div>
	      );
	    }
	  });
	*/
  
  //var SelectPicker = ReactBootstrap.SelectPicker;
  var ReservationUserGender = React.createClass({
	    getInitialState() {
	      return {
	        t_user_gender: '0'
	      };
	    },
	    onChangeSelectValue(e) {
	      //console.log(this.state.t_user_gender+","+e.target.value);
	      this.setState({t_user_gender: e.target.value});
	    },
	    render() {
	      return (
    	        <div>
    	          <select value={this.state.t_user_gender} onChange={this.onChangeSelectValue}>
    	          	<option value='0'>男性</option>
    	          	<option value='1'>女性</option>
    	          </select>
    	        </div>
	      );
	    }
	  });

  var ages = (function(){
	  var ages = [];
	  for(var i=10;i<=70;i++){
		  //i_ = ""+i;
		  //ages.push(i_);
		  ages.push(i);
		  //console.log(i_);
	  }
	  return ages;
  })();
  var ReservationUserAge = React.createClass({
	    getDefaultProps() {
	        return {
	          t_user_age: ages
	        };
	      },
	    getInitialState() {
	      return {
	        t_user_age: ""
	      };
	    },
	    changeText(e) {
	      this.setState({t_user_age: e.target.value});
	    },
	    render() {
	    	  var options = this.props.t_user_age.map(function(t_user_age) {
	    	      return <option value={t_user_age}>{t_user_age}</option>;
	    	    });
	    	  var options1 = 
	    		  <div>
		  	        <select value={this.state.t_user_age} onChange={this.onChangeSelectValue}>
			          {options}
			        </select>
			      </div>;
	    	    return (
	    	    	<div>
	    	    		{options1}
	    	    	</div>
	    	    );
	    }
	  });
  
/*
 * 	      return (
	        <div>
	          <Input type="text" value={this.state.t_user_age} onChange={this.changeText} />
	        </div>
	      );

 */
  
  var ReservationStylist = React.createClass({
    getDefaultProps() {
      return {
        stylist: ['']
      };
    },
    getInitialState() {
      return {
        t_stylist_id: '',
      };
    },
    onChangeSelectValue(e) {
      this.setState({t_stylist_id: e.target.value});
    },
    render() {
      var options = this.props.stylist.map(function(stylist) {
        return <option value={stylist.t_stylist_Id}>{stylist.t_stylist_name}</option>;
      });
      return (
        <div>
          <select value={this.state.t_stylist_id} onChange={this.onChangeSelectValue}>
            {options}
          </select>
        </div>
      );
    }
  });
  
  /*
  var ServiceCategory = React.createClass({
	    getDefaultProps() {
	      return {
	        category: ['']
	      };
	    },
	    getInitialState() {
	      return {
	        t_menu_categoryId: '',
	      };
	    },
	    onChangeSelectValue(e) {
	      // メニュー一覧
	      var menu_options = getMenuListOfCategory(e.target.value);
	      component_service_list.setState({"service_list":menu_options});
	      this.setState({t_menu_categoryId: e.target.value});
	    },
	    render() {
	      var options = this.props.category.map(function(category) {
	    	  var link = "#"+category.t_menuCategory_name;
	    	  return <li class="active"><a href={link} data-toggle="tab" value={category.t_menuCategory_categoryId}>{category.t_menuCategory_name}</a></li>;
	      });
	      return (
	    	<ul class="nav nav-tabs">
	            {options}
        	</ul>
	      );
	    }
	  });
	  */
  
  //var Tabs = ReactBootstrap.Tabs;
  //var Tab = ReactBootstrap.Tab;
  var Nav = ReactBootstrap.Nav;
  var Navbar = ReactBootstrap.Navbar;
  var NavItem = ReactBootstrap.NavItem;
  var Button = ReactBootstrap.Button;
  var ServiceCategory = React.createClass({
	    getDefaultProps() {
		      return {
		        category: ['']
		      };
		    },
	    getInitialState() {
	      return {
	        t_menu_categoryId: '',
	      };
	    },
	  handleSelect(e) {
	    //alert('selected ' + e);
	      // メニュー一覧
	      var menu_options = getMenuListOfCategory(e);
	      component_service_list.setState({"service_list":menu_options});
	      this.setState({t_menu_categoryId: e});
	  },
	  render() {
		  var category1 = [];
		  var category2 = [];
		  //console.log("a:"+this.props.category.length);
		  for(var i=0;i<this.props.category.length;i++){
			  //console.log(this.props.category[i].t_menuCategory_name);
			  if(i<6) category1.push(this.props.category[i]);
			  else category2.push(this.props.category[i]);
		  }
	      var options1 = category1.map(function(category1) {
	    	  return <NavItem bsSize="xsmall" eventKey={category1.t_menuCategory_categoryId}>
	    	  {category1.t_menuCategory_name}
	    	  </NavItem>;
	      });
	      var options2 = category2.map(function(category2) {
	    	  return <NavItem bsSize="xsmall" eventKey={category2.t_menuCategory_categoryId}>
	    	  {category2.t_menuCategory_name}
	    	  </NavItem>;
	      });
	    return (
		<div>
	    	<Navbar bsStyle="warning" >
				<Nav activeKey={this.state.t_menu_categoryId} onSelect={this.handleSelect} >
				{options1}
				</Nav>
		    	</Navbar>
		    	<Navbar bsStyle="warning" >
				<Nav activeKey={this.state.t_menu_categoryId} onSelect={this.handleSelect} >
				{options2}
				</Nav>
	    	</Navbar>
    	</div>
	    );
	  }
	});
 
  /*
   * 	      <Tabs activeKey={this.state.t_menu_categoryId} onSelect={this.handleSelect}>
	      	{options}
	      </Tabs>
		<Tab eventKey={category.t_menuCategory_categoryId} title={category.t_menuCategory_name}></Tab>;
   * 
   * <!-- タブの表示 -->
	<ul class="nav nav-tabs">
	    <li class="active"><a href="#menu1" data-toggle="tab">登録</a></li>
	    <li><a href="#menu2" data-toggle="tab">変更</a></li>
	    <li><a href="#menu3" data-toggle="tab">削除</a></li>
	</ul>

	<!-- Old -->
      var options = this.props.category.map(function(category) {
        return <option value={category.t_menuCategory_categoryId}>{category.t_menuCategory_name}</option>;
      });
      return (
        <div>
          <select value={this.state.t_menu_categoryId} onChange={this.onChangeSelectValue}>
            {options}
          </select>
        </div>
      );

   *
   */
  /*
  var Tabs = ReactBootstrap.Tabs;
  var Tab = ReactBootstrap.Tab;
  var ControlledTabs = React.createClass({
	  getInitialState() {
	    return {
	      key: 1
	    };
	  },

	  handleSelect(key) {
	    alert('selected ' + key);
	    this.setState({key});
	  },

	  render() {
	    return (
	      <Tabs activeKey={this.state.key} onSelect={this.handleSelect}>
	        <Tab eventKey={1} title="Tab 1">Tab 1 content</Tab>
	        <Tab eventKey={2} title="Tab 2">Tab 2 content</Tab>
	        <Tab eventKey={3} title="Tab 3" disabled>Tab 3 content</Tab>
	      </Tabs>
	    );
	  }
	});
  	*/
	//React.render(<ControlledTabs />, document.getElementById("test_tab"));
  
  var ServiceList = React.createClass({
    getInitialState() {
      return {
        service_list: [{
          "t_menu_categoryId": "",
          "t_menu_menuId": "",
          "t_menu_name": "",
          "t_menu_price": "",
          "t_menu_time": ""}]
      };
    },
    render() {
      var service = this.state.service_list.map(function(service) {
        return <tr><td>{service.t_menu_name}</td>
        <td>{service.t_menu_time}</td>
        <td>{service.t_menu_price}</td>
        <td><a className="reserve">予約</a></td></tr>;
      });
      return (
        <div>
          <table>
          	<thead>
            <tr><th>服务名称</th><th>Time</th><th>Price</th><th>予約</th></tr>
            </thead>
            <tbody>
            {service}
            <tr></tr>
            </tbody>
          </table>
        </div>
      );
    }
  });
  
  var ServiceList2 = React.createClass({
	    getInitialState() {
	      return {
	        service_list2: [{
	          "t_menu_menuId":"",
	          "t_menu_name": "",
	          "t_menu_start": "",
	          "t_menu_time": "",
	          "t_menu_stylist": "",
	          "t_menu_seat": "",
	          "t_menu_price":""
	        	  }]
	      };
	    },
	    render() {
	      var service = this.state.service_list2.map(function(service) {
	        return <tr><td>{service.t_menu_name}</td><td>{service.t_menu_start}</td><td>{service.t_menu_time}</td><td>{service.t_menu_stylist}</td><td>{service.t_menu_seat}</td><td><a className="delete">削除</a></td></tr>;
	      });
	      return (
	        <div>
	          <table>
	          	<thead>
	            <tr><th>服务名称</th><th>Start</th><th>Time</th><th>Stylist</th><th>Seat</th><th>削除</th></tr>
	            </thead>
	            <tbody>
	            {service}
	            </tbody>
	          </table>
	        </div>
	      );
	    }
	  });

  
	/*
  // set state to component
  function componentSetState(menu) {
    // コンポーネントにjsonを渡して関係する部分だけ書き換わる
    component_service_category.setState(menu);
    component_service_name.setState(menu);
    component_service_detail_text.setState(menu);
    component_service_price.setState(menu);
    component_service_image_path.setState(menu);
  }
  */


  /*
    List
  */
  // var service_category_info = getServiceCategoryList();
  // sanitaize.decode(service_category_info);

	/*
  // categoryを参照しやすい形に変換
  var categorys = new Array();
  for (var i = 0; i < service_category_info.category.length; i++) {
    var category_id = service_category_info.category[i].t_menuCategory_categoryId;
    var category_name = service_category_info.category[i].t_menuCategory_name;
    categorys[category_id] = category_name;
  }
	*/

  /*
    Component Render
  */
  // コンポーネントをエレメントに割り当てる
  var component_reservation_start = React.render(<ReservationStartTime />, document.getElementById('reservation_start'));
  var component_reservation_end = React.render(<ReservationEndTime />, document.getElementById('reservation_end'));
  var component_reservation_user_tel = React.render(<ReservationUserTel />, document.getElementById('reservation_user_tel'));
  var component_reservation_user_name = React.render(<ReservationUserName />, document.getElementById('reservation_user_name'));
  var component_reservation_user_sex = React.render(<ReservationUserGender />, document.getElementById('reservation_user_sex'));
  var component_reservation_user_age = React.render(<ReservationUserAge />, document.getElementById('reservation_user_age'));  
  var component_reservation_stylist = React.render(<ReservationStylist />, document.getElementById('reservation_stylist'));
  var component_service_category = React.render(<ServiceCategory />, document.getElementById('service_category'));
  var component_service_list = React.render(<ServiceList />, document.getElementById('service_list_info'));
  var component_service_list2 = React.render(<ServiceList2 />, document.getElementById('service_list_info2'));
  /*
  var component_service_name = React.render(<ServiceName />, document.getElementById('service_name'));
  var component_service_detail_text = React.render(<ServiceDetailText />, document.getElementById('service_detail_text'));
  var component_service_price = React.render(<ServicePrice />, document.getElementById('service_price'));
  var component_service_image_path = React.render(<ServiceImagePath />, document.getElementById('service_image_path'));
	*/

  /*
    Main Part
  */
  // セッションIDからサービス情報を取得する
  var session_info = getSessionInfo();
  //パラメータ
  var parameter_info = getParam();
  //var service_info = getMenuInfo(session_info.t_hairSalonMaster_salonId);
  //sanitaize.decode(service_info);

  //施述開始時間
  var reservation_start_time;
  var reservation_menu_start_time;
  //施述終了時間
  var reservation_end_time;
  var reserveId = -1;
  
  var reservationFlag = false;
  if(parameter_info['reservationId']!=null){
	  reservationFlag = true;
	  reserveId = parameter_info['reservationId'];
	  //console.log("Parameters: " + reserveId);
	  var reservationInfo = getReservationInfo({t_reservation_id:reserveId});
	  if(reservationInfo!=null){
		  //time
		  var param_time = reservationInfo.t_reservation_date;
		  console.log("param_time:"+reservationInfo.t_reservation_date);
		  var month = Number(moment(param_time).month()) + 1;
		 var p_date = moment(param_time).year() + "/" + month +"/"+ moment(param_time).date();
		  var hours = (Number(moment(param_time).hours())<10) ? '0'+moment(param_time).hours(): moment(param_time).hours();
		  var minutes = (Number(moment(param_time).minutes())<10) ? '0'+moment(param_time).minutes(): moment(param_time).minutes();
		 //var p_start_time = hours + ":" +minutes;
		  //var p_end_time = p_start_time;
		  reservation_start_time = hours + ":" +minutes;
		  reservation_menu_start_time = reservation_start_time;
		  reservation_end_time = reservation_start_time;
		  var param_stylistId = reservationInfo.t_reservation_stylistId;
		  //userInfo~
		  var userInfo = getUserInfo({t_user_tel: reservationInfo.t_reservation_userTel});
		  component_reservation_user_tel.setState({t_user_tel: userInfo.t_user_tel});
		  component_reservation_user_name.setState({
			  t_user_id: userInfo.t_user_id,
			  t_user_name: userInfo.t_user_name});
		  //var gender = getGenderStr(userInfo.t_user_gender);
		  //component_reservation_user_sex.setState({t_user_gender: gender});
		  component_reservation_user_sex.setState({t_user_gender: userInfo.t_user_gender});
		  component_reservation_user_age.setState({t_user_age: userInfo.t_user_age});
		  //reserved menus
		  var reserved_menus = reservationInfo.t_reservation_menuId;
	  }
  }else{
	  var param_time = parameter_info['time'];
	  var p_date = param_time.substring(0, 10);
	  //var p_start_time = param_time.substring(13, 18);
	  //var p_end_time = p_start_time;
	  reservation_start_time = param_time.substring(13, 18);
	  reservation_menu_start_time = reservation_start_time;
	  reservation_end_time = reservation_start_time;
	  //var p_end_time = Number(p_start_time.substring(0,2)) + 2 ;
	  var param_stylistId = parameter_info['stylistId'];
	  console.log("Parameters: "+p_date + " " + reservation_start_time + "-" +reservation_end_time +" ,"+param_stylistId);
  }

  //genderを文字列に
  function getGenderStr(genderId){
	  var gender = "";
    	if(genderId==0){
    		gender = "男性";
    	}else if(genderId==1){
    		gender = "女性";
    	}
    	return gender;
  }
  
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

	//Menu系
	  var service_category_info = getServiceCategoryList();
	  sanitaize.decode(service_category_info);
	  var category = service_category_info.category;
	  var menu_list = getMenuInfo({t_hairSalonMaster_salonId : session_info.t_hairSalonMaster_salonId});
	  sanitaize.decode(menu_list);
	  var menus = new Array();
	  for (var i = 0; i < menu_list.menu.length; i++) {
	    var menu_id = menu_list.menu[i].t_menu_id;
	    var menu_name = menu_list.menu[i].t_menu_name;
	    menus[menu_id] = menu_name;
	  }
	  //categoryIdにマッチするメニューを取得
	  var getMenuListOfCategory = function(t_category_id){
		  var ret = [];
		  //console.log("getMenuList:"+t_category_id);
		  for(var i=0; i<menu_list.menu.length; i++){
			  if(menu_list.menu[i].t_menu_categoryId == t_category_id){
				  ret.push({
				          "t_menu_categoryId": menu_list.menu[i].t_menu_categoryId,
				          "t_menu_menuId": menu_list.menu[i].t_menu_menuId,
				          "t_menu_name": menu_list.menu[i].t_menu_name,
				          "t_menu_price": menu_list.menu[i].t_menu_price,
				          "t_menu_time": menu_list.menu[i].t_menu_time
				  });
			  }
		  }
		  if(ret.length==0){
			  ret.push({
				          "t_menu_categoryId": "",
				          "t_menu_menuId": "",
				          "t_menu_name": "",
				          "t_menu_price": "",
				          "t_menu_time": ""
			  });
		  }
		  return ret;
	  }
	
	  // stylistを参照しやすい形に変換
	  var stylist_info = getStylistList({'t_hairSalonMaster_salonId':session_info.t_hairSalonMaster_salonId});
	  sanitaize.decode(stylist_info);
	  // stylistを参照しやすい形に変換
	  var stylists = new Array();
	  for (var i = 0; i < stylist_info.stylist.length; i++) {
	    var stylist_id = stylist_info.stylist[i].t_stylist_Id;
	    var stylist_name = stylist_info.stylist[i].t_stylist_name;
	    stylists[stylist_id] = stylist_name;
	  }

	  //予約終了時刻
	  function getEndTime(st,total_time){
    	//var start_time = component_reservation_start.state.t_reservation_start;
		  //var start_time = reservation_start_time;
		  var start_time = st;
    	var start_hours = Number(start_time.substring(0,2));
    	var start_minutes = Number(start_time.substring(3,5));
    	ope_hours = Math.floor(total_time/60);
    	ope_minutes = total_time%60;
    	var end_hours = start_hours+ope_hours;
    	var end_minutes = start_minutes+ope_minutes;
    	if(end_minutes >= 60){
    		end_minutes -= 60;
    		end_hours += 1;
    	}
    	var end_hours_str = (end_hours<10) ? '0'+end_hours: end_hours;
    	var end_minutes_str = (end_minutes<10) ? '0'+end_minutes: end_minutes;
    	var end_time = end_hours_str + ":" + end_minutes_str;
		  console.log("start_time="+start_time+", total_time="+total_time+", end_time="+end_time);
    	return end_time;
	  }
	  
  /*<!-- データピッカー2　予約日時設定 -->*/
  $('#date').datepicker({
  format: "yyyy/mm/dd",
  language: "ja",
  autoclose: true,
  orientation: "top auto"
  });
  
  // Component set. 
  $("#date").datepicker("setDate", p_date);
  component_reservation_start.setState({t_reservation_start: reservation_start_time});
  component_reservation_end.setState({t_reservation_end: reservation_end_time});
  component_reservation_stylist.setProps({stylist: stylist_info.stylist});
  component_reservation_stylist.setState({t_stylist_id: param_stylistId})
  component_service_category.setProps({category: service_category_info.category});
  component_service_category.setState({t_menu_categoryId: component_service_category.props.category[0].t_menuCategory_categoryId});
  var menu_options = getMenuListOfCategory(1);
  component_service_list.setState({"service_list":menu_options});

  //予約ボタンで選んだメニューリスト
  var reserve_menu_list = [];
  //Total
  var total_price = 0;
  var total_time  = 0;

  //予約されていた情報を配置
  if(reservationFlag){
	  var s_time = reservation_start_time;
	  var menu_ids =  reserved_menus.split(',');
	  for (var i = 0; i < menu_ids.length; i++) {
		  for (var j = 0; j < menu_list.menu.length; j++) {
			  if(menu_ids[i] == menu_list.menu[j].t_menu_menuId){
				  reserve_menu_list.push({
				    	"t_menu_menuId": menu_ids[i],
				        "t_menu_name": menu_list.menu[j].t_menu_name,
				        "t_menu_start": reservation_menu_start_time,
				        "t_menu_time": menu_list.menu[j].t_menu_time,
				        "t_menu_stylist": stylists[param_stylistId],
				        "t_menu_seat": "",
				        "t_menu_price": menu_list.menu[j].t_menu_price
				  });
			    total_price+=Number(menu_list.menu[j].t_menu_price);
			    total_time+=Number(menu_list.menu[j].t_menu_time);
			    var end = getEndTime(reservation_start_time,total_time);
			    reservation_menu_start_time = getEndTime(reservation_start_time, total_time);
			    component_reservation_end.setState({t_reservation_end: end});
			  }
		  }
	  }
	  component_service_list2.setState({"service_list2": reserve_menu_list});
  }

  //予約競合チェック conflictしてたらtrue
  function checkConflict(oneStart, oneEnd, checkStart, checkEnd){
	  var result = true;
	  var oneStart_hours = oneStart.substring(0,2);
	  var oneStart_minutes = oneStart.substring(3,5);
	  var oneEnd_hours = oneEnd.substring(0,2);
	  var oneEnd_minutes = oneEnd.substring(3,5);
	  var checkStart_hours = checkStart.substring(0,2);
	  var checkStart_minutes = checkStart.substring(3,5);
	  var checkEnd_hours = checkEnd.substring(0,2);
	  var checkEnd_minutes = checkEnd.substring(3,5);
	  
	  if(Number(oneStart_hours)<Number(checkStart_hours)){
		  if(Number(oneEnd_hours)<Number(checkStart_hours)){
			  result = false;
		  }else if(Number(oneEnd_hours)>Number(checkStart_hours)){
			  result = true;
		  }else if(Number(oneEnd_hours)==Number(checkStart_hours)){
			  if(Number(oneEnd_minutes)<Number(checkStart_minutes)){
				  result = false;
			  }else if(Number(oneEnd_minutes)>Number(checkStart_minutes)){
				  result = true;
			  }else if(Number(oneEnd_minutes)==Number(checkStart_minutes)){
				  result = false;
			  }
		  }
	  }else if(Number(oneStart_hours)>Number(checkStart_hours)){
		  if(Number(oneStart_hours)>Number(checkEnd_hours)){
			  return false;
		  }else if(Number(oneStart_hours)<Number(checkEnd_hours)){
			  return true;
		  }else if(Number(oneStart_hours)==Number(checkEnd_hours)){
			  if(Number(oneStart_minutes)>Number(checkEnd_minutes)){
				  result = false;
			  }else if(Number(oneStart_minutes)<Number(checkEnd_minutes)){
				  result = true;
			  }else if(Number(oneStart_minutes)==Number(checkEnd_minutes)){
				  result = false;
			  }
		  }
	  }else if(Number(oneStart_hours)==Number(checkStart_hours)){
		  result = true;
	  }

	  /*
	  var r_lists = getReservationList({
		　 t_reservation_salonId : session_info.t_hairSalonMaster_salonId,
		　 t_reservation_date: param_time
	  });
	  var r_list = r_lists.reservation_lists;
	  */
	  
	  return result;
  }
  
  
  // アカウント名を表示
  $('#account-name').text(session_info.t_hairSalonMaster_contactUserName);

  // メニュー一覧
  /*
  if (service_info.menu.length != 0) {
    component_service_list.setState({"service_list":service_info.menu});
  }
  */

  /*
    Button Handler
  */
  // 検索ボタン押下時
  $('#search_button').on('click', function() {
	  if(component_reservation_user_tel.state.t_user_tel!="" && component_reservation_user_tel.state.t_user_tel!=null){
		  //console.log("search button clicked."+component_reservation_user_tel.state.t_user_tel);
		  var user_info = getUserInfo({t_user_tel: component_reservation_user_tel.state.t_user_tel});
		  if(user_info.t_user_id>0){
			  //var gender = getGenderStr(user_info.t_user_gender);
			  //component_reservation_user_sex.setState({t_user_gender: gender});
			  component_reservation_user_sex.setState({t_user_gender: user_info.t_user_gender});
			  component_reservation_user_name.setState({t_user_id: user_info.t_user_id, 
				  t_user_name: user_info.t_user_name});
			  component_reservation_user_age.setState({t_user_age: user_info.t_user_age});
		  }else{
			  alert("Unregisted PhoneNumber");
		  }
  	}
  });
  
  // 右メニューの予約ボタン押下時
  $('.reserve').on('click', function() {
	  /*
	  if (album_info.album.length == 0) {
        return false;
      }
      */
    var id = $(".reserve").index(this);
	//var service_id = component_service_list.state.service_list[id].t_menu_menuId;
    //console.log("r_id:"+id);
    var exist=false;
    for(var i=0; i<reserve_menu_list.length; i++){
        if(reserve_menu_list[i].t_menu_menuId == component_service_list.state.service_list[id].t_menu_menuId){
        	exist = true;
        }
    }
    if(exist==false){
    	//console.log(reservation_menu_start_time);
    	//push
	    reserve_menu_list.push({
	    	"t_menu_menuId": component_service_list.state.service_list[id].t_menu_menuId,
	        "t_menu_name": component_service_list.state.service_list[id].t_menu_name,
	        "t_menu_start": reservation_menu_start_time,
	        "t_menu_time": component_service_list.state.service_list[id].t_menu_time,
	        "t_menu_stylist": stylists[component_reservation_stylist.state.t_stylist_id],
	        "t_menu_seat": "",
	        "t_menu_price": component_service_list.state.service_list[id].t_menu_price
	    });
	    total_price+=Number(component_service_list.state.service_list[id].t_menu_price);
	    total_time+=Number(component_service_list.state.service_list[id].t_menu_time);
	    //console.log(total_price+","+total_time);
	    var end = getEndTime(reservation_start_time, total_time);
	    reservation_menu_start_time = getEndTime(reservation_start_time, total_time);
	    component_reservation_end.setState({t_reservation_end: end});
    }
    component_service_list2.setState({"service_list2": reserve_menu_list});
  });

  // 削除ボタン押下時
  $('.delete').on('click', function() {
    var id = $(".delete").index(this);
    console.log("d_id:"+id);
    for(var i=0; i<reserve_menu_list.length; i++){
        if(reserve_menu_list[i].t_menu_menuId == component_service_list2.state.service_list2[id].t_menu_menuId){
        	console.log(reserve_menu_list[i].t_menu_name+","+component_service_list2.state.service_list2[id].t_menu_price);
    	    total_price = total_price-Number(component_service_list2.state.service_list2[id].t_menu_price);
    	    total_time = total_time-Number(component_service_list2.state.service_list2[id].t_menu_time);
        	reserve_menu_list.splice(i, 1);
        }
    }
    component_service_list2.setState({"service_list2": reserve_menu_list});
  });
  
  // 登録ボタン押下時
  $('#regist').on('click', function() {
	  //日付取得
	  var md = moment($('#date').datepicker('getDate'));
	  var mth = Number(md.month())+1;
	  var d = md.year() +"/" + mth +"/"+ md.date()+ 
		  " "+component_reservation_start.state.t_reservation_start;

	  //かかる時間
	 var m_time = 0;
	 for(var i=0; i<reserve_menu_list.length ; i++){
		 for(var j=0; j<menu_list.length; j++){
			 if(reserve_menu_list[i].t_menu_menuId == menu_list.menu[j].t_menu_id){
				 m_time += menu_list.menu[j].t_menu_time;
			 }
		 }
	 }
	  
	 if(reserveId < 0){
		 //競合チェック
		  var r_lists = getReservationList({
			　 t_reservation_salonId : session_info.t_hairSalonMaster_salonId,
			　 t_reservation_date: param_time
		  });
		  var r_list = [];
		  r_list = r_lists.reservation_lists;
		  if(r_list==undefined){
			  console.log("No reservation.");
		  }else{
			  for(var i=0; i<r_list.length; i++){
				  console.log(d.substring(0, 10)+","+r_list[i].t_reservation_date.substring(0, 10));
				  if(d.substring(0, 10)!=r_list[i].t_reservation_date.substring(0, 10)){
					  continue;
				  }
				  var representative = "";
				  for(var j=0; j<stylist_info.stylist.length; j++){
					  if(stylist_info.stylist[j].t_stylist_id == component_reservation_stylist.state.t_stylist_id){
						  representative = stylist_info.stylist[j].t_stylist_name;
					  }
				  }
				  if(representative != r_list.t_stylist_name){
					  continue;
				  }
				  var oneSt = r_list[i].t_reservation_date.substring(13, 18);
				  var oneEt = getEndTime(st, r_list[i].t_reservation_time);
				  var cSt = component_reservation_start.state.t_reservation_start;
				  var cEt = getEndTime(cSt ,m_time);
				  console.log(oneSt+","+ oneEt+","+ cSt+","+ cEt);
				  var result = checkConflict(oneSt, oneEt, cSt, cEt);
				  if(result){
					  return;
				  }
			  }
		  }
	 }
	  
	  //メニューリストをカンマつづりで取得
	  var ms = (function(){
		 var ms = "";
		 for(var i=0; i<reserve_menu_list.length ; i++){
			 ms += reserve_menu_list[i].t_menu_menuId + ",";
		 }
		 //console.log(ms + "," +ms.length);
		 if(ms.length>0){
			 ms = ms.substring(0, ms.length-1);
		 }
		 console.log(ms);
		 return ms;
	  });
	  
	  //ユーザidを発見できなかった場合は、ユーザを仮登録
	  var uid = (function(){
		  var uid = -1;
		  uid = component_reservation_user_name.state.t_user_id;
		  console.log("uid:"+uid);
		  if(uid == ""){
			  ui = setRegistUser(
				{
					t_user_name: component_reservation_user_name.state.t_user_name,
					t_user_gender: component_reservation_user_sex.state.t_user_gender,
					t_user_tel: component_reservation_user_tel.state.t_user_tel,
					t_user_age: component_reservation_user_age.state.t_user_age
				}	  
			  );
			  uid=ui.t_user_id;
			  //console.log("uid:"+uid.t_user_id);
		  }
		  return uid;
	  });

	  //サーブレット送信情報
    var data = {
      t_reservation_userId: uid,
    　 t_reservation_salonId: session_info.t_hairSalonMaster_salonId,
    　 t_reservation_stylistId: component_reservation_stylist.state.t_stylist_id,
    　 t_reservation_date: d,
    　 t_reservation_menuId: ms,
    　 t_reservation_seatId: 1,
      t_reservation_memo: $('#memo').text()　
     };

    // サニタイズ
    sanitaize.encode(data);

    var result = setReservationInfo(data);
    if (result.result == "true") {
      alert('Regist Success');
      window.location.reload();
    }
    else {
      alert('Regist Failed');
    }
  });

 $( function() {
	 /*
	  * http://alphasis.info/2011/06/jquery-ui-dialog-form/
	  */
	    $( 'button', '.jquery-ui-button' ).button();
	    $( '#jquery-ui-dialog-opener' ).click( function() {
	        $( '#jquery-ui-dialog' ).dialog( 'open' );
	    } );
	    var name = $( '#jquery-ui-dialog-form-name' );
	    var comment = $( '#jquery-ui-dialog-form-comment' );
	    $( '#jquery-ui-dialog' ).dialog( {
	        autoOpen: false,
	        width: 350,
	        show: 'explode',
	        hide: 'explode',
	        modal: true,
	        buttons: {
	            '登録': function() {
	                if ( comment . val() ) {
	               	 if(reserveId=>0){
	                	setClaimFunc(comment.val());
	               	 }
	                	$( this ).dialog( 'close' );
	                }
	                $( this ) . dialog( 'close' );
	            },
	            'キャンセル': function() {
	                $( this ) . dialog( 'close' );
	            },
	        }
	    } );
	} );
 
 	/*
 	 *    t_claim_reservationId,
		   t_claim_userId,
		　 t_claim_salonId,
		　 t_claim_menuId,
		   t_claim_date,
		　 t_claim_message
 	 */
 	function setClaimFunc(comment){
 		  //サーブレット送信情報
 	    var data = {
 	    	 t_claim_reservationId: reserveId,
 	 		 t_claim_userId: component_reservation_user_name.state.t_user_id,
 	 		 t_claim_salonId: session_info.t_hairSalonMaster_salonId,
 	 		　t_claim_menuId: 0,
 	 		 t_claim_date: param_time,
 	 		 t_claim_message: comment 
 	     };

 	    // サニタイズ
 	    sanitaize.encode(data);

 	    var result = setClaim(data);
 	    if (result.result == "true") {
 	      alert('Regist Success');
 	      window.location.reload();
 	    }
 	    else {
 	      alert('Regist Failed');
 	    }
 	}
  

  //予約終了関数呼び出し
  function finishReservation(flag){
	  if(reserveId < 0){
		  return;
	  }
	  
	  //サーブレット送信情報
	    var data = {
	    		   t_reservation_id: reserveId,
	    		     t_reservation_isFinished: flag
  		     };

	    // サニタイズ
	    sanitaize.encode(data);

	    var result = setReservationFinish(data);
	    if (result.result == "true") {
	      alert('Regist Success');
	      window.location.reload();
	    }
	    else {
	      alert('Regist Failed');
	    }
  }
 	
  // 来店ボタン押下時
  $('#come').on('click', function() {
	  finishReservation(1);
  });

  // 取り消しボタン押下時
  $('#cancel').on('click', function() {
	  $('#dialogdemo1').dialog('open');
  });
  $('#dialogdemo1').dialog({
	  /*
	   * http://jquery.keicode.com/ui/dialog.php
	   */
	  autoOpen: false,
	  title: '予約取消',
	  closeOnEscape: false,
	  modal: true,
	  buttons: {
	    "連絡あり": function(){
	    	finishReservation(2);
	      $(this).dialog('close');
	    },
	  "連絡なし": function(){
		  finishReservation(3);
	      $(this).dialog('close');
	    }
	  }
	});
 	
  /*
  // 編集ボタン押下時
  $('.edit').on('click', function() {
    if (service_info.menu.length == 0) {
      return false;
    }

    // component set state
    var id = $(".edit").index(this);
    componentSetState(service_info.menu[id]);
  });

  // 削除ボタン押下時
  $('.delete').on('click', function() {
    if (service_info.menu.length == 0) {
      return false;
    }

    var id = $(".delete").index(this);
    var data = {t_menu_menuId: service_info.menu[id].t_menu_menuId};

    // サニタイズ
    sanitaize.encode(data);

    var result = deleteMenuInfo(data);
    if (result.result == "true") {
      alert('Delete Success');
      window.location.reload();
    }
    else {
      alert('Delete Failed');
    }
  });
  */

  /*
  // 画像アップロード
  $('#service_image').change(function() {
    // ファイルが選択されたか
    if($(this).prop('files')[0]){
      var data = new FormData($('#update')[0]);

      //非同期アップロード
      (function(data){
          $.ajax({
              type: "POST",
              url: API_PATH + "uploadImage",
              async: true,
              processData: false,
              data: data,
              dataType: 'text',
              contentType: false,
          }).then(function(response){
              response = JSON.parse(response);
              if (response.result == "true") {
                  component_service_image_path.setState({t_menu_imagePath: response.image_path});
              }
              else {
                alert('Upload Failed');
              }
          });
      })(data);
    }
  });
  */

  /*<!-- データピッカー1 -->
  $("#datepicker").datepicker({
  // 日付が選択された時、日付をテキストフィールドへセット
  onSelect: function(dateText, inst) {
  $("#date_val").val(dateText);
  }
  });
   */
  /*<!-- データピッカー2 -->
  $('#date').datepicker({
  format: "yyyy/mm/dd",
  language: "ja",
  autoclose: true,
  orientation: "top auto"
  });
  */
  
});