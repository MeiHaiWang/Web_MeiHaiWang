$(function(){
  /*
    Component for React
  */
  // コンポーネントの定義
  var SalonName = React.createClass({
    getInitialState() {
      return {
        t_hairSalonMaster_salon_name: ""
      };
    },
    changeText(e) {
      this.setState({t_hairSalonMaster_salon_name: e.target.value});
    },
    render() {
      return (
        <div>
          <input type="text" value={this.state.t_hairSalonMaster_salon_name} onChange={this.changeText} />
        </div>
      );
    }
  });

  var SalonCountry = React.createClass({
    getDefaultProps() {
      return {
        area: ['']
      };
    },
    getInitialState() {
      return {
        t_area_name: '',
      };
    },
    onChangeSelectValue(e) {
      // countryが変化したらAreaもCountryに対応したリストに変化させる
      var areas_slave = getSlaveAreasByAreaName(country_area_info.area, e.target.value);
      component_salon_area.setProps({area: areas_slave});
      component_salon_area.setState({t_area_id: getAreaIdByAreaName(e.target.value, areas_slave)});
      this.setState({t_area_name: e.target.value});
    },
    render() {
      var options = this.props.area.map(function(area) {
        return <option value={area.area_name}>{area.area_name}</option>;
      });
      return (
        <div>
          <select value={this.state.t_area_name} onChange={this.onChangeSelectValue}>
            {options}
          </select>
        </div>
      );
    }
  });

  var SalonArea = React.createClass({
    getDefaultProps() {
      return {
        area: ['']
      };
    },
    getInitialState() {
      return {
        t_area_id: '',
      };
    },
    onChangeSelectValue(e) {
      this.setState({t_area_id: e.target.value});
    },
    render() {
      //debug
      //console.log("taid:"+this.state.t_area_id);
      var options = this.props.area.map(function(area) {
        return <option value={area.t_area_id}>{area.t_area_name}</option>;
      });
      return (
        <div>
          <select value={this.state.t_area_id} onChange={this.onChangeSelectValue}>
            {options}
          </select>
        </div>
      );
    }
  });

  var SalonDetailText = React.createClass({
    getInitialState() {
      return {
        t_hairSalonMaster_detailText: ""
      };
    },
    onChangeText(e) {
      this.setState({t_hairSalonMaster_detailText: e.target.value});
    },
    onClick() {
      this.setState({t_hairSalonMaster_detailText: this.refs.textArea.getDOMNode().value});
    },
    render() {
      return (
        <div>
          <div>
            <textarea value={this.state.t_hairSalonMaster_detailText} onChange={this.onChangeText} />
          </div>
        </div>
      );
    }
  });

  var SalonOpenTime = React.createClass({
    getDefaultProps() {
      return {
        open_time: ['09:00','10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00', '21:00', '22:00']
      };
    },
    getInitialState() {
      return {
        t_hairSalonMaster_openTime: this.props.open_time[0],
      };
    },
    onChangeSelectValue(e) {
      this.setState({t_hairSalonMaster_openTime: e.target.value});
    },
    render() {
      var options = this.props.open_time.map(function(open_time) {
        return <option value={open_time}>{open_time}</option>;
      });
      return (
        <div>
          <select value={this.state.t_hairSalonMaster_openTime} onChange={this.onChangeSelectValue}>
            {options}
          </select>
        </div>
      );
    }
  });

  var SalonCloseTime = React.createClass({
    getDefaultProps() {
      return {
        close_time: ['10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00', '21:00', '22:00', '23:00']
      };
    },
    getInitialState() {
      return {
        t_hairSalonMaster_closeTime: this.props.close_time[0],
      };
    },
    onChangeSelectValue(e) {
      this.setState({t_hairSalonMaster_closeTime: e.target.value});
    },
    render() {
      var options = this.props.close_time.map(function(close_time) {
        return <option value={close_time}>{close_time}</option>;
      });
      return (
        <div>
          <select value={this.state.t_hairSalonMaster_closeTime} onChange={this.onChangeSelectValue}>
            {options}
          </select>
        </div>
      );
    }
  });

  var SalonCloseDay = React.createClass({
    getDefaultProps() {
      return {
        close_day: ['星期一', '星期二', '星期三', '星期四', '星期五', '星期六', '星期日', '终年无休']
      };
    },
    getInitialState() {
      return {
        t_hairSalonMaster_closeDay: '星期一',
      };
    },
    onChangeSelectValue(e) {
      this.setState({t_hairSalonMaster_closeDay: e.target.value});
    },
    render() {
      var options = this.props.close_day.map(function(close_day) {
        return <option value={close_day}>{close_day}</option>;
      });
      return (
        <div>
          <select value={this.state.t_hairSalonMaster_closeDay} onChange={this.onChangeSelectValue}>
            {options}
          </select>
        </div>
      );
    }
  });

  var SalonCreditAvailable = React.createClass({
    getDefaultProps() {
      return {
        credit: [{value: 0, text:'信用卡不可用'}, {value: 1, text:'信用卡可用'}]
      };
    },
    getInitialState() {
      return {
        t_hairSalonMaster_creditAvailable: 0,
      };
    },
    onChangeSelectValue(e) {
      this.setState({t_hairSalonMaster_creditAvailable: e.target.value});
    },
    render() {
      var options = this.props.credit.map(function(credit) {
        return <option value={credit.value}>{credit.text}</option>;
      });
      return (
        <div>
          <select value={this.state.t_hairSalonMaster_creditAvailable} onChange={this.onChangeSelectValue}>
            {options}
          </select>
        </div>
      );
    }
  });

  var SalonCarParkAvailable = React.createClass({
    getDefaultProps() {
      return {
        car_park: [{value: 0, text:'无'}, {value: 1, text:'有'}]
      };
    },
    getInitialState() {
      return {
        t_hairSalonMaster_carParkAvailable: 0,
      };
    },
    onChangeSelectValue(e) {
      this.setState({t_hairSalonMaster_carParkAvailable: e.target.value});
    },
    render() {
      var options = this.props.car_park.map(function(car_park) {
        return <option value={car_park.value}>{car_park.text}</option>;
      });
      return (
        <div>
          <select value={this.state.t_hairSalonMaster_carParkAvailable} onChange={this.onChangeSelectValue}>
            {options}
          </select>
        </div>
      );
    }
  });
/*
  var SalonJapaneseAvailable = React.createClass({
    getDefaultProps() {
      return {
        japanese: [{value: 0, text:'无日语接待'}, {value: 1, text:'日语接待'}]
      };
    },
    getInitialState() {
      return {
        t_hairSalonMaster_japaneseAvailable: 0,
      };
    },
    onChangeSelectValue(e) {
      this.setState({t_hairSalonMaster_japaneseAvailable: e.target.value});
    },
    render() {
      var options = this.props.japanese.map(function(japanese) {
        return <option value={japanese.value}>{japanese.text}</option>;
      });
      return (
        <div>
          <select value={this.state.t_hairSalonMaster_japaneseAvailable} onChange={this.onChangeSelectValue}>
            {options}
          </select>
        </div>
      );
    }
  });
  */

  var SalonImagePath = React.createClass({
    getInitialState() {
      return {
        t_hairSalonMaster_salonImagePath: ["img/notfound.jpg", "img/notfound.jpg", "img/notfound.jpg", "img/notfound.jpg"]
      };
    },
    render() {
      var images = this.state.t_hairSalonMaster_salonImagePath.map(function(images) {
        return <div className="salon_image_path_div"><img className="salon_image_img" src={images} /></div>;
      });
      return (
        <div>
          {images}
        </div>
      );
    }
  });


  /*
   * Salon Service Tag
   * サロン検索条件のタイトルidの一つ目
   */
  var SalonServiceTag =
	  React.createClass({displayName: "SalonServiceTag",
	    getDefaultProps:function() {
	      return {
	        service_tag: ['']
	      };
	    },
	    getInitialState:function() {
	      return {
	    	  t_hairSalonMaster_searchConditionId: '',
	      };
	    },
	    onChangeSelectValue:function(e) {
		    　//選択された項目のidを取得
		    　var addId = nameToId(e.target.value); 
		      /**
		       * 
		       */
			  //serchConditionIdの最新版を取得
			  var elms = document.getElementsByName("tags");
			  var text = "";
			  for(p=0;p<elms.length;p++){
				  if(nameToId(elms[p].value)!=-1){
					  if(text.length==0){
						  text = nameToId(elms[p].value);
					  }else{
						  text += "," + nameToId(elms[p].value);
					  }
				  }
			  }
			  condId = text;
		      /**
		       * 
		       */
		      //すでに登録されたidでなければ、condIdに追加
		      condId += "";
		      if(!hasAddTag(condId, addId)){
		    	  if(condId.length<=0){
		    		  condId= addId;	    		  
		    	  }else{
		    		  condId+=","+addId;
		    	  }
		      }
		     		      
		      //サロン検索条件のリストをいったん削除
		      $("#salon_cond_list1").tagit("removeAll");
		      //再リスト化
		      var _v2 = v2(condId, 0);
		      //console.log(_v2.length+": _v2[0]:"+_v2[0].name);
		      for(i=0;i<_v2.length;i++){
			    //console.log("_v2_1:"+_v2[i].name);
	    	    $("#salon_cond_list1").tagit("createTag", _v2[i]);
	    	  }
		      console.log("title1_condId:"+condId);
		      this.setState({t_hairSalonMaster_searchConditionId: condId});
		      //component_cond_list.setProps({cond_list: _v2});
	    },
	    render:function() {
	      var options = this.props.service_tag.map(
	        function(service_tag) {
	        return React.createElement("option",
	        {value: service_tag}, service_tag);
	      });
	      return (
	       /*
	        React.createElement("div", null,
	          React.createElement("select", {value: this.state.t_hairSalonMaster_searchConditionId, onChange: this.onChangeSelectValue},
	            options
	          )
	        )
	        */
	        <div>
	          <select value={this.state.t_hairSalonMaster_searchConditionId} onChange={this.onChangeSelectValue}>
	            {options}
	          </select>
	        </div>
	      );
	    }
	  });

  var SalonServiceTag2 =
	  React.createClass({displayName: "SalonServiceTag2",
	    getDefaultProps:function() {
	      return {
	        service_tag: ['']
	      };
	    },
	    getInitialState:function() {
	      return {
	    	  t_hairSalonMaster_searchConditionId: '',
	      };
	    },
	    onChangeSelectValue:function(e) {
		      //選択された項目のidを取得
		      var addId = nameToId(e.target.value);
		      /**
		       * 
		       */
			  //serchConditionIdの最新版を取得
			  var elms2 = document.getElementsByName("tags2");
			  var text = "";
			  for(p=0;p<elms2.length;p++){
				  if(nameToId(elms2[p].value)!=-1){
					  if(text.length==0){
						  text = nameToId(elms2[p].value);
					  }else{
						  text += "," + nameToId(elms2[p].value);
					  }
				  }
			  }
			  condId = text;
		      /**
		       * 
		       */
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
		      $("#salon_cond_list2").tagit("removeAll");
		      var _v2_2 = v2(condId, 1);
		      for(i=0;i<_v2_2.length;i++){
	    	    $("#salon_cond_list2").tagit("createTag", _v2_2[i]);
	    	  }
	    	  //console.log("title2_condId:"+condId);
		      this.setState({t_hairSalonMaster_searchConditionId: condId});
		      //component_cond_list.setProps({cond_list: _v2});
	    },
	    render:function() {
	      var options = this.props.service_tag.map(
	        function(service_tag) {
	        return React.createElement("option",
	        {value: service_tag}, service_tag);
	      });
	      return (
  	        <div>
	          <select value={this.state.t_hairSalonMaster_searchConditionId} onChange={this.onChangeSelectValue}>
	            {options}
	          </select>
	        </div>
	      );
	    }
	  });

  /*
   * 
   */
  /*
  var ConditionList =
	   React.createClass({displayName: "ConditionList",
	   getDefaultProps:function() {
	     return {
	       cond_list: [{
	         "id": "",
	         "name": ""
	       }
	     ]};
	   },
	    getInitialState: function() {
	      return {
	        t_hairSalonMaster_serviceConditionId: ''
	      };
	    },
	    render: function() {
	      var index = 1;
	      var cond = this.props.cond_list.map(
	        function(cond) {
	          return(
	          <tr>
	            <td>
	              {index++}
	            </td>
	            <td>
	              {cond.name}
	            </td>
	            <td>
	            	<div id="d"> × </div>
	            </td>
	          </tr>
	        );
	      });
	      return (
	        <table>
	          <tr>
	            <th>
	              <div> id </div>
	            </th>
	            <th>
	              <div> name </div>
	            </th>
	          </tr>
	          {cond}
	        </table>
	      );
	    }
	  });
   */
  //
  
  //mail&pass change.
  var SalonMail = React.createClass({
	    getInitialState() {
	      return {
	        t_hairSalonMaster_mail: ""
	      };
	    },
	    changeText(e) {
	      this.setState({t_hairSalonMaster_mail: e.target.value});
	    },
	    render() {
	      return (
	        <div>
	          <input type="email" placeholder="【例】youjiandizi-888@meihaiwang.com" class="input-xxlarge"
	        	  value={this.state.t_hairSalonMaster_mail} onChange={this.changeText} />
	        </div>
	      );
	    }
	  });
  //mail&pass change.
  var SalonPass = React.createClass({
	    getInitialState() {
	      return {
	        t_hairSalonMaster_pass: ""
	      };
	    },
	    changeText(e) {
	      this.setState({t_hairSalonMaster_pass: e.target.value});
	    },
	    render() {
	      return (
	        <div>
	        	<input type="password" placeholder="请填写6～20字的半角英文或数字" class="input-xxlarge"
	        		value={this.state.t_hairSalonMaster_pass} onChange={this.changeText} />
	        </div>
	      );
	    }
	  });
  //mail&pass change.
  var SalonPassConfirm = React.createClass({
	    getInitialState() {
	      return {
	        t_hairSalonMaster_confirm: ""
	      };
	    },
	    changeText(e) {
	      this.setState({t_hairSalonMaster_confirm: e.target.value});
	    },
	    render() {
	      return (
	        <div>
	            <input type="password" id="confirm" placeholder="请在输入一次密码" class="input-xxlarge" 
	            	value={this.state.t_hairSalonMaster_confirm} onChange={this.changeText} />
	        </div>
	      );
	    }
	  });

  
  
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
  
  /*
    Component Render
  */
  // コンポーネントをエレメントに割り当てる
  var component_salon_name = React.render(<SalonName />, document.getElementById('salon_salon_name'));
  var component_salon_country = React.render(<SalonCountry />, document.getElementById('salon_country'));
  var component_salon_area = React.render(<SalonArea />, document.getElementById('salon_area'));
  var component_salon_detail_text = React.render(<SalonDetailText />, document.getElementById('salon_detail_text'));
  var component_salon_open_time = React.render(<SalonOpenTime />, document.getElementById('salon_open_time'));
  var component_salon_close_time = React.render(<SalonCloseTime />, document.getElementById('salon_close_time'));
  var component_salon_close_day = React.render(<SalonCloseDay />, document.getElementById('salon_close_day'));
  var component_salon_credit_available = React.render(<SalonCreditAvailable />, document.getElementById('salon_credit_available'));
  var component_salon_car_park_available = React.render(<SalonCarParkAvailable />, document.getElementById('salon_car_park_available'));
  //var component_salon_japanese_available = React.render(<SalonJapaneseAvailable />, document.getElementById('salon_japanese_available'));
  var component_salon_image_path = React.render(<SalonImagePath />, document.getElementById('salon_image_path'));
  var component_salon_service_tag = React.render(<SalonServiceTag />, document.getElementById('salon_cond_tag1'));
  var component_salon_service_tag2 = React.render(<SalonServiceTag2 />, document.getElementById('salon_cond_tag2'));
  //var component_salon_cond_list = React.render(<ConditionList />, document.getElementById('salon_cond_list1'));
  var component_salon_mail = React.render(<SalonMail />, document.getElementById('salon_mail'));
  var component_salon_pass = React.render(<SalonPass />, document.getElementById('salon_pass'));
  var component_salon_pass_confirm = React.render(<SalonPassConfirm />, document.getElementById('salon_pass_confirm'));
  
  /*
    Main Part
  */
  // セッションIDからサロン情報を取得する
  var session_info = getSessionInfo();
  var salon_info = getSalonInfo(session_info.t_hairSalonMaster_salonId);
  var country_area_info = getCountryAreaList();
  sanitaize.decode(salon_info);
  sanitaize.decode(country_area_info);
  var search_condition_type = {
		  t_masterSearchConditionType: "サロン検索条件"
  }
  sanitaize.encode(search_condition_type);
  var search_condition = getSearchConditionList(search_condition_type);
  sanitaize.decode(search_condition);
  var titles = search_condition.titles;
  var values = search_condition.values;
  //console.log("titles.length:"+titles.length+","+"values.length:"+values.length);

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
      //console.log(i+":"+t_array[i].name+"<br>");
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
    }
  }());

  /*
   * 条件名をタイトル名から取得
   */
  $("#salon_cond_tag_name1").text(t_array[0].name);
  $("#salon_cond_tag_name2").text(t_array[1].name);

  
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
  var area_name = getAreaNameBySlaveAreaId(country_area_info.area, salon_info.t_hairSalonMaster_areaId);
  //var area_name = getAreaNameBySlaveAreaName(country_area_info.area, salon_info.t_area_name);
  //console.log(area_name + ","+ salon_info.t_area_name);
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
  component_salon_area.setProps({area: areas_slave});
  //debug
  //console.log(salon_info.t_hairSalonMaster_areaId+":"+getAreaIdByAreaName(salon_info.t_area_name, areas_slave));
  component_salon_area.setState({t_area_id:salon_info.t_hairSalonMaster_areaId});
  //component_salon_area.setState({t_area_id:getAreaIdByAreaName(salon_info.t_area_name, areas_slave)});
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
  /*
  if (salon_info.t_hairSalonMaster_japaneseAvailable != '') {
    component_salon_japanese_available.setState(salon_info);
  }
  */
  component_salon_image_path.setState(salon_info);
  component_salon_mail.setState(salon_info);

  /*
  if (salon_info.t_hairSalonMaster_searchConditionId != '') {
  }
  */

  // salon_infoが持つ検索条件のidリスト
  var condId = salon_info.t_hairSalonMaster_searchConditionId;
  //console.log("searchConditionId:"+salon_info.t_hairSalonMaster_searchConditionId);

  //タイトルidのリスト
  //var t_id=0;
  
  /*
   *  サロンの検索条件の一覧を使って、
   *  タイトルごとに条件一覧をselectできるようsetProps
   */
  var v1 = function(title_id){
    var v1 = [];
    v1.push(" ");
    for(i=0;i<v_array[title_id].length;i++){
      v1.push(v_array[title_id][i].name);
      //console.log("v1.push:"+v_array[title_id][i].name);
    }
    return v1;
  };

  var _v1 = v1(0) //titleId=0, time
  component_salon_service_tag.setProps({service_tag:_v1});
  var _v1_2 = v1(1) //titleId=1, service
  component_salon_service_tag2.setProps({service_tag:_v1_2});

  /*
   * サロンの検索条件一覧からタイトルごとに、
   * searchConditionIdで選択されていた条件リストを、
   * タグとして表示しておくためのリスト
   */
  var v2 = function(t_hairSalonMaster_searchConditionId, t_id){
    //var v2 = new Array();
	var v2 = [];
    var index=0;
    t_hairSalonMaster_searchConditionId = t_hairSalonMaster_searchConditionId+"";
    var idlist = t_hairSalonMaster_searchConditionId.split(',');
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
  
  $('#salon_cond_list1').tagit({placeholderText:"",fieldName:"tags"});
  var _v2 = v2(condId, 0);
  //component_salon_cond_list.setProps({cond_list: _v2});
  // $('#salon_cond_list1').tagit({placeholderText:"タグをつけよう",fieldName:"tags[]"});
  for(i=0;i<_v2.length;i++){
    $("#salon_cond_list1").tagit("createTag", _v2[i]);
  }
  // $("#salon_cond_list1").tagit({placeholderText:"タグをつけよう",fieldName:"tags"});

  $('#salon_cond_list2').tagit({placeholderText:"",fieldName:"tags2"});
  var _v2_2 = v2(condId, 1);
  //component_salon_cond_list.setProps({cond_list: _v2});
  // $('#salon_cond_list1').tagit({placeholderText:"タグをつけよう",fieldName:"tags[]"});
  for(i=0;i<_v2_2.length;i++){
    $("#salon_cond_list2").tagit("createTag", _v2_2[i]);
  }
  

  /*
    Button Handler
  */
  // 登録ボタン押下時
  $('#salon_regist_button').on('click', function() {
	  //serchConditionIdの最新版を取得
	  var searchConditionIds="";
	  /*
	  if(component_salon_service_tag.state.t_hairSalonMaster_searchConditionId.length
			  >component_salon_service_tag2.state.t_hairSalonMaster_searchConditionId.length){
		  searchConditionIds = component_salon_service_tag.state.t_hairSalonMaster_searchConditionId;
	  }else{
		  searchConditionIds = component_salon_service_tag2.state.t_hairSalonMaster_searchConditionId;
	  }
	  */
	  var elms = document.getElementsByName("tags");
	  var elms2 = document.getElementsByName("tags2");
	  //console.log("elms.length,elms2.length:"+elms.length+","+elms2.length);
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
	  searchConditionIds = text;
	  //console.log("searchConditionIds:"+searchConditionIds);
	  
	  /*
	  console.log(component_salon_service_tag.state.t_hairSalonMaster_searchConditionId + " > "
			  +component_salon_service_tag2.state.t_hairSalonMaster_searchConditionId +" ? ")
	  console.log("result="+searchConditionIds);
	  */

	  //mail&pass change.
	    // エラーメッセージをクリアする
	  //salon_info.confirm.setCustomValidity("");
	    // パスワードの一致確認
	  if(component_salon_pass.state.t_hairSalonMaster_pass!=""){
	    if (component_salon_pass.state.t_hairSalonMaster_pass != component_salon_pass_confirm.state.t_hairSalonMaster_confirm) {
	      // 一致していなかったら、エラーメッセージを表示する
	      //confirm.setCustomValidity("两次输入的密码不一致");
	    	alert("密码:两次输入的密码不一致");
	      return;
	    }   
	    if(component_salon_pass.state.t_hairSalonMaster_pass.length < 6　 || component_salon_pass.state.t_hairSalonMaster_pass.length > 20){
	    	//confirm.setCustomValidity("密码请填写6～20字");
	    	alert("密码:密码请填写6～20字");
	    	 return;
	    }
  	}
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
      //t_hairSalonMaster_japaneseAvailable: component_salon_japanese_available.state.t_hairSalonMaster_japaneseAvailable,
      t_hairSalonMaster_japaneseAvailable : "0", //使用しない。検索条件で設定.
      t_hairSalonMaster_searchConditionId: searchConditionIds,
      t_hairSalonMaster_mail:              component_salon_mail.state.t_hairSalonMaster_mail,
      t_hairSalonMaster_pass:              component_salon_pass.state.t_hairSalonMaster_pass
    }

    /*
    //debug
    alert("regist salonData:" +
    		"salonId:"+data.t_hairSalonMaster_salonId
    		+"/n name:"+data.t_hairSalonMaster_name
    		+"/n credit:"+data.t_hairSalonMaster_creditAvailable
    		+"/n carPark:"+data.t_hairSalonMaster_carParkAvailable
    		+"/n japanese:"+data.t_hairSalonMaster_japaneseAvailable
    		+"/n searchId:"+data.t_hairSalonMaster_searchConditionId
    );
    */
    
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

  /*
  // 画像アップロード（非同期用）
  function work_async(data, callback) {
	    setTimeout(function() {
	        // 時間がかかる処理をする
	    	var param = uploadImage(data);
	        console.log("result"+param.result);
	        callback(param.result);
	    }, 0);
	}
  var callback = function(result) {// 処理結果を引数としてうけとる
      if (result == "true") {
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
  */
  
  // 画像アップロード
  $('#salon_image').change(function() {
    // ファイルが選択されたか
    if($(this).prop('files')[0]){
      var data = new FormData($('#update')[0]);
      //var result = uploadImage(data);
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
        	  console.log("salon-image:"+response);
              response = JSON.parse(response);
              if (response.result == "true") {
                  var salon_image_path = new Array(response.image_path);
                  var current_image_path = component_salon_image_path.state.t_hairSalonMaster_salonImagePath;

                  // 常に画像は4つまでしか管理しない
                  salon_image_path = salon_image_path.concat(current_image_path).slice(0, 4);
                  component_salon_image_path.setState({t_hairSalonMaster_salonImagePath: salon_image_path});
                }
                else {
                  alert('Upload Failed');
                }
          });
      })(data);

      /*
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
      */
    }
  });
  
  // 画像削除ボタン
  $('.salon_image_trash_button').on('click', function() {
    var id = $(".salon_image_trash_button").index(this);
    var current_image_path = component_salon_image_path.state.t_hairSalonMaster_salonImagePath;
    var tmp_image_path = "";
    current_image_path[id] = 'img/notfound.jpg';
    for(i=id; i<3; i++){
        tmp_image_path = current_image_path[i];
        current_image_path[i]=current_image_path[i+1];
        current_image_path[i+1]=tmp_image_path;
    }
    component_salon_image_path.setState({t_hairSalonMaster_salonImagePath: current_image_path});
  });

  /*
  //mail&password confirm
   function regist(event) {
		if(mail.value=="" || pass.value==""){
 			return;
 		}
	    // エラーメッセージをクリアする
	    registForm.pass.setCustomValidity("");
	    // パスワードの一致確認
	    if (registForm.pass.value != registForm.passConfirm.value) {
	      // 一致していなかったら、エラーメッセージを表示する
	      registForm.pass.setCustomValidity("两次输入的密码不一致");
	      return;
	    }   
	    if(registForm.pass.value.length < 6　 || registForm.pass.value.length > 20){
	    	 registForm.pass.setCustomValidity("密码请填写6～20字");
	    	 return;
	    }
		var response =　$.ajax({
				url: API_PATH + "changeSalon",
				type: "POST",
				async: false,
				dataType: "text",
				data:{
					id : salonId,
					mail : mail.value,
					pass : pass.value,
				},
			    success: function(data, status) {
			    	location.href = "#top";
			    }
		}).responseText;
   }
*/

});
