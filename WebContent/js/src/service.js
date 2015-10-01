$(function(){
  /*
    Component for React
  */
  // コンポーネントの定義
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
    onChangeSelectValue:function(e) {
      this.setState({t_menu_categoryId: e.target.value});
    },
    render:function() {
      var options = this.props.category.map(function(category) {
        return React.createElement("option", {value: category.t_menuCategory_categoryId}, category.t_menuCategory_name);
      });
      return (
        React.createElement("div", null, 
          React.createElement("select", {value: this.state.t_menu_categoryId, onChange: this.onChangeSelectValue}, 
            options
          )
        )
      );
    }
  });

  var ServiceName = React.createClass({displayName: "ServiceName",
    getInitialState:function() {
      return {
        t_menu_name: ""
      };
    },
    changeText:function(e) {
      this.setState({t_menu_name: e.target.value});
    },
    render:function() {
      return (
        React.createElement("div", null, 
          React.createElement("input", {type: "text", value: this.state.t_menu_name, onChange: this.changeText})
        )
      );
    }
  });
  var ServiceDetailText = React.createClass({displayName: "ServiceDetailText",
    getInitialState:function() {
      return {
        t_menu_detailText: ""
      };
    },
    onChangeText:function(e) {
      this.setState({t_menu_detailText: e.target.value});
    },
    onClick:function() {
      this.setState({t_menu_detailText: this.refs.textArea.getDOMNode().value});
    },
    render:function() {
      return (
        React.createElement("div", null, 
          React.createElement("div", null, 
            React.createElement("textarea", {value: this.state.t_menu_detailText, onChange: this.onChangeText})
          )
        )
      );
    }
  });
  var ServicePrice = React.createClass({displayName: "ServicePrice",
    getInitialState:function() {
      return {
        t_menu_price: ""
      };
    },
    changeText:function(e) {
      this.setState({t_menu_price: e.target.value});
    },
    render:function() {
      return (
        React.createElement("div", null, 
          React.createElement("input", {type: "text", value: this.state.t_menu_price, onChange: this.changeText})
        )
      );
    }
  });

  var ServiceTime = React.createClass({displayName: "ServiceTime",
	    getInitialState:function() {
	      return {
	        t_menu_time: ""
	      };
	    },
	    changeText:function(e) {
	      this.setState({t_menu_time: e.target.value});
	    },
	    render:function() {
	      return (
	        React.createElement("div", null, 
	          React.createElement("input", {type: "text", value: this.state.t_menu_time, onChange: this.changeText})
	        )
	      );
	    }
	  });
  
  var ServiceImagePath = React.createClass({displayName: "ServiceImagePath",
    getInitialState:function() {
      return {
        t_menu_imagePath: "img/notfound.jpg"
      };
    },
    render:function() {
      return (
        React.createElement("div", null, 
          React.createElement("img", {className: "service_image_img", src: this.state.t_menu_imagePath?this.state.t_menu_imagePath:'img/notfound.jpg'})
        )
      );
    }
  });

  var ServiceList = React.createClass({displayName: "ServiceList",
    getInitialState:function() {
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
    render:function() {
      var index = 1;
      var service = this.state.service_list.map(function(service) {
        return React.createElement("tr", null, React.createElement("td", null, index++), React.createElement("td", null, categorys[service.t_menu_categoryId]), React.createElement("td", null, service.t_menu_name), React.createElement("td", null, React.createElement("img", {src: service.t_menu_imagePath?service.t_menu_imagePath:'img/notfound.jpg'})), React.createElement("td", null, React.createElement("a", {className: "edit"}, "编辑"), "/", React.createElement("a", {className: "delete"}, "删除")));
      });
      return (
        React.createElement("div", null, 
          React.createElement("table", null, 
            React.createElement("tr", null, React.createElement("th", null, "No."), React.createElement("th", null, "分类"), React.createElement("th", null, "服务名称"), React.createElement("th", null, "照片"), React.createElement("th", null, "编辑")), 
            service
          )
        )
      );
    }
  });

  // set state to component
  function componentSetState(menu) {
    // コンポーネントにjsonを渡して関係する部分だけ書き換わる
    component_service_category.setState(menu);
    component_service_name.setState(menu);
    component_service_detail_text.setState(menu);
    component_service_price.setState(menu);
    component_service_time.setState(menu);
    component_service_image_path.setState(menu);
  }


  /*
    List
  */
  var service_category_info = getServiceCategoryList();
  sanitaize.decode(service_category_info);

  // categoryを参照しやすい形に変換
  var categorys = new Array();
  for (var i = 0; i < service_category_info.category.length; i++) {
    var category_id = service_category_info.category[i].t_menuCategory_categoryId;
    var category_name = service_category_info.category[i].t_menuCategory_name;
    categorys[category_id] = category_name;
  }


  /*
    Component Render
  */
  // コンポーネントをエレメントに割り当てる
  var component_service_category = React.render(React.createElement(ServiceCategory, null), document.getElementById('service_category'));
  var component_service_name = React.render(React.createElement(ServiceName, null), document.getElementById('service_name'));
  var component_service_detail_text = React.render(React.createElement(ServiceDetailText, null), document.getElementById('service_detail_text'));
  var component_service_price = React.render(React.createElement(ServicePrice, null), document.getElementById('service_price'));
  var component_service_time = React.render(React.createElement(ServiceTime, null), document.getElementById('service_time'));
  var component_service_image_path = React.render(React.createElement(ServiceImagePath, null), document.getElementById('service_image_path'));
  var component_service_list = React.render(React.createElement(ServiceList, null), document.getElementById('service_list_info'));


  /*
    Main Part
  */
  // セッションIDからサービス情報を取得する
  var session_info = getSessionInfo();
  var service_info = getMenuInfo(session_info.t_hairSalonMaster_salonId);
  sanitaize.decode(service_info);

  // アカウント名を表示
  $('#account-name').text(session_info.t_hairSalonMaster_contactUserName);

  // set component
  component_service_category.setProps({category: service_category_info.category});
  component_service_category.setState({t_menu_categoryId: component_service_category.props.category[0].t_menuCategory_categoryId});

  // メニュー一覧
  if (service_info.menu.length != 0) {
    component_service_list.setState({"service_list":service_info.menu});
  }

  /*
    Button Handler
  */
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
      t_menu_time:               component_service_time.state.t_menu_time
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

  // 画像アップロード
  $('#service_image').change(function() {
    // ファイルが選択されたか
    if($(this).prop('files')[0]){
      var data = new FormData($('#update')[0]);

      console.log("async upload image.");
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
      /*
      var result = uploadImage(data);
      if (result.result == "true") {
        component_service_image_path.setState({t_menu_imagePath: result.image_path});
      }
      else {
        alert('Upload Failed');
      }
      */
    }
  });

});