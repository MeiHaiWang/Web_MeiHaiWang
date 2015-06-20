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
        t_menuCategory_categoryId: '',
      };
    },
    onChangeSelectValue(e) {
      this.setState({t_menuCategory_categoryId: e.target.value});
    },
    render() {
      var options = this.props.category.map(function(category) {
        return <option value={category.t_menuCategory_categoryId}>{category.t_menuCategory_name}</option>;
      });
      return (
        <div>
          <select value={this.state.t_menuCategory_categoryId} onChange={this.onChangeSelectValue}>
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
          <img className="salon_image_img" src={this.state.t_menu_imagePath} />
        </div>
      );
    }
  });



  /*
    Component Render
  */
  // コンポーネントをエレメントに割り当てる
  var component_service_category = React.render(<ServiceCategory />, document.getElementById('service_category'));
  var component_service_name = React.render(<ServiceName />, document.getElementById('service_name'));
  var component_service_detail_text = React.render(<ServiceDetailText />, document.getElementById('service_detail_text'));
  var component_service_price = React.render(<ServicePrice />, document.getElementById('service_price'));
  var component_service_image_path = React.render(<ServiceImagePath />, document.getElementById('service_image_path'));


  /*
    Main Part
  */
  // セッションIDからサービス情報を取得する
  var session_info = getSessionInfo();
  var service_info = getMenuInfo(session_info.t_hairSalonMaster_salonId);

  var service_category_info = getServiceCategoryList();

  // コンポーネントにjsonを渡して関係する部分だけ書き換わる
  component_service_category.setState(service_info.menu[0]);
  component_service_category.setProps({category: service_category_info.category});
  component_service_name.setState(service_info.menu[0]);
  component_service_detail_text.setState(service_info.menu[0]);
  component_service_price.setState(service_info.menu[0]);
  component_service_image_path.setState(service_info.menu[0]);
});