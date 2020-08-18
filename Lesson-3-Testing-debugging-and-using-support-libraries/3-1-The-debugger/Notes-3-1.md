# Notes: 3.1: The Android Studio debugger

## 關於除錯這件事 About debugging

> 除錯 (Debugging)是指尋找錯誤、修正錯誤(bugs)或是異常行為處理的過程。

Bugs可能是從這些原因而來:

1. 設計或實作(implementation)上的錯誤(Errors)。
2. Android Framework 的限制或錯誤。
3. APP執行所需的要求(requirements)未達到，或是未預想(assumptions)到APP執行後可能產生的情況。
4. 硬體裝置本身的限制或錯誤。

除錯的方法包含 debugging、testing、profiling，而 Android Studio 提供的方式包含以下:

1. **Logcat**: 查看錯誤訊息。
2. **Debugger**: 查看除錯過程中的frames, threads, and variables。
3. **Debug mode**: 使用除錯模式設定中斷點 (breakpoints)。
4. **測試框架**: JUnit、Espresso。
5. Dalvik Debug Monitor Server (DDMS): 追蹤資源(resources)用量。

## 執行 debugger

### 直接以Debug mode執行APP

直接點選工具列上的`DEBUG`圖示，即可以Debug mode執行APP。
而畫面下方的Debugger區域有下列三大重點:

1. **Frames** 區塊
    - 目前執行緒中，已經被執行的方法們，依執行順序由下而上堆疊(stack)，最近執行的方法會在最上面。
    - 點選 **Threads** 可切換執行緒。
2. **Watches** 按鈕
    - 點選可以切換是否要在**Variables**區域中顯示**Watches**。
    - 這個功能能夠讓開發者追蹤特定變數，觀察該變數在執行過程中的變化。
3. **Variables**
    - 顯示目前scope裡的變數與其值。
    - 下拉選單可以展開變數物件的properties

### 在執行APP的狀態下插入Debugger

在APP執行的狀態下，點選工具列上的**Attach Debugger**，即可將Debugger插入目前的APP中。

## 如何使用中斷點 (breakpoints)

Android Studio 支援多種中斷點的行為，最常使用的一種就是暫停APP在特定行數。

### 新增中斷點

在要中斷的Code的行數旁邊點選滑鼠左鍵，即可新增紅色的中斷點符號。
或者使用`Ctrl + F8`的快捷鍵即可新增或移除中斷點。

> 如果點選到的Code是無法被執行到的，則會顯示x符號。

### 檢視所有中斷點/設定有條件的中斷點

點選 `Debug工作區` 的左側的`View Breakpoints`圖示，即可檢視前專案內所有已設定的中斷點。
每個中斷點都可以設定

1. 是否啟用或停用
2. 是否在某中斷點執行後再執行此中斷點
3. 是否執行一次就好

### 停用所有中斷點

點選 `Debug工作區` 的左側的 `Mute Breakpoints`圖示，可以停用所有中斷點。

### 使用有條件的中斷點

有條件的中斷點能夠讓中斷點在特定狀態下才啟用。

1. 右鍵點選中斷點或是在`View Breakpoints`視窗中設定
2. 條件設定
    - 只要是Java Expression 且回傳值為 boolean
    - 可使用程式碼中的變數值

## 逐行/逐方法檢視程式碼 Stepping through code

除了在中斷點時暫停程式外，也可使用下列方法，逐步觀看程式的執行流向。

### Step Over 下一步

<!-- Step Over executes the next line of the code in the current class and method, executing all of the method calls on that line and remaining in the same file. -->
執行目前debug的類別或方法中的下一行程式碼。

### Step Into 進入

<!-- Step Into jumps into the execution of a method call on the current line (as compared to just executing that method and remaining on the same line). -->
進入目前被呼叫的這個方法中。如果被呼叫到的方法位於另外一個檔案或類別，則會自動開啟該檔案。

### Step Out 退出

Step Out 離開目前所Debug到的方法，並回到該方法被呼叫的點。

## 檢視目前執行的Frames堆疊 Viewing execution stack frames

Debug 工作區中的 **Frames** 區域，可以看到所有被執行的方法(method/frame)或類別(class)，並以反向方式排序(最新的在最上面)，以及是哪一個方法跑到目前的中斷點。
點擊該行**Frame**會更新右側的**Variable**、**Watches**以及上方反白的程式碼。

## 檢視並修改變數 Inspecting and modifying variables

- **Variables**工作區可以讓開發者檢視目前frame的變數狀態。若變數為物件或集合等型態，可以展開檢視它的屬性或值。
- 右鍵點選要編輯的變數，可以透過`set value`這個方法更改其值。

## Setting watches

- **Watches**跟**Variables**的作用類似，但是**Watches**能夠跨debug sessions，所以通常是需要頻繁取用的屬性或變數。
- 透過**Watches**工作區的加號或減號就可新增與移除

> 老實說試用了幾次，還是不太明白這個好處在哪  
> 可能是還沒遇到適用的除錯情境吧XD

## Evaluating expressions

- 可以觀察變數或物件的狀態，並可呼叫其方法。
- 點選 `Evaluate Expression`的圖式即可開啟，或者在**Variables**中選擇該變數後按`alt + F8`。
- 在跳出視窗的`Code Fragments`輸入框中，可存取程式碼中的物件與方法，這裡操作的結果也會影響程式中執行的結果。
- 在`Evaluate Expression`中所取到的物件或值，會是跟程式運行中的狀態相同。

## 其他除錯工具

1. System log (Logcat pane)
    - `Log`類別設定。
2. Tracing and logging
    - 分析方法使用了多少時間執行。
    - `Debug`類別中的`startMethodTracing()`、`stopMethodTracing()`，分別設定開始與結束追蹤。
    - 會追蹤整個VM裡所執行的方法。
    - E.g. 可設定在`Activity`的`onCreate()`與`onDestroy()`，來觀察整個`Activity`的活動。
3. Android Debug Bridge (ADB)
    - 使用指令與模擬機或實體裝置溝通
4. Android Profiler
    - 即時監測CPU、記憶體、網路使用狀況
5. CPU Profiler
    - 即時監測CPU與各執行緒的狀態
6. Network Profiler
    - 即時監測連線狀態，上傳下載狀態、目前連線數等。

## 其他補充說明

> 補充: TraceView 這個工具已經被棄用了，但Concept中還沒被拿掉。

1. `AndroidManifest.xml`中的`<application>`可設定 `android:debuggable`屬性，決定APP是否為可除錯的狀態。預設值為 `false` 。
2. 利用 `Gradle.BuildType` 來設定 `debuggable`。在預設的 `BuildType` 分類裡，`debug`中的`debuggable`為`true`。

### 發布正式版前的注意事項

除錯完後總要發布，官方給了下列幾點建議，希望開發者在發布APP前能夠做這些調整。

1. 移除所有測試/除錯用的程式碼。
2. 移除所有Logging相關的程式碼。
3. 移除所有顯示`Toast`的呼叫。
4. 把`AndroidManifest.xml`或`Gradle.BuildType`裡的`debuggable`都設為`false`。
5. 移除所有 debug tracing 方法。例如，`startMethodTracing()`
