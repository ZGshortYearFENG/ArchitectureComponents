# Android Architecture Component
Android体系结构组件是一组库，可帮助您设计健壮，可测试和可维护的应用程序。从用于管理UI组件生命周期和处理数据持久性的类开始。 

了解将健壮的应用程序与应用程序架构指南放在一起的基础知识。 管理您应用的生命周期。

新的可感知生命周期的组件可帮助您管理活动和碎片化生命周期。保留配置更改，避免内存泄漏，并轻松将数据加载到UI中。 

使用LiveData构建数据对象，这些数据对象在基础数据库更改时通知视图。 

ViewModel存储与UI相关的数据，这些数据不会在应用程序旋转时被破坏。 

Room是一个SQLite对象映射库。使用它来避免样板代码，并轻松地将SQLite表数据转换为Java对象。 Room提供了SQLite语句的编译时检查，并且可以返回RxJava，Flowable和LiveData可观察对象。

Overview基本介绍了Architecture Component比较好用的几个组件 lifecycle livedata viewmodel room

## View Binding
视图绑定是一项功能，使您可以更轻松地编写与视图交互的代码。在模块中启用视图绑定后，它将为该模块中存在的每个XML布局文件生成一个绑定类。绑定类的实例包含对在相应布局中具有ID的所有视图的直接引用。 在大多数情况下，视图绑定会替换findViewById。

需要as升级到3.6版本

### 与使用findViewById相比，视图绑定具有重要的优势： 

Null safety：由于视图绑定会创建对视图的直接引用，因此不会因无效的视图ID而导致空指针异常的风险。此外，当视图仅在布局的某些配置中存在时，在绑定类中包含其引用的字段将用@Nullable标记。 

Type safety：每个绑定类中的字段具有与其在XML文件中引用的视图匹配的类型。这意味着没有类强制转换异常的风险。 这些差异意味着布局和代码之间的不兼容性将导致编译在编译时而不是在运行时失败。


### 与DataBinding的区别 

ViewBinding和DataBinding都生成可用于直接引用视图的绑定类。但是，有明显的区别： 

DataBinding仅处理使用<layout>标记创建的数据绑定布局。 

ViewBinding不支持布局变量或布局表达式，因此不能用于将布局与XML数据绑定。

## Data Binding
数据绑定库是一个支持库，使用该库，您可以使用声明性格式而非编程方式将布局中的UI组件绑定到应用程序中的数据源。 

通过在布局文件中绑定组件，您可以删除活动中的许多UI框架调用，从而使它们更易于维护。这也可以提高应用程序的性能，并有助于防止内存泄漏和空指针异常。

内存泄漏：？？

空指针异常：？？

应该是没有上面两个的用处的，就一个数据绑定跟findviewbyid

### 基本使用

as支持databinding：

语法高亮 

标记表达语言语法错误 

XML代码完成 

安装插件 databindingconverter

build.gradle中设置
```groovy
dataBinding {
    enabled = true
}
```
通过插件装换xml到layout形式
在MainActivity中实例化DataBinding
```
//        var databinding = ActivityMainBinding.inflate(layoutInflater)
var databinding: ActivityMainBinding =
    DataBindingUtil.setContentView(this, R.layout.activity_main)
```

### layouts binding 表达式

### 1/ 空合并运算符
````xml
android:text="@{user.displayName ?? user.lastName}"
android:text="@{user.displayName != null ? user.displayName : user.lastName}"
````


#### 2/ 避免空指针异常
生成的数据绑定代码自动检查空值，并避免空指针异常。例如，在表达式@ {user.name}中，如果user为null，则为user.name分配其默认值null。如果您引用user.age，age是int类型，则数据绑定将使用默认值0

#### 3/ Collections
````xml
<data>
    <import type="android.util.SparseArray"/>
    <import type="java.util.Map"/>
    <import type="java.util.List"/>
    <variable name="list" type="List&lt;String>"/>
    <variable name="sparse" type="SparseArray&lt;String>"/>
    <variable name="map" type="Map&lt;String, String>"/>
    <variable name="index" type="int"/>
    <variable name="key" type="String"/>
</data>
…
android:text="@{list[index]}"
…
android:text="@{sparse[index]}"
…
android:text="@{map[key]}"
````

