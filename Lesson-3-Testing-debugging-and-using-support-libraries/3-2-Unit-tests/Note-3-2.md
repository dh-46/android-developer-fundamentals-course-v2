# Note: 3.2: App testing

## 關於測試

- 為什麼要寫
    1. 確保程式依照設計正確地執行。
    2. 在程式開發初期就抓出問題。
    3. 確保程式的品質。
- 軟體開發中非常重要的環節之一。
- Test-driven development (TDD) 測試驅動的開發流程，就是以測試作為流程核心。
- 測試的程式碼不會包含在發布的APP中，但是是並存在專案裡。

### 測試的種類

Android支援多種不同的測試種類與測試框架，最基本的兩種是**單元測試 (Local Unit Test)**與**Instrumented Tests**。

#### Local Unit Tests

1. 在本地端編譯並執行在JVM上，不需要模擬機或實體裝置。
2. 主要在測商業邏輯。
3. 不會使用到Android Framework 或 Mock Android Framework的物件。
4. 所有測試中的最小單元，其中的每個測試都具有獨立性。
5. 測試Production code的最小單元，如methods、class、component等。
    - Methods: 可能會測不同參數值的產出，像是參數為NULL時的狀態。
    - Class: 測試類別的狀態
6. 常用的Mock Framework: Mockito
    - 將被測試的部分解依賴。
7. 常用的測試Lib: JUnit 4。

#### Instrumented tests

1. 需要在模擬機或實體裝置執行。
2. 需要 Android Framework 或是 **Instrumentation** 的資訊。
3. 可以用在單元測試、UI測試、整合性測試(integration testing)。
4. 最常用在UI測試，以驗證與預期的使用者體驗相符。
5. UI測試常用Espresso Framework，執行自動化測試。

> Android Testing Support Library
>
> - 提供測試Android App的基本工具與APIs
> - 通常會跟Android Studio一起安裝 (SDK Tools > Support Repository)
> - package `android.support.test`/ `android.test`

## 測試環境的設定

### Source Set

> Source sets are collections of code in your project that are for different build targets or other "flavors" of your app.

1. *main*: APP的原始碼與資源檔
2. *test*: 本地單元測試 (路徑: `src/test/java` )
3. *androidTest*:  Android instrumented tests

### Test dependencies (Gradle 設定)

為了要能使用單元測試的API，需要在專案中新增所需的 Dependencies。  
如果你的專案是透過Android Studio的Template所建立的，預設應該會有幾個基本的測試框架依賴。通常會因使用不同的Mocking或Matching框架，而再加入其他依賴。

預設來說，應該會在`build.gradle (Module: app)`中看到:

```groovy
// 基本單元測試會用到的
testImplementation 'junit:junit:4.12'
androidTestImplementation 'com.android.support.test:runner:1.0.1'

// 這個是UI測試會用到的
androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'
```

而在練習的Codelab裡，可能還會需要以下兩種

```groovy
// for matching
testImplementation 'org.hamcrest:hamcrest-library:1.3'
// for mocking
testImplementation 'org.mockito:mockito-core:1.10.19'
```

### Test runner 設定

> A test runner is a library or set of tools that enables testing to occur and the results to be printed to a log.

- JUnit 4 APIs 本身就帶有一個基本的Test Runner
- Android test support library 則有一個Test Runner，且支援 instrumented test 、 Espresso tests、JUnit 3、JUnit 4。

預設來說，應該會在`build.gradle (Module: app)`中看到:

```groovy
defaultConfig {
    testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
}
```

## 新增與執行單元測試

> 使用JUnit 4 API建立一個測試類別，並將其儲存在*test*的Source set下。

### 建立測試類別

- 命名慣例: 測試目標類別 + `Test`
- 類別存取修飾須為`PUBLIC`

### 撰寫測試

根據JUnit 4的語法撰寫測試，其類別須包含以下Annotation。

- `@RunWith`: 指名要用哪一種Test runner。
- `@SmallTest`: 代表這是一個小/快速測試。
- `@Before`: 測試初始化時要執行。
- `@Test`: 表示此方法是測試執行的方法。

```java
@RunWith(JUnit4.class)
@SmallTest
public class CalculatorTest {
   private Calculator mCalculator;
   // Set up the environment for testing
   @Before
   public void setUp() {
      mCalculator = new Calculator();
   }

   // test for simple addition
   @Test
   public void addTwoNumbers() {
       double resultAdd = mCalculator.add(1d, 1d);
       assertThat(resultAdd, is(equalTo(2d)));
   }
}
```

#### Assertion

> Assertions are expressions that must evaluate and result in a value of true for the test to pass.

整個測試方法裡最重要的部分就是`Assertion`，也就是上方範例裡的 `assertThat(resultAdd, is(equalTo(2d)));`。
Assertion的功能在於檢驗被測試所產出的值或結果，並得出是否通過測試的結論。

JUnit 4 框架提供了多種形態的 Assertion方法，但是`assertThat()`是相對最彈性的方法，因為它能夠使用 *matchers* 的比較方法(泛用型的比對)。

Hamcrest 是常用的matchers框架，它包含多種的比較方法，並且能夠讓開發者自行實作客製的比較方法。

另外一點要注意的是，單一測試方法裡最好只包含一種assertion，否則會造成測試難以除錯。
