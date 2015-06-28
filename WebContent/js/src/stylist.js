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
        sex: [{value: 0, text:'男性'}, {value: 1, text:'女性'}]
      };
    },
    getInitialState:function() {
      return {
        t_stylist_sex: '',
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
        year: '',
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
        month: '',
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
        day: '',
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
        t_stylist_experienceYear: '',
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
          "t_stylist_Id": "2",
          "t_stylist_name": "スタイリスト2",
          "t_stylist_position": "チーフ",
          "t_stylist_phoneNumber": "000-1234-5678",
          "t_menu_t_menu_id": ["1", "2"]}]
      };
    },
    render:function() {
      var stylist = this.state.stylist_list.map(function(stylist) {
        return React.createElement("tr", null, React.createElement("td", null, stylist.t_stylist_Id), React.createElement("td", null, stylist.t_stylist_name), React.createElement("td", null, stylist.t_stylist_position), React.createElement("td", null, stylist.t_stylist_phoneNumber), React.createElement("td", null, stylist.t_menu_t_menu_name), React.createElement("td", null, React.createElement("a", {className: "edit"}, "編集"), "/", React.createElement("a", {className: "delete"}, "削除")));
      });
      return (
        React.createElement("div", null, 
          React.createElement("table", null, 
            React.createElement("tr", null, React.createElement("th", null, "No."), React.createElement("th", null, "氏名"), React.createElement("th", null, "役職"), React.createElement("th", null, "連絡先"), React.createElement("th", null, "対応可能サービス"), React.createElement("th", null, "編集")), 
            stylist
          )
        )
      );
    }
  });

  var StylistServiceMapingName = React.createClass({displayName: "StylistServiceMapingName",
    getDefaultProps:function() {
      return {
        name: ['']
      };
    },
    getInitialState:function() {
      return {
        t_stylist_name: '',
      };
    },
    onChangeSelectValue:function(e) {
      this.setState({t_stylist_name: e.target.value});
    },
    render:function() {
      var options = this.props.name.map(function(name) {
        return React.createElement("option", {value: name.t_stylist_name}, name.t_stylist_name);
      });
      return (
        React.createElement("div", null, 
          React.createElement("select", {value: this.state.t_stylist_name, onChange: this.onChangeSelectValue}, 
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
        t_menu_name: '',
      };
    },
    onChangeSelectValue:function(e) {
      this.setState({t_menu_name: e.target.value});
    },
    render:function() {
      var options = this.props.services.map(function(services) {
        return React.createElement("option", {value: services.t_menu_menuId}, services.t_menu_name);
      });
      return (
        React.createElement("div", null, 
          React.createElement("select", {value: this.state.t_menu_menuId, onChange: this.onChangeSelectValue}, 
            options
          )
        )
      );
    }
  });


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


  /*
    Main Part
  */
  // セッションIDからスタッフ情報を取得する
  var session_info = getSessionInfo();
  var stylist_info = getStaffInfo(session_info.t_hairSalonMaster_salonId);

  // サロンIDに紐づくサービス一覧を取得する
  var service_list = getServiceList(session_info.t_hairSalonMaster_salonId);
  // service_listを参照しやすい形に変換
  var services = new Array();
  for (var i = 0; i < service_list.menu.length; i++) {
    var service_id = service_list.menu[i].t_menu_menuId;
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

  // MysqlのDATETIME型から年月日に変換する
  var ymd = getYearMonthDayByDateTime(stylist_info.stylist[0].t_stylist_birth);

  // コンポーネントにjsonを渡して関係する部分だけ書き換わる
  component_stylist_name.setState(stylist_info.stylist[0]);
  component_stylist_sex.setState(stylist_info.stylist[0]);
  component_stylist_birth_year.setState(ymd);
  component_stylist_birth_month.setState(ymd);
  component_stylist_birth_day.setState(ymd);
  component_stylist_phone_number.setState(stylist_info.stylist[0]);
  component_stylist_position.setState(stylist_info.stylist[0]);
  component_stylist_mail.setState(stylist_info.stylist[0]);
  component_stylist_experience_year.setState(stylist_info.stylist[0]);
  component_stylist_special_menu.setState(stylist_info.stylist[0]);
  component_stylist_message.setState(stylist_info.stylist[0]);
  component_stylist_image_path.setState(stylist_info.stylist[0]);

  // アルバム一覧
  component_stylist_list.setState({"stylist_list":stylist_info.stylist});


  // service maping
  component_stylist_service_maping_name.setProps({name: stylist_info.stylist});
  component_stylist_service_maping_service.setProps({services: service_list.menu});


  // 編集ボタン押下時
  $('.edit').on('click', function() {

  });
});