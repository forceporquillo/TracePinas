## HENLO FREN
A COVID19 tracker and self-assessment test app 
(Inspired from Corona Tracker a web-version)
=======
WALA PANG PROJECT DESCRIPTION DI PA TAPOS.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.
>>>>>>> 62c2a9ab57b37f55eef4541370a5555294caf470

## Summary
-   Use  [MVVM](https://en.wikipedia.org/wiki/Model_View_ViewModel)  using  [architecture components](https://developer.android.com/topic/libraries/architecture/index.html)  with to separate Android Framework with a  [clean architecture](http://blog.8thlight.com/uncle-bob/2012/08/13/the-clean-architecture.html)  to my domain logic.
-   Use  [Android Databinding](https://developer.android.com/topic/libraries/data-binding/index.html)  wih  [LiveData](https://developer.android.com/topic/libraries/architecture/livedata.html)  to glue  [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel.html)  and Android.
-   Asynchronous communications implemented with  [Rx](http://reactivex.io/).
-   Rest API from  [Novel COVID API](https://corona.lmao.ninja/).
-   Store data using  [Room](https://developer.android.com/topic/libraries/architecture/room.html).
-  Uses Pie Charts from [https://github.com/PhilJay/MPAndroidChart](https://github.com/PhilJay/MPAndroidChart) to graph data statistically.
## Installation
This project requires a newer version of Android Studio such as 
- Android Studio 4.0
- AndroidX dependencies 
-- migrate to  [https://developer.android.com/jetpack/androidx/migrate](https://developer.android.com/jetpack/androidx/migrate)

- Java 8 or later.

## Features
- **Android Architecture Components** - Lifecycle awareness has been achieved using a combination of LiveData, ViewModels and Room.

- **MVVM Architecture** - Using the lifecycle aware viewmodels, the view observes changes in the model / repository.

- **Offline First Architecture** - All the data is first tried to be loaded from the local database and then updated from the server. This ensures that the app is usable even in an offline mode.

- **Java**  - This app is completely written in Java both front-end (with XML) and back-end.

- **Modular and Adaptive UI Design** -  Adaps to different screen sizes and densities.

- **Modular** - The app is broken into modules of features and libraries which can be combined to build instant-apps, complete apps or lite version of apps.

-   **Intelligent Sync**  - Intelligent hybrid syncing logic makes sure your Android app does not make repeated calls to the same back-end API for the same data in a particular time period.

- **Reactive Networking** - This observes network configuration, and systematically adapts to any network condition and network configuration without requiring manual reconfiguration. This notifies the UI Thread.

- **Multithreading** - These separates both network and local repository to reactively synchronize each task independently.

- **Asynchronous Programming** - Work runs separately from the main application thread and notifies the calling thread of its completion, failure or progress.

## Core Dependencies
**Android Architecture Components**:  [Google Github repository](https://github.com/android/architecture-components-samples)
- **ViewModel** - Save the activity/fragment state across configuration changes.  
- **LiveData** - A data holder class that observes the data changes in our database and automatically emmits data in our UI whenever new data is inserted in DB.
 - **PageList** - Loads data in gradual chunks (pages) from a **DataSource**.
- **Room** - stores country data in local database.
- **Data Binding** - bind the UI components in the layout to **data** resources using a declarative format rather than programmatically.

**ReactiveX 2.x**

-- RxJava/Android
- Composing asynchronous and event-based programs by using observable sequences.

-- RxNetwork
- Listening **network connection state** and **Internet connectivity** with approach to **RxJava Observables.**

**Retrofit2**  
- The application get the content by http request using OkHttp3.  
 - Parse the response via JsonDeserializer.  
 - Create data objects via Gson.  
 - Allow to debug the response via Logging-interceptor (okhttp).  
  
**Glide** 
- For loading and caching images.  
  
**RecyclerView**  
- Display the country data as list in effectively.  

**Leak Canary** - memory leaks detection.

**Google Maps**
**PieView**
**SwipeRefreshLayout**
**HTTP Interceptor**
**Animated Bottombar**


## Network Repository API


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
