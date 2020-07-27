

[![Foo](https://camo.githubusercontent.com/4ba50f927b0ea667976efa9f43ae166caa2c4cf7/68747470733a2f2f63646e2e6275796d6561636f666665652e636f6d2f627574746f6e732f6c61746f2d6f72616e67652e706e67)](https://www.buymeacoffee.com/forcecodes)
## COVID19 Tracker App
The COVID19 Tracker app a mobile version of virus tracker inspired by different tracking web apps. This app has a bunch of new features such as data breakdown visualization of each country using graph charts, maps to provide seamless hover to different affected countries, news and tweets from local media providers to provide updates anywhere in the world.

## Summary
-   Uses  [MVVM](https://en.wikipedia.org/wiki/Model_View_ViewModel)  architecture pattern to break and organize module dependencies to make development clean, organized, easy to maintain and update.
-   Uses  [Android Databinding](https://developer.android.com/topic/libraries/data-binding/index.html)  with  [LiveData](https://developer.android.com/topic/libraries/architecture/livedata.html) to manage UI lifecycle changes.
-   Asynchronous communications implemented with  [ReactiveX](http://reactivex.io/).
- Converts Rx flowable observable to LiveData using  [LiveDataReactiveStreams](https://developer.android.com/reference/android/arch/lifecycle/LiveDataReactiveStreams).
-   Store data using  [Room](https://developer.android.com/topic/libraries/architecture/room.html) database.
-  Uses Charts from [https://github.com/PhilJay/MPAndroidChart](https://github.com/PhilJay/MPAndroidChart) to graph data statistically.
- Uses Dagger2 to inject instance dependencies.
## Prerequisites
This project requires a newer version of Android Studio such as 
- Android Studio 4.0
- AndroidX dependencies 
-- migrate to  [https://developer.android.com/jetpack/androidx/migrate](https://developer.android.com/jetpack/androidx/migrate)

- Java 8 or later.

- Google Maps SDK API - you can obtain your own api here - [https://developers.google.com/maps/documentation/android-sdk/overview](https://developers.google.com/maps/documentation/android-sdk/overview)

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

## Features
- **Android Architecture Components** - Lifecycle awareness has been achieved using a combination of LiveData, ViewModels and Room.

- **MVVM Architecture** - Using the lifecycle aware viewmodels, the view observes changes in the model / repository.

- **Offline First Architecture** - All the data is first tried to be loaded from the local database and then updated from the server. This ensures that the app is usable even in an offline mode.

- **Modular and Adaptive UI Design** -  Adaps to different screen sizes and densities of device.

- **Modular** - The app is broken into modules of features and libraries which can be combined to build instant-apps, complete apps or lite version of apps.

-   **Intelligent Sync**  - Intelligent hybrid syncing logic makes sure this app does not make repeated calls to the same back-end API for the same data in a particular time period.

- **Reactive Networking** - This observes network configuration, and systematically adapts to any network condition and network configuration without requiring manual reconfiguration. This notifies the UI Thread.

- **Multithreading** - These separates both network and local repository to reactively synchronize each task independently.

- **Asynchronous Programming** - Work runs separately from the main baseApplication thread and notifies the calling thread of its completion, failure or progress.

## Core Dependencies
**Android Architecture Components**:  [Google Github repository](https://github.com/android/architecture-components-samples)
- **ViewModel** - Save the activity/fragment state across configuration changes.  
- **LiveData** - observes the data changes in local database and automatically emmits data in our UI whenever new data is inserted in DB.
 - **PageList** -  loads data in gradual chunks (pages) from a **DataSource**.
- **Room** - stores country information in local database for offline compatibility.
- **Data Binding** - bind the UI components in the layout to **data** resources using a declarative format rather than programmatically.
- **Google Maps API SDK** - provides map visualization to draw markers in affected areas.
- **Dagger 2** - injects relying object dependencies; reduces boilerplate code when instantiating object instances.

**ReactiveX 2.x**
- **RxJava/Android** - Composing asynchronous and event-based programs by using observable sequences.

- **RxNetwork** - observes **network connection state** and **internet connectivity** with approach to **RxJava Observables.**

## Preview Screenshots
![https://github.com/forceporquillo/android-henlo-fren/blob/2f01d81cd4e0f8e682db75f24a6ba48e56f15285/preview_screenshot.jpg](https://github.com/forceporquillo/android-henlo-fren/blob/2f01d81cd4e0f8e682db75f24a6ba48e56f15285/preview_screenshot.jpg)

## Data Sources - REST API
**COVID API NOVEL** - [https://corona.lmao.ninja/ ](https://corona.lmao.ninja/)
<br />**Twitter-API** - [https://developer.twitter.com/en/docs](https://developer.twitter.com/en/docs) 
<br />**DiseaseSh**- [https://corona.lmao.ninja/](https://corona.lmao.ninja/)
<br />**Johns Hopkins CSSE**-  [(https://github.com/CSSEGISandData/COVID-19](https://github.com/CSSEGISandData/COVID-19)
<br />**PH DATASETS/DOH** 
- Katerinahronik -- [https://apify.com/katerinahronik/covid-philippines](https://apify.com/katerinahronik/covid-philippines) from [https://ncovtracker.doh.gov.ph/](https://ncovtracker.doh.gov.ph/)
<br />
- Sorxrob Github COVID19 PH API -- [https://github.com/sorxrob/coronavirus-ph-api]

<br />**COVID19 API**-  [https://covid19api.com/](https://covid19api.com/)
<br />**European Centre for Disease Prevention and Control (ECDC)** - [https://www.ecdc.europa.eu/en](https://www.ecdc.europa.eu/en)
<br />**COVID-19 Data Repository by the Center for Systems Science and Engineering (CSSE) at Johns Hopkins University** -[JHU-CSSE Github](https://github.com/CSSEGISandData/COVID-19)

## Author

**Force Porquillo** - fporquillo18@gmail.com
* [Github](https://github.com/forceporquillo)
* [Twitter](https://twitter.com/tito_4s)

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
