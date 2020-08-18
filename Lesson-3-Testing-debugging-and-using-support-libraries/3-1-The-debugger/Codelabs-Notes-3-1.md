# Codelabs Notes: 3.1: The debugger

## SimpleCalc 編譯失敗: Aapt2Exception 

### 環境設定

> 1. Android Studio 4.0.1
> 2. Emulator: API 29

### 問題

課程範例無法正常編譯，出現以下錯誤訊息。

```log
java.util.concurrent.ExecutionException: com.android.builder.internal.aapt.v2.Aapt2Exception: AAPT2 error: check logs for details
```

利用`Gradle>app>assembleDebug`指令，並從它產出的 `BuildOutput`來看，訊息如下:

```log
D:\Daniel\github\android-developer-fundamentals-course-v2\Lesson-3-Testing-debugging-and-using-support-libraries\3-1-The-debugger\SimpleCalc\app\build\intermediates\incremental\mergeDebugResources\merged.dir\values-sw600dp-v13\values-sw600dp-v13.xml: error: file not found.

> Task :app:mergeDebugResources FAILED
Error: java.util.concurrent.ExecutionException: com.android.builder.internal.aapt.v2.Aapt2Exception: AAPT2 error: check logs for details

FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':app:mergeDebugResources'.
> Error: java.util.concurrent.ExecutionException: com.android.builder.internal.aapt.v2.Aapt2Exception: AAPT2 error: check logs for details
```

上網爬了幾篇文，主要有幾個方向:

1. 檢查`XML`、`Manifest`、其他資源檔是否有錯誤要修正。
2. 在`gradle.properties`中加入`android.enableAapt2=false`。
3. Non-ASCII字元的問題
4. 在`build.gradle`中設定

    ```groovy
    lintOptions {
        checkReleaseBuilds false
        abortOnError false
    }
    ```

以上方法除了第一點之外，看起來都並不是解決根本問題的方法。
因為從Log來看，實際的問題出在 `values-sw600dp-v13.xml: error: file not found.`。

### 解決

抱著死馬當活馬醫的心態，將`Gradle`版本更新到最新，然後就莫名的編譯成功了...

`Gradle` 版本:

- 舊: `classpath 'com.android.tools.build:gradle:3.1.4'`
- 新: `classpath 'com.android.tools.build:gradle:4.0.1'`

### 參考資料

1. [Error:com.android.tools.aapt2.Aapt2Exception: AAPT2 error: check logs for details](https://stackoverflow.com/q/46988102/9982091)
2. [真正解決方案:java.util.concurrent.ExecutionException: com.android.tools.aapt2.Aapt2Exception: AAPT2 error:](https://codertw.com/%E7%A8%8B%E5%BC%8F%E8%AA%9E%E8%A8%80/506828/)
