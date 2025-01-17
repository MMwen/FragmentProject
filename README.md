# 前言
学习```Kotlin```有一段时间了，想写一个项目总结收获，就有了这个可能是东半球最简洁的玩安卓客户端，在此感谢[玩Android](https://www.wanandroid.com/) 的开放API。
# 简介
采用 Kotlin 语言编写，单Activity多Fragment，MVVM + ViewModel + LiveData + Retrofit + 协程 + ViewBinding等架构设计的项目，项目结构清晰，代码简介优雅
# 截图展示
| ![1.jpg](https://gitee.com/zhao.git/FragmentProject/raw/master/screenshot/Screenshot_1621158973.png) | ![2.jpg](https://gitee.com/zhao.git/FragmentProject/raw/master/screenshot/Screenshot_1621155363.png) | ![3.jpg](https://gitee.com/zhao.git/FragmentProject/raw/master/screenshot/Screenshot_1621155387.png) |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| ![4.jpg](https://gitee.com/zhao.git/FragmentProject/raw/master/screenshot/Screenshot_1621155408.png) | ![5.jpg](https://gitee.com/zhao.git/FragmentProject/raw/master/screenshot/Screenshot_1621155418.png) | ![6.jpg](https://gitee.com/zhao.git/FragmentProject/raw/master/screenshot/Screenshot_1621155439.png) |
# Fragment + ViewMode + LiveData
ViewMode和LiveData的概念就不再赘述，这里简单介绍下它们三者之间的关系：  
1、ViewModel是一个抽象类通过继承实现我们自己的ViewModel类，代码如下
```
   class MyViewModel : ViewModel(){
      // 使用LiveData存放数据
      val result = MutableLiveData<String>()
      
      fun getData(){
         val data = "Test" //这里获取数据为示例代码，请根据实际情况从网络或本地获取
         result.postValue(data)
      }
   }
```
2、Fragment里面初始化ViewModel，并观察观察Viewmodel的Livedata，如果Livedata的值改变会通知Frament，代码如下
```
   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      val viewModel = ViewModelProvider(this as ViewModelStoreOwner).get(MyViewModel::class.java)
      viewModel.result.observe(viewLifecycleOwner, {it->
         println(it)
      })
   }
```
3、ViewModel获取数据（网络、数据库），然后设置Livedata的值，通过Livedata通知Frament刷新界面，代码如下
```
   viewModel.getData()
```
# 自定义路由实现页面跳转的前置拦截，从而进行各种登录态等校验
因为是单activity多fragment设计，所以单activity的作用之一就是用来控制fragment的切换。MainActivity有个switcher方法用来切换fragmen不用多说。  
重点看navigation方法，页面的前置拦截就是在这个方法里面实现的，原理也很简单就是通过Fragment类名进行拦截在做相应的处理。
```
   fun navigation(clazz: Class<out Fragment>, bundle: Bundle?, addToBackStack: Boolean) {
        if (aspectFragments.contains(clazz) && !isLogin()) {
            switcher(LoginFragment::class.java, bundle, addToBackStack)
        } else {
            switcher(clazz, bundle, addToBackStack)
        }
    }

    private val aspectFragments = listOf(
        MyCoinFragment::class.java,
        MyCollectArticleFragment::class.java,
        MyShareArticleFragment::class.java
    )
```
# 主要开源框架
- [square/okhttp](https://github.com/square/okhttp)
- [square/retrofit](https://github.com/square/retrofit)
- [google/gson](https://github.com/google/gson)
- [bumptech/glide](https://github.com/bumptech/glide)
# Thanks
  感谢所有优秀的开源项目 ^_^   
  如果喜欢的话希望给个 Star 或 Fork ^_^  
  谢谢~~  
# LICENSE
```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
