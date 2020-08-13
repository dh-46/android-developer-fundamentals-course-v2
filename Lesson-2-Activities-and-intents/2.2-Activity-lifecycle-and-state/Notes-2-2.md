# Notes: 2.2: Activity lifecycle and state

## Activity Lifecycle

- Set of states an `Activity` can be in during its entire lifetime.
- When an Activity transitions into and out of the different lifecycle states as it runs, the Android system calls several **lifecycle callback** methods at each stage.
- the lifecycle states (and callbacks) are per `Activity`, not per `app`.

### Activity created: the `onCreate()` method

```java
@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // The activity is being created.
}
```

- Your `Activity` enters into the created state when it is started for the first time.
- The `onCreate()` method is the only required callback you must implement in your `Activity` class.
- In your `onCreate()` method you perform basic app startup logic that should **happen only once**.
- the `Activity` remains in the created state only as long as it takes to run `onCreate()`, and then the `Activity` moves to the started state.

### Activity started: the `onStart()` method

```java
@Override
protected void onStart() {
    super.onStart();
    // The activity is about to become visible.
}
```

- The `onStart()` method is also called if a stopped `Activity` returns to the foreground.
- While `onCreate()` is called only once when the Activity is created, **the `onStart()` method may be called many times during the lifecycle** of the Activity as the user navigates around your app.
- visible on the screen,but the user cannot interact with it until `onResume()` is called.
- the `Activity` is running, and the `Activity` is in the foreground.
- Typically you implement `onStart()` in your Activity as **a counterpart to the `onStop()` method.**
    - Register hardware resources, such as GPS/Sensors, and unregister at `onStop()`
- Started, like created, is a transient state.

### Activity resumed/running: the onResume() method

```java
@Override
protected void onResume() {
    super.onResume();
    // The activity has become visible (it is now "resumed").
}
```

- visible on screen, and ready to use.
- The `onResume()` method **may also be called multiple times**, each time the app comes back from the paused state.
- typically only implement `onResume()` as a counterpart to `onPause()`.

### Activity paused: the onPause() method

```java
@Override
protected void onPause() {
    super.onPause();
    // Another activity is taking focus 
    // (this activity is about to be "paused").
}
```

- occur in several situations:
    1. The `Activity` is going into the background, but has not yet been fully stopped. (the first indication that the user is leaving)
    2. The `Activity` is only partially visible on the screen, because a dialog or other transparent `Activity` is overlaid on top of it.
    3. **In multi-window or split screen mode (API 24), the Activity is displayed on the screen, but some other Activity has the user focus.**
- can stop animation or video playback, release any hardware-intensive resources, or commit unsaved `Activity` changes (such as a draft email).
- should execute quickly
    - Don't use `onPause()` for CPU-intensive operations
- Note that in multi-window mode (API 24), your paused `Activity` may still fully visible on the screen.
    - use the `inMultiWindowMode()` method in the `Activity` class to test whether your app is running in multi-window mode.

### Activity stopped: the onStop() method

```java
@Override
protected void onStop() {
    super.onStop();
    // The activity is no longer visible (it is now "stopped")
}
```

> The Android system retains the activity instance in the back stack, and if the user returns to the activity, the system restarts it. If resources are low, the system might kill a stopped activity altogether. 

- usually because the user started another activity or returned to the home screen.
- Implement the `onStop()` method to save persistent data and release resources that you didn't already release in `onPause()`, including operations that may have been too heavyweight for `onPause()`. 

### Activity destroyed: the onDestroy() method

```java
@Override
protected void onDestroy() {
    super.onDestroy();
    // The activity is about to be destroyed.
}
```

- shut down completely, and the Activity instance is reclaimed by the system. Here are the cases:
    1. call `finish()` in your `Activity` to manually shut it down.
    2. user navigates back to the previous `Activity`
    3. system reclaims any stopped `Activity` to free more resources.
    4. A device configuration change occurs.
