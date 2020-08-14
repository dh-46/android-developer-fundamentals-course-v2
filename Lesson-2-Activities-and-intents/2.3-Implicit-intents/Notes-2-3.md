# 2.3: Implicit intents

## Understanding an implicit Intent

- **don't specify the exact** activity (or other component) to run—instead, you include just enough information in the intent about the task you want to perform.
- If there's only one activity that matches, that activity is launched.
- If **more than one activity matches the intent**, the user is presented with an **app chooser** and picks which app they would like to perform the task.
- An Activity registers itself with the system as being able to handle an implicit `Intent` with *`Intent` filters*

### Intent filter

1. declare in `AndroidManifest.xml`
2. Generally, every app will have an Intent filter for the launcher category.(App's main entry point)

### Intent Actions, Categories, and Data

- `Intent` *action*
    1. the generic action the receiving `Activity` should perform.
    2. The available `Intent` actions are defined as constants in the `Intent` class and begin with the word `ACTION_`
    3. specify the action for an `Intent` in the `Intent` constructor, or with the `setAction()` method.
- `Intent` *category*
    1. provides additional information about the category of component that should handle the `Intent`.
    2. optional, and you can add more than one category to an `Intent`.
    3. defined as constants in the `Intent` class and begin with the word `CATEGORY_`.
    4. add categories to the `Intent` with the `addCategory()` method.
- data *type*
    1. indicates the MIME type of data the `Activity` should operate on.
    2. Usually, the data type is inferred from the URI in the `Intent` data field, but you can also explicitly define the data type with the `setType()` method.

`Intent` actions, categories, and data types are used both by the `Intent` object you create in your sending `Activity`, as well as in the `Intent` filters you define in the `AndroidManifest.xml` file for the receiving `Activity`. The Android system uses this information to match an implicit `Intent` request with an `Activity` or other component that can handle that Intent.

## Sending an implicit Intent

works much the same way as explicit `Intent`.

- use `startActivity()` or `startActivityForResult()`
- Do NOT SPECIFY the specific `Activity` or other component to launch.
- Add an `Intent` action or `Intent` category (or BOTH)
- Resolve the `Intent` with the system before calling.
- Show app chooser for the request. (optional)

```java
Intent sendIntent = new Intent();
sendIntent.setAction(Intent.ACTION_SEND);
sendIntent.putExtra(Intent.EXTRA_TEXT, textMessage);
sendIntent.setType("text/plain");

// if there's no Activity to resolve the intent, the app will crash.
// So you must resolve Activity before sending the intent
if (sendIntent.resolveActivity(getPackageManager()) != null) {
    // not null => at least one app can handle this intent
    startActivity(sendIntent);
} else {
    Log.d(TAG, "No Activity can handle this Intent");
}
```

### Show the app chooser

If there are multiple apps installed that match, the user is presented with an app chooser that lets them select which app they want to use to handle that `Intent`.
User may have a prefered app for a task. They can select an option to always use that app.
However, if we want user to choose with our Intent, You can choose to explicitly show a chooser dialog every time.

```java
// The implicit Intent object
Intent sendIntent = new Intent(Intent.ACTION_SEND);
// Always use string resources for UI text.
String title = getResources().getString(R.string.chooser_title);
// Create the wrapper intent to show the chooser dialog.
Intent chooser = Intent.createChooser(sendIntent, title);
// Resolve the intent before starting the activity
if (sendIntent.resolveActivity(getPackageManager()) != null) {
    startActivity(chooser);
}
```

## Receiving an implicit Intent

- declare one or more `Intent` filters in the `AndroidManifest.xml` file.
- Each `Intent` filter specifies the type of `Intent` it accepts based on the action, data, and category for the `Intent`.
- The way of handling implicit `Intent` is the same as handling explicit `Intent`

> Note: An explicit Intent is always delivered to its target, regardless of any `Intent` filters the component declares. Conversely, if an `Activity` does not include `Intent` filters, it can only be launched with an explicit Intent.

### `Intent` filter

- Define `Intent` filters with **one or more** `<intent-filter>` elements in the `AndroidManifest.xml` file
- specify the type of intent your activity can handle
- `Intent` filter may contains:
    1. `<action>`: `Intent` action that the activity accepts
    2. `<category>`: `Intent` category
    3. `<data>`: The type of data accepted, including the MIME type or other attributes of the data URI (such as scheme, host, port, and path).
- You can specify more than one action, data, or category for the same Intent filter, or have multiple Intent filters per Activity to handle each different kind of Intent. 

#### Ezample 1

```xml
<!-- The main entry Activity for an app -->
<intent-filter>
    <!-- specifies that this is the app's "main" entry point. -->
    <action android:name="android.intent.action.MAIN" />
    <!-- specifies that this activity should be listed in the system's app launcher -->
    <category android:name="android.intent.category.LAUNCHER" />
</intent-filter>
```

#### Example 2

```xml
<activity android:name="ShareActivity">
    <intent-filter>
        <!-- A valid Intent for this filter, must pass these three tags. -->
        <action android:name="android.intent.action.SEND"/>
        <category android:name="android.intent.category.DEFAULT"/>
        <data android:mimeType="text/plain"/>
    </intent-filter>
</activity>
```

The Android system tests an implicit `Intent` against an `Intent` filter by comparing the parts of that `Intent` to each of the three `Intent` filter elements (action, category, and data).
**The `Intent` must pass all three tests or the Android system won't deliver the Intent to the component.**
However, because a component may have multiple `Intent` filters, an `Intent` that does not pass through one of a component's filters might make it through on another filter.

### Actions

- An `Intent` filter can declare zero or more `<action>` elements for the `Intent` action.
- The action is defined in the name attribute, and consists of the string `"android.intent.action."` plus the name of the `Intent` action, minus the `ACTION_` prefix.
    - `ACTION_VIEW` => `android.intent.action.VIEW`
- To get through this filter, the action specified in the incoming `Intent` object **must match at least one of the actions**.
- You must include at least one `Intent` action for an incoming implicit `Intent` to match.

```xml
<intent-filter>
    <action android:name="android.intent.action.EDIT" />
    <action android:name="android.intent.action.VIEW" />
</intent-filter>
```

### Categories

- An `Intent` filter can declare zero or more `<category>` elements for `Intent` categories.
- The category is defined in the name attribute, and consists of the string `"android.intent.category."` plus the name of the `Intent` category, minus the `CATEGORY` prefix.
- **ANY Activity that you want to accept an implicit `Intent` must include the `android.intent.category.DEFAULT` Intent filter.** This category is applied to all implicit `Intent` objects by the Android system. 

```xml
<intent-filter>
    <!-- CATEGORY_DEFAULT -->
    <category android:name="android.intent.category.DEFAULT" />
    <!-- CATEGORY_BROWSABLE -->
    <category android:name="android.intent.category.BROWSABLE" />
</intent-filter>
```

### Data

- An `Intent` filter can declare zero or more `<data>` elements for the URI contained in the `Intent` data.
- As the `Intent` data consists of a `URI` and (optionally) a MIME type, you can create an `Intent` filter for various aspects of that data
    1. URI Scheme
    2. URI Host
    3. URI Path
    4. Mime type

```xml
<!-- 
    this Intent filter matches any data Intent with a URI scheme of http 
    and a MIME type of either "video/mpeg" or "audio/mpeg".  
-->
<intent-filter>
    <data android:mimeType="video/mpeg" android:scheme="http" />
    <data android:mimeType="audio/mpeg" android:scheme="http" />
</intent-filter>
```

## Sharing data with `ShareCompat.IntentBuilder`

- Android provides the `ShareCompat.IntentBuilder` helper class to easily implement sharing in your app.
    - For apps that target Android releases after API 14, you can use the `ShareActionProvider` class for share actions instead of `ShareCompat.IntentBuilder`.
    - `ShareCompat` is part of the V4 support library, and allows you to provide share actions in apps in a backward-compatible fashion.

```java
ShareCompat.IntentBuilder
    .from(this)         // information about the calling activity
    .setType(mimeType)  // mime type for the data
    .setChooserTitle("Share this text with: ") //title for the app chooser
    .setText(txt)       // intent data
    .startChooser();    // send the intent
```

## Managing tasks

The task for your app contains its own stack that contains each `Activity` the user has visited while using your app. As the user navigates around your app, `Activity` instances for that task are pushed and popped from the stack for that task.

Most of the time the user's navigation from one `Activity` to another `Activity` and back again through the stack is straightforward. Depending on the design and navigation of your app there may be complications, especially with an `Activity` started from another app and other tasks.

- `Activity` launch modes, to determine how an `Activity` should be launched.
- Task affinities, which indicate which task a launched `Activity` belongs to.

## Activity launch modes

- indicate how each new Activity should be treated when launched
- Define launch modes for the `Activity` with attributes on the `<activity>` element of the `AndroidManifest.xml` file, or with flags set on the `Intent` that starts that `Activity`.

### Activity attributes

```xml
<activity
   android:name=".SecondActivity"
   android:label="@string/activity2_name"
   android:parentActivityName=".MainActivity"
   android:launchMode="standard">
   <!-- More attributes ... -->
</activity>
```

`android:launchMode` defines a launch mode for an Activity

1. `standard`: (DEFAULT) A new `Activity` is launched and added to the back stack for the current task. An `Activity` can be instantiated multiple times, a single task can have multiple instances of the same `Activity`, and multiple instances can belong to different tasks.
2. `singleTop`: If an instance of an `Activity` exists at the top of the back stack for the current task and an `Intent` request for that `Activity` arrives, Android routes that `Intent` to the existing `Activity` instance rather than creating a new instance. A new `Activity` is still instantiated if there is an existing `Activity` anywhere in the back stack other than the top.
3. `singleTask`: When the `Activity` is launched the system creates a new task for that `Activity`. If another task already exists with an instance of that `Activity`, the system routes the `Intent` to that `Activity` instead.
4. `singleInstance`: Same as single task, except that the system doesn't launch any other `Activity` into the task holding the `Activity` instance. The `Activity` is always the single and only member of its task.

### Intent flags

- options that specify how the activity (or other app component) that receives the intent should handle that intent.
- defined as constants in the `Intent` class and begin with the word `FLAG_`.
- add `Intent` flags to an `Intent` object with `setFlag()` or `addFlag()`
- in conjunction with the `launchMode` attribute or in place of it.
- **`Intent` flags always take precedence over the launch mode in case of conflicts.**

1. `FLAG_ACTIVITY_NEW_TASK`: start the `Activity` in a new task. (== `singleTask` launch mode.)
2. `FLAG_ACTIVITY_SINGLE_TOP`: if the `Activity` to be launched is at the top of the back stack, route the `Intent` to that existing `Activity` instance. Otherwise create a new `Activity` instance. (== `singleTop`)
3. `FLAG_ACTIVITY_CLEAR_TOP`: If an instance of the `Activity` to be launched already exists in the back stack, destroy any other `Activity` on top of it and route the `Intent` to that existing instance. When used in conjunction with `FLAG_ACTIVITY_NEW_TASK`, this flag locates any existing instances of the `Activity` in any task and brings it to the foreground.

### Handle a new Intent

When the Android system routes an `Intent` to an existing `Activity` instance, the system calls the `onNewIntent()` callback method (usually just before the `onResume()` method). Override the `onNewIntent()` method in your class to handle the information from that new `Intent`.

**The `getIntent()` method—to get access to the `Intent` that launched the `Activity`— always retains the original `Intent` that launched the `Activity` instance. You should call `setIntent()` in the `onNewIntent()` method**

```java
@Override
public void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
    // Use the new intent, not the original one
    setIntent(intent);
    // Any call to getIntent() after this returns the new Intent.
}
```

## Task affinities

- indicate which task an `Activity` prefers to belong to when that `Activity` instance is launched.
- By default each `Activity` belongs to the app that launched it.
- **An `Activity` from outside an app launched with an implicit `Intent` belongs to the app that sent the implicit Intent.**
- `android:taskAffinity` attribute to the `<activity>` element in the `AndroidManifest.xml` file defines it
- default task affinity is the package name for the app
- new task name should be unique and different from the package name

```xml
<activity
   android:name=".SecondActivity"
   android:label="@string/activity2_name"
   android:parentActivityName=".MainActivity"
   android:taskAffinity="com.example.android.myapp.newtask">
   <!-- More attributes ... -->
</activity>
```

### Usage 1: singleTask

Task affinities often used with the `singleTask` launch mode or the `FLAG_ACTIVITY_NEW_TASK` Intent flag to place a new `Activity` in its own named task. If the new task already exists, the `Intent` is routed to that task and that affinity. 

### Usage 2: Reparenting

This enables a task to move from the `Activity` in which it was launched to the `Activity` it has an affinity for. To enable task reparenting, add a task affinity attribute to the `<activity>` element and set `android:allowTaskReparenting` to true.

```xml
<activity
   android:name=".SecondActivity"
   android:label="@string/activity2_name"
   android:parentActivityName=".MainActivity"
   android:taskAffinity="com.example.android.myapp.newtask"
   android:allowTaskReparenting="true" >
   <!-- More attributes ... -->
</activity>
```
