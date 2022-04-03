# HouseholdBook

HouseholdBookは複数人で使用することを目的とした家計簿アプリです。  
現在はローカルで使用することを目的としています。  

# 使い方

1.支出入力  
データの入力ができます。  
日付、メモ、金額、タグを入力・選択して入力完了ボタンを押すと入力完了です。  

![MainActivity](https://user-images.githubusercontent.com/102669119/161408454-e64edd28-cb8f-4d90-90ec-92a45a4c5f3c.png)

2.カレンダー  
カレンダーの日付をタップすると下部にその日の支出情報が一覧表示されます。  

![CalendarActivity](https://user-images.githubusercontent.com/102669119/161408459-88955120-9e69-4bfe-a29b-aaebef0748dd.png)

3.レポート  
一か月の支出情報をタグごとに円グラフで見ることができます。  
下部には支出情報がタグごとに一覧表示されます。  

![ReportActivity](https://user-images.githubusercontent.com/102669119/161408460-ea0ad990-c9d1-43af-b29e-810463dcdac5.png)

4.その他  
ヘルプ画面や固定支出画面への遷移、ユーザー名の設定ができます。  

![OthersActivity](https://user-images.githubusercontent.com/102669119/161408462-5d8d03ef-dd32-4dcd-a9de-af973901346e.png)

# 今後の実装予定

・データのリセット  
・固定支出の設定  
・カレンダー・レポート画面における一か月の総支出額の表示  
・カレンダー画面からのデータの編集、削除  
・ヘルプ画面の内容の記載  

# 開発環境・使用技術

・Windows 10  
・Android Studio Bumblebee | 2021.1.1 Patch 2  
・java version 1.7.0_80  
・Firebase Firestore database  
・Sourcetree version 3.4.5.0  
