# Note: 4.3: Menus and pickers

## 一、Menu 簡介

- Menu 是一組選項的集合。
- 使用者選擇任一選項後可執行對應的行為。

### 1-1 Menu 的種類

![](https://i.imgur.com/VbViGYR.png)

1. Options menu
    - 出現在App bar上
    - 通常執行App相關設定等選項
2. Contextual menu
    - 浮動式的選項清單，通常出現在畫面中央
    - 使用者長按一個View元件後出現
3. Contextual action bar
    - 出現在畫面上端，並蓋住App bar
    - bar上的選項可影響被選擇的View元件。
    - 例如長按某清單後出現Contextual action bar，Bar中可以選擇刪除或編輯此選項。
4. Popup menu
    - 浮動式的選項，會錨點在某個View上，像是`ImageButton`。
    - 例如舊版Gmail上按回覆會有Reply,Reply All, Forward這三個選項。

## 二、App bar 與 Options menu

### 2-1 App bar

- 又稱為 Action bar
- 在每個 `Activity` 上方都有其專屬位置
- 如果是用Android Studio的template建立的 `Activity` ，都會自動包含App bar。
- 如要隱藏自動加入的 App bar，需要修改該 `Activity` 的 `Theme` ，使其繼承`xxx.NoActionBar`
- 標題預設會顯示App的名稱，或是`Activity`在`AndroidManifest.xml`中的`android:label`值。
- 如果該 `Activity` 被指定為某 `Activity` 的子 `Activity` ，則會在App bar上顯示 *Up* 按鈕。(其實就是返回鍵，不過實際的細節不太一樣)

### 2-2 Option menu

- 行為通常是連結到其他畫面或是跟APP相關的設定(全域)。
  - 不應該是針對畫面上特定元件的行為，若要針對特定元件，應該使用`Contextual menu`
- 位於App bar的右側角落

### 2-3 App bar 的畫面配置

由左至右依序為

1. Navigation button or Up button:
    - Navigation button: 用來開啟`Navigation Drawer` Layout。
    - Up button: 返回到父`Activity`。
2. Title:
    - App的名稱
    - Activity的label (`AndroidManifest.xml`中的`android:label`值)
3. Action icons for the options menu:
    - 可將常用的options以icon形式顯示在這裡
4. Overflow options menu:
    - 點選後會跳出浮動的option menu，通常是較不常使用的選項。

### 2-4 加入 App bar

如同前面所提，基本上預設的樣板 `Activity` 就會自動加上原生的 `ActionBar` 當作App bar，但是因為系統版本更迭與功能不斷新增的情況，原生的`ActionBar`在各版本的差異過大，官方建議使用**Support Library**中的`ToolBar`作為App bar的選擇。

`Toolbar`的好處有:

1. 支援多種裝置
2. 客製化與擴充性較高
3. 支援最新的功能與特性

如果要使用`Toolbar`的話，有以下幾個步驟要做:

- 將要使用的`Activity`的`Theme`繼承`NoActionBar`結尾的主題。
- 在Layout中要加入`AppBarLayout`與`Toolbar`。
- 在`Activity`中將`Toolbar`設為`App bar`。

### 2-5 使用Themes設計App bar

剛剛前面有提到，如果要使用`Toolbar`作為`App bar`，一個方式是將App的Theme所繼承的父類，改成`NoActionBar`結尾的主題。
這樣的方式雖然方便，但是會是改變整個App的`Theme`，倘若我們只需要修改單一`Activity`就必須要針對`Style`做處理。

以下是一個預設的AppTheme，通常Android Studio產生出來的專案就會長這樣。
`AppTheme` 會繼承所有`Theme.AppCompat.Light.DarkActionBar`的屬性，`item`為我們自訂的屬性。

```xml
 <!-- Base application theme. -->
 <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
       <!-- Customize your theme here. -->
       <item name="colorPrimary">@color/colorPrimary</item>
       <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
       <item name="colorAccent">@color/colorAccent</item>
 </style>
```

#### 2-5-1 建立新的style並繼承`AppTheme`後override

```xml
 <style name="AppTheme.NoActionBar">
       <item name="windowActionBar">false</item>
       <item name="windowNoTitle">true</item>
 </style>

 <style name="AppTheme.AppBarOverlay"
             parent="ThemeOverlay.AppCompat.Dark.ActionBar" />

 <style name="AppTheme.PopupOverlay"
             parent="ThemeOverlay.AppCompat.Light" />
```

以上的寫法會override `AppTheme`中相同名稱的屬性。

#### 2-5-2 讓指定的`Activity`隱藏原生的`ActionBar`

```xml
<activity
      <!-- android:name and android:label code goes here. -->
      android:theme="@style/AppTheme.NoActionBar">
      <!-- intent filter code would go here if needed. -->
</activity>
```

### 2-6 加入 `AppBarLayout` 與 `ToolBar`

- root element 須為`CoordinatorLayout`
- `AppBarLayout`要是第一層

```xml
<android.support.design.widget.CoordinatorLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.example.android.droidcafeinput.MainActivity">

    <android.support.design.widget.AppBarLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:theme="@style/AppTheme.AppBarOverlay">
        <android.support.v7.widget.Toolbar
            android:id="@+id/toolbar"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize"
            android:background="?attr/colorPrimary"
            app:popupTheme="@style/AppTheme.PopupOverlay" />
    </android.support.design.widget.AppBarLayout>
    
    <include layout="@layout/content_main" />

</android.support.design.widget.CoordinatorLayout>
```

#### 2-6-1 Scrolling Behavior

- 主要的Content Layout 可加上 `app:layout_behavior="@string/appbar_scrolling_view_behavior"`屬性，定義Content Layout滑動時與App bar的互動方式。
  - `android.support.design.widget.AppBarLayout$ScrollingViewBehavior`

### 2-6-2 `Activity`中設定`ToolBar`

```java
// `onCreate()` 階段設定ToolBar
Toolbar toolbar = findViewById(R.id.toolbar);
setSupportActionBar(toolbar);
```

#### 2-7 加入 Options Menu

#### 2-7-1 建立 menu/main_menu.xml

```xml
 <menu xmlns:android="http://schemas.android.com/apk/res/android"
     xmlns:app="http://schemas.android.com/apk/res-auto"
     xmlns:tools="http://schemas.android.com/tools"
     tools:context="com.example.android.droidcafeinput.MainActivity">
     <item
         android:id="@+id/action_settings"
         android:orderInCategory="100"
         android:title="Settings"
         app:showAsAction="never" />
```

#### 2-7-2 Override `onCreateOptionsMenu()` 建立menu

```java
@Override
public boolean onCreateOptionsMenu(Menu menu) {
 getMenuInflater().inflate(R.menu.menu_main, menu);
 return true;
}
```

#### 2-7-3 Override `onOptionsItemSelected()` 處理menu點擊

```java
@Override
public boolean onOptionsItemSelected(MenuItem item) {
   switch (item.getItemId()) {
      case R.id.action_order:
         showOrder();
         return true;
      case R.id.action_status:
         showStatus();
         return true;
      case R.id.action_contact:
         showContact();
         return true;
      default:
         // Do nothing
   }
   return super.onOptionsItemSelected(item);
}
```

## 三、Contextual menus

- 讓使用者可針對被選取的View執行動作。
- 常與 `RecyclerView`, `GridView` 搭配使用。
- 有兩種形式
   1. context menu:
        - 顯示於畫面正中央
   2. contextual action bar
        - 顯示於App bar的位置

### 3-1 Floating context menu

1. 建立menu.xml
2. 在Activity中使用`registerForContextMenu()`綁定View與menu
3. Override `onCreateContextMenu()` 來建立menu
4. Override `onContextItemSelected()` 來處理點擊事件

### 3-2 Contextual action bar

- 只有在`Contextual Action Mode`下才會顯示
  - `Contextual Action Mode`是`ActionMode`的實作，當使用者常按元件的時候觸發。
- `ActionMode`能夠提供另一種的互動方式，也就是將原本畫面上的UI暫時性的替換，直到行為完成。
  - 像是某些APP在選取文字時，會出現可分享、編輯、刪除等行為選項，這樣的情況。
- 當`ActionMode`被停用了，`Contextual Action Bar`就消失了。

#### 3-2-1 建構方式

1. 建立對應的menu.xml
2. 對可接受長按的View設定`setOnLongClickListener()`，並在override的方法中呼叫`startActionMode()`。
3. 實作`ActionMode.Callback`來處理`ActionMode`的生命週期。其中包含`onActionItemClicked()`這個方法，主要處理menu選項點擊事件。
4. 建立每個選項要觸發的事件。

#### 3-2-2 `startActionMode`

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
   // ... Rest of onCreate code
   articleView.setOnLongClickListener(new View.OnLongClickListener() {
      // Called when the user long-clicks on articleView
      public boolean onLongClick(View view) {
         if (mActionMode != null) return false;
         // Start the contextual action bar
         // using the ActionMode.Callback.
         mActionMode = 
               MainActivity.this.startActionMode(mActionModeCallback);
         view.setSelected(true);
         return true;
      }
   });
}
```

#### 3-2-3 `ActionMode.Callback`

```java
public ActionMode.Callback mActionModeCallback = new
                                        ActionMode.Callback() {
   @Override
   public boolean onCreateActionMode(ActionMode mode, Menu menu) {
      // Inflate a menu resource providing context menu items
      MenuInflater inflater = mode.getMenuInflater();
      inflater.inflate(R.menu.menu_context, menu);
      return true;
   }

   // Called each time ActionMode is shown. Always called after 
   // onCreateActionMode.
   @Override
   public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
      return false; // Return false if nothing is done
   }

   // Called when the user selects a contextual menu item
   @Override
   public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
      switch (item.getItemId()) {
         case R.id.context_edit:
            editNote();
            mode.finish();
            return true;
         case R.id.context_share:
            shareNote();
            mode.finish();
            return true;
         default:
            return false;
      }
   }

   // Called when the user exits the action mode
   @Override
   public void onDestroyActionMode(ActionMode mode) {
      mActionMode = null;
   }
};
```

### 3-3 Popup menu

- 直向清單元件且錨點在特定的View上，通常是`Button`。
- 通常是提供overflow的選項。
- 通常裡面的選項不會影響View的內容。

#### 3-3-1 建置步驟

1. 建立menu.xml
2. 建立錨點的View，可以是`ImageButton`
3. 設定`setOnClickListener()`
4. 實作`onClick()`，inflate menu並將其註冊`PopupMenu.OnMenuItemClickListener`
5. 實作`onMenuItemClick()`

#### 3-3-2 建立 Popup menu

```java
// Define onClick here ...
@Override
public void onClick(View v) {
   // Create the instance of PopupMenu.
   PopupMenu popup = new PopupMenu(MainActivity.this, mButton);
   // Inflate the Popup using XML file.
   popup.getMenuInflater().inflate(R.menu.menu_popup, popup.getMenu());
   // Register the popup with OnMenuItemClickListener.
   popup.setOnMenuItemClickListener(new 
                              PopupMenu.OnMenuItemClickListener() {
      // Add onMenuItemClick here...
      // Perform action here ...
}
```

## 四、Dialog and Picker

### 4-1 Dialog

- Dialog是一種佔滿螢幕或是在最上方的視窗元件。
- 會中斷使用者流程，所以要謹慎的使用。
- 通常是重要訊息或是特定的任務。
- `Dialog`類別是所有對話框類型的父類別。

#### 4-1-2 Dialog的子類別

Android原生提供許多不同的子類別，方便開發者快速運用。
官方也建議不要直接使用`Dialog`，而是使用其子類別的實作。

1. `AlertDialog`: 顯示文字訊息，包含標題、訊息、三種按鈕(Pos/Neg/Nue)、清單選項、或是自訂版面。
2. `DatePickerDialog`: 預設的版面讓使用者選擇日期。
3. `TimePickerDialog`: 預設的版面讓使用者選擇時間。

### 4-2 `AlertDialog`

- 標準的警示用對話框
- 有三大區塊
    1. 標題
    2. 內文
        - 文字訊息
        - 清單
        - 自訂Layout
    3. 按鈕
        - Positive
        - Negative
        - Nuetral
- 使用`AlertDialog.Builder`建立。

```java
AlertDialog.Builder myAlertBuilder = new 
                  AlertDialog.Builder(MainActivity.this);
myAlertBuilder.setTitle(R.string.alert_title);
myAlertBuilder.setMessage(R.string.alert_message);
myAlertBuilder.setPositiveButton("OK", new 
                                 DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int which) {
           // User clicked OK button.
           // ... Action to take when OK is clicked.
      }
});
myAlertBuilder.setNegativeButton("Cancel", new 
                                 DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int which) {
         // User clicked the CANCEL button.
         // ... Action to take when CANCEL is clicked.
      }
});
alertDialog.show();
```

### 4-3 `Date and time pickers`

- 使用`DialogFragment`來實作。(`Fragment`的子類別。)
  - 能夠將程式的區塊劃分
  - 能夠利用`DialogFragment`掌握`Dialog`的生命週期
  - 能夠實作不同的layout形式
- 會是浮現在畫面上方的形式。

#### 4-3-1 建構方式

1. 建立新類別繼承 `DialogFragment` 並實作 `DatePickerDialog.OnDateSetListener`。
2. Override `onDateSet()` 處理設定的時間。
3. Override `onCreateDialog()` 建立`DatePickerDialog`的物件實體。
4. 在`Activity`中建立新類別的物件實體，使用`show()`方法顯示。

DatePickerDialog 與 TimePickerDialog原則上建構方式相同，只是回傳的Dialog與部分方法(`onTimeSet`)不同。


## 五、參考來源

- [4.3: Menus and pickers](https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/unit-2-user-experience/lesson-4-user-interaction/4-3-c-menus-and-pickers/4-3-c-menus-and-pickers.html#app_bar)