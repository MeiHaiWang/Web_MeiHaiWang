すべての通信のヘッダーに以下の情報をのせる

userID:0000  
ログインしている場合ユーザID
ログインしていない場合は-1

## <a name="osusume"></a>おすすめサロン、ヘアスタイル取得

### URL
```
GET /api/:version/osusume
```
### Response
```

{
  information: {
    published_at": "201304121613"
  }, 
  salon_lists: [
    {
      id: 834336,
      name: "美美美",
      image: "http://madeth-mac.local:3000/system/uploads/banner_information/image/2/ff037c9c15.png",
      message: "サロンからのメッセージだよーん",
      place: "北京",
    },
    {
      id: 55556,
      name: "美美美",
      image: "http://madeth-mac.local:3000/system/uploads/banner_information/image/2/ff037c9c15.png",
      message: "サロンからのメッセージだよーん",
      place: "北京",
    },
    {
      id: 666666,
      name: "美美美",
      image: "http://madeth-mac.local:3000/system/uploads/banner_information/image/2/ff037c9c15.png",
      message: "サロンからのメッセージだよーん",
      place: "北京",
    }
  ],
  style_lists: [
    {
      id: 28376,
      name: "ショートボブちん",
      image: "http://madeth-mac.local:3000/system/uploads/banner_information/image/2/ff037c9c15.png",
      shopId: 33333,
      stylistId: 2222222
    },
    {
      id: 28376,
      name: "ショートボブちん",
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


## <a name="shopDetail"></a>店舗詳細取得

### URL
```
POST /api/:version/shop_detail
```

### Request Params
```
id=834336　店舗ID
```

### Response
```
    {
      id: 834336,
      name: "美美美",
      image1: "http://madeth-mac.local:3000/system/uploads/banner_information/image/2/ff037c9c15.png",
      image2: "http://madeth-mac.local:3000/system/uploads/banner_information/image/2/ff037c9c15.png",
      image3: "http://madeth-mac.local:3000/system/uploads/banner_information/image/2/ff037c9c15.png",
      star_count: 4.0,
      message: "サロンからのメッセージだよーん",
      tel: "0000000000",
      address: "北京市あああああああ",
      business_hours: "10:00-22:00",
      regular_holiday: "火曜日",
      multilingual: "日本語、韓国語",
      word_of_mouth_count: 150
    }

```

 キー | 値
--------|---------
id | サロンのID
name | サロン名
image1 image2 image3 | 画像のURL 画像の枚数が少ない時は項目ごと消える
star_count | 星の平均値
message | サロンからのメッセージ
address | 住所
tel | 電話番号
business_hours | 営業時間
regular_holiday | 定休日
multilingual | 対応外国語
word_of_mouth_count | 口コミの数

## <a name="shopMap"></a>店舗地図取得

### URL
```
POST /api/:version/shopMap
```

### Request Params
```
id=834336　店舗ID
```

### Response
```
    {
      image: "http://madeth-mac.local:3000/system/uploads/banner_information/image/2/map.png",
      latitude: 35.689,
      longitude: 135.928,
      info: "駅から徒歩５分"
    }

```

 キー | 値
--------|---------
image | 地図画像（仮　googleマップの代わりがあるなら要らない）
latitude | 緯度
longitude | 経度
info | 最寄り駅からの距離など

## <a name="stylistDetail"></a>スタイリスト一覧取得

### URL
```
POST /api/:version/stylist_list
```

### Request Params
```
id=834336　店舗ID
```

### Response
```
    {
      id: 834336,
      shopID:3945773,
      name: "イケメンちゃん",
      gender: 0,
      image: "http://madeth-mac.local:3000/system/uploads/banner_information/image/2/ff037c9c15.png",
      message: "スタイリストからのメッセージだよーん",
      years: "7年間"
    },
    {
      id: 834336,
      shopID:3945773,
      name: "イケメンちゃん",
      gender: 0,
      image: "http://madeth-mac.local:3000/system/uploads/banner_information/image/2/ff037c9c15.png",
      message: "スタイリストからのメッセージだよーん",
      years: "7年間"
    }

