# Android Transitions

android.Transition Framework can be used for three main things:

1. Animate View elements in transitions between activites (or fragments)
2. Animate shared elements (hero views) in transitions between activities (or fragments)
3. Animate View elements from one activity scene to another.


## 1. Transitions between Activities

Animate existing activity layout **content** (non-hero views) 

![A Start B](https://raw.githubusercontent.com/lgvalle/Material-Animations/master/screenshots/A_startActivity_B.png)

You can define these transitions **declarative** using XML or **programatically**.

### Declarative

> res/transition/activity_explode.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<transitionSet xmlns:android="http://schemas.android.com/apk/res/android">
    <explode android:duration="2000"/>
</transitionSet>
```
> res/values/style.xml

```xml
<item name="android:windowEnterTransition">@transition/activity_explode.xml</item>
```

To inflate specific xml defined transition: 

> MainActivity.java
 
```java
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupWindowAnimations();
    }

    private void setupWindowAnimations() {
        Explode explode = TransitionInflater.from(this).inflateTransition(R.transition.activity_explode);
        explode.setDuration(2000);
        getWindow().setExitTransition(explode);
    }

```

### Programatically 

> MainActivity.java

```java
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupWindowAnimations();
    }

    private void setupWindowAnimations() {
        Explode explode = new Explode();
        explode.setDuration(2000);
        getWindow().setExitTransition(explode);
    }
```

> DetailActivity.java

```java
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupWindowAnimations();
    }

    private void setupWindowAnimations() {
        Explode explode = new Explode();
        explode.setDuration(2000);
        getWindow().setEnterTransition(explode);
    }

```

### Any of those produce this result:

![A start B exmaple] (https://raw.githubusercontent.com/lgvalle/Material-Animations/master/screenshots/example1.gif)


### What is happening step by step:

1. Activity A starts Activity B

2. Transition Framework finds A Exit Transition (explode) and apply it to all visible views.
3. Transition Framework finds B Enter Transition (explode) and apply it to all visible views.
4. On Back Pressed Transition Framework executes Enter and Exit reverse animations respectively (because it cannot find `returnTransition` and `reenterTransition`) 

### ReturnTransition & ReenterTransition

These two methods define the reverse animations for `enter` and `exit` respectively.

![b back a](https://raw.githubusercontent.com/lgvalle/Material-Animations/master/screenshots/B_back_A.png)

In our example, if we do:
 
> MainActivity.java

```java
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupWindowAnimations();
    }

    private void setupWindowAnimations() {
        Explode explode = new Explode();
        explode.setDuration(2000);
        getWindow().setExitTransition(explode);
        
        Fade fade = new Fade();
        fade.setDuration(2000);
        getWindow().setReenterTransition(fade);
          
    }
```

> DetailActivity.java

```java
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupWindowAnimations();
    }

    private void setupWindowAnimations() {
        Explode explode = new Explode();
        explode.setDuration(2000);
        getWindow().setEnterTransition(explode);
        
        Fade fade = new Fade();
        fade.setDuration(2000);
        getWindow().setReturnTransition(fade);        
    }

```

We have a nice Explode for going forward and Fade for backward:

![b back a screenshot](https://raw.githubusercontent.com/lgvalle/Material-Animations/master/screenshots/example2.gif)


## 2. Shared elements between Activities

The idea behind this is having two different views in two different layouts and link them somehow with an animation.

Transition framework will then do _whatever animations it consider necessary_ to show the user a transition from one view to another.

Keep this always in mind: the view **is not really moving** from one layout to another. They are two independent views.


![A Start B with shared](https://raw.githubusercontent.com/lgvalle/Material-Animations/master/screenshots/a_b_shared_element.png)

As you can see there are two views with ids 'smallSquare' and 'bigSquare'. But they have the **same** 'transitionName'. 

This way the Transition Framework knows it needs to create an animation from one view to the other.


> MainActivity.java

```java

squareBlue.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i = new Intent(MainActivity.this, DetailActivity2.class);

        View sharedView = squareBlue;
        String transitionName = getString(R.string.square_blue_name);

        ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, sharedView, transitionName);
        startActivity(i, transitionActivityOptions.toBundle());
    }
});

```

> layout/main_activity.xml

```xml
<View
        android:layout_margin="10dp"
        android:id="@+id/square_blue"
        android:layout_width="50dp"
        android:background="@android:color/holo_blue_light"
        android:transitionName="@string/square_blue_name"
        android:layout_height="50dp"/>

```

> layou/details_activity2.xml

```xml
<View
        android:layout_width="150dp"
        android:id="@+id/big_square_blue"
        android:layout_margin="10dp"
        android:transitionName="@string/square_blue_name"
        android:layout_centerInParent="true"
        android:background="@android:color/holo_blue_light"
        android:layout_height="150dp" />
```

Just that code will produce this beautiful transition animation:

![a to b with shared element](https://raw.githubusercontent.com/lgvalle/Material-Animations/master/screenshots/transition-shared-elements.gif)

As you can see, Transition framework is creating and executing an animation to create the illusion that the view is moving and changing shape.

To proof the blue square view is not really _moving_ we can do this quick exercise: change transitioName in DetailsActivity from Big Blue Square to the Title Text above it.

```xml
<TextView
        android:layout_width="wrap_content"
        android:text="Activity Detail 2"
        style="@style/Base.TextAppearance.AppCompat.Large"
        android:layout_centerHorizontal="true"
        android:transitionName="@string/square_blue_name"
        android:layout_above="@+id/big_square_blue"
        android:layout_height="wrap_content" />
