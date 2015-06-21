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
          "t_menu_categoryId": "&nbsp;",
          "t_menu_menuId": "&nbsp;",
          "t_menu_name": "&nbsp;",
          "t_menu_price": "&nbsp;",
          "t_menu_detailText": "&nbsp;",
          "t_menu_imagePath": "img/notfound.jpg"}]
      };
    },
    render:function() {
      var service = this.state.service_list.map(function(service) {
        return React.createElement("tr", null, React.createElement("td", null, service.t_menu_menuId), React.createElement("td", null, categorys[service.t_menu_categoryId]), React.createElement("td", null, service.t_menu_name), React.createElement("td", null, React.createElement("img", {src: service.t_menu_imagePath?service.t_menu_imagePath:'img/notfound.jpg'})), React.createElement("td", null, React.createElement("a", {className: "edit"}, "編集"), "/", React.createElement("a", {className: "delete"}, "削除")));
      });
      return (
        React.createElement("div", null, 
          React.createElement("table", null, 
            React.createElement("tr", null, React.createElement("th", null, "No."), React.createElement("th", null, "カテゴリー"), React.createElement("th", null, "サービス名"), React.createElement("th", null, "写真"), React.createElement("th", null, "編集")), 
            service
          )
        )
      );
    }
  });


  /*
    List
  */
  var service_category_info = getServiceCategoryList();
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
  var component_service_image_path = React.render(React.createElement(ServiceImagePath, null), document.getElementById('service_image_path'));
  var component_service_list = React.render(React.createElement(ServiceList, null), document.getElementById('service_list_info'));


  /*
    Main Part
  */
  // セッションIDからサービス情報を取得する
  var session_info = getSessionInfo();
  var service_info = getMenuInfo(session_info.t_hairSalonMaster_salonId);


  // コンポーネントにjsonを渡して関係する部分だけ書き換わる
  component_service_category.setState(service_info.menu[0]);
  component_service_category.setProps({category: service_category_info.category});
  component_service_name.setState(service_info.menu[0]);
  component_service_detail_text.setState(service_info.menu[0]);
  component_service_price.setState(service_info.menu[0]);
  component_service_image_path.setState(service_info.menu[0]);

  // メニュー一覧
  component_service_list.setState({"service_list":service_info.menu});

});