# Homework: 2.1: Activities and intents

## Question 1

What changes are made when you add a second Activity to your app by choosing File > New > Activity and an Activity template? Choose one:

1. The second Activity is added as a Java class. You still need to add the XML layout file.
2. The second Activity XML layout file is created and a Java class added. You still need to define the class signature.
3. **The second Activity is added as a Java class, the XML layout file is created, and the AndroidManifest.xml file is changed to declare a second Activity.**
4. The second Activity XML layout file is created, and the AndroidManifest.xml file is changed to declare a second Activity.

## Question 2

What happens if you remove the `android:parentActivityName` and the `<meta-data>` elements from the second Activity declaration in the `AndroidManifest.xml` file? Choose one:

1. The second Activity no longer appears when you try to start it with an explicit Intent.
2. The second Activity XML layout file is deleted.
3. The Back button no longer works in the second Activity to send the user back to the main Activity.
4. **The Up button in the app bar no longer appears in the second Activity to send the user back to the parent Activity.**

Question 3

Which constructor method do you use to create a new explicit Intent? Choose one:

1. new Intent()
2. **new Intent(Context context, Class<?> class)**
3. new Intent(String action, Uri uri)
4. new Intent(String action)

Question 4

In the HelloToast app homework, how do you add the current value of the count to the Intent? Choose one:

1. As the Intent data
2. As the Intent TEXT_REQUEST
3. As an Intent action
4. **As an Intent extra**

Question 5

In the HelloToast app homework, how do you display the current count in the second "Hello" Activity? Choose one:

1. Get the Intent that the Activity was launched with.
2. Get the current count value out of the Intent.
3. Update the TextView for the count.
4. **All of the above.**
