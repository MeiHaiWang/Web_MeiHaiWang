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
        country: ['']
      };
    },
    getInitialState:function() {
      return {
        t_country_name: '',
      };
    },
    onChangeSelectValue:function(e) {
      // countryが変化したらAreaもCountryに対応したリストに変化させる
      var areas = getAreasByCountryName(country_area_info.country, e.target.value);
      component_salon_area.setProps({area: areas});

      this.setState({t_country_name: e.target.value});
    },
    render:function() {
      var options = this.props.country.map(function(country) {
        return React.createElement("option", {value: country.country_name}, country.country_name);
      });
      return (
        React.createElement("div", null, 
          React.createElement("select", {value: this.state.t_country_name, onChange: this.onChangeSelectValue}, 
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
        t_area_name: '',
      };
    },
    onChangeSelectValue:function(e) {
      this.setState({t_area_name: e.target.value});
    },
    render:function() {
      var options = this.props.area.map(function(area) {
        return React.createElement("option", {value: area.t_area_name}, area.t_area_name);
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
        t_hairSalonMaster_openTime: '',
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

  var SalonOpenTime = React.createClass({displayName: "SalonOpenTime",
    getDefaultProps:function() {
      return {
        open_time: ['10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00']
      };
    },
    getInitialState:function() {
      return {
        t_hairSalonMaster_openTime: '',
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
        t_hairSalonMaster_closeTime: '',
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
        close_day: ['月曜日', '火曜日', '水曜日', '木曜日', '金曜日', '土曜日', '日曜日']
      };
    },
    getInitialState:function() {
      return {
        t_hairSalonMaster_closeDay: '',
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
        credit: [{value: 0, text:'クレジットカード未対応'}, {value: 1, text:'クレジットカード対応'}]
      };
    },
    getInitialState:function() {
      return {
        t_hairSalonMaster_creditAvailable: '',
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
        car_park: [{value: 0, text:'なし'}, {value: 1, text:'あり'}]
      };
    },
    getInitialState:function() {
      return {
        t_hairSalonMaster_carParkAvailable: '',
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
        japanese: [{value: 0, text:'日本語対応不可'}, {value: 1, text:'日本語対応可'}]
      };
    },
    getInitialState:function() {
      return {
        t_hairSalonMaster_japaneseAvailable: '',
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
  //var session_info = getSessionInfo();
  //var salon_info = getSalonInfo(session_info.t_hairSalonMaster_salonId);
  //var country_area_info = getCountryAreaList();
  //sanitaize.decode(salon_info);
  //sanitaize.decode(country_area_info);
  var session_info_json = getSessionInfo();
  var session_info = JSON.parse(session_info_json);

  var salon_info_json = getSalonInfo(session_info.t_hairSalonMaster_salonId);
  var country_area_info_json = getCountryAreaList();
  sanitaize.decode(salon_info_json);
  sanitaize.decode(country_area_info_json);
  var salon_info = JSON.parse(salon_info_json);
  var country_area_info = JSON.parse(country_area_info_json);
  console.log("salon_info:"+salon_info_json);
  console.log("country_area_info:"+country_area_info_json);
  //console.log("session_info_obj:"+session_info);
  //console.log("salon_info_obj:"+salon_info.t_hairSalonMaster_salon_name);
  //console.log("country_area_info_obj:"+country_area_info);

  // countryを参照しやすい形に変換
  var countrys = new Array();
  var country_area_id_info = new Array();
  for (var i = 0; i < Object.keys(country_area_info).length; i++) {
    var country_id = country_area_info.country[i].t_country_id;
    var country_name = country_area_info.country[i].t_country_name;
    countrys[i] = {country_id: country_id, country_name: country_name};
  }
  /*
  for (var i = 0; i < country_area_info.country.length; i++) {
    var country_id = country_area_info.country[i].t_country_id;
    var country_name = country_area_info.country[i].t_country_name;
    countrys[i] = {country_id: country_id, country_name: country_name};
  }
  */

  // areaを参照しやすい形に変換
  var areas = getAreasByCountryName(country_area_info.country, salon_info.t_country_name);
	console.log("ca"+country_area_info.country);
	console.log("si"+salon_info.t_country_name);

  // image画像情報を長さ4の配列に変換し、画像指定のない要素にnotfoundを表示するように
  var salon_image_path = new Array();
  salon_image_path = salon_info.t_hairSalonMaster_salonImagePath.split(',');
  for (var i = salon_image_path.length; i < 4; i++) {
    salon_image_path[i] = 'img/notfound.jpg';
  }
  salon_info.t_hairSalonMaster_salonImagePath = salon_image_path;

  // コンポーネントにjsonを渡して関係する部分だけ書き換わる
  component_salon_name.setState(salon_info);
  component_salon_country.setState(salon_info);
  component_salon_country.setProps({country: countrys});
  component_salon_area.setState(salon_info);
  component_salon_area.setProps({area: areas});
  component_salon_detail_text.setState(salon_info);
  component_salon_open_time.setState(salon_info);
  component_salon_close_time.setState(salon_info);
  component_salon_close_day.setState(salon_info);
  component_salon_credit_available.setState(salon_info);
  component_salon_car_park_available.setState(salon_info);
  component_salon_japanese_available.setState(salon_info);
  component_salon_image_path.setState(salon_info);


  /*
    Button Handler
  */
  // 登録ボタン押下時
  $('#salon_regist_button').on('click', function() {
    var data = {
      t_hairSalonMaster_salonId:           session_info.t_hairSalonMaster_salonId,
      t_hairSalonMaster_name:              component_salon_name.state.t_hairSalonMaster_salon_name,
      t_area_id:                           getAreaIdByAreaName(component_salon_area.state.t_area_name, areas),
      t_hairSalonMaster_detailText:        component_salon_detail_text.state.t_hairSalonMaster_detailText,
      t_hairSalonMaster_openTime:          component_salon_open_time.state.t_hairSalonMaster_openTime,
      t_hairSalonMaster_closeTime:         component_salon_close_time.state.t_hairSalonMaster_closeTime,
      t_hairSalonMaster_closeDay:          component_salon_close_day.state.t_hairSalonMaster_closeDay,
      t_hairSalonMaster_creditAvailable:   component_salon_credit_available.state.t_hairSalonMaster_creditAvailable,
      t_hairSalonMaster_carParkAvailable:  component_salon_car_park_available.state.t_hairSalonMaster_carParkAvailable,
      t_hairSalonMaster_salonImagePath:    component_salon_image_path.state.t_hairSalonMaster_salonImagePath.join(','),
      t_hairSalonMaster_japaneseAvailable: component_salon_japanese_available.state.t_hairSalonMaster_japaneseAvailable,
    }
    
    console.log(data);

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
      var fd = new FormData($('#update')[0]);

      var data = {
        t_hairSalonMaster_salonId: session_info.t_hairSalonMaster_salonId,
        file: fd,
      }
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