```

 キー | 値
--------|---------
id | サロンのID
shopID | 所属店舗のID
name | スタイリスト名
gender | 性別　0=男性　1=女性
image | 画像のURL
message | スタイリストからのメッセージ
years | 経験年数


## <a name="stylistDetail"></a>スタイリスト詳細取得

### URL
```
POST /api/:version/stylist_detail
```

### Request Params
```
id=834336　スタイリストID
```

### Response
```
    {
      id: 834336,
      shopID:3945773,
      name: "イケメンちゃん",
      gender: 0,
      image: "http://madeth-mac.local:3000/system/uploads/banner_information/image/2/ff037c9c15.png",
      message: "スタイリストからのメッセージだよーん",
      years: "7年間",
      good: 1
    }

```

 キー | 値
--------|---------
id | サロンのID
shopID | 所属店舗のID
name | スタイリスト名
gender | 性別　0=男性　1=女性
image | 画像のURL
message | スタイリストからのメッセージ
years | 経験年数
good | いいねフラグ


## <a name="getArea"></a>地域エリア取得

### URL
```
POST /api/:version/area
```

### Request Params
```
id=1　地域ID
パラメータ無しで最上位の地域を取得する　（北京、広州など）
パラメータを与えられた場合その地域の詳細地域を取得する　
```

### Response
```
    areaList: [
       {
         id: 1,
         name: "北京",
         isDetail: 1,
       },
       {
         id: 2,
         name: "広州",
         isDetail: 1
       }
    ]

```

 キー | 値
--------|---------
id | サロンのID
name | サロン名
isDetail | 詳細地域があるのかないのか判定用フラグ

## <a name="conditions"></a>こだわり条件取得

### URL
```
POST /api/:version/conditions
```
### Response
```
{
      titles:[
         {
            id: 1,
            name: "時間"
         },
         {
            id: 2,
            name: "サービス"
　　　　  },
         {
            id: 3,
            name: "設備"
　　　　　}
      ],
      values: [
         {
            id: 1,
            titleID: 1,
            name: "当日予約可能",
         },
         {
             id: 2,
             titleID: 1,
             name: "１９時以降も予約受付"
          },
          {
             id:3,
             titleID: 2,
             name: "英語受付対応"
          }
      ]
}
```

 キー | 値
--------|---------
titles:id | タイトルID
titles:name | タイトル
values:id | こだわり条件のID
values:titleID | どこのグループ（タイトルに属しているか）
values:name | こだわり条件

*これ構造が微妙だから要検討

## <a name="catalog_categoly"></a>ヘアカタログカテゴリー取得

### URL
```
POST /api/:version/catalog/categoly
```

### Request Params
```
gender=0　性別フラグ　0=男性、1=女性
```

### Response
```
{
   catalogCategory:[
      {
         id:1,
         name: "ショート",
         image: "http://exsample.com/Short.png"
      },
      {
         id:1,
         name: "ロング",
         image: "http://exsample.com/long.png"
      }
   ]
}
```

 キー | 値
--------|---------
catalogCategory:id | catalogCategoryID
catalogCategory:name | タイトル
catalogCategory:image | 画像のURL

## <a name="cataloglist"></a>ヘアカタログ一覧取得

### URL
```
POST /api/:version/catalog/list/new 新着順
POST /api/:version/catalog/list/good 高評価順
```
### Request Params
```
categoryID=0 or stylistID=0
```

### Response
```
{
   stylistInfo:{
      id:0,
      name: "イケメンちゃん"
   },
   cataloglist:[
      {
         id:1,
         image: "http://exsample.com/Shor1.png"
      },
      {
         id:2,
         image: "http://exsample.com/Shor2.png"
      },
      {
         id:3,
         image: "http://exsample.com/Shor3.png"
      },
      {
         id:4,
         image: "http://exsample.com/Shor4.png"
      }
   ]
}
```

 キー | 値
--------|---------
stylistInfo:id | stylistID
stylistInfo:name | スタイリストの名前
cataloglist:id | cataloglistID
cataloglist:image | 画像のURL
*パラメータにstylistIDが与えられた場合stylistInfoの項目が出現する

## <a name="cataloglist"></a>ヘアカタログ詳細取得

### URL
```
POST /api/:version/catalog/detail
```
### Request Params
```
cataloglistID=1
```

### Response
```
{
   id: 1,
   image1: "http://exsample.com/minibobex.png",
   image2: "http://exsample.com/minibobex.png",
   image3: "http://exsample.com/minibobex.png",
   isgood: 1,
   stylistID: 1
}
```

 キー | 値
--------|---------
id | catalogDetailID
image1 | 画像のURL
image2 | 画像のURL
image3 | 画像のURL
isgood | いいねフラグ
stylistID | この髪型を登録したスタイリストのID