```

If we now execute the app we have the same behaviour but targeting a different view:

![a to b with shared element - 2](https://raw.githubusercontent.com/lgvalle/Material-Animations/master/screenshots/transition-shared-elements2.gif)        


## 3. Animate view layout elements

Transition framework can also be used to animate element changes within current activity layout. 

Transitions happen between scenes. An scene defines a static state of our UI. You can do complex things regarding _scenes_ but I want to keep this example as **simple as possible**. 

If you want to know more about scenes I recomend you check [this video by Chet Hasse] 
(https://www.youtube.com/watch?v=S3H7nJ4QaD8)

In this example I'm going to use the easier way to animate layout changes inside an Activity layout:

```java
TransitionManager.beginDelayedTransition(sceneRoot);
```

With just this line of code we are telling the framework we are going to perform some UI changes that it will need to animate.

After that we made the changes on our UI elements:

```java
setViewWidth(squareRed, 500);
setViewWidth(squareBlue, 500);
setViewWidth(squareGreen, 500);
setViewWidth(squareYellow, 500);
```

This will change those views width attribute to make it larger. That will trigger a `layoutMeasure`. At that point the Transition framework will record start and ending values and will create an animation to transition from one to another.

```java
 squareGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(sceneRoot);
                setViewWidth(squareRed, 500);
                setViewWidth(squareBlue, 500);
                setViewWidth(squareGreen, 500);
                setViewWidth(squareYellow, 500);
            }
        });
    }

    private void setViewWidth(View view, int x) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = x;
        view.setLayoutParams(params);
    }
```
    
![a to b with shared element - 2](https://raw.githubusercontent.com/lgvalle/Material-Animations/master/screenshots/scene-transition.gif)   


## 4. Shared elements + Circular Reveal
Circular Reveal is just an animation to show or hide a group of UI elements. It is available since API 21 in `ViewAnimationUtils` class. 

In this example I'm going to demostrate how can you make use of Shared Element Transition and Circular Reveal Animation to smoothly switch UI context.

![shared+circularreveal](https://raw.githubusercontent.com/lgvalle/Material-Animations/master/screenshots/example3.gif)   

### Enter Animation
What is happening step by step is:

* Shared orange box is transitioning from `MainActivity` to `DetailsActivity`.
* `DetailsActivity` background viewgroup visibility starts as `INVISIBLE`.

```xml
 <RelativeLayout
        android:layout_width="match_parent"
        android:id="@+id/backgroundViewGroup"
        android:visibility="invisible"
        ...
```        
* After `SharedElementEnterTransition` ends a `CircularReveal` animation takes place making the background viewgroup visible.

```java
        Transition enterTransition = getWindow().getSharedElementEnterTransition();
        enterTransition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {}

            @Override
            public void onTransitionEnd(Transition transition) {
                animateRevealShow(bgViewGroup);
            }

            @Override
            public void onTransitionCancel(Transition transition) {}

            @Override
            public void onTransitionPause(Transition transition) {}

            @Override
            public void onTransitionResume(Transition transition) {}
        });
```

### Exit Animation
On exit transition steps are:

* `SharedElementReturnTransition` is delayed 1 second.

```java
        Transition sharedElementReturnTransition = getWindow().getSharedElementReturnTransition();
        sharedElementReturnTransition.setStartDelay(ANIM_DURATION);
```

* `ReturnTransition` duration is setted to 1 second. Have in mind this are two **different** transitions.

```java
        Transition returnTransition = getWindow().getReturnTransition();
        returnTransition.setDuration(ANIM_DURATION);
```


* On `ReturnTransition` start a `CircularReveal` animation takes place hiding the background viewgroup.

```java
        returnTransition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                animateRevealHide(bgViewGroup);
            }

            @Override
            public void onTransitionEnd(Transition transition) {}

            @Override
            public void onTransitionCancel(Transition transition) {}

            @Override
            public void onTransitionPause(Transition transition) {}

            @Override
            public void onTransitionResume(Transition transition) {}
        });
```


* After 1 second, `CircularReveal` has finished and `SharedElementReturnTransition` gets executed producing orange box animation.



## More information

  * Alex Lockwood posts about transitions in Lollipop. A great in deep into this topic: [http://www.androiddesignpatterns.com/2014/12/activity-fragment-transitions-in-android-lollipop-part1.html](http://www.androiddesignpatterns.com/2014/12/activity-fragment-transitions-in-android-lollipop-part1.html)
  * Very complete repository with examples by Saul Molinero: [https://github.com/saulmm/Android-Material-Examples](https://github.com/saulmm/Android-Material-Examples)
  * Chet Hasse video explaining Transition framework: [https://www.youtube.com/watch?v=S3H7nJ4QaD8](https://www.youtube.com/watch?v=S3H7nJ4QaD8)


## LICENSE

	The MIT License (MIT)
	Copyright (c) 2015 Luis G. Valle

	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:

	The above copyright notice and this permission notice shall be included in all
	copies or substantial portions of the Software.

	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
	SOFTWARE.
