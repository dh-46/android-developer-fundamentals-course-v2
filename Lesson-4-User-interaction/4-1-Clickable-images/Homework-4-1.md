# Homework: 4.1: Clickable images

## Question 1

How do you add images to an Android Studio project? Choose one:

1. Drag each image to the layout editor.
2. Copy the image files into your project's `drawable` folder.
3. Drag an `ImageButton` to the layout editor.
4. **Choose New > Image Asset and then choose the image file.**

## Question 2

How do you make an ImageView clickable like a simple Button? Choose one:

1. Add the `android:contentDescription` attribute to the `ImageView` in the layout and use it to call the click handler in the `Activity`.
2. Add the `android:src` attribute to the `ImageView` in the layout and use it to call the click handler in the `Activity`.
3. **Add the `android:onClick` attribute to the `ImageView` in the layout and use it to call the click handler in the `Activity`.**
4. Add the `android:id` attribute to the `ImageView` in the layout and use it to call the click handler in the `Activity`.

## Question 3

Which rule applies to a click handler called from the attribute in the layout? Choose one:

1. The click handler method must include the event listener `View.OnClickListener`, which is an interface in the `View` class .
2. **The click handler method must be `public`, return `void`, and define a `View` as its only parameter.**
3. The click handler must customize the `View.OnClickListener` class and override its click handler to perform some action.
4. The click handler method must be `private` and return a `View`.
