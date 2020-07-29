
## COVID19 Tracker App 📱
The COVID19 Tracker app is a mobile version of virus tracker inspired by various tracking web apps. This app has a bunch of new features such as data breakdown visualization of each country using graph charts, maps to provide seamless hover to different affected countries, news and tweets from local media providers to provide updates anywhere in the world.

![https://github.com/forceporquillo/android-henlo-fren/blob/2f01d81cd4e0f8e682db75f24a6ba48e56f15285/preview_screenshot.jpg](https://github.com/forceporquillo/android-henlo-fren/blob/2f01d81cd4e0f8e682db75f24a6ba48e56f15285/preview_screenshot.jpg)


## Summary 📋
This [COVID-19 Tracker App](https://github.com/forceporquillo/android-covid19-tracker) showcase how I  apply what I've learned on learning Mobile Programming in amidst of these pandemic.

This app uses:
-   [MVVM](https://en.wikipedia.org/wiki/Model_View_ViewModel)  architecture pattern to separate and organized module dependencies to make development clean, easy to maintain and update.
-   [Android Databinding](https://developer.android.com/topic/libraries/data-binding/index.html)  with  [LiveData](https://developer.android.com/topic/libraries/architecture/livedata.html) to manage UI lifecycle changes.
-   Asynchronous communications implemented with  [ReactiveX](http://reactivex.io/).
- Converts Rx flowable observable to LiveData using  [LiveDataReactiveStreams](https://developer.android.com/reference/android/arch/lifecycle/LiveDataReactiveStreams).
-   store data using  [Room](https://developer.android.com/topic/libraries/architecture/room.html) database.
-  charts from [https://github.com/PhilJay/MPAndroidChart](https://github.com/PhilJay/MPAndroidChart) to graph data statistically.
- Dagger2 to inject instance dependencies.
- implemented with Facebook shimmers, SmartTabLayouts, Pagination.
- OkHttpInterceptor (chain calls) for Oauth2 bearer token authentication in Twitter API.
- Google Maps SDK, Twitter API and various COVID-19 related API's .
- LeakCanary for detecting memory leaks.

## Prerequisites 📍⚠️
This project requires a newer version of Android Studio such as 
- Android Studio 4.0
- AndroidX dependencies 
-- migrate to  [https://developer.android.com/jetpack/androidx/migrate](https://developer.android.com/jetpack/androidx/migrate)

- Java 8 or later.

- Google Maps SDK API - you can obtain your own api here [https://developers.google.com/maps/documentation/android-sdk/overview](https://developers.google.com/maps/documentation/android-sdk/overview)

```
// Read the API key from demo/secure.properties into R.string.maps_api_key  
def secureProps = new Properties()  
if (file("secure.properties").exists()) {  
    file("secure.properties")?.withInputStream { secureProps.load(it) }  
}  
resValue "string", "maps_api_key", (secureProps.getProperty("MAPS_API_KEY") ?: "")  

// To add your Maps API key to this project:  
// 1. Create a file demo/secure.properties  
// 2. Add this line, where YOUR_API_KEY is your API key: MAPS_API_KEY=YOUR_API_KEY
```

## Features 🚀 🔥
- **Android Architecture Components** - Lifecycle aware achieved by using a combination of RxJava, LiveData, ViewModels and Room.

- **MVVM Architecture** - Using the lifecycle aware ViewModels, the view observes changes in the model / repository.

- **Offline First Architecture** - All the data is first tried to be loaded from the local database and then updated from the server. This ensures that the app is usable even in an offline mode.

- **Modular and Adaptive UI Design** -  Adaps to different screen sizes and densities of device.

- **Modular** - The app is broken into modules of features and libraries which can be combined to build instant-apps, complete apps or lite version of apps.

-   **Intelligent Sync**  - Intelligent hybrid syncing logic makes sure this app does not make repeated calls to the same back-end API for the same data in a particular time period.

- **Reactive Networking** - This observes network configuration, and systematically adapts to any network condition and network configuration without requiring manual reconfiguration. This notifies the UI Thread.

## Dependencies/Libraries 📚🛠
- [AndroidX](https://developer.android.com/jetpack/androidx)
- [Timber](https://github.com/JakeWharton/timber)
- [Gson Converter](https://github.com/square/retrofit/tree/master/retrofit-converters/gson)
- [Retrofit 2](https://square.github.io/retrofit/)
- [Data Binding](https://developer.android.com/topic/libraries/data-binding)
- [OkHttp 3](https://square.github.io/okhttp/)
- [RxNetwork](https://github.com/greyfoxit/RxNetwork)
- [Dagger 2](https://github.com/google/dagger)
- [LiveData](https://developer.android.com/reference/androidx/lifecycle/LiveData)
- [Room](https://developer.android.com/topic/libraries/architecture/room)
- [RxJava 2](https://github.com/ReactiveX/RxJava)
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [LiveDataReactiveStreams](https://developer.android.com/reference/android/arch/lifecycle/LiveDataReactiveStreams)
- [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart)
- [Glide](https://github.com/bumptech/glide)
- [Facebook Shimmer](https://facebook.github.io/shimmer-android/)
- [Paging](https://developer.android.com/topic/libraries/architecture/paging)
- [SwipeRefresh](https://developer.android.com/jetpack/androidx/releases/swiperefreshlayout)
- [Google Maps SDK](https://developers.google.com/maps/documentation)
- [SmartTabLayout](https://github.com/ogaclejapan/SmartTabLayout)
- [LeakCanary](https://square.github.io/leakcanary/)

## Data Sources - REST API 📊
**COVID-19 Data Repository by the Center for Systems Science and Engineering (CSSE) at Johns Hopkins University** - [https://github.com/CSSEGISandData/COVID-19](https://github.com/CSSEGISandData/COVID-19)

**COVID API NOVEL** - [https://corona.lmao.ninja/ ](https://corona.lmao.ninja/)

**NEWS ORG** - [https://newsapi.org/](https://newsapi.org/)

**Twitter-API** - [https://developer.twitter.com/en/docs](https://developer.twitter.com/en/docs) 

**DiseaseSh**- [https://corona.lmao.ninja/](https://corona.lmao.ninja/)

**Johns Hopkins CSSE** -  [(https://github.com/CSSEGISandData/COVID-19](https://github.com/CSSEGISandData/COVID-19)

**COVID19 API**-  [https://covid19api.com/](https://covid19api.com/)

**European Centre for Disease Prevention and Control (ECDC)** - [https://www.ecdc.europa.eu/en](https://www.ecdc.europa.eu/en)

**PH DATASETS/DOH**
- **Katerinahronik** - [https://apify.com/katerinahronik/covid-philippines](https://apify.com/katerinahronik/covid-philippines) from 
- **DOH** [https://ncovtracker.doh.gov.ph/](https://ncovtracker.doh.gov.ph/)
- **Sorxrob Github** -- [https://github.com/sorxrob/coronavirus-ph-api]

## Contributing 🧑🏻‍🔧📲
I love contributions! There is a lot to do in this app so why not chat or email me about what you're interested in doing? Or you can directly submit a [pull request](https://github.com/forceporquillo/android-covid19-tracker/pulls) and I'd to love merged it.

If you find some issues about this app you can submit a [issue](https://github.com/forceporquillo/android-covid19-tracker/issues) here.

## Author 👨🏻‍💻🇵🇭

**Force Porquillo** <br/>
[
![https://github.com/forceporquillo/android-covid19-tracker/blob/master/gmail_icon.png|width=15px](https://github.com/forceporquillo/android-covid19-tracker/blob/master/gmail_icon.png)](fporquillo18@gmail.com)

[
![https://github.com/forceporquillo/android-covid19-tracker/blob/master/github_icon.png|width=15px](https://github.com/forceporquillo/android-covid19-tracker/blob/master/github_icon.png)](https://github.com/forceporquillo)

[
![https://github.com/forceporquillo/android-covid19-tracker/blob/master/twitter_icon.png|width=15px](https://github.com/forceporquillo/android-covid19-tracker/blob/master/twitter_icon.png)](https://www.twitter.com/tito_4s)

## Find this project useful ?  ❤️🔥💯
-   Support it by clicking the  ⭐ or ☕🙏

[![Foo](https://camo.githubusercontent.com/4ba50f927b0ea667976efa9f43ae166caa2c4cf7/68747470733a2f2f63646e2e6275796d6561636f666665652e636f6d2f627574746f6e732f6c61746f2d6f72616e67652e706e67)](https://www.buymeacoffee.com/forcecodes)
## License

Copyright 2020 Force Porquillo

Licensed under the Apache License, Version 2.0 (the "License");  
you may not use this file except in compliance with the License.  
You may obtain a copy of the License at  

 [http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)
   
Unless required by applicable law or agreed to in writing, software  
distributed under the License is distributed on an "AS IS" BASIS,  
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  
See the License for the specific language governing permissions and  
limitations under the License.