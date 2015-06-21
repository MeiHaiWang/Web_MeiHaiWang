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
        return <option value={stylist.t_stylist_Id}>{stylist.t_stylist_name}</option>;
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


  /*
    List
  */
  var hairtype_info = getHairTypeList();
  // hairtypeを参照しやすい形に変換
  var hairtypes = new Array();
  for (var i = 0; i < hairtype_info.type.length; i++) {
    var hairtype_id = hairtype_info.type[i].t_hairType_id;
    var hairtype_name = hairtype_info.type[i].t_hairType_name;
    hairtypes[hairtype_id] = hairtype_name;
  }
  var stylist_info = getStylistList();


  /*
    Component Render
  */
  // コンポーネントをエレメントに割り当てる
  var component_album_category = React.render(<AlbumCategory />, document.getElementById('album_category'));
  var component_album_name = React.render(<AlbumName />, document.getElementById('album_name'));
  var component_album_stylist_name = React.render(<AlbumStylistName />, document.getElementById('album_stylist_name'));
  var component_album_image_path = React.render(<AlbumImagePath />, document.getElementById('album_image_path'));


  /*
    Main Part
  */
  // セッションIDからサービス情報を取得する
  var session_info = getSessionInfo();
  var album_info = getAlbumInfo(session_info.t_hairSalonMaster_salonId);


  // コンポーネントにjsonを渡して関係する部分だけ書き換わる
  component_album_category.setState(album_info.album[0]);
  component_album_category.setProps({hairtype: hairtype_info.type});
  component_album_name.setState(album_info.album[0]);
  component_album_stylist_name.setState(album_info.album[0]);
  component_album_stylist_name.setProps({stylist: stylist_info.stylist});
  component_album_image_path.setState(album_info.album[0]);

});