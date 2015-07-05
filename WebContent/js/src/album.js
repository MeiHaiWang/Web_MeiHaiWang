$(function(){
  /*
    Component for React
  */
  // コンポーネントの定義
  var AlbumCategory = React.createClass({displayName: "AlbumCategory",
    getDefaultProps:function() {
      return {
        hairtype: ['']
      };
    },
    getInitialState:function() {
      return {
        t_hairStyle_hairTypeId: '',
      };
    },
    onChangeSelectValue:function(e) {
      this.setState({t_hairStyle_hairTypeId: e.target.value});
    },
    render:function() {
      var options = this.props.hairtype.map(function(hairtype) {
        return React.createElement("option", {value: hairtype.t_hairType_id}, hairtype.t_hairType_name);
      });
      return (
        React.createElement("div", null, 
          React.createElement("select", {value: this.state.t_hairStyle_hairTypeId, onChange: this.onChangeSelectValue}, 
            options
          )
        )
      );
    }
  });

  var AlbumName = React.createClass({displayName: "AlbumName",
    getInitialState:function() {
      return {
        t_hairStyle_name: ""
      };
    },
    changeText:function(e) {
      this.setState({t_hairStyle_name: e.target.value});
    },
    render:function() {
      return (
        React.createElement("div", null, 
          React.createElement("input", {type: "text", value: this.state.t_hairStyle_name, onChange: this.changeText})
        )
      );
    }
  });

  var AlbumStylistName = React.createClass({displayName: "AlbumStylistName",
    getDefaultProps:function() {
      return {
        stylist: ['']
      };
    },
    getInitialState:function() {
      return {
        t_hairStyle_stylistId: '',
      };
    },
    onChangeSelectValue:function(e) {
      this.setState({t_hairStyle_stylistId: e.target.value});
    },
    render:function() {
      var options = this.props.stylist.map(function(stylist) {
        return React.createElement("option", {value: stylist.t_stylist_Id}, stylist.t_stylist_name);
      });
      return (
        React.createElement("div", null, 
          React.createElement("select", {value: this.state.t_hairStyle_stylistId, onChange: this.onChangeSelectValue}, 
            options
          )
        )
      );
    }
  });

  var AlbumImagePath = React.createClass({displayName: "AlbumImagePath",
    getInitialState:function() {
      return {
        t_hairStyle_imagePath: "img/notfound.jpg"
      };
    },
    render:function() {
      return (
        React.createElement("div", null, 
          React.createElement("img", {className: "album_image_img", src: this.state.t_hairStyle_imagePath?this.state.t_hairStyle_imagePath:'img/notfound.jpg'})
        )
      );
    }
  });

  var AlbumList = React.createClass({displayName: "AlbumList",
    getInitialState:function() {
      return {
        album_list: [{
          "t_hairStyle_id": "",
          "t_hairStyle_hairTypeId": "",
          "t_hairStyle_name": "",
          "t_hairStyle_stylistId": "",
          "t_hairStyle_imagePath": "img/notfound.jpg"}]
      };
    },
    render:function() {
      var album = this.state.album_list.map(function(album) {
        return React.createElement("tr", null, React.createElement("td", null, album.t_hairStyle_id), React.createElement("td", null, hairtypes[album.t_hairStyle_hairTypeId]), React.createElement("td", null, album.t_hairStyle_name), React.createElement("td", null, stylists[album.t_hairStyle_stylistId]), React.createElement("td", null, React.createElement("img", {src: album.t_hairStyle_imagePath?album.t_hairStyle_imagePath:'img/notfound.jpg'})), React.createElement("td", null, React.createElement("a", {className: "edit"}, "編集"), "/", React.createElement("a", {className: "delete"}, "削除")));
      });
      return (
        React.createElement("div", null, 
          React.createElement("table", null, 
            React.createElement("tr", null, React.createElement("th", null, "No."), React.createElement("th", null, "カテゴリー"), React.createElement("th", null, "ヘアスタイル名"), React.createElement("th", null, "スタイリスト名"), React.createElement("th", null, "写真"), React.createElement("th", null, "編集")), 
            album
          )
        )
      );
    }
  });

  // set state to component
  function componentSetState(album) {
    // コンポーネントにjsonを渡して関係する部分だけ書き換わる
    component_album_category.setState(album);
    component_album_name.setState(album);
    component_album_stylist_name.setState(album);
    component_album_image_path.setState(album);
  }



  /*
    List
  */
  var hairtype_info = getHairTypeList();
  sanitaize.decode(hairtype_info);

  // hairtypeを参照しやすい形に変換
  var hairtypes = new Array();
  for (var i = 0; i < hairtype_info.type.length; i++) {
    var hairtype_id = hairtype_info.type[i].t_hairType_id;
    var hairtype_name = hairtype_info.type[i].t_hairType_name;
    hairtypes[hairtype_id] = hairtype_name;
  }

  var stylist_info = getStylistList();
  sanitaize.decode(stylist_info);
  // stylistを参照しやすい形に変換
  var stylists = new Array();
  for (var i = 0; i < stylist_info.stylist.length; i++) {
    var stylist_id = stylist_info.stylist[i].t_stylist_Id;
    var stylist_name = stylist_info.stylist[i].t_stylist_name;
    stylists[stylist_id] = stylist_name;
  }


  /*
    Component Render
  */
  // コンポーネントをエレメントに割り当てる
  var component_album_category = React.render(React.createElement(AlbumCategory, null), document.getElementById('album_category'));
  var component_album_name = React.render(React.createElement(AlbumName, null), document.getElementById('album_name'));
  var component_album_stylist_name = React.render(React.createElement(AlbumStylistName, null), document.getElementById('album_stylist_name'));
  var component_album_image_path = React.render(React.createElement(AlbumImagePath, null), document.getElementById('album_image_path'));
  var component_album_list = React.render(React.createElement(AlbumList, null), document.getElementById('album_list_info'));


  /*
    Main Part
  */
  // セッションIDからアルバム情報を取得する
  var session_info = getSessionInfo();
  var album_info = getAlbumInfo(session_info.t_hairSalonMaster_salonId);

  sanitaize.decode(album_info);

  // set component
  component_album_category.setProps({hairtype: hairtype_info.type});
  component_album_stylist_name.setProps({stylist: stylist_info.stylist});

  // アルバム一覧
  component_album_list.setState({"album_list":album_info.album});


  /*
    Button Handler
  */
  // 登録ボタン押下時
  $('#album_regist_button').on('click', function() {
    var data = {
      t_hairSalonMaster_salonId: session_info.t_hairSalonMaster_salonId,
      t_hairStyle_id:            component_album_category.state.t_hairStyle_id,
      t_hairStyle_hairTypeId:    component_album_category.state.t_hairStyle_hairTypeId,
      t_hairStyle_name:          component_album_name.state.t_hairStyle_name,
      t_hairStyle_stylistId:     component_album_stylist_name.state.t_hairStyle_stylistId,
      t_hairStyle_imagePath:     component_album_image_path.state.t_hairStyle_imagePath,
    }

    // サニタイズ
    sanitaize.encode(data);

    var result = setAlbumInfo(data);
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
    componentSetState(album_info.album[id]);
  });

  // 削除ボタン押下時
  $('.delete').on('click', function() {
    var id = $(".delete").index(this);
    var data = {t_hairStyle_id: album_info.album[id].t_hairStyle_id};

    // サニタイズ
    sanitaize.encode(data);

    var result = deleteAlbumInfo(data);
    if (result.result == "true") {
      alert('Delete Success');
      window.location.reload();
    }
    else {
      alert('Delete Failed');
    }
  });

  // 画像アップロード
  $('#album_image').change(function() {
    // ファイルが選択されたか
    if($(this).prop('files')[0]){
      var fd = new FormData($('#update')[0]);

      var data = {
        t_hairSalonMaster_salonId: session_info.t_hairSalonMaster_salonId,
        file: fd,
      }
      var result = uploadImage(data);
      if (result.result == "true") {
        component_album_image_path.setState({t_hairStyle_imagePath: result.image_path});
      }
      else {
        alert('Upload Failed');
      }
    }
  });

});