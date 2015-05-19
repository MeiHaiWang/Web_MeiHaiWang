## <a name="ads"></a>おすすめサロン、ヘアスタイル取得

### URL
```
GET /api/:version/omakase-
```
### Response
```

{
  information: {
    published_at": "201304121613"
  }, 
  salon_lists: [
    {
      id: 834336
      name: "美美美"
      image: "http://madeth-mac.local:3000/system/uploads/banner_information/image/2/ff037c9c15.png",
      message: "サロンからのメッセージだよーん",
      place: "北京",
    },
    {
      id: 55556
      name: "美美美"
      image: "http://madeth-mac.local:3000/system/uploads/banner_information/image/2/ff037c9c15.png",
      message: "サロンからのメッセージだよーん",
      place: "北京",
    },
    {
      id: 666666
      name: "美美美"
      image: "http://madeth-mac.local:3000/system/uploads/banner_information/image/2/ff037c9c15.png",
      message: "サロンからのメッセージだよーん",
      place: "北京",
    }
  ],
  style_lists: [
    {
      id: 28376
      name: "ショートボブちん"
      image: "http://madeth-mac.local:3000/system/uploads/banner_information/image/2/ff037c9c15.png",
      shopId: 33333,
      stylistId: 2222222
    },
    {
      id: 28376
      name: "ショートボブちん"
      image: "http://madeth-mac.local:3000/system/uploads/banner_information/image/2/ff037c9c15.png",
      shopId: 33333,
      stylistId: 2222222
    }
  ]
}
```

 キー | 値
--------|---------
information:published_at | 更新日
salon_lists:id | サロンのID
salon_lists:name | サロン名
salon_lists:image | 画像のURL
salon_lists:message | サロンからのメッセージ
salon_lists:place | 大体の場所
style_lists:id | 髪型のID
style_lists:name | 名前
style_lists:image | 画像のURL
style_lists:shopId | お店のID
style_lists:stylistId | スタイリストのID
