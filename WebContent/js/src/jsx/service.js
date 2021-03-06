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
/*
  var ServiceTime = React.createClass({
	    getInitialState() {
	      return {
	        t_menu_time: ""
	      };
	    },
	    changeText(e) {
	      this.setState({t_menu_time: e.target.value});
	    },
	    render() {
	      return (
	        <div>
	          <input type="text" value={this.state.t_menu_time} onChange={this.changeText} placeholder="分単位"/>
	        </div>
	      );
	    }
	  });
  */
  var ServiceTime = React.createClass({
	    getDefaultProps() {
	      return {
	    	  menu_time: ['10','20', '30', '40', '50', '60',
	    	              '70','80', '90', '100', '110', '120',
	    	              '130','140', '150', '160', '170', '180']
	      };
	    },
	    getInitialState() {
	      return {
	    	  t_menu_time: this.props.menu_time[0],
	      };
	    },
	    onChangeSelectValue(e) {
	      this.setState({t_menu_time: e.target.value});
	    },
	    render() {
	      var options = this.props.menu_time.map(function(menu_time) {
	        return <option value={menu_time}>{menu_time}</option>;
	      });
	      return (
	        <div>
	          <select value={this.state.t_menu_time} onChange={this.onChangeSelectValue}>
	            {options}
	          </select>
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
        return <tr><td>{index++}</td><td>{categorys[service.t_menu_categoryId]}</td><td>{service.t_menu_name}</td><td>{service.t_menu_time}</td><td><img src={service.t_menu_imagePath?service.t_menu_imagePath:'img/notfound.jpg'} /></td><td><a className="edit">编辑</a>/<a className="delete">删除</a></td></tr>;
      });
      return (
        <div>
          <table>
            <tr><th>No.</th><th>分类</th><th>服务名称</th><th>時間</th><th>照片</th><th>编辑</th></tr>
            {service}
          </table>
        </div>
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
  var component_service_category = React.render(<ServiceCategory />, document.getElementById('service_category'));
  var component_service_name = React.render(<ServiceName />, document.getElementById('service_name'));
  var component_service_detail_text = React.render(<ServiceDetailText />, document.getElementById('service_detail_text'));
  var component_service_price = React.render(<ServicePrice />, document.getElementById('service_price'));
  var component_service_time = React.render(<ServiceTime />, document.getElementById('service_time'));
  var component_service_image_path = React.render(<ServiceImagePath />, document.getElementById('service_image_path'));
  var component_service_list = React.render(<ServiceList />, document.getElementById('service_list_info'));


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