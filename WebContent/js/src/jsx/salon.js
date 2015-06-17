$(function(){

  // セッションIDからサロン情報を取得する
  var session_info = getSessionInfo();
  var salon_info = getSalonInfo(session_info.t_hairSalonMaster_salonId);


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

  // コンポーネントをエレメントに割り当てる
  var component_salon_name = React.render(<SalonName />, document.getElementById('salon_salon_name'));

  // コンポーネントにjsonを渡して関係する部分だけ書き換わる
  component_salon_name.setState(salon_info);

});