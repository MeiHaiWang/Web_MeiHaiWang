// コンポーネントの定義
var Test = React.createClass({

  // 初期化
  getDefaultProps: function() {
    return {
      id: 1
    };
  },
  // 実際のHTML描画
  render: function() {
    return (
      <div>{this.props.id}:{this.props.name}</div>
    );
  }
});

// コンポーネントをエレメントに割り当てる（初期値はname=hoge）
var component = React.render(<Test name="hoge" />, document.getElementById('content'));

// propの一部だけを置き換える（replaceだと割り当てていないプロパティはNULLが代入される）
component.setProps({ name: "piyo" });