Databinding 支持include viewstub，不支持merge

### Observable objects
可观察性是指对象将其数据更改通知他人的能力。数据绑定库使您可以观察对象，字段或集合。 任何普通的旧对象都可以用于数据绑定，但是修改对象不会自动导致UI更新。数据绑定可用于使您的数据对象在数据更改时通知其他对象（称为侦听器）。有三种不同类型的可观察类：对象，字段和集合。 当这些可观察数据对象之一绑定到UI且数据对象的属性更改时，UI将自动更新。

自己总结就是不用代码livedata.observe去监控数据变化影响ui，可以双向绑定ui变化反应到data，data变化可以影响ui

ps: Android Studio 3.1及更高版本允许您用LiveData对象替换可观察字段，这为您的应用程序提供了更多好处。有关更多信息，请参见使用LiveData通知UI有关数据更改。

属性更改通知器
    
### 生成的绑定类

```
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    // 1
    val binding: MyLayoutBinding = MyLayoutBinding.inflate(layoutInflater)
    
    // 2
    val binding: MyLayoutBinding = MyLayoutBinding.inflate(getLayoutInflater(), viewGroup, false)
    
    // 3
    val binding: MyLayoutBinding = MyLayoutBinding.bind(viewRoot)
    
    // 4适用于不知道binding名字
    val viewRoot = LayoutInflater.from(this).inflate(layoutId, parent, attachToParent)
    val binding: ViewDataBinding? = DataBindingUtil.bind(viewRoot)
}
```

##### ViewStub DataBinding

与普通视图不同，ViewStub对象以不可见视图开始。当它们变为可见或被明确告知要膨胀时，它们会通过膨胀另一个布局来替换自身。 因为ViewStub本质上从视图层次结构中消失了，所以绑定对象中的视图也必须消失以允许被垃圾回收声明。因为视图是最终视图，所以ViewStubProxy对象将在生成的绑定类中代替ViewStub，从而使您可以在存在ViewStub时访问ViewStub，也可以在扩展ViewStub时访问已扩展的视图层次结构。 当膨胀另一个布局时，必须为新布局建立一个绑定。因此，ViewStubProxy必须侦听ViewStub OnInflateListener并在需要时建立绑定。由于在给定时间只能存在一个侦听器，因此ViewStubProxy允许您设置OnInflateListener，在建立绑定后将调用它。

````
mBinding.viewStub.setOnInflateListener(new ViewStub.OnInflateListener() {
    @Override
    public void onInflate(ViewStub stub, View inflated) {
        ViewStubBinding binding = DataBindingUtil.bind(inflated);
        binding.setModel(model);
    }
});



public void inflateViewStub(View view) {
    if (!mBinding.viewStub.isInflated()) {
        mBinding.viewStub.getViewStub().inflate();
    }
}
````

##### 立即绑定

当变量或可观察对象发生更改时，绑定将安排在下一帧之前更改。但是，有时绑定必须立即执行。要强制执行，请使用executePendingBindings（）方法。
当你改变了数据以后(在你设置了Observable观察器的情况下)会马上刷新ui, 但是会在下一帧才会刷新UI, 存在一定的延迟时间. 在这段时间内hasPendingBindings()会返回true. 如果想要同步(或者说立刻)刷新UI可以马上调用executePendingBindings().

#### Binding adapters


##### 设置属性值

例如，给定android：text =“ @ {user.name}”表达式，该库将寻找一个setText（arg）方法，该方法接受user.getName（）返回的类型。如果user.getName（）的返回类型为String，则库将查找接受String参数的setText（）方法。如果表达式返回一个int，则库将搜索一个接受int参数的setText（）方法。表达式必须返回正确的类型，必要时可以转换返回值。

