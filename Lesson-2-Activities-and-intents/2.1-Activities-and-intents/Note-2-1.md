# Note 2.1: Activities and intents

## Activity

- the major building block of your app's user interface (UI)
- using an `Intent` to communicate from one activity to another.
- represents a single screen in your app

> Each time a new activity starts, the previous activity is stopped, but the system preserves the activity in a stack (the "back stack"). When the user is done with the current activity and presses the Back button, the activity is popped from the stack and destroyed, and the previous activity resumes.

### Activity Lifecycle

#### `onCreate();`

- system calls this method when it creates your `Activity`
- all the essential components of your `Activity` should be initialized here.
- the `onCreate()` method calls `setContentView()` to create the primary layout for the `Activity`.
    - `setContentView()`: creates all the initial views from the specified layout and adds them to your `Activity` (inflating the layout)

#### `onPause()`

- system calls this method as the first indication that the user is leaving your `Activity`
- not always mean that the `Activity` is being destroyed

### Implement the activity's UI

- most common way: using XML file to define
- create new `View` elements in code, and add them into a `ViewGroup`, then add the `ViewGroup` into root `ViewGroup` in XML file.

### Activity in AndroidManifest.xml

- Each Activity in your app **must** be declared in the `AndroidManifest.xml` file with the `<activity>` element, inside the `<application>` section.

```xml
<activity android:name=".MainActivity" >
   <intent-filter>
      <action android:name="android.intent.action.MAIN" />
      <category android:name="android.intent.category.LAUNCHER" />
   </intent-filter>
</activity>
```

- `intent-filter` define what kind of `Intent` yout `Activity` will accept
    - include at least one `action`, and can also include a `<category>` and optional `<data>`
    - The `MainActivity` for your app needs an `Intent` filter that defines the "main" action and the "launcher" category so that the system can launch your app.
- Each `Activity` in your app can also declare `Intent` filters, but only your `MainActivity` should include the "main" action

## Intents

- Each activity is started or activated with an `Intent`
- is a **message object** that makes a request to the Android runtime to start an activity or other app component in your app or in some other app.
- also be used to start services or broadcast receivers.

### Explicit intent

> You **specify the receiving activity (or other component)** using the activity's fully qualified class name. You use explicit intents to start components in your own app (for example, to move between screens in the UI), because you already know the package and class name of that component.

#### Key fields for Explicit intent

1. The `Activity` class (for an explicit Intent).
    - `constructor`, `setComponent()`, `setComponentName()`, or `setClassName()` methods to specify the class. 
2. The `Intent` data.
    - The Intent data field contains a reference to the data you want the receiving Activity to operate on as a `Uri` object.
3. `Intent` extras.
    - These are key-value pairs that carry information the receiving Activity requires to accomplish the requested action.
4. `Intent` flags.
    - These are additional bits of metadata, defined by the Intent class. The flags may instruct the Android system how to launch an Activity or how to treat it after it's launched.

### Implicit intent

> You **do not specify a specific activity or other component** to receive the intent. Instead, you declare a general action to perform, and the Android system matches your request to an activity or other component that can handle the requested action.

- Intent action
- category

### Passing data

1. Intent data
    - hold only one piece of information: a `URI` representing the location of the data you want to operate on.
    - When you only have one piece of information you need to send to the started `Activity`.
    - When that information is a data location that can be represented by a `URI`.
2. Intent extra
    - stored in a `Bundle` object as key and value pairs.
    - If you want to pass more than one piece of information to the started Activity.
    - If any of the information you want to pass is not expressible by a URI.

#### `setData()` URI

```java
// A web page URL
messageIntent.setData(Uri.parse("http://www.google.com")); 
// a Sample file URI
messageIntent.setData(Uri.fromFile(new File("/sdcard/sample.jpg")));
// A sample content: URI for your app's data model
messageIntent.setData(Uri.parse("content://mysample.provider/data")); 
// Custom URI 
messageIntent.setData(Uri.parse("custom:" + dataID + buttonId));
```

- data field can **only contain a single URI;**
- Use Intent extras to include additional information (including URIs.)

#### Extras examples

```java
// Custom keys convention: 
// 1. variable name start with EXTRA_
// 2. value includes package names
public final static String EXTRA_MESSAGE = "com.example.mysampleapp.MESSAGE";
public final static String EXTRA_POSITION_X = "com.example.mysampleapp.X";
public final static String EXTRA_POSITION_Y = "com.example.mysampleapp.Y";
```

