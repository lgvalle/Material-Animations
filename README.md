# Android Transitions

android.Transition Framework can be used for three things:

1. Animate View elements in transitions between activites (or fragments)
2. Animate shared elements (hero views) in transitions between activities (or fragments)
3. Animate View elements from one activity scene to another.


## 1. Transitions between Activities

Animate **content** (non-hero views) 

![A Start B](https://raw.githubusercontent.com/lgvalle/Material-Animations/master/screenshots/A_startActivity_B.png)


You can define these transitions **declarative** using XML 

> res/transation/activity_explode.xml

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

... or **programatically**.

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
        expl.setDuration(2000);
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
        expl.setDuration(2000);
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
        expl.setDuration(2000);
        getWindow().setEnterTransition(explode);
        
        Fade fade = new Fade();
        fade.setDuration(2000);
        getWindow().setReturnTransition(fade);        
    }

```

We have a nice Explode for going forward and Fade for backward:

![b back a screenshot](https://raw.githubusercontent.com/lgvalle/Material-Animations/master/screenshots/example2.gif)