- fully clean up in this method
- there are situations where the system will simply kill the hosting process for the `Activity` **without calling this method** (or any others), so you **should not** rely on `onDestroy()` to save any required data or `Activity` state. (Use `onPause()` or `onStop()` instead.)

### Activity restarted: the onRestart() method

```java
@Override
protected void onRestart() {
    super.onRestart();
    // The activity is about to be restarted.
}
```

- a transient state, only occurs if a stopped Activity is started again.
- is called between `onStop()` and `onStart()`

## Configuration changes and Activity state

### Configuration changes

- *Configuration change* will destroy and recreate `Activity`.
- invalidate the current layout or other resources in your Activity.
- common cases are
    1. screen rotation
    2. a change in locale (the user chooses a different system language)
    3. user enters multi-window mode (Android 7)
        - In multi-window mode, if you have configured your app to be resizeable, Android recreates the Activity to use a layout definition for the new, smaller size.
- When a configuration change occurs, the Android system shuts down your activity, calling
    1. `onPause()`
    2. `onStop()`
    3. `onDestroy()`
- Then the system restarts the activity from the beginning, calling
    1. `onCreate()`
    2. `onStart()`
    3. `onResume()`

### Activity instance state

- When an `Activity` is paused or stopped, the state of the `Activity` is retained because that `Activity` is still held in memory.
- When an `Activity` is recreated, the state of the `Activity` and any user progress in that `Activity` is lost, with these exceptions:
    1. Some Activity state information is automatically saved and restored by default.
        - `View` in layout with `ID` (E.g. `EditText`)
    2. The `Intent` that was used to start the `Activity`, and the information stored in the data or extras for that `Intent`, remains available to that `Activity` when it is recreated.
- The `Activity` state is stored as a set of key/value pairs in a `Bundle` object called the `Activity` *instance state*.
- it was saved before `Activity` stopped and passed to the new `Activity` to restore.
- add your own data by overriding `onSaveInstanceState()` callback, and put key/value data into `Bundle`.
- `Bundle` can be handled in `onCreate()` or `onRestoreInstanceState()` to restore the data.

> **NOTE:** Note: The Activity instance state is particular to a specific instance of an Activity, running in a single task. If the user force-quits the app or reboots the device, or if the Android system shuts down the app process to preserve memory, the Activity instance state is lost. To keep state changes across app instances and device reboots, you need to write that data to shared preferences. You learn more about shared preferences in another chapter.

### Saving Activity instance state

```java
@Override
public void onSaveInstanceState(Bundle savedInstanceState) {
    super.onSaveInstanceState(savedInstanceState);
    // Save the user's current game state
    savedInstanceState.putInt("score", mCurrentScore);
    savedInstanceState.putInt("level", mCurrentLevel);
}
```

- NOT a lifecycle callback method, but it's called when user is leaving your `Activity`(sometime before `onStop()`)
- `Bundle` object is the instance state `Bundle` to which you will add your own Activity state information.
- Don't forget to call through to the superclass, to make sure the state of the `View` hierarchy is also saved to the `Bundle`.

### Restoring Activity instance state

Two places to restore data:

1. The `onCreate()` callback method, which is called with the instance state `Bundle` when the `Activity` is created.
2. The `onRestoreInstanceState()` callback, which is called after `onStart()` after the `Activity` is created.

Most of the time the better place to restore the `Activity` state is `onCreate()`, to ensure that your UI, including the state, is available as soon as possible.

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    // Always call the superclass first
    super.onCreate(savedInstanceState); 

    // Check if recreating a previously destroyed instance.
    if (savedInstanceState != null) {
        // Restore value of members from saved state.
        mCurrentScore = savedInstanceState.getInt("score");
        mCurrentLevel = savedInstanceState.getInt("level");
    } else {
        // Initialize members with default values for a new instance.
        // ...
    }
    // ... Rest of code
}
```
