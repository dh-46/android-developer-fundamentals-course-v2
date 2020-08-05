# Note for 1.1: Android Studio and Hello 

> [name=Daniel Huang][time=Wed, Aug 5, 2020 2:42 PM]

## About Gradle

Gradle is an automated project building tool. The related configurations are written in `build.gradle` files.
Basically, if you create a template project from Android Studio, there will be two `build.gradle` files. 
The one located in the root folder is the top-level gradle file, which contains projects-level configurations. 
The other one inside the `/app` folder is the module-level gradle file. It allows you to write custom build settings for each module.

In Android Pane, `Build Script` folder contains all the files needed by the build system.

> Gradle is a Domain Specific Language (DSL) for describing and manipulating the build logic using Groovy, which is a dynamic language for the Java Virtual Machine (JVM). You don't need to learn Groovy to make changes, because the Android Plugin for Gradle introduces most of the DSL elements you need.

### Project's build.gradle

- By default, there will be two repositories in script.
```gradle
allprojects {
    repositories {
        google()
        jcenter()
    }
}
```
- They are common to all modules in the project.

### Module's build.gradle

- Each module has its build.gradle
- Allows you to edit custom configuration for each module.
    - buildType: controls how the app is built and packaged. 
    - flavor
- The settings you written in here can override the settings from `AndroidManifest.xml` and `Project's build.gradle`.
- This file is the most often gradle file to be modified.
    - E.g. add new implementation for remote repository
- `implementation fileTree(dir: 'libs', include: ['*.jar'])`
    - adds a dependency of all ".jar" files inside the `libs` directory. 

:::success
To learn more about Gradle:
- [Configure Gradle Build](http://developer.android.com/tools/building/configuring-gradle.html)
- [Build System OverView](https://developer.android.com/studio/build)
:::

## manifest folder and AndroidManifest.xml

- The manifests folder contains files that provide essential information about your app to the Android system.
- The app must have, or the system cannot run.
- `AndroidManifest.xml` describes all of the components of your Android app. 
    - Activity
    - ContentProvider
    - BroadcastReceiver
    - Service
- The app's permissions and features are also written in here

### Android Manifest

```xml=
<?xml version="1.0" encoding="utf-8"?>
<!-- coded in XML and always uses the Android namespace: -->
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.android.helloworld">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".MainActivity">
            <intent-filter>
               <action android:name="android.intent.action.MAIN" />

               <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```

#### `android:allowBackup`

- enable system to auto backup your app for user
- For apps whose target SDK version is Android 6.0 (API level 23) and higher, devices running Android 6.0 and higher automatically create backups of app data to the cloud because the android:allowBackup attribute defaults to true if omitted.
- For apps < API level 22 you have to explicitly add the android:allowBackup attribute and set it to true.

## Logcat

- Debug、Error、Warn、Info、Verbose
- By convention, we will set the TAG like this:
    - `private static final String LOG_TAG = MainActivity.class.getSimpleName();`

## Resource files

- `res` folder
    - `drawable`: Store all your app's images in this folder.
    - `layout`: Every UI layouts xml is in here.
    - `mipmap`: The launcher icons are stored in this folder. There is a subfolder for each supported screen density. Android uses the screen density (the number of pixels per inch) to determine the required image resolution. Android groups all actual screen densities into generalized densities, such as medium (mdpi), high (hdpi), or extra-extra-extra-high (xxxhdpi). The ic_launcher.png folder contains the default launcher icons for all the densities supported by your app.
    - `values`: Instead of hardcoding values like strings, dimensions, and colors in your XML and Java files, it is best practice to define them in their respective values files.
        - `colors.xml`
        - `dimens.xml`
        - `strings.xml`
        - `styles.xml`

## TargetSdkVersion

```xml=
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.android.helloworld">
    <uses-sdk android:minSdkVersion="14" android:targetSdkVersion="19" />
    // ... Rest of manifest information
</manifest>
```

> In the [concept document](https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/unit-1-get-started/lesson-1-build-your-first-app/1-1-c-your-first-android-app/1-1-c-your-first-android-app.html), "Declaring the Android version" section mentions about `TargetSdkVersion` settings in Android Manifest.xml. I found some key concepts that worth to be written down.

- minSdkVersion attribute declares the minimum version for the app
- targetSdkVersion 
    - declares the highest (newest) version which has been optimized within the app
    - does not prevent an app from being installed on Android versions that are higher (newer) than the specified value.
    - it indicates to the system whether the app should inherit behavior changes in newer versions.
    - If you don't update the targetSdkVersion to the latest version, the system assumes that your app requires backward-compatible behaviors when it runs on the latest version.


:::warning
For more info, you could read the matrials from:
[1.0: Introduction to Android](https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/unit-1-get-started/lesson-1-build-your-first-app/1-0-c-introduction-to-android/1-0-c-introduction-to-android.html)
[1.1 Your first Android app](https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/unit-1-get-started/lesson-1-build-your-first-app/1-1-c-your-first-android-app/1-1-c-your-first-android-app.html)
:::

###### tags: `Note/AndroidDeveloperFundamentals`