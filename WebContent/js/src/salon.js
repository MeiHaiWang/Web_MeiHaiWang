$(function(){
  /*
    Component for React
  */
  // コンポーネントの定義
  var SalonName = React.createClass({displayName: "SalonName",
    getInitialState:function() {
      return {
        t_hairSalonMaster_salon_name: ""
      };
    },
    changeText:function(e) {
      this.setState({t_hairSalonMaster_salon_name: e.target.value});
    },
    render:function() {
      return (
        React.createElement("div", null, 
          React.createElement("input", {type: "text", value: this.state.t_hairSalonMaster_salon_name, onChange: this.changeText})
        )
      );
    }
  });

  var SalonCountry = React.createClass({displayName: "SalonCountry",
    getDefaultProps:function() {
      return {
        area: ['']
      };
    },
    getInitialState:function() {
      return {
        t_area_name: '',
      };
    },
    onChangeSelectValue:function(e) {
      // countryが変化したらAreaもCountryに対応したリストに変化させる
      var areas_slave = getSlaveAreasByAreaName(country_area_info.area, e.target.value);
      component_salon_area.setProps({area: areas_slave});

      this.setState({t_area_name: e.target.value});
    },
    render:function() {
      var options = this.props.area.map(function(area) {
        return React.createElement("option", {value: area.area_name}, area.area_name);
      });
      return (
        React.createElement("div", null, 
          React.createElement("select", {value: this.state.t_area_name, onChange: this.onChangeSelectValue}, 
            options
          )
        )
      );
    }
  });

  var SalonArea = React.createClass({displayName: "SalonArea",
    getDefaultProps:function() {
      return {
        area: ['']
      };
    },
    getInitialState:function() {
      return {
        t_area_id: '',
      };
    },
    onChangeSelectValue:function(e) {
      this.setState({t_area_id: e.target.value});
    },
    render:function() {
      var options = this.props.area.map(function(area) {
        return React.createElement("option", {value: area.t_area_id}, area.t_area_name);
      });
      return (
        React.createElement("div", null, 
          React.createElement("select", {value: this.state.t_area_id, onChange: this.onChangeSelectValue}, 
            options
          )
        )
      );
    }
  });

  var SalonDetailText = React.createClass({displayName: "SalonDetailText",
    getInitialState:function() {
      return {
        t_hairSalonMaster_detailText: ""
      };
    },
    onChangeText:function(e) {
      this.setState({t_hairSalonMaster_detailText: e.target.value});
    },
    onClick:function() {
      this.setState({t_hairSalonMaster_detailText: this.refs.textArea.getDOMNode().value});
    },
    render:function() {
      return (
        React.createElement("div", null, 
          React.createElement("div", null, 
            React.createElement("textarea", {value: this.state.t_hairSalonMaster_detailText, onChange: this.onChangeText})
          )
        )
      );
    }
  });

  var SalonOpenTime = React.createClass({displayName: "SalonOpenTime",
    getDefaultProps:function() {
      return {
        open_time: ['10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00']
      };
    },
    getInitialState:function() {
      return {
        t_hairSalonMaster_openTime: this.props.open_time[0],
      };
    },
    onChangeSelectValue:function(e) {
      this.setState({t_hairSalonMaster_openTime: e.target.value});
    },
    render:function() {
      var options = this.props.open_time.map(function(open_time) {
        return React.createElement("option", {value: open_time}, open_time);
      });
      return (
        React.createElement("div", null, 
          React.createElement("select", {value: this.state.t_hairSalonMaster_openTime, onChange: this.onChangeSelectValue}, 
            options
          )
        )
      );
    }
  });

  var SalonCloseTime = React.createClass({displayName: "SalonCloseTime",
    getDefaultProps:function() {
      return {
        close_time: ['10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00']
      };
    },
    getInitialState:function() {
      return {
        t_hairSalonMaster_closeTime: this.props.close_time[0],
      };
    },
    onChangeSelectValue:function(e) {
      this.setState({t_hairSalonMaster_closeTime: e.target.value});
    },
    render:function() {
      var options = this.props.close_time.map(function(close_time) {
        return React.createElement("option", {value: close_time}, close_time);
      });
      return (
        React.createElement("div", null, 
          React.createElement("select", {value: this.state.t_hairSalonMaster_closeTime, onChange: this.onChangeSelectValue}, 
            options
          )
        )
      );
    }
  });

  var SalonCloseDay = React.createClass({displayName: "SalonCloseDay",
    getDefaultProps:function() {
      return {
        close_day: ['星期一', '星期二', '星期三', '星期四', '星期五', '星期六', '星期日']
      };
    },
    getInitialState:function() {
      return {
        t_hairSalonMaster_closeDay: '星期一',
      };
    },
    onChangeSelectValue:function(e) {
      this.setState({t_hairSalonMaster_closeDay: e.target.value});
    },
    render:function() {
      var options = this.props.close_day.map(function(close_day) {
        return React.createElement("option", {value: close_day}, close_day);
      });
      return (
        React.createElement("div", null, 
          React.createElement("select", {value: this.state.t_hairSalonMaster_closeDay, onChange: this.onChangeSelectValue}, 
            options
          )
        )
      );
    }
  });

  var SalonCreditAvailable = React.createClass({displayName: "SalonCreditAvailable",
    getDefaultProps:function() {
      return {
        credit: [{value: 0, text:'信用卡不可用'}, {value: 1, text:'信用卡可用'}]
      };
    },
    getInitialState:function() {
      return {
        t_hairSalonMaster_creditAvailable: 0,
      };
    },
    onChangeSelectValue:function(e) {
      this.setState({t_hairSalonMaster_creditAvailable: e.target.value});
    },
    render:function() {
      var options = this.props.credit.map(function(credit) {
        return React.createElement("option", {value: credit.value}, credit.text);
      });
      return (
        React.createElement("div", null, 
          React.createElement("select", {value: this.state.t_hairSalonMaster_creditAvailable, onChange: this.onChangeSelectValue}, 
            options
          )
        )
      );
    }
  });

  var SalonCarParkAvailable = React.createClass({displayName: "SalonCarParkAvailable",
    getDefaultProps:function() {
      return {
        car_park: [{value: 0, text:'无'}, {value: 1, text:'有'}]
      };
    },
    getInitialState:function() {
      return {
        t_hairSalonMaster_carParkAvailable: 0,
      };
    },
    onChangeSelectValue:function(e) {
      this.setState({t_hairSalonMaster_carParkAvailable: e.target.value});
    },
    render:function() {
      var options = this.props.car_park.map(function(car_park) {
        return React.createElement("option", {value: car_park.value}, car_park.text);
      });
      return (
        React.createElement("div", null, 
          React.createElement("select", {value: this.state.t_hairSalonMaster_carParkAvailable, onChange: this.onChangeSelectValue}, 
            options
          )
        )
      );
    }
  });

  var SalonJapaneseAvailable = React.createClass({displayName: "SalonJapaneseAvailable",
    getDefaultProps:function() {
      return {
        japanese: [{value: 0, text:'无日语接待'}, {value: 1, text:'日语接待'}]
      };
    },
    getInitialState:function() {
      return {
        t_hairSalonMaster_japaneseAvailable: 0,
      };
    },
    onChangeSelectValue:function(e) {
      this.setState({t_hairSalonMaster_japaneseAvailable: e.target.value});
    },
    render:function() {
      var options = this.props.japanese.map(function(japanese) {
        return React.createElement("option", {value: japanese.value}, japanese.text);
      });
      return (
        React.createElement("div", null, 
          React.createElement("select", {value: this.state.t_hairSalonMaster_japaneseAvailable, onChange: this.onChangeSelectValue}, 
            options
          )
        )
      );
    }
  });

  var SalonImagePath = React.createClass({displayName: "SalonImagePath",
    getInitialState:function() {
      return {
        t_hairSalonMaster_salonImagePath: ["img/notfound.jpg", "img/notfound.jpg", "img/notfound.jpg", "img/notfound.jpg"]
      };
    },
    render:function() {
      var images = this.state.t_hairSalonMaster_salonImagePath.map(function(images) {
        return React.createElement("div", {className: "salon_image_path_div"}, React.createElement("img", {className: "salon_image_img", src: images}));
      });
      return (
        React.createElement("div", null, 
          images
        )
      );
    }
  });


  /*
    Component Render
  */
  // コンポーネントをエレメントに割り当てる
  var component_salon_name = React.render(React.createElement(SalonName, null), document.getElementById('salon_salon_name'));
  var component_salon_country = React.render(React.createElement(SalonCountry, null), document.getElementById('salon_country'));
  var component_salon_area = React.render(React.createElement(SalonArea, null), document.getElementById('salon_area'));
  var component_salon_detail_text = React.render(React.createElement(SalonDetailText, null), document.getElementById('salon_detail_text'));
  var component_salon_open_time = React.render(React.createElement(SalonOpenTime, null), document.getElementById('salon_open_time'));
  var component_salon_close_time = React.render(React.createElement(SalonCloseTime, null), document.getElementById('salon_close_time'));
  var component_salon_close_day = React.render(React.createElement(SalonCloseDay, null), document.getElementById('salon_close_day'));
  var component_salon_credit_available = React.render(React.createElement(SalonCreditAvailable, null), document.getElementById('salon_credit_available'));
  var component_salon_car_park_available = React.render(React.createElement(SalonCarParkAvailable, null), document.getElementById('salon_car_park_available'));
  var component_salon_japanese_available = React.render(React.createElement(SalonJapaneseAvailable, null), document.getElementById('salon_japanese_available'));
  var component_salon_image_path = React.render(React.createElement(SalonImagePath, null), document.getElementById('salon_image_path'));


  /*
    Main Part
  */
  // セッションIDからサロン情報を取得する
  var session_info = getSessionInfo();
  var salon_info = getSalonInfo(session_info.t_hairSalonMaster_salonId);
  var country_area_info = getCountryAreaList();
  sanitaize.decode(salon_info);
  sanitaize.decode(country_area_info);

  // アカウント名を表示
  $('#account-name').text(session_info.t_hairSalonMaster_contactUserName);

  // countryを参照しやすい形に変換
  var areas = new Array();
  var country_area_id_info = new Array();
  for (var i = 0; i < country_area_info.area.length; i++) {
    var area_id = country_area_info.area[i].t_area_id;
    var area_name = country_area_info.area[i].t_area_name;
    areas[i] = {area_id: area_id, area_name: area_name};
  }
  // areaが登録されていない場合は先頭をセットするように
  if (salon_info.t_area_name == '') {
    salon_info.t_area_name = country_area_info.area[0].area_slave[0].t_area_name;
  }

  // areaを参照しやすい形に変換
  var area_name = getAreaNameBySlaveAreaName(country_area_info.area, salon_info.t_area_name);
  var areas_slave = getSlaveAreasByAreaName(country_area_info.area, area_name);

  // image画像情報を長さ4の配列に変換し、画像指定のない要素にnotfoundを表示するように
  var salon_image_path = new Array();
  salon_image_path = salon_info.t_hairSalonMaster_salonImagePath.split(',');
  for (var i = salon_image_path.length; i < 4; i++) {
    salon_image_path[i] = 'img/notfound.jpg';
  }
  salon_info.t_hairSalonMaster_salonImagePath = salon_image_path;

  // コンポーネントにjsonを渡して関係する部分だけ書き換わる
  component_salon_name.setState(salon_info);
  component_salon_country.setState({t_area_name: area_name});
  component_salon_country.setProps({area: areas});
  component_salon_area.setState({t_area_id:getAreaIdByAreaName(salon_info.t_area_name, areas_slave)});
  component_salon_area.setProps({area: areas_slave});
  component_salon_detail_text.setState(salon_info);
  // 値がセットされている場合のみセット
  if (salon_info.t_hairSalonMaster_openTime != '') {
    component_salon_open_time.setState(salon_info);
  }
  if (salon_info.t_hairSalonMaster_closeTime != '') {
    component_salon_close_time.setState(salon_info);
  }
  if (salon_info.t_hairSalonMaster_closeDay != '') {
    component_salon_close_day.setState(salon_info);
  }
  if (salon_info.t_hairSalonMaster_creditAvailable != '') {
    component_salon_credit_available.setState(salon_info);
  }
  if (salon_info.t_hairSalonMaster_carParkAvailable != '') {
    component_salon_car_park_available.setState(salon_info);
  }
  if (salon_info.t_hairSalonMaster_japaneseAvailable != '') {
    component_salon_japanese_available.setState(salon_info);
  }
  component_salon_image_path.setState(salon_info);

  /*
    Button Handler
  */
  // 登録ボタン押下時
  $('#salon_regist_button').on('click', function() {
    var data = {
      t_hairSalonMaster_salonId:           session_info.t_hairSalonMaster_salonId,
      t_hairSalonMaster_name:              component_salon_name.state.t_hairSalonMaster_salon_name,
      t_area_id:                           component_salon_area.state.t_area_id,
      t_hairSalonMaster_detailText:        component_salon_detail_text.state.t_hairSalonMaster_detailText,
      t_hairSalonMaster_openTime:          component_salon_open_time.state.t_hairSalonMaster_openTime,
      t_hairSalonMaster_closeTime:         component_salon_close_time.state.t_hairSalonMaster_closeTime,
      t_hairSalonMaster_closeDay:          component_salon_close_day.state.t_hairSalonMaster_closeDay,
      t_hairSalonMaster_creditAvailable:   component_salon_credit_available.state.t_hairSalonMaster_creditAvailable,
      t_hairSalonMaster_carParkAvailable:  component_salon_car_park_available.state.t_hairSalonMaster_carParkAvailable,
      t_hairSalonMaster_salonImagePath:    component_salon_image_path.state.t_hairSalonMaster_salonImagePath.join(','),
      t_hairSalonMaster_japaneseAvailable: component_salon_japanese_available.state.t_hairSalonMaster_japaneseAvailable,
    }

    // サニタイズ
    sanitaize.encode(data);

    var result = setSalonInfo(data);
    if (result.result == "true") {
      alert('Regist Success');
      window.location.reload();
    }
    else {
      alert('Regist Failed');
    }
  });

  // 画像アップロード
  $('#salon_image').change(function() {
    // ファイルが選択されたか
    if($(this).prop('files')[0]){
      var data = new FormData($('#update')[0]);

      var result = uploadImage(data);
      if (result.result == "true") {
        var salon_image_path = new Array(result.image_path);
        var current_image_path = component_salon_image_path.state.t_hairSalonMaster_salonImagePath;

        // 常に画像は4つまでしか管理しない
        salon_image_path = salon_image_path.concat(current_image_path).slice(0, 4);

        component_salon_image_path.setState({t_hairSalonMaster_salonImagePath: salon_image_path});
      }
      else {
        alert('Upload Failed');
      }
    }
  });

  // 画像削除ボタン
  $('.salon_image_trash_button').on('click', function() {
    var id = $(".salon_image_trash_button").index(this);
    var current_image_path = component_salon_image_path.state.t_hairSalonMaster_salonImagePath;
    current_image_path[id] = 'img/notfound.jpg';
    component_salon_image_path.setState({t_hairSalonMaster_salonImagePath: current_image_path});
  });

});
