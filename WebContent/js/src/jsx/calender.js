$(function(){
/*	
  // コンポーネントの定義
  var UserFind = React.createClass({
    getInitialState() {
      return {
        t_user_term: ""
      };
    },
    changeText(e) {
      this.setState({t_user_term: e.target.value});
    },
    render() {
      return (
        <div>
          <input type="text" class="form-control" value={this.state.t_user_term} onChange={this.changeText} placeholeder = "名前 or 電話番号" />
        </div>
      );
    }
  });

  // set state to component
  function componentSetState(term) {
    // コンポーネントにjsonを渡して関係する部分だけ書き換わる
    component_calender_user_find.setState(term);
  }

  // コンポーネントをエレメントに割り当てる
  var component_service_user_find = React.render(<UserFind />, document.getElementById('react_user_find'));
*/

  /*
    Main Part
  */
  //日時取得
  var nowTime = new Date();
  var nowDate = nowTime.getFullYear()+"-";
  if(nowTime.getMonth()+1<10){
	 nowDate+="0";
  }
  nowDate+= nowTime.getMonth()+1+"-";
  if(nowTime.getDate()+1<10){
		 nowDate+="0";
  }
  nowDate+= nowTime.getDate()+" 09:00:00";
  //console.log(nowDate);

  // セッションIDからサービス情報を取得する
  var session_info = getSessionInfo();
  //サロン情報を取得
  var salon_info = getSalonInfo({t_hairSalonMaster_salonId: session_info.t_hairSalonMaster_salonId});
  //var stylistList = getStylistList({t_hairSalonMaster_salonId: session_info.t_hairSalonMaster_salonId});
  var stylistList = getStaffInfo({t_hairSalonMaster_salonId: session_info.t_hairSalonMaster_salonId});
  //var staffList = getStaffInfo({t_hairSalonMaster_salonId: session_info.t_hairSalonMaster_salonId});
  //予約リストreservationList
  var reservationList = {};
  reservations = 
	  getReservationList(
			  {
				  t_reservation_salonId:session_info.t_hairSalonMaster_salonId,
				  t_reservation_date: nowDate
			  });
  reservationList = reservations.reservation_list;
  //console.log("本日の予約数は、 : "+reservationList.length +"件です");
  //sanitaize.decode(service_info);

  // アカウント名を表示
  $('#account-name').text(session_info.t_hairSalonMaster_contactUserName);

  //本日の予約数index1、
  //来店数index2をそれぞれ表示
  var unfinishedList = {};
  var finishedList = {};
  var index1 = 0; var index2 = 0;
	 for(i=0; i<reservationList.length; i++){
		 if(reservationList[i].t_reservation_isFinished==0){
			 if(reservationList[i].t_reservation_menuId.indexOf('R')<0){
				 unfinishedList[index1]=reservationList[i];
				 index1++;
			 }
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
		  //$("#date_val").val(dateText);
		  //console.log("put a "+dateText);
		  var m_oneDay = moment(dateText);
		  var oneDay = m_oneDay.year()+"-";
		  if(m_oneDay.month()+1<10){
			 oneDay+="0";
		  }
		  oneDay+= m_oneDay.month()+1+"-";
		  if(m_oneDay.date()+1<10){
				 oneDay+="0";
		  }
		  oneDay+= m_oneDay.date()+" 09:00:00";
		  //console.log("選択した日付は "+oneDay+" です.");
		  reservations = 
			  getReservationList(
					  {
						  t_reservation_salonId:session_info.t_hairSalonMaster_salonId,
						  t_reservation_date: oneDay
					  });
		  reservationList = reservations.reservation_list;
		  //console.log("予約数は "+reservationList.length+ " 件です.");
		  if(reservationList.length==0){
			  //当日に予約がなかったら.
			console.log("reservation.html?time="+oneDay+"&stylistId=1");
			if(checkAfterDate(oneDay)){
				alert("予約がありません.");
		        //location.href = "reservation.html?time="+oneDay+"&stylistId=1";
				design_gantt(oneDay);
			}else{
				alert("予約がありません.　本日より以前の日にちです.");
				design_gantt(oneDay);
			}
		  }else{
			  design_gantt(oneDay);
		  }
	  }
  });

  //その日が今日よりも前ならfalse
  function checkAfterDate(oneDate){
	  result=true;
	  //console.log(oneDate+">"+nowDate+"?");
	  var mt1=Number(oneDate.substring(5,7));
	  var mt2=Number(nowDate.substring(5,7));
	  var dt1=Number(oneDate.substring(8,10));
	  var dt2=Number(nowDate.substring(8,10));
	  //console.log(mt1+","+mt2+","+dt1+","+dt2);
	  if(mt1==mt2 && dt1 < dt2){ result = false; }
	  if(mt1<mt2){  result = false;  }
	  return result;
  }
  
  /*<!-- データピッカー2 -->
  $('#date').datepicker({
  format: "yyyy/mm/dd",
  language: "ja",
  autoclose: true,
  orientation: "top auto"
  });
  */

  /*<!-- ガントチャート -->*/
  function design_gantt(oneDay) {
    //"use strict";

    /*
    console.log(today.valueOf());
    console.log(today.unix());
    console.log(moment(today.valueOf()).year());
    console.log(moment(today.unix()*1000).year());
    console.log(moment("2015-09-22 00:00:00").year());
    */
    
    var stylists = stylistList.stylist;
    //var staffs = staffList.stylist;
    
    //スタイリストごとの予約情報を確認
    //valueListに予約dataを詰め込む
    var valueList = [];
    var stylistReservationNum = [];
    for(var i=0; i<stylists.length; i++){
		valueList[i] = [];
		stylistReservationNum[i]=0;
    	for(var j=0; j<reservationList.length; j++){
    		if(stylists[i].t_stylist_name == reservationList[j].t_stylist_name){
    			stylistReservationNum[i]++;
    			if(reservationList[j].t_reservation_menuId.indexOf("R1")>=0){
    				//休憩の場合
    				//console.log(stylists[i].t_stylist_name+" rest time is "+reservationList[j].t_reservation_date+"("+reservationList[j].t_reservation_time+").");
	    			var t_from = moment(reservationList[j].t_reservation_date);
	    			//-1しておかないと、2時間分のスペースを埋めてしまう
	    			var oneOpTime=Number((reservationList[j].t_reservation_time)-1)/60;
	    			var t_to = moment(reservationList[j].t_reservation_date).add("hours",oneOpTime);
	        	    valueList[i].push(
	        	    		{
	        					from: "/Date("+t_from.valueOf()+")/",
	        					to: "/Date("+t_to.valueOf()+")/",
	        					label: reservationList[j].t_menu_name,
	        					customClass: "ganttBlue",
	        					dataObj: reservationList[j].t_reservation_id
	        				});
    			}else if(reservationList[j].t_reservation_menuId=="R2"){
    				//休暇の場合
    				var t_rest_day=reservationList[j].t_reservation_date.substring(0,10);
    				var t_rest_start=salon_info.t_hairSalonMaster_openTime;
    				//var t_rest_end=salon_info.t_hairSalonMaster_closeTime;
    			    //閉店時間を１時間減らしておかないとガントチャートがうまく表示できない
    			    var close_time = salon_info.t_hairSalonMaster_closeTime;
    			    var close_time_hour=Number(close_time.substring(0,2))-1;
    			    close_time = close_time_hour+":00";
    			    var salon_close_time = oneDay.substring(0,10)+" "+close_time;
	    			var t_from = moment(t_rest_day+" "+t_rest_start);
	    			var t_to = moment(t_rest_day+" "+close_time);
	        	    valueList[i].push(
	        	    		{
	        					from: "/Date("+t_from.valueOf()+")/",
	        					to: "/Date("+t_to.valueOf()+")/",
	        					label: reservationList[j].t_menu_name,
	        					customClass: "ganttBlue",
	        					dataObj: reservationList[j].t_reservation_id
	        				});
    			}else{
	    			//console.log(stylists[i].t_stylist_name+":"+reservationList[j].t_reservation_date);
	    			var t_from = moment(reservationList[j].t_reservation_date);
	    			var oneOpTime=Number((reservationList[j].t_reservation_time)-1)/60;
	    			//console.log("施術時間:"+oneOpTime);
	    			var t_to = moment(reservationList[j].t_reservation_date).add("hours",oneOpTime);
	    			//console.log(t_from.valueOf()+","+t_to.valueOf());
	    			var barcolor = "ganttOrange";
	    			if(reservationList[j].t_reservation_isFinished>0){barcolor = "ganttRed";}
	        	    valueList[i].push(
	        	    		{
	        					from: "/Date("+t_from.valueOf()+")/",
	        					to: "/Date("+t_to.valueOf()+")/",
	        					label: reservationList[j].t_menu_name,
	        					customClass: barcolor,
	        					dataObj: reservationList[j].t_reservation_id
	        				});
	        	    //ganttBrown
    			}
    		}
    	}
    	if(stylistReservationNum[i]==0){
    		//console.log("stylist["+i+"] ReservationNum = 0");
    	}
    }
    /*
    function minutes2hour(total_time){
    	var ope_hours = Math.floor(total_time/60);
    	var ope_minutes = total_time%60;
    	var end_hours = start_hours+ope_hours;
    	var end_minutes = start_minutes+ope_minutes;
    	//console.log(start_hours+","+start_minutes+","+end_hours+","+end_minutes);
    	if(end_minutes >= 60){
    		end_minutes -= 60;
    		end_hours += 1;
    	}
    }
    */
    
    //sources←スタイリスト情報
    var sources = [];
    var salon_open_time = oneDay.substring(0,10)+" "+salon_info.t_hairSalonMaster_openTime;
    //閉店時間を１時間減らしておかないとガントチャートがうまく表示できない
    var close_time = salon_info.t_hairSalonMaster_closeTime;
    var close_time_hour=Number(close_time.substring(0,2))-1;
    close_time = close_time_hour+":00";
    var salon_close_time = oneDay.substring(0,10)+" "+close_time;
    //console.log(salon_open_time+","+salon_close_time);
    //souces[0]には、営業時間を入れる
    sources.push({
		name:"营业时间",
		desc:"Opentime",
		id:-1,
		values: [{
			from: moment(salon_open_time).valueOf(),
			to: moment(salon_close_time).valueOf(),
			label: "营业时间",
			customClass: "ganttGreen",
			dataObj: "Opentime"
		}]
    });
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
			//console.log(name + "," +stylists[i].t_stylist_Id +stylists[i].t_stylist_name );
			if(name == stylists[i].t_stylist_name){
				id = stylists[i].t_stylist_Id;
			}
		}
		//console.log("stylistId="+id);
		return id;
	};
        
	//ガントチャート描画
	$(".gantt").gantt({
		source: sources,
		scale: "hours",
		navigate: "scroll",
		maxScale: "days",
		itemsPerPage: 10,
	      months : ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"],
	      dow : ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
		onItemClick: function(data) {
			//alert("進捗バーがクリックされました。"+data);
			if(data=="Opentime"){
				//console.log("Opentime.");
			}else{
				console.log("reservation.html?reservationId="+data);
	            location.href = "reservation.html?reservationId="+data;
			}
		},
        onAddClick: function(dt, rowId) {
        	//console.log("rowId ="+ rowId);
        	if(rowId != undefined && rowId>=0){
	           /*
	           * moment関数
	           * http://blog.asial.co.jp/1158
	           */
	          //console.log(Number(dt), moment(Number(dt)).year());
	          //console.log(Number(dt)+","+rowId + ","+sources[rowId].desc);
	          var m = moment(Number(dt));
	          var paramTime = m.year() + "-";
	          var month = Number(m.month()) + 1;
	          paramTime += (month < 10 ) ? '0'+month+"-" : month+"-";
	          paramTime += (m.date() < 10 ) ? '0'+m.date()+" " : m.date()+" ";
	          paramTime += (m.hours() < 10 ) ? '0'+m.hours()+":" : m.hours()+":";
	          paramTime += (m.minutes() < 10 ) ? '0'+m.minutes()+":" : m.minutes()+":";
	          paramTime += (m.seconds() < 10 ) ? '0'+m.seconds() : m.seconds();
	          var stylistId = stylistName2Id(sources[rowId+1].desc);
	          //console.log(sources[rowId+1].desc);
	          //alert("reservation.html?time="+paramTime+"&stylistId="+stylistId);
	          //console.log("reservation.html?time="+paramTime+"&stylistId="+stylistId);
	          location.href = "reservation.html?time="+paramTime+"&stylistId="+stylistId;
        	}else{
        		//console.log("空白部分がクリックされました。"+dt+","+rowId);
        	}
      },
      onRender: function() {
          if (window.console && typeof console.log === "function") {
              //console.log("chart rendered");
          }
      }
    });
    //prettyPrint();
  }
  design_gantt(nowDate);
	  
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

  // 顧客検索ボタン押下時
  $('#search_button').on('click', function() {
	  var target = $('#form_user_search').val();
	  console.log("customerlist.html?target="+target);
      location.href = "customerlist.html?target="+target;
  });
  
  // 本日の予約一覧ボタン押下時
  $('#reservation_list_button').on('click', function() {
		console.log("reservationlist.html?date="+nowDate);
        location.href = "reservationlist.html?date="+nowDate;
  });

  // 顧客一覧ボタン押下時
  $('#customer_list_button').on('click', function() {
		console.log("customerlist.html");
        location.href = "customerlist.html";
  });

});
