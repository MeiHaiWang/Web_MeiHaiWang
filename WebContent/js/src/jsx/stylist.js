$(function(){
  /*
    Component for React
  */
  // コンポーネントの定義
  var StylistName = React.createClass({
    getInitialState() {
      return {
        t_stylist_name: ""
      };
    },
    changeText(e) {
      this.setState({t_stylist_name: e.target.value});
    },
    render() {
      return (
        <div>
          <input type="text" value={this.state.t_stylist_name} onChange={this.changeText} />
        </div>
      );
    }
  });

  var StylistSex = React.createClass({
    getDefaultProps() {
      return {
        sex: [{value: 0, text:'男性'}, {value: 1, text:'女性'}]
      };
    },
    getInitialState() {
      return {
        t_stylist_sex: 0,
      };
    },
    onChangeSelectValue(e) {
      this.setState({t_stylist_sex: e.target.value});
    },
    render() {
      var options = this.props.sex.map(function(sex) {
        return <option value={sex.value}>{sex.text}</option>;
      });
      return (
        <div>
          <select value={this.state.t_stylist_sex} onChange={this.onChangeSelectValue}>
            {options}
          </select>
        </div>
      );
    }
  });

  var StylistBirthYear = React.createClass({
    getDefaultProps() {
      var time = new Date();
      var now_year = time.getFullYear();
      var birth_year = new Array();
      for (var i = now_year; i >= 1950; i--) {
        birth_year.push(i.toString(10));
      }

      return {
        birth_year: birth_year
      };
    },
    getInitialState() {
      return {
        year: this.props.birth_year[0],
      };
    },
    onChangeSelectValue(e) {
      this.setState({year: e.target.value});

      // 年を変えた際に、存在する日のリストを作成する
      var max_day = new Date(e.target.value, component_stylist_birth_month.state.month, 0).getDate();
      var birth_day = new Array();
      var tmp_i;
      for (var i = 1; i <= max_day; i++) {
        tmp_i = i;
        if (tmp_i < 10) {
          tmp_i = "0" + tmp_i;
        }
        birth_day.push(tmp_i.toString(10));
      }
      component_stylist_birth_day.setProps({birth_day: birth_day});
    },
    render() {
      var options = this.props.birth_year.map(function(year) {
        return <option value={year}>{year}</option>;
      });
      return (
        <div>
          <select value={this.state.year} onChange={this.onChangeSelectValue}>
            {options}
          </select>
        </div>
      );
    }
  });

  var StylistBirthMonth = React.createClass({
    getDefaultProps() {
      var birth_month = new Array();
      var tmp_i;
      for (var i = 1; i <= 12; i++) {
        tmp_i = i;
        if (tmp_i < 10) {
          tmp_i = "0" + tmp_i;
        }
        birth_month.push(tmp_i.toString(10));
      }

      return {
        birth_month: birth_month
      };
    },
    getInitialState() {
      return {
        month: this.props.birth_month[0],
      };
    },
    onChangeSelectValue(e) {
      this.setState({month: e.target.value});

      // 月を変えた際に、存在する日のリストを作成する
      var max_day = new Date(component_stylist_birth_year.state.year, e.target.value, 0).getDate();
      var birth_day = new Array();
      var tmp_i;
      for (var i = 1; i <= max_day; i++) {
        tmp_i = i;
        if (tmp_i < 10) {
          tmp_i = "0" + tmp_i;
        }
        birth_day.push(tmp_i.toString(10));
      }
      component_stylist_birth_day.setProps({birth_day: birth_day});
    },
    render() {
      var options = this.props.birth_month.map(function(month) {
        return <option value={month}>{month}</option>;
      });
      return (
        <div>
          <select value={this.state.month} onChange={this.onChangeSelectValue}>
            {options}
          </select>
        </div>
      );
    }
  });

  var StylistBirthDay = React.createClass({
    getDefaultProps() {
      var birth_day = new Array();
      var tmp_i;
      for (var i = 1; i <= 31; i++) {
        tmp_i = i;
        if (tmp_i < 10) {
          tmp_i = "0" + tmp_i;
        }
        birth_day.push(tmp_i.toString(10));
      }

      return {
        birth_day: birth_day
      };
    },
    getInitialState() {
      return {
        day: this.props.birth_day[0],
      };
    },
    onChangeSelectValue(e) {
      this.setState({day: e.target.value});
    },
    render() {
      var options = this.props.birth_day.map(function(day) {
        return <option value={day}>{day}</option>;
      });
      return (
        <div>
          <select value={this.state.day} onChange={this.onChangeSelectValue}>
            {options}
          </select>
        </div>
      );
    }
  });

  var StylistPhoneNumber = React.createClass({
    getInitialState() {
      return {
        t_stylist_phoneNumber: ""
      };
    },
    changeText(e) {
      this.setState({t_stylist_phoneNumber: e.target.value});
    },
    render() {
      return (
        <div>
          <input type="text" value={this.state.t_stylist_phoneNumber} onChange={this.changeText} />
        </div>
      );
    }
  });

  var StylistPosition = React.createClass({
    getInitialState() {
      return {
        t_stylist_position: ""
      };
    },
    changeText(e) {
      this.setState({t_stylist_position: e.target.value});
    },
    render() {
      return (
        <div>
          <input type="text" value={this.state.t_stylist_position} onChange={this.changeText} />
        </div>
      );
    }
  });

  var StylistMail = React.createClass({
    getInitialState() {
      return {
        t_stylist_mail: ""
      };
    },
    changeText(e) {
      this.setState({t_stylist_mail: e.target.value});
    },
    render() {
      return (
        <div>
          <input type="text" value={this.state.t_stylist_mail} onChange={this.changeText} />
        </div>
      );
    }
  });

  var StylistExperienceYear = React.createClass({
    getDefaultProps() {
      var experience_year = new Array();
      for (var i = 0; i <= 30; i++) {
        experience_year.push(i.toString(10));
      }

      return {
        experience_year: experience_year
      };
    },
    getInitialState() {
      return {
        t_stylist_experienceYear: 0,
      };
    },
    onChangeSelectValue(e) {
      this.setState({t_stylist_experienceYear: e.target.value});
    },
    render() {
      var options = this.props.experience_year.map(function(experience_year) {
        return <option value={experience_year}>{experience_year}</option>;
      });
      return (
        <div>
          <select value={this.state.t_stylist_experienceYear} onChange={this.onChangeSelectValue}>
            {options}
          </select>
        </div>
      );
    }
  });

  var StylistSpecialMenu = React.createClass({
    getInitialState() {
      return {
        t_stylist_specialMenu: ""
      };
    },
    changeText(e) {
      this.setState({t_stylist_specialMenu: e.target.value});
    },
    render() {
      return (
        <div>
          <input type="text" value={this.state.t_stylist_specialMenu} onChange={this.changeText} />
        </div>
      );
    }
  });

  var StylistMessage = React.createClass({
    getInitialState() {
      return {
        t_stylist_message: ""
      };
    },
    onChangeText(e) {
      this.setState({t_stylist_message: e.target.value});
    },
    onClick() {
      this.setState({t_stylist_message: this.refs.textArea.getDOMNode().value});
    },
    render() {
      return (
        <div>
          <div>
            <textarea value={this.state.t_stylist_message} onChange={this.onChangeText} />
          </div>
        </div>
      );
    }
  });

  var StylistImagePath = React.createClass({
    getInitialState() {
      return {
        t_stylist_imagePath: "img/notfound.jpg"
      };
    },
    render() {
      return (
        <div>
          <img className="service_image_img" src={this.state.t_stylist_imagePath?this.state.t_stylist_imagePath:'img/notfound.jpg'} />
        </div>
      );
    }
  });

  var StylistList = React.createClass({
    getInitialState() {
      return {
        stylist_list: [{
          "t_stylist_stylist_id": "",
          "t_stylist_name": "",
          "t_stylist_position": "",
          "t_stylist_phoneNumber": "",
          "t_menu_t_menu_id": [""]}]
      };
    },
    render() {
      var stylist = this.state.stylist_list.map(function(stylist) {
        return <tr><td>{stylist.t_stylist_stylist_id}</td><td>{stylist.t_stylist_name}</td><td>{stylist.t_stylist_position}</td><td>{stylist.t_stylist_phoneNumber}</td><td>{stylist.t_menu_t_menu_name}</td><td><a className="edit">編集</a>/<a className="delete">削除</a></td></tr>;
      });
      return (
        <div>
          <table>
            <tr><th>No.</th><th>氏名</th><th>役職</th><th>連絡先</th><th>対応可能サービス</th><th>編集</th></tr>
            {stylist}
          </table>
        </div>
      );
    }
  });

  var StylistServiceMapingName = React.createClass({
    getDefaultProps() {
      return {
        stylists: ['']
      };
    },
    getInitialState() {
      return {
        t_stylist_stylist_id: '',
      };
    },
    onChangeSelectValue(e) {
      this.setState({t_stylist_stylist_id: e.target.value});
    },
    render() {
      var options = this.props.stylists.map(function(stylist) {
        return <option value={stylist.t_stylist_stylist_id}>{stylist.t_stylist_name}</option>;
      });
      return (
        <div>
          <select value={this.state.t_stylist_stylist_id} onChange={this.onChangeSelectValue}>
            {options}
          </select>
        </div>
      );
    }
  });

  var StylistServiceMapingService = React.createClass({
    getDefaultProps() {
      return {
        services: ['']
      };
    },
    getInitialState() {
      return {
        t_menu_menuId: '',
      };
    },
    onChangeSelectValue(e) {
      this.setState({t_menu_menuId: e.target.value});
    },
    render() {
      var options = this.props.services.map(function(services) {
        return <option value={services.t_menu_menuId}>{services.t_menu_name}</option>;
      });
      return (
        <div>
          <select value={this.state.t_menu_menuId} onChange={this.onChangeSelectValue}>
            {options}
          </select>
        </div>
      );
    }
  });

  // set state to component
  function componentSetState(stylist) {
    // MysqlのDATETIME型から年月日に変換する
    var ymd = getYearMonthDayByDateTime(stylist.t_stylist_birth);

    // コンポーネントにjsonを渡して関係する部分だけ書き換わる
    component_stylist_name.setState(stylist);
    component_stylist_sex.setState(stylist);
    component_stylist_birth_year.setState(ymd);
    component_stylist_birth_month.setState(ymd);
    component_stylist_birth_day.setState(ymd);
    component_stylist_phone_number.setState(stylist);
    component_stylist_position.setState(stylist);
    component_stylist_mail.setState(stylist);
    component_stylist_experience_year.setState(stylist);
    component_stylist_special_menu.setState(stylist);
    component_stylist_message.setState(stylist);
    component_stylist_image_path.setState(stylist);
  }


  /*
    Component Render
  */
  // コンポーネントをエレメントに割り当てる
  var component_stylist_name = React.render(<StylistName />, document.getElementById('stylist_name'));
  var component_stylist_sex = React.render(<StylistSex />, document.getElementById('stylist_sex'));
  var component_stylist_birth_year = React.render(<StylistBirthYear />, document.getElementById('stylist_birth_year'));
  var component_stylist_birth_month = React.render(<StylistBirthMonth />, document.getElementById('stylist_birth_month'));
  var component_stylist_birth_day = React.render(<StylistBirthDay />, document.getElementById('stylist_birth_day'));
  var component_stylist_phone_number = React.render(<StylistPhoneNumber />, document.getElementById('stylist_phone_number'));
  var component_stylist_position = React.render(<StylistPosition />, document.getElementById('stylist_position'));
  var component_stylist_mail = React.render(<StylistMail />, document.getElementById('stylist_mail'));
  var component_stylist_experience_year = React.render(<StylistExperienceYear />, document.getElementById('stylist_experience_year'));
  var component_stylist_special_menu = React.render(<StylistSpecialMenu />, document.getElementById('stylist_special_menu'));
  var component_stylist_message = React.render(<StylistMessage />, document.getElementById('stylist_message'));
  var component_stylist_image_path = React.render(<StylistImagePath />, document.getElementById('stylist_image_path'));
  var component_stylist_list = React.render(<StylistList />, document.getElementById('stylist_list_info'));
  var component_stylist_service_maping_name = React.render(<StylistServiceMapingName />, document.getElementById('stylist_service_maping_name'));
  var component_stylist_service_maping_service = React.render(<StylistServiceMapingService />, document.getElementById('stylist_service_maping_service'));


  /*
    Main Part
  */
  // セッションIDからスタッフ情報を取得する
  var session_info = getSessionInfo();
  var stylist_info = getStaffInfo(session_info.t_hairSalonMaster_salonId);
  sanitaize.decode(stylist_info);

  // アカウント名を表示
  $('#account-name').text(session_info.t_hairSalonMaster_contactUserName);

  // サロンIDに紐づくサービス一覧を取得する
  var service_list = getServiceList(session_info.t_hairSalonMaster_salonId);

  sanitaize.decode(service_list);

  // service_listを参照しやすい形に変換
  var services = new Array();
  for (var i = 0; i < service_list.menu.length; i++) {
    var service_id = service_list.menu[i].t_menu_menu_id;
    var service_name = service_list.menu[i].t_menu_name;
    services[service_id] = service_name;
  }

  // スタッフの対応可能サービス一覧をIDから文字列に変換
  for (var i = 0; i < stylist_info.stylist.length; i++) {
    var menu_ids = stylist_info.stylist[i].t_menu_t_menu_id.split(',');
    var menu_name = new Array();
    for (var j = 0; j < menu_ids.length; j++) {
      menu_name.push(services[menu_ids[j]]);
    }
    stylist_info.stylist[i].t_menu_t_menu_name = menu_name.join(',');
  }


  // service maping
  component_stylist_service_maping_name.setProps({stylists: stylist_info.stylist});
  component_stylist_service_maping_service.setProps({services: service_list.menu});

  // スタッフ一覧
  if (stylist_info.stylist.length != 0) {
    component_stylist_list.setState({"stylist_list":stylist_info.stylist});
    component_stylist_service_maping_name.setState(stylist_info.stylist[0]);
    component_stylist_service_maping_service.setState(service_list.menu[0]);
  }


  /*
    Button Handler
  */
  // 登録ボタン押下時
  $('#stylist_regist_button').on('click', function() {
    var birthday =
      component_stylist_birth_year.state.year   + '-' +
      component_stylist_birth_month.state.month + '-' +
      component_stylist_birth_day.state.day + ' 00:00:00';
    var data = {
      t_hairSalonMaster_salonId: session_info.t_hairSalonMaster_salonId,
      t_stylist_Id:              component_stylist_name.state.t_stylist_stylist_id,
      t_stylist_name:            component_stylist_name.state.t_stylist_name,
      t_stylist_sex:             component_stylist_sex.state.t_stylist_sex,
      t_stylist_phoneNumber:     component_stylist_phone_number.state.t_stylist_phoneNumber,
      t_stylist_mail:            component_stylist_mail.state.t_stylist_mail,
      t_stylist_imagePath:       component_stylist_image_path.state.t_stylist_imagePath,
      t_stylist_birth:           birthday,
      t_stylist_position:        component_stylist_position.state.t_stylist_position,
      t_stylist_experienceYear:  component_stylist_experience_year.state.t_stylist_experienceYear,
      t_stylist_specialMenu:     component_stylist_special_menu.state.t_stylist_specialMenu,
      t_stylist_message:         component_stylist_message.state.t_stylist_message,
    };

    // サニタイズ
    sanitaize.encode(data);

    var result = setStaffInfo(data);
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
    componentSetState(stylist_info.stylist[id]);
  });

  // 削除ボタン押下時
  $('.delete').on('click', function() {
    var id = $(".delete").index(this);
    var data = {t_stylist_Id: stylist_info.stylist[id].t_stylist_stylist_id};

    // サニタイズ
    sanitaize.encode(data);

    var result = deleteStaffInfo(data);
    if (result.result == "true") {
      alert('Delete Success');
      window.location.reload();
    }
    else {
      alert('Delete Failed');
    }
  });

  // ServiceMaping登録ボタン押下時
  $('#stylist_service_maping_regist_button').on('click', function() {
    var id = $('#stylist_service_maping_name select').prop("selectedIndex");
    var stylist_id = component_stylist_service_maping_name.state.t_stylist_stylist_id;

    // スタッフの対応可能サービス一覧を追加する
    var menu_ids = stylist_info.stylist[id].t_menu_t_menu_id.split(',');
    var regist_menu_id = component_stylist_service_maping_service.state.t_menu_menu_id;
    var menu_id = new Array();
    for (var i = 0; i < menu_ids.length; i++) {
      menu_id.push(menu_ids[i]);
    }

    // すでに登録されているサービスの場合はエラー
    if (menu_id.indexOf(regist_menu_id) !== -1) {
      alert('Already exists');
    }
    else {
      menu_id.push(regist_menu_id);
      menu_id = menu_id.join(',');

      var data = {
        t_stylist_Id:     stylist_id,
        t_menu_t_menu_id: menu_id,
      };

      // サニタイズ
      sanitaize.encode(data);

      var result = setStaffMenu(data);
      if (result.result == "true") {
        alert('Regist Success');
        window.location.reload();
      }
      else {
        alert('Regist Failed');
      }
    }
  });

  // ServiceMaping削除ボタン押下時
  $('#stylist_service_maping_delete_button').on('click', function() {

    var stylist_id = component_stylist_service_maping_name.state.t_stylist_stylist_id;

    var data = {
      t_stylist_Id: stylist_id,
    };

    // サニタイズ
    sanitaize.encode(data);

    var result = deleteStaffMenu(data);
    if (result.result == "true") {
      alert('Delete Success');
      window.location.reload();
    }
    else {
      alert('Delete Failed');
    }
  });

  // 画像アップロード
  $('#stylist_image').change(function() {
    // ファイルが選択されたか
    if($(this).prop('files')[0]){
      var data = new FormData($('#update')[0]);

      var result = uploadImage(data);
      if (result.result == "true") {
        component_stylist_image_path.setState({t_stylist_imagePath: result.image_path});
      }
      else {
        alert('Upload Failed');
      }
    }
  });

});