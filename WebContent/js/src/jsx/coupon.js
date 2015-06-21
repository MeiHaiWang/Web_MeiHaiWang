$(function(){
  /*
    Component for React
  */
  // コンポーネントの定義
  var CouponName = React.createClass({
    getInitialState() {
      return {
        t_coupon_name: ""
      };
    },
    changeText(e) {
      this.setState({t_coupon_name: e.target.value});
    },
    render() {
      return (
        <div>
          <input type="text" value={this.state.t_coupon_name} onChange={this.changeText} />
        </div>
      );
    }
  });

  var CouponDetailText = React.createClass({
    getInitialState() {
      return {
        t_coupon_detailText: ""
      };
    },
    onChangeText(e) {
      this.setState({t_coupon_detailText: e.target.value});
    },
    onClick() {
      this.setState({t_coupon_detailText: this.refs.textArea.getDOMNode().value});
    },
    render() {
      return (
        <div>
          <div>
            <textarea value={this.state.t_coupon_detailText} onChange={this.onChangeText} />
          </div>
        </div>
      );
    }
  });

  var CouponPrice = React.createClass({
    getInitialState() {
      return {
        t_coupon_price: ""
      };
    },
    changeText(e) {
      this.setState({t_coupon_price: e.target.value});
    },
    render() {
      return (
        <div>
          <input type="text" value={this.state.t_coupon_price} onChange={this.changeText} />
        </div>
      );
    }
  });

  var CouponDeadLine = React.createClass({
    getInitialState() {
      return {
        t_coupon_deadLine: ""
      };
    },
    changeText(e) {
      this.setState({t_coupon_deadLine: e.target.value});
    },
    render() {
      return (
        <div>
          <input type="text" value={this.state.t_coupon_deadLine} onChange={this.changeText} />
        </div>
      );
    }
  });

  var CouponPresentationCondition = React.createClass({
    getInitialState() {
      return {
        t_coupon_presentationCondition: ""
      };
    },
    changeText(e) {
      this.setState({t_coupon_presentationCondition: e.target.value});
    },
    render() {
      return (
        <div>
          <input type="text" value={this.state.t_coupon_presentationCondition} onChange={this.changeText} />
        </div>
      );
    }
  });

  var CouponUseCondition = React.createClass({
    getInitialState() {
      return {
        t_coupon_useCondition: ""
      };
    },
    changeText(e) {
      this.setState({t_coupon_useCondition: e.target.value});
    },
    render() {
      return (
        <div>
          <input type="text" value={this.state.t_coupon_useCondition} onChange={this.changeText} />
        </div>
      );
    }
  });

  var CouponImagePath = React.createClass({
    getInitialState() {
      return {
        t_coupon_imagePath: "img/notfound.jpg"
      };
    },
    render() {
      return (
        <div>
          <img className="coupon_image_img" src={this.state.t_coupon_imagePath?this.state.t_coupon_imagePath:'img/notfound.jpg'} />
        </div>
      );
    }
  });


  /*
    List
  */
  // var service_category_info = getServiceCategoryList();
  // // categoryを参照しやすい形に変換
  // var categorys = new Array();
  // for (var i = 0; i < service_category_info.category.length; i++) {
  //   var category_id = service_category_info.category[i].t_menuCategory_categoryId;
  //   var category_name = service_category_info.category[i].t_menuCategory_name;
  //   categorys[category_id] = category_name;
  // }


  /*
    Component Render
  */
  // コンポーネントをエレメントに割り当てる
  var component_coupon_name = React.render(<CouponName />, document.getElementById('coupon_name'));
  var component_coupon_detail_text = React.render(<CouponDetailText />, document.getElementById('coupon_detail_text'));
  var component_coupon_price = React.render(<CouponPrice />, document.getElementById('coupon_price'));
  var component_coupon_dead_line = React.render(<CouponDeadLine />, document.getElementById('coupon_dead_line'));
  var component_coupon_presentation_condition = React.render(<CouponPresentationCondition />, document.getElementById('coupon_presentation_condition'));
  var component_coupon_use_condition = React.render(<CouponUseCondition />, document.getElementById('coupon_use_condition'));
  var component_coupon_image_path = React.render(<CouponImagePath />, document.getElementById('coupon_image_path'));

  /*
    Main Part
  */
  // セッションIDからサービス情報を取得する
  var session_info = getSessionInfo();
  var coupon_info = getCouponInfo(session_info.t_hairSalonMaster_salonId);


  // コンポーネントにjsonを渡して関係する部分だけ書き換わる
  component_coupon_name.setState(coupon_info.coupon[0]);
  component_coupon_detail_text.setState(coupon_info.coupon[0]);
  component_coupon_price.setState(coupon_info.coupon[0]);
  component_coupon_dead_line.setState(coupon_info.coupon[0]);
  component_coupon_presentation_condition.setState(coupon_info.coupon[0]);
  component_coupon_use_condition.setState(coupon_info.coupon[0]);
  component_coupon_image_path.setState(coupon_info.coupon[0]);

});