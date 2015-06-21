$(function(){
  /*
    Component for React
  */
  // コンポーネントの定義
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
      this.setState({t_menu_categoryId: e.target.value});
    },
    render() {
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
    }
  });

  var ServiceName = React.createClass({
    getInitialState() {
      return {
        t_menu_name: ""
      };
    },
    changeText(e) {
      this.setState({t_menu_name: e.target.value});
    },
    render() {
      return (
        <div>
          <input type="text" value={this.state.t_menu_name} onChange={this.changeText} />
        </div>
      );
    }
  });
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
          <img className="service_image_img" src={this.state.t_menu_imagePath} />
        </div>
      );
    }
  });

  var ServiceList = React.createClass({
    getInitialState() {
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
    render() {
      var service = this.state.service_list.map(function(service) {
        return <tr><td>{service.t_menu_menuId}</td><td>{categorys[service.t_menu_categoryId]}</td><td>{service.t_menu_name}</td><td><img src={service.t_menu_imagePath?service.t_menu_imagePath:'img/notfound.jpg'} /></td><td><a className="edit">編集</a>/<a className="delete">削除</a></td></tr>;
      });
      return (
        <div>
          <table>
            <tr><th>No.</th><th>カテゴリー</th><th>サービス名</th><th>写真</th><th>編集</th></tr>
            {service}
          </table>
        </div>
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
  var component_service_category = React.render(<ServiceCategory />, document.getElementById('service_category'));
  var component_service_name = React.render(<ServiceName />, document.getElementById('service_name'));
  var component_service_detail_text = React.render(<ServiceDetailText />, document.getElementById('service_detail_text'));
  var component_service_price = React.render(<ServicePrice />, document.getElementById('service_price'));
  var component_service_image_path = React.render(<ServiceImagePath />, document.getElementById('service_image_path'));
  var component_service_list = React.render(<ServiceList />, document.getElementById('service_list_info'));


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