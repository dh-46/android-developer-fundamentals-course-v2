# Homework: 2.2: Activity lifecycle and state

## Question 1

If you run the homework app before implementing `onSaveInstanceState()`, what happens if you rotate the device? Choose one:

1. The `EditText` no longer contains the text you entered, but the counter is preserved.
2. The counter is reset to 0, and the `EditText` no longer contains the text you entered.
3. **The counter is reset to 0, but the contents of the `EditText` is preserved.**
4. The counter and the contents of the `EditText` are preserved.

## Question 2

What `Activity` lifecycle methods are called when a device-configuration change (such as rotation) occurs? Choose one:

1. Android immediately shuts down your Activity by calling `onStop()`. Your code must restart the `Activity`.
2. Android shuts down your `Activity` by calling `onPause()`, `onStop()`, and `onDestroy()`. Your code must restart the `Activity`.
3. **Android shuts down your `Activity` by calling `onPause()`, `onStop()`, and `onDestroy()`, and then starts it over again, calling `onCreate()`, `onStart()`, and `onResume()`.**
4. Android immediately calls onResume().

## Question 3

When in the `Activity` lifecycle is `onSaveInstanceState()` called? Choose one:

1. **`onSaveInstanceState()` is called before the `onStop()` method.**
2. `onSaveInstanceState()` is called before the `onResume()` method.
3. `onSaveInstanceState()` is called before the `onCreate()` method.
4. `onSaveInstanceState()` is called before the `onDestroy()` method.

## Question 4

Which `Activity` lifecycle methods are best to use for saving data before the `Activity` is finished or destroyed? Choose one:

1. **`onPause()` or `onStop()`**
2. `onResume()` or `onCreate()`
3. `onDestroy()`
4. `onStart()` or `onRestart()`
