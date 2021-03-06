$(function(){
  /*
    Component for React
  */
  // コンポーネントの定義
  var CouponKind = React.createClass({
    getDefaultProps() {
      return {
        kind: ['']
      };
    },
    getInitialState() {
      return {
        t_coupon_couponKindId: '',
      };
    },
    onChangeSelectValue(e) {
      this.setState({t_coupon_couponKindId: e.target.value});
    },
    render() {
      var options = this.props.kind.map(function(kind) {
        return <option value={kind.t_couponKind_id}>{kind.t_couponKind_Name}</option>;
      });
      return (
        <div>
          <select value={this.state.t_coupon_couponKindId} onChange={this.onChangeSelectValue}>
            {options}
          </select>
        </div>
      );
    }
  });

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

  var CouponList = React.createClass({
    getInitialState() {
      return {
        coupon_list: [{
          "t_coupon_Id": "",
          "t_coupon_name": "",
          "t_coupon_detailText": "",
          "t_coupon_price": "",
          "t_coupon_imagePath": "img/notfound.jpg"}]
      };
    },
    render() {
      var index = 1;
      var coupon = this.state.coupon_list.map(function(coupon) {
        return <tr><td>{index++}</td><td>{coupon.t_coupon_name}</td><td>{coupon.t_coupon_price}</td><td>{coupon.t_coupon_detailText}</td><td><img src={coupon.t_coupon_imagePath?coupon.t_coupon_imagePath:'img/notfound.jpg'} /></td><td><a className="edit">编辑</a>/<a className="delete">删除</a></td></tr>;
      });
      return (
        <div>
          <table>
            <tr><th>No.</th><th>优惠券名称</th><th>价格</th><th>优惠券内容</th><th>照片</th><th>编辑</th></tr>
            {coupon}
          </table>
        </div>
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
  var component_coupon_kind = React.render(<CouponKind />, document.getElementById('coupon_kind'));
  var component_coupon_name = React.render(<CouponName />, document.getElementById('coupon_name'));
  var component_coupon_detail_text = React.render(<CouponDetailText />, document.getElementById('coupon_detail_text'));
  var component_coupon_price = React.render(<CouponPrice />, document.getElementById('coupon_price'));
  var component_coupon_dead_line = React.render(<CouponDeadLine />, document.getElementById('coupon_dead_line'));
  var component_coupon_presentation_condition = React.render(<CouponPresentationCondition />, document.getElementById('coupon_presentation_condition'));
  var component_coupon_use_condition = React.render(<CouponUseCondition />, document.getElementById('coupon_use_condition'));
  var component_coupon_image_path = React.render(<CouponImagePath />, document.getElementById('coupon_image_path'));
  var component_coupon_list = React.render(<CouponList />, document.getElementById('coupon_list_info'));


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
    if (coupon_info.coupon.length == 0) {
      return false;
    }

    // component set state
    var id = $(".edit").index(this);
    componentSetState(coupon_info.coupon[id]);
  });

  // 削除ボタン押下時
  $('.delete').on('click', function() {
    if (coupon_info.coupon.length == 0) {
      return false;
    }
    
    //削除していいか確認
    var check_delete = false;
    (function disp(){
    	// 「OK」時の処理開始 ＋ 確認ダイアログの表示
    	if(window.confirm('本当に削除しますか？')){
    		console.log("OK clicked.")
    		check_delete = true;
    	}
    	// 「キャンセル」時の処理開始
    	else{
    		alert('キャンセルされました'); // 警告ダイアログを表示
    		check_delete = false;
    	}
    	// 「キャンセル」時の処理終了
    })();
    if(!check_delete) return false;

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
            	  component_coupon_image_path.setState({t_coupon_imagePath: response.image_path});
              }
              else {
                alert('Upload Failed');
              }
          });
      })(data);
      /*
      var result = uploadImage(data);
      if (result.result == "true") {
        component_coupon_image_path.setState({t_coupon_imagePath: result.image_path});
      }
      else {
        alert('Upload Failed');
      }
      */
    }
  });

});