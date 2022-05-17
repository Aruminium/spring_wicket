# spring + wicketのテストです

[wicket_spring_boot_practice](https://github.com/gishi-yama/wicket_spring-boot_practice)をざっくりまとめました。

WicketでWebPageに表示するデータは、Springで作成・加工する

これによって、「データの生成」（Spring）と「画面への表示」（Wicket）の役割分担を行える。

<details><summary>MVCとは - モデル、ビュー、コントローラー、それぞれの機能の頭文字</summary>
  
- モデル
  
>MVCのモデルとはシステムの本体、根幹の大切な部分
>データを関連づけ検証するなど、繊細な処理を行います。

>例としては、日付機能やデータの保存、計算や、ユーザーインターフェイスに通知するという役割もあります。
>
>ユーザーアクセスにより促された処理をデータベースにアクセスし、操作・参照して必要な値に取り出し、または変化して、インターフェイスに通知するのがモデルの役割です。

- ビュー
  
>MVCのビューとはユーザーインターフェイスの部分を担当します。ユーザーインターフェイスとはユーザーが直接見る画面のことで、入力した機能の処理を行います。

>使用者（ユーザー）とサイトとのインターフェイス（接点）でもあり、レイアウトやメニュー、ボタンの操作性など、画面表示やボタンの操作性もビューの役割

>ビューの役割は、データを表示させたりする出力部分を担当します。HTMLを「動的に生成する」という役目を負います。
>
>例えば、TwitterなどのSNSなどは、最新の情報を画面に表示させます。アクセスするたびにデータベースから最新記事を取得し、トップページに表示するという処理を行います。

- コントローラー
  
>MVCのコントローラーとはユーザーの入力に基づき、モデルとビューを制御する役目を担う部分

>ユーザーが入力した情報に基づいて、モデルへデータを取り出す指示を、ビューにはモデルで取り出したデータを元に画面を表示する指示を出します。

>コントローラーの役目は、クライアントとビュー、モデルとの橋渡し役です。
>
>クライアントからのリクエストに応じた処理を行う際、必要に応じてモデルからデータの受け渡しを行い、ビューに表示する画面の処理を促す、という仲介役がコントローラーの役目です。
>
>データベースに送られてきたクライアントが入力した情報をビューへ渡し、画面を表示します。クライアントのボタン操作による情報でモデルに処理を促します。

</details>

***
## アノテーション

### 1. @WicketHomePage
\-- Wicket-Spring-Bootが表示する最初のページに設定する

`import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;`

### 2. @MountPath(〇〇)
\-- ` http://.../〇〇 ` URLパス指定

`import org.wicketstuff.annotation.mount.MountPath;`

### 3. @Service
\-- SpringでDIできる実装クラスであることを設定する
@Componentを継承しているのでDIコンテナで管理してくれる

DI(依存性注入)とは - オブジェクトの注入、クラス間の依存関係が明らかになる
テストコードを書くときに楽できる

`import org.springframework.stereotype.Service;`

>例えば，あるクラスAの中で別のクラスBのインスタンスを生成して利用しているとき，AはBに強く依存してしまっています。つまり，Bを別のクラスに差し替えたときなどにはAも変更しなければなりません。このような依存関係は，AとBを別の人が作っている場合などに特に困ります。
>
>　こうした依存性をクラスから取り除くのがDIパターンです。Bへの依存性をAから排除するには，まずBの機能を抽象化したインタフェースIを定義し，Iを実装したクラスとしてBを作ります。
>
> Bのインスタンスを生成するコードはAから削除し，その代わりに「Iを実装したクラスのインスタンス」を外部から与えてもらう形にします。Bの利用を前提に書かれていたコードはすべてIの機能を利用するコードに書き換えます。これでBを利用するコードがAから完全に取り除かれます。
>
>　実行時には誰かがBのインスタンスを生成してIにキャストし，Aに与えます。この操作が「依存性の注入」で，ここで初めてAがBに対して依存性を持つことになります。

### 4. @SpringBean
\-- DIコンテナに登録

SpringはIoC/DI用に設定されたフィールドに、実装クラスを自動でインスタンス化する

@Serviceを始めとした@Componentを継承しているDIコンテナに登録するものとの違いはなんだろうか

>SpringのDIコンテナに管理してもらいたいクラスが、自分で作成しているクラスなのか、それとも外部のライブラリ（サードパーティのライブラリ）のクラスなのかによって、@Componentを使うか@Beanを使うか変わる。

`import org.apache.wicket.spring.injection.annot.SpringBean;`

### 5. @Autowired
\-- DIコンテナに登録されているクラスを使用する.

[Autowired](https://springhack.com/autowired-%EF%BC%88springboot%E3%81%AE%E3%82%A2%E3%83%8E%E3%83%86%E3%83%BC%E3%82%B7%E3%83%A7%E3%83%B3%EF%BC%89/#Autowired-2)がわかりやすい

-> jdbcは1つしかない.のでAutowiredをつけてほかのクラスからでも使えるようにする

`import org.springframework.beans.factory.annotation.Autowired;`

### 6. @Repository
\-- DBアクセスを行います。付与することでSpirngのコンポーネントとして認識される(DIコンテナに登録)

`import org.springframework.stereotype.Repository;`

### 7. @AuthorizeInstantiation(Roles.USER)
\-- @AuthorizeInstantiation に設定されている Roles.USER

USER(おそらくログイン状態であるユーザ)のときそのクラスは有効化される

- MySession クラスの getRoles メソッドは、ユーザ認証済みの時に new Roles(Roles.USER) を戻す。new Roles() の中身が @AuthorizeInstantiation の値と一致しているので、ページを表示する。

- 一方で、 MySession クラスの getRoles メソッドは、ユーザが未認証の時に new Roles() を戻す。new Roles() の中身が @AuthorizeInstantiation の値と一致していないので、ページを表示しない。

### 8. @WicketSignInPage
\-- ユーザ認証を行うページであることを表す。まだ認証をしていないブラウザが、認証が必要なページにアクセスすると、このページに転送されてくる

***
## 修飾子

### 1. final修飾子
\-- 主に値を返納する予定がない定数に使う

### 2. protected
\-- 現在のクラスとサブクラスからアクセスできる
継承したクラスでも継承元のフィールドやメソッドが呼び出せる

***
## データベース関連

### 1. jdbcTemplate
\-- SQL クエリまたは更新を実行することができる

`import org.springframework.jdbc.core.JdbcTemplate;`


***
## メソッド・フィールド・クラス

### 1.RowMapper

SingleColumnRowMapper:
\-- データベースのテーブルから、１列だけのデータを検索するときに使う。

DataClassRowMapper:
\-- SingleColumnRowMapperの2列以上

### 2. LocalDataTime
\-- 時間を管理するJavaのライブラリクラス

### 3. BookmarkablePageLinkコンポーネント

```java
var toUserMakerLink = new BookmarkablePageLink<>("toUserMaker", UserMakerPage.class);
add(toUserMakerLink);
```

>- BookmarkablePageLink で、違うページへのリンクを作成する
>宛先のWebPageで @MountPage を指定しないと、URLにクラス階層が表示されるので注意
>- IModelインターフェースは、Link系のコンポーネントクラスの機能を定義するインターフェース。型引数に、Linkやそのモデルが管理する予定のデータ型（この場合はVoid)を指定する。
>- このBookmarkablePageLinkコンポーネントには、モデルを使っていない
>コンポーネントによっては、モデルがなくても良いものがある
>モデルが必要のない場合は <Void> 型引数を使う・型引数がそもそも存在しない（<>）の2パターンがある

***
## その他

### 1. ... extends WebPage
\-- Wicketが提供する WebPage クラスを継承することで、同じ名前のHTMLファイルを書き換える能力をもつ

`import org.apache.wicket.markup.html.WebPage;`

### 2. モデルクラス
\-- Modelクラスは、コンポーネント用のデータを設定する基本的な型（モデルと呼ぶ）

`import org.apache.wicket.model.Model;`

IModelインターフェースは、Model系クラスの機能を定義するインターフェース。型引数に、モデルが管理する予定のデータ型（この場合はString)を指定する。

