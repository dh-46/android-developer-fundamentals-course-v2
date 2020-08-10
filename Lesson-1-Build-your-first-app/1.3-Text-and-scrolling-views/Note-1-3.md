# 1.3: Text and scrolling views

## TextView

### Attributes

- `android:autoLink`: Controls whether links such as URLs and email addresses are automatically found and converted to clickable (touchable) links.
    - `none`: Match no patterns (default).
    - `web`: Match web URLs.
    - `email`: Match email addresses.
    - `phone`: Match phone numbers.
    - `map`: Match map addresses.
    - `all`: Match all patterns (equivalent to web|email|phone|map).

## ScrollView

- `ScrollView`, `HorizontalScrollView`.
- ScrollView is a subclass of `FrameLayout`
- place only one `View` as a child within it
- A good choice for a `View` within a `ScrollView` is a LinearLayout that is arranged in a vertical orientation.

### Performance

- All of the contents of a ScrollView (such as a ViewGroup with View elements) occupy memory and the view hierarchy **even if portions are not displayed on screen.**
- useful for smoothly scrolling pages of free-form text
- a `ScrollView` with a `ViewGroup` with `View` elements can use up a lot of memory
- Nested `LinearLayout` will lead to deep view hierarchy and slow down performance
    - Nested `LinearLayout` that uses `wrap_content`, its child `View` needs to be measure twice.
    - Use `RelativeLayout` or `GridLayout` to improve performance (flatter hierarchy)
- NOT RECOMMEND to use Images inside ScrollView, try `RecyclerView` instead.
