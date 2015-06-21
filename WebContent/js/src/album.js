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
  var component_album_category = React.render(React.createElement(AlbumCategory, null), document.getElementById('album_category'));
  var component_album_name = React.render(React.createElement(AlbumName, null), document.getElementById('album_name'));
  var component_album_stylist_name = React.render(React.createElement(AlbumStylistName, null), document.getElementById('album_stylist_name'));
  var component_album_image_path = React.render(React.createElement(AlbumImagePath, null), document.getElementById('album_image_path'));


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