即使给定名称不存在任何属性，数据绑定也有效。然后，您可以使用数据绑定为任何设置器创建属性。例如，支持类DrawerLayout没有任何属性，但是有很多设置器。以下布局自动将setScrimColor（int）和setDrawerListener（DrawerListener）方法分别用作app：scrimColor和app：drawerListener属性的设置器：
```xml
<android.support.v4.widget.DrawerLayout
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:scrimColor="@{@color/scrim}"
    app:drawerListener="@{fragment.drawerListener}" />
```
一些属性的设置器名称不匹配。在这些情况下，可以使用BindingMethods批注将属性与设置器关联。该批注与一个类一起使用，并且可以包含多个BindingMethod批注，每个重命名方法均一个。绑定方法是可以添加到应用程序中任何类的注释。在以下示例中，android：tint属性与setImageTintList（ColorStateList）方法关联，而不与setTint（）方法关联：
```kotlin
@BindingMethods(value = [
    BindingMethod(
        type = android.widget.ImageView::class,
        attribute = "android:tint",
        method = "setImageTintList")]){}
```

一些属性需要自定义绑定逻辑。例如，android：paddingLeft属性没有关联的设置器。而是提供了setPadding（left，top，right，bottom）方法。带有BindingAdapter批注的静态绑定适配器方法允许您自定义如何调用属性的setter。 Android框架类的属性已经创建了BindingAdapter批注。例如，以下示例显示paddingLeft属性的绑定适配器：
```kotlin
@BindingAdapter("android:paddingLeft")
fun setPaddingLeft(view: View, padding: Int) {
    view.setPadding(padding,
                view.getPaddingTop(),
                view.getPaddingRight(),
                view.getPaddingBottom())
}
```

##### 与livedata配合使用

应该要设置setLifecycleOwner
设置应用于观察此绑定中* LiveData更改的{@link LifecycleOwner}。如果{@link LiveData}位于绑定表达式*之一中，并且未设置LifecycleOwner，则将不会观察LiveData，并且不会将其更新*传播到UI。

##### 双向绑定（挖坑还没看懂）

## Lifecycle

生命周期感知组件执行操作以响应另一个组件的生命周期状态变化，例如活动和片段。这些组件可帮助您生成组织更好，更轻量的代码，更易于维护。
```kotlin
internal class MyLocationListener(
        private val context: Context,
        private val callback: (Location) -> Unit
) {

    fun start() {
        // connect to system location service
    }

    fun stop() {
        // disconnect from system location service
    }
}

class MyActivity : AppCompatActivity() {
    private lateinit var myLocationListener: MyLocationListener

    override fun onCreate(...) {
        myLocationListener = MyLocationListener(this) { location ->
            // update UI
        }
    }

    public override fun onStart() {
        super.onStart()
        myLocationListener.start()
        // manage other components that need to respond
        // to the activity lifecycle
    }

    public override fun onStop() {
        super.onStop()
        myLocationListener.stop()
        // manage other components that need to respond
        // to the activity lifecycle
    }
}
```
即使该示例看起来不错，在实际的应用程序中，您仍然会响应生命周期的当前状态而进行过多的调用来管理UI和其他组件。管理多个组件会在生命周期方法（例如onStart()和中）中 放置大量代码onStop()，这使它们难以维护。

而且，不能保证组件在活动或片段停止之前就已启动。如果我们需要执行长时间运行的操作（例如检入），则尤其如此onStart()。这可能导致争用情况，其中onStop()方法在之前完成onStart()，从而使组件的生存期超过了所需的生存期。
```kotlin
internal class MyLocationListener(
        private val context: Context,
        private val lifecycle: Lifecycle,
        private val callback: (Location) -> Unit
) {

    private var enabled = false

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() {
        if (enabled) {
            // connect
        }
    }

    fun enable() {
        enabled = true
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            // connect if not connected
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() {
        // disconnect if connected
    }
}
```
使用`lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)`可以判断是否在onstart执行

lifecycle使用
实现lifecycleobserver
```kotlin
class MyLifeCycleObserver : LifecycleObserver {

    private val TAG = "MyLifecycleObserver"

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun created(){
        Log.d(TAG, "Created called")
    }
}
```
AppCompatActivity实现LifeCycleOwner
```kotlin
class MainActivity : AppCompatActivity() {

    private lateinit var lifeCycleObserver: MyLifeCycleObserver
    private lateinit var lifeCycleOwner: MyLifeCycleOwner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifeCycleObserver = MyLifeCycleObserver()
        // 通过调用Lifecycle类的addObserver（）方法并传递观察者的实例来添加观察者
        lifecycle.addObserver(lifeCycleObserver)
    }
}
```

参考：
[architecture-samples](https://github.com/android/architecture-samples)
