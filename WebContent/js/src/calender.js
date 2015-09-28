$(function(){
  /*
    Component for React
  */
	
  // コンポーネントの定義
  var UserFind = React.createClass({displayName: "UserFind",
    getInitialState:function() {
      return {
        t_user_term: ""
      };
    },
    changeText:function(e) {
      this.setState({t_user_term: e.target.value});
    },
    render:function() {
      return (
        React.createElement("div", null, 
          React.createElement("input", {type: "text", value: this.state.t_user_term, onChange: this.changeText, placeholeder: "名前 or 電話番号"})
        )
      );
    }
  });

  // set state to component
  function componentSetState(term) {
    // コンポーネントにjsonを渡して関係する部分だけ書き換わる
    component_calender_user_find.setState(term);
  }

  /*
    Component Render
  */
  // コンポーネントをエレメントに割り当てる
  var component_service_user_find = React.render(React.createElement(UserFind, null), document.getElementById('react_user_find'));


  /*
    Main Part
  */
  //日時取得
  var nowTime = new Date();
  var str = nowTime.getFullYear()+"-";
  if(nowTime.getMonth()+1<10){
	 str+="0";
  }
  str+= nowTime.getMonth()+1+"-";
  str+= nowTime.getDate()+" 00:00:00";
  console.log(str);

  // セッションIDからサービス情報を取得する
  var session_info = getSessionInfo();
  var stylistList = getStylistList({t_hairSalonMaster_salonId: session_info.t_hairSalonMaster_salonId});
  //var staffList = getStaffInfo({t_hairSalonMaster_salonId: session_info.t_hairSalonMaster_salonId});
  //予約リストreservationList
  var reservationList = {};
  reservations = 
	  getReservationList(
			  {
				  t_reservation_salonId:session_info.t_hairSalonMaster_salonId,
				  t_reservation_date: str
			  });
  reservationList = reservations.reservation_list;
  console.log("reservationNumber : "+reservationList.length);
  //sanitaize.decode(service_info);

  // アカウント名を表示
  $('#account-name').text(session_info.t_hairSalonMaster_contactUserName);

  //予約数index1、来店数index2
  var unfinishedList = {};
  var finishedList = {};
  var index1 = 0; var index2 = 0;
	 for(i=0; i<reservationList.length; i++){
		 if(reservationList[i].t_reservation_isFinished==0){
			 unfinishedList[index1]=reservationList[i];
			 index1++;
		 }else if(reservationList[i].t_reservation_isFinished>0){
			 finishedList[index2]=reservationList[i];
			 index2++;
		 }else{
		 }
	 }
	  $('#reserve_number').text(index1);
	  $('#customer_number').text(index2);

  /*<!-- データピッカー1 -->*/
  $("#datepicker").datepicker({
  // 日付が選択された時、日付をテキストフィールドへセット
  onSelect: function(dateText, inst) {
  $("#date_val").val(dateText);
  }
  });

  /*<!-- データピッカー2 -->*/
  $('#date').datepicker({
  format: "yyyy/mm/dd",
  language: "ja",
  autoclose: true,
  orientation: "top auto"
  });

  /*<!-- ガントチャート -->*/
  $(function() {
    "use strict";

    /*
    console.log(today.valueOf());
    console.log(today.unix());
    console.log(moment(today.valueOf()).year());
    console.log(moment(today.unix()*1000).year());
    console.log(moment("2015-09-22 00:00:00").year());
    */
    
    var stylists = stylistList.stylist;
    //var staffs = staffList.stylist;
    var valueList = [];
    var stylistReservationNum = [];
    
    for(var i=0; i<stylists.length; i++){
		valueList[i] = [];
		stylistReservationNum[i]=0;
    	for(var j=0; j<reservationList.length; j++){
    		if(stylists[i].t_stylist_name == reservationList[j].t_stylist_name){
    			stylistReservationNum[i]++;
    			//console.log(stylists[i].t_stylist_name+":"+reservationList[j].t_reservation_date);
    			var t_from = moment(reservationList[j].t_reservation_date);
    			var t_to = moment(reservationList[j].t_reservation_date).add("hours",2);
    			console.log(t_from.valueOf()+","+t_to.valueOf());
        	    valueList[i].push(
        	    		{
        					from: "/Date("+t_from.valueOf()+")/",
        					to: "/Date("+t_to.valueOf()+")/",
        					label: reservationList[j].t_menu_name,
        					customClass: "ganttRed",
        					dataObj: reservationList[j].t_reservation_id
        				});
    		}
    	}
    	if(stylistReservationNum[i]==0){
    		//console.log("stylist["+i+"] ReservationNum = 0");
    		/*
    		valueList[i].push(
    	    		{
    	    			from: moment("2015-09-22 11:00:00").valueOf(),
    					to: moment("2015-09-22 16:00:00").valueOf(),
    					label: "menu1",
    					customClass: "ganttRed",
    					dataObj: "data1"
    	    		}
    		);
    		*/
    	}
    }
    //sources←スタイリスト情報
    var sources = [];
    for(var i=0; i<stylists.length; i++){
    	sources.push( 
    	{
    		name:"staff"+i,
    		id: i,
    		desc: stylists[i].t_stylist_name,
    		values: valueList[i]
    		/*
    		values: [{
    			from: moment("2015-09-22 11:00:00").valueOf(),
				to: moment("2015-09-22 16:00:00").valueOf(),
				label: "menu1",
				customClass: "ganttRed",
				dataObj: "data1"
    		}]
    		*/
    	});
    }
        
    //スタイリスト名→スタイリストid
	var stylistName2Id = function(name){
		var id = -1;
		for(var i=0; i<stylists.length; i++){
			//console.log(name + "," + stylists[i].t_stylist_name );
			if(name == stylists[i].t_stylist_name){
				id = stylists[i].t_stylist_Id;
			}
		}
		return id;
	};

	//ガントチャート描画
	$(".gantt").gantt({
		source: sources,
		scale: "hours",
		navigate: "scroll",
		maxScale: "hours",
		itemsPerPage: 10,
	      months : ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"],
	      dow : ["日", "月", "火", "水", "木", "金", "土"],
		onItemClick: function(data) {
			//alert("進捗バーがクリックされました。"+data);
			console.log("reservation.html?reservationId="+data);
            location.href = "reservation.html?reservationId="+data;
		},
        onAddClick: function(dt, rowId) {
        	if(rowId != undefined ){
	           /*
	           * moment関数
	           * http://blog.asial.co.jp/1158
	           */
	          //console.log(Number(dt), moment(Number(dt)).year());
	          //console.log(Number(dt)+","+rowId + ","+sources[rowId].desc);
	          var m = moment(Number(dt));
	          var paramTime = m.year() + "/";
	          var month = Number(m.month()) + 1;
	          paramTime += (month < 10 ) ? '0'+month+"/" : month+"/";
	          paramTime += (m.date() < 10 ) ? '0'+m.date()+" " : m.date()+" ";
	          paramTime += (m.hours() < 10 ) ? '0'+m.hours()+":" : m.hours()+":";
	          paramTime += (m.minutes() < 10 ) ? '0'+m.minutes()+":" : m.minutes()+":";
	          paramTime += (m.seconds() < 10 ) ? '0'+m.seconds() : m.seconds();
	          var stylistId = stylistName2Id(sources[rowId].desc);
	          alert("reservation.html?time="+paramTime+"&stylistId="+stylistId);
	          //console.log("reservation.html?time="+paramTime+"&stylistId="+stylistId);
	          location.href = "reservation.html?time="+paramTime+"&stylistId="+stylistId;
        	}else{
        		//alert("空白部分がクリックされました。"+dt+","+rowId);
        	}
      },
      onRender: function() {
          if (window.console && typeof console.log === "function") {
              console.log("chart rendered");
          }
      }
    });
    //prettyPrint();
  });

  
	  /*
	  Button Handler
	*/
	// 登録ボタン押下時
	/*
	$('#service_regist_button').on('click', function() {
	  var data = {
	    t_hairSalonMaster_salonId: session_info.t_hairSalonMaster_salonId,
	    t_menu_menuId:             component_service_category.state.t_menu_menuId,
	    t_menu_categoryId:         component_service_category.state.t_menu_categoryId,
	    t_menu_name:               component_service_name.state.t_menu_name,
	    t_menu_price:              component_service_price.state.t_menu_price,
	    t_menu_detailText:         component_service_detail_text.state.t_menu_detailText,
	    t_menu_imagePath:          component_service_image_path.state.t_menu_imagePath,
	  }
	
	  // サニタイズ
	  sanitaize.encode(data);
	
	  var result = setMenuInfo(data);
	  if (result.result == "true") {
	    alert('Regist Success');
	    window.location.reload();
	  }
	  else {
	    alert('Regist Failed');
	  }
	});
	*/
  // 本日の予約一覧ボタン押下時
  $('#reservation_list_button').on('click', function() {
		console.log("reservationlist.html?date="+str);
        location.href = "reservationlist.html?date="+str;
  });

});
