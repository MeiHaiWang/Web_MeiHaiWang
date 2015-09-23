$(function(){
  /*
    Component for React
  */
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
    onChangeSelectValue:function(e) {
      this.setState({t_reservation_start: e.target.value});
    },
    render:function() {
    	return (
    			React.createElement("input", {type: "text", value: this.state.t_reservation_start, onChange: this.changeText})
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
	        React.createElement("input", {type: "text", value: this.state.t_reservation_end, onChange: this.changeText})
	        );
	    }
	  });
  
  var ReservationUserTel = React.createClass({displayName: "ReservationUserTel",
	    getInitialState:function() {
	      return {
	        t_user_tel: ""
	      };
	    },
	    changeText:function(e) {
	      this.setState({t_user_tel: e.target.value});
	    },
	    render:function() {
	      return (
	        React.createElement("div", null, 
	          React.createElement("input", {type: "text", value: this.state.t_user_tel, onChange: this.changeText, placeholder: "電話番号"})
	        )
	      );
	    }
	  });
	  
  var ReservationUserName = React.createClass({displayName: "ReservationUserName",
    getInitialState:function() {
      return {
        t_user_name: ""
      };
    },
    changeText:function(e) {
      this.setState({t_user_name: e.target.value});
    },
    render:function() {
      return (
        React.createElement("div", null, 
          React.createElement("input", {type: "text", value: this.state.t_user_name, onChange: this.changeText})
        )
      );
    }
  });

  var ReservationUserGender = React.createClass({displayName: "ReservationUserGender",
	    getInitialState:function() {
	      return {
	        t_user_gender: ""
	      };
	    },
	    changeText:function(e) {
	      this.setState({t_user_gender: e.target.value});
	    },
	    render:function() {
	      return (
	        React.createElement("div", null, 
	          React.createElement("input", {type: "text", value: this.state.t_user_gender, onChange: this.changeText})
	        )
	      );
	    }
	  });

  var ReservationUserAge = React.createClass({displayName: "ReservationUserAge",
	    getInitialState:function() {
	      return {
	        t_user_age: ""
	      };
	    },
	    changeText:function(e) {
	      this.setState({t_user_age: e.target.value});
	    },
	    render:function() {
	      return (
	        React.createElement("div", null, 
	          React.createElement("input", {type: "text", value: this.state.t_user_age, onChange: this.changeText})
	        )
	      );
	    }
	  });

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
        return React.createElement("option", {value: stylist.t_stylist_stylist_id}, stylist.t_stylist_name);
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
  var ServiceDetailText = React.createClass({
    getInitialState() {
      return {
        t_menu_detailText: ""
      };
    },
    onChangeText(e) {
      this.setState({t_menu_detailText: e.target.value});
    },
    onClick() {
      this.setState({t_menu_detailText: this.refs.textArea.getDOMNode().value});
    },
    render() {
      return (
        <div>
          <div>
            <textarea value={this.state.t_menu_detailText} onChange={this.onChangeText} />
          </div>
        </div>
      );
    }
  });
  
  var ServicePrice = React.createClass({
    getInitialState() {
      return {
        t_menu_price: ""
      };
    },
    changeText(e) {
      this.setState({t_menu_price: e.target.value});
    },
    render() {
      return (
        <div>
          <input type="text" value={this.state.t_menu_price} onChange={this.changeText} />
        </div>
      );
    }
  });

  var ServiceImagePath = React.createClass({
    getInitialState() {
      return {
        t_menu_imagePath: "img/notfound.jpg"
      };
    },
    render() {
      return (
        <div>
          <img className="service_image_img" src={this.state.t_menu_imagePath?this.state.t_menu_imagePath:'img/notfound.jpg'} />
        </div>
      );
    }
  });

  var ServiceList = React.createClass({
    getInitialState() {
      return {
        service_list: [{
          "t_menu_categoryId": "",
          "t_menu_menuId": "",
          "t_menu_name": "",
          "t_menu_price": "",
          "t_menu_detailText": "",
          "t_menu_imagePath": "img/notfound.jpg"}]
      };
    },
    render() {
      var index = 1;
      var service = this.state.service_list.map(function(service) {
        return <tr><td>{index++}</td><td>{categorys[service.t_menu_categoryId]}</td><td>{service.t_menu_name}</td><td><img src={service.t_menu_imagePath?service.t_menu_imagePath:'img/notfound.jpg'} /></td><td><a className="edit">编辑</a>/<a className="delete">删除</a></td></tr>;
      });
      return (
        <div>
          <table>
            <tr><th>No.</th><th>分类</th><th>服务名称</th><th>照片</th><th>编辑</th></tr>
            {service}
          </table>
        </div>
      );
    }
  });
  */

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
  /*
  var component_service_name = React.render(<ServiceName />, document.getElementById('service_name'));
  var component_service_detail_text = React.render(<ServiceDetailText />, document.getElementById('service_detail_text'));
  var component_service_price = React.render(<ServicePrice />, document.getElementById('service_price'));
  var component_service_image_path = React.render(<ServiceImagePath />, document.getElementById('service_image_path'));
  var component_service_list = React.render(<ServiceList />, document.getElementById('service_list_info'));
	*/

  /*
    Main Part
  */
  // セッションIDからサービス情報を取得する
  var session_info = getSessionInfo();
  var parameter_info = getParam();
  //var service_info = getMenuInfo(session_info.t_hairSalonMaster_salonId);
  //sanitaize.decode(service_info);
  var param_time = parameter_info['time'];
  var p_date = param_time.substring(0, 10);
  var p_start_time = param_time.substring(13, 18);
  var p_end_time = Number(p_start_time.substring(0,2)) + 2 ;
  var param_stylistId = parameter_info['stylistId'];
  console.log("Parameters: "+p_date + " " + p_start_time + "-" +p_end_time +":00 ,"+param_stylistId);
  
  //パラメータget
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
	
	  // stylistを参照しやすい形に変換
	  var stylist_info = getStylistList({'t_hairSalonMaster_salonId':session_info.t_hairSalonMaster_salonId});
	  sanitaize.decode(stylist_info);
	  // stylistを参照しやすい形に変換
	  var stylists = new Array();
	  for (var i = 0; i < stylist_info.stylist.length; i++) {
	    var stylist_id = stylist_info.stylist[i].t_stylist_stylist_id;
	    var stylist_name = stylist_info.stylist[i].t_stylist_name;
	    stylists[stylist_id] = stylist_name;
	  }

  // アカウント名を表示
  $('#account-name').text(session_info.t_hairSalonMaster_contactUserName);
  
  // set component
  //$('#date').text(p_date);
  $("#Date").datepicker("setDate", p_date);
  component_reservation_start.setState({t_reservation_start: p_start_time});
  component_reservation_end.setState({t_reservation_end: p_end_time+":00"});
  component_reservation_stylist.setProps({stylist: stylist_info.stylist});
  component_reservation_stylist.setState({t_stylist_id: param_stylistId})

  /*
  component_service_category.setProps({category: service_category_info.category});
  component_service_category.setState({t_menu_categoryId: component_service_category.props.category[0].t_menuCategory_categoryId});
  */

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
	  console.log("search button clicked."+component_reservation_user_tel.state.t_user_tel);
	  var user_info = (function(){
		 var user_info = getUserInfo({t_user_tel: component_reservation_user_tel.state.t_user_tel});
		 return user_info;
	  });
	  if(user_info.t_user_id>0){
		  component_reservation_user_name.setState({t_user_name: user_info.t_user_name});
	  }
  });

  /*
  // 登録ボタン押下時
  $('#service_regist_button').on('click', function() {
    var data = {
      t_hairSalonMaster_salonId: session_info.t_hairSalonMaster_salonId,
      t_menu_menuId:             component_service_category.state.t_menu_menuId,
      t_menu_categoryId:         component_service_category.state.t_menu_categoryId,
      t_menu_name:               component_service_name.state.t_menu_name,
      t_menu_price:              component_service_price.state.t_menu_price,
      t_menu_detailText:         component_service_detail_text.state.t_menu_detailText,
      t_menu_imagePath:          component_service_image_path.state.t_menu_imagePath,
    }

    // サニタイズ
    sanitaize.encode(data);

    var result = setMenuInfo(data);
    if (result.result == "true") {
      alert('Regist Success');
      window.location.reload();
    }
    else {
      alert('Regist Failed');
    }
  });

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
  /*<!-- データピッカー2 -->*/
  $('#date').datepicker({
  format: "yyyy/mm/dd",
  language: "ja",
  autoclose: true,
  orientation: "top auto"
  });
  
});