```java
// put extra with different kinds of data
messageIntent.putExtra(EXTRA_MESSAGE, "this is my message");
messageIntent.putExtra(EXTRA_POSITION_X, 100);
messageIntent.putExtra(EXTRA_POSITION_Y, 500);
```

```java
// put extras with bundle
Bundle extras = new Bundle();
extras.putString(EXTRA_MESSAGE, "this is my message");
extras.putInt(EXTRA_POSITION_X, 100);
extras.putInt(EXTRA_POSITION_Y, 500);
messageIntent.putExtras(extras);
```

## Getting data back from an Activity

1. launch activity by `startActivityForResult()` with `REQUEST_CODE`
2. extract Intent data in `onActivityResult()`;

```java
// by convention, defines request code as static integer
// and include word 'REQUEST'
public static final int PHOTO_REQUEST = 1;
public static final int PHOTO_PICK_REQUEST = 2;
public static final int TEXT_REQUEST = 3;

// ...

public void onActivityResult(int requestCode, int resultCode,  Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == TEXT_REQUEST) {
        if (resultCode == RESULT_OK) {
            String reply = data.getStringExtra(SecondActivity.EXTRA_RETURN_MESSAGE);
            // process data
        }
    }
}
```

## Set return data for Activity

```java
public final static String EXTRA_RETURN_MESSAGE = 
                                  "com.example.mysampleapp.RETURN_MESSAGE";
// ...
replyIntent.putExtra(EXTRA_RETURN_MESSAGE, mMessage);
// RESULT_OK: The request was successful.
// RESULT_CANCELED: The user canceled the operation.
// RESULT_FIRST_USER: For defining your own result codes.
setResult(RESULT_OK,replyIntent);
// call finish() to close the Activity and resume the originating Activity:
finish(); 
```

## Activity navigation

Android system supports two different forms of navigation strategies:

1. Back (temporal) navigation, provided by the device Back button, and the back stack.
2. Up (ancestral) navigation, provided by you as an option in the app bar.

### Back navigation, tasks, and the back stack

- allows your users to return to the previous Activity by tapping the device back button
- also called temporal navigation
- navigates the history of recently viewed screens, in reverse chronological order.

#### Back stack

- the set of each `Activity` that the user has visited and that can be returned to by the user with the back button.
- previous Activity is stopped but is still available in the back stack.
- "last in, first out" mechanism
    1. when the user is done with the current Activity and presses the Back button, that Activity is popped from the stack (and destroyed) and the previous Activity resumes.
- Each time the user presses the Back button, each `Activity` in the stack is popped off to reveal the previous one, until the user returns to the Home screen.

#### Task

- Android provides a back stack for each *task*.
- A *task* is an organizing concept for each `Activity` the user interacts with when performing an operation, whether they are inside your app or across multiple apps.
- Most tasks start from the Android home screen, and tapping an app icon starts a task (and a new back stack) for that app.
- Navigating with the Back button returns only to the `Activity` in the current task
- Android enables the user to navigate between tasks with the overview or recent tasks screen

### Up Navigation

- sometimes referred to as ancestral or logical navigation
- used to **navigate within an app** based on the explicit hierarchical relationships between screens.
- From child Activity to parent Activity. (E.g.: PaymentActivity back to MainActivity)
- The behavior of the Up button is defined by you in each Activity based on how you design your app's navigation.
- is optional in app design
- this method will always recreate the parent Activity. If you want to reuse the parent Activity (onResume), you should set parent Activity's launch mode to "singleTop"

#### Implement Up navigation with a parent Activity

- If one `Activity` is a child of another `Activity` in your app's `Activity` hierarchy, specify the parent of that other `Activity` in the `AndroidManifest.xml` file.

```xml
<application
    android:allowBackup="true"
    android:icon="@mipmap/ic_launcher"
    android:label="@string/app_name"
    android:roundIcon="@mipmap/ic_launcher_round"
    android:supportsRtl="true"
    android:theme="@style/AppTheme">
    <!-- The main activity (it has no parent activity) -->
    <activity android:name=".MainActivity">
       <intent-filter>
            <action android:name="android.intent.action.MAIN" />

            <category android:name="android.intent.category.LAUNCHER" />
       </intent-filter>
    </activity>
    <!-- The child activity) -->
    <activity android:name=".SecondActivity"
       android:label = "Second Activity"
       android:parentActivityName=".MainActivity">
       <!-- 
           To support older versions of Android (Android 4.1 (API level 16)), 
           you shoud provide this meta-data 
        -->
       <meta-data
          android:name="android.support.PARENT_ACTIVITY"
          android:value="com.example.android.twoactivities.MainActivity" />
       </activity>
</application>
```
