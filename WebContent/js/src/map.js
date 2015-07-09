$(function(){
  /*
    Component for React
  */
  // コンポーネントの定義
  var MapUrl = React.createClass({displayName: "MapUrl",
    getInitialState:function() {
      return {
        t_hairSalonMaster_mapUrl: ""
      };
    },
    changeText:function(e) {
      this.setState({t_hairSalonMaster_mapUrl: e.target.value});
    },
    render:function() {
      return (
        React.createElement("div", null, 
          React.createElement("input", {type: "text", value: this.state.t_hairSalonMaster_mapUrl, onChange: this.changeText})
        )
      );
    }
  });

  var MapImagePath = React.createClass({displayName: "MapImagePath",
    getInitialState:function() {
      return {
        t_hairSalonMaster_mapImagePath: "img/notfound.jpg"
      };
    },
    render:function() {
      return (
        React.createElement("div", null, 
          React.createElement("img", {className: "map_image_img", src: this.state.t_hairSalonMaster_mapImagePath?this.state.t_hairSalonMaster_mapImagePath:'img/notfound.jpg'})
        )
      );
    }
  });


  /*
    Component Render
  */
  // コンポーネントをエレメントに割り当てる
  var component_map_url = React.render(React.createElement(MapUrl, null), document.getElementById('map_url'));
  var component_map_image_path = React.render(React.createElement(MapImagePath, null), document.getElementById('map_image_path'));


  /*
    Main Part
  */
  // セッションIDから地図情報を取得する
  var session_info = getSessionInfo();
  var map_info = getMapInfo(session_info.t_hairSalonMaster_salonId);
  sanitaize.decode(map_info);

  // アカウント名を表示
  $('#account-name').text(session_info.t_hairSalonMaster_contactUserName);

  // コンポーネントにjsonを渡して関係する部分だけ書き換わる
  component_map_url.setState(map_info);
  component_map_image_path.setState(map_info);


  /*
    Button Handler
  */
  // 登録ボタン押下時
  $('#map_regist_button').on('click', function() {
    var data = {
      t_hairSalonMaster_salonId:      session_info.t_hairSalonMaster_salonId,
      t_hairSalonMaster_mapUrl:       component_map_url.state.t_hairSalonMaster_mapUrl,
      t_hairSalonMaster_mapImagePath: component_map_image_path.state.t_hairSalonMaster_mapImagePath,
    }

    // サニタイズ
    sanitaize.encode(data);

    var result = setMapInfo(data);
    if (result.result == "true") {
      alert('Regist Success');
      window.location.reload();
    }
    else {
      alert('Regist Failed');
    }
  });

  // 画像アップロード
  $('#map_image').change(function() {
    // ファイルが選択されたか
    if($(this).prop('files')[0]){
      var data = new FormData($('#update')[0]);
      var result = uploadImage(data);
      if (result.result == "true") {
        component_map_image_path.setState({t_hairSalonMaster_mapImagePath: result.image_path});
      }
      else {
        alert('Upload Failed');
      }
    }
  });

  // 画像削除ボタン
  $('.map_image_trash_button').on('click', function() {
    component_map_image_path.setState({t_hairSalonMaster_mapImagePath: 'img/notfound.jpg'});
  });
});