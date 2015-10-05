$(function(){
  /*
    Component for React
  */
  // コンポーネントの定義
  var StylistName = React.createClass({displayName: "StylistName",
    getInitialState:function() {
      return {
        t_stylist_name: ""
      };
    },
    changeText:function(e) {
      this.setState({t_stylist_name: e.target.value});
    },
    render:function() {
      return (
        React.createElement("div", null, 
          React.createElement("input", {type: "text", value: this.state.t_stylist_name, onChange: this.changeText})
        )
      );
    }
  });

  var StylistSex = React.createClass({displayName: "StylistSex",
    getDefaultProps:function() {
      return {
        sex: [{value: 0, text:'男士'}, {value: 1, text:'女士'}]
      };
    },
    getInitialState:function() {
      return {
        t_stylist_sex: 0,
      };
    },
    onChangeSelectValue:function(e) {
      this.setState({t_stylist_sex: e.target.value});
    },
    render:function() {
      var options = this.props.sex.map(function(sex) {
        return React.createElement("option", {value: sex.value}, sex.text);
      });
      return (
        React.createElement("div", null, 
          React.createElement("select", {value: this.state.t_stylist_sex, onChange: this.onChangeSelectValue}, 
            options
          )
        )
      );
    }
  });

  var StylistBirthYear = React.createClass({displayName: "StylistBirthYear",
    getDefaultProps:function() {
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
    getInitialState:function() {
      return {
        year: this.props.birth_year[0],
      };
    },
    onChangeSelectValue:function(e) {
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
    render:function() {
      var options = this.props.birth_year.map(function(year) {
        return React.createElement("option", {value: year}, year);
      });
      return (
        React.createElement("div", null, 
          React.createElement("select", {value: this.state.year, onChange: this.onChangeSelectValue}, 
            options
          )
        )
      );
    }
  });

  var StylistBirthMonth = React.createClass({displayName: "StylistBirthMonth",
    getDefaultProps:function() {
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
    getInitialState:function() {
      return {
        month: this.props.birth_month[0],
      };
    },
    onChangeSelectValue:function(e) {
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
    render:function() {
      var options = this.props.birth_month.map(function(month) {
        return React.createElement("option", {value: month}, month);
      });
      return (
        React.createElement("div", null, 
          React.createElement("select", {value: this.state.month, onChange: this.onChangeSelectValue}, 
            options
          )
        )
      );
    }
  });

  var StylistBirthDay = React.createClass({displayName: "StylistBirthDay",
    getDefaultProps:function() {
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
    getInitialState:function() {
      return {
        day: this.props.birth_day[0],
      };
    },
    onChangeSelectValue:function(e) {
      this.setState({day: e.target.value});
    },
    render:function() {
      var options = this.props.birth_day.map(function(day) {
        return React.createElement("option", {value: day}, day);
      });
      return (
        React.createElement("div", null, 
          React.createElement("select", {value: this.state.day, onChange: this.onChangeSelectValue}, 
            options
          )
        )
      );
    }
  });

  var StylistPhoneNumber = React.createClass({displayName: "StylistPhoneNumber",
    getInitialState:function() {
      return {
        t_stylist_phoneNumber: ""
      };
    },
    changeText:function(e) {
      this.setState({t_stylist_phoneNumber: e.target.value});
    },
    render:function() {
      return (
        React.createElement("div", null, 
          React.createElement("input", {type: "text", value: this.state.t_stylist_phoneNumber, onChange: this.changeText})
        )
      );
    }
  });

  var StylistPosition = React.createClass({displayName: "StylistPosition",
    getInitialState:function() {
      return {
        t_stylist_position: ""
      };
    },
    changeText:function(e) {
      this.setState({t_stylist_position: e.target.value});
    },
    render:function() {
      return (
        React.createElement("div", null, 
          React.createElement("input", {type: "text", value: this.state.t_stylist_position, onChange: this.changeText})
        )
      );
    }
  });

  var StylistMail = React.createClass({displayName: "StylistMail",
    getInitialState:function() {
      return {
        t_stylist_mail: ""
      };
    },
    changeText:function(e) {
      this.setState({t_stylist_mail: e.target.value});
    },
    render:function() {
      return (
        React.createElement("div", null, 
          React.createElement("input", {type: "text", value: this.state.t_stylist_mail, onChange: this.changeText})
        )
      );
    }
  });

  var StylistExperienceYear = React.createClass({displayName: "StylistExperienceYear",
    getDefaultProps:function() {
      var experience_year = new Array();
      for (var i = 0; i <= 30; i++) {
        experience_year.push(i.toString(10));
      }

      return {
        experience_year: experience_year
      };
    },
    getInitialState:function() {
      return {
        t_stylist_experienceYear: 0,
      };
    },
    onChangeSelectValue:function(e) {
      this.setState({t_stylist_experienceYear: e.target.value});
    },
    render:function() {
      var options = this.props.experience_year.map(function(experience_year) {
        return React.createElement("option", {value: experience_year}, experience_year);
      });
      return (
        React.createElement("div", null, 
          React.createElement("select", {value: this.state.t_stylist_experienceYear, onChange: this.onChangeSelectValue}, 
            options
          )
        )
      );
    }
  });

  var StylistSpecialMenu = React.createClass({displayName: "StylistSpecialMenu",
    getInitialState:function() {
      return {
        t_stylist_specialMenu: ""
      };
    },
    changeText:function(e) {
      this.setState({t_stylist_specialMenu: e.target.value});
    },
    render:function() {
      return (
        React.createElement("div", null, 
          React.createElement("input", {type: "text", value: this.state.t_stylist_specialMenu, onChange: this.changeText})
        )
      );
    }
  });

  var StylistMessage = React.createClass({displayName: "StylistMessage",
    getInitialState:function() {
      return {
        t_stylist_message: ""
      };
    },
    onChangeText:function(e) {
      this.setState({t_stylist_message: e.target.value});
    },
    onClick:function() {
      this.setState({t_stylist_message: this.refs.textArea.getDOMNode().value});
    },
    render:function() {
      return (
        React.createElement("div", null, 
          React.createElement("div", null, 
            React.createElement("textarea", {value: this.state.t_stylist_message, onChange: this.onChangeText})
          )
        )
      );
    }
  });

  var StylistImagePath = React.createClass({displayName: "StylistImagePath",
    getInitialState:function() {
      return {
        t_stylist_imagePath: "img/notfound.jpg"
      };
    },
    render:function() {
      return (
        React.createElement("div", null, 
          React.createElement("img", {className: "service_image_img", src: this.state.t_stylist_imagePath?this.state.t_stylist_imagePath:'img/notfound.jpg'})
        )
      );
    }
  });

  var StylistList = React.createClass({displayName: "StylistList",
    getInitialState:function() {
      return {
        stylist_list: [{
          "t_stylist_stylist_id": "",
          "t_stylist_name": "",
          "t_stylist_position": "",
          "t_stylist_phoneNumber": "",
          "t_menu_t_menu_id": [""]}]
      };
    },
    render:function() {
      var index = 1;
      var stylist = this.state.stylist_list.map(function(stylist) {
        return React.createElement("tr", null, React.createElement("td", null, index++), React.createElement("td", null, stylist.t_stylist_name), React.createElement("td", null, stylist.t_stylist_position), React.createElement("td", null, stylist.t_stylist_phoneNumber), React.createElement("td", null, stylist.t_menu_t_menu_name), React.createElement("td", null, React.createElement("a", {className: "edit"}, "编辑"), "/", React.createElement("a", {className: "delete"}, "删除")));
      });
      return (
        React.createElement("div", null, 
          React.createElement("table", null, 
            React.createElement("tr", null, React.createElement("th", null, "No."), React.createElement("th", null, "姓名"), React.createElement("th", null, "职位"), React.createElement("th", null, "电话号码"), React.createElement("th", null, "可能服务"), React.createElement("th", null, "编辑")), 
            stylist
          )
        )
      );
    }
  });

  var StylistServiceMapingName = React.createClass({displayName: "StylistServiceMapingName",
    getDefaultProps:function() {
      return {
        stylists: ['']
      };
    },
    getInitialState:function() {
      return {
        t_stylist_stylist_id: '',
      };
    },
    onChangeSelectValue:function(e) {
      this.setState({t_stylist_stylist_id: e.target.value});
    },
    render:function() {
      var options = this.props.stylists.map(function(stylist) {
        return React.createElement("option", {value: stylist.t_stylist_stylist_id}, stylist.t_stylist_name);
      });
      return (
        React.createElement("div", null, 
          React.createElement("select", {value: this.state.t_stylist_stylist_id, onChange: this.onChangeSelectValue}, 
            options
          )
        )
      );
    }
  });

  var StylistServiceMapingService = React.createClass({displayName: "StylistServiceMapingService",
    getDefaultProps:function() {
      return {
        services: ['']
      };
    },
    getInitialState:function() {
      return {
        t_menu_menu_id: '',
      };
    },
    onChangeSelectValue:function(e) {
      this.setState({t_menu_menu_id: e.target.value});
    },
    render:function() {
      var options = this.props.services.map(function(services) {
        return React.createElement("option", {value: services.t_menu_menu_id}, services.t_menu_name);
      });
      return (
        React.createElement("div", null, 
          React.createElement("select", {value: this.state.t_menu_menu_id, onChange: this.onChangeSelectValue}, 
            options
          )
        )
      );
    }
  });
  
  /*
  //休憩
  var StylistRestTime1 = React.createClass({
	    getDefaultProps:function() {
	      return {
	        start_time: ['09:00','10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00', '21:00', '22:00']
	      };
	    },
	    getInitialState:function() {
	      return {
	        start_time: this.props.start_time[0],
	      };
	    },
	    onChangeSelectValue:function(e) {
	      this.setState({start_time: e.target.value});
	    },
	    render:function() {
	        var options = this.props.start_time.map(function(start_time) {
	            return <option value={start_time}>{start_time}</option>;
	          });
	          return (
    	        <div>
    	          <select value={this.state.start_ime} onChange={this.onChangeSelectValue}>
    	            {options}
    	          </select>
    	        </div>
	          );
	    }
	  });
  var StylistRestTime2 = React.createClass({
	    getDefaultProps:function() {
	      return {
	        end_time: ['10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00', '21:00', '22:00']
	      };
	    },
	    getInitialState:function() {
	      return {
	        end_time: this.props.end_time[0],
	      };
	    },
	    onChangeSelectValue:function(e) {
	      this.setState({end_time: e.target.value});
	    },
	    render:function() {
	        var options = this.props.end_time.map(function(end_time) {
	            return <option value={end_time}>{end_time}</option>;
	          });
	          return (
    	        <div>
    	          <select value={this.state.end_ime} onChange={this.onChangeSelectValue}>
    	            {options}
    	          </select>
    	        </div>
	          );
	    }
	  });
	  */
  
  
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
  var component_stylist_name = React.render(React.createElement(StylistName, null), document.getElementById('stylist_name'));
  var component_stylist_sex = React.render(React.createElement(StylistSex, null), document.getElementById('stylist_sex'));
  var component_stylist_birth_year = React.render(React.createElement(StylistBirthYear, null), document.getElementById('stylist_birth_year'));
  var component_stylist_birth_month = React.render(React.createElement(StylistBirthMonth, null), document.getElementById('stylist_birth_month'));
  var component_stylist_birth_day = React.render(React.createElement(StylistBirthDay, null), document.getElementById('stylist_birth_day'));
  var component_stylist_phone_number = React.render(React.createElement(StylistPhoneNumber, null), document.getElementById('stylist_phone_number'));
  var component_stylist_position = React.render(React.createElement(StylistPosition, null), document.getElementById('stylist_position'));
  var component_stylist_mail = React.render(React.createElement(StylistMail, null), document.getElementById('stylist_mail'));
  var component_stylist_experience_year = React.render(React.createElement(StylistExperienceYear, null), document.getElementById('stylist_experience_year'));
  var component_stylist_special_menu = React.render(React.createElement(StylistSpecialMenu, null), document.getElementById('stylist_special_menu'));
  var component_stylist_message = React.render(React.createElement(StylistMessage, null), document.getElementById('stylist_message'));
  var component_stylist_image_path = React.render(React.createElement(StylistImagePath, null), document.getElementById('stylist_image_path'));
  var component_stylist_list = React.render(React.createElement(StylistList, null), document.getElementById('stylist_list_info'));
  var component_stylist_service_maping_name = React.render(React.createElement(StylistServiceMapingName, null), document.getElementById('stylist_service_maping_name'));
  var component_stylist_service_maping_service = React.render(React.createElement(StylistServiceMapingService, null), document.getElementById('stylist_service_maping_service'));
//  var component_stylist_rest_time1 = React.render(<StylistRestTime1 />, document.getElementById('stylist_rest_time1'));
//  var component_stylist_rest_time2 = React.render(<StylistRestTime2 />, document.getElementById('stylist_rest_time2'));

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
    
    //validation
    var validate_check = true;
    if(component_stylist_name.state.t_stylist_name=="" || component_stylist_name.state.t_stylist_name==null){
        alert('Regist Failed... name is empty.');
    	validate_check = false;
    }
    if(component_stylist_phone_number.state.t_stylist_phoneNumber=="" || component_stylist_phone_number.state.t_stylist_phoneNumber==null){
        alert('Regist Failed... phone_number is empty.');
    	validate_check = false;
    }
    if(component_stylist_mail.state.t_stylist_mail=="" || component_stylist_mail.state.t_stylist_mail==null){
        alert('Regist Failed... mail_address is empty.');
    	validate_check = false;
    }
    if(validate_check == true){
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

        //debug
        console.log(
               " t_stylist_Id: "+            component_stylist_name.state.t_stylist_stylist_id+
               " t_stylist_name: "+          component_stylist_name.state.t_stylist_name
        );
        
        var result = setStaffInfo(data);
        if (result.result == "true") {
          alert('Regist Success');
          window.location.reload();
        }
        else {
//            alert('Regist Failed');
            alert('Regist Failed' + result.reason);
        }
    }
  });

  // 編集ボタン押下時
  $('.edit').on('click', function() {
    if (stylist_info.stylist.length == 0) {
      return false;
    }

    // component set state
    var id = $(".edit").index(this);
    componentSetState(stylist_info.stylist[id]);
  });

  // 削除ボタン押下時
  $('.delete').on('click', function() {
    if (stylist_info.stylist.length == 0) {
      return false;
    }

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
    if (typeof stylist_info.stylist[id] != 'undefined') {
      var menu_ids = stylist_info.stylist[id].t_menu_t_menu_id.split(',');
      var regist_menu_id = component_stylist_service_maping_service.state.t_menu_menu_id;
      var menu_id = new Array();
      for (var i = 0; i < menu_ids.length; i++) {
        menu_id.push(menu_ids[i]);
      }
    }

    if (stylist_id == null || menu_id == null) {
      // どちらかのデータがない場合はエラー
      alert('Regist Failed');
    }
    else if (menu_id.indexOf(regist_menu_id) !== -1) {
      // すでに登録されているサービスの場合はエラー
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
                  component_stylist_image_path.setState({t_stylist_imagePath: response.image_path});
              }
              else {
                alert('Upload Failed');
              }
          });
      })(data);
      
      /*
      var result = uploadImage(data);
      if (result.result == "true") {
        component_stylist_image_path.setState({t_stylist_imagePath: result.image_path});
      }
      else {
        alert('Upload Failed');
      }
      */
    }
  });

});