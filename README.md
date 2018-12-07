# EasyWebview
[![](https://jitpack.io/v/BugSir/EasyWebview.svg)](https://jitpack.io/#BugSir/EasyWebview)
# 引用方法:<br/>
<pre><code>
工程目录gradle
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
项目gradle
	dependencies {
	        implementation 'com.github.BugSir:EasyWebview:1.0.4'
	}
</code></pre>
# 使用：<br/>
### 一、简单用法
#### 1、创建自己的Model类 MyWebViewModel 实现getLayout（）方法，返回带webview的View
```java 
    @Override
    public View getLayout() {
        WebView webView=new WebView(getModelCallback().getModelActivity());
        this.mWebView=webView;
        return webView;
    }
 ```
 #### 2、使用EasyWebUtil类跳转到web界面
 ```java
 //mBundle是你要带到model的参数model.getData()即可拿到
 EasyWebUtil.startActivity(this,MyWebViewModel.class,null,"www.baidu.com",mBundle);
 ```
 ### 二、我是个懒人不想同一个Model到处都要写一遍？EasyWebConfig 帮你
 ```java
 //在任意地方调用（最好在application初始化）
 EasyWebConfig.getInstance().init(MyWebViewModel.class,null);
 //愉快的调起来吧
 EasyWebUtil.startActivity(this,"www.baidu.com",mBundle);
 EasyWebUtil.startActivity(this,OtherWebModel.class,"www.baidu.com",mBundle);
 ```
 ### 三、有自己的BaseActivity，我还能用EasyWebview不？当然可以！参照EasyWebActivity重写一个继承你的BaseActivity
 #### 1、重写EasyWebActivity并继承自己的BaseActivity，把EasyWebFragmentHelper 实现了
 ```java
 public    class MyWebActivity extends BaseActivity   {

    private EasyWebFragmentHelper mEasyUtil;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_web);
        mEasyUtil=EasyWebFragmentHelper.create(getIntent()).replace(getSupportFragmentManager(),R.id.flyt_easy);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (mEasyUtil.onKeyDown(keyCode,event))
        {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        mEasyUtil.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mEasyUtil.destroy();
        mEasyUtil=null;
    }
}
 ```
 #### 2、设置并使用你自己的webactivity
 ```java
 EasyWebUtil.startActivity(this,MyWebViewModel.class,MyWebActivity.class,"www.baidu.com",mBundle);
 或者
 //默认配置改成自己的
 EasyWebConfig.getInstance().init(MyWebViewModel.class,MyWebActivity.class);
 //调用
 EasyWebUtil.startActivity(this,"www.baidu.com",mBundle);
 ```
 ### 四、我的fragment也是需要继承BaseFragment的怎么办？一句话Copy EasyWebFragment 以及IBaseFragmentImpl与IModelCallBack接口
 ```java
 EasyWebConfig.getInstance().setWebFragmentClass(MyWebFragment);
 或
 EasyWebConfig.getInstance().init(MyWebViewModel.class,MyWebActivity.class,MyWebFragment);
 ```
 ### 五、Model类方法的解释以及使用
 
 ### 六、我使用的第三方的webView怎么办？这个目前你能做的是参照BaseWebviewModel 继承 BaseModel<T> 自己拓展实现了（请原谅博主的懒）
  ```java
  对，就是以下这坨，实现它！
  public abstract class BaseModel<T> implements IBaseModelImpl {


    /**
     * 界面UI
     */
    protected View mMainView;

    /**
     * webview
     */
    protected T mWebView;

    protected Bundle mBundle;

    public String mUrl;

    public IModelCallBack callBack;

    @Override
    public void setData(Bundle bundle) {
        this.mBundle = bundle;
    }

    @Override
    public Bundle getData() {
        return mBundle;
    }

    @Override
    public String getUrl() {
        return mUrl;
    }

    @Override
    public void setModelCallback(IModelCallBack callback) {
        this.callBack = callback;
    }

    @Override
    public IModelCallBack getModelCallback() {
        return callBack;
    }

    @Override
    public String jsCallBack(String json) {
        return null;
    }

    @Override
    public void callOtherMethod() {

    }

    @Override
    public void loadUrl(String url) {
        this.mUrl = url;
    }

    //--------------------其它拓展类--------------------
    protected boolean isSetWebCache() {
        return false;
    }
}
  ```
