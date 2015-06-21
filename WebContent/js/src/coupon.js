$(function(){
  /*
    Component for React
  */
  // コンポーネントの定義
  var CouponName = React.createClass({displayName: "CouponName",
    getInitialState:function() {
      return {
        t_coupon_name: ""
      };
    },
    changeText:function(e) {
      this.setState({t_coupon_name: e.target.value});
    },
    render:function() {
      return (
        React.createElement("div", null, 
          React.createElement("input", {type: "text", value: this.state.t_coupon_name, onChange: this.changeText})
        )
      );
    }
  });

  var CouponDetailText = React.createClass({displayName: "CouponDetailText",
    getInitialState:function() {
      return {
        t_coupon_detailText: ""
      };
    },
    onChangeText:function(e) {
      this.setState({t_coupon_detailText: e.target.value});
    },
    onClick:function() {
      this.setState({t_coupon_detailText: this.refs.textArea.getDOMNode().value});
    },
    render:function() {
      return (
        React.createElement("div", null, 
          React.createElement("div", null, 
            React.createElement("textarea", {value: this.state.t_coupon_detailText, onChange: this.onChangeText})
          )
        )
      );
    }
  });

  var CouponPrice = React.createClass({displayName: "CouponPrice",
    getInitialState:function() {
      return {
        t_coupon_price: ""
      };
    },
    changeText:function(e) {
      this.setState({t_coupon_price: e.target.value});
    },
    render:function() {
      return (
        React.createElement("div", null, 
          React.createElement("input", {type: "text", value: this.state.t_coupon_price, onChange: this.changeText})
        )
      );
    }
  });

  var CouponDeadLine = React.createClass({displayName: "CouponDeadLine",
    getInitialState:function() {
      return {
        t_coupon_deadLine: ""
      };
    },
    changeText:function(e) {
      this.setState({t_coupon_deadLine: e.target.value});
    },
    render:function() {
      return (
        React.createElement("div", null, 
          React.createElement("input", {type: "text", value: this.state.t_coupon_deadLine, onChange: this.changeText})
        )
      );
    }
  });

  var CouponPresentationCondition = React.createClass({displayName: "CouponPresentationCondition",
    getInitialState:function() {
      return {
        t_coupon_presentationCondition: ""
      };
    },
    changeText:function(e) {
      this.setState({t_coupon_presentationCondition: e.target.value});
    },
    render:function() {
      return (
        React.createElement("div", null, 
          React.createElement("input", {type: "text", value: this.state.t_coupon_presentationCondition, onChange: this.changeText})
        )
      );
    }
  });

  var CouponUseCondition = React.createClass({displayName: "CouponUseCondition",
    getInitialState:function() {
      return {
        t_coupon_useCondition: ""
      };
    },
    changeText:function(e) {
      this.setState({t_coupon_useCondition: e.target.value});
    },
    render:function() {
      return (
        React.createElement("div", null, 
          React.createElement("input", {type: "text", value: this.state.t_coupon_useCondition, onChange: this.changeText})
        )
      );
    }
  });

  var CouponImagePath = React.createClass({displayName: "CouponImagePath",
    getInitialState:function() {
      return {
        t_coupon_imagePath: "img/notfound.jpg"
      };
    },
    render:function() {
      return (
        React.createElement("div", null, 
          React.createElement("img", {className: "coupon_image_img", src: this.state.t_coupon_imagePath?this.state.t_coupon_imagePath:'img/notfound.jpg'})
        )
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
  var component_coupon_name = React.render(React.createElement(CouponName, null), document.getElementById('coupon_name'));
  var component_coupon_detail_text = React.render(React.createElement(CouponDetailText, null), document.getElementById('coupon_detail_text'));
  var component_coupon_price = React.render(React.createElement(CouponPrice, null), document.getElementById('coupon_price'));
  var component_coupon_dead_line = React.render(React.createElement(CouponDeadLine, null), document.getElementById('coupon_dead_line'));
  var component_coupon_presentation_condition = React.render(React.createElement(CouponPresentationCondition, null), document.getElementById('coupon_presentation_condition'));
  var component_coupon_use_condition = React.render(React.createElement(CouponUseCondition, null), document.getElementById('coupon_use_condition'));
  var component_coupon_image_path = React.render(React.createElement(CouponImagePath, null), document.getElementById('coupon_image_path'));

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