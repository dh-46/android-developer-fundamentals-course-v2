# 1.2: Layouts and resources for the UI

The UI consists of a hierarchy of objects called views — every element of the screen is a **View**.

## View

- the basic building block for all UI components
- the base class for classes that provide interactive UI components
- view's location is expressed as a pair of left and top coordinates
- has height and width
- the dimension unit is dp, **density-independent pixel**.
- Android provides some common predefined views, such as `TextView`, `EditText`, `Button`, etc.

## ViewGroup

- views can be grouped inside it.
- acts as a container.
    - child-parent relationship
    - child can be a view or another viewgrouop

### Layout's ViewGroup groups

- organized in a hierarchy
- root of this hierarchy is a ViewGroup that contains the layout of the entire screen
- Some ViewGroup groups are designated as layouts
    - `ConstraintLayout`
    - `LinearLayout`
    - `RelativeLayout`
    - `TableLayout`: rows and columns
    - `FrameLayout`: contains child views in a stack.
    - `GridLayout`

## ConstraintLayout

- A constraint is a connection or alignment to another UI element, to the parent layout, or to an invisible guideline.

### Using a baseline constraint

- can align one UI element that contains text, such as a `TextView` or `Button`

```java
<TextView
    // ...
    app:layout_constraintBaseline_toBaselineOf="@+id/tv_123"/>
```

## Layout variants

### LandScape

To create a variant of the layout strictly for the horizontal orientation, leaving the vertical orientation layout alone: click the Orientation in Editor button and select Create Landscape Variation. A new editor window opens with the `land/activity_main.xml` tab showing the layout for the landscape (horizontal) orientation.

### Large Layout (Tablet Sized)

To create a layout variant for tablet-sized screens, click the Orientation in Editor button and select Create layout x-large Variation. A new editor window opens with the `xlarge/activity_main.xml` tab showing the layout for a tablet-sized device.

## XML attributes (view properties)

Views have properties that define where a view appears on the screen, its size, how the view relates to other views, and how it responds to user input.

### Every View and ViewGroup supports its own variety of XML attributes:

- Some attributes are specific to a `View` subclass. For example, the `TextView` subclass supports the textSize attribute. Any elements that extend the `TextView` subclass inherit these subclass-specific attributes.
- Some attributes are common to all `View` elements, because they are inherited from the root `View` class. The `android:id attribute` is one example.

## Identifying a View

To uniquely identify a `View` and reference it from your code, you must give it an `id`. The `android:id` attribute lets you specify a unique `id`—a resource identifier for a `View`.

```xml
android:id="@+id/button_count"
//  plus (+) symbol indicates that you're creating a new symbol.
```

## Style-related attributes

You specify style attributes to customize the appearance of a `View`. 
A `View` that doesn't have style attributes, such as `android:textColor`, `android:textSize`, and `android:background`, takes on the styles defined in the app's theme.

## Resource files

Resource files are a way of separating static values from code so that you don't have to change the code itself to change the values.

### `res`folder

- `drawable`: For images and icons
- `layout`: For layout resource files
- `menu`: For menu items
- `mipmap`: For pre-calculated, optimized collections of app icons used by the Launcher
- `values`: For colors, dimensions, strings, and styles (theme attributes)

### syntax to reference a resource

`@package_name:resource_type/resource_name`

- `package_name` is the name of the package in which the resource is located. The package name is **not required when you reference resources that are stored in the res folder of your project**, because these resources are from the same package.
- `resource_type` is the R subclass for the resource type. See Resource Types for more about the resource types and how to reference them.
- `resource_name` is either the resource filename without the extension, or the `android:name` attribute value in the XML element.

### Values resource files

Keeping values such as strings and colors in separate resource files makes it easier to manage them, especially if you use them more than once in your layouts.

#### Strings

- Extracting text into Strings resources makes it easier on the process of app's translation.

#### Colors

- hexadecimal color value
- Using the base colors for other UI elements creates a uniform UI.

#### Dimensions

To make dimensions easier to manage, you should separate the dimensions from your code, especially if you need to adjust your layout for devices with different screen densities.

> **Density-independent pixels (dp)** are independent of screen resolution. For example, 10px (10 fixed pixels) look a lot smaller on a higher resolution screen, but Android scales 10dp (10 device-independent pixels) to look right on different resolution screens. Text sizes can also be set to look right on different resolution screens using scaled-pixel (sp) sizes.

#### Styles

- specifies common attributes such as height, padding, font color, font size, background color.


## Responding to View clicks: _click event_

- Write a Java method that performs the specific action you want the app to do when this event occurs. This method is typically referred to as an _event handler_.
- Associate this event-handler method to the `View`, so that the method executes when the event occurs.

### `onClick` attribute

use the `android:onClick` attribute in the XML layout.

## Updating a View

- Obtain the `View` object by using `findViewById()` method in `Activity`