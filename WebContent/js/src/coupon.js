$(function(){
  /*
    Component for React
  */
  // コンポーネントの定義
  var CouponKind = React.createClass({displayName: "CouponKind",
    getDefaultProps:function() {
      return {
        kind: ['']
      };
    },
    getInitialState:function() {
      return {
        t_coupon_couponKindId: '',
      };
    },
    onChangeSelectValue:function(e) {
      this.setState({t_coupon_couponKindId: e.target.value});
    },
    render:function() {
      var options = this.props.kind.map(function(kind) {
        return React.createElement("option", {value: kind.t_couponKind_id}, kind.t_couponKind_Name);
      });
      return (
        React.createElement("div", null, 
          React.createElement("select", {value: this.state.t_coupon_couponKindId, onChange: this.onChangeSelectValue}, 
            options
          )
        )
      );
    }
  });

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

  var CouponList = React.createClass({displayName: "CouponList",
    getInitialState:function() {
      return {
        coupon_list: [{
          "t_coupon_Id": "",
          "t_coupon_name": "",
          "t_coupon_detailText": "",
          "t_coupon_price": "",
          "t_coupon_imagePath": "img/notfound.jpg"}]
      };
    },
    render:function() {
      var coupon = this.state.coupon_list.map(function(coupon) {
        return React.createElement("tr", null, React.createElement("td", null, coupon.t_coupon_Id), React.createElement("td", null, coupon.t_coupon_name), React.createElement("td", null, coupon.t_coupon_price), React.createElement("td", null, coupon.t_coupon_detailText), React.createElement("td", null, React.createElement("img", {src: coupon.t_coupon_imagePath?coupon.t_coupon_imagePath:'img/notfound.jpg'})), React.createElement("td", null, React.createElement("a", {className: "edit"}, "編集"), "/", React.createElement("a", {className: "delete"}, "削除")));
      });
      return (
        React.createElement("div", null, 
          React.createElement("table", null, 
            React.createElement("tr", null, React.createElement("th", null, "No."), React.createElement("th", null, "クーポン名"), React.createElement("th", null, "価格"), React.createElement("th", null, "クーポン内容"), React.createElement("th", null, "写真"), React.createElement("th", null, "編集")), 
            coupon
          )
        )
      );
    }
  });

  // set state to component
  function componentSetState(coupon) {
    // コンポーネントにjsonを渡して関係する部分だけ書き換わる
    component_coupon_kind.setState(coupon);
    component_coupon_name.setState(coupon);
    component_coupon_detail_text.setState(coupon);
    component_coupon_price.setState(coupon);
    component_coupon_dead_line.setState(coupon);
    component_coupon_presentation_condition.setState(coupon);
    component_coupon_use_condition.setState(coupon);
    component_coupon_image_path.setState(coupon);
  }


  /*
    Component Render
  */
  // コンポーネントをエレメントに割り当てる
  var component_coupon_kind = React.render(React.createElement(CouponKind, null), document.getElementById('coupon_kind'));
  var component_coupon_name = React.render(React.createElement(CouponName, null), document.getElementById('coupon_name'));
  var component_coupon_detail_text = React.render(React.createElement(CouponDetailText, null), document.getElementById('coupon_detail_text'));
  var component_coupon_price = React.render(React.createElement(CouponPrice, null), document.getElementById('coupon_price'));
  var component_coupon_dead_line = React.render(React.createElement(CouponDeadLine, null), document.getElementById('coupon_dead_line'));
  var component_coupon_presentation_condition = React.render(React.createElement(CouponPresentationCondition, null), document.getElementById('coupon_presentation_condition'));
  var component_coupon_use_condition = React.render(React.createElement(CouponUseCondition, null), document.getElementById('coupon_use_condition'));
  var component_coupon_image_path = React.render(React.createElement(CouponImagePath, null), document.getElementById('coupon_image_path'));
  var component_coupon_list = React.render(React.createElement(CouponList, null), document.getElementById('coupon_list_info'));


  /*
    Main Part
  */
  // セッションIDからクーポン情報を取得する
  var session_info = getSessionInfo();
  var coupon_info = getCouponInfo(session_info.t_hairSalonMaster_salonId);
  var coupon_kind_info = getCouponKindList();
  sanitaize.decode(coupon_info);
  sanitaize.decode(coupon_kind_info);

  // アカウント名を表示
  $('#account-name').text(session_info.t_hairSalonMaster_contactUserName);

  // set component
  component_coupon_kind.setProps({kind: coupon_kind_info.kind});
  component_coupon_kind.setState({t_coupon_couponKindId: component_coupon_kind.props.kind[0].t_couponKind_id});

  // クーポン一覧
  if (coupon_info.coupon.length != 0) {
    component_coupon_list.setState({"coupon_list":coupon_info.coupon});
  }

  /*
    Button Handler
  */
  // 登録ボタン押下時
  $('#coupon_regist_button').on('click', function() {
    var data = {
      t_hairSalonMaster_salonId:      session_info.t_hairSalonMaster_salonId,
      t_coupon_Id:                    component_coupon_kind.state.t_coupon_Id,
      t_coupon_couponKindId:          component_coupon_kind.state.t_coupon_couponKindId,
      t_coupon_name:                  component_coupon_name.state.t_coupon_name,
      t_coupon_detailText:            component_coupon_detail_text.state.t_coupon_detailText,
      t_coupon_price:                 component_coupon_price.state.t_coupon_price,
      t_coupon_deadLine:              component_coupon_dead_line.state.t_coupon_deadLine,
      t_coupon_presentationCondition: component_coupon_presentation_condition.state.t_coupon_presentationCondition,
      t_coupon_useCondition:          component_coupon_use_condition.state.t_coupon_useCondition,
      t_coupon_imagePath:             component_coupon_image_path.state.t_coupon_imagePath,
    }

    // サニタイズ
    sanitaize.encode(data);

    var result = setCouponInfo(data);
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
    // component set state
    var id = $(".edit").index(this);
    componentSetState(coupon_info.coupon[id]);
  });

  // 削除ボタン押下時
  $('.delete').on('click', function() {
    var id = $(".delete").index(this);
    var data = {t_coupon_Id: coupon_info.coupon[id].t_coupon_Id};

    // サニタイズ
    sanitaize.encode(data);

    var result = deleteCouponInfo(data);
    if (result.result == "true") {
      alert('Delete Success');
      window.location.reload();
    }
    else {
      alert('Delete Failed');
    }
  });

  // 画像アップロード
  $('#coupon_image').change(function() {
    // ファイルが選択されたか
    if($(this).prop('files')[0]){
      var fd = new FormData($('#update')[0]);

      var data = {
        t_hairSalonMaster_salonId: session_info.t_hairSalonMaster_salonId,
        file: fd,
      }
      var result = uploadImage(data);
      if (result.result == "true") {
        component_coupon_image_path.setState({t_coupon_imagePath: result.image_path});
      }
      else {
        alert('Upload Failed');
      }
    }
  });

});