### 3. ラベルクラス
\-- Labelクラスは、「HTMLのタグの中身の文章を書き換える」機能をもつコンポーネント
- Labelクラスの第1引数は、書き換える場所の wicket:id
- Labelクラスの第2引数は、書き換えるためのデータ（つまり、モデル）

モデルはコンポーネントに設定する
コンポーネントは、ページか、上位のコンポーネント（後述）に必ずaddする

モデルとクラスを組み合わせて
```java
public HomePage() {
  var youModel = Model.of("Wicket-Spring-Boot");
  var youLabel = new Label("you", youModel);
  add(youLabel);
}
```

### 4. 	AuthenticatedWebSession クラス
\-- ユーザー名とパスワードから認証を行う

protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass()
\-- アプリケーションで使うセッションストアのクラスを返す

### 5. MySession.get().sign(userName);
\-- セッションに、ユーザ認証が成功したユーザ名を記録する。 MySession を MySession.get() で呼び出すことがポイント


## まとめ

1. HTMLを作成
2. WebPageクラスのサブクラスを、HTMLと同じ場所に同じファイル名で作成
3. HTMLの中で、Wicketで書き換えたい部分に識別子（ wicket:id ）を設定
4. WebPageクラスのサブクラスの中で、HTMLのwicket:idの部分を書き換える モデルとコンポーネント を準備

- リンクを作ることで、Wicketで作成したページ間を移動できる
- モデルを不要とするコンポーネントも存在する
  
1. ブラウザから入力・送信された値は、UserMakerPage の Form の onSubmit の中で取得される
2. UserMakerPage の Form は、 IUserService のregisterUser機能に、取得した入力値（記録してほしいユーザIdとパスワード）を依頼する
3. IUserService の registerUser 機能は、渡された入力値を IAuthUserRepository の insert 機能に依頼する
4. IAuthUserRepository の insert 機能 は、H2DBにデータの登録を依頼し、呼び出し元に記録行数を返す
5. IUserService の registerUser 機能は、IAuthUserRepository から返された記録行数を標準出力に表示する
6. UserMakerPage の Form は、IUserService の registerUser機能の終了後、UserMakerCompPageを作ってブラウザに返す
7. ブラウザに UserMakerCompPage が表示される

