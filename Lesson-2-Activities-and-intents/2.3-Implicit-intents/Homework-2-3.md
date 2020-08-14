# Homework: 2.3: Implicit intents

## Question 1

Which constructor method do you use to create an implicit Intent to launch a camera app?

1. new `Intent()`
2. new `Intent(Context context, Class<?> class)`
3. new `Intent(String action, Uri uri)`
4. **new `Intent(String action)`**

## Question 2

When you create an implicit Intent object, which of the following is true?

1. **Don't specify the specific `Activity` or other component to launch.**
2. Add an Intent action or `Intent` categories (or both).
3. Resolve the `Intent` with the system before calling `startActivity()` or `startActivityforResult()`.
4. All of the above.

## Question 3

Which Intent action do you use to take a picture with a camera app?

1. `Intent takePicture = new Intent(Intent.ACTION_VIEW);`
2. `Intent takePicture = new Intent(Intent.ACTION_MAIN);`
3. **`Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);`**
4. `Intent takePicture = new Intent(Intent.ACTION_GET_CONTENT);`
