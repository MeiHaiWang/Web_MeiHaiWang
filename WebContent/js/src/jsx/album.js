$(function(){

  /*
   * TODO: 
   * ・Tag-itのCSSが反映されない
   * ・男性女性や条件の決め打ちをどうにかする
   * 　→ 1.性別 2.長さ 3.イメージ 4.長さ（女性） 5.イメージ（女性）
   * ・見た目の整形
   * ・中国語対応
   * ・正面横背後が４枚以上選ばれたらだめにする
   * ・ヘアアルバムの一覧表示
   * 　・写真一覧にする？とりあえずいまのまま
   * 　・条件を選んだものを表示する
   * 　・条件を複数選んだ場合
   * 　・編集ボタンを押したら編集画面に表示
   * 　　そのときは、条件一覧も表示されるようにする
   * ・編集押してから新規の登録することができない
   * ・画像のアップロード同じものもアップロードを認めるか
   * ・画像のサイズ指定（でかいのは表示できないように）
   */
	
	/*
    Component for React
  */
  // コンポーネントの定義
	/*
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
*/

	var AlbumSex = React.createClass({
		    getDefaultProps() {
		      return {
		        gender_tag: ['']
		      };
		    },
		    getInitialState() {
		      return {
		        t_hairStyle_searchConditionId: '',
		      };
		    },
		    onChangeSelectValue(e) {
		    	/*
		    	 * 男と女でプルダウンの項目
		    	 */
		    	//console.log(v_array[0][0].name+":"+e.target.value);
		    	if(v_array[0][0].name == e.target.value){
		    		var _v1_2 = v1(1) //titleId=1, length(men)
				    //console.log("_v1_2:"+_v1_2[0]);
				    component_album_long.setProps({length_tag:_v1_2});
				    var _v1_3 = v1(2) //titleId=2, feel(men)
				    //console.log("_v1_3:"+_v1_3[0]);
				    component_album_feel.setProps({feel_tag:_v1_3});
		    	}else{
		    		var _v1_2 = v1(3) //titleId=3, length(women)
				    //console.log("_v1_2:"+_v1_2[0]);
				    component_album_long.setProps({length_tag:_v1_2});
				    var _v1_3 = v1(4) //titleId=4, feel(women)
				    //console.log("_v1_3:"+_v1_3[0]);
				    component_album_feel.setProps({feel_tag:_v1_3});
		    	}
			      //選択された項目のidを取得
			      var addId = nameToId(e.target.value);
			    	/*
			      //すでに登録されたidでなければ、condIdに追加
			      condId += "";
			      if(!hasAddTag(condId, addId)){
			    	  if(condId.length<=0){
			    		  condId=addId;	    		  
			    	  }else{
			    		  condId+=","+addId;
			    	  }
			      }
			      */
			      //サロン検索条件のリストをいったん削除
			      $("#album_sex_list").tagit("removeAll");
			      $("#album_long_list").tagit("removeAll");
			      $("#album_feel_list").tagit("removeAll");

			      var _v2 = v2(addId, 0);
			      for(i=0;i<_v2.length;i++){
		    	    $("#album_sex_list").tagit("createTag", _v2[i]);
		    	  }
				  var _v2_1 = v2(condId, 1);
				  for(i=0;i<_v2_1.length;i++){
				    $("#album_long_list").tagit("createTag", _v2_1[i]);
				  }
				  var _v2_2 = v2(condId, 2);
				  for(i=0;i<_v2_2.length;i++){
				    $("#album_feel_list").tagit("createTag", _v2_2[i]);
				  }
				  var _v2_3 = v2(condId, 3);
				  for(i=0;i<_v2_3.length;i++){
				    $("#album_long_list").tagit("createTag", _v2_3[i]);
				  }
				  var _v2_4 = v2(condId, 4);
				  for(i=0;i<_v2_4.length;i++){
				    $("#album_feel_list").tagit("createTag", _v2_4[i]);
				  }

			      
			      this.setState({t_hairStyle_searchConditionId: addId});
			      //component_cond_list.setProps({cond_list: _v2});

		     // this.setState({t_hairStyle_hairTypeId: e.target.value});
		    },
		    render() {
		      var options = this.props.gender_tag.map(
	    		  function(gender_tag) {
	    		  return React.createElement("option",
	  		  	        {value: gender_tag}, gender_tag);
		      });
		      return (
		        <div>
		          <select value={this.state.t_hairStyle_searchConditionId} onChange={this.onChangeSelectValue}>
		            {options}
		          </select>
		        </div>
		      );
		    }
		  });

	/*
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
*/
	  var AlbumLong = React.createClass({
		    getDefaultProps() {
			      return {
			        length_tag: ['']
			      };
			    },
			    getInitialState() {
			      return {
			        t_hairStyle_searchConditionId: '',
			      };
			    },
			    onChangeSelectValue(e) {
				      //選択された項目のidを取得
				      var addId = nameToId(e.target.value);
				      //すでに登録されたidでなければ、condIdに追加
				      condId += "";
				      if(!hasAddTag(condId, addId)){
				    	  if(condId.length<=0){
				    		  condId=addId;	    		  
				    	  }else{
				    		  condId+=","+addId;
				    	  }
				      }
				      //サロン検索条件のリストをいったん削除
				      $("#album_long_list").tagit("removeAll");
				      /*
				       * 8: Men :v_array[0][0]
				       * 9: Women :v_array[0][1]
				       */
				      if(component_album_sex.state.t_hairStyle_searchConditionId == v_array[0][0].id){
					      var _v2_2 = v2(condId, 1);
				      }else{
					      var _v2_2 = v2(condId, 3);
				      }
				      for(i=0;i<_v2_2.length;i++){
				    	    $("#album_long_list").tagit("createTag", _v2_2[i]);
				      }
			    	  //console.log("title2_condId:"+condId);
				      this.setState({t_hairStyle_searchConditionId: condId});
				      //component_cond_list.setProps({cond_list: _v2});

			    	// this.setState({t_hairStyle_hairTypeId: e.target.value});
			    },
			    render() {
			      var options = this.props.length_tag.map(
		    		  function(length_tag) {
		    		  return React.createElement("option",
		  		  	        {value: length_tag}, length_tag);
			      });
			      return (
			        <div>
			          <select value={this.state.t_hairStyle_searchConditionId} onChange={this.onChangeSelectValue}>
			            {options}
			          </select>
			        </div>
			      );
			    }
	  });
	  
	  var AlbumFeel = React.createClass({
		    getDefaultProps() {
			      return {
			         feel_tag: ['']
			      };
			    },
			    getInitialState() {
			      return {
			        t_hairStyle_searchConditionId: '',
			      };
			    },
			    onChangeSelectValue(e) {
				      //選択された項目のidを取得
				      var addId = nameToId(e.target.value);
				      //すでに登録されたidでなければ、condIdに追加
				      condId += "";
				      if(!hasAddTag(condId, addId)){
				    	  if(condId.length<=0){
				    		  condId=addId;	    		  
				    	  }else{
				    		  condId+=","+addId;
				    	  }
				      }
				      //サロン検索条件のリストをいったん削除
				      $("#album_feel_list").tagit("removeAll");
				      if(component_album_sex.state.t_hairStyle_searchConditionId == v_array[0][0].id){
					      var _v2_3 = v2(condId, 2);
				      }else{
					      var _v2_3 = v2(condId, 4);
				      }
				      for(i=0;i<_v2_3.length;i++){
			    	    $("#album_feel_list").tagit("createTag", _v2_3[i]);
			    	  }
			    	  //console.log("title2_condId:"+condId);
				      this.setState({t_hairStyle_searchConditionId: condId});
				      //component_cond_list.setProps({cond_list: _v2});
			     // this.setState({t_hairStyle_hairTypeId: e.target.value});
			    },
			    render() {
			      var options = this.props.feel_tag.map(
		    		  function(feel_tag) {
		    		  return React.createElement("option",
		  		  	        {value: feel_tag}, feel_tag);
			      });
			      return (
			        <div>
			          <select value={this.state.t_hairStyle_searchConditionId} onChange={this.onChangeSelectValue}>
			            {options}
			          </select>
			        </div>
			      );
			    }
	  });

	  
	  //検索用↓
	  var AlbumSearchSex = React.createClass({
		    getDefaultProps() {
		      return {
		    	  search_gender_tag: ['']
		      };
		    },
		    getInitialState() {
		      return {
		    	  album_search_sex_id: ""
		      };
		    },
		    onChangeSelectValue(e) {
		    	/*
		    	 * 男と女でプルダウンの項目
		    	 */
		    	if(v_array[0][0].name == e.target.value){
		    		var _v1_2 = v1(1) //titleId=1, length(men)
				    component_search_album_long.setProps({search_length_tag:_v1_2});
				    var _v1_3 = v1(2) //titleId=2, feel(men)
				    component_search_album_feel.setProps({search_feel_tag:_v1_3});
		    	}else{
		    		var _v1_2 = v1(3) //titleId=3, length(women)
				    component_search_album_long.setProps({search_length_tag:_v1_2});
				    var _v1_3 = v1(4) //titleId=4, feel(women)
				    component_search_album_feel.setProps({search_feel_tag:_v1_3});
		    	}
			      //選択された項目のidを取得
			      var addId = nameToId(e.target.value);
			      this.setState({album_search_sex_id: addId});
		    },
		    render() {
		      var options = this.props.search_gender_tag.map(
	    		  function(search_gender_tag) {
	    		  return React.createElement("option",
	  		  	        {value: search_gender_tag}, search_gender_tag);
		      });
		      return (
		        <div>
		          <select onChange={this.onChangeSelectValue}>
		            {options}
		          </select>
		        </div>
		      );
		    }
		  });

	  var AlbumSearchLong = React.createClass({
		    getDefaultProps() {
		      return {
		    	  search_length_tag: ['']
		      };
		    },
		    getInitialState() {
		      return {
		    	  album_search_long_id : ""
		      };
		    },
		    onChangeSelectValue(e) {
			      //選択された項目のidを取得
			      var addId = nameToId(e.target.value);
			      this.setState({album_search_long_id: addId});
		    },
		    render() {
		      var options = this.props.search_length_tag.map(
	    		  function(search_length_tag) {
	    		  return React.createElement("option",
	  		  	        {value: search_length_tag}, search_length_tag);
		      });
		      return (
		        <div>
		          <select onChange={this.onChangeSelectValue}>
		            {options}
		          </select>
		        </div>
		      );
		    }
		  });

	  var AlbumSearchFeel = React.createClass({
		    getDefaultProps() {
		      return {
		    	  search_feel_tag: ['']
		      };
		    },
		    getInitialState() {
		      return {
		    	  album_search_feel_id : ""
		      };
		    },
		    onChangeSelectValue(e) {
			      //選択された項目のidを取得
			      var addId = nameToId(e.target.value);
			      this.setState({album_search_feel_id: addId});
		    },
		    render() {
		      var options = this.props.search_feel_tag.map(
	    		  function(search_feel_tag) {
	    		  return React.createElement("option",
	  		  	        {value: search_feel_tag}, search_feel_tag);
		      });
		      return (
		        <div>
		          <select onChange={this.onChangeSelectValue}>
		            {options}
		          </select>
		        </div>
		      );
		    }
		  });

	  
  /*
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
*/
/*
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
*/
  /*
  var AlbumImagePath = React.createClass({
	    getDefaultProps() {
	        return {
	          image_path_list: ['']
	        };
	      },
	    getInitialState() {
	      return {
	        t_hairStyle_imagePath: "img/notfound.jpg"
	      };
	    },
	    render() {
	    	//console.log("album_image render.: "+ this.state.t_hairStyle_imagePath);
	    	var ip = this.state.t_hairStyle_imagePath + "";
	    	this.state.image_path_list = ip.split(',');
	        var images = this.state.image_path_list.map(function(image) {
    			return <img className="album_image_img" src={image?image:'img/notfound.jpg'} />;
	    	});
	      return (
	        <div>
	          {images}
	        </div>
	      );
	    }
	  });
	  */

  var AlbumImagePath = React.createClass({
	    getDefaultProps() {
	        return {
	          t_hairStyle_imagePath: ""
	        };
	      },
	    getInitialState() {
	      return {
	        t_hairStyle_imagePath: "img/notfound.jpg"
	      };
	    },
	    render() {
	      console.log("album_image render.: "+ this.state.t_hairStyle_imagePath);
	      return (
	        <div>
	        	<img className="image" src={this.state.t_hairStyle_imagePath?this.state.t_hairStyle_imagePath:'img/notfound.jpg'} />
	        </div>
	      );
	    }
	  });

  var AlbumImagePath2 = React.createClass({
	    getDefaultProps() {
	        return {
	        	t_hairStyle_imagePath2: ""
	        };
	      },
	    getInitialState() {
	      return {
	        t_hairStyle_imagePath2: "img/notfound.jpg"
	      };
	    },
	    render() {
	    	//console.log("album_image render.: "+ this.state.t_hairStyle_imagePath);
	      return (
	        <div>
	        	<img className="image" src={this.state.t_hairStyle_imagePath2?this.state.t_hairStyle_imagePath2:'img/notfound.jpg'} />
	        </div>
	      );
	    }
	  });

  var AlbumImagePath3 = React.createClass({
	    getDefaultProps() {
	        return {
	        	t_hairStyle_imagePath3: ""
	        };
	      },
	    getInitialState() {
	      return {
	        t_hairStyle_imagePath3: "img/notfound.jpg"
	      };
	    },
	    render() {
	    	//console.log("album_image render.: "+ this.state.t_hairStyle_imagePath);
	      return (
	        <div>
	        	<img className="image" src={this.state.t_hairStyle_imagePath3?this.state.t_hairStyle_imagePath3:'img/notfound.jpg'} />
	        </div>
	      );
	    }
	  });

  
  //アルバム一覧
  var AlbumList = React.createClass({
    getInitialState() {
      return {
        album_list: [{
          "t_hairStyle_id": "",
          "t_hairStyle_hairTypeId": "",
          "t_hairStyle_name": "",
          "t_hairStyle_stylistId": "",
          "t_hairStyle_imagePath": "img/notfound.jpg",
          "t_hairStyle_imagePath2": "img/notfound.jpg",
          "t_hairStyle_imagePath3": "img/notfound.jpg",          
          "t_hairStyle_searchConditionId": "",
          "gender" : "",
          "hair_length" : "",
          "feeling": ""
    	  }]
      };
    },
    render() {
      var index = 1;
      /*
      var album = this.state.album_list.map(function(album) {
    	//console.log("album_render:-["+album.t_hairStyle_id+"]:"+album.t_hairStyle_searchConditionId+","+album.gender+","+album.hair_length+","+album.feeling);
    	//console.log("imagePath:"+album.t_hairStyle_imagePath);
    	return 
    	<tr>
    	<td>{index++}</td>
    	<td><img src={album.t_hairStyle_imagePath?album.t_hairStyle_imagePath:'img/notfound.jpg'} /></td>
        <td><img src={album.t_hairStyle_imagePath2?album.t_hairStyle_imagePath2:'img/notfound.jpg'} /></td>
        <td><img src={album.t_hairStyle_imagePath3?album.t_hairStyle_imagePath3:'img/notfound.jpg'} /></td>
        <td><a className="edit">编辑</a>/<a className="delete">删除</a></td></tr>;
      });
      */
      var album = this.state.album_list.map(function(album) {
      	console.log("album_render:-["+album.t_hairStyle_id+"]:"+album.t_hairStyle_searchConditionId+","+album.gender+","+album.hair_length+","+album.feeling);
      	return 
      	<tr>
      	<td>{index++}</td>
      	 <td>{album.gender}</td><td>{album.hair_length}</td><td>{album.feeling}</td><td>
          <img src={album.t_hairStyle_imagePath?album.t_hairStyle_imagePath:'img/notfound.jpg'} /></td><td>
          <img src={album.t_hairStyle_imagePath2?album.t_hairStyle_imagePath2:'img/notfound.jpg'} /></td><td>
          <img src={album.t_hairStyle_imagePath3?album.t_hairStyle_imagePath3:'img/notfound.jpg'} />
          </td><td><a className="edit">编辑</a>/<a className="delete">删除</a></td></tr>;
        });
      /*
      var album = this.state.album_list.map(function(album) {
          return <tr><td>{index++}</td><td>{hairtypes[album.t_hairStyle_hairTypeId]}</td><td>{album.t_hairStyle_name}</td><td>{typeof album.t_hairStyle_stylistId!=="undefined"?stylists[album.t_hairStyle_stylistId]:''}</td><td><img src={album.t_hairStyle_imagePath?album.t_hairStyle_imagePath:'img/notfound.jpg'} /></td><td><a className="edit">编辑</a>/<a className="delete">删除</a></td></tr>;
        });
        */
      /*
      return (
        <div>
          <table>
          <tr>
            <th>No.</th>
            <th>照片</th><th>照片2</th><th>照片3</th><th>编辑</th>
          </tr>
            {album}
          </table>
        </div>
      );
      */
      return (
    	        <div>
    	          <table><tr>
    	            <th>No.</th>
    	            <th>性別</th><th>長さ</th><th>イメージ</th>
    	            <th>照片</th><th>照片2</th><th>照片3</th><th>编辑</th></tr>
    	            {album}
    	          </table>
    	        </div>
    	      );

    }
  });

  // set state to component
  function componentSetState(album) {
	  /*
	console.log("Edit_sid of album{id "+album.t_hairStyle_id+"}:" + album.t_hairStyle_searchConditionId);
	console.log(" t_hairStyle_imagePath:"+album.t_hairStyle_imagePath);
	console.log(" t_hairStyle_imagePath2:"+album.t_hairStyle_imagePath2);
	console.log(" t_hairStyle_imagePath3:"+album.t_hairStyle_imagePath3);
	*/

    // コンポーネントにjsonを渡して関係する部分だけ書き換わる
	/*
    component_album_category.setState(album);
    component_album_name.setState(album);
    component_album_stylist_name.setState(album);
    */
	
    //component_album_image_path.setState(album);

    component_album_image_path.setState(album);
    component_album_image_path2.setState(album);
    component_album_image_path3.setState(album);

    //サロン検索条件のリストをいったん削除
    $("#album_sex_list").tagit("removeAll");
    $("#album_long_list").tagit("removeAll");
    $("#album_feel_list").tagit("removeAll");
	  
    condId = album.t_hairStyle_searchConditionId;
	  //$('#album_sex_list').tagit({placeholderText:"AlbumSex",fieldName:"tags"});
	  var _v2 = v2(condId, 0);
	  //component_salon_cond_list.setProps({cond_list: _v2});
	  // $('#salon_cond_list1').tagit({placeholderText:"タグをつけよう",fieldName:"tags[]"});
	  for(i=0;i<_v2.length;i++){
	    $("#album_sex_list").tagit("createTag", _v2[i]);
	  }
	  // $("#salon_cond_list1").tagit({placeholderText:"タグをつけよう",fieldName:"tags"});

	  //$('#album_long_list').tagit({placeholderText:"AlbumLong",fieldName:"tags2"});
	  var _v2_2 = v2(condId, 1);
	  //component_salon_cond_list.setProps({cond_list: _v2});
	  // $('#salon_cond_list1').tagit({placeholderText:"タグをつけよう",fieldName:"tags[]"});
	  for(i=0;i<_v2_2.length;i++){
	    $("#album_long_list").tagit("createTag", _v2_2[i]);
	  }

	  //$('#album_feel_list').tagit({placeholderText:"AlbumFeel",fieldName:"tags3"});
	  var _v2_3 = v2(condId, 2);
	  //component_salon_cond_list.setProps({cond_list: _v2});
	  // $('#salon_cond_list1').tagit({placeholderText:"タグをつけよう",fieldName:"tags[]"});
	  for(i=0;i<_v2_3.length;i++){
	    $("#album_feel_list").tagit("createTag", _v2_3[i]);
	  }

	  //女性用
	  var _v2_2 = v2(condId, 3);
	  for(i=0;i<_v2_2.length;i++){
	    $("#album_long_list").tagit("createTag", _v2_2[i]);
	  }
	  var _v2_3 = v2(condId, 4);
	  for(i=0;i<_v2_3.length;i++){
	    $("#album_feel_list").tagit("createTag", _v2_3[i]);
	  }
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

  // stylistを参照しやすい形に変換
  var stylists = new Array();

  var search_condition_type = {
		  t_masterSearchConditionType: "ヘアスタイル検索条件"
  }
  sanitaize.encode(search_condition_type);
  var search_condition = getSearchConditionList(search_condition_type);
  sanitaize.decode(search_condition);
  var titles = search_condition.titles;
  var values = search_condition.values;
  
  /*
   * 検索条件を扱いやすいように再配列
   * t_array: titlesのリスト
   * 	titles[x] -> t_array[0],     t_array[1]
   * v_array: valuesのリスト
   * 	values[y] -> v_array[0][j] , v_array[1][j]
   */
  var t_array = new Array();
  var v_array = new Array();
  var toArrays = (function(){
	//console.log("titles.length:"+titles.length);
    for(i=0;i<titles.length;i++){
      //console.log("titles.length-i:"+i);
    	t_array[i] = {id: titles[i].id, name: titles[i].name};
    	console.log("t_array["+i+"]:"+t_array[i].name);
    }    
    for(i=0;i<t_array.length;i++){
      v_array[i] = new Array();
      var index=0;
      for(j=0;j<values.length;j++){
        if(t_array[i].id == values[j].titleID){
          v_array[i][index] = {id: values[j].id, name: values[j].name};
          //console.log("(i,index)="+i+","+index+": "+v_array[i][index].id+","+v_array[i][index].name+"<br>");
          index++;
        }
      }
      console.log("v_array["+i+"]:"+v_array[i][0].name);
    }
    
    //男、女で、分けて考える
    /* v_array[0][0]: men, v_array[0][1]: women
     * v_array[1][x]: long(men)
     * v_array[2][x]: feel(men)
     * v_array[3][x]: long(women)
     * v_array[4][x]: feel(women)
     */
  }());
  
  /*
   * 条件名をタイトル名から取得
   */
  $("#hs_cond_tag_name1").text(t_array[0].name+":");
  $("#hs_cond_tag_name2").text(t_array[1].name+":");
  $("#hs_cond_tag_name3").text(t_array[2].name+":");


  /*
    Component Render
  */
  // コンポーネントをエレメントに割り当てる
  /*
  var component_album_category = React.render(<AlbumCategory />, document.getElementById('album_category'));
  var component_album_name = React.render(<AlbumName />, document.getElementById('album_name'));
  var component_album_stylist_name = React.render(<AlbumStylistName />, document.getElementById('album_stylist_name'));
  */
  var component_album_sex = React.render(<AlbumSex />, document.getElementById('album_sex'));
  var component_album_long = React.render(<AlbumLong />, document.getElementById('album_long'));
  var component_album_feel = React.render(<AlbumFeel />, document.getElementById('album_feel'));
  var component_album_image_path = React.render(<AlbumImagePath />, document.getElementById('album_image_path'));
  var component_album_image_path2 = React.render(<AlbumImagePath2 />, document.getElementById('album_image_path2'));
  var component_album_image_path3 = React.render(<AlbumImagePath3 />, document.getElementById('album_image_path3'));
  var component_album_list = React.render(<AlbumList />, document.getElementById('album_list_info'));

  //search
  var component_search_album_sex = React.render(<AlbumSearchSex />, document.getElementById('album_search_sex'));
  var component_search_album_long = React.render(<AlbumSearchLong />, document.getElementById('album_search_long'));
  var component_search_album_feel = React.render(<AlbumSearchFeel />, document.getElementById('album_search_feel'));

  //tagitを表示
  $('#album_sex_list').tagit({placeholderText:"AlbumSex",fieldName:"tags"});
  $('#album_long_list').tagit({placeholderText:"AlbumLong",fieldName:"tags2"});
  $('#album_feel_list').tagit({placeholderText:"AlbumFeel",fieldName:"tags3"});

  /*
    Main Part
  */
  // セッションIDからアルバム情報を取得する
  var session_info = getSessionInfo();
  var album_info = getAlbumInfo(session_info.t_hairSalonMaster_salonId);

  sanitaize.decode(album_info);
  
/*
  // stylistを参照しやすい形に変換
  var stylist_info = getStylistList({'t_hairSalonMaster_salonId':session_info.t_hairSalonMaster_salonId});
  sanitaize.decode(stylist_info);
  for (var i = 0; i < stylist_info.stylist.length; i++) {
    var stylist_id = stylist_info.stylist[i].t_stylist_stylist_id;
    var stylist_name = stylist_info.stylist[i].t_stylist_name;
    stylists[stylist_id] = stylist_name;
  }
*/
  
  //
  var v1 = function(title_id){
	    var v1 = [];
	    v1.push("Select a tags of ["+t_array[title_id].name+"]");
	    for(i=0;i<v_array[title_id].length;i++){
	      v1.push(v_array[title_id][i].name);
	    }
	    return v1;
	  };

	  var _v1 = v1(0) //titleId=0, gender
	  //console.log("_v1:"+_v1[0]);
	  component_album_sex.setProps({gender_tag:_v1});
	  component_search_album_sex.setProps({search_gender_tag:_v1});
	  /*
	  var _v1_2 = v1(1) //titleId=1, length
	  console.log("_v1_2:"+_v1_2[0]);
	  component_album_long.setProps({length_tag:_v1_2});
	  var _v1_3 = v1(2) //titleId=2, feeling
	  console.log("_v1_3:"+_v1_3[0]);
	  component_album_feel.setProps({feel_tag:_v1_3});
  	  */
	  /*
	   * サロンの検索条件一覧からタイトルごとに、
	   * searchConditionIdで選択されていた条件リストを、
	   * タグとして表示しておくためのリスト
	   */
	  var v2 = function(t_hairStyle_searchConditionId, t_id){
	    //var v2 = new Array();
		var v2 = [];
	    var index=0;
	    t_hairStyle_searchConditionId = t_hairStyle_searchConditionId+"";
	    var idlist = t_hairStyle_searchConditionId.split(',');
	     for(i=0;i<idlist.length;i++){
	       for(j=0;j<values.length;j++){
	         if(idlist[i] == values[j].id){
	        	 //console.log("i,j="+i+","+j+" values:"+values[j].id+","+values[j].name+","+values[j].titleID);
	        	 //console.log("t_arrayid:"+t_array[t_id].name);
	        	 if(values[j].titleID == t_array[t_id].id){
	                 v2.push(values[j].name);
	                 //v2[index++] = {"name": values[j].name};
	                 //console.log(j+": name:"+ values[j].name);
	        	 }
	         }
	       }
	     }
	    return v2;
	  }; 

	  
	  /*
	   * condidの中に、addidが含まれているかどうか
	   */
	  var hasAddTag = function(condid, addid){
		  condid=condid+"";
		  var idlist =
		   condid.split(',');
		  for(i=0;i<idlist.length;i++){
		    if(idlist[i]==addid){
		      return true;
		    }
		  }
		  //idlist.push(addid);
		  //document.write("addid:"+addid);
		  return false;
		};
		
		//valuesのリストから、
		//name -> id を引っ張る
		var nameToId = function(name){
		  var id=-1;
		  for(i=0;i<values.length;i++){
		      if(name == values[i].name){
		        id = values[i].id;
		      }
		  }
		  return id;
		};

		//searchId から性別を取得
		var getGender = function(id){
			var _gender="";
			id+="";
			var idlist = id.split(',');
			var men = v_array[0][0] //men
			var women = v_array[0][1] //women
			for(l=0; l<idlist.length; l++){
				//console.log("getGender:::id="+idlist[l]+"? m,w="+men.id+","+women.id);
				if(idlist[l] == men.id){
					_gender = men;
					break;
				}else if(idlist[l] == women.id){
					_gender = women;
					break;
				}
			}
			return _gender;
		};

		//searchId から長さ条件一覧を取得
		var getLength = function(id){
			var _length = [];
			id+="";
			var idlist = id.split(',');
			var men_length = v_array[1] //men用length_List
			var women_length = v_array[3] //women
			for(l=0; l<idlist.length; l++){
				for(j=0; j<men_length.length; j++){
					if(idlist[l] == men_length[j].id){
						_length.push(men_length[j]);
						//console.log("ml:"+men_length[j].name);
						break;
					}
				}
				for(j=0; j<women_length.length; j++){
					if(idlist[l] == women_length[j].id){
						_length.push(women_length[j]);
						//console.log("wl:"+women_length[j].name);
						break;
					}
				}
			}
			return _length;
		};

		//searchId からイメージ条件一覧を取得
		var getFeeling = function(id){
			var _feeling = [];
			id+="";
			var idlist = id.split(',');
			var men_feeling = v_array[2] //men用feeling_List
			var women_feeling = v_array[4] //women
			for(l=0; l<idlist.length; l++){
				for(j=0; j<men_feeling.length; j++){
					if(idlist[l] == men_feeling[j].id){
						_feeling.push(men_feeling[j]);
						break;
					}
				}
				for(j=0; j<women_feeling.length; j++){
					if(idlist[l] == women_feeling[j].id){
						_feeling.push(women_feeling[j]);
						break;
					}
				}
			}
			return _feeling;
		};

	  
  // アカウント名を表示
  $('#account-name').text(session_info.t_hairSalonMaster_contactUserName);

  // set component
  //component_album_category.setProps({hairtype: hairtype_info.type});
  //component_album_stylist_name.setProps({stylist: stylist_info.stylist});

  //searchConditionIdからalbum情報を拡張して、setState用に
  for(i=0; i<album_info.album.length; i++){
	  var album = album_info.album[i];
	  console.log("set condition of album["+i+"]:"+album.t_hairStyle_searchConditionId);
		var _gender = getGender(album.t_hairStyle_searchConditionId);
		//album_info.album[i]={gender: _gender.name};
		album_info.album[i]["gender"]=_gender.name;
		//album_info.album[i].gender=_gender.name;
		//console.log(album_info.album[i].gender+", "+_gender.name);
		
		var length_list = getLength(album.t_hairStyle_searchConditionId);
		if(length_list.length>0){
			var _length=length_list[0].name;
			for(k=1;k<length_list.length;k++){
				_length+=","+length_list[k].name;
			}
			album_info.album[i]["hair_length"] = _length;
			//album_info.album[i].hair_length = _length;
			//album_info.album[i]={hair_length: _length};
			//console.log(album_info.album[i].hair_length+", "+_length);
		}
		
		var feeling_list = getFeeling(album.t_hairStyle_searchConditionId);
		if(feeling_list.length>0){
			var _feeling=feeling_list[0].name;
			for(k=1;k<feeling_list.length;k++){
				_feeling+=","+feeling_list[k].name;
			}
			//album_info.album[i].feeling = _feeling;
			//album_info.album[i]={feeling: _feeling};
			album_info.album[i]["feeling"] = _feeling;
			//console.log(album_info.album[i].feeling+", "+_feeling);
		}

		var t_path_list = album.t_hairStyle_imagePath + "";
		var path_list = t_path_list.split(',');
		for(k=1;k<path_list.length;k++){
			album_info.album[i]["t_hairStyle_imagePath"+k] = path_list[k];			
		}
  }
  
  //debug
  for(i=0; i<album_info.album.length; i++){
	  var album = album_info.album[i];
	  console.log("album["+i+"]:");
	  console.log(" t_hairStyle_id:"+album.t_hairStyle_id);
	  console.log(" t_hairStyle_hairTypeId:"+album.t_hairStyle_hairTypeId);
	  console.log(" t_hairStyle_imagePath:"+album.t_hairStyle_imagePath);
	  console.log(" t_hairStyle_imagePath2:"+album.t_hairStyle_imagePath2);
	  console.log(" t_hairStyle_imagePath3:"+album.t_hairStyle_imagePath3);
	  console.log(" t_hairStyle_searchConditionId:"+album.t_hairStyle_searchConditionId);
 	  console.log(" gender:"+album.gender);
 	  console.log(" hair_length:"+album.hair_length);
 	  console.log(" feeling:"+album.feeling);
  }
  
  // アルバム一覧をsetState
  if (album_info.album.length != 0) {
    component_album_list.setState({"album_list":album_info.album});
  }
  
  //検索条件id一覧
  var condId=""; 

  //edit_id
  var edit_id = -1;
  
  /*
  // album_info[]が持つ検索条件のidリスト
  var condId =[]; 
  for(i=0; i<album_info.album.length; i++){
	  condId[i] = album_info.album[i].t_hairStyle_searchConditionId;
  }
 */
  
  /*
  component_album_category.setState({t_hairStyle_hairTypeId: component_album_category.props.hairtype[0].t_hairType_id});
  if (component_album_stylist_name.props.stylist.length != 0) {
    component_album_stylist_name.setState({t_hairStyle_stylistId: component_album_stylist_name.props.stylist[0].t_stylist_stylist_id});
  }
	*/

  /*
    Button Handler
  */
  // 登録ボタン押下時
  $('#album_regist_button').on('click', function() {
	  //serchConditionIdの最新版を取得
	  var searchConditionIds="";
	  var elms = document.getElementsByName("tags");
	  var elms2 = document.getElementsByName("tags2");
	  var elms3 = document.getElementsByName("tags3");
	  
	  var text = "";
	  for(p=0;p<elms.length;p++){
		  //console.log(p+"~"+elms.length+":"+elms[p].value);
		  if(nameToId(elms[p].value)!=-1){
			  if(text.length==0){
				  text = nameToId(elms[p].value);
			  }else{
				  text += "," + nameToId(elms[p].value);
			  }
		  }
	  }
	  for(q=0;q<elms2.length;q++){
		  //console.log(q+"~"+elms2.length+":"+elms2[q].value);
		  if(nameToId(elms2[q].value)!=-1){
			  if(text.length==0){
				  text = nameToId(elms2[q].value);
			  }else{
				  text += "," + nameToId(elms2[q].value);
			  }
		  }
	  }
	  for(q=0;q<elms3.length;q++){
		  //console.log(q+"~"+elms2.length+":"+elms2[q].value);
		  if(nameToId(elms3[q].value)!=-1){
			  if(text.length==0){
				  text = nameToId(elms3[q].value);
			  }else{
				  text += "," + nameToId(elms3[q].value);
			  }
		  }
	  }
	  searchConditionIds = text;
	  //console.log("searchConditionIds:"+searchConditionIds);

	  //imagePath を結合
	  var image_paths = "";
	  image_paths = component_album_image_path.state.t_hairStyle_imagePath;
	  image_paths += "," + component_album_image_path.state.t_hairStyle_imagePath2;
	  image_paths += "," + component_album_image_path.state.t_hairStyle_imagePath3;
	  
	  /*
    var data = {
      t_hairSalonMaster_salonId: session_info.t_hairSalonMaster_salonId,
      t_hairStyle_id:            component_album_category.state.t_hairStyle_id,
      t_hairStyle_hairTypeId:    component_album_category.state.t_hairStyle_hairTypeId,
      t_hairStyle_name:          component_album_name.state.t_hairStyle_name,
      t_hairStyle_stylistId:     component_album_stylist_name.state.t_hairStyle_stylistId,
      t_hairStyle_imagePath:     component_album_image_path.state.t_hairStyle_imagePath,
      t_hairStyle_searchConditionId: searchConditionId
    }
    */
    var data = {
    	      t_hairSalonMaster_salonId: session_info.t_hairSalonMaster_salonId,
    	      t_hairStyle_id:            edit_id,
    	      t_hairStyle_hairTypeId:    "0",
    	      t_hairStyle_name:          "",
    	      t_hairStyle_stylistId:     "0",
    	      t_hairStyle_imagePath:     image_paths,
    	      t_hairStyle_searchConditionId: searchConditionIds
    }

    // サニタイズ
    sanitaize.encode(data);

    //debug
	console.log("Regist album["+i+"]:");
	console.log(" t_hairStyle_id:"+album.t_hairStyle_id);
	console.log(" t_hairStyle_hairTypeId:"+album.t_hairStyle_hairTypeId);
	console.log(" t_hairStyle_imagePath:"+album.t_hairStyle_imagePath);
	console.log(" t_hairStyle_searchConditionId:"+album.t_hairStyle_searchConditionId);
    
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
    //console.log("edit album["+id+"]");
    edit_id = album_info.album[id].t_hairStyle_id;
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
    	  var img_path = component_album_image_path.state.t_hairStyle_imagePath;
    	  console.log("component_album_image_path.state.t_hairStyle_imagePath:"+img_path)
    	  console.log("result.image_path:"+result.image_path)
    	  /*
    	  if(img_path=="img/notfound.jpg"){
    		 img_path=result.image_path; 
    	  }else{
     		 img_path+=","+result.image_path; 
    	  }
    	  */
          //component_album_image_path.setState({t_hairStyle_imagePath: img_path});
          component_album_image_path.setState({t_hairStyle_imagePath: result.image_path});
      }
      else {
        alert('Upload Failed');
      }
    }
  });

  // 画像アップロード2
  $('#album_image2').change(function() {
    // ファイルが選択されたか
    if($(this).prop('files')[0]){
      var data = new FormData($('#update')[0]);

      var result = uploadImage(data);
      if (result.result == "true") {
    	  //var img_path = component_album_image_path2.state.t_hairStyle_imagePath2;
    	  //console.log("component_album_image_path.state.t_hairStyle_imagePath:"+img_path)
    	  //console.log("result.image_path:"+result.image_path)
    	  /*
    	  if(img_path=="img/notfound.jpg"){
    		 img_path=result.image_path; 
    	  }else{
     		 img_path+=","+result.image_path; 
    	  }
    	  */
          //component_album_image_path.setState({t_hairStyle_imagePath: img_path});

        component_album_image_path2.setState({t_hairStyle_imagePath2: result.image_path});
      }
      else {
        alert('Upload Failed');
      }
    }
  });

  // 画像アップロード
  $('#album_image3').change(function() {
    // ファイルが選択されたか
    if($(this).prop('files')[0]){
      var data = new FormData($('#update')[0]);

      var result = uploadImage(data);
      if (result.result == "true") {
    	  //var img_path = component_album_image_path.state.t_hairStyle_imagePath;
    	  //console.log("component_album_image_path.state.t_hairStyle_imagePath:"+img_path)
    	  //console.log("result.image_path:"+result.image_path)
    	  /*
    	  if(img_path=="img/notfound.jpg"){
    		 img_path=result.image_path; 
    	  }else{
     		 img_path+=","+result.image_path; 
    	  }
        component_album_image_path.setState({t_hairStyle_imagePath: img_path});
        */
        component_album_image_path3.setState({t_hairStyle_imagePath3: result.image_path});
      }
      else {
        alert('Upload Failed');
      }
    }
  });

  // 一覧表示 (えらんだ検索条件のものだけ)
  $('#album_search_button').on('click', function() {
	  var album_sex_id = component_search_album_sex.state.album_search_sex_id;
	  var album_long_id = component_search_album_long.state.album_search_long_id;
	  var album_feel_id = component_search_album_feel.state.album_search_feel_id;

	  var albums = album_info.album;
	  var searched_albums = [];
	  
	  if(album_sex_id!=""){
		  var sa1 = [];
		  for(x=0;x<albums.length;x++){
			  var cond_list_str = albums[x].t_hairStyle_searchConditionId + "";
			  var cond_list = cond_list_str.split(',');
			  for(y=0;y<cond_list.length;y++){
				  if(album_sex_id==cond_list[y]){
					  sa1.push(albums[x]);
				  }
			  }
		  }
	  }	  

	  var albums = sa1;
	  var sa2 = [];
	  if(album_long_id!=""){
		  for(x=0;x<albums.length;x++){
			  var cond_list_str = albums[x].t_hairStyle_searchConditionId + "";
			  var cond_list = cond_list_str.split(',');
			  for(y=0;y<cond_list.length;y++){
				  if(album_long_id==cond_list[y]){
					  sa2.push(albums[x]);
				  }
			  }
		  }
	  }	else{
		  sa2 = sa1;
	  }  

	  var albums = sa2;
	  var sa3 = [];
	  if(album_feel_id!=""){		  
		  for(x=0;x<albums.length;x++){
			  var cond_list_str = albums[x].t_hairStyle_searchConditionId + "";
			  var cond_list = cond_list_str.split(',');
			  for(y=0;y<cond_list.length;y++){
				  if(album_feel_id==cond_list[y]){
					  sa3.push(albums[x]);
				  }
			  }
		  }
	  }	else{
		  sa3 = sa2;
	  }  

	  /*
	  //var albums = component_album_list.state.album_list;
	  var albums = album_info.album;
	  var searched_albums = [];
	  for(x=0;x<albums.length;x++){
		  var cond_list_str = albums[x].t_hairStyle_searchConditionId + "";
		  var cond_list = cond_list_str.split(',');
		  for(y=0;y<cond_list.length;y++){
			  if(album_sex_id==cond_list[y] 
			  && album_long_id==cond_list[y]
			  && album_feel_id==cond_list[y]){
				  searched_albums.push(albums[x]);
			  }
		  }
	  }
	  */
	  searched_albums=sa3;
	  component_album_list.setState({"album_list":searched_albums});
  });
  
});