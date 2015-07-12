$(function(){
  /*
    Component for React
  */
  // コンポーネントの定義
  var AlbumCategory = React.createClass({
    getDefaultProps() {
      return {
        hairtype: ['']
      };
    },
    getInitialState() {
      return {
        t_hairStyle_hairTypeId: '',
      };
    },
    onChangeSelectValue(e) {
      this.setState({t_hairStyle_hairTypeId: e.target.value});
    },
    render() {
      var options = this.props.hairtype.map(function(hairtype) {
        return <option value={hairtype.t_hairType_id}>{hairtype.t_hairType_name}</option>;
      });
      return (
        <div>
          <select value={this.state.t_hairStyle_hairTypeId} onChange={this.onChangeSelectValue}>
            {options}
          </select>
        </div>
      );
    }
  });

  var AlbumName = React.createClass({
    getInitialState() {
      return {
        t_hairStyle_name: ""
      };
    },
    changeText(e) {
      this.setState({t_hairStyle_name: e.target.value});
    },
    render() {
      return (
        <div>
          <input type="text" value={this.state.t_hairStyle_name} onChange={this.changeText} />
        </div>
      );
    }
  });

  var AlbumStylistName = React.createClass({
    getDefaultProps() {
      return {
        stylist: ['']
      };
    },
    getInitialState() {
      return {
        t_hairStyle_stylistId: '',
      };
    },
    onChangeSelectValue(e) {
      this.setState({t_hairStyle_stylistId: e.target.value});
    },
    render() {
      var options = this.props.stylist.map(function(stylist) {
        return <option value={stylist.t_stylist_stylist_id}>{stylist.t_stylist_name}</option>;
      });
      return (
        <div>
          <select value={this.state.t_hairStyle_stylistId} onChange={this.onChangeSelectValue}>
            {options}
          </select>
        </div>
      );
    }
  });

  var AlbumImagePath = React.createClass({
    getInitialState() {
      return {
        t_hairStyle_imagePath: "img/notfound.jpg"
      };
    },
    render() {
      return (
        <div>
          <img className="album_image_img" src={this.state.t_hairStyle_imagePath?this.state.t_hairStyle_imagePath:'img/notfound.jpg'} />
        </div>
      );
    }
  });

  var AlbumList = React.createClass({
    getInitialState() {
      return {
        album_list: [{
          "t_hairStyle_id": "",
          "t_hairStyle_hairTypeId": "",
          "t_hairStyle_name": "",
          "t_hairStyle_stylistId": "",
          "t_hairStyle_imagePath": "img/notfound.jpg"}]
      };
    },
    render() {
      var index = 1;
      var album = this.state.album_list.map(function(album) {
        return <tr><td>{index++}</td><td>{hairtypes[album.t_hairStyle_hairTypeId]}</td><td>{album.t_hairStyle_name}</td><td>{typeof album.t_stylist_stylist_id!=="undefined"?stylists[album.t_stylist_stylist_id]:''}</td><td><img src={album.t_hairStyle_imagePath?album.t_hairStyle_imagePath:'img/notfound.jpg'} /></td><td><a className="edit">編集</a>/<a className="delete">削除</a></td></tr>;
      });
      return (
        <div>
          <table>
            <tr><th>No.</th><th>カテゴリー</th><th>ヘアスタイル名</th><th>スタイリスト名</th><th>写真</th><th>編集</th></tr>
            {album}
          </table>
        </div>
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
  var component_album_category = React.render(<AlbumCategory />, document.getElementById('album_category'));
  var component_album_name = React.render(<AlbumName />, document.getElementById('album_name'));
  var component_album_stylist_name = React.render(<AlbumStylistName />, document.getElementById('album_stylist_name'));
  var component_album_image_path = React.render(<AlbumImagePath />, document.getElementById('album_image_path'));
  var component_album_list = React.render(<AlbumList />, document.getElementById('album_list_info'));


  /*
    Main Part
  */
  // セッションIDからアルバム情報を取得する
  var session_info = getSessionInfo();
  var album_info = getAlbumInfo(session_info.t_hairSalonMaster_salonId);

  sanitaize.decode(album_info);

  // アカウント名を表示
  $('#account-name').text(session_info.t_hairSalonMaster_contactUserName);

  // set component
  component_album_category.setProps({hairtype: hairtype_info.type});
  component_album_stylist_name.setProps({stylist: stylist_info.stylist});

  // アルバム一覧
  if (album_info.album.length != 0) {
    component_album_list.setState({"album_list":album_info.album});
  }

  component_album_category.setState({t_hairStyle_hairTypeId: component_album_category.props.hairtype[0].t_hairType_id});
  if (component_album_stylist_name.props.stylist.length != 0) {
    component_album_stylist_name.setState({t_hairStyle_stylistId: component_album_stylist_name.props.stylist[0].t_stylist_stylist_id});
  }

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
    if (album_info.album.length == 0) {
      return false;
    }

    // component set state
    var id = $(".edit").index(this);
    componentSetState(album_info.album[id]);
  });

  // 削除ボタン押下時
  $('.delete').on('click', function() {
    if (album_info.album.length == 0) {
      return false;
    }

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
      var data = new FormData($('#update')[0]);

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