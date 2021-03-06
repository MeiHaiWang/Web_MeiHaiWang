$(function(){
  /*
    Component for React
  */
  //ReactBootstrap
  var Input = ReactBootstrap.Input;
	
  // コンポーネントの定義
  var ReservationStartTime = React.createClass({displayName: "ReservationStartTime",
    getDefaultProps:function() {
      return {
        t_reservation_start: ['']
      };
    },
    getInitialState:function() {
      return {
        t_reservation_start: '',
      };
    },
    changeText:function(e) {
      this.setState({t_reservation_start: e.target.value});
    },
    render:function() {
    	return (
    			React.createElement(Input, {type: "text", value: this.state.t_reservation_start, onChange: this.changeText})
    		);
	    }
	  });
  
  var ReservationEndTime = React.createClass({displayName: "ReservationEndTime",
	    getDefaultProps:function() {
	      return {
	        t_reservation_end: ['']
	      };
	    },
	    getInitialState:function() {
	      return {
	        t_reservation_end: '',
	      };
	    },
	    onChangeSelectValue:function(e) {
	      this.setState({t_reservation_end: e.target.value});
	    },
	    render:function() {
	    	return(
	        React.createElement(Input, {type: "text", value: this.state.t_reservation_end, onChange: this.changeText, disabled: true})
	        );
	    }
	  });
  
  var ReservationUserTel = React.createClass({displayName: "ReservationUserTel",
	    getInitialState:function() {
	      return {
	        t_user_tel: "",
	        reserveId: -1
	      };
	    },
	    changeText:function(e) {
	      this.setState({t_user_tel: e.target.value});
	    },
	    render:function() {
	      var input_user_tel = (function(){
	    	  var user_tel;
	    	  //console.log("reserveId:"+reserveId);
	    	  if(this.state.reserveId<0){
	    		  user_tel=
	    			  React.createElement(Input, {type: "text", value: this.state.t_user_tel, onChange: this.changeText, placeholder: "電話番号"});
	    	  }else{
	    		  user_tel=
	    			  React.createElement(Input, {type: "text", value: this.state.t_user_tel, onChange: this.changeText, placeholder: "電話番号", disabled: true});
	    	  }
	    	  return user_tel;
	      }).bind(this)();
	      return (
	        React.createElement("div", null, 
	        input_user_tel
	        )
	      );
	    }
	  });
	  
  var ReservationUserName = React.createClass({displayName: "ReservationUserName",
    getInitialState:function() {
      return {
    	  t_user_id: "",
        t_user_name: "",
        reserveId: -1
      };
    },
    changeText:function(e) {
      this.setState({t_user_name: e.target.value});
    },
    render:function() {
	      var input_user_name = (function(){
	    	  var user_name;
	    	  if(this.state.reserveId<0){
	    		  user_name=
	    	          React.createElement(Input, {type: "text", value: this.state.t_user_name, onChange: this.changeText})
	    	  }else{
	    		  user_name=
	    	          React.createElement(Input, {type: "text", value: this.state.t_user_name, onChange: this.changeText, disabled: true})
	    	  }
	    	  return user_name;
	      }).bind(this)();
      return (
        React.createElement("div", null, 
        input_user_name
        )
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
  var ReservationUserGender = React.createClass({displayName: "ReservationUserGender",
	    getInitialState:function() {
	      return {
	        t_user_gender: '0',
		        reserveId: -1
	      };
	    },
	    onChangeSelectValue:function(e) {
	      //console.log(this.state.t_user_gender+","+e.target.value);
	      this.setState({t_user_gender: e.target.value});
	    },
	    render:function() {
	      var input_user_gender = (function(){
	    	  var user_gender;
	    	  if(this.state.reserveId<0){
	    		  user_gender=
	    	          React.createElement("select", {value: this.state.t_user_gender, onChange: this.onChangeSelectValue}, 
		  	          	React.createElement("option", {value: "0"}, "男性"), 
			          	React.createElement("option", {value: "1"}, "女性")
			          );
	    	  }else{
	    		  user_gender=
	    	          React.createElement("select", {value: this.state.t_user_gender, onChange: this.onChangeSelectValue, disabled: true}, 
		  	          	React.createElement("option", {value: "0"}, "男性"), 
			          	React.createElement("option", {value: "1"}, "女性")
			          );
	    	  }
	    	  return user_gender;
	      }).bind(this)();
	      return (
    	        React.createElement("div", null, 
    	        input_user_gender
    	        )
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
  var ReservationUserAge = React.createClass({displayName: "ReservationUserAge",
	    getDefaultProps:function() {
	        return {
	          t_user_age: ages
	        };
	      },
	    getInitialState:function() {
	      return {
	        t_user_age: "20",
		        reserveId: -1
	      };
	    },
	    onChangeSelectValue:function(e) {
	      this.setState({t_user_age: e.target.value});
	    },
	    render:function() {
	    	  var options = this.props.t_user_age.map(function(t_user_age) {
	    	      return React.createElement("option", {value: t_user_age}, t_user_age);
	    	    });
		      var input_user_age = (function(){
		    	  var user_age;
		    	  if(this.state.reserveId<0){
		    		  user_age=
		    	          React.createElement("select", {value: this.state.t_user_age, onChange: this.onChangeSelectValue}, 
	    	  			options
				        );
		    	  }else{
		    		  user_age=
		    	          React.createElement("select", {value: this.state.t_user_age, onChange: this.onChangeSelectValue, disabled: true}, 
		    	  			options
					        );
		    	  }
		    	  return user_age;
		      }).bind(this)();
	    	  var options1 = 
	    		  React.createElement("div", null, 
	    	  			input_user_age
			      );
	    	    return (
	    	    	React.createElement("div", null, 
	    	    		options1
	    	    	)
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
  
  var ReservationStylist = React.createClass({displayName: "ReservationStylist",
    getDefaultProps:function() {
      return {
        stylist: ['']
      };
    },
    getInitialState:function() {
      return {
        t_stylist_id: '',
      };
    },
    onChangeSelectValue:function(e) {
      this.setState({t_stylist_id: e.target.value});
    },
    render:function() {
      var options = this.props.stylist.map(function(stylist) {
        return React.createElement("option", {value: stylist.t_stylist_Id}, stylist.t_stylist_name);
      });
      return (
        React.createElement("div", null, 
          React.createElement("select", {value: this.state.t_stylist_id, onChange: this.onChangeSelectValue}, 
            options
          )
        )
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
  var ServiceCategory = React.createClass({displayName: "ServiceCategory",
	    getDefaultProps:function() {
		      return {
		        category: ['']
		      };
		    },
	    getInitialState:function() {
	      return {
	        t_menu_categoryId: '',
	      };
	    },
	  handleSelect:function(e) {
	    //alert('selected ' + e);
	      // メニュー一覧
	      var menu_options = getMenuListOfCategory(e);
	      component_service_list.setState({"service_list":menu_options});
	      this.setState({t_menu_categoryId: e});
	  },
	  render:function() {
		  var category1 = [];
		  var category2 = [];
		  //console.log("a:"+this.props.category.length);
		  for(var i=0;i<this.props.category.length;i++){
			  //console.log(this.props.category[i].t_menuCategory_name);
			  if(i<6) category1.push(this.props.category[i]);
			  else category2.push(this.props.category[i]);
		  }
	      var options1 = category1.map(function(category1) {
	    	  return React.createElement(NavItem, {bsSize: "xsmall", eventKey: category1.t_menuCategory_categoryId}, 
	    	  category1.t_menuCategory_name
	    	  );
	      });
	      var options2 = category2.map(function(category2) {
	    	  return React.createElement(NavItem, {bsSize: "xsmall", eventKey: category2.t_menuCategory_categoryId}, 
	    	  category2.t_menuCategory_name
	    	  );
	      });
	    return (
		React.createElement("div", null, 
	    	React.createElement(Navbar, {bsStyle: "warning"}, 
				React.createElement(Nav, {activeKey: this.state.t_menu_categoryId, onSelect: this.handleSelect}, 
				options1
				)
		    	), 
		    	React.createElement(Navbar, {bsStyle: "warning"}, 
				React.createElement(Nav, {activeKey: this.state.t_menu_categoryId, onSelect: this.handleSelect}, 
				options2
				)
	    	)
    	)
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
  
  var ServiceList = React.createClass({displayName: "ServiceList",
    getInitialState:function() {
      return {
        service_list: [{
          "t_menu_categoryId": "",
          "t_menu_menuId": "",
          "t_menu_name": "",
          "t_menu_price": "",
          "t_menu_time": ""}]
      };
    },
    render:function() {
      var service = this.state.service_list.map(function(service) {
        return React.createElement("tr", null, React.createElement("td", null, service.t_menu_name), 
        React.createElement("td", null, service.t_menu_time), 
        React.createElement("td", null, service.t_menu_price), 
        React.createElement("td", null, React.createElement("a", {className: "reserve"}, "预约")));
      });
      return (
        React.createElement("div", null, 
          React.createElement("table", null, 
          	React.createElement("thead", null, 
            React.createElement("tr", null, React.createElement("th", null, "服务名称"), React.createElement("th", null, "时间"), React.createElement("th", null, "价格"), React.createElement("th", null, "预约"))
            ), 
            React.createElement("tbody", null, 
            service, 
            React.createElement("tr", null)
            )
          )
        )
      );
    }
  });
  
  var ServiceList2 = React.createClass({displayName: "ServiceList2",
	    getInitialState:function() {
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
	    render:function() {
	      var service = this.state.service_list2.map(function(service) {
	        return React.createElement("tr", null, React.createElement("td", null, service.t_menu_name), React.createElement("td", null, service.t_menu_start), React.createElement("td", null, service.t_menu_time), React.createElement("td", null, service.t_menu_stylist), React.createElement("td", null, service.t_menu_seat), React.createElement("td", null, React.createElement("a", {className: "delete"}, "削除")));
	      });
	      return (
	        React.createElement("div", null, 
	          React.createElement("table", null, 
	          	React.createElement("thead", null, 
	            React.createElement("tr", null, React.createElement("th", null, "服务名称"), React.createElement("th", null, "开始时间"), React.createElement("th", null, "时间"), React.createElement("th", null, "专人"), React.createElement("th", null, "席位"), React.createElement("th", null, "取消"))
	            ), 
	            React.createElement("tbody", null, 
	            service
	            )
	          )
	        )
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
  var component_reservation_start = React.render(React.createElement(ReservationStartTime, null), document.getElementById('reservation_start'));
  var component_reservation_end = React.render(React.createElement(ReservationEndTime, null), document.getElementById('reservation_end'));
  var component_reservation_user_tel = React.render(React.createElement(ReservationUserTel, null), document.getElementById('reservation_user_tel'));
  var component_reservation_user_name = React.render(React.createElement(ReservationUserName, null), document.getElementById('reservation_user_name'));
  var component_reservation_user_sex = React.render(React.createElement(ReservationUserGender, null), document.getElementById('reservation_user_sex'));
  var component_reservation_user_age = React.render(React.createElement(ReservationUserAge, null), document.getElementById('reservation_user_age'));  
  var component_reservation_stylist = React.render(React.createElement(ReservationStylist, null), document.getElementById('reservation_stylist'));
  var component_service_category = React.render(React.createElement(ServiceCategory, null), document.getElementById('service_menu_category'));
  var component_service_list = React.render(React.createElement(ServiceList, null), document.getElementById('service_menu_list_info'));
  var component_service_list2 = React.render(React.createElement(ServiceList2, null), document.getElementById('service_menu_list_info2'));
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
		  //予約時間を取得
		  var param_time = reservationInfo.t_reservation_date;
		  //console.log("param_time:"+reservationInfo.t_reservation_date);
		  var month = Number(moment(param_time).month()) + 1;
		 var p_date = moment(param_time).year() + "-" + month +"-"+ moment(param_time).date();
		  var hours = (Number(moment(param_time).hours())<10) ? '0'+moment(param_time).hours(): moment(param_time).hours();
		  var minutes = (Number(moment(param_time).minutes())<10) ? '0'+moment(param_time).minutes(): moment(param_time).minutes();
		  reservation_start_time = hours + ":" +minutes;
		  reservation_menu_start_time = reservation_start_time;
		  reservation_end_time = reservation_start_time;
		  var param_stylistId = reservationInfo.t_reservation_stylistId;
		  //userInfo~を取得
		  var userInfo = getUserInfo({t_user_tel: reservationInfo.t_reservation_userTel});
		  component_reservation_user_tel.setState({t_user_tel: userInfo.t_user_tel,reserveId:reserveId});
		  component_reservation_user_name.setState({
			  t_user_id: userInfo.t_user_id,
			  t_user_name: userInfo.t_user_name,
			  reserveId:reserveId});
		  //var gender = getGenderStr(userInfo.t_user_gender);
		  //component_reservation_user_sex.setState({t_user_gender: gender});
		  component_reservation_user_sex.setState({t_user_gender: userInfo.t_user_gender,reserveId:reserveId});
		  component_reservation_user_age.setState({t_user_age: userInfo.t_user_age,reserveId:reserveId});
		  //reserved menus
		  var reserved_menus = reservationInfo.t_reservation_menuId;
		  //apoint_check
		  //console.log("appoint:"+reservationInfo.t_reservation_appoint);
		  if(reservationInfo.t_reservation_appoint==1){
			  $('#appoint').prop("checked",true);
		  }
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
	  //console.log("Parameters: "+p_date + " " + reservation_start_time + "-" +reservation_end_time +" ,"+param_stylistId);
  }

  //genderを文字列に
  function getGenderStr(genderId){
	  var gender = "";
    	if(genderId==0){
    		gender = "男士";
    	}else if(genderId==1){
    		gender = "女士";
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
	  //メニューカテゴリを取得
	  var service_category_info = getServiceCategoryList();
	  sanitaize.decode(service_category_info);
	  var category = service_category_info.category;
	  //categoryに休憩・休暇を追加
	  category.push({
          t_menuCategory_categoryId:"R",
          t_menuCategory_name:"休息"
        });
	  //メニューリストを取得
	  var menu_list = getMenuInfo({t_hairSalonMaster_salonId : session_info.t_hairSalonMaster_salonId});
	  sanitaize.decode(menu_list);
	  //メニューリストに休憩・休暇を追加
	  menu_list.menu.push({
          t_menu_menuId:"R1",
          t_menu_name:"休息",
          t_menu_time: "60"
        });
	  menu_list.menu.push({
          t_menu_menuId:"R2",
          t_menu_name:"休假",
          t_menu_time:"ALLDAY"  
        });
	  var menus = new Array();
	  for (var i = 0; i < menu_list.menu.length; i++) {
	    var menu_id = menu_list.menu[i].t_menu_menuId;
	    var menu_name = menu_list.menu[i].t_menu_name;
	    menus[menu_id] = menu_name;
	  }

	  //categoryIdにマッチするメニューを取得
	  var getMenuListOfCategory = function(t_category_id){
		  var ret = [];
		  //console.log("getMenuList:"+t_category_id);
		  //休憩対応
		  if(t_category_id == "R"){
			  ret.push({
		          "t_menu_categoryId": "R",
		          "t_menu_menuId": "R1",
		          "t_menu_name": "休憩",
		          "t_menu_price": "0",
		          "t_menu_time": "60"
			  });
			  ret.push({
		          "t_menu_categoryId": "R",
		          "t_menu_menuId": "R2",
		          "t_menu_name": "休暇",
		          "t_menu_price": "0",
		          "t_menu_time": "ALLDAY"
			  });
			  return ret;
		  }
		  //メニュー対応
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
		//console.log("getEndTime("+st+","+total_time+")");
    	//var start_time = component_reservation_start.state.t_reservation_start;
		  //var start_time = reservation_start_time;
		  var start_time = st;
    	var start_hours = Number(start_time.substring(0,2));
    	var start_minutes = Number(start_time.substring(3,5));
    	ope_hours = Math.floor(total_time/60);
    	ope_minutes = total_time%60;
    	var end_hours = start_hours+ope_hours;
    	var end_minutes = start_minutes+ope_minutes;
    	//console.log(start_hours+","+start_minutes+","+end_hours+","+end_minutes);
    	if(end_minutes >= 60){
    		end_minutes -= 60;
    		end_hours += 1;
    	}
    	var end_hours_str = (end_hours<10) ? '0'+end_hours: end_hours;
    	var end_minutes_str = (end_minutes<10) ? '0'+end_minutes: end_minutes;
    	var end_time = end_hours_str + ":" + end_minutes_str;
    	//console.log("start_time="+start_time+", total_time="+total_time+", end_time="+end_time);
    	return end_time;
	  }
	  
  /*<!-- データピッカー2　予約日時設定 -->*/
  $('#date').datepicker({
  dateFormat: "yy-mm-dd",
  language: "ja",
  autoclose: true,
  orientation: "top auto"
  });
  
  // Component set. 
  //console.log("p_date:"+p_date);
  $("#date").datepicker("setDate", p_date);
  component_reservation_start.setState({t_reservation_start: reservation_start_time});
  component_reservation_end.setState({t_reservation_end: reservation_end_time});
  component_reservation_stylist.setProps({stylist: stylist_info.stylist});
  component_reservation_stylist.setState({t_stylist_id: param_stylistId});
  component_service_category.setProps({category: service_category_info.category});
  component_service_category.setState({t_menu_categoryId: component_service_category.props.category[0].t_menuCategory_categoryId});
  var menu_options = getMenuListOfCategory(1);
  component_service_list.setState({"service_list":menu_options});

  //予約ボタンで選んだメニューリスト
  var reserve_menu_list = [];
  //Total
  var total_price = 0;
  var total_time  = 0;

  //予約情報編集の場合、予約されていた情報を配置する
  if(reservationFlag){
	  var s_time = reservation_start_time;
	  var menu_ids =  reserved_menus.split(',');
	  //console.log(menu_ids.length+","+menu_list.menu.length);
	  for (var i = 0; i < menu_ids.length; i++) {
		  for (var j = 0; j < menu_list.menu.length; j++) {
			  //console.log(menu_ids[i]+","+menu_list.menu[j].t_menu_menuId);
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

  //予約競合チェック関数 conflictしてたらtrue
  /* oneStart, oneEnd: 予約されている開始時間、終了時間
   * checkStart, checkEnd: 新しく予約する開始時間、終了時間
   */
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
			  //console.log("user_info.t_user_age:"+user_info.t_user_age);
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
    var id = $(".reserve").index(this);
    //console.log("予約ボタンがオサレました:r_id:"+id);
    var exist=false;
    for(var i=0; i<reserve_menu_list.length; i++){
    	//予約メニューに休憩が含まれていて、休憩以外のメニューを選択したら初期化
    	if(reserve_menu_list[i].t_menu_menuId=="R1"){
    		if(!component_service_list.state.service_list[id].t_menu_menuId=="R1"){
        		reserve_menu_list = [];
    		}
    	}else if(reserve_menu_list[i].t_menu_menuId=="R2"){
			reserve_menu_list = [];
    	}else{
    	    //すでに登録されているメニューではないかをチェック
    	    if(reserve_menu_list[i].t_menu_menuId == component_service_list.state.service_list[id].t_menu_menuId){
    	    	exist = true;
    	    }
    	}
    }
    if(exist==false){
    	if(component_service_list.state.service_list[id].t_menu_menuId=="R1"){
    		//休憩は追加できる
    		//console.log("length:"+reserve_menu_list.length);
    		if(reserve_menu_list.length>0 && reserve_menu_list[0].t_menu_menuId!="R1"){
        		reserve_menu_list = [];
    		}
    	}else if(component_service_list.state.service_list[id].t_menu_menuId=="R2"){
    		//休暇が選ばれたらメニューを空にする
    		reserve_menu_list = [];
    	}
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
	    //var end = getEndTime(reservation_start_time, total_time);
	    //reservation_menu_start_time = getEndTime(reservation_start_time, total_time);
	    //console.log("aaa:"+component_reservation_start.state.t_reservation_start);
	    var end = getEndTime(component_reservation_start.state.t_reservation_start, total_time);
	    reservation_start_time = component_reservation_start.state.t_reservation_start;
	    reservation_menu_start_time = end;
	    //console.log("reservation_start_time:"+reservation_start_time+",reservation_menu_start_time:"+reservation_menu_start_time);
	    component_reservation_end.setState({t_reservation_end: end});
    }
    component_service_list2.setState({"service_list2": reserve_menu_list});
  });

  // 削除ボタン押下時
  $('.delete').on('click', function() {
    var id = $(".delete").index(this);
    //console.log("d_id:"+id);
    for(var i=0; i<reserve_menu_list.length; i++){
        if(reserve_menu_list[i].t_menu_menuId == component_service_list2.state.service_list2[id].t_menu_menuId){
        	//console.log(reserve_menu_list[i].t_menu_name+","+component_service_list2.state.service_list2[id].t_menu_price);
    	    total_price = total_price-Number(component_service_list2.state.service_list2[id].t_menu_price);
    	    total_time = total_time-Number(component_service_list2.state.service_list2[id].t_menu_time);
        	reserve_menu_list.splice(i, 1);
        }
    }
    //表示時間を削除された分ひいて表示し直す
    if(reserve_menu_list.length>0){
    	reserve_menu_list[0].t_menu_start=reservation_start_time;
		//console.log("reserve_menu_list[0]:"+reserve_menu_list[0].t_menu_start);
	    for(var i=1; i<reserve_menu_list.length; i++){
	    	reserve_menu_list[i].t_menu_start=getEndTime(reserve_menu_list[i-1].t_menu_start, reserve_menu_list[i-1].t_menu_time);
	    	//console.log("reserve_menu_list[i]:"+reserve_menu_list[i].t_menu_start);
	    }
	    reservation_menu_start_time=getEndTime(reserve_menu_list[reserve_menu_list.length-1].t_menu_start,
	    		reserve_menu_list[reserve_menu_list.length-1].t_menu_time);
	    component_reservation_end.setState({t_reservation_end: reservation_menu_start_time});
    }
    component_service_list2.setState({"service_list2": reserve_menu_list});
  });
  
  // 登録ボタン押下時
  $('#regist').on('click', function() {
	  //日付取得
	  var md = moment($('#date').datepicker('getDate'));
	  var mth = Number(md.month())+1;
	  var d = md.year() +"-" + mth +"-"+ md.date()+ 
		  " "+component_reservation_start.state.t_reservation_start;

	  //console.log("rl:"+reserve_menu_list.length+","+reserve_menu_list[0].t_menu_menuId);
	  //予約するメニューリストをカンマつづりで取得
	  var ms = (function(){
		 var ms = "";
		 for(var i=0; i<reserve_menu_list.length ; i++){
			 ms += reserve_menu_list[i].t_menu_menuId + ",";
		 }
		 //console.log(ms + "," +ms.length);
		 if(ms.length>1){
			 ms = ms.substring(0, ms.length-1);
		 }
		 //console.log(ms);
		 return ms;
	  })();

	  //Validate- 予約するための項目が埋まっているかチェック
	  //予約メニューなしでは登録できない
	  if(ms.length<=0){
		  console.log("予約メニューが選択されていません.");
		  alert("菜单没有选择");
		  return;
	  }
	  //休憩以外でユーザ情報が入力されていないと登録できない
	  //console.log(reserve_menu_list[0].t_menu_menuId+","+reserve_menu_list[0].t_menu_menuId.indexOf("R"));
	  var restCheck = reserve_menu_list[0].t_menu_menuId+"";
	  if(restCheck.indexOf("R")<0){
		  if(component_reservation_user_name.state.t_user_name==""
			  || component_reservation_user_tel.state.t_user_tel==""){
			  console.log("ユーザ情報の入力が足りません.");
			  alert("预约信息不够");
			  return;
		  }
	  }
	  
	  //予約メニューにかかる時間を算出
		 var m_time = 0;
		 //console.log(reserve_menu_list.length+","+menu_list.menu.length);
		 for(var i=0; i<reserve_menu_list.length ; i++){
			 for(var j=0; j<menu_list.menu.length; j++){
				 //console.log(reserve_menu_list[i].t_menu_menuId +","+ menu_list.menu[j].t_menu_menuId);
				 if(reserve_menu_list[i].t_menu_menuId == menu_list.menu[j].t_menu_menuId){
					 m_time += menu_list.menu[j].t_menu_time;
				 }
			 }
			 if(reserve_menu_list[i].t_menu_menuId == "R1"){
				 m_time += 60;
			 }
		 }
		 //console.log("予約メニューにかかる時間："+m_time+"分.");
		  
	 
	 //競合チェック
	  var r_lists = getReservationList({
		　 t_reservation_salonId : session_info.t_hairSalonMaster_salonId,
		  t_reservation_date: d
	  });
	  var r_list = [];
	  r_list = r_lists.reservation_list;
	  //予約編集の場合は、編集している予約情報をのぞく
	  for(var i=0; i<r_list.length; i++){
		  if(r_list[i].t_reservation_id == reserveId){
			  //console.log("予約編集中："+r_list[i].t_reservation_id);
			  r_list.splice(i,1);
		  }
	  }
	  if(r_list==undefined){
		  //予約なし
		  console.log("予約しようとしている日に他の予約はありません.");
	  }else{
		  //予約あり(競合チェック)
		  for(var i=0; i<r_list.length; i++){
			  //console.log(d.substring(0, 10)+","+r_list[i].t_reservation_date.substring(0, 10));
			  if(d.substring(0, 10)!=r_list[i].t_reservation_date.substring(0, 10)){
				  continue;
			  }
			  var representative = "";
			  for(var j=0; j<stylist_info.stylist.length; j++){
				  //console.log(stylist_info.stylist[j].t_stylist_Id+","+component_reservation_stylist.state.t_stylist_id);
				  if(stylist_info.stylist[j].t_stylist_Id == component_reservation_stylist.state.t_stylist_id){
					  representative = stylist_info.stylist[j].t_stylist_name;
				  }
			  }
			  //console.log(representative+","+r_list[i].t_stylist_name);
			  if(representative != r_list[i].t_stylist_name){
				  continue;
			  }
			  //console.log("予約された時間:"+r_list[i].t_reservation_date)
			  var oneSt = r_list[i].t_reservation_date.substring(11, 16);
			  var oneEt = getEndTime(oneSt, r_list[i].t_reservation_time);
			  var cSt = component_reservation_start.state.t_reservation_start;
			  var cEt = getEndTime(cSt ,m_time);
			  //console.log("CoflictCheck:"+oneSt+","+ oneEt+","+ cSt+","+ cEt);
			  var result = checkConflict(oneSt, oneEt, cSt, cEt);
			  if(result){
				  console.log("予約時間が競合しています. : 競合予約時刻（"+oneSt+"〜"+oneEt+")");
				  alert("预约重复 : 重复预约时间（"+oneSt+"〜"+oneEt+")");
				  return;
			  }else{
				  //console.log("競合していません.");
			  }
		  }
	  }
		  
	  //ユーザidを発見できなかった場合は、ユーザを仮登録
	  if(restCheck.indexOf("R")<0){
		  var uid = (function(){
			  var uid = -1;
			  uid = component_reservation_user_name.state.t_user_id;
			  //console.log("uid:"+uid);
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
		  })();
	  }else{
		  var uid=0;
	  }

	  //指名かどうか
	  var check = document.forms.check_form.appoint.checked;
	  //console.log("appoint:"+check);
	  var appoint;
	  if(check==true){
		  appoint=1;
	  }else{
		  appoint=0;
	  }
		  
	  //サーブレット送信情報
	  var data;
	  if(reserveId<0){
	    data = {
	      t_reservation_userId: uid,
	    　 t_reservation_salonId: session_info.t_hairSalonMaster_salonId,
	    　 t_reservation_stylistId: component_reservation_stylist.state.t_stylist_id,
	    　 t_reservation_date: d,
	    　 t_reservation_menuId: ms,
	    　 t_reservation_seatId: 1,
	      t_reservation_memo: $('#memo').text()　,
	      t_reservation_appoint: appoint
	     };
	  }else{
		  //サーブレットUpdate情報
	    data = {
	      t_reservation_id: reserveId,
	      t_reservation_userId: uid,
	    　 t_reservation_salonId: session_info.t_hairSalonMaster_salonId,
	    　 t_reservation_stylistId: component_reservation_stylist.state.t_stylist_id,
	    　 t_reservation_date: d,
	    　 t_reservation_menuId: ms,
	    　 t_reservation_seatId: 1,
	      t_reservation_memo: $('#memo').text()　,
	      t_reservation_appoint: appoint
	     };
	  }
    
    // サニタイズ
    sanitaize.encode(data);

    //debug
    //alert(data);
    console.log(data);
    
    var result = setReservationInfo(data);
    if (result.result == "true") {
      alert('Regist Success');
      //window.location.reload();
      location.href="calender.html";
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
	    $( '#claim-opener' ).click( function() {
	        $( '#claim-dialog' ).dialog( 'open' );
	    } );
	    var name = $( '#claim-dialog-form-name' );
	    var comment = $( '#claim-dialog-form-comment' );
	    $( '#claim-dialog' ).dialog( {
	        autoOpen: false,
	        width: 350,
	        show: 'explode',
	        hide: 'explode',
	        modal: true,
	        buttons: {
	            '登记': function() {
	                if ( comment . val() ) {
	               	 if(reserveId>0){
	                	setClaimFunc(comment.val());
	               	 }else{
	               		 alert('无法查到预约信息');
	               	 }
	                	$( this ).dialog( 'close' );
	                }
	                $( this ) . dialog( 'close' );
	            },
	            '取消': function() {
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
		  alert('无法查到预约信息');
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
	      //window.location.reload();
	      location.href="calender.html";
	    }
	    else {
	      alert('Regist Failed');
	    }
  }
 	
  // 来店ボタン押下時
  $('#come').on('click', function() {
	  finishReservation(1);
      location.href="calender.html";
  });

  // 取り消しボタン押下時
  $('#cancel').on('click', function() {
	  $('#cancel_dialog').dialog('open');
  });
  $('#cancel_dialog').dialog({
	  /*
	   * http://jquery.keicode.com/ui/dialog.php
	   */
	  autoOpen: false,
	  title: '预约取消',
	  closeOnEscape: false,
	  modal: true,
	  buttons: {
	    "有联系": function(){
	    	finishReservation(2);
	      $(this).dialog('close');
	    },
	  "没有联系": function(